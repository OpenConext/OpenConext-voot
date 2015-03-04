
package edu.internet2.middleware.grouper.ws.soap_v2_2.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clientVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oldSubjectId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oldSubjectSourceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oldSubjectIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newSubjectId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newSubjectSourceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newSubjectIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="actAsSubjectId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="actAsSubjectSourceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="actAsSubjectIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deleteOldMember" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includeSubjectDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subjectAttributeNames" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paramName0" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paramValue0" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paramName1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paramValue1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "clientVersion",
    "oldSubjectId",
    "oldSubjectSourceId",
    "oldSubjectIdentifier",
    "newSubjectId",
    "newSubjectSourceId",
    "newSubjectIdentifier",
    "actAsSubjectId",
    "actAsSubjectSourceId",
    "actAsSubjectIdentifier",
    "deleteOldMember",
    "includeSubjectDetail",
    "subjectAttributeNames",
    "paramName0",
    "paramValue0",
    "paramName1",
    "paramValue1"
})
@XmlRootElement(name = "memberChangeSubjectLite")
public class MemberChangeSubjectLite {

    @XmlElementRef(name = "clientVersion", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> clientVersion;
    @XmlElementRef(name = "oldSubjectId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> oldSubjectId;
    @XmlElementRef(name = "oldSubjectSourceId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> oldSubjectSourceId;
    @XmlElementRef(name = "oldSubjectIdentifier", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> oldSubjectIdentifier;
    @XmlElementRef(name = "newSubjectId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> newSubjectId;
    @XmlElementRef(name = "newSubjectSourceId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> newSubjectSourceId;
    @XmlElementRef(name = "newSubjectIdentifier", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> newSubjectIdentifier;
    @XmlElementRef(name = "actAsSubjectId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> actAsSubjectId;
    @XmlElementRef(name = "actAsSubjectSourceId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> actAsSubjectSourceId;
    @XmlElementRef(name = "actAsSubjectIdentifier", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> actAsSubjectIdentifier;
    @XmlElementRef(name = "deleteOldMember", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> deleteOldMember;
    @XmlElementRef(name = "includeSubjectDetail", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> includeSubjectDetail;
    @XmlElementRef(name = "subjectAttributeNames", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> subjectAttributeNames;
    @XmlElementRef(name = "paramName0", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> paramName0;
    @XmlElementRef(name = "paramValue0", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> paramValue0;
    @XmlElementRef(name = "paramName1", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> paramName1;
    @XmlElementRef(name = "paramValue1", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> paramValue1;

    /**
     * Gets the value of the clientVersion property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getClientVersion() {
        return clientVersion;
    }

    /**
     * Sets the value of the clientVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setClientVersion(JAXBElement<String> value) {
        this.clientVersion = value;
    }

    /**
     * Gets the value of the oldSubjectId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOldSubjectId() {
        return oldSubjectId;
    }

    /**
     * Sets the value of the oldSubjectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOldSubjectId(JAXBElement<String> value) {
        this.oldSubjectId = value;
    }

    /**
     * Gets the value of the oldSubjectSourceId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOldSubjectSourceId() {
        return oldSubjectSourceId;
    }

    /**
     * Sets the value of the oldSubjectSourceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOldSubjectSourceId(JAXBElement<String> value) {
        this.oldSubjectSourceId = value;
    }

    /**
     * Gets the value of the oldSubjectIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOldSubjectIdentifier() {
        return oldSubjectIdentifier;
    }

    /**
     * Sets the value of the oldSubjectIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOldSubjectIdentifier(JAXBElement<String> value) {
        this.oldSubjectIdentifier = value;
    }

    /**
     * Gets the value of the newSubjectId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNewSubjectId() {
        return newSubjectId;
    }

    /**
     * Sets the value of the newSubjectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNewSubjectId(JAXBElement<String> value) {
        this.newSubjectId = value;
    }

    /**
     * Gets the value of the newSubjectSourceId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNewSubjectSourceId() {
        return newSubjectSourceId;
    }

    /**
     * Sets the value of the newSubjectSourceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNewSubjectSourceId(JAXBElement<String> value) {
        this.newSubjectSourceId = value;
    }

    /**
     * Gets the value of the newSubjectIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNewSubjectIdentifier() {
        return newSubjectIdentifier;
    }

    /**
     * Sets the value of the newSubjectIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNewSubjectIdentifier(JAXBElement<String> value) {
        this.newSubjectIdentifier = value;
    }

    /**
     * Gets the value of the actAsSubjectId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getActAsSubjectId() {
        return actAsSubjectId;
    }

    /**
     * Sets the value of the actAsSubjectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setActAsSubjectId(JAXBElement<String> value) {
        this.actAsSubjectId = value;
    }

    /**
     * Gets the value of the actAsSubjectSourceId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getActAsSubjectSourceId() {
        return actAsSubjectSourceId;
    }

    /**
     * Sets the value of the actAsSubjectSourceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setActAsSubjectSourceId(JAXBElement<String> value) {
        this.actAsSubjectSourceId = value;
    }

    /**
     * Gets the value of the actAsSubjectIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getActAsSubjectIdentifier() {
        return actAsSubjectIdentifier;
    }

    /**
     * Sets the value of the actAsSubjectIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setActAsSubjectIdentifier(JAXBElement<String> value) {
        this.actAsSubjectIdentifier = value;
    }

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
     * Gets the value of the includeSubjectDetail property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIncludeSubjectDetail() {
        return includeSubjectDetail;
    }

    /**
     * Sets the value of the includeSubjectDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIncludeSubjectDetail(JAXBElement<String> value) {
        this.includeSubjectDetail = value;
    }

    /**
     * Gets the value of the subjectAttributeNames property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubjectAttributeNames() {
        return subjectAttributeNames;
    }

    /**
     * Sets the value of the subjectAttributeNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubjectAttributeNames(JAXBElement<String> value) {
        this.subjectAttributeNames = value;
    }

    /**
     * Gets the value of the paramName0 property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getParamName0() {
        return paramName0;
    }

    /**
     * Sets the value of the paramName0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setParamName0(JAXBElement<String> value) {
        this.paramName0 = value;
    }

    /**
     * Gets the value of the paramValue0 property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getParamValue0() {
        return paramValue0;
    }

    /**
     * Sets the value of the paramValue0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setParamValue0(JAXBElement<String> value) {
        this.paramValue0 = value;
    }

    /**
     * Gets the value of the paramName1 property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getParamName1() {
        return paramName1;
    }

    /**
     * Sets the value of the paramName1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setParamName1(JAXBElement<String> value) {
        this.paramName1 = value;
    }

    /**
     * Gets the value of the paramValue1 property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getParamValue1() {
        return paramValue1;
    }

    /**
     * Sets the value of the paramValue1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setParamValue1(JAXBElement<String> value) {
        this.paramValue1 = value;
    }

}
