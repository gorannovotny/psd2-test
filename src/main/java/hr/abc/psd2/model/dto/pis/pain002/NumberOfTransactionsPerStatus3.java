
package hr.abc.psd2.model.dto.pis.pain002;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;


/**
 * <p>Java class for NumberOfTransactionsPerStatus3 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NumberOfTransactionsPerStatus3">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DtldNbOfTxs" type="{urn:iso:std:iso:20022:tech:xsd:pain.002.001.03}Max15NumericText"/>
 *         &lt;element name="DtldSts" type="{urn:iso:std:iso:20022:tech:xsd:pain.002.001.03}TransactionIndividualStatus3Code"/>
 *         &lt;element name="DtldCtrlSum" type="{urn:iso:std:iso:20022:tech:xsd:pain.002.001.03}DecimalNumber" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NumberOfTransactionsPerStatus3", propOrder = {
    "dtldNbOfTxs",
    "dtldSts",
    "dtldCtrlSum"
})
public class NumberOfTransactionsPerStatus3 {

    @XmlElement(name = "DtldNbOfTxs", required = true)
    protected String dtldNbOfTxs;
    @XmlElement(name = "DtldSts", required = true)
    @XmlSchemaType(name = "string")
    protected TransactionIndividualStatus3Code dtldSts;
    @XmlElement(name = "DtldCtrlSum")
    protected BigDecimal dtldCtrlSum;

    /**
     * Gets the value of the dtldNbOfTxs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDtldNbOfTxs() {
        return dtldNbOfTxs;
    }

    /**
     * Sets the value of the dtldNbOfTxs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDtldNbOfTxs(String value) {
        this.dtldNbOfTxs = value;
    }

    /**
     * Gets the value of the dtldSts property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionIndividualStatus3Code }
     *     
     */
    public TransactionIndividualStatus3Code getDtldSts() {
        return dtldSts;
    }

    /**
     * Sets the value of the dtldSts property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionIndividualStatus3Code }
     *     
     */
    public void setDtldSts(TransactionIndividualStatus3Code value) {
        this.dtldSts = value;
    }

    /**
     * Gets the value of the dtldCtrlSum property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDtldCtrlSum() {
        return dtldCtrlSum;
    }

    /**
     * Sets the value of the dtldCtrlSum property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDtldCtrlSum(BigDecimal value) {
        this.dtldCtrlSum = value;
    }

}
