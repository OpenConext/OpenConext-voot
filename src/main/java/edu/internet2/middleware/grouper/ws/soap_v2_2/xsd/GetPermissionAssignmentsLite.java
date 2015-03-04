
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
 *         &lt;element name="wsAttributeDefName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsAttributeDefId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsAttributeDefNameName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsAttributeDefNameId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roleId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsSubjectId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsSubjectSourceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsSubjectIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="action" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includePermissionAssignDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includeAttributeDefNames" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includeAttributeAssignments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includeAssignmentsOnAssignments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *         &lt;element name="enabled" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pointInTimeFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pointInTimeTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="immediateOnly" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="permissionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="permissionProcessor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="limitEnvVarName0" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="limitEnvVarValue0" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="limitEnvVarType0" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="limitEnvVarName1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="limitEnvVarValue1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="limitEnvVarType1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includeLimits" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "wsAttributeDefName",
    "wsAttributeDefId",
    "wsAttributeDefNameName",
    "wsAttributeDefNameId",
    "roleName",
    "roleId",
    "wsSubjectId",
    "wsSubjectSourceId",
    "wsSubjectIdentifier",
    "action",
    "includePermissionAssignDetail",
    "includeAttributeDefNames",
    "includeAttributeAssignments",
    "includeAssignmentsOnAssignments",
    "actAsSubjectId",
    "actAsSubjectSourceId",
    "actAsSubjectIdentifier",
    "includeSubjectDetail",
    "subjectAttributeNames",
    "includeGroupDetail",
    "paramName0",
    "paramValue0",
    "paramName1",
    "paramValue1",
    "enabled",
    "pointInTimeFrom",
    "pointInTimeTo",
    "immediateOnly",
    "permissionType",
    "permissionProcessor",
    "limitEnvVarName0",
    "limitEnvVarValue0",
    "limitEnvVarType0",
    "limitEnvVarName1",
    "limitEnvVarValue1",
    "limitEnvVarType1",
    "includeLimits"
})
@XmlRootElement(name = "getPermissionAssignmentsLite")
public class GetPermissionAssignmentsLite {

    @XmlElementRef(name = "clientVersion", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> clientVersion;
    @XmlElementRef(name = "wsAttributeDefName", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> wsAttributeDefName;
    @XmlElementRef(name = "wsAttributeDefId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> wsAttributeDefId;
    @XmlElementRef(name = "wsAttributeDefNameName", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> wsAttributeDefNameName;
    @XmlElementRef(name = "wsAttributeDefNameId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> wsAttributeDefNameId;
    @XmlElementRef(name = "roleName", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> roleName;
    @XmlElementRef(name = "roleId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> roleId;
    @XmlElementRef(name = "wsSubjectId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> wsSubjectId;
    @XmlElementRef(name = "wsSubjectSourceId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> wsSubjectSourceId;
    @XmlElementRef(name = "wsSubjectIdentifier", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> wsSubjectIdentifier;
    @XmlElementRef(name = "action", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> action;
    @XmlElementRef(name = "includePermissionAssignDetail", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> includePermissionAssignDetail;
    @XmlElementRef(name = "includeAttributeDefNames", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> includeAttributeDefNames;
    @XmlElementRef(name = "includeAttributeAssignments", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> includeAttributeAssignments;
    @XmlElementRef(name = "includeAssignmentsOnAssignments", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> includeAssignmentsOnAssignments;
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
    @XmlElementRef(name = "enabled", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> enabled;
    @XmlElementRef(name = "pointInTimeFrom", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> pointInTimeFrom;
    @XmlElementRef(name = "pointInTimeTo", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> pointInTimeTo;
    @XmlElementRef(name = "immediateOnly", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> immediateOnly;
    @XmlElementRef(name = "permissionType", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> permissionType;
    @XmlElementRef(name = "permissionProcessor", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> permissionProcessor;
    @XmlElementRef(name = "limitEnvVarName0", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> limitEnvVarName0;
    @XmlElementRef(name = "limitEnvVarValue0", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> limitEnvVarValue0;
    @XmlElementRef(name = "limitEnvVarType0", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> limitEnvVarType0;
    @XmlElementRef(name = "limitEnvVarName1", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> limitEnvVarName1;
    @XmlElementRef(name = "limitEnvVarValue1", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> limitEnvVarValue1;
    @XmlElementRef(name = "limitEnvVarType1", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> limitEnvVarType1;
    @XmlElementRef(name = "includeLimits", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> includeLimits;

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
     * Gets the value of the wsAttributeDefName property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWsAttributeDefName() {
        return wsAttributeDefName;
    }

    /**
     * Sets the value of the wsAttributeDefName property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWsAttributeDefName(JAXBElement<String> value) {
        this.wsAttributeDefName = value;
    }

    /**
     * Gets the value of the wsAttributeDefId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWsAttributeDefId() {
        return wsAttributeDefId;
    }

    /**
     * Sets the value of the wsAttributeDefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWsAttributeDefId(JAXBElement<String> value) {
        this.wsAttributeDefId = value;
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
     * Gets the value of the roleName property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRoleName() {
        return roleName;
    }

    /**
     * Sets the value of the roleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRoleName(JAXBElement<String> value) {
        this.roleName = value;
    }

    /**
     * Gets the value of the roleId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRoleId() {
        return roleId;
    }

    /**
     * Sets the value of the roleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRoleId(JAXBElement<String> value) {
        this.roleId = value;
    }

    /**
     * Gets the value of the wsSubjectId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWsSubjectId() {
        return wsSubjectId;
    }

    /**
     * Sets the value of the wsSubjectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWsSubjectId(JAXBElement<String> value) {
        this.wsSubjectId = value;
    }

    /**
     * Gets the value of the wsSubjectSourceId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWsSubjectSourceId() {
        return wsSubjectSourceId;
    }

    /**
     * Sets the value of the wsSubjectSourceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWsSubjectSourceId(JAXBElement<String> value) {
        this.wsSubjectSourceId = value;
    }

    /**
     * Gets the value of the wsSubjectIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWsSubjectIdentifier() {
        return wsSubjectIdentifier;
    }

    /**
     * Sets the value of the wsSubjectIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWsSubjectIdentifier(JAXBElement<String> value) {
        this.wsSubjectIdentifier = value;
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
     * Gets the value of the includePermissionAssignDetail property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIncludePermissionAssignDetail() {
        return includePermissionAssignDetail;
    }

    /**
     * Sets the value of the includePermissionAssignDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIncludePermissionAssignDetail(JAXBElement<String> value) {
        this.includePermissionAssignDetail = value;
    }

    /**
     * Gets the value of the includeAttributeDefNames property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIncludeAttributeDefNames() {
        return includeAttributeDefNames;
    }

    /**
     * Sets the value of the includeAttributeDefNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIncludeAttributeDefNames(JAXBElement<String> value) {
        this.includeAttributeDefNames = value;
    }

    /**
     * Gets the value of the includeAttributeAssignments property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIncludeAttributeAssignments() {
        return includeAttributeAssignments;
    }

    /**
     * Sets the value of the includeAttributeAssignments property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIncludeAttributeAssignments(JAXBElement<String> value) {
        this.includeAttributeAssignments = value;
    }

    /**
     * Gets the value of the includeAssignmentsOnAssignments property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIncludeAssignmentsOnAssignments() {
        return includeAssignmentsOnAssignments;
    }

    /**
     * Sets the value of the includeAssignmentsOnAssignments property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIncludeAssignmentsOnAssignments(JAXBElement<String> value) {
        this.includeAssignmentsOnAssignments = value;
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

    /**
     * Gets the value of the enabled property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEnabled() {
        return enabled;
    }

    /**
     * Sets the value of the enabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEnabled(JAXBElement<String> value) {
        this.enabled = value;
    }

    /**
     * Gets the value of the pointInTimeFrom property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPointInTimeFrom() {
        return pointInTimeFrom;
    }

    /**
     * Sets the value of the pointInTimeFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPointInTimeFrom(JAXBElement<String> value) {
        this.pointInTimeFrom = value;
    }

    /**
     * Gets the value of the pointInTimeTo property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPointInTimeTo() {
        return pointInTimeTo;
    }

    /**
     * Sets the value of the pointInTimeTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPointInTimeTo(JAXBElement<String> value) {
        this.pointInTimeTo = value;
    }

    /**
     * Gets the value of the immediateOnly property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImmediateOnly() {
        return immediateOnly;
    }

    /**
     * Sets the value of the immediateOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImmediateOnly(JAXBElement<String> value) {
        this.immediateOnly = value;
    }

    /**
     * Gets the value of the permissionType property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPermissionType() {
        return permissionType;
    }

    /**
     * Sets the value of the permissionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPermissionType(JAXBElement<String> value) {
        this.permissionType = value;
    }

    /**
     * Gets the value of the permissionProcessor property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPermissionProcessor() {
        return permissionProcessor;
    }

    /**
     * Sets the value of the permissionProcessor property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPermissionProcessor(JAXBElement<String> value) {
        this.permissionProcessor = value;
    }

    /**
     * Gets the value of the limitEnvVarName0 property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLimitEnvVarName0() {
        return limitEnvVarName0;
    }

    /**
     * Sets the value of the limitEnvVarName0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLimitEnvVarName0(JAXBElement<String> value) {
        this.limitEnvVarName0 = value;
    }

    /**
     * Gets the value of the limitEnvVarValue0 property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLimitEnvVarValue0() {
        return limitEnvVarValue0;
    }

    /**
     * Sets the value of the limitEnvVarValue0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLimitEnvVarValue0(JAXBElement<String> value) {
        this.limitEnvVarValue0 = value;
    }

    /**
     * Gets the value of the limitEnvVarType0 property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLimitEnvVarType0() {
        return limitEnvVarType0;
    }

    /**
     * Sets the value of the limitEnvVarType0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLimitEnvVarType0(JAXBElement<String> value) {
        this.limitEnvVarType0 = value;
    }

    /**
     * Gets the value of the limitEnvVarName1 property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLimitEnvVarName1() {
        return limitEnvVarName1;
    }

    /**
     * Sets the value of the limitEnvVarName1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLimitEnvVarName1(JAXBElement<String> value) {
        this.limitEnvVarName1 = value;
    }

    /**
     * Gets the value of the limitEnvVarValue1 property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLimitEnvVarValue1() {
        return limitEnvVarValue1;
    }

    /**
     * Sets the value of the limitEnvVarValue1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLimitEnvVarValue1(JAXBElement<String> value) {
        this.limitEnvVarValue1 = value;
    }

    /**
     * Gets the value of the limitEnvVarType1 property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLimitEnvVarType1() {
        return limitEnvVarType1;
    }

    /**
     * Sets the value of the limitEnvVarType1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLimitEnvVarType1(JAXBElement<String> value) {
        this.limitEnvVarType1 = value;
    }

    /**
     * Gets the value of the includeLimits property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIncludeLimits() {
        return includeLimits;
    }

    /**
     * Sets the value of the includeLimits property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIncludeLimits(JAXBElement<String> value) {
        this.includeLimits = value;
    }

}
