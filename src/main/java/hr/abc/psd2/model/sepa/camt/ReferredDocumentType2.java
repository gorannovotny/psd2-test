
package hr.abc.psd2.model.sepa.camt;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


/**
 * <p>Java class for ReferredDocumentType2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReferredDocumentType2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CdOrPrtry" type="{urn:iso:std:iso:20022:tech:xsd:camt.053.001.02}ReferredDocumentType1Choice"/>
 *         &lt;element name="Issr" type="{urn:iso:std:iso:20022:tech:xsd:camt.053.001.02}Max35Text" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferredDocumentType2", propOrder = {
    "cdOrPrtry",
    "issr"
})
public class ReferredDocumentType2 implements Serializable {

    @XmlElement(name = "CdOrPrtry", required = true)
    protected ReferredDocumentType1Choice cdOrPrtry;
    @XmlElement(name = "Issr")
    protected String issr;

    /**
     * Gets the value of the cdOrPrtry property.
     * 
     * @return
     *     possible object is
     *     {@link ReferredDocumentType1Choice }
     *     
     */
    public ReferredDocumentType1Choice getCdOrPrtry() {
        return cdOrPrtry;
    }

    /**
     * Sets the value of the cdOrPrtry property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferredDocumentType1Choice }
     *     
     */
    public void setCdOrPrtry(ReferredDocumentType1Choice value) {
        this.cdOrPrtry = value;
    }

    /**
     * Gets the value of the issr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssr() {
        return issr;
    }

    /**
     * Sets the value of the issr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssr(String value) {
        this.issr = value;
    }

}
