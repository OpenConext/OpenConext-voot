package voot.provider;

import org.junit.Test;

import static org.junit.Assert.*;

public class GrouperNameSpaceContextTest {

  private GrouperNameSpaceContext subject = new GrouperNameSpaceContext();

  @Test
  public void testGetNamespaceURI() throws Exception {
    assertEquals("http://schemas.xmlsoap.org/soap/envelope/", subject.getNamespaceURI("soapenv"));
    assertEquals("http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", subject.getNamespaceURI("ns"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNamespaceURIUnknown() throws Exception {
    subject.getNamespaceURI("bogus");
  }

  @Test
  public void testGetPrefix() throws Exception {
    assertNull(subject.getPrefix("bogus"));
  }

  @Test
  public void testGetPrefixes() throws Exception {
    assertNull(subject.getPrefixes("bogus"));

  }
}
