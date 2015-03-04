
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
 *         &lt;element name="wsGroupLookups" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsGroupLookup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="memberFilter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="actAsSubjectLookup" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsSubjectLookup" minOccurs="0"/>
 *         &lt;element name="fieldName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includeGroupDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includeSubjectDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subjectAttributeNames" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="params" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsParam" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="sourceIds" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="pointInTimeFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pointInTimeTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "wsGroupLookups",
    "memberFilter",
    "actAsSubjectLookup",
    "fieldName",
    "includeGroupDetail",
    "includeSubjectDetail",
    "subjectAttributeNames",
    "params",
    "sourceIds",
    "pointInTimeFrom",
    "pointInTimeTo"
})
@XmlRootElement(name = "getMembers")
public class GetMembers {

    @XmlElementRef(name = "clientVersion", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> clientVersion;
    @XmlElement(nillable = true)
    protected List<WsGroupLookup> wsGroupLookups;
    @XmlElementRef(name = "memberFilter", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> memberFilter;
    @XmlElementRef(name = "actAsSubjectLookup", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsSubjectLookup> actAsSubjectLookup;
    @XmlElementRef(name = "fieldName", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> fieldName;
    @XmlElementRef(name = "includeGroupDetail", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> includeGroupDetail;
    @XmlElementRef(name = "includeSubjectDetail", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> includeSubjectDetail;
    @XmlElement(nillable = true)
    protected List<String> subjectAttributeNames;
    @XmlElement(nillable = true)
    protected List<WsParam> params;
    @XmlElement(nillable = true)
    protected List<String> sourceIds;
    @XmlElementRef(name = "pointInTimeFrom", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> pointInTimeFrom;
    @XmlElementRef(name = "pointInTimeTo", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> pointInTimeTo;

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
     * Gets the value of the wsGroupLookups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsGroupLookups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsGroupLookups().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsGroupLookup }
     * 
     * 
     */
    public List<WsGroupLookup> getWsGroupLookups() {
        if (wsGroupLookups == null) {
            wsGroupLookups = new ArrayList<WsGroupLookup>();
        }
        return this.wsGroupLookups;
    }

    /**
     * Gets the value of the memberFilter property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMemberFilter() {
        return memberFilter;
    }

    /**
     * Sets the value of the memberFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMemberFilter(JAXBElement<String> value) {
        this.memberFilter = value;
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
     * Gets the value of the fieldName property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFieldName() {
        return fieldName;
    }

    /**
     * Sets the value of the fieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFieldName(JAXBElement<String> value) {
        this.fieldName = value;
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
     * Gets the value of the sourceIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sourceIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSourceIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSourceIds() {
        if (sourceIds == null) {
            sourceIds = new ArrayList<String>();
        }
        return this.sourceIds;
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

}
