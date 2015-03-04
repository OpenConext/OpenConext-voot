
package edu.internet2.middleware.grouper.ws.soap_v2_2.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WsMembershipAnyLookup complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WsMembershipAnyLookup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wsGroupLookup" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsGroupLookup" minOccurs="0"/>
 *         &lt;element name="wsSubjectLookup" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsSubjectLookup" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WsMembershipAnyLookup", propOrder = {
    "wsGroupLookup",
    "wsSubjectLookup"
})
public class WsMembershipAnyLookup {

    @XmlElementRef(name = "wsGroupLookup", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsGroupLookup> wsGroupLookup;
    @XmlElementRef(name = "wsSubjectLookup", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsSubjectLookup> wsSubjectLookup;

    /**
     * Gets the value of the wsGroupLookup property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsGroupLookup }{@code >}
     *     
     */
    public JAXBElement<WsGroupLookup> getWsGroupLookup() {
        return wsGroupLookup;
    }

    /**
     * Sets the value of the wsGroupLookup property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsGroupLookup }{@code >}
     *     
     */
    public void setWsGroupLookup(JAXBElement<WsGroupLookup> value) {
        this.wsGroupLookup = value;
    }

    /**
     * Gets the value of the wsSubjectLookup property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsSubjectLookup }{@code >}
     *     
     */
    public JAXBElement<WsSubjectLookup> getWsSubjectLookup() {
        return wsSubjectLookup;
    }

    /**
     * Sets the value of the wsSubjectLookup property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsSubjectLookup }{@code >}
     *     
     */
    public void setWsSubjectLookup(JAXBElement<WsSubjectLookup> value) {
        this.wsSubjectLookup = value;
    }

}
