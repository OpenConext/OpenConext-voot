package voot.provider;

import javax.xml.namespace.NamespaceContext;
import java.util.Iterator;

public class GrouperNameSpaceContext implements NamespaceContext {

  @Override
  public String getNamespaceURI(String prefix) {
    switch (prefix) {
      case "soapenv":
        return "http://schemas.xmlsoap.org/soap/envelope/";
      case "ns":
        return "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd";
    }
    throw new IllegalArgumentException(String.format("Unknown prefix '%s'", prefix));
  }

  @Override
  public String getPrefix(String namespaceURI) {
    return null;
  }

  @Override
  public Iterator getPrefixes(String namespaceURI) {
    return null;
  }
}
