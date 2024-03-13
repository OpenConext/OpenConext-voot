package voot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.util.ReflectionTestUtils;

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
    void externalGroupsServiceUnknown() {
        VootServiceApplication app = new VootServiceApplication();
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> app.externalGroupsService(new ClassPathResource("/testUnknownExternalProviders.yml")));

    }
}