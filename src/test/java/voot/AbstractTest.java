package voot;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        value = {
                "externalProviders.config.path=classpath:/testExternalProviders.yml",
                "oidcng.checkToken.endpoint_url=http://localhost:8889/introspect",
                "checkToken.cache=false",
                "support.linkedGrouperExternalGroups=true"
        }
)
public abstract class AbstractTest {

    protected String admin = "urn:collab:person:example.org:admin";
    protected String UID = "admin";
    protected String GROUP_ID = "nl:surfnet:diensten:apachecon";
    protected String USER_URN = "urn:collab:person:example.org:" + UID;
    protected String GROUP_URN = "urn:collab:group:surfteams.nl:" + GROUP_ID;

    @Autowired
    protected ObjectMapper objectMapper;

    @RegisterExtension
    WireMockExtension mockServer = new WireMockExtension(8889);

    @RegisterExtension
    WireMockExtension mockTeamsServer = new WireMockExtension(8999);

    @LocalServerPort
    protected int port;

    @BeforeEach
    protected void beforeEach() {
        RestAssured.port = port;
    }

    protected String opaqueAccessToken(String responseJsonFileName, String... scopes) throws IOException {
        List<String> scopeList = new ArrayList<>(Arrays.asList(scopes));
        scopeList.add("openid");

        String introspectResult = IOUtils.toString(new ClassPathResource(responseJsonFileName).getInputStream(), Charset.defaultCharset().name());
        String introspectResultWithScope = String.format(introspectResult, String.join(" ", scopeList));
        mockServer.stubFor(post(urlPathMatching("/introspect")).willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(introspectResultWithScope)));
        return UUID.randomUUID().toString();
    }

    protected void doStubOAuthCheckToken(String path) throws IOException {
        InputStream inputStream = new ClassPathResource(path).getInputStream();
        String json = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
        stubFor(post(urlPathEqualTo("/introspect")).willReturn(
                aResponse().
                        withStatus(200).
                        withHeader("Content-type", "application/json").
                        withBody(json)
        ));
    }

    protected void stubCallVoot2(String queryPart, String responseFile) throws IOException {
        doStub(queryPart, responseFile, mockServer);
    }

    protected void stubCallTeams(String queryPart, String responseFile) throws IOException {
        doStub(queryPart, responseFile, mockTeamsServer);
    }

    private void doStub(String queryPart, String responseFile, WireMockExtension mockServer) throws IOException {
        String response = StreamUtils.copyToString(new ClassPathResource(responseFile).getInputStream(), Charset.forName("UTF-8"));
        String query = queryPart.startsWith("/") ? queryPart : "/" + queryPart;
        mockServer.stubFor(get(urlEqualTo(query)).willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(response)));
    }


}
