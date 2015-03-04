
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
 * <p>Java class for WsMemberChangeSubjectResults complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WsMemberChangeSubjectResults">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="responseMetadata" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsResponseMeta" minOccurs="0"/>
 *         &lt;element name="resultMetadata" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsResultMeta" minOccurs="0"/>
 *         &lt;element name="results" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsMemberChangeSubjectResult" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "WsMemberChangeSubjectResults", propOrder = {
    "responseMetadata",
    "resultMetadata",
    "results",
    "subjectAttributeNames"
})
public class WsMemberChangeSubjectResults {

    @XmlElementRef(name = "responseMetadata", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsResponseMeta> responseMetadata;
    @XmlElementRef(name = "resultMetadata", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsResultMeta> resultMetadata;
    @XmlElement(nillable = true)
    protected List<WsMemberChangeSubjectResult> results;
    @XmlElement(nillable = true)
    protected List<String> subjectAttributeNames;

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
     * Gets the value of the results property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the results property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResults().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsMemberChangeSubjectResult }
     * 
     * 
     */
    public List<WsMemberChangeSubjectResult> getResults() {
        if (results == null) {
            results = new ArrayList<WsMemberChangeSubjectResult>();
        }
        return this.results;
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
