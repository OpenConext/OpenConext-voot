
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
 * <p>Java class for WsGetGrouperPrivilegesLiteResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WsGetGrouperPrivilegesLiteResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="params" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsParam" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="privilegeResults" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsGrouperPrivilegeResult" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="responseMetadata" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsResponseMeta" minOccurs="0"/>
 *         &lt;element name="resultMetadata" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsResultMeta" minOccurs="0"/>
 *         &lt;element name="subjectAttributeNames" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WsGetGrouperPrivilegesLiteResult", propOrder = {
    "params",
    "privilegeResults",
    "responseMetadata",
    "resultMetadata",
    "subjectAttributeNames"
})
public class WsGetGrouperPrivilegesLiteResult {

    @XmlElement(nillable = true)
    protected List<WsParam> params;
    @XmlElement(nillable = true)
    protected List<WsGrouperPrivilegeResult> privilegeResults;
    @XmlElementRef(name = "responseMetadata", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsResponseMeta> responseMetadata;
    @XmlElementRef(name = "resultMetadata", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsResultMeta> resultMetadata;
    @XmlElement(nillable = true)
    protected List<String> subjectAttributeNames;

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
     * Gets the value of the privilegeResults property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the privilegeResults property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrivilegeResults().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsGrouperPrivilegeResult }
     * 
     * 
     */
    public List<WsGrouperPrivilegeResult> getPrivilegeResults() {
        if (privilegeResults == null) {
            privilegeResults = new ArrayList<WsGrouperPrivilegeResult>();
        }
        return this.privilegeResults;
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

}
