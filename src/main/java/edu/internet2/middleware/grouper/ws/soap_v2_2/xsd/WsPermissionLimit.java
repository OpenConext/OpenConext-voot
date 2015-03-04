
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
 * <p>Java class for WsPermissionLimit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WsPermissionLimit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="attributeAssignId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attributeDefNameId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attributeDefNameName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsAttributeAssignValues" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsAttributeAssignValue" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WsPermissionLimit", propOrder = {
    "attributeAssignId",
    "attributeDefNameId",
    "attributeDefNameName",
    "wsAttributeAssignValues"
})
public class WsPermissionLimit {

    @XmlElementRef(name = "attributeAssignId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> attributeAssignId;
    @XmlElementRef(name = "attributeDefNameId", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> attributeDefNameId;
    @XmlElementRef(name = "attributeDefNameName", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> attributeDefNameName;
    @XmlElement(nillable = true)
    protected List<WsAttributeAssignValue> wsAttributeAssignValues;

    /**
     * Gets the value of the attributeAssignId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAttributeAssignId() {
        return attributeAssignId;
    }

    /**
     * Sets the value of the attributeAssignId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAttributeAssignId(JAXBElement<String> value) {
        this.attributeAssignId = value;
    }

    /**
     * Gets the value of the attributeDefNameId property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAttributeDefNameId() {
        return attributeDefNameId;
    }

    /**
     * Sets the value of the attributeDefNameId property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAttributeDefNameId(JAXBElement<String> value) {
        this.attributeDefNameId = value;
    }

    /**
     * Gets the value of the attributeDefNameName property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAttributeDefNameName() {
        return attributeDefNameName;
    }

    /**
     * Sets the value of the attributeDefNameName property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAttributeDefNameName(JAXBElement<String> value) {
        this.attributeDefNameName = value;
    }

    /**
     * Gets the value of the wsAttributeAssignValues property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsAttributeAssignValues property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWsAttributeAssignValues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsAttributeAssignValue }
     * 
     * 
     */
    public List<WsAttributeAssignValue> getWsAttributeAssignValues() {
        if (wsAttributeAssignValues == null) {
            wsAttributeAssignValues = new ArrayList<WsAttributeAssignValue>();
        }
        return this.wsAttributeAssignValues;
    }

}
