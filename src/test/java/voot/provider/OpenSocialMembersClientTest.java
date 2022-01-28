package voot.provider;

import org.junit.jupiter.api.Test;
import voot.AbstractTest;
import voot.model.Member;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class OpenSocialMembersClientTest extends AbstractTest {

    private Provider.Configuration configuration = new Provider.Configuration(GroupProviderType.OPEN_SOCIAL_MEMBERS, "http://localhost:8889", new Provider.Configuration.Credentials("user", "password"), 2000, "example.org", "Example");
    private OpenSocialMembersClient subject = new OpenSocialMembersClient(configuration);

    @Test
    void testGetMembers() throws Exception {
        stubCallVoot2("people/" + UID + "/" + GROUP_ID, "json/members/external_members.json");
        List<Member> members = subject.getMembers(USER_URN, GROUP_URN);

        assertEquals(4, members.size());
        members.forEach(this::assertMember);
    }

    private void assertMember(Member member) {
        assertNotNull(member.id);
        assertNotNull(member.name);
        assertNotNull(member.email);
    }

}
