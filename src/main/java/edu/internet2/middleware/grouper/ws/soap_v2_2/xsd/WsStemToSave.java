
package edu.internet2.middleware.grouper.ws.soap_v2_2.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WsStemToSave complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WsStemToSave">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createParentStemsIfNotExist" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="saveMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsStem" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsStem" minOccurs="0"/>
 *         &lt;element name="wsStemLookup" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsStemLookup" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WsStemToSave", propOrder = {
    "createParentStemsIfNotExist",
    "saveMode",
    "wsStem",
    "wsStemLookup"
})
public class WsStemToSave {

    @XmlElementRef(name = "createParentStemsIfNotExist", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> createParentStemsIfNotExist;
    @XmlElementRef(name = "saveMode", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> saveMode;
    @XmlElementRef(name = "wsStem", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsStem> wsStem;
    @XmlElementRef(name = "wsStemLookup", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsStemLookup> wsStemLookup;

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
     * Gets the value of the wsStemLookup property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsStemLookup }{@code >}
     *     
     */
    public JAXBElement<WsStemLookup> getWsStemLookup() {
        return wsStemLookup;
    }

    /**
     * Sets the value of the wsStemLookup property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsStemLookup }{@code >}
     *     
     */
    public void setWsStemLookup(JAXBElement<WsStemLookup> value) {
        this.wsStemLookup = value;
    }

}
