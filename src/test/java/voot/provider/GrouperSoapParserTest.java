package voot.provider;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.HttpStatusCodeException;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GrouperSoapParserTest {

  private final Charset charSet = Charset.forName("UTF-8");
  private GrouperSoapParser parser = new GrouperSoapParser("source", "example");

  @Test
  public void testParsePrivileges() throws Exception {
    String soap = StreamUtils.copyToString(new ClassPathResource("soap/GetPrivileges_Success_Response.xml").getInputStream(), charSet);
    List<String> memberships = parser.parsePrivileges(new ResponseEntity<>(soap, HttpStatus.OK), "urn:collab:person:example.com:okke");
    assertEquals(memberships, Arrays.asList("optout", "read", "view"));
  }
}
