package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.model.dto.GenericDto;
import hr.abc.psd2.model.entity.core.IBIdentifikacija;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DTO za ib_korisnik_racun_usluga
 *
 * @author Kristijan Novak
 *
 */
public class WebIdentifikacijaDto extends GenericDto<Integer> implements Serializable {

    public static final String[] TIP_PRAVNE = {
            IBIdentifikacija.TIP_AUTH_SMARTTOKEN,
            IBIdentifikacija.TIP_AUTH_SOFT_TOKEN_MOBILE_NEW
    };

    public static final String[] TIP_STANOVNISTVO = {
            IBIdentifikacija.TIP_AUTH_TOKEN,
            IBIdentifikacija.TIP_AUTH_SOFT_TOKEN_MOBILE,
            IBIdentifikacija.TIP_AUTH_SOFT_TOKEN_MOBILE_NEW
    };

    private static final long serialVersionUID = 1L;

    //IB_IDENT fields
    @IFilter(entityField = "ib_ident.datak_ide")
    private Date datumAktivacije;
    @IFilter(entityField = "ib_ident.datde_ide")
    private Date datumDeaktivacije;
    private String pin;
    @IFilter(entityField = "ib_ident.serij_ide")
    private String serijskiBroj;
//    @IFilter(entityField = "ib_ident.ibena_ide")
    private String ibEnabled;
//    @IFilter(entityField = "ib_ident.caena_ide")
    private String cardEnabled;
    @IFilter(entityField = "ib_ident.statu_ide")
    private String status;
    @IFilter(entityField = "ib_ident.tipid_ide")
    private String tip;
    @IFilter(entityField = "ib_ident.datun_ide")
    private Date vrijemeUnosa;
    private Integer sifraUredjajaExterna;
    @IFilter(entityField = "ib_ident.komit_ide")
    private Integer sifraIbKorisnika;
    private String prvaRegistracija;
    private Boolean citac;
    @IFilter(entityField = "kom.oib_kom")
    private String oibVlasnika;
    @IFilter(entityField = "kom.matbr_kom")
    private String matbrVlasnika;
    @IFilter(entityField = "kom.naziv_kom")
    private String nazivVlasnika;
    @IFilter(entityField = "kom.sifra_kom")
    private Integer sifraVlasnika;

    private Boolean zaZamjenu = Boolean.FALSE;
    private Boolean zaZamjenuUSBPKI = Boolean.FALSE;
    private Boolean zaZamjenuTokeni = Boolean.FALSE;
    private Boolean zaIzdavanje = Boolean.FALSE;
    private Boolean provjeraIsteVrste = Boolean.FALSE;
    private String infoZamjene;
    private Boolean zaPravneOsobe;
    private Boolean prijava = Boolean.FALSE;
    private List<String> bezTipova = new ArrayList<>();

    //    @IFilter(entityField = "ib_ident.lockd_ide")
    private String locked;
    @IFilter(entityField = "ib_ident.lockt_ide")
    private Date lockedTime;
    @IFilter(entityField = "ib_ident.lockv_ide")
    private String lockedVrsta;
    private Boolean isLocked = Boolean.FALSE;

    //constructors
    public WebIdentifikacijaDto() {
        super();
    }

    public WebIdentifikacijaDto(Integer sifra) {
        super(sifra);
    }

    public WebIdentifikacijaDto(boolean isFirstCall) {
        super(isFirstCall);
    }



    public WebIdentifikacijaDto(String serijskiBroj) {
        super();
        this.serijskiBroj = serijskiBroj;
    }

    //getters & setters
    @Override
    @IFilter(entityField = "ib_ident.sifra_ide")
    public Integer getSifra() {
        return super.getSifra();
    }

    public Date getDatumAktivacije() {
        return datumAktivacije;
    }

    public void setDatumAktivacije(Date datumAktivacije) {
        this.datumAktivacije = datumAktivacije;
    }

    public Date getDatumDeaktivacije() {
        return datumDeaktivacije;
    }

    public void setDatumDeaktivacije(Date datumDeaktivacije) {
        this.datumDeaktivacije = datumDeaktivacije;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getSerijskiBroj() {
        return serijskiBroj;
    }

    public void setSerijskiBroj(String serijskiBroj) {
        this.serijskiBroj = serijskiBroj;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Date getVrijemeUnosa() {
        return vrijemeUnosa;
    }

    public void setVrijemeUnosa(Date vrijemeUnosa) {
        this.vrijemeUnosa = vrijemeUnosa;
    }

    public Integer getSifraUredjajaExterna() {
        return sifraUredjajaExterna;
    }

    public void setSifraUredjajaExterna(Integer sifraUredjajaExterna) {
        this.sifraUredjajaExterna = sifraUredjajaExterna;
    }

    public Integer getSifraIbKorisnika() {
        return sifraIbKorisnika;
    }

    public void setSifraIbKorisnika(Integer sifraIbKorisnika) {
        this.sifraIbKorisnika = sifraIbKorisnika;
    }

    public String getPrvaRegistracija() {
        return prvaRegistracija;
    }

    public void setPrvaRegistracija(String prvaRegistracija) {
        this.prvaRegistracija = prvaRegistracija;
    }

    public Boolean getCitac() {
        return citac;
    }

    public void setCitac(Boolean citac) {
        this.citac = citac;
    }

    public String getOibVlasnika() {
        return oibVlasnika;
    }

    public void setOibVlasnika(String oibVlasnika) {
        this.oibVlasnika = oibVlasnika;
    }

    public String getMatbrVlasnika() {
        return matbrVlasnika;
    }

    public void setMatbrVlasnika(String matbrVlasnika) {
        this.matbrVlasnika = matbrVlasnika;
    }

    public String getNazivVlasnika() {
        return nazivVlasnika;
    }

    public void setNazivVlasnika(String nazivVlasnika) {
        this.nazivVlasnika = nazivVlasnika;
    }

    public Integer getSifraVlasnika() {
        return sifraVlasnika;
    }

    public void setSifraVlasnika(Integer sifraVlasnika) {
        this.sifraVlasnika = sifraVlasnika;
    }

    public String getTipInfo(){
        return this.getTip() != null
                ? this.getTip().equals(IBIdentifikacija.TIP_AUTH_PKI_KARTICA) ? IBIdentifikacija.TIP_AUTH_PKI_KARTICA_OPIS :
                this.getTip().equals(IBIdentifikacija.TIP_AUTH_TOKEN) ? IBIdentifikacija.TIP_AUTH_TOKEN_OPIS :
                        this.getTip().equals(IBIdentifikacija.TIP_AUTH_SMARTTOKEN) ? IBIdentifikacija.TIP_AUTH_SMARTTOKEN_OPIS : IBIdentifikacija.TIP_AUTH_SOFT_TOKEN_MOBILE_OPIS
                : "-";
    }

    public String getStatusInfo(){
        return this.getStatus() != null
                ? this.getStatus().equals(IBIdentifikacija.STATUS_DEAKTIVIRAN) ? "DEAKTIVIRAN" :
                this.getStatus().equals(IBIdentifikacija.STATUS_NEAKTIVAN) ? "NEAKTIVAN" :
                        this.getStatus().equals(IBIdentifikacija.STATUS_BLOKIRAN) ? "BLOKIRAN" :
                                "AKTIVAN"
                : "-";
    }

    public Boolean getZaZamjenu() {
        return zaZamjenu;
    }

    public void setZaZamjenu(Boolean zaZamjenu) {
        this.zaZamjenu = zaZamjenu;
    }

    public Boolean getZaIzdavanje() {
        return zaIzdavanje;
    }

    public void setZaIzdavanje(Boolean zaIzdavanje) {
        this.zaIzdavanje = zaIzdavanje;
    }

    public Boolean getZaZamjenuUSBPKI() {
        return zaZamjenuUSBPKI;
    }

    public void setZaZamjenuUSBPKI(Boolean zaZamjenuUSBPKI) {
        this.zaZamjenuUSBPKI = zaZamjenuUSBPKI;
    }

    public Boolean getZaZamjenuTokeni() {
        return zaZamjenuTokeni;
    }

    public void setZaZamjenuTokeni(Boolean zaZamjenuTokeni) {
        this.zaZamjenuTokeni = zaZamjenuTokeni;
    }

    public Boolean getProvjeraIsteVrste() {
        return provjeraIsteVrste;
    }

    public void setProvjeraIsteVrste(Boolean provjeraIsteVrste) {
        this.provjeraIsteVrste = provjeraIsteVrste;
    }

    public Boolean getZaPravneOsobe() {
        return tip != null && (tip.equals(IBIdentifikacija.TIP_AUTH_SMARTTOKEN) || tip.equals(IBIdentifikacija.TIP_AUTH_PKI_KARTICA));
    }

    public Boolean getPrijava() {
        return prijava;
    }

    public void setPrijava(Boolean prijava) {
        this.prijava = prijava;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public Boolean isLocked() {
        return locked != null && locked.equals("Y");
    }

    public Date getLockedTime() {
        return lockedTime;
    }

    public void setLockedTime(Date lockedTime) {
        this.lockedTime = lockedTime;
    }

    public String getLockedVrsta() {
        return lockedVrsta;
    }

    public void setLockedVrsta(String lockedVrsta) {
        this.lockedVrsta = lockedVrsta;
    }
    public String getLockedStr(){
        return locked != null && locked.equals("Y") ? "DA" : "NE";
    }
    public String getLockedVrstaStr(){
        return lockedVrsta == null ? "-" : (lockedVrsta != null && lockedVrsta.equals("P") ? "OTP" : "POTPIS");
    }

    public String getIbEnabled() {
        return ibEnabled;
    }

    public void setIbEnabled(String ibEnabled) {
        this.ibEnabled = ibEnabled;
    }

    public String getCardEnabled() {
        return cardEnabled;
    }

    public void setCardEnabled(String cardEnabled) {
        this.cardEnabled = cardEnabled;
    }
    public Boolean getIsIbEnabled(){
        return this.ibEnabled != null && ibEnabled.equals("Y");
    }
    public Boolean getIsCardEnabled(){
        return this.cardEnabled != null && cardEnabled.equals("Y");
    }
    public void setIsIbEnabled(Boolean ibEnabled){
        this.ibEnabled = ibEnabled ? "Y" : "N";
    }
    public void setIsCardEnabled(Boolean ibEnabled){
        this.cardEnabled = ibEnabled ? "Y" : "N";
    }

    public List<String> getBezTipova() {
        return bezTipova;
    }

    public void setBezTipova(List<String> bezTipova) {
        this.bezTipova = bezTipova;
    }
}
