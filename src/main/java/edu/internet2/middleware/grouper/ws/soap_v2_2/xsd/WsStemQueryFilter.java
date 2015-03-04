
package edu.internet2.middleware.grouper.ws.soap_v2_2.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WsStemQueryFilter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WsStemQueryFilter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="parentStemName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parentStemNameScope" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="queryTerm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stemAttributeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stemAttributeValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stemName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stemQueryFilter0" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsStemQueryFilter" minOccurs="0"/>
 *         &lt;element name="stemQueryFilter1" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsStemQueryFilter" minOccurs="0"/>
 *         &lt;element name="stemQueryFilterType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stemUuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WsStemQueryFilter", propOrder = {
    "parentStemName",
    "parentStemNameScope",
    "queryTerm",
    "stemAttributeName",
    "stemAttributeValue",
    "stemName",
    "stemQueryFilter0",
    "stemQueryFilter1",
    "stemQueryFilterType",
    "stemUuid"
})
public class WsStemQueryFilter {

    @XmlElementRef(name = "parentStemName", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> parentStemName;
    @XmlElementRef(name = "parentStemNameScope", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> parentStemNameScope;
    @XmlElementRef(name = "queryTerm", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> queryTerm;
    @XmlElementRef(name = "stemAttributeName", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> stemAttributeName;
    @XmlElementRef(name = "stemAttributeValue", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> stemAttributeValue;
    @XmlElementRef(name = "stemName", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> stemName;
    @XmlElementRef(name = "stemQueryFilter0", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsStemQueryFilter> stemQueryFilter0;
    @XmlElementRef(name = "stemQueryFilter1", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsStemQueryFilter> stemQueryFilter1;
    @XmlElementRef(name = "stemQueryFilterType", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> stemQueryFilterType;
    @XmlElementRef(name = "stemUuid", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> stemUuid;

    /**
     * Gets the value of the parentStemName property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getParentStemName() {
        return parentStemName;
    }

    /**
     * Sets the value of the parentStemName property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setParentStemName(JAXBElement<String> value) {
        this.parentStemName = value;
    }

    /**
     * Gets the value of the parentStemNameScope property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getParentStemNameScope() {
        return parentStemNameScope;
    }

    /**
     * Sets the value of the parentStemNameScope property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setParentStemNameScope(JAXBElement<String> value) {
        this.parentStemNameScope = value;
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
     * Gets the value of the stemAttributeName property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStemAttributeName() {
        return stemAttributeName;
    }

    /**
     * Sets the value of the stemAttributeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStemAttributeName(JAXBElement<String> value) {
        this.stemAttributeName = value;
    }

    /**
     * Gets the value of the stemAttributeValue property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStemAttributeValue() {
        return stemAttributeValue;
    }

    /**
     * Sets the value of the stemAttributeValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStemAttributeValue(JAXBElement<String> value) {
        this.stemAttributeValue = value;
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
     * Gets the value of the stemQueryFilter0 property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsStemQueryFilter }{@code >}
     *     
     */
    public JAXBElement<WsStemQueryFilter> getStemQueryFilter0() {
        return stemQueryFilter0;
    }

    /**
     * Sets the value of the stemQueryFilter0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsStemQueryFilter }{@code >}
     *     
     */
    public void setStemQueryFilter0(JAXBElement<WsStemQueryFilter> value) {
        this.stemQueryFilter0 = value;
    }

    /**
     * Gets the value of the stemQueryFilter1 property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsStemQueryFilter }{@code >}
     *     
     */
    public JAXBElement<WsStemQueryFilter> getStemQueryFilter1() {
        return stemQueryFilter1;
    }

    /**
     * Sets the value of the stemQueryFilter1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsStemQueryFilter }{@code >}
     *     
     */
    public void setStemQueryFilter1(JAXBElement<WsStemQueryFilter> value) {
        this.stemQueryFilter1 = value;
    }

    /**
     * Gets the value of the stemQueryFilterType property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStemQueryFilterType() {
        return stemQueryFilterType;
    }

    /**
     * Sets the value of the stemQueryFilterType property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStemQueryFilterType(JAXBElement<String> value) {
        this.stemQueryFilterType = value;
    }

    /**
     * Gets the value of the stemUuid property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStemUuid() {
        return stemUuid;
    }

    /**
     * Sets the value of the stemUuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStemUuid(JAXBElement<String> value) {
        this.stemUuid = value;
    }

}
