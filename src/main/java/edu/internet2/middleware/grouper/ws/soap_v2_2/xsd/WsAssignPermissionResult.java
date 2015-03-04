
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
 * <p>Java class for WsAssignPermissionResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WsAssignPermissionResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="changed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deleted" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsAttributeAssigns" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsAttributeAssign" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WsAssignPermissionResult", propOrder = {
    "changed",
    "deleted",
    "wsAttributeAssigns"
})
public class WsAssignPermissionResult {

    @XmlElementRef(name = "changed", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> changed;
    @XmlElementRef(name = "deleted", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> deleted;
    @XmlElement(nillable = true)
    protected List<WsAttributeAssign> wsAttributeAssigns;

    /**
     * Gets the value of the changed property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getChanged() {
        return changed;
    }

    /**
     * Sets the value of the changed property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setChanged(JAXBElement<String> value) {
        this.changed = value;
    }

    /**
     * Gets the value of the deleted property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDeleted() {
        return deleted;
    }

    /**
     * Sets the value of the deleted property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDeleted(JAXBElement<String> value) {
        this.deleted = value;
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

}
