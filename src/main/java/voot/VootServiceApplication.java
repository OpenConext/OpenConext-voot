package voot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.trace.http.HttpTraceEndpointAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import voot.provider.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication(exclude = {HttpTraceEndpointAutoConfiguration.class, MetricsAutoConfiguration.class})
public class VootServiceApplication {

    @Value("${support.linkedGrouperExternalGroups}")
    private boolean supportLinkedGrouperExternalGroups;

    public static void main(String[] args) {
        SpringApplication.run(VootServiceApplication.class, args);
    }

    @Bean
    @Autowired
    public ExternalGroupsService externalGroupsService(
            @Value("${externalProviders.config.path}") final Resource configResource) throws IOException {

        Yaml yaml = new Yaml(new SafeConstructor());

        @SuppressWarnings("unchecked")
        Map<String, List<Map<String, Object>>> config = yaml.load(configResource.getInputStream());
        final List<Map<String, Object>> externalGroupProviders = config.get("externalGroupProviders");

        final List<Provider> groupClients = externalGroupProviders.stream().map(entryMap -> {
            final String type = (String) entryMap.get("type");
            final String url = StringUtils.trimTrailingCharacter((String) entryMap.get("url"), '/');
            final String schacHomeOrganization = (String) entryMap.get("schacHomeOrganization");
            final String name = (String) entryMap.get("name");
            final Integer timeoutMillis = (Integer) entryMap.get("timeoutMillis");
            @SuppressWarnings("unchecked") final Map<String, Object> rawCredentials = (Map<String, Object>) entryMap.get("credentials");
            String username = (String) rawCredentials.get("username");
            String secret = (String) rawCredentials.get("secret");

            GroupProviderType groupProviderType = GroupProviderType.valueOf(type.toUpperCase());

            final Provider.Configuration configuration = new Provider.Configuration(groupProviderType, url, new Provider.Configuration.Credentials(username, secret), timeoutMillis, schacHomeOrganization, name);
            switch (groupProviderType) {
                case VOOT2:
                    return new Voot2Provider(configuration);
                case OPEN_SOCIAL:
                    return new OpenSocialClient(configuration);
                case TEAMS:
                    return new TeamsProviderClient(configuration);
                case OPEN_SOCIAL_MEMBERS:
                    return new OpenSocialMembersClient(configuration);
                case GUESTS:
                    return new EduIDGuestProvider(configuration);
                case INVITE:
                    return new InviteProvider(configuration);
                default:
                    throw new IllegalArgumentException("Unknown external provider-type: " + type);
            }
        }).collect(Collectors.toList());
        return new ExternalGroupsService(groupClients, supportLinkedGrouperExternalGroups);
    }

}
