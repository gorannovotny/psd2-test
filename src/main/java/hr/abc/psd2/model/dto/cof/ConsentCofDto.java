package hr.abc.psd2.model.dto.cof;

import hr.abc.psd2.util.GenericBassxConstants;
import hr.abc.psd2.util.InternationalizationUtil;
import hr.abc.psd2.model.dto.GenericDto;
import hr.abc.psd2.model.dto.authorization.AuthorizationDto;
import hr.abc.psd2.model.dto.authorization.ScaStatus;
import hr.abc.psd2.model.dto.error.ErrorInformationDto;
import hr.abc.psd2.model.dto.error.ErrorInformationXmlDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dKosutar on 12-06-19.
 *
 * PSD2 Confirmation of Founds (cof) Consent DTO
 *
 */
public class ConsentCofDto extends GenericDto<Integer> {
    private static final long serialVersionUID = 1L;

    private Integer sifraCon;
    private String stateCon;
    private Integer frequencyPerDayCon; //frequency per day from request
    private Integer tppUsageCounterCon; // tpp usaga counter - kod kreiranja je jednak tppFrequencyPerDayCon a nakon toga se smanjuje kod korištenja consenta
    private Integer tppFrequencyPerDayCon; //TPP frequency per day - parametarski određen limit  ukoliko u freque_con dođe veći broj
    private Boolean recurringIndicatorCon;
    private Boolean combinedServiceIndicator;
    private String xRequestIdCon;
    private String tppIdCon;
    private String psuIdCon;
    private LocalDateTime creationDateCon;
    private LocalDate toDateCon;
    private ConsentAccountCofDto consentAccountAisDto;
    private List<ConsentAccountCofDto> consentAccountCofDtoList;
    private Integer sifraKom;
    private LocalDate lastActionDate;
    private AuthorizationDto authorization;
    private String tppName;

    private Boolean mozePotpis = Boolean.FALSE;
    private Integer trenutniAutenticiraniKorisnik;
    private String greska;
    private Integer sifraSmartPotpisa;
    private String iconLink;
    private String nazivStatusa;
    private Integer brojPotrebnihPotpisa = 1; //po defaultu je 1 za Consent+

    private Boolean isValid;
    private ErrorInformationDto errorInformationDto; //Additional Error Information
    private ErrorInformationXmlDto errorInformationXmlDto;
    private boolean mozePotpisPovlacenja;

    private String tppRedirectUri;

    public ConsentCofDto() {
        super();
    }

    // getters & setters
    @Override
    public Integer getSifra() {
        return super.getSifra();
    }

//    public Integer getSifraCon() {
//        return sifraCon;
//    }
//
//    public void setSifraCon(Integer sifraCon) {
//        this.sifraCon = sifraCon;
//    }

    public String getStateCon() {
        return stateCon;
    }

    public void setStateCon(String stateCon) {
        this.stateCon = stateCon;
    }

    public boolean isMozePotpisPovlacenja() {
        return mozePotpisPovlacenja;
    }

    public void setMozePotpisPovlacenja(boolean mozePotpisPovlacenja) {
        this.mozePotpisPovlacenja = mozePotpisPovlacenja;
    }

    public Integer getFrequencyPerDayCon() {
        return frequencyPerDayCon;
    }

    public void setFrequencyPerDayCon(Integer frequencyPerDayCon) {
        this.frequencyPerDayCon = frequencyPerDayCon;
    }

    public Boolean getRecurringIndicatorCon() {
        return recurringIndicatorCon;
    }

    public void setRecurringIndicatorCon(Boolean recurringIndicatorCon) {
        this.recurringIndicatorCon = recurringIndicatorCon;
    }

    public String getxRequestIdCon() {
        return xRequestIdCon;
    }

    public void setxRequestIdCon(String xRequestIdCon) {
        this.xRequestIdCon = xRequestIdCon;
    }

    public String getTppIdCon() {
        return tppIdCon;
    }

    public void setTppIdCon(String tppIdCon) {
        this.tppIdCon = tppIdCon;
    }

    public String getPsuIdCon() {
        return psuIdCon;
    }

    public void setPsuIdCon(String psuIdCon) {
        this.psuIdCon = psuIdCon;
    }

    public Integer getTppUsageCounterCon() {
        return tppUsageCounterCon;
    }

    public void setTppUsageCounterCon(Integer tppUsageCounterCon) {
        this.tppUsageCounterCon = tppUsageCounterCon;
    }

    public LocalDateTime getCreationDateCon() {
        return creationDateCon;
    }

    public void setCreationDateCon(LocalDateTime creationDateCon) {
        this.creationDateCon = creationDateCon;
    }

    public LocalDate getToDateCon() {
        return toDateCon;
    }

    public void setToDateCon(LocalDate toDateCon) {
        this.toDateCon = toDateCon;
    }

    public ConsentAccountCofDto getConsentAccountCofDto() {
        return consentAccountAisDto;
    }

    public void setConsentAccountCofDto(ConsentAccountCofDto consentAccountAisDtoList) {
        this.consentAccountAisDto = consentAccountAisDtoList;
    }

    public Integer getSifraKom() {
        return sifraKom;
    }

    public void setSifraKom(Integer sifraKom) {
        this.sifraKom = sifraKom;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Boolean getCombinedServiceIndicator() {
        return combinedServiceIndicator;
    }

    public void setCombinedServiceIndicator(Boolean combinedServiceIndicator) {
        this.combinedServiceIndicator = combinedServiceIndicator;
    }

    public ErrorInformationDto getErrorInformationDto() {
        return errorInformationDto;
    }

    public void setErrorInformationDto(ErrorInformationDto errorInformationDto) {
        this.errorInformationDto = errorInformationDto;
    }

    public LocalDate getLastActionDate() {
        return lastActionDate;
    }

    public void setLastActionDate(LocalDate lastActionDate) {
        this.lastActionDate = lastActionDate;
    }

    public Integer getTppFrequencyPerDayCon() {
        return tppFrequencyPerDayCon;
    }

    public void setTppFrequencyPerDayCon(Integer tppFrequencyPerDayCon) {
        this.tppFrequencyPerDayCon = tppFrequencyPerDayCon;
    }

    public AuthorizationDto getAuthorization() {
        return authorization;
    }

    public void setAuthorization(AuthorizationDto authorization) {
        this.authorization = authorization;
    }

    public Boolean getMozePotpis() {
        resolveMozePotpis();
        return mozePotpis;
    }

    public List<ConsentAccountCofDto> getConsentAccountCofDtoList() {
        return consentAccountCofDtoList;
    }

    public void setConsentAccountCofDtoList(List<ConsentAccountCofDto> consentAccountCofDtoList) {
        this.consentAccountCofDtoList = consentAccountCofDtoList;
    }

    private void resolveMozePotpis() {
        mozePotpis = Boolean.TRUE;
        mozePotpis = mozePotpis && authorizationInValidStatusForSign();
        if(mozePotpis) mozePotpis = !this.getKorisnikPotpisaoNalog();
    }
    public Boolean getKorisnikPotpisaoNalog(){
        Boolean res = Boolean.FALSE;
        if(authorization != null && authorization.getScastAuth().equals(ScaStatus.FINALISED.getValue())){
            try {
                res = authorization.getPsuIdAuth() != null && Integer.valueOf(authorization.getPsuIdAuth()).compareTo(trenutniAutenticiraniKorisnik) == 0;
            } catch (NumberFormatException e) {
                res = Boolean.FALSE;
            }
        }
        return res;
    }

    private Boolean authorizationInValidStatusForSign(){
        Boolean res = Boolean.FALSE;
        if(authorization != null && (authorization.getScastAuth().equals(ScaStatus.RECEIVED.getValue()) || authorization.getScastAuth().equals(ScaStatus.SCAMETHODSELECTED.getValue())) ){
            res = Boolean.TRUE;
        }
        return res;
    }

    public Integer getTrenutniAutenticiraniKorisnik() {
        return trenutniAutenticiraniKorisnik;
    }

    public void setTrenutniAutenticiraniKorisnik(Integer trenutniAutenticiraniKorisnik) {
        this.trenutniAutenticiraniKorisnik = trenutniAutenticiraniKorisnik;
    }

    public String getGreska() {
        return greska;
    }

    public void setGreska(String greska) {
        this.greska = greska;
    }

    public Integer getSifraSmartPotpisa() {
        return sifraSmartPotpisa;
    }

    public void setSifraSmartPotpisa(Integer sifraSmartPotpisa) {
        this.sifraSmartPotpisa = sifraSmartPotpisa;
    }

    public String getIconLink() {
        if(getIsNepotpisan()){
            iconLink = "/images/blue_32x32.png";
            nazivStatusa = InternationalizationUtil.getLocalMessage("statusNalogaNepotpisan", GenericBassxConstants.Authentication.MESSAGE_BUNDLE);
        }
        if(getIsObradjen()){
            iconLink = "/images/green_32x32.png";
            nazivStatusa = InternationalizationUtil.getLocalMessage("statusNalogaProknjizen", GenericBassxConstants.Authentication.MESSAGE_BUNDLE);
        }
//        if(getIsObrisan()){
//            iconLink = "/images/gray_light_32x32.png";
//        }
//        if(getIsPovucen()){
//            iconLink = "/images/gray_32x32.png";
//        }
        return iconLink;
    }

    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
    }

    public Boolean getIsObradjen(){
        return this.authorization != null && !Arrays.asList(AuthorizationDto.SCA_STATUSI_ZA_POTPIS).contains(this.authorization.getScastAuth());
    }

    public Boolean getIsNepotpisan(){
        return this.authorization != null && Arrays.asList(AuthorizationDto.SCA_STATUSI_ZA_POTPIS).contains(this.authorization.getScastAuth());
    }

    public String getNazivStatusa() {
        return nazivStatusa;
    }

    public void setNazivStatusa(String nazivStatusa) {
        this.nazivStatusa = nazivStatusa;
    }

    public String getTppRedirectUri() {
        String res = tppRedirectUri;
        if(res != null){
            res = res.startsWith("http") ? res : "http://" + res;
        }
        return res;
    }

    public void setTppRedirectUri(String tppRedirectUri) {
        this.tppRedirectUri = tppRedirectUri;
    }

    public ErrorInformationXmlDto getErrorInformationXmlDto() {
        return errorInformationXmlDto;
    }

    public void setErrorInformationXmlDto(ErrorInformationXmlDto errorInformationXmlDto) {
        this.errorInformationXmlDto = errorInformationXmlDto;
    }

    public String getTppName() {
        return tppName;
    }

    public void setTppName(String tppName) {
        this.tppName = tppName;
    }

}
