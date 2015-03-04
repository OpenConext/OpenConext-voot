
package edu.internet2.middleware.grouper.ws.soap_v2_2.xsd;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WsGetAttributeAssignmentsResults complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WsGetAttributeAssignmentsResults">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="responseMetadata" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsResponseMeta" minOccurs="0"/>
 *         &lt;element name="resultMetadata" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsResultMeta" minOccurs="0"/>
 *         &lt;element name="subjectAttributeNames" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wsAttributeAssigns" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsAttributeAssign" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wsAttributeDefNames" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsAttributeDefName" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wsAttributeDefs" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsAttributeDef" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wsGroups" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsGroup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wsMemberships" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsMembership" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wsStems" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsStem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wsSubjects" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsSubject" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WsGetAttributeAssignmentsResults", propOrder = {
    "responseMetadata",
    "resultMetadata",
    "subjectAttributeNames",
    "wsAttributeAssigns",
    "wsAttributeDefNames",
    "wsAttributeDefs",
    "wsGroups",
    "wsMemberships",
    "wsStems",
    "wsSubjects"
})
public class WsGetAttributeAssignmentsResults {

    @XmlElementRef(name = "responseMetadata", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsResponseMeta> responseMetadata;
    @XmlElementRef(name = "resultMetadata", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsResultMeta> resultMetadata;
    @XmlElement(nillable = true)
    protected List<String> subjectAttributeNames;
    @XmlElement(nillable = true)
    protected List<WsAttributeAssign> wsAttributeAssigns;
    @XmlElement(nillable = true)
    protected List<WsAttributeDefName> wsAttributeDefNames;
    @XmlElement(nillable = true)
    protected List<WsAttributeDef> wsAttributeDefs;
    @XmlElement(nillable = true)
    protected List<WsGroup> wsGroups;
    @XmlElement(nillable = true)
    protected List<WsMembership> wsMemberships;
    @XmlElement(nillable = true)
    protected List<WsStem> wsStems;
    @XmlElement(nillable = true)
    protected List<WsSubject> wsSubjects;

    /**
     * Gets the value of the responseMetadata property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsResponseMeta }{@code >}
     *     
     */
    public JAXBElement<WsResponseMeta> getResponseMetadata() {
        return responseMetadata;
    }

    /**
     * Sets the value of the responseMetadata property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsResponseMeta }{@code >}
     *     
     */
    public void setResponseMetadata(JAXBElement<WsResponseMeta> value) {
        this.responseMetadata = value;
    }

    /**
     * Gets the value of the resultMetadata property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsResultMeta }{@code >}
     *     
     */
    public JAXBElement<WsResultMeta> getResultMetadata() {
        return resultMetadata;
    }

    /**
     * Sets the value of the resultMetadata property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsResultMeta }{@code >}
     *     
     */
    public void setResultMetadata(JAXBElement<WsResultMeta> value) {
        this.resultMetadata = value;
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
     * Gets the value of the wsAttributeAssigns property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsAttributeAssigns property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsAttributeAssigns().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsAttributeAssign }
     * 
     * 
     */
    public List<WsAttributeAssign> getWsAttributeAssigns() {
        if (wsAttributeAssigns == null) {
            wsAttributeAssigns = new ArrayList<WsAttributeAssign>();
        }
        return this.wsAttributeAssigns;
    }

    /**
     * Gets the value of the wsAttributeDefNames property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsAttributeDefNames property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsAttributeDefNames().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsAttributeDefName }
     * 
     * 
     */
    public List<WsAttributeDefName> getWsAttributeDefNames() {
        if (wsAttributeDefNames == null) {
            wsAttributeDefNames = new ArrayList<WsAttributeDefName>();
        }
        return this.wsAttributeDefNames;
    }

    /**
     * Gets the value of the wsAttributeDefs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsAttributeDefs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsAttributeDefs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsAttributeDef }
     * 
     * 
     */
    public List<WsAttributeDef> getWsAttributeDefs() {
        if (wsAttributeDefs == null) {
            wsAttributeDefs = new ArrayList<WsAttributeDef>();
        }
        return this.wsAttributeDefs;
    }

    /**
     * Gets the value of the wsGroups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsGroups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsGroups().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsGroup }
     * 
     * 
     */
    public List<WsGroup> getWsGroups() {
        if (wsGroups == null) {
            wsGroups = new ArrayList<WsGroup>();
        }
        return this.wsGroups;
    }

    /**
     * Gets the value of the wsMemberships property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsMemberships property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsMemberships().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsMembership }
     * 
     * 
     */
    public List<WsMembership> getWsMemberships() {
        if (wsMemberships == null) {
            wsMemberships = new ArrayList<WsMembership>();
        }
        return this.wsMemberships;
    }

    /**
     * Gets the value of the wsStems property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsStems property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsStems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsStem }
     * 
     * 
     */
    public List<WsStem> getWsStems() {
        if (wsStems == null) {
            wsStems = new ArrayList<WsStem>();
        }
        return this.wsStems;
    }

    /**
     * Gets the value of the wsSubjects property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsSubjects property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsSubjects().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsSubject }
     * 
     * 
     */
    public List<WsSubject> getWsSubjects() {
        if (wsSubjects == null) {
            wsSubjects = new ArrayList<WsSubject>();
        }
        return this.wsSubjects;
    }

}
