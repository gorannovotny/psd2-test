
package hr.abc.psd2.model.dto.pis.pain002;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for Document complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Document">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CstmrPmtStsRpt" type="{urn:iso:std:iso:20022:tech:xsd:pain.002.001.03}CustomerPaymentStatusReportV03"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Document", propOrder = {
    "cstmrPmtStsRpt"
})
@XmlRootElement
public class Document {

    @XmlElement(name = "CstmrPmtStsRpt", required = true)
    protected CustomerPaymentStatusReportV03 cstmrPmtStsRpt;

    /**
     * Gets the value of the cstmrPmtStsRpt property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerPaymentStatusReportV03 }
     *     
     */
    public CustomerPaymentStatusReportV03 getCstmrPmtStsRpt() {
        return cstmrPmtStsRpt;
    }

    /**
     * Sets the value of the cstmrPmtStsRpt property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerPaymentStatusReportV03 }
     *     
     */
    public void setCstmrPmtStsRpt(CustomerPaymentStatusReportV03 value) {
        this.cstmrPmtStsRpt = value;
    }

}
