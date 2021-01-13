package hr.abc.psd2.model.cards;

import java.time.LocalDate;
import java.util.Objects;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * Exchange Rate.
 **/

@Schema(description = "Exchange Rate.")
public class ReportExchangeRate   {
  private String sourceCurrency = null;
  private String exchangeRate = null;
  private String unitCurrency = null;
  private String targetCurrency = null;
  private LocalDate quotationDate = null;
  private String contractIdentification = null;

  /**
   **/
  public ReportExchangeRate sourceCurrency(String sourceCurrency) {
    this.sourceCurrency = sourceCurrency;
    return this;
  }

 
  public String getSourceCurrency() {
    return sourceCurrency;
  }
  public void setSourceCurrency(String sourceCurrency) {
    this.sourceCurrency = sourceCurrency;
  }

  /**
   **/
  public ReportExchangeRate exchangeRate(String exchangeRate) {
    this.exchangeRate = exchangeRate;
    return this;
  }

 
  public String getExchangeRate() {
    return exchangeRate;
  }
  public void setExchangeRate(String exchangeRate) {
    this.exchangeRate = exchangeRate;
  }

  /**
   **/
  public ReportExchangeRate unitCurrency(String unitCurrency) {
    this.unitCurrency = unitCurrency;
    return this;
  }

  
  public String getUnitCurrency() {
    return unitCurrency;
  }
  public void setUnitCurrency(String unitCurrency) {
    this.unitCurrency = unitCurrency;
  }

  /**
   **/
  public ReportExchangeRate targetCurrency(String targetCurrency) {
    this.targetCurrency = targetCurrency;
    return this;
  }

  
  public String getTargetCurrency() {
    return targetCurrency;
  }
  public void setTargetCurrency(String targetCurrency) {
    this.targetCurrency = targetCurrency;
  }

  /**
   **/
  public ReportExchangeRate quotationDate(LocalDate quotationDate) {
    this.quotationDate = quotationDate;
    return this;
  }


  public LocalDate getQuotationDate() {
    return quotationDate;
  }
  public void setQuotationDate(LocalDate quotationDate) {
    this.quotationDate = quotationDate;
  }

  /**
   **/
  public ReportExchangeRate contractIdentification(String contractIdentification) {
    this.contractIdentification = contractIdentification;
    return this;
  }

  

  public String getContractIdentification() {
    return contractIdentification;
  }
  public void setContractIdentification(String contractIdentification) {
    this.contractIdentification = contractIdentification;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReportExchangeRate reportExchangeRate = (ReportExchangeRate) o;
    return Objects.equals(sourceCurrency, reportExchangeRate.sourceCurrency) &&
        Objects.equals(exchangeRate, reportExchangeRate.exchangeRate) &&
        Objects.equals(unitCurrency, reportExchangeRate.unitCurrency) &&
        Objects.equals(targetCurrency, reportExchangeRate.targetCurrency) &&
        Objects.equals(quotationDate, reportExchangeRate.quotationDate) &&
        Objects.equals(contractIdentification, reportExchangeRate.contractIdentification);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sourceCurrency, exchangeRate, unitCurrency, targetCurrency, quotationDate, contractIdentification);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReportExchangeRate {\n");
    
    sb.append("    sourceCurrency: ").append(toIndentedString(sourceCurrency)).append("\n");
    sb.append("    exchangeRate: ").append(toIndentedString(exchangeRate)).append("\n");
    sb.append("    unitCurrency: ").append(toIndentedString(unitCurrency)).append("\n");
    sb.append("    targetCurrency: ").append(toIndentedString(targetCurrency)).append("\n");
    sb.append("    quotationDate: ").append(toIndentedString(quotationDate)).append("\n");
    sb.append("    contractIdentification: ").append(toIndentedString(contractIdentification)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
