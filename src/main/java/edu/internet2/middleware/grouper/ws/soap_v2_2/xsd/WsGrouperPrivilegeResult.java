
package edu.internet2.middleware.grouper.ws.soap_v2_2.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WsGrouperPrivilegeResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WsGrouperPrivilegeResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="allowed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ownerSubject" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsSubject" minOccurs="0"/>
 *         &lt;element name="privilegeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="privilegeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="revokable" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsGroup" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsGroup" minOccurs="0"/>
 *         &lt;element name="wsStem" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsStem" minOccurs="0"/>
 *         &lt;element name="wsSubject" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsSubject" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WsGrouperPrivilegeResult", propOrder = {
    "allowed",
    "ownerSubject",
    "privilegeName",
    "privilegeType",
    "revokable",
    "wsGroup",
    "wsStem",
    "wsSubject"
})
public class WsGrouperPrivilegeResult {

    @XmlElementRef(name = "allowed", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> allowed;
    @XmlElementRef(name = "ownerSubject", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsSubject> ownerSubject;
    @XmlElementRef(name = "privilegeName", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> privilegeName;
    @XmlElementRef(name = "privilegeType", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> privilegeType;
    @XmlElementRef(name = "revokable", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> revokable;
    @XmlElementRef(name = "wsGroup", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsGroup> wsGroup;
    @XmlElementRef(name = "wsStem", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsStem> wsStem;
    @XmlElementRef(name = "wsSubject", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsSubject> wsSubject;

    /**
     * Gets the value of the allowed property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAllowed() {
        return allowed;
    }

    /**
     * Sets the value of the allowed property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAllowed(JAXBElement<String> value) {
        this.allowed = value;
    }

    /**
     * Gets the value of the ownerSubject property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsSubject }{@code >}
     *     
     */
    public JAXBElement<WsSubject> getOwnerSubject() {
        return ownerSubject;
    }

    /**
     * Sets the value of the ownerSubject property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsSubject }{@code >}
     *     
     */
    public void setOwnerSubject(JAXBElement<WsSubject> value) {
        this.ownerSubject = value;
    }

    /**
     * Gets the value of the privilegeName property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPrivilegeName() {
        return privilegeName;
    }

    /**
     * Sets the value of the privilegeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPrivilegeName(JAXBElement<String> value) {
        this.privilegeName = value;
    }

    /**
     * Gets the value of the privilegeType property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPrivilegeType() {
        return privilegeType;
    }

    /**
     * Sets the value of the privilegeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPrivilegeType(JAXBElement<String> value) {
        this.privilegeType = value;
    }

    /**
     * Gets the value of the revokable property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRevokable() {
        return revokable;
    }

    /**
     * Sets the value of the revokable property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRevokable(JAXBElement<String> value) {
        this.revokable = value;
    }

    /**
     * Gets the value of the wsGroup property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsGroup }{@code >}
     *     
     */
    public JAXBElement<WsGroup> getWsGroup() {
        return wsGroup;
    }

    /**
     * Sets the value of the wsGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsGroup }{@code >}
     *     
     */
    public void setWsGroup(JAXBElement<WsGroup> value) {
        this.wsGroup = value;
    }

    /**
     * Gets the value of the wsStem property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsStem }{@code >}
     *     
     */
    public JAXBElement<WsStem> getWsStem() {
        return wsStem;
    }

    /**
     * Sets the value of the wsStem property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsStem }{@code >}
     *     
     */
    public void setWsStem(JAXBElement<WsStem> value) {
        this.wsStem = value;
    }

    /**
     * Gets the value of the wsSubject property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsSubject }{@code >}
     *     
     */
    public JAXBElement<WsSubject> getWsSubject() {
        return wsSubject;
    }

    /**
     * Sets the value of the wsSubject property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsSubject }{@code >}
     *     
     */
    public void setWsSubject(JAXBElement<WsSubject> value) {
        this.wsSubject = value;
    }

}
