package voot.provider;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public abstract class AbstractOpenSocialClientTest {

  protected String admin = "urn:collab:person:example.org:admin";
  protected String UID = "admin";
  protected String GROUP_ID = "nl:surfnet:diensten:apachecon";
  protected String USER_URN = "urn:collab:person:example.org:" + UID;
  protected String GROUP_URN = "urn:collab:group:surfteams.nl:" + GROUP_ID;

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(8889);

  protected void stubCall(String queryPart, String responseFile) throws IOException {
    String response = StreamUtils.copyToString(new ClassPathResource(responseFile).getInputStream(), Charset.forName("UTF-8"));
    stubFor(get(urlEqualTo("/" + queryPart)).willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody(response)));
  }


}
