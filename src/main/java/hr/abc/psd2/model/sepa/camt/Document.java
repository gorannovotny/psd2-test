
package hr.abc.psd2.model.sepa.camt;

import javax.xml.bind.annotation.*;
import java.io.Serializable;


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
 *         &lt;element name="BkToCstmrStmt" type="{urn:iso:std:iso:20022:tech:xsd:camt.053.001.02}BankToCustomerStatementV02"/>
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
    "bkToCstmrStmt"
})

@XmlRootElement(name = "Document")
public class Document implements Serializable {

    @XmlElement(name = "BkToCstmrStmt", required = true)
    protected BankToCustomerStatementV02 bkToCstmrStmt;

    /**
     * Gets the value of the bkToCstmrStmt property.
     * 
     * @return
     *     possible object is
     *     {@link BankToCustomerStatementV02 }
     *     
     */
    public BankToCustomerStatementV02 getBkToCstmrStmt() {
        return bkToCstmrStmt;
    }

    /**
     * Sets the value of the bkToCstmrStmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BankToCustomerStatementV02 }
     *     
     */
    public void setBkToCstmrStmt(BankToCustomerStatementV02 value) {
        this.bkToCstmrStmt = value;
    }

}
