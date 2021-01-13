package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.model.dto.GenericDto;

import java.io.Serializable;

public class IbanFormatDto extends GenericDto<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String countryCode;
    private Integer ibanLength;
    private String structure;
    private String checkDigits;
    private Integer bankFirstDigitPosition;
    private Integer bankLastDigitPosition;
    private Integer branchFirstDigitPosition;
    private Integer branchLastDigitPosition;
    private String sepaMember;
    // help fields
    private String code3Country;
    private String localNameCountry;
    private String englishNameCountry;

    public static final int IBAN_COUNTRY_CODE_LENGTH = 2;
    public static final int IBAN_MIN_LENGTH = 6;
    public static final int IBAN_MAX_LENGTH = 34;
    public static final String MEMBER_OF_SEPA = "Y";
    public static final String NOT_MEMBER_OF_SEPA = "N";
    public static final int MIN_STRUCTURE_LENGTH = 8;
    public static final int MIN_BANK_FIRST_DIGIT_POSITION = 5;
    public static final int MIN_BRANCH_FIRST_DIGIT_POSITION = 6;
    public static final int NO_DIGIT_POSITION = -1;
    public static final Integer TIP_NALOGA_IBAN_FORMAT_CREATE = 10000;
    public static final Integer TIP_NALOGA_IBAN_FORMAT_EDIT = 10001;
    public static final Integer TIP_NALOGA_IBAN_FORMAT_DELETE = 10002;

    // constructors
    public IbanFormatDto() {  super(); }

    public IbanFormatDto(boolean isFirstCall) { super(isFirstCall); }

    public IbanFormatDto(String countryCode, Integer ibanLength, String structure, String checkDigits, Integer bankFirstDigitPosition, Integer bankLastDigitPosition, Integer branchFirstDigitPosition, Integer branchLastDigitPosition, String sepaMember) {
        this.countryCode = countryCode;
        this.ibanLength = ibanLength;
        this.structure = structure;
        this.checkDigits = checkDigits;
        this.bankFirstDigitPosition = bankFirstDigitPosition;
        this.bankLastDigitPosition = bankLastDigitPosition;
        this.branchFirstDigitPosition = branchFirstDigitPosition;
        this.branchLastDigitPosition = branchLastDigitPosition;
        this.sepaMember = sepaMember;
    }

    // getters & setters
    @IFilter(entityField="ibanFormat.ctry2_ibf")
    public String getCountryCode() { return countryCode; }

    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }

    @IFilter(entityField="ibanFormat.lngth_ibf")
    public Integer getIbanLength() { return ibanLength; }

    public void setIbanLength(Integer ibanLength) { this.ibanLength = ibanLength; }

    @IFilter(entityField="ibanFormat.formt_ibf")
    public String getStructure() { return structure; }

    public void setStructure(String structure) { this.structure = structure; }

    @IFilter(entityField="ibanFormat.chkdg_ibf")
    public String getCheckDigits() { return checkDigits; }

    public void setCheckDigits(String checkDigits) { this.checkDigits = checkDigits; }

    @IFilter(entityField="ibanFormat.banfd_ibf")
    public Integer getBankFirstDigitPosition() { return bankFirstDigitPosition; }

    public void setBankFirstDigitPosition(Integer bankFirstDigitPosition) { this.bankFirstDigitPosition = bankFirstDigitPosition; }

    @IFilter(entityField="ibanFormat.banld_ibf")
    public Integer getBankLastDigitPosition() { return bankLastDigitPosition; }

    public void setBankLastDigitPosition(Integer bankLastDigitPosition) { this.bankLastDigitPosition = bankLastDigitPosition; }

    @IFilter(entityField="ibanFormat.brafd_ibf")
    public Integer getBranchFirstDigitPosition() { return branchFirstDigitPosition; }

    public void setBranchFirstDigitPosition(Integer branchFirstDigitPosition) { this.branchFirstDigitPosition = branchFirstDigitPosition; }

    @IFilter(entityField="ibanFormat.brald_ibf")
    public Integer getBranchLastDigitPosition() { return branchLastDigitPosition; }

    public void setBranchLastDigitPosition(Integer branchLastDigitPosition) { this.branchLastDigitPosition = branchLastDigitPosition; }

    @IFilter(entityField="ibanFormat.sepac_ibf")
    public String getSepaMember() { return sepaMember; }

    public void setSepaMember(String sepaMember) { this.sepaMember = sepaMember; }

    public boolean isMemberOfSepa() { return (this.sepaMember != null && this.getSepaMember().equals("Y")) ? true : false; }

    public int getNationalSortingCodeFirstPosition() { return this.bankFirstDigitPosition != null ? this.bankFirstDigitPosition.intValue() - 1 : NO_DIGIT_POSITION; }

    public int getNationalSortingCodeLastPosition() {
        if (this.bankLastDigitPosition != null) {
            return this.branchLastDigitPosition != null ? this.branchLastDigitPosition.intValue() : this.bankLastDigitPosition.intValue();
        } else {
            return NO_DIGIT_POSITION;
        }
    }

    public String getCode3Country() { return code3Country; }

    public void setCode3Country(String code3Country) { this.code3Country = code3Country; }

    public String getLocalNameCountry() { return localNameCountry; }

    public void setLocalNameCountry(String localNameCountry) { this.localNameCountry = localNameCountry; }

    public String getEnglishNameCountry() { return englishNameCountry; }

    public void setEnglishNameCountry(String englishNameCountry) { this.englishNameCountry = englishNameCountry; }
}
