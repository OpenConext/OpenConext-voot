
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
 *         &lt;element name="permissionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="permissionDefNameLookups" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsAttributeDefNameLookup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="permissionAssignOperation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="assignmentNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="assignmentEnabledTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="assignmentDisabledTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="delegatable" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsAttributeAssignLookups" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsAttributeAssignLookup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="roleLookups" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsGroupLookup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="subjectRoleLookups" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsMembershipAnyLookup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="actions" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="actAsSubjectLookup" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsSubjectLookup" minOccurs="0"/>
 *         &lt;element name="includeSubjectDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subjectAttributeNames" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="includeGroupDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="params" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsParam" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="attributeDefsToReplace" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsAttributeDefLookup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="actionsToReplace" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="disallowed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "permissionType",
    "permissionDefNameLookups",
    "permissionAssignOperation",
    "assignmentNotes",
    "assignmentEnabledTime",
    "assignmentDisabledTime",
    "delegatable",
    "wsAttributeAssignLookups",
    "roleLookups",
    "subjectRoleLookups",
    "actions",
    "actAsSubjectLookup",
    "includeSubjectDetail",
    "subjectAttributeNames",
    "includeGroupDetail",
    "params",
    "attributeDefsToReplace",
    "actionsToReplace",
    "disallowed"
})
@XmlRootElement(name = "assignPermissions")
public class AssignPermissions {

    @XmlElementRef(name = "clientVersion", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> clientVersion;
    @XmlElementRef(name = "permissionType", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> permissionType;
    @XmlElement(nillable = true)
    protected List<WsAttributeDefNameLookup> permissionDefNameLookups;
    @XmlElementRef(name = "permissionAssignOperation", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> permissionAssignOperation;
    @XmlElementRef(name = "assignmentNotes", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> assignmentNotes;
    @XmlElementRef(name = "assignmentEnabledTime", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> assignmentEnabledTime;
    @XmlElementRef(name = "assignmentDisabledTime", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> assignmentDisabledTime;
    @XmlElementRef(name = "delegatable", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> delegatable;
    @XmlElement(nillable = true)
    protected List<WsAttributeAssignLookup> wsAttributeAssignLookups;
    @XmlElement(nillable = true)
    protected List<WsGroupLookup> roleLookups;
    @XmlElement(nillable = true)
    protected List<WsMembershipAnyLookup> subjectRoleLookups;
    @XmlElement(nillable = true)
    protected List<String> actions;
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
    @XmlElement(nillable = true)
    protected List<WsAttributeDefLookup> attributeDefsToReplace;
    @XmlElement(nillable = true)
    protected List<String> actionsToReplace;
    @XmlElementRef(name = "disallowed", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> disallowed;

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
     * Gets the value of the permissionDefNameLookups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the permissionDefNameLookups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPermissionDefNameLookups().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsAttributeDefNameLookup }
     * 
     * 
     */
    public List<WsAttributeDefNameLookup> getPermissionDefNameLookups() {
        if (permissionDefNameLookups == null) {
            permissionDefNameLookups = new ArrayList<WsAttributeDefNameLookup>();
        }
        return this.permissionDefNameLookups;
    }

    /**
     * Gets the value of the permissionAssignOperation property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPermissionAssignOperation() {
        return permissionAssignOperation;
    }

    /**
     * Sets the value of the permissionAssignOperation property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPermissionAssignOperation(JAXBElement<String> value) {
        this.permissionAssignOperation = value;
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
     * Gets the value of the wsAttributeAssignLookups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsAttributeAssignLookups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsAttributeAssignLookups().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsAttributeAssignLookup }
     * 
     * 
     */
    public List<WsAttributeAssignLookup> getWsAttributeAssignLookups() {
        if (wsAttributeAssignLookups == null) {
            wsAttributeAssignLookups = new ArrayList<WsAttributeAssignLookup>();
        }
        return this.wsAttributeAssignLookups;
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
     * Gets the value of the subjectRoleLookups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subjectRoleLookups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubjectRoleLookups().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsMembershipAnyLookup }
     * 
     * 
     */
    public List<WsMembershipAnyLookup> getSubjectRoleLookups() {
        if (subjectRoleLookups == null) {
            subjectRoleLookups = new ArrayList<WsMembershipAnyLookup>();
        }
        return this.subjectRoleLookups;
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
     * Gets the value of the attributeDefsToReplace property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attributeDefsToReplace property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttributeDefsToReplace().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsAttributeDefLookup }
     * 
     * 
     */
    public List<WsAttributeDefLookup> getAttributeDefsToReplace() {
        if (attributeDefsToReplace == null) {
            attributeDefsToReplace = new ArrayList<WsAttributeDefLookup>();
        }
        return this.attributeDefsToReplace;
    }

    /**
     * Gets the value of the actionsToReplace property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the actionsToReplace property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActionsToReplace().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getActionsToReplace() {
        if (actionsToReplace == null) {
            actionsToReplace = new ArrayList<String>();
        }
        return this.actionsToReplace;
    }

    /**
     * Gets the value of the disallowed property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDisallowed() {
        return disallowed;
    }

    /**
     * Sets the value of the disallowed property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDisallowed(JAXBElement<String> value) {
        this.disallowed = value;
    }

}
