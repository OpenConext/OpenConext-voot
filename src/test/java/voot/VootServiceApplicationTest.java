package voot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.util.ReflectionTestUtils;
import voot.provider.Provider;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VootServiceApplicationTest {

    @Test
    void main() {
        VootServiceApplication.main(new String[]{"--server.port=8088"});
    }

    @Test
    void externalGroupsService() throws IOException {
        VootServiceApplication app = new VootServiceApplication();
        ExternalGroupsService externalGroupsService =
                app.externalGroupsService(new ClassPathResource("/testAllExternalProviders.yml"));
        Object providers = ReflectionTestUtils.getField(externalGroupsService, "providers");
        assertEquals(6, ((List) providers).size());
    }

    @Test
    void externalGroupsServiceUserAgent() throws IOException {
        VootServiceApplication app = new VootServiceApplication();
        ReflectionTestUtils.setField(app, "version", "1.2.3");
        ReflectionTestUtils.setField(app, "userAgentExtra", "");

        ExternalGroupsService externalGroupsService =
                app.externalGroupsService(new ClassPathResource("/testAllExternalProviders.yml"));
        List<Provider> providers = (List<Provider>) ReflectionTestUtils.getField(externalGroupsService, "providers");

        for (Provider provider : providers) {
            Provider.Configuration configuration = (Provider.Configuration) ReflectionTestUtils.getField(provider, "configuration");
            assertEquals("voot/1.2.3", configuration.userAgent);
        }
    }

    @Test
    void externalGroupsServiceUserAgentWithExtra() throws IOException {
        VootServiceApplication app = new VootServiceApplication();
        ReflectionTestUtils.setField(app, "version", "1.2.3");
        ReflectionTestUtils.setField(app, "userAgentExtra", "myinstance");

        ExternalGroupsService externalGroupsService =
                app.externalGroupsService(new ClassPathResource("/testAllExternalProviders.yml"));
        List<Provider> providers = (List<Provider>) ReflectionTestUtils.getField(externalGroupsService, "providers");

        Provider.Configuration configuration = (Provider.Configuration) ReflectionTestUtils.getField(providers.get(0), "configuration");
        assertEquals("voot/1.2.3/myinstance", configuration.userAgent);
    }


    @Test
    void externalGroupsServiceUnknown() {
        VootServiceApplication app = new VootServiceApplication();
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> app.externalGroupsService(new ClassPathResource("/testUnknownExternalProviders.yml")));

    }
}