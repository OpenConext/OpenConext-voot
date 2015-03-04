
package edu.internet2.middleware.grouper.ws.soap_v2_2.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WsPermissionAssignDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WsPermissionAssignDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actionDepth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="actionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="assignmentNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attributeDefNameSetDepth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="disabledTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="enabledTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="heuristicFriendlyScore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="immediateMembership" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="immediatePermission" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memberId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="membershipDepth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="permissionDelegatable" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roleSetDepth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WsPermissionAssignDetail", propOrder = {
    "actionDepth",
    "actionId",
    "assignmentNotes",
    "attributeDefNameSetDepth",
    "disabledTime",
    "enabledTime",
    "heuristicFriendlyScore",
    "immediateMembership",
    "immediatePermission",
    "memberId",
    "membershipDepth",
    "permissionDelegatable",
    "roleSetDepth"
})
public class WsPermissionAssignDetail {

    @XmlElementRef(name = "actionDepth", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> actionDepth;
    @XmlElementRef(name = "actionId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> actionId;
    @XmlElementRef(name = "assignmentNotes", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> assignmentNotes;
    @XmlElementRef(name = "attributeDefNameSetDepth", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> attributeDefNameSetDepth;
    @XmlElementRef(name = "disabledTime", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> disabledTime;
    @XmlElementRef(name = "enabledTime", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> enabledTime;
    @XmlElementRef(name = "heuristicFriendlyScore", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> heuristicFriendlyScore;
    @XmlElementRef(name = "immediateMembership", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> immediateMembership;
    @XmlElementRef(name = "immediatePermission", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> immediatePermission;
    @XmlElementRef(name = "memberId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> memberId;
    @XmlElementRef(name = "membershipDepth", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> membershipDepth;
    @XmlElementRef(name = "permissionDelegatable", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> permissionDelegatable;
    @XmlElementRef(name = "roleSetDepth", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> roleSetDepth;

    /**
     * Gets the value of the actionDepth property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getActionDepth() {
        return actionDepth;
    }

    /**
     * Sets the value of the actionDepth property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setActionDepth(JAXBElement<String> value) {
        this.actionDepth = value;
    }

    /**
     * Gets the value of the actionId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getActionId() {
        return actionId;
    }

    /**
     * Sets the value of the actionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setActionId(JAXBElement<String> value) {
        this.actionId = value;
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
     * Gets the value of the attributeDefNameSetDepth property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAttributeDefNameSetDepth() {
        return attributeDefNameSetDepth;
    }

    /**
     * Sets the value of the attributeDefNameSetDepth property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAttributeDefNameSetDepth(JAXBElement<String> value) {
        this.attributeDefNameSetDepth = value;
    }

    /**
     * Gets the value of the disabledTime property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDisabledTime() {
        return disabledTime;
    }

    /**
     * Sets the value of the disabledTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDisabledTime(JAXBElement<String> value) {
        this.disabledTime = value;
    }

    /**
     * Gets the value of the enabledTime property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEnabledTime() {
        return enabledTime;
    }

    /**
     * Sets the value of the enabledTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEnabledTime(JAXBElement<String> value) {
        this.enabledTime = value;
    }

    /**
     * Gets the value of the heuristicFriendlyScore property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getHeuristicFriendlyScore() {
        return heuristicFriendlyScore;
    }

    /**
     * Sets the value of the heuristicFriendlyScore property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setHeuristicFriendlyScore(JAXBElement<String> value) {
        this.heuristicFriendlyScore = value;
    }

    /**
     * Gets the value of the immediateMembership property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImmediateMembership() {
        return immediateMembership;
    }

    /**
     * Sets the value of the immediateMembership property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImmediateMembership(JAXBElement<String> value) {
        this.immediateMembership = value;
    }

    /**
     * Gets the value of the immediatePermission property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImmediatePermission() {
        return immediatePermission;
    }

    /**
     * Sets the value of the immediatePermission property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImmediatePermission(JAXBElement<String> value) {
        this.immediatePermission = value;
    }

    /**
     * Gets the value of the memberId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMemberId() {
        return memberId;
    }

    /**
     * Sets the value of the memberId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMemberId(JAXBElement<String> value) {
        this.memberId = value;
    }

    /**
     * Gets the value of the membershipDepth property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMembershipDepth() {
        return membershipDepth;
    }

    /**
     * Sets the value of the membershipDepth property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMembershipDepth(JAXBElement<String> value) {
        this.membershipDepth = value;
    }

    /**
     * Gets the value of the permissionDelegatable property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPermissionDelegatable() {
        return permissionDelegatable;
    }

    /**
     * Sets the value of the permissionDelegatable property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPermissionDelegatable(JAXBElement<String> value) {
        this.permissionDelegatable = value;
    }

    /**
     * Gets the value of the roleSetDepth property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRoleSetDepth() {
        return roleSetDepth;
    }

    /**
     * Sets the value of the roleSetDepth property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRoleSetDepth(JAXBElement<String> value) {
        this.roleSetDepth = value;
    }

}
