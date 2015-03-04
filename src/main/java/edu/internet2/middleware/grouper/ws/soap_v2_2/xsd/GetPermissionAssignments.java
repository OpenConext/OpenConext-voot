
package edu.internet2.middleware.grouper.ws.soap_v2_2.xsd;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="wsAttributeDefLookups" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsAttributeDefLookup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wsAttributeDefNameLookups" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsAttributeDefNameLookup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="roleLookups" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsGroupLookup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wsSubjectLookups" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsSubjectLookup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="actions" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="includePermissionAssignDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includeAttributeDefNames" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includeAttributeAssignments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includeAssignmentsOnAssignments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="actAsSubjectLookup" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsSubjectLookup" minOccurs="0"/>
 *         &lt;element name="includeSubjectDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subjectAttributeNames" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="includeGroupDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="params" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsParam" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="enabled" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pointInTimeFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pointInTimeTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="immediateOnly" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="permissionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="permissionProcessor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="limitEnvVars" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsPermissionEnvVar" maxOccurs="unbounded" minOccurs="0"/>
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
    "wsAttributeDefLookups",
    "wsAttributeDefNameLookups",
    "roleLookups",
    "wsSubjectLookups",
    "actions",
    "includePermissionAssignDetail",
    "includeAttributeDefNames",
    "includeAttributeAssignments",
    "includeAssignmentsOnAssignments",
    "actAsSubjectLookup",
    "includeSubjectDetail",
    "subjectAttributeNames",
    "includeGroupDetail",
    "params",
    "enabled",
    "pointInTimeFrom",
    "pointInTimeTo",
    "immediateOnly",
    "permissionType",
    "permissionProcessor",
    "limitEnvVars",
    "includeLimits"
})
@XmlRootElement(name = "getPermissionAssignments")
public class GetPermissionAssignments {

    @XmlElementRef(name = "clientVersion", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> clientVersion;
    @XmlElement(nillable = true)
    protected List<WsAttributeDefLookup> wsAttributeDefLookups;
    @XmlElement(nillable = true)
    protected List<WsAttributeDefNameLookup> wsAttributeDefNameLookups;
    @XmlElement(nillable = true)
    protected List<WsGroupLookup> roleLookups;
    @XmlElement(nillable = true)
    protected List<WsSubjectLookup> wsSubjectLookups;
    @XmlElement(nillable = true)
    protected List<String> actions;
    @XmlElementRef(name = "includePermissionAssignDetail", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> includePermissionAssignDetail;
    @XmlElementRef(name = "includeAttributeDefNames", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> includeAttributeDefNames;
    @XmlElementRef(name = "includeAttributeAssignments", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> includeAttributeAssignments;
    @XmlElementRef(name = "includeAssignmentsOnAssignments", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> includeAssignmentsOnAssignments;
    @XmlElementRef(name = "actAsSubjectLookup", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsSubjectLookup> actAsSubjectLookup;
    @XmlElementRef(name = "includeSubjectDetail", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> includeSubjectDetail;
    @XmlElement(nillable = true)
    protected List<String> subjectAttributeNames;
    @XmlElementRef(name = "includeGroupDetail", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> includeGroupDetail;
    @XmlElement(nillable = true)
    protected List<WsParam> params;
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
    @XmlElement(nillable = true)
    protected List<WsPermissionEnvVar> limitEnvVars;
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
     * Gets the value of the wsAttributeDefLookups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsAttributeDefLookups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsAttributeDefLookups().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsAttributeDefLookup }
     * 
     * 
     */
    public List<WsAttributeDefLookup> getWsAttributeDefLookups() {
        if (wsAttributeDefLookups == null) {
            wsAttributeDefLookups = new ArrayList<WsAttributeDefLookup>();
        }
        return this.wsAttributeDefLookups;
    }

    /**
     * Gets the value of the wsAttributeDefNameLookups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsAttributeDefNameLookups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsAttributeDefNameLookups().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsAttributeDefNameLookup }
     * 
     * 
     */
    public List<WsAttributeDefNameLookup> getWsAttributeDefNameLookups() {
        if (wsAttributeDefNameLookups == null) {
            wsAttributeDefNameLookups = new ArrayList<WsAttributeDefNameLookup>();
        }
        return this.wsAttributeDefNameLookups;
    }

    /**
     * Gets the value of the roleLookups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roleLookups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoleLookups().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsGroupLookup }
     * 
     * 
     */
    public List<WsGroupLookup> getRoleLookups() {
        if (roleLookups == null) {
            roleLookups = new ArrayList<WsGroupLookup>();
        }
        return this.roleLookups;
    }

    /**
     * Gets the value of the wsSubjectLookups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsSubjectLookups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsSubjectLookups().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsSubjectLookup }
     * 
     * 
     */
    public List<WsSubjectLookup> getWsSubjectLookups() {
        if (wsSubjectLookups == null) {
            wsSubjectLookups = new ArrayList<WsSubjectLookup>();
        }
        return this.wsSubjectLookups;
    }

    /**
     * Gets the value of the actions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the actions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getActions() {
        if (actions == null) {
            actions = new ArrayList<String>();
        }
        return this.actions;
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
     * Gets the value of the actAsSubjectLookup property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsSubjectLookup }{@code >}
     *     
     */
    public JAXBElement<WsSubjectLookup> getActAsSubjectLookup() {
        return actAsSubjectLookup;
    }

    /**
     * Sets the value of the actAsSubjectLookup property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsSubjectLookup }{@code >}
     *     
     */
    public void setActAsSubjectLookup(JAXBElement<WsSubjectLookup> value) {
        this.actAsSubjectLookup = value;
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
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subjectAttributeNames property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubjectAttributeNames().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSubjectAttributeNames() {
        if (subjectAttributeNames == null) {
            subjectAttributeNames = new ArrayList<String>();
        }
        return this.subjectAttributeNames;
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
     * Gets the value of the params property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the params property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParams().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsParam }
     * 
     * 
     */
    public List<WsParam> getParams() {
        if (params == null) {
            params = new ArrayList<WsParam>();
        }
        return this.params;
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
     * Gets the value of the limitEnvVars property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the limitEnvVars property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLimitEnvVars().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsPermissionEnvVar }
     * 
     * 
     */
    public List<WsPermissionEnvVar> getLimitEnvVars() {
        if (limitEnvVars == null) {
            limitEnvVars = new ArrayList<WsPermissionEnvVar>();
        }
        return this.limitEnvVars;
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
