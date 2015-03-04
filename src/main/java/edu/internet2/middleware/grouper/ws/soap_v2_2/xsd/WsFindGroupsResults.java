
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
 * <p>Java class for WsFindGroupsResults complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WsFindGroupsResults">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="groupResults" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsGroup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="responseMetadata" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsResponseMeta" minOccurs="0"/>
 *         &lt;element name="resultMetadata" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsResultMeta" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WsFindGroupsResults", propOrder = {
    "groupResults",
    "responseMetadata",
    "resultMetadata"
})
public class WsFindGroupsResults {

    @XmlElement(nillable = true)
    protected List<WsGroup> groupResults;
    @XmlElementRef(name = "responseMetadata", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsResponseMeta> responseMetadata;
    @XmlElementRef(name = "resultMetadata", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsResultMeta> resultMetadata;

    /**
     * Gets the value of the groupResults property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the groupResults property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGroupResults().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsGroup }
     * 
     * 
     */
    public List<WsGroup> getGroupResults() {
        if (groupResults == null) {
            groupResults = new ArrayList<WsGroup>();
        }
        return this.groupResults;
    }

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

}
