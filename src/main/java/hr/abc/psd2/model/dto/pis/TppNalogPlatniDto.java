package hr.abc.psd2.model.dto.pis;

import hr.abc.psd2.util.*;
import hr.abc.psd2.model.dto.GenericDto;
import hr.abc.psd2.model.dto.authorization.AuthorizationDto;
import hr.abc.psd2.model.dto.authorization.ScaStatus;
import hr.abc.psd2.model.dto.core.NalogSepaDto;
import hr.abc.psd2.model.dto.error.ErrorInformationDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TppNalogPlatniDto implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String TIP_GRUPIRANJA_BASKET = "B";
    public static final String TIP_GRUPIRANJA_DATOTEKA = "D";

    // fields
    private Integer sifraTppNaloga;
    private BigDecimal iznos;
    private String sifraValute;
    private String oznakaValute;
    private String sifraValutePokrica;
    private String ibanZaduzenja;
    private String nazivDebtor;
    private String adresaDebtor;
    private String gradDebtor;
    private String drzavaDebtor;
    private String idDebtor;
    private String ultimateDebtorNaziv;
    private String ultimateDebtorId;
    private String ibanOdobrenja;
    private String nazivCreditor;
    private String adresaCreditor;
    private String gradCreditor;
    private String drzavaCreditor;
    private String idCreditor;
    private String ultimateCreditorNaziv;
    private String ultimateCreditorId;
    private String modelZaduzenja;
    private String pozivNaBrojZaduzenja;
    private String modelOdobrenja;
    private String pozivNaBrojOdobrenja;
    private String smjer;
    private String referenca;
    private String greska;
    private Integer sifraKnjiznogNaloga;
    private String swiftNalogodavateljaKorisnika;
    private String swiftNalogodavateljaKorisnikaNaziv;
    private String trosakInoBanke;
    private String sifraNamjene;
    private String kategorijaNamjene;
    private String sifraKategorijaNamjene;
    private String sifraOpisaPlacanja;
    private Date datumValute;
    private Date datumKnjizenja;
    private String status; //status knjižnog naloga, odnosno naloga
    private String statusTpp; //status tpp naloga
    private String statusPsd2; //PSD2 status - informacija koja ide prema TPP-ovima
    private String svrha;
    private String nacinIzvrsenja;
    private Integer sifraTipaNaloga;
    private Integer sifraPrethodnogNaloga;
    private Integer sifraNalogaStorna;
    private Integer sifraPlatnogNaloga;
    private String datumValuteTxt;
    private String datumKnjizenjaTxt;
    private BigDecimal iznosNaknade;
    private String oibMobileUser;
    private Integer hitnost; // 1 - hitno, 0 - default
    private String typeOfTransaction;
    private LocalDateTime vrijemeUpisaNaloga;
    private Integer sifraDatoteke;
    private Integer sifraKlaseNaloga;
    private Integer sifraOznakePosla;
    private String greskaPlatnogNaloga;
    private String painPaymentInfoId;
    private String painPaymentInformationId;
    private String painPaymentMethod;
    private Integer sifraBasketa;
    private Boolean isInBasket = Boolean.FALSE;

    //psd2 fields
    private String tppId;
    private String xRequestId;
    private String contentType;
    private String paymentProduct;
    private String paymentService;
    private String tppRedirectUri;
    private String psuIpAddress;
    private String tppRedirectUriCancelation;
    private String paymentRequestId;
    private TransactionStatus transactionStatus;

    //USB token fields
    private Date smartLockPeriod;
    private Integer sifraSmartPotpisa = null;

    //Batch booking
    private Integer sifraZbirnogBatchBookingNaloga;
    private Boolean batchBooking = Boolean.FALSE;
    private List<TppNalogPlatniDto> batchBookingNalozi = null;
    private Boolean detaljiBatcBookinga = Boolean.FALSE;
    private Date initDatumValute;

    //Pomocna polja
    private String iconLink;
    private String nazivStatusa;
    private String nazivStatusaCaps;
    private List<String> infoObrade;
    private List<String> warningMessages;
    private Boolean mozePotpis = Boolean.FALSE;
    private Boolean mozeUnos = Boolean.FALSE;
    private Boolean mozeBrisanjePovlacenje = Boolean.FALSE;
    private Boolean mozePromjena = Boolean.FALSE;
    private String nalogPotpisaliInfo;
    private String oznakaValuteNaknade = "HRK";
    private String checkable;
    private String checked = "false";
    private boolean hitno = false;
    private String hitnoOpis = null;
    private String bankaNalogodavateljaKorisnika;
    private Integer sifraPartijeZaduzenja;
    private boolean changed;
    private String changedFields;
    private NalogSepaDto nalogSepa;
    private String iznosStr;
    private String iznosNaknadeStr;
    private String nazivDrzaveCreditora;
    private Integer brojPotrebnihPotpisa;

    //temp
    private List<AuthorizationDto> authorizations;
    private AuthorizationDto authorization;
    private Integer trenutniAutenticiraniKorisnik;

    private Boolean isValid; //ukoiko kod validacije naidemo na grešku --> false
    private ErrorInformationDto errorInformationDto; //Additional Error Information

    //kupoprodaja specific fields
    private String valutaZaduzenja, valutaOdobrenja, oznakaValuteZaduzenja, oznakaValuteOdobrenja, tipTecajaZaduzenja, tipTecajaOdobrenja, tipTecaja, vrstaNaloga;
    private BigDecimal iznosZaduzenja, iznosOdobrenja, tecajZaduzenja, tecajOdobrenja, povlasteniTecajZaduzenja, povlasteniTecajOdobrenja, regularniTecajZaduzenja, regularniTecajOdobrenja, maxIznosZaduzenja, maxIznosOdobrenja;
    private Integer sifraPartijeOdobrenja, sifraKomitenta, sifraPovlastenogTecajaZaduzenja, sifraPovlastenogTecajaOdobrenja, sifraPovlastenogTecaja;


    private Boolean pravnaOsoba;

    //getters and setters

    public Integer getSifraTppNaloga() {
        return sifraTppNaloga;
    }

    public void setSifraTppNaloga(Integer sifraTppNaloga) {
        this.sifraTppNaloga = sifraTppNaloga;
    }

    public BigDecimal getIznos() {
        return iznos;
    }

    public void setIznos(BigDecimal iznos) {
        this.iznos = iznos;
    }

    public String getSifraValute() {
        return sifraValute;
    }

    public void setSifraValute(String sifraValute) {
        this.sifraValute = sifraValute;
    }

    public String getOznakaValute() {
        return oznakaValute;
    }

    public void setOznakaValute(String oznakaValute) {
        this.oznakaValute = oznakaValute;
    }

    public String getSifraValutePokrica() {
        return sifraValutePokrica;
    }

    public void setSifraValutePokrica(String sifraValutePokrica) {
        this.sifraValutePokrica = sifraValutePokrica;
    }

    public String getIbanZaduzenja() {
        return ibanZaduzenja;
    }

    public void setIbanZaduzenja(String ibanZaduzenja) {
        this.ibanZaduzenja = ibanZaduzenja;
    }

    public String getNazivDebtor() {
        return nazivDebtor;
    }

    public void setNazivDebtor(String nazivDebtor) {
        this.nazivDebtor = nazivDebtor;
    }

    public String getAdresaDebtor() {
        return adresaDebtor;
    }

    public void setAdresaDebtor(String adresaDebtor) {
        this.adresaDebtor = adresaDebtor;
    }

    public String getGradDebtor() {
        return gradDebtor;
    }

    public void setGradDebtor(String gradDebtor) {
        this.gradDebtor = gradDebtor;
    }

    public String getDrzavaDebtor() {
        return drzavaDebtor;
    }

    public void setDrzavaDebtor(String drzavaDebtor) {
        this.drzavaDebtor = drzavaDebtor;
    }

    public String getIdDebtor() {
        return idDebtor;
    }

    public void setIdDebtor(String idDebtor) {
        this.idDebtor = idDebtor;
    }

    public String getUltimateDebtorNaziv() {
        return ultimateDebtorNaziv;
    }

    public void setUltimateDebtorNaziv(String ultimateDebtorNaziv) {
        this.ultimateDebtorNaziv = ultimateDebtorNaziv;
    }

    public String getUltimateDebtorId() {
        return ultimateDebtorId;
    }

    public void setUltimateDebtorId(String ultimateDebtorId) {
        this.ultimateDebtorId = ultimateDebtorId;
    }

    public String getIbanOdobrenja() {
        return ibanOdobrenja;
    }

    public void setIbanOdobrenja(String ibanOdobrenja) {
        this.ibanOdobrenja = ibanOdobrenja;
    }

    public String getNazivCreditor() {
        return nazivCreditor;
    }

    public void setNazivCreditor(String nazivCreditor) {
        this.nazivCreditor = nazivCreditor;
    }

    public String getAdresaCreditor() {
        return adresaCreditor;
    }

    public void setAdresaCreditor(String adresaCreditor) {
        this.adresaCreditor = adresaCreditor;
    }

    public String getGradCreditor() {
        return gradCreditor;
    }

    public void setGradCreditor(String gradCreditor) {
        this.gradCreditor = gradCreditor;
    }

    public String getDrzavaCreditor() {
        return drzavaCreditor;
    }

    public void setDrzavaCreditor(String drzavaCreditor) {
        this.drzavaCreditor = drzavaCreditor;
    }

    public String getIdCreditor() {
        return idCreditor;
    }

    public void setIdCreditor(String idCreditor) {
        this.idCreditor = idCreditor;
    }

    public String getUltimateCreditorNaziv() {
        return ultimateCreditorNaziv;
    }

    public void setUltimateCreditorNaziv(String ultimateCreditorNaziv) {
        this.ultimateCreditorNaziv = ultimateCreditorNaziv;
    }

    public String getUltimateCreditorId() {
        return ultimateCreditorId;
    }

    public void setUltimateCreditorId(String ultimateCreditorId) {
        this.ultimateCreditorId = ultimateCreditorId;
    }

    public String getModelZaduzenja() {
        return modelZaduzenja;
    }

    public void setModelZaduzenja(String modelZaduzenja) {
        this.modelZaduzenja = modelZaduzenja;
    }

    public String getPozivNaBrojZaduzenja() {
        return pozivNaBrojZaduzenja;
    }

    public void setPozivNaBrojZaduzenja(String pozivNaBrojZaduzenja) {
        this.pozivNaBrojZaduzenja = pozivNaBrojZaduzenja;
    }

    public String getModelOdobrenja() {
        return modelOdobrenja;
    }

    public void setModelOdobrenja(String modelOdobrenja) {
        this.modelOdobrenja = modelOdobrenja;
    }

    public String getPozivNaBrojOdobrenja() {
        return pozivNaBrojOdobrenja;
    }

    public void setPozivNaBrojOdobrenja(String pozivNaBrojOdobrenja) {
        this.pozivNaBrojOdobrenja = pozivNaBrojOdobrenja;
    }

    public String getSmjer() {
        return smjer;
    }

    public void setSmjer(String smjer) {
        this.smjer = smjer;
    }

    public String getReferenca() {
        return referenca;
    }

    public void setReferenca(String referenca) {
        this.referenca = referenca;
    }

    public String getGreska() {
        return greska;
    }

    public void setGreska(String greska) {
        this.greska = greska;
    }

    public Integer getSifraKnjiznogNaloga() {
        return sifraKnjiznogNaloga;
    }

    public void setSifraKnjiznogNaloga(Integer sifraKnjiznogNaloga) {
        this.sifraKnjiznogNaloga = sifraKnjiznogNaloga;
    }

    public String getSwiftNalogodavateljaKorisnika() {
        return swiftNalogodavateljaKorisnika;
    }

    public void setSwiftNalogodavateljaKorisnika(String swiftNalogodavateljaKorisnika) {
        this.swiftNalogodavateljaKorisnika = swiftNalogodavateljaKorisnika;
    }

    public String getSwiftNalogodavateljaKorisnikaNaziv() {
        return swiftNalogodavateljaKorisnikaNaziv;
    }

    public void setSwiftNalogodavateljaKorisnikaNaziv(String swiftNalogodavateljaKorisnikaNaziv) {
        this.swiftNalogodavateljaKorisnikaNaziv = swiftNalogodavateljaKorisnikaNaziv;
    }

    public String getTrosakInoBanke() {
        return trosakInoBanke;
    }

    public void setTrosakInoBanke(String trosakInoBanke) {
        this.trosakInoBanke = trosakInoBanke;
    }

    public String getSifraNamjene() {
        return sifraNamjene;
    }

    public void setSifraNamjene(String sifraNamjene) {
        this.sifraNamjene = sifraNamjene;
    }

    public String getKategorijaNamjene() {
        return kategorijaNamjene;
    }

    public void setKategorijaNamjene(String kategorijaNamjene) {
        this.kategorijaNamjene = kategorijaNamjene;
    }

    public String getSifraKategorijaNamjene() {
        return sifraKategorijaNamjene;
    }

    public void setSifraKategorijaNamjene(String sifraKategorijaNamjene) {
        this.sifraKategorijaNamjene = sifraKategorijaNamjene;
    }

    public String getSifraOpisaPlacanja() {
        return sifraOpisaPlacanja;
    }

    public void setSifraOpisaPlacanja(String sifraOpisaPlacanja) {
        this.sifraOpisaPlacanja = sifraOpisaPlacanja;
    }

    public Date getDatumValute() {
        return datumValute;
    }

    public void setDatumValute(Date datumValute) {
        this.datumValute = datumValute;
    }

    public Date getDatumKnjizenja() {
        return datumKnjizenja;
    }

    public void setDatumKnjizenja(Date datumKnjizenja) {
        this.datumKnjizenja = datumKnjizenja;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSvrha() {
        return svrha;
    }

    public void setSvrha(String svrha) {
        this.svrha = svrha;
    }

    public String getNacinIzvrsenja() {
        return nacinIzvrsenja;
    }

    public void setNacinIzvrsenja(String nacinIzvrsenja) {
        this.nacinIzvrsenja = nacinIzvrsenja;
    }

    public Integer getSifraTipaNaloga() {
        return sifraTipaNaloga;
    }

    public void setSifraTipaNaloga(Integer sifraTipaNaloga) {
        this.sifraTipaNaloga = sifraTipaNaloga;
    }

    public Integer getSifraPrethodnogNaloga() {
        return sifraPrethodnogNaloga;
    }

    public void setSifraPrethodnogNaloga(Integer sifraPrethodnogNaloga) {
        this.sifraPrethodnogNaloga = sifraPrethodnogNaloga;
    }

    public Integer getSifraNalogaStorna() {
        return sifraNalogaStorna;
    }

    public void setSifraNalogaStorna(Integer sifraNalogaStorna) {
        this.sifraNalogaStorna = sifraNalogaStorna;
    }

    public Integer getSifraPlatnogNaloga() {
        return sifraPlatnogNaloga;
    }

    public void setSifraPlatnogNaloga(Integer sifraPlatnogNaloga) {
        this.sifraPlatnogNaloga = sifraPlatnogNaloga;
    }

    public String getDatumValuteTxt() {
        return datumValuteTxt;
    }

    public void setDatumValuteTxt(String datumValuteTxt) {
        this.datumValuteTxt = datumValuteTxt;
    }

    public String getDatumKnjizenjaTxt() {
        return datumKnjizenjaTxt;
    }

    public void setDatumKnjizenjaTxt(String datumKnjizenjaTxt) {
        this.datumKnjizenjaTxt = datumKnjizenjaTxt;
    }

    public BigDecimal getIznosNaknade() {
        return iznosNaknade != null ? iznosNaknade : BigDecimal.ZERO;
    }

    public void setIznosNaknade(BigDecimal iznosNaknade) {
        this.iznosNaknade = iznosNaknade;
    }

    public String getOibMobileUser() {
        return oibMobileUser;
    }

    public void setOibMobileUser(String oibMobileUser) {
        this.oibMobileUser = oibMobileUser;
    }

    public Integer getHitnost() {
        return isInst() ? GenericBassxConstants.PlatniPromet.PLATNI_NALOG_HITNOST_INSTANT : (isHitno() ? 1 : 0);
    }

    public void setHitnost(Integer hitnost) {
        this.hitnost = hitnost;
    }

    public String getTypeOfTransaction() {
        return typeOfTransaction;
    }

    public void setTypeOfTransaction(String typeOfTransaction) {
        this.typeOfTransaction = typeOfTransaction;
    }

    public String getTppId() {
        return tppId;
    }

    public void setTppId(String tppId) {
        this.tppId = tppId;
    }

    public String getxRequestId() {
        return xRequestId;
    }

    public void setxRequestId(String xRequestId) {
        this.xRequestId = xRequestId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getPaymentProduct() {
        return paymentProduct;
    }

    public void setPaymentProduct(String paymentProduct) {
        this.paymentProduct = paymentProduct;
    }

    public LocalDateTime getVrijemeUpisaNaloga() {
        return vrijemeUpisaNaloga;
    }

    public void setVrijemeUpisaNaloga(LocalDateTime vrijemeUpisaNaloga) {
        this.vrijemeUpisaNaloga = vrijemeUpisaNaloga;
    }

    public String getPaymentService() {
        return paymentService;
    }

    public void setPaymentService(String paymentService) {
        this.paymentService = paymentService;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public ErrorInformationDto getErrorInformationDto() {
        return errorInformationDto;
    }

    public void setErrorInformationDto(ErrorInformationDto errorInformationDto) {
        this.errorInformationDto = errorInformationDto;
    }

    public Date getSmartLockPeriod() {
        return smartLockPeriod;
    }

    public void setSmartLockPeriod(Date smartLockPeriod) {
        this.smartLockPeriod = smartLockPeriod;
    }

    public Integer getSifraSmartPotpisa() {
        return sifraSmartPotpisa;
    }

    public void setSifraSmartPotpisa(Integer sifraSmartPotpisa) {
        this.sifraSmartPotpisa = sifraSmartPotpisa;
    }

    public Integer getSifraDatoteke() {
        return sifraDatoteke;
    }

    public void setSifraDatoteke(Integer sifraDatoteke) {
        this.sifraDatoteke = sifraDatoteke;
    }

    public String getIconLink() {
        if (getIsOdbijen()) {
            iconLink = "/images/red_32x32.png";
        }
        if (getIsUObradi()) {
            iconLink = "/images/yellow_32x32.png";
        }
        if (getIsNepotpisan()) {
            iconLink = "/images/blue_32x32.png";
        }
        if (getIsNepotpisanCekaSeDrugiPotpis()) {
            iconLink = "/images/pink_32x32.png";
        }
        if (getIsObradjen()) {
            iconLink = "/images/green_32x32.png";
        }
        if (getIsObrisan()) {
            iconLink = "/images/gray_light_32x32.png";
        }
        if (getIsPovucen()) {
            iconLink = "/images/gray_32x32.png";
        }
        return iconLink;
    }

    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
    }

    public String getNazivStatusa() {
        return nazivStatusa;
    }

    public void setNazivStatusa(String nazivStatusa) {
        this.nazivStatusa = nazivStatusa;
    }

    public List<String> getInfoObrade() {
        return infoObrade;
    }

    public void setInfoObrade(List<String> infoObrade) {
        this.infoObrade = infoObrade;
    }

    public List<String> getWarningMessages() {
        return warningMessages;
    }

    public void setWarningMessages(List<String> warningMessages) {
        this.warningMessages = warningMessages;
    }

    public Boolean getMozePotpis() {
        resolveMozePotpis();
        return mozePotpis;
    }

    public void setMozePotpis(Boolean mozePotpis) {
        this.mozePotpis = mozePotpis;
    }

    public Boolean getMozeUnos() {
        return mozeUnos;
    }

    public void setMozeUnos(Boolean mozeUnos) {
        this.mozeUnos = mozeUnos;
    }

    public Boolean getMozeBrisanjePovlacenje() {
        mozeBrisanjePovlacenje = mozeStartAutorizacijePovlacenje();
        return mozeBrisanjePovlacenje;
    }

    public void setMozeBrisanjePovlacenje(Boolean mozeBrisanjePovlacenje) {
        this.mozeBrisanjePovlacenje = mozeBrisanjePovlacenje;
    }

    public Boolean getMozePromjena() {
        return mozePromjena;
    }

    public void setMozePromjena(Boolean mozePromjena) {
        this.mozePromjena = mozePromjena;
    }

    public Integer getTrenutniAutenticiraniKorisnik() {
        return trenutniAutenticiraniKorisnik;
    }

    public void setTrenutniAutenticiraniKorisnik(Integer trenutniAutenticiraniKorisnik) {
        this.trenutniAutenticiraniKorisnik = trenutniAutenticiraniKorisnik;
    }

    public Boolean getKorisnikPotpisaoNalog() {
        Boolean res = Boolean.FALSE;
        if (authorizations != null) {
            for (AuthorizationDto akc : authorizations) {
                if (!akc.getScastAuth().equals(ScaStatus.FINALISED.getValue())) continue;
                try {
                    res = akc.getPsuIdAuth() != null && Integer.valueOf(akc.getPsuIdAuth()).compareTo(trenutniAutenticiraniKorisnik) == 0;
                } catch (NumberFormatException e) {
                    res = Boolean.FALSE;
                }
                if (res) break;
            }
        }
        return res;
    }

    public Boolean getIsObradjen() {
        return this.status != null && this.status.equals("7");
    }

    public Boolean getIsUObradi() {
        return this.status != null && !this.status.equals(GenericBassxConstants.Core.NALOG_STATUNAL_PROKNJIZEN) && !getIsPovucen();
    }

    public Boolean getIsOdbijen() {
        return this.status != null && getIsPovucen() && this.greskaPlatnogNaloga != null;
    }

    public Boolean getIsPovucen() {
        return this.status != null && (this.status.equals(GenericBassxConstants.Core.NALOG_STATUNAL_POVUCEN) || this.status.equals(GenericBassxConstants.Core.NALOG_STATUNAL_POVUCEN_B2));
    }

    public Boolean getIsObrisan() {
        return Boolean.FALSE;
//        return this.ibStatus != null && this.ibStatus.equals(IBNalog.STATUS_IB_OBRISAN);
    }

    public Boolean getIsNepotpisan() {
        return this.sifraKnjiznogNaloga == null;
    }

    public Boolean getIsNepotpisanCekaSeDrugiPotpis() {
        return this.sifraKnjiznogNaloga == null && resolveBrojPotpisa() > 0;
    }

    private void resolveMozePotpis() {
        mozePotpis = Boolean.TRUE;
        if (this.sifraKnjiznogNaloga != null) mozePotpis = Boolean.FALSE;
        //Provjerimo broj potpisa
//        if(mozePotpis) mozePotpis = resolveBrojPotpisa().compareTo(brojPotrebnihPotpisa) < 0;
        if (mozePotpis) mozePotpis = !this.getKorisnikPotpisaoNalog();
//        if(mozePotpis) mozePotpis = AuthenticationUtils.mozeUslugaByIban(this.ibanZaduzenja, this.ovlastenje, IBUsluga.USLUGA_POTPIS_NALOGA);
    }

    public Boolean isNajava() {
        Boolean najava = Boolean.FALSE;
        if (StringUtils.isNotBlank(this.status) && this.datumKnjizenja == null) {
            if (this.status.equals(GenericBassxConstants.Core.NALOG_STATUNAL_UPISAN) || this.status.equals(GenericBassxConstants.Core.NALOG_STATUNAL_VERIFICIRAN)) {
                najava = Boolean.TRUE;
            }
        }
        return najava;
    }

    public Boolean isNajavaPrijePotpisa() {
        Boolean najava = Boolean.FALSE;
        if (this.sifraKnjiznogNaloga == null && this.datumValute != null && DateUtil.compareDatesOnly(this.datumValute, new Date()) > 0) {
            najava = Boolean.TRUE;
        }
        return najava;
    }

    /**
     * Provjera da li možemo pokrenuti autorizacijski process povlačenja naloga
     * smije se povuči nalog koji je najava (nema današnji datum valute) i koji je potpisan (ako ima knjižni nalog)
     *
     * @return
     */
    public Boolean mozeStartAutorizacijePovlacenje() {
        Boolean result = Boolean.FALSE;
        result = isNajava();
        return result;
    }

    public Integer getSifraZbirnogBatchBookingNaloga() {
        return sifraZbirnogBatchBookingNaloga;
    }

    public void setSifraZbirnogBatchBookingNaloga(Integer sifraZbirnogBatchBookingNaloga) {
        this.sifraZbirnogBatchBookingNaloga = sifraZbirnogBatchBookingNaloga;
    }

    public Date getInitDatumValute() {
        return initDatumValute;
    }

    public void setInitDatumValute(Date initDatumValute) {
        this.initDatumValute = initDatumValute;
    }

    public Boolean getBatchBooking() {
        return batchBooking;
    }

    public void setBatchBooking(Boolean batchBooking) {
        this.batchBooking = batchBooking;
    }

    public String getNalogPotpisaliInfo() {
        return nalogPotpisaliInfo;
    }

    public void setNalogPotpisaliInfo(String nalogPotpisaliInfo) {
        this.nalogPotpisaliInfo = nalogPotpisaliInfo;
    }

    public String getOznakaValuteNaknade() {
        return oznakaValuteNaknade;
    }

    public void setOznakaValuteNaknade(String oznakaValuteNaknade) {
        this.oznakaValuteNaknade = oznakaValuteNaknade;
    }

    public String getNazivStatusaCaps() {
        return StringUtils.isNotBlank(nazivStatusa) ? nazivStatusa.toUpperCase() : "";
    }

    public void setNazivStatusaCaps(String nazivStatusaCaps) {
        this.nazivStatusaCaps = nazivStatusaCaps;
    }

    public String getPsuIpAddress() {
        return psuIpAddress;
    }

    public void setPsuIpAddress(String psuIpAddress) {
        this.psuIpAddress = psuIpAddress;
    }

    public String getTppRedirectUri() {
        return tppRedirectUri;
    }

    public void setTppRedirectUri(String tppRedirectUri) {
        this.tppRedirectUri = tppRedirectUri;
    }

    public String getStatusTpp() {
        return statusTpp;
    }

    public void setStatusTpp(String statusTpp) {
        this.statusTpp = statusTpp;
    }

    public String getStatusPsd2() {
        return statusPsd2;
    }

    public void setStatusPsd2(String statusPsd2) {
        this.statusPsd2 = statusPsd2;
    }

    public String getCheckable() {
        return this.mozePotpis ? "true" : "false";
    }

    public void setCheckable(String checkable) {
        this.checkable = checkable;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public boolean isHitno() {
        boolean res = this.paymentProduct != null && PaymentProduct.getListPaymentProductHitno().contains(this.paymentProduct);
        res = res && !this.nacinIzvrsenja.equals(Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_INTERNI_NALOG) && !this.nacinIzvrsenja.equals(Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_DEVIZNI_INTERNI_NALOG);
        return res;
    }

    public boolean isInst() {
        boolean res = this.paymentProduct != null && PaymentProduct.getListPaymentProductInst().contains(this.paymentProduct);
        return res;
    }

    public void setHitno(boolean hitno) {
        this.hitno = hitno;
    }

    public Integer getSifraKlaseNaloga() {
        return sifraKlaseNaloga;
    }

    public void setSifraKlaseNaloga(Integer sifraKlaseNaloga) {
        this.sifraKlaseNaloga = sifraKlaseNaloga;
    }

    public Integer getSifraOznakePosla() {
        return sifraOznakePosla;
    }

    public void setSifraOznakePosla(Integer sifraOznakePosla) {
        this.sifraOznakePosla = sifraOznakePosla;
    }

    public Integer getSifraPartijeZaduzenja() {
        return sifraPartijeZaduzenja;
    }

    public void setSifraPartijeZaduzenja(Integer sifraPartijeZaduzenja) {
        this.sifraPartijeZaduzenja = sifraPartijeZaduzenja;
    }

    public NalogSepaDto getNalogSepa() {
        return nalogSepa;
    }

    public void setNalogSepa(NalogSepaDto nalogSepa) {
        this.nalogSepa = nalogSepa;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public String getChangedFields() {
        return changedFields;
    }

    public void setChangedFields(String changedFields) {
        this.changedFields = changedFields;
    }

    public List<AuthorizationDto> getAuthorizations() {
        return authorizations;
    }

    public void setAuthorizations(List<AuthorizationDto> authorizations) {
        this.authorizations = authorizations;
    }

    public void addAuthorization(AuthorizationDto auth) {
        if (authorizations == null) authorizations = new ArrayList<>();
        if (auth != null) authorizations.add(auth);
    }

    public Boolean getDovoljanBrojPotpisa(Integer brojPotrebnihPotpisa, Integer authType) {
        int brojPotpisa = 0;
        Boolean res = Boolean.FALSE;
        if (this.authorizations != null) {
            for (AuthorizationDto auth : this.authorizations) {
                if (auth == null) continue;
                if (auth.getAutTypeAuth().compareTo(authType) != 0) continue;
                if (!auth.getScastAuth().equals(ScaStatus.FINALISED.getValue())) continue;
                brojPotpisa++;
            }
            res = brojPotpisa >= brojPotrebnihPotpisa;
        }
        return res;
    }

    public void addInfoObrade(String info) {
        if (infoObrade == null) infoObrade = new ArrayList<>();
        infoObrade.add(info);
    }

    public AuthorizationDto getAuthorization() {
        return authorization;
    }

    public void setAuthorization(AuthorizationDto authorization) {
        this.authorization = authorization;
    }

    public void resolveStatusAndInfo(boolean ukljuciSifruStatusa) {
        if (getIsOdbijen()) {
            iconLink = "/images/red_32x32.png";
            nazivStatusa = InternationalizationUtil.getLocalMessage("statusNalogaOdbijen", GenericBassxConstants.Authentication.MESSAGE_BUNDLE) + (ukljuciSifruStatusa && getStatus() != null ? " (" + getStatus() + ") " : "");
        }
        if (getIsUObradi()) {
            iconLink = "/images/yellow_32x32.png";
            nazivStatusa = InternationalizationUtil.getLocalMessage("statusNalogaObrada", GenericBassxConstants.Authentication.MESSAGE_BUNDLE) + (ukljuciSifruStatusa && getStatus() != null ? " (" + getStatus() + ") " : "");
        }
        if (getIsNepotpisan()) {
            iconLink = "/images/blue_32x32.png";
            nazivStatusa = InternationalizationUtil.getLocalMessage("statusNalogaNepotpisan", GenericBassxConstants.Authentication.MESSAGE_BUNDLE) + (ukljuciSifruStatusa && getStatus() != null ? " (" + getStatus() + ") " : "");
        }
        if (getIsNepotpisanCekaSeDrugiPotpis()) {
            iconLink = "/images/pink_32x32.png";
            nazivStatusa = InternationalizationUtil.getLocalMessage("statusNalogaNepotpisanCekaSeDrugiPotpis", GenericBassxConstants.Authentication.MESSAGE_BUNDLE) + (ukljuciSifruStatusa && getStatus() != null ? " (" + getStatus() + ") " : "");
        }
        if (getIsObradjen()) {
            iconLink = "/images/green_32x32.png";
            nazivStatusa = InternationalizationUtil.getLocalMessage("statusNalogaProknjizen", GenericBassxConstants.Authentication.MESSAGE_BUNDLE) + (ukljuciSifruStatusa && getStatus() != null ? " (" + getStatus() + ") " : "");
        }
        if (getIsObrisan()) {
            iconLink = "/images/gray_light_32x32.png";
            nazivStatusa = InternationalizationUtil.getLocalMessage("statusNalogaObrisan", GenericBassxConstants.Authentication.MESSAGE_BUNDLE) + (ukljuciSifruStatusa && getStatus() != null ? " (" + getStatus() + ") " : "");
        }
        if (getIsPovucen()) {
            iconLink = "/images/gray_32x32.png";
            nazivStatusa = InternationalizationUtil.getLocalMessage("statusNalogaPovucen", GenericBassxConstants.Authentication.MESSAGE_BUNDLE) + (ukljuciSifruStatusa && getStatus() != null ? " (" + getStatus() + ") " : "");
        }
    }

    public Integer resolveBrojPotpisa() {
        Integer res = 0;
        if (authorizations != null) {
            for (AuthorizationDto auth : authorizations) {
                if (!auth.getScastAuth().equals(ScaStatus.FINALISED.getValue())) continue;
                res++;
            }
        }
        return res;
    }

    public String getIznosStr() {
        return this.iznos != null ? StringFunctions.bigDecimalToStringCurrency(this.iznos) : "";
    }

    public void setIznosStr(String iznosStr) {
        this.iznosStr = iznosStr;
    }

    public String getIznosNaknadeStr() {
        return iznosNaknade != null ? StringFunctions.bigDecimalToStringCurrency(iznosNaknade) + " HRK" : "";
    }

    public void setIznosNaknadeStr(String iznosNaknadeStr) {
        this.iznosNaknadeStr = iznosNaknadeStr;
    }

    public String getHitnoOpis() {
        return isInst() ? "INSTANT" : isHitno() ? "HITNO" : "REDOVAN";
    }

    public void setHitnoOpis(String hitnoOpis) {
        this.hitnoOpis = hitnoOpis;
    }

    public String getNazivDrzaveCreditora() {
        return nazivDrzaveCreditora;
    }

    public void setNazivDrzaveCreditora(String nazivDrzaveCreditora) {
        this.nazivDrzaveCreditora = nazivDrzaveCreditora;
    }

    public String getGreskaPlatnogNaloga() {
        return greskaPlatnogNaloga;
    }

    public void setGreskaPlatnogNaloga(String greskaPlatnogNaloga) {
        this.greskaPlatnogNaloga = greskaPlatnogNaloga;
    }

    public String getPainPaymentInfoId() {
        return painPaymentInfoId;
    }

    public void setPainPaymentInfoId(String painPaymentInfoId) {
        //Buduci da se kod dohvata iz baze paymentId nalazi u svrhi zbirnog naloga, moramo maknuti rijec 'batch booking'
        if (painPaymentInfoId != null && painPaymentInfoId.toUpperCase().indexOf("BATCH BOOKING") > -1)
            painPaymentInfoId = painPaymentInfoId.substring(13).trim();
        this.painPaymentInfoId = painPaymentInfoId;
    }

    public Boolean getDetaljiBatcBookinga() {
        return detaljiBatcBookinga;
    }

    public void setDetaljiBatcBookinga(Boolean detaljiBatcBookinga) {
        this.detaljiBatcBookinga = detaljiBatcBookinga;
    }

    public List<TppNalogPlatniDto> getBatchBookingNalozi() {
        return batchBookingNalozi != null ? batchBookingNalozi : new ArrayList<>();
    }

    public void setBatchBookingNalozi(List<TppNalogPlatniDto> batchBookingNalozi) {
        this.batchBookingNalozi = batchBookingNalozi;
    }

    public String getPainPaymentInformationId() {
        return painPaymentInformationId;
    }

    public void setPainPaymentInformationId(String painPaymentInformationId) {
        this.painPaymentInformationId = painPaymentInformationId;
    }

    public String getPainPaymentMethod() {
        return painPaymentMethod;
    }

    public void setPainPaymentMethod(String painPaymentMethod) {
        this.painPaymentMethod = painPaymentMethod;
    }

    public Integer getBrojPotrebnihPotpisa() {
        return brojPotrebnihPotpisa;
    }

    public void setBrojPotrebnihPotpisa(Integer brojPotrebnihPotpisa) {
        this.brojPotrebnihPotpisa = brojPotrebnihPotpisa;
    }

    public String getRenderedGreska() {
        return this.greska != null ? "true" : "false";
    }

    public Integer getSifraBasketa() {
        return sifraBasketa;
    }

    public void setSifraBasketa(Integer sifraBasketa) {
        this.sifraBasketa = sifraBasketa;
    }

    public void addNaknada(BigDecimal iznos) {
        if (this.iznosNaknade == null) this.iznosNaknade = BigDecimal.ZERO;
        this.iznosNaknade = this.iznosNaknade.add(iznos);
    }

    public Boolean isInBasket() {
        return sifraBasketa != null;
    }

    public String getTppRedirectUriCancelation() {
        return tppRedirectUriCancelation;
    }

    public void setTppRedirectUriCancelation(String tppRedirectUriCancelation) {
        this.tppRedirectUriCancelation = tppRedirectUriCancelation;
    }

    public String getValutaZaduzenja() {
        return valutaZaduzenja;
    }

    public void setValutaZaduzenja(String valutaZaduzenja) {
        this.valutaZaduzenja = valutaZaduzenja;
    }

    public String getValutaOdobrenja() {
        return valutaOdobrenja;
    }

    public void setValutaOdobrenja(String valutaOdobrenja) {
        this.valutaOdobrenja = valutaOdobrenja;
    }

    public String getOznakaValuteZaduzenja() {
        return oznakaValuteZaduzenja;
    }

    public void setOznakaValuteZaduzenja(String oznakaValuteZaduzenja) {
        this.oznakaValuteZaduzenja = oznakaValuteZaduzenja;
    }

    public String getOznakaValuteOdobrenja() {
        return oznakaValuteOdobrenja;
    }

    public void setOznakaValuteOdobrenja(String oznakaValuteOdobrenja) {
        this.oznakaValuteOdobrenja = oznakaValuteOdobrenja;
    }

    public String getTipTecajaZaduzenja() {
        return tipTecajaZaduzenja;
    }

    public void setTipTecajaZaduzenja(String tipTecajaZaduzenja) {
        this.tipTecajaZaduzenja = tipTecajaZaduzenja;
    }

    public String getTipTecajaOdobrenja() {
        return tipTecajaOdobrenja;
    }

    public void setTipTecajaOdobrenja(String tipTecajaOdobrenja) {
        this.tipTecajaOdobrenja = tipTecajaOdobrenja;
    }

    public BigDecimal getIznosZaduzenja() {
        return iznosZaduzenja;
    }

    public void setIznosZaduzenja(BigDecimal iznosZaduzenja) {
        this.iznosZaduzenja = iznosZaduzenja;
    }

    public BigDecimal getIznosOdobrenja() {
        return iznosOdobrenja;
    }

    public void setIznosOdobrenja(BigDecimal iznosOdobrenja) {
        this.iznosOdobrenja = iznosOdobrenja;
    }

    public BigDecimal getTecajZaduzenja() {
        BigDecimal tecaj = getRegularniTecajZaduzenja();
        if(getPovlasteniTecajZaduzenja() != null && getPovlasteniTecajZaduzenja().compareTo(BigDecimal.ZERO) != 0) {
            if(getMaxIznosZaduzenja() != null && getIznosZaduzenja() != null &&
                    getMaxIznosZaduzenja().compareTo(getIznosZaduzenja()) >= 0){
                tecaj = getPovlasteniTecajZaduzenja();
            } else if (getMaxIznosZaduzenja() == null || getIznosZaduzenja() == null) {
                tecaj = getPovlasteniTecajZaduzenja();
            }
        }
        return tecaj;
    }

    public void setTecajZaduzenja(BigDecimal tecajZaduzenja) {
        this.tecajZaduzenja = tecajZaduzenja;
    }

    public BigDecimal getTecajOdobrenja() {
        BigDecimal tecaj = getRegularniTecajOdobrenja();
        if(getPovlasteniTecajOdobrenja() != null && getPovlasteniTecajOdobrenja().compareTo(BigDecimal.ZERO) != 0) {
            if(getMaxIznosOdobrenja() != null && getIznosOdobrenja() != null &&
                    getMaxIznosOdobrenja().compareTo(getIznosOdobrenja()) >= 0) {
                tecaj = getPovlasteniTecajOdobrenja();
            } else if (getMaxIznosOdobrenja() == null || getIznosOdobrenja() == null) {
                tecaj = getPovlasteniTecajOdobrenja();
            }
        }
        return tecaj;
    }
    public void setTecajOdobrenja(BigDecimal tecajOdobrenja) {
        this.tecajOdobrenja = tecajOdobrenja;
    }

    public BigDecimal getPovlasteniTecajZaduzenja() {
        return povlasteniTecajZaduzenja;
    }

    public void setPovlasteniTecajZaduzenja(BigDecimal povlasteniTecajZaduzenja) {
        this.povlasteniTecajZaduzenja = povlasteniTecajZaduzenja;
    }

    public BigDecimal getPovlasteniTecajOdobrenja() {
        return povlasteniTecajOdobrenja;
    }

    public void setPovlasteniTecajOdobrenja(BigDecimal povlasteniTecajOdobrenja) {
        this.povlasteniTecajOdobrenja = povlasteniTecajOdobrenja;
    }

    public BigDecimal getRegularniTecajZaduzenja() {
        return regularniTecajZaduzenja;
    }

    public void setRegularniTecajZaduzenja(BigDecimal regularniTecajZaduzenja) {
        this.regularniTecajZaduzenja = regularniTecajZaduzenja;
    }

    public BigDecimal getRegularniTecajOdobrenja() {
        return regularniTecajOdobrenja;
    }

    public void setRegularniTecajOdobrenja(BigDecimal regularniTecajOdobrenja) {
        this.regularniTecajOdobrenja = regularniTecajOdobrenja;
    }

    public BigDecimal getMaxIznosZaduzenja() {
        return maxIznosZaduzenja;
    }

    public void setMaxIznosZaduzenja(BigDecimal maxIznosZaduzenja) {
        this.maxIznosZaduzenja = maxIznosZaduzenja;
    }

    public BigDecimal getMaxIznosOdobrenja() {
        return maxIznosOdobrenja;
    }

    public void setMaxIznosOdobrenja(BigDecimal maxIznosOdobrenja) {
        this.maxIznosOdobrenja = maxIznosOdobrenja;
    }

    public Integer getSifraPartijeOdobrenja() {
        return sifraPartijeOdobrenja;
    }

    public void setSifraPartijeOdobrenja(Integer sifraPartijeOdobrenja) {
        this.sifraPartijeOdobrenja = sifraPartijeOdobrenja;
    }

    public Integer getSifraKomitenta() {
        return sifraKomitenta;
    }

    public void setSifraKomitenta(Integer sifraKomitenta) {
        this.sifraKomitenta = sifraKomitenta;
    }

    public Integer getSifraPovlastenogTecajaZaduzenja() {
        return sifraPovlastenogTecajaZaduzenja;
    }

    public void setSifraPovlastenogTecajaZaduzenja(Integer sifraPovlastenogTecajaZaduzenja) {
        this.sifraPovlastenogTecajaZaduzenja = sifraPovlastenogTecajaZaduzenja;
    }

    public Integer getSifraPovlastenogTecajaOdobrenja() {
        return sifraPovlastenogTecajaOdobrenja;
    }

    public void setSifraPovlastenogTecajaOdobrenja(Integer sifraPovlastenogTecajaOdobrenja) {
        this.sifraPovlastenogTecajaOdobrenja = sifraPovlastenogTecajaOdobrenja;
    }

    public Integer getSifraPovlastenogTecaja() {
        return sifraPovlastenogTecaja;
    }

    public void setSifraPovlastenogTecaja(Integer sifraPovlastenogTecaja) {
        this.sifraPovlastenogTecaja = sifraPovlastenogTecaja;
    }

    public Boolean isPravnaOsoba() {
        return pravnaOsoba;
    }

    public void setIsPravnaOsoba(Boolean pravnaOsoba) {
        this.pravnaOsoba = pravnaOsoba;
    }

    public String getTipTecaja() {
        return tipTecaja;
    }

    public void setTipTecaja(String tipTecaja) {
        this.tipTecaja = tipTecaja;
    }

    public String getVrstaNaloga() {
        return vrstaNaloga;
    }

    public void setVrstaNaloga(String vrstaNaloga) {
        this.vrstaNaloga = vrstaNaloga;
    }

    public String getPaymentRequestId() {
        return paymentRequestId;
    }

    public void setPaymentRequestId(String paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}

