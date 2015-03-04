
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
 *         &lt;element name="attributeAssignType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsAttributeDefNameName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsAttributeDefNameId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attributeAssignOperation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valueId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valueSystem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valueFormatted" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="assignmentNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="assignmentEnabledTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="assignmentDisabledTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="delegatable" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attributeAssignValueOperation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsAttributeAssignId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsOwnerGroupName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsOwnerGroupId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsOwnerStemName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsOwnerStemId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsOwnerSubjectId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsOwnerSubjectSourceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsOwnerSubjectIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsOwnerMembershipId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsOwnerMembershipAnyGroupName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsOwnerMembershipAnyGroupId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsOwnerMembershipAnySubjectId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsOwnerMembershipAnySubjectSourceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsOwnerMembershipAnySubjectIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsOwnerAttributeDefName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsOwnerAttributeDefId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsOwnerAttributeAssignId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="action" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="actAsSubjectId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="actAsSubjectSourceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="actAsSubjectIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includeSubjectDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subjectAttributeNames" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includeGroupDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "attributeAssignType",
    "wsAttributeDefNameName",
    "wsAttributeDefNameId",
    "attributeAssignOperation",
    "valueId",
    "valueSystem",
    "valueFormatted",
    "assignmentNotes",
    "assignmentEnabledTime",
    "assignmentDisabledTime",
    "delegatable",
    "attributeAssignValueOperation",
    "wsAttributeAssignId",
    "wsOwnerGroupName",
    "wsOwnerGroupId",
    "wsOwnerStemName",
    "wsOwnerStemId",
    "wsOwnerSubjectId",
    "wsOwnerSubjectSourceId",
    "wsOwnerSubjectIdentifier",
    "wsOwnerMembershipId",
    "wsOwnerMembershipAnyGroupName",
    "wsOwnerMembershipAnyGroupId",
    "wsOwnerMembershipAnySubjectId",
    "wsOwnerMembershipAnySubjectSourceId",
    "wsOwnerMembershipAnySubjectIdentifier",
    "wsOwnerAttributeDefName",
    "wsOwnerAttributeDefId",
    "wsOwnerAttributeAssignId",
    "action",
    "actAsSubjectId",
    "actAsSubjectSourceId",
    "actAsSubjectIdentifier",
    "includeSubjectDetail",
    "subjectAttributeNames",
    "includeGroupDetail",
    "paramName0",
    "paramValue0",
    "paramName1",
    "paramValue1"
})
@XmlRootElement(name = "assignAttributesLite")
public class AssignAttributesLite {

    @XmlElementRef(name = "clientVersion", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> clientVersion;
    @XmlElementRef(name = "attributeAssignType", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> attributeAssignType;
    @XmlElementRef(name = "wsAttributeDefNameName", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> wsAttributeDefNameName;
    @XmlElementRef(name = "wsAttributeDefNameId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> wsAttributeDefNameId;
    @XmlElementRef(name = "attributeAssignOperation", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> attributeAssignOperation;
    @XmlElementRef(name = "valueId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> valueId;
    @XmlElementRef(name = "valueSystem", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> valueSystem;
    @XmlElementRef(name = "valueFormatted", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> valueFormatted;
    @XmlElementRef(name = "assignmentNotes", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> assignmentNotes;
    @XmlElementRef(name = "assignmentEnabledTime", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> assignmentEnabledTime;
    @XmlElementRef(name = "assignmentDisabledTime", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> assignmentDisabledTime;
    @XmlElementRef(name = "delegatable", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> delegatable;
    @XmlElementRef(name = "attributeAssignValueOperation", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> attributeAssignValueOperation;
    @XmlElementRef(name = "wsAttributeAssignId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> wsAttributeAssignId;
    @XmlElementRef(name = "wsOwnerGroupName", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> wsOwnerGroupName;
    @XmlElementRef(name = "wsOwnerGroupId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> wsOwnerGroupId;
    @XmlElementRef(name = "wsOwnerStemName", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> wsOwnerStemName;
    @XmlElementRef(name = "wsOwnerStemId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> wsOwnerStemId;
    @XmlElementRef(name = "wsOwnerSubjectId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> wsOwnerSubjectId;
    @XmlElementRef(name = "wsOwnerSubjectSourceId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> wsOwnerSubjectSourceId;
    @XmlElementRef(name = "wsOwnerSubjectIdentifier", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> wsOwnerSubjectIdentifier;
    @XmlElementRef(name = "wsOwnerMembershipId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> wsOwnerMembershipId;
    @XmlElementRef(name = "wsOwnerMembershipAnyGroupName", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> wsOwnerMembershipAnyGroupName;
    @XmlElementRef(name = "wsOwnerMembershipAnyGroupId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> wsOwnerMembershipAnyGroupId;
    @XmlElementRef(name = "wsOwnerMembershipAnySubjectId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> wsOwnerMembershipAnySubjectId;
    @XmlElementRef(name = "wsOwnerMembershipAnySubjectSourceId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> wsOwnerMembershipAnySubjectSourceId;
    @XmlElementRef(name = "wsOwnerMembershipAnySubjectIdentifier", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> wsOwnerMembershipAnySubjectIdentifier;
    @XmlElementRef(name = "wsOwnerAttributeDefName", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> wsOwnerAttributeDefName;
    @XmlElementRef(name = "wsOwnerAttributeDefId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> wsOwnerAttributeDefId;
    @XmlElementRef(name = "wsOwnerAttributeAssignId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> wsOwnerAttributeAssignId;
    @XmlElementRef(name = "action", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> action;
    @XmlElementRef(name = "actAsSubjectId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> actAsSubjectId;
    @XmlElementRef(name = "actAsSubjectSourceId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> actAsSubjectSourceId;
    @XmlElementRef(name = "actAsSubjectIdentifier", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> actAsSubjectIdentifier;
    @XmlElementRef(name = "includeSubjectDetail", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> includeSubjectDetail;
    @XmlElementRef(name = "subjectAttributeNames", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> subjectAttributeNames;
    @XmlElementRef(name = "includeGroupDetail", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> includeGroupDetail;
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
     * Gets the value of the attributeAssignType property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAttributeAssignType() {
        return attributeAssignType;
    }

    /**
     * Sets the value of the attributeAssignType property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAttributeAssignType(JAXBElement<String> value) {
        this.attributeAssignType = value;
    }

    /**
     * Gets the value of the wsAttributeDefNameName property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWsAttributeDefNameName() {
        return wsAttributeDefNameName;
    }

    /**
     * Sets the value of the wsAttributeDefNameName property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWsAttributeDefNameName(JAXBElement<String> value) {
        this.wsAttributeDefNameName = value;
    }

    /**
     * Gets the value of the wsAttributeDefNameId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWsAttributeDefNameId() {
        return wsAttributeDefNameId;
    }

    /**
     * Sets the value of the wsAttributeDefNameId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWsAttributeDefNameId(JAXBElement<String> value) {
        this.wsAttributeDefNameId = value;
    }

    /**
     * Gets the value of the attributeAssignOperation property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAttributeAssignOperation() {
        return attributeAssignOperation;
    }

    /**
     * Sets the value of the attributeAssignOperation property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAttributeAssignOperation(JAXBElement<String> value) {
        this.attributeAssignOperation = value;
    }

    /**
     * Gets the value of the valueId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getValueId() {
        return valueId;
    }

    /**
     * Sets the value of the valueId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setValueId(JAXBElement<String> value) {
        this.valueId = value;
    }

    /**
     * Gets the value of the valueSystem property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getValueSystem() {
        return valueSystem;
    }

    /**
     * Sets the value of the valueSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setValueSystem(JAXBElement<String> value) {
        this.valueSystem = value;
    }

    /**
     * Gets the value of the valueFormatted property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getValueFormatted() {
        return valueFormatted;
    }

    /**
     * Sets the value of the valueFormatted property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setValueFormatted(JAXBElement<String> value) {
        this.valueFormatted = value;
    }

    /**
     * Gets the value of the assignmentNotes property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAssignmentNotes() {
        return assignmentNotes;
    }

    /**
     * Sets the value of the assignmentNotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAssignmentNotes(JAXBElement<String> value) {
        this.assignmentNotes = value;
    }

    /**
     * Gets the value of the assignmentEnabledTime property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAssignmentEnabledTime() {
        return assignmentEnabledTime;
    }

    /**
     * Sets the value of the assignmentEnabledTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAssignmentEnabledTime(JAXBElement<String> value) {
        this.assignmentEnabledTime = value;
    }

    /**
     * Gets the value of the assignmentDisabledTime property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAssignmentDisabledTime() {
        return assignmentDisabledTime;
    }

    /**
     * Sets the value of the assignmentDisabledTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAssignmentDisabledTime(JAXBElement<String> value) {
        this.assignmentDisabledTime = value;
    }

    /**
     * Gets the value of the delegatable property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelegatable() {
        return delegatable;
    }

    /**
     * Sets the value of the delegatable property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelegatable(JAXBElement<String> value) {
        this.delegatable = value;
    }

    /**
     * Gets the value of the attributeAssignValueOperation property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAttributeAssignValueOperation() {
        return attributeAssignValueOperation;
    }

    /**
     * Sets the value of the attributeAssignValueOperation property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAttributeAssignValueOperation(JAXBElement<String> value) {
        this.attributeAssignValueOperation = value;
    }

    /**
     * Gets the value of the wsAttributeAssignId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWsAttributeAssignId() {
        return wsAttributeAssignId;
    }

    /**
     * Sets the value of the wsAttributeAssignId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWsAttributeAssignId(JAXBElement<String> value) {
        this.wsAttributeAssignId = value;
    }

    /**
     * Gets the value of the wsOwnerGroupName property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWsOwnerGroupName() {
        return wsOwnerGroupName;
    }

    /**
     * Sets the value of the wsOwnerGroupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWsOwnerGroupName(JAXBElement<String> value) {
        this.wsOwnerGroupName = value;
    }

    /**
     * Gets the value of the wsOwnerGroupId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWsOwnerGroupId() {
        return wsOwnerGroupId;
    }

    /**
     * Sets the value of the wsOwnerGroupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWsOwnerGroupId(JAXBElement<String> value) {
        this.wsOwnerGroupId = value;
    }

    /**
     * Gets the value of the wsOwnerStemName property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWsOwnerStemName() {
        return wsOwnerStemName;
    }

    /**
     * Sets the value of the wsOwnerStemName property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWsOwnerStemName(JAXBElement<String> value) {
        this.wsOwnerStemName = value;
    }

    /**
     * Gets the value of the wsOwnerStemId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWsOwnerStemId() {
        return wsOwnerStemId;
    }

    /**
     * Sets the value of the wsOwnerStemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWsOwnerStemId(JAXBElement<String> value) {
        this.wsOwnerStemId = value;
    }

    /**
     * Gets the value of the wsOwnerSubjectId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWsOwnerSubjectId() {
        return wsOwnerSubjectId;
    }

    /**
     * Sets the value of the wsOwnerSubjectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWsOwnerSubjectId(JAXBElement<String> value) {
        this.wsOwnerSubjectId = value;
    }

    /**
     * Gets the value of the wsOwnerSubjectSourceId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWsOwnerSubjectSourceId() {
        return wsOwnerSubjectSourceId;
    }

    /**
     * Sets the value of the wsOwnerSubjectSourceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWsOwnerSubjectSourceId(JAXBElement<String> value) {
        this.wsOwnerSubjectSourceId = value;
    }

    /**
     * Gets the value of the wsOwnerSubjectIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWsOwnerSubjectIdentifier() {
        return wsOwnerSubjectIdentifier;
    }

    /**
     * Sets the value of the wsOwnerSubjectIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWsOwnerSubjectIdentifier(JAXBElement<String> value) {
        this.wsOwnerSubjectIdentifier = value;
    }

    /**
     * Gets the value of the wsOwnerMembershipId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWsOwnerMembershipId() {
        return wsOwnerMembershipId;
    }

    /**
     * Sets the value of the wsOwnerMembershipId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWsOwnerMembershipId(JAXBElement<String> value) {
        this.wsOwnerMembershipId = value;
    }

    /**
     * Gets the value of the wsOwnerMembershipAnyGroupName property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWsOwnerMembershipAnyGroupName() {
        return wsOwnerMembershipAnyGroupName;
    }

    /**
     * Sets the value of the wsOwnerMembershipAnyGroupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWsOwnerMembershipAnyGroupName(JAXBElement<String> value) {
        this.wsOwnerMembershipAnyGroupName = value;
    }

    /**
     * Gets the value of the wsOwnerMembershipAnyGroupId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWsOwnerMembershipAnyGroupId() {
        return wsOwnerMembershipAnyGroupId;
    }

    /**
     * Sets the value of the wsOwnerMembershipAnyGroupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWsOwnerMembershipAnyGroupId(JAXBElement<String> value) {
        this.wsOwnerMembershipAnyGroupId = value;
    }

    /**
     * Gets the value of the wsOwnerMembershipAnySubjectId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWsOwnerMembershipAnySubjectId() {
        return wsOwnerMembershipAnySubjectId;
    }

    /**
     * Sets the value of the wsOwnerMembershipAnySubjectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWsOwnerMembershipAnySubjectId(JAXBElement<String> value) {
        this.wsOwnerMembershipAnySubjectId = value;
    }

    /**
     * Gets the value of the wsOwnerMembershipAnySubjectSourceId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWsOwnerMembershipAnySubjectSourceId() {
        return wsOwnerMembershipAnySubjectSourceId;
    }

    /**
     * Sets the value of the wsOwnerMembershipAnySubjectSourceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWsOwnerMembershipAnySubjectSourceId(JAXBElement<String> value) {
        this.wsOwnerMembershipAnySubjectSourceId = value;
    }

    /**
     * Gets the value of the wsOwnerMembershipAnySubjectIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWsOwnerMembershipAnySubjectIdentifier() {
        return wsOwnerMembershipAnySubjectIdentifier;
    }

    /**
     * Sets the value of the wsOwnerMembershipAnySubjectIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWsOwnerMembershipAnySubjectIdentifier(JAXBElement<String> value) {
        this.wsOwnerMembershipAnySubjectIdentifier = value;
    }

    /**
     * Gets the value of the wsOwnerAttributeDefName property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWsOwnerAttributeDefName() {
        return wsOwnerAttributeDefName;
    }

    /**
     * Sets the value of the wsOwnerAttributeDefName property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWsOwnerAttributeDefName(JAXBElement<String> value) {
        this.wsOwnerAttributeDefName = value;
    }

    /**
     * Gets the value of the wsOwnerAttributeDefId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWsOwnerAttributeDefId() {
        return wsOwnerAttributeDefId;
    }

    /**
     * Sets the value of the wsOwnerAttributeDefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWsOwnerAttributeDefId(JAXBElement<String> value) {
        this.wsOwnerAttributeDefId = value;
    }

    /**
     * Gets the value of the wsOwnerAttributeAssignId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWsOwnerAttributeAssignId() {
        return wsOwnerAttributeAssignId;
    }

    /**
     * Sets the value of the wsOwnerAttributeAssignId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWsOwnerAttributeAssignId(JAXBElement<String> value) {
        this.wsOwnerAttributeAssignId = value;
    }

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAction(JAXBElement<String> value) {
        this.action = value;
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
     * Gets the value of the includeGroupDetail property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIncludeGroupDetail() {
        return includeGroupDetail;
    }

    /**
     * Sets the value of the includeGroupDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIncludeGroupDetail(JAXBElement<String> value) {
        this.includeGroupDetail = value;
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
