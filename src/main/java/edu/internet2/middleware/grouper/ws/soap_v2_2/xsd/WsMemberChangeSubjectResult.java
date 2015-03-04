
package edu.internet2.middleware.grouper.ws.soap_v2_2.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WsMemberChangeSubjectResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WsMemberChangeSubjectResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resultMetadata" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsResultMeta" minOccurs="0"/>
 *         &lt;element name="wsSubjectNew" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsSubject" minOccurs="0"/>
 *         &lt;element name="wsSubjectOld" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsSubject" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WsMemberChangeSubjectResult", propOrder = {
    "resultMetadata",
    "wsSubjectNew",
    "wsSubjectOld"
})
public class WsMemberChangeSubjectResult {

    @XmlElementRef(name = "resultMetadata", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsResultMeta> resultMetadata;
    @XmlElementRef(name = "wsSubjectNew", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsSubject> wsSubjectNew;
    @XmlElementRef(name = "wsSubjectOld", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsSubject> wsSubjectOld;

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
     * Gets the value of the wsSubjectNew property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsSubject }{@code >}
     *     
     */
    public JAXBElement<WsSubject> getWsSubjectNew() {
        return wsSubjectNew;
    }

    /**
     * Sets the value of the wsSubjectNew property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsSubject }{@code >}
     *     
     */
    public void setWsSubjectNew(JAXBElement<WsSubject> value) {
        this.wsSubjectNew = value;
    }

    /**
     * Gets the value of the wsSubjectOld property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsSubject }{@code >}
     *     
     */
    public JAXBElement<WsSubject> getWsSubjectOld() {
        return wsSubjectOld;
    }

    /**
     * Sets the value of the wsSubjectOld property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsSubject }{@code >}
     *     
     */
    public void setWsSubjectOld(JAXBElement<WsSubject> value) {
        this.wsSubjectOld = value;
    }

}
