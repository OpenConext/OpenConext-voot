
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
 * <p>Java class for WsAssignPermissionsLiteResults complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WsAssignPermissionsLiteResults">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="responseMetadata" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsResponseMeta" minOccurs="0"/>
 *         &lt;element name="resultMetadata" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsResultMeta" minOccurs="0"/>
 *         &lt;element name="subjectAttributeNames" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wsAttributeDefName" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsAttributeDefName" minOccurs="0"/>
 *         &lt;element name="wsAttributeDefs" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsAttributeDef" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wsGroup" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsGroup" minOccurs="0"/>
 *         &lt;element name="wsPermissionAssignResult" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsAssignPermissionResult" minOccurs="0"/>
 *         &lt;element name="wsSubject" type="{http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd}WsSubject" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WsAssignPermissionsLiteResults", propOrder = {
    "responseMetadata",
    "resultMetadata",
    "subjectAttributeNames",
    "wsAttributeDefName",
    "wsAttributeDefs",
    "wsGroup",
    "wsPermissionAssignResult",
    "wsSubject"
})
public class WsAssignPermissionsLiteResults {

    @XmlElementRef(name = "responseMetadata", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsResponseMeta> responseMetadata;
    @XmlElementRef(name = "resultMetadata", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsResultMeta> resultMetadata;
    @XmlElement(nillable = true)
    protected List<String> subjectAttributeNames;
    @XmlElementRef(name = "wsAttributeDefName", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsAttributeDefName> wsAttributeDefName;
    @XmlElement(nillable = true)
    protected List<WsAttributeDef> wsAttributeDefs;
    @XmlElementRef(name = "wsGroup", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsGroup> wsGroup;
    @XmlElementRef(name = "wsPermissionAssignResult", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsAssignPermissionResult> wsPermissionAssignResult;
    @XmlElementRef(name = "wsSubject", namespace = "http://soap_v2_0.ws.grouper.middleware.internet2.edu/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<WsSubject> wsSubject;

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
     * Gets the value of the wsAttributeDefName property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsAttributeDefName }{@code >}
     *     
     */
    public JAXBElement<WsAttributeDefName> getWsAttributeDefName() {
        return wsAttributeDefName;
    }

    /**
     * Sets the value of the wsAttributeDefName property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsAttributeDefName }{@code >}
     *     
     */
    public void setWsAttributeDefName(JAXBElement<WsAttributeDefName> value) {
        this.wsAttributeDefName = value;
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
     * Gets the value of the wsGroup property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsGroup }{@code >}
     *     
     */
    public JAXBElement<WsGroup> getWsGroup() {
        return wsGroup;
    }

    /**
     * Sets the value of the wsGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsGroup }{@code >}
     *     
     */
    public void setWsGroup(JAXBElement<WsGroup> value) {
        this.wsGroup = value;
    }

    /**
     * Gets the value of the wsPermissionAssignResult property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsAssignPermissionResult }{@code >}
     *     
     */
    public JAXBElement<WsAssignPermissionResult> getWsPermissionAssignResult() {
        return wsPermissionAssignResult;
    }

    /**
     * Sets the value of the wsPermissionAssignResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsAssignPermissionResult }{@code >}
     *     
     */
    public void setWsPermissionAssignResult(JAXBElement<WsAssignPermissionResult> value) {
        this.wsPermissionAssignResult = value;
    }

    /**
     * Gets the value of the wsSubject property.
     * 
     * @return
     *     possible object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsSubject }{@code >}
     *     
     */
    public JAXBElement<WsSubject> getWsSubject() {
        return wsSubject;
    }

    /**
     * Sets the value of the wsSubject property.
     * 
     * @param value
     *     allowed object is
     *     {@link javax.xml.bind.JAXBElement }{@code <}{@link edu.internet2.middleware.grouper.ws.soap_v2_2.xsd.WsSubject }{@code >}
     *     
     */
    public void setWsSubject(JAXBElement<WsSubject> value) {
        this.wsSubject = value;
    }

}
