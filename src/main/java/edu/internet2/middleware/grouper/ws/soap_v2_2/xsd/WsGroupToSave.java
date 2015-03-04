
package edu.internet2.middleware.grouper.ws.soap_v2_2.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WsGroupToSave complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WsGroupToSave">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createParentStemsIfNotExist" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="saveMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsGroup" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsGroup" minOccurs="0"/>
 *         &lt;element name="wsGroupLookup" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsGroupLookup" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WsGroupToSave", propOrder = {
    "createParentStemsIfNotExist",
    "saveMode",
    "wsGroup",
    "wsGroupLookup"
})
public class WsGroupToSave {

    @XmlElementRef(name = "createParentStemsIfNotExist", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> createParentStemsIfNotExist;
    @XmlElementRef(name = "saveMode", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> saveMode;
    @XmlElementRef(name = "wsGroup", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsGroup> wsGroup;
    @XmlElementRef(name = "wsGroupLookup", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsGroupLookup> wsGroupLookup;

    /**
     * Gets the value of the createParentStemsIfNotExist property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCreateParentStemsIfNotExist() {
        return createParentStemsIfNotExist;
    }

    /**
     * Sets the value of the createParentStemsIfNotExist property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCreateParentStemsIfNotExist(JAXBElement<String> value) {
        this.createParentStemsIfNotExist = value;
    }

    /**
     * Gets the value of the saveMode property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaveMode() {
        return saveMode;
    }

    /**
     * Sets the value of the saveMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaveMode(JAXBElement<String> value) {
        this.saveMode = value;
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

}
