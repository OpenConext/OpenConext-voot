package voot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icegreen.greenmail.store.FolderException;
import guests.config.HashGenerator;
import guests.domain.*;
import guests.repository.*;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.Period;
import java.util.*;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles(value = "test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "oidc.introspection_uri=http://localhost:8081/introspect",
                "email.environment=test"
        })
@SuppressWarnings("unchecked")
public abstract class AbstractTest {

    protected final String INVITATION_UTRECHT_HASH = HashGenerator.generateHash();
    protected final String INVITATION_EMAIL_EQUALITY_HASH = HashGenerator.generateHash();
    protected final String INVITATION_UVA_HASH = HashGenerator.generateHash();

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected InstitutionRepository institutionRepository;

    @Autowired
    protected ApplicationRepository applicationRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected UserRoleRepository userRoleRepository;

    @Autowired
    protected InvitationRepository invitationRepository;

    @Autowired
    protected RoleRepository roleRepository;

    @Autowired
    protected SCIMFailureRepository scimFailureRepository;

    @RegisterExtension
    WireMockExtension mockServer = new WireMockExtension(8081);

    @LocalServerPort
    protected int port;

    @BeforeAll
    protected static void beforeAll() {
        RestAssured.config = RestAssuredConfig.config()
                .objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                        new ConfigurableJackson2ObjectMapperFactory()));
    }

    @BeforeEach
    protected void beforeEach() throws FolderException {
        institutionRepository.deleteAll();
        userRepository.deleteAll();
        seed();
        RestAssured.port = port;
    }

    private void seed() {
        SecurityContextHolder.getContext().setAuthentication(new TestingAuthenticationToken("Principal", "N/A", "ADMIN"));
        List<Institution> institutions = Arrays.asList(
                this.institution("Utrecht"),
                this.institution("UVA")
        );
        institutionRepository.saveAll(institutions);

        Institution utrecht = institutions.get(0);
        List<Application> applications = Arrays.asList(
                this.application(utrecht, "CANVAS"),
                this.application(institutions.get(1), "blackboard")
        );
        applicationRepository.saveAll(applications);

        Role administratorCanvas = new Role("administratorCanvas", applications.get(0));
        administratorCanvas.setServiceProviderId(UUID.randomUUID().toString());
        administratorCanvas = roleRepository.save(administratorCanvas);

        Role guestCanvas = new Role("guestCanvas", applications.get(0));
        guestCanvas.setServiceProviderId(UUID.randomUUID().toString());
        guestCanvas = roleRepository.save(guestCanvas);

        Role guestBlackboard = new Role("guestBlackboard", applications.get(1));
        guestBlackboard.setServiceProviderId(UUID.randomUUID().toString());
        guestBlackboard = roleRepository.save(guestBlackboard);

        User mary = user(utrecht, Authority.INSTITUTION_ADMINISTRATOR, "admin@utrecht.nl", "Mary", "Doe", "admin@utrecht.nl");
        UserRole userRole = new UserRole(administratorCanvas, Instant.now().plus(Period.ofDays(90)));
        userRole.setServiceProviderId(UUID.randomUUID().toString());
        mary.addUserRole(userRole);
        mary.addAup(new Aup(utrecht));

        User inviter = user(utrecht, Authority.INVITER, "inviter@utrecht.nl", "inv", "iter", "inviter@utrecht.nl");

        User guest = user(utrecht, Authority.GUEST, "guest@utrecht.nl", "fn", "ln", "guest@utrecht.nl");
        UserRole guestUserRole = new UserRole(guestCanvas, Instant.now().plus(Period.ofDays(90)));
        guestUserRole.setServiceProviderId(UUID.randomUUID().toString());
        guest.addUserRole(guestUserRole);
        guest.addAup(new Aup(utrecht));

        List<User> users = Arrays.asList(mary, guest, inviter);
        userRepository.saveAll(users);

        Invitation invitation = new Invitation(Authority.INVITER, Status.OPEN, INVITATION_UTRECHT_HASH, mary, utrecht, "guest@test.com");
        invitation.addInvitationRole(new InvitationRole(administratorCanvas));
        invitation.setMessage("Please join...");

        Invitation invitationEmailEquality = new Invitation(Authority.INVITER, Status.OPEN, INVITATION_EMAIL_EQUALITY_HASH, mary, utrecht, "equals@test.com");
        invitationEmailEquality.addInvitationRole(new InvitationRole(administratorCanvas));
        invitationEmailEquality.setEnforceEmailEquality(true);

        Invitation invitationUva = new Invitation(Authority.INVITER, Status.OPEN, INVITATION_UVA_HASH, mary, institutions.get(1), "guest@test.com");
        invitationUva.addInvitationRole(new InvitationRole(guestBlackboard));

        List<Invitation> invitations = Arrays.asList(invitation, invitationEmailEquality, invitationUva);
        invitationRepository.saveAll(invitations);
    }


    protected User user(Institution institution, Authority authority, String eppn, String givenName, String familyNmae, String email) {
        return new User(authority, eppn, eppn, givenName, familyNmae, email, institution);
    }

    protected String opaqueAccessToken(String eppn, String responseJsonFileName, String... scopes) throws IOException {
        List<String> scopeList = new ArrayList<>(Arrays.asList(scopes));
        scopeList.add("openid");

        String introspectResult = IOUtils.toString(new ClassPathResource(responseJsonFileName).getInputStream(), Charset.defaultCharset().name());
        String introspectResultWithScope = String.format(introspectResult, eppn, String.join(" ", scopeList), eppn);
        stubFor(post(urlPathMatching("/introspect")).willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(introspectResultWithScope)));
        return UUID.randomUUID().toString();
    }

    protected Map<String, Object> convertObjectToMap(Object o) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(o);
        return objectMapper.readValue(json, Map.class);
    }

    protected User user() {
        Institution institution = new Institution();
        institution.setDisplayName("University");
        UUID uuid = UUID.randomUUID();
        institution.setEntityId("https://entity/" + uuid);
        institution.setHomeInstitution("university_" + uuid);
        return user(institution, Authority.SUPER_ADMIN, "eppn@example.com", "John", "Doe", "jdoe@example.com");
    }

    protected Application application(Institution institution, String entityId) {
        return new Application(institution, entityId, "https://landing.nl", "http://localhost:8081", "inviter", "secret");
    }

    protected Institution institution(String base) {
        String baseLowerCase = base.toLowerCase();
        return new Institution(base,
                "https://" + baseLowerCase,
                baseLowerCase + ".nl",
                "https://" + baseLowerCase + ".nl/aup",
                1);
    }

    protected void stubForDeleteUser() {
        stubFor(delete(urlPathMatching("/scim/v1/users/(.*)"))
                .willReturn(aResponse()
                        .withStatus(201)));
    }

    protected void stubForDeleteRole() {
        stubFor(delete(urlPathMatching("/scim/v1/groups/(.*)"))
                .willReturn(aResponse()
                        .withStatus(201)));
    }

    protected String stubForCreateRole() throws JsonProcessingException {
        String value = UUID.randomUUID().toString();
        String body = objectMapper.writeValueAsString(Collections.singletonMap("id", value));
        stubFor(post(urlPathMatching(String.format("/scim/v1/groups")))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));
        return value;
    }

    protected String stubForCreateUser() throws JsonProcessingException {
        String value = UUID.randomUUID().toString();
        String body = objectMapper.writeValueAsString(Collections.singletonMap("id", value));
        stubFor(post(urlPathMatching(String.format("/scim/v1/users")))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));
        return value;
    }

    protected String stubForUpdateRole() throws JsonProcessingException {
        String value = UUID.randomUUID().toString();
        String body = objectMapper.writeValueAsString(Collections.singletonMap("id", value));
        stubFor(patch(urlPathMatching(String.format("/scim/v1/groups/(.*)")))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));
        return value;
    }

    @SneakyThrows
    protected String stubForUpdateUser() {
        String value = UUID.randomUUID().toString();
        String body = objectMapper.writeValueAsString(Collections.singletonMap("id", value));
        stubFor(patch(urlPathMatching(String.format("/scim/v1/users/(.*)")))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));
        return value;
    }

    protected Institution getInstitution(User user) {
        return user.getInstitutionMemberships().iterator().next().getInstitution();
    }

    protected User seedUser() {
        User user = user();
        Institution institution = getInstitution(user);
        institutionRepository.save(institution);

        Application application = this.application(institution, "https://entity");
        String provisioningUri = "http://localhost:8081";
        application.setProvisioningHookUrl(provisioningUri);
        application.setProvisioningHookUsername("user");
        application = applicationRepository.save(application);

        Role role = new Role("administrator", application);
        role = roleRepository.save(role);

        user.addUserRole(new UserRole(role, Instant.now().plus(Period.ofDays(365))));
        return userRepository.save(user);
    }


}
