
package edu.internet2.middleware.grouper.ws.soap_v2_2.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WsQueryFilter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WsQueryFilter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="groupAttributeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupAttributeValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupUuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="queryFilter0" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsQueryFilter" minOccurs="0"/>
 *         &lt;element name="queryFilter1" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsQueryFilter" minOccurs="0"/>
 *         &lt;element name="queryFilterType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="queryTerm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stemName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stemNameScope" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WsQueryFilter", propOrder = {
    "groupAttributeName",
    "groupAttributeValue",
    "groupName",
    "groupTypeName",
    "groupUuid",
    "queryFilter0",
    "queryFilter1",
    "queryFilterType",
    "queryTerm",
    "stemName",
    "stemNameScope"
})
public class WsQueryFilter {

    @XmlElementRef(name = "groupAttributeName", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> groupAttributeName;
    @XmlElementRef(name = "groupAttributeValue", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> groupAttributeValue;
    @XmlElementRef(name = "groupName", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> groupName;
    @XmlElementRef(name = "groupTypeName", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> groupTypeName;
    @XmlElementRef(name = "groupUuid", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> groupUuid;
    @XmlElementRef(name = "queryFilter0", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsQueryFilter> queryFilter0;
    @XmlElementRef(name = "queryFilter1", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsQueryFilter> queryFilter1;
    @XmlElementRef(name = "queryFilterType", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> queryFilterType;
    @XmlElementRef(name = "queryTerm", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> queryTerm;
    @XmlElementRef(name = "stemName", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> stemName;
    @XmlElementRef(name = "stemNameScope", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> stemNameScope;

    /**
     * Gets the value of the groupAttributeName property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGroupAttributeName() {
        return groupAttributeName;
    }

    /**
     * Sets the value of the groupAttributeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGroupAttributeName(JAXBElement<String> value) {
        this.groupAttributeName = value;
    }

    /**
     * Gets the value of the groupAttributeValue property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGroupAttributeValue() {
        return groupAttributeValue;
    }

    /**
     * Sets the value of the groupAttributeValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGroupAttributeValue(JAXBElement<String> value) {
        this.groupAttributeValue = value;
    }

    /**
     * Gets the value of the groupName property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGroupName() {
        return groupName;
    }

    /**
     * Sets the value of the groupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGroupName(JAXBElement<String> value) {
        this.groupName = value;
    }

    /**
     * Gets the value of the groupTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGroupTypeName() {
        return groupTypeName;
    }

    /**
     * Sets the value of the groupTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGroupTypeName(JAXBElement<String> value) {
        this.groupTypeName = value;
    }

    /**
     * Gets the value of the groupUuid property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGroupUuid() {
        return groupUuid;
    }

    /**
     * Sets the value of the groupUuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGroupUuid(JAXBElement<String> value) {
        this.groupUuid = value;
    }

    /**
     * Gets the value of the queryFilter0 property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsQueryFilter }{@code >}
     *     
     */
    public JAXBElement<WsQueryFilter> getQueryFilter0() {
        return queryFilter0;
    }

    /**
     * Sets the value of the queryFilter0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsQueryFilter }{@code >}
     *     
     */
    public void setQueryFilter0(JAXBElement<WsQueryFilter> value) {
        this.queryFilter0 = value;
    }

    /**
     * Gets the value of the queryFilter1 property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsQueryFilter }{@code >}
     *     
     */
    public JAXBElement<WsQueryFilter> getQueryFilter1() {
        return queryFilter1;
    }

    /**
     * Sets the value of the queryFilter1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsQueryFilter }{@code >}
     *     
     */
    public void setQueryFilter1(JAXBElement<WsQueryFilter> value) {
        this.queryFilter1 = value;
    }

    /**
     * Gets the value of the queryFilterType property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getQueryFilterType() {
        return queryFilterType;
    }

    /**
     * Sets the value of the queryFilterType property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setQueryFilterType(JAXBElement<String> value) {
        this.queryFilterType = value;
    }

    /**
     * Gets the value of the queryTerm property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getQueryTerm() {
        return queryTerm;
    }

    /**
     * Sets the value of the queryTerm property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setQueryTerm(JAXBElement<String> value) {
        this.queryTerm = value;
    }

    /**
     * Gets the value of the stemName property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStemName() {
        return stemName;
    }

    /**
     * Sets the value of the stemName property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStemName(JAXBElement<String> value) {
        this.stemName = value;
    }

    /**
     * Gets the value of the stemNameScope property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStemNameScope() {
        return stemNameScope;
    }

    /**
     * Sets the value of the stemNameScope property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStemNameScope(JAXBElement<String> value) {
        this.stemNameScope = value;
    }

}
