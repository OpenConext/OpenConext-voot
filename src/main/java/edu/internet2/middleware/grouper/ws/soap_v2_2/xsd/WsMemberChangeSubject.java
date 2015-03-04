
package edu.internet2.middleware.grouper.ws.soap_v2_2.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WsMemberChangeSubject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WsMemberChangeSubject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deleteOldMember" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newSubjectLookup" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsSubjectLookup" minOccurs="0"/>
 *         &lt;element name="oldSubjectLookup" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsSubjectLookup" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WsMemberChangeSubject", propOrder = {
    "deleteOldMember",
    "newSubjectLookup",
    "oldSubjectLookup"
})
public class WsMemberChangeSubject {

    @XmlElementRef(name = "deleteOldMember", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> deleteOldMember;
    @XmlElementRef(name = "newSubjectLookup", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsSubjectLookup> newSubjectLookup;
    @XmlElementRef(name = "oldSubjectLookup", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsSubjectLookup> oldSubjectLookup;

    /**
     * Gets the value of the deleteOldMember property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDeleteOldMember() {
        return deleteOldMember;
    }

    /**
     * Sets the value of the deleteOldMember property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDeleteOldMember(JAXBElement<String> value) {
        this.deleteOldMember = value;
    }

    /**
     * Gets the value of the newSubjectLookup property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsSubjectLookup }{@code >}
     *     
     */
    public JAXBElement<WsSubjectLookup> getNewSubjectLookup() {
        return newSubjectLookup;
    }

    /**
     * Sets the value of the newSubjectLookup property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsSubjectLookup }{@code >}
     *     
     */
    public void setNewSubjectLookup(JAXBElement<WsSubjectLookup> value) {
        this.newSubjectLookup = value;
    }

    /**
     * Gets the value of the oldSubjectLookup property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsSubjectLookup }{@code >}
     *     
     */
    public JAXBElement<WsSubjectLookup> getOldSubjectLookup() {
        return oldSubjectLookup;
    }

    /**
     * Sets the value of the oldSubjectLookup property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsSubjectLookup }{@code >}
     *     
     */
    public void setOldSubjectLookup(JAXBElement<WsSubjectLookup> value) {
        this.oldSubjectLookup = value;
    }

}
