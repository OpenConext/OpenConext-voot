package voot.provider;

import org.junit.Test;
import voot.model.Member;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OpenSocialMembersClientTest extends AbstractOpenSocialClientTest {

    private Provider.Configuration configuration = new Provider.Configuration(GroupProviderType.OPEN_SOCIAL_MEMBERS, "http://localhost:8889", new Provider.Configuration.Credentials("user", "password"), 2000, "example.org", "Example");
    private OpenSocialMembersClient subject = new OpenSocialMembersClient(configuration);

    @Test
    public void testGetMembers() throws Exception {
        stubCall("people/" + UID + "/" + GROUP_ID, "json/members/external_members.json");
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
