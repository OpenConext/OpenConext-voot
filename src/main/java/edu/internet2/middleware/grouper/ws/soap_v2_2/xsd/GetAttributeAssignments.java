
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
 *         &lt;element name="attributeAssignType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsAttributeAssignLookups" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsAttributeAssignLookup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wsAttributeDefLookups" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsAttributeDefLookup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wsAttributeDefNameLookups" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsAttributeDefNameLookup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wsOwnerGroupLookups" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsGroupLookup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wsOwnerStemLookups" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsStemLookup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wsOwnerSubjectLookups" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsSubjectLookup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wsOwnerMembershipLookups" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsMembershipLookup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wsOwnerMembershipAnyLookups" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsMembershipAnyLookup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wsOwnerAttributeDefLookups" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsAttributeDefLookup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="actions" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="includeAssignmentsOnAssignments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="actAsSubjectLookup" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsSubjectLookup" minOccurs="0"/>
 *         &lt;element name="includeSubjectDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subjectAttributeNames" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="includeGroupDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="params" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsParam" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="enabled" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "wsAttributeAssignLookups",
    "wsAttributeDefLookups",
    "wsAttributeDefNameLookups",
    "wsOwnerGroupLookups",
    "wsOwnerStemLookups",
    "wsOwnerSubjectLookups",
    "wsOwnerMembershipLookups",
    "wsOwnerMembershipAnyLookups",
    "wsOwnerAttributeDefLookups",
    "actions",
    "includeAssignmentsOnAssignments",
    "actAsSubjectLookup",
    "includeSubjectDetail",
    "subjectAttributeNames",
    "includeGroupDetail",
    "params",
    "enabled"
})
@XmlRootElement(name = "getAttributeAssignments")
public class GetAttributeAssignments {

    @XmlElementRef(name = "clientVersion", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> clientVersion;
    @XmlElementRef(name = "attributeAssignType", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> attributeAssignType;
    @XmlElement(nillable = true)
    protected List<WsAttributeAssignLookup> wsAttributeAssignLookups;
    @XmlElement(nillable = true)
    protected List<WsAttributeDefLookup> wsAttributeDefLookups;
    @XmlElement(nillable = true)
    protected List<WsAttributeDefNameLookup> wsAttributeDefNameLookups;
    @XmlElement(nillable = true)
    protected List<WsGroupLookup> wsOwnerGroupLookups;
    @XmlElement(nillable = true)
    protected List<WsStemLookup> wsOwnerStemLookups;
    @XmlElement(nillable = true)
    protected List<WsSubjectLookup> wsOwnerSubjectLookups;
    @XmlElement(nillable = true)
    protected List<WsMembershipLookup> wsOwnerMembershipLookups;
    @XmlElement(nillable = true)
    protected List<WsMembershipAnyLookup> wsOwnerMembershipAnyLookups;
    @XmlElement(nillable = true)
    protected List<WsAttributeDefLookup> wsOwnerAttributeDefLookups;
    @XmlElement(nillable = true)
    protected List<String> actions;
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
     * Gets the value of the wsOwnerGroupLookups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsOwnerGroupLookups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsOwnerGroupLookups().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsGroupLookup }
     * 
     * 
     */
    public List<WsGroupLookup> getWsOwnerGroupLookups() {
        if (wsOwnerGroupLookups == null) {
            wsOwnerGroupLookups = new ArrayList<WsGroupLookup>();
        }
        return this.wsOwnerGroupLookups;
    }

    /**
     * Gets the value of the wsOwnerStemLookups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsOwnerStemLookups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsOwnerStemLookups().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsStemLookup }
     * 
     * 
     */
    public List<WsStemLookup> getWsOwnerStemLookups() {
        if (wsOwnerStemLookups == null) {
            wsOwnerStemLookups = new ArrayList<WsStemLookup>();
        }
        return this.wsOwnerStemLookups;
    }

    /**
     * Gets the value of the wsOwnerSubjectLookups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsOwnerSubjectLookups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsOwnerSubjectLookups().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsSubjectLookup }
     * 
     * 
     */
    public List<WsSubjectLookup> getWsOwnerSubjectLookups() {
        if (wsOwnerSubjectLookups == null) {
            wsOwnerSubjectLookups = new ArrayList<WsSubjectLookup>();
        }
        return this.wsOwnerSubjectLookups;
    }

    /**
     * Gets the value of the wsOwnerMembershipLookups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsOwnerMembershipLookups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsOwnerMembershipLookups().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsMembershipLookup }
     * 
     * 
     */
    public List<WsMembershipLookup> getWsOwnerMembershipLookups() {
        if (wsOwnerMembershipLookups == null) {
            wsOwnerMembershipLookups = new ArrayList<WsMembershipLookup>();
        }
        return this.wsOwnerMembershipLookups;
    }

    /**
     * Gets the value of the wsOwnerMembershipAnyLookups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsOwnerMembershipAnyLookups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsOwnerMembershipAnyLookups().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsMembershipAnyLookup }
     * 
     * 
     */
    public List<WsMembershipAnyLookup> getWsOwnerMembershipAnyLookups() {
        if (wsOwnerMembershipAnyLookups == null) {
            wsOwnerMembershipAnyLookups = new ArrayList<WsMembershipAnyLookup>();
        }
        return this.wsOwnerMembershipAnyLookups;
    }

    /**
     * Gets the value of the wsOwnerAttributeDefLookups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsOwnerAttributeDefLookups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsOwnerAttributeDefLookups().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsAttributeDefLookup }
     * 
     * 
     */
    public List<WsAttributeDefLookup> getWsOwnerAttributeDefLookups() {
        if (wsOwnerAttributeDefLookups == null) {
            wsOwnerAttributeDefLookups = new ArrayList<WsAttributeDefLookup>();
        }
        return this.wsOwnerAttributeDefLookups;
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

}
