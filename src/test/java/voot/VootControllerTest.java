package voot;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VootControllerTest extends AbstractTest {

    @Test
    void testGroupsMe() throws IOException {
        super.stubCallVoot2("user/admin/groups", "json/voot2/voot2_groups.json");
        super.stubCallTeams("user/urn:collab:person:example.com:admin/groups", "json/voot2/voot2_groups.json");
        super.stubCallTeams("linked-locals?externalGroupIds=urn:collab:group:surfteams.nl:id1,urn:collab:group:surfteams.nl:id2",
                "json/voot2/voot2_group.json");
        List<Map> results = given()
                .when()
                .accept(ContentType.JSON)
                .auth().oauth2(opaqueAccessToken("json/oidcng/introspect.success.json", "groups"))
                .get("/me/groups")
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList(".", Map.class);
        assertEquals(5, results.size());
    }

    @Test
    void testGroupsMeDetail() throws IOException {
        super.stubCallTeams("/user/urn:collab:person:example.com:admin/groups/id1", "json/voot2/voot2_group.json");
        Map results = given()
                .when()
                .accept(ContentType.JSON)
                .auth().oauth2(opaqueAccessToken("json/oidcng/introspect.success.json", "groups"))
                .get("/me/groups/urn:collab:group:external.nl:id1")
                .then()
                .extract()
                .body()
                .jsonPath()
                .getMap(".");
        assertEquals("urn:collab:group:external.nl:id3", results.get("id"));
    }

    @Test
    void testGroupsMeDetailUnqualifiedGroupName() throws IOException {
        given()
                .when()
                .accept(ContentType.JSON)
                .auth().oauth2(opaqueAccessToken("json/oidcng/introspect.success.json", "groups"))
                .get("/me/groups/nope")
                .then()
                .statusCode(400);
    }

    @Test
    void testGroupsMeDetailLinkedGroup() throws IOException {
        mockTeamsServer.stubFor(get(urlEqualTo("/user/urn:collab:person:example.com:admin/groups/id1")).willReturn(aResponse()
                .withStatus(404)));
        super.stubCallTeams("/linked-externals?teamId=id1", "json/teams/external_team_ids.json");
        super.stubCallTeams("/group/id1", "json/voot2/voot2_group.json");
        Map results = given()
                .when()
                .accept(ContentType.JSON)
                .auth().oauth2(opaqueAccessToken("json/oidcng/introspect.success.json", "groups"))
                .get("/me/groups/urn:collab:group:external.nl:id1")
                .then()
                .extract()
                .body()
                .jsonPath()
                .getMap(".");
        assertEquals("urn:collab:group:external.nl:id3", results.get("id"));
    }

    @Test
    void testGroupsMeDetailLinkedGroup404() throws IOException {
        mockTeamsServer.stubFor(get(urlEqualTo("/user/urn:collab:person:example.com:admin/groups/id1")).willReturn(aResponse()
                .withStatus(404)));
        super.stubCallTeams("/linked-externals?teamId=id1", "json/teams/external_team_ids.json");
        Map results = given()
                .when()
                .accept(ContentType.JSON)
                .auth().oauth2(opaqueAccessToken("json/oidcng/introspect.success.json", "groups"))
                .get("/me/groups/urn:collab:group:external.nl:id1")
                .then()
                .extract()
                .body()
                .jsonPath()
                .getMap(".");
        assertEquals(404, results.get("status"));
    }

    @Test
    void testGroupsDetailClientCredentials() throws IOException {
        super.stubCallTeams("/user/admin/groups/id1", "json/voot2/voot2_group.json");
        Map results = given()
                .when()
                .accept(ContentType.JSON)
                .auth().oauth2(opaqueAccessToken("json/oidcng/introspect.client_credentials.json", "groups"))
                .get("/internal/groups/admin/urn:collab:group:external.nl:id1")
                .then()
                .extract()
                .body()
                .jsonPath()
                .getMap(".");
        assertEquals("urn:collab:group:external.nl:id3", results.get("id"));
    }

    @Test
    void testGroupsDetailAuthorizationCodeNotAllowed() throws IOException {
        given()
                .when()
                .accept(ContentType.JSON)
                .auth().oauth2(opaqueAccessToken("json/oidcng/introspect.success.json", "groups"))
                .get("/internal/groups/admin/urn:collab:group:external.nl:id1")
                .then()
                .statusCode(403);
    }

    @Test
    void testInternalGroups() throws IOException {
        super.stubCallTeams("user/urn:collab:person:example.com:admin/groups", "json/voot2/voot2_groups.json");
        List<Map> results = given()
                .when()
                .accept(ContentType.JSON)
                .auth().oauth2(opaqueAccessToken("json/oidcng/introspect.client_credentials.json", "groups"))
                .get("/internal/groups/urn:collab:person:example.com:admin")
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList(".", Map.class);
        assertEquals(2, results.size());
    }

    @Test
    void testExternalGroups() throws IOException {
        super.stubCallVoot2("user/admin/groups", "json/voot2/voot2_groups.json");
        List<Map> results = given()
                .when()
                .accept(ContentType.JSON)
                .auth().oauth2(opaqueAccessToken("json/oidcng/introspect.client_credentials.json", "groups"))
                .get("/internal/external-groups/urn:collab:person:surfteams.nl:admin")
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList(".", Map.class);
        assertEquals(2, results.size());
    }

    @Test
    void testAllGroups() throws IOException {
        super.stubCallTeams("/groups", "json/voot2/voot2_groups.json");
        List<Map> results = given()
                .when()
                .accept(ContentType.JSON)
                .auth().oauth2(opaqueAccessToken("json/oidcng/introspect.client_credentials.json", "all-groups"))
                .get("/internal/all-groups")
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList(".", Map.class);
        assertEquals(2, results.size());
    }

    @Test
    void testMembers() throws IOException {
        super.stubCallTeams("/members/urn:collab:groups:test", "json/teams/members.json");
        List<Map> results = given()
                .when()
                .accept(ContentType.JSON)
                .auth().oauth2(opaqueAccessToken("json/oidcng/introspect.client_credentials.json", "members"))
                .get("/members/urn:collab:groups:test")
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList(".", Map.class);
        assertEquals(2, results.size());
    }

    @Test
    void testMembersIncExternalMembers() throws IOException {
        super.stubCallTeams("/members/external", "json/teams/members.json");
        List<Map> results = given()
                .when()
                .accept(ContentType.JSON)
                .auth().oauth2(opaqueAccessToken("json/oidcng/introspect.client_credentials.json", "members"))
                .get("/members/urn:collab:groups:test/external")
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList(".", Map.class);
        assertEquals(2, results.size());
    }

    @Test
    void testMembers403() throws IOException {
        given()
                .when()
                .accept(ContentType.JSON)
                .auth().oauth2(opaqueAccessToken("json/oidcng/introspect.client_credentials.json", "groups"))
                .get("/members/urn:collab:groups:test")
                .then()
                .statusCode(403);
    }

    @Test
    void testGroups403() throws IOException {
        given()
                .when()
                .accept(ContentType.JSON)
                .auth().oauth2(opaqueAccessToken("json/oidcng/introspect.success.json", "members"))
                .get("/me/groups")
                .then()
                .statusCode(403);

    }

    @Test
    void testAllGroups403() throws IOException {
        given()
                .when()
                .accept(ContentType.JSON)
                .auth().oauth2(opaqueAccessToken("json/oidcng/introspect.client_credentials.json", "groups"))
                .get("/internal/all-groups")
                .then()
                .statusCode(403);
    }

}
