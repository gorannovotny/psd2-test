package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.model.dto.GenericDto;
import hr.abc.psd2.model.entity.core.IBKorisnikRacun;
import hr.abc.psd2.util.GenericBassxConstants;

import org.apache.commons.collections.keyvalue.MultiKey;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * DTO za ib_korisnik_racun
 *
 * @author Kristijan Novak
 *
 */
public class WebKorisnikRacunDto extends GenericDto<Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static String STATUS_IDENT = "statusIdent";
    public static String STATUS_KORISNIK = "statusKorisnik";
    public static String STATUS_OVLASTENJE = "statusOvlastenje";

    public static final Integer[] KLASA_TRANSAKCIJSKI_STANOVNISTVO = {
            GenericBassxConstants.Core.KLASA_PARTIJE_SIFRAKLP_MVR_I_ZIRO_RETAIL,
            103
    };

    public static final Integer[] KLASA_KREDITI = {
            GenericBassxConstants.Core.KLASA_PARTIJE_SIFRAKLP_KREDITI_FIZICKA_LICA
    };

    public static final Integer[] KLASA_STEDNJE = {
            102,
            103
    };

    public static final Integer[] KLASA_TRANSAKCIJSKI_PRAVNE = {
            GenericBassxConstants.Core.KLASA_PARTIJE_SIFRAKLP_TRANSAKCIJSKI_RACUNI_PRAVNIH_OSOBA_AVPO
    };

    public static final Integer[] KLASA_STANOVNISTVO = {
            GenericBassxConstants.Core.KLASA_PARTIJE_SIFRAKLP_MVR_I_ZIRO_RETAIL,
            102,
            103,
    };

    public static final String[] PARTIJA_PREFIX_STEDNJE_KNJIZICE = {
            "73",
            "75",
            "85",
    };

    //IB_KORISNIK_RACUN fields
    private Date vrijemeUnosa;//ib_korisnik_racun.vrije_ibr datum
    private Date datumPocetka;//ib_korisnik_racun.datod_ibr datum
    private Date datumZavrsetka;//ib_korisnik_racun.datdo_ibr datum
    private Date datum;
    @IFilter(entityField = "isadm_ibr")
    private String isAdmin;//ib_korisnik_racun.isadm_ibr flag isAdmin (Y, N)
    private String status;//ib_korisnik_racun.statu_ibr status ib_korisnik_racun (N, A)
    private String potpis;//ib_korisnik_racun.potpi_ibr potpis
    private BigDecimal iznosAutorizacije;//ib_korisnik_racun.autor_ibr
    private BigDecimal iznosVerifikacije;//ib_korisnik_racun.verif_ibr
    @IFilter(entityField = "komit_ide")
    private Integer sifraKorisnika;//ib_korisnik_racun.komit_ibr sifra korisnika
    @IFilter(entityField = "k.sifra_kom")
    private Integer sifraFizKomitenta;//komitent.sifra_kom
    private Integer sifraLikvidatora;//ib_korisnik_racun.likvi_ibr sifra likvidatora
    @IFilter(entityField = "parti_ibr")
    private Integer sifraPartijeOvlastenja;//ib_korisnik_racun.parti_ibr sifra partije
    @IFilter(entityField = "ident_ibr")
    private Integer sifraIdentUredjaja;//ib_korisnik_racun.ident_ibr sifra ident
    @IFilter(entityField = "serij_ide")
    private String serijskiBrojIdentUredjaja;//ib_korisnik_racun.ident_ibr sifra ident

    @IFilter(entityField = "parti_par")
    private String oznakaPartijeOvlastenja;
    private Integer tipVeze;
    private String nazivLikvidatora;

    private PartijaDto partijaDto;
    private KomitentDto komitentDto;
    private List<String> listaUsluga;
    private List<WebKorisnikRacunUslugaDto> listaUslugaDto;
    private Integer brojPotrebnihPotpisa;

    private String platiteljNaziv;
    private String platiteljAdresa;
    private String platiteljNazivMjesta;
    private String platiteljDrzava;
    private String platiteljlOib;
    private String platiteljMbr;
    private Boolean trebaPotpisTokenom = Boolean.FALSE;
    private Boolean dodajListuUslugaDto = Boolean.FALSE;
    private String statusInfo;
    private String uloga;
    private String infoObrade;

    @IFilter(entityField = "k.oib_kom")
    private String oibKorisnika;
    @IFilter(entityField = "k.matbr_kom")
    private String mbrKorisnika;
    @IFilter(entityField = "k.naziv_kom")
    private String nazivKorisnika;
    @IFilter(entityField = "vp.oib_kom")
    private String oibVlasnikaPartije;
    @IFilter(entityField = "vp.matbr_kom")
    private String mbrVlasnikaPartije;
    @IFilter(entityField = "vp.naziv_kom")
    private String nazivVlasnikaPartije;
    @IFilter(entityField = "vp.sifra_kom")
    private Integer sifraVlasnikaPartije;
    private String sektorVlasnikaPartije;

    private Boolean isTransakcijski = Boolean.FALSE, isKredit = Boolean.FALSE, isStednja = Boolean.FALSE;

    /**
     * MultiKey = sifraPartije, partiPar, sifraValute
     */
    private Map<MultiKey, WebPregledRacunaDto> mapPartijaValuta;

    // za usluge
    private String isUnos;     // (Y,N)
    private String isPotpis;   // Y,N
    private String isPregled;  // Y,N

    //statusi
    private String statusIdent;
    private String statusKorisnik;

    //constructors
    public WebKorisnikRacunDto() {
        super();
    }

    public WebKorisnikRacunDto(boolean isFirstCall) {
        super(isFirstCall);
    }

    //getters & setters
    @Override
    @IFilter(entityField = "sifra_ibr")
    public Integer getSifra() {
        return super.getSifra();
    }


    public Date getVrijemeUnosa() {
        return vrijemeUnosa;
    }

    public void setVrijemeUnosa(Date vrijemeUnosa) {
        this.vrijemeUnosa = vrijemeUnosa;
    }

    public Date getDatumPocetka() {
        return datumPocetka;
    }

    public void setDatumPocetka(Date datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    public Date getDatumZavrsetka() {
        return datumZavrsetka;
    }

    public void setDatumZavrsetka(Date datumZavrsetka) {
        this.datumZavrsetka = datumZavrsetka;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPotpis() {
        return potpis;
    }

    public void setPotpis(String potpis) {
        this.potpis = potpis;
    }

    public BigDecimal getIznosAutorizacije() {
        return iznosAutorizacije;
    }

    public void setIznosAutorizacije(BigDecimal iznosAutorizacije) {
        this.iznosAutorizacije = iznosAutorizacije;
    }

    public BigDecimal getIznosVerifikacije() {
        return iznosVerifikacije;
    }

    public void setIznosVerifikacije(BigDecimal iznosVerifikacije) {
        this.iznosVerifikacije = iznosVerifikacije;
    }

    public Integer getSifraKorisnika() {
        return sifraKorisnika;
    }

    public void setSifraKorisnika(Integer sifraKorisnika) {
        this.sifraKorisnika = sifraKorisnika;
    }

    public Integer getSifraLikvidatora() {
        return sifraLikvidatora;
    }

    public void setSifraLikvidatora(Integer sifraLikvidatora) {
        this.sifraLikvidatora = sifraLikvidatora;
    }

    public Integer getSifraPartijeOvlastenja() {
        return sifraPartijeOvlastenja;
    }

    public void setSifraPartijeOvlastenja(Integer sifraPartijeOvkastenja) {
        this.sifraPartijeOvlastenja = sifraPartijeOvkastenja;
    }

    public String getOznakaPartijeOvlastenja() {
        return oznakaPartijeOvlastenja;
    }

    public void setOznakaPartijeOvlastenja(String oznakaPartijeOvlastenja) {
        this.oznakaPartijeOvlastenja = oznakaPartijeOvlastenja;
    }

    public KomitentDto getKomitentDto() {
        return komitentDto;
    }

    public void setKomitentDto(KomitentDto komitentDto) {
        this.komitentDto = komitentDto;
    }

    public PartijaDto getPartijaDto() {
        return partijaDto;
    }

    public void setPartijaDto(PartijaDto partijaDto) {
        this.partijaDto = partijaDto;
    }

    public List<String> getListaUsluga() {
        return listaUsluga;
    }

    public void setListaUsluga(List<String> listaUsluga) {
        this.listaUsluga = listaUsluga;
    }

    public String getIsUnos() {
        return isUnos;
    }

    public void setIsUnos(String isUnos) {
        this.isUnos = isUnos;
    }

    public String getIsPotpis() {
        return isPotpis;
    }

    public void setIsPotpis(String isPotpis) {
        this.isPotpis = isPotpis;
    }

    public String getIsPregled() {
        return isPregled;
    }

    public void setIsPregled(String isPregled) {
        this.isPregled = isPregled;
    }

//    public String getTipVeze() { return tipVeze; }

//    public void setTipVeze(String tipVeze) { this.tipVeze = tipVeze; }

    public Integer getTipVeze() { return tipVeze; }

    public void setTipVeze(Integer tipVeze) { this.tipVeze = tipVeze; }

    public Map<MultiKey, WebPregledRacunaDto> getMapPartijaValuta() {
        return mapPartijaValuta;
    }

    public void setMapPartijaValuta(Map<MultiKey, WebPregledRacunaDto> mapPartijaValuta) {
        this.mapPartijaValuta = mapPartijaValuta;
    }

    public Integer getBrojPotrebnihPotpisa() {
        return brojPotrebnihPotpisa;
    }

    public void setBrojPotrebnihPotpisa(Integer brojPotrebnihPotpisa) {
        this.brojPotrebnihPotpisa = brojPotrebnihPotpisa;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getPlatiteljNaziv() {
        return platiteljNaziv;
    }

    public void setPlatiteljNaziv(String platiteljNaziv) {
        this.platiteljNaziv = platiteljNaziv;
    }

    public String getPlatiteljAdresa() {
        return platiteljAdresa;
    }

    public void setPlatiteljAdresa(String platiteljAdresa) {
        this.platiteljAdresa = platiteljAdresa;
    }

    public String getPlatiteljNazivMjesta() {
        return platiteljNazivMjesta;
    }

    public void setPlatiteljNazivMjesta(String platiteljNazivMjesta) {
        this.platiteljNazivMjesta = platiteljNazivMjesta;
    }

    public String getPlatiteljDrzava() {
        return platiteljDrzava;
    }

    public void setPlatiteljDrzava(String platiteljDrzava) {
        this.platiteljDrzava = platiteljDrzava;
    }

    public String getPlatiteljlOib() {
        return platiteljlOib;
    }

    public void setPlatiteljlOib(String platiteljlOib) {
        this.platiteljlOib = platiteljlOib;
    }

    public String getPlatiteljMbr() {
        return platiteljMbr;
    }

    public void setPlatiteljMbr(String platiteljMbr) {
        this.platiteljMbr = platiteljMbr;
    }

    public Boolean getIsStednja() {
        return partijaDto != null && Arrays.asList(WebKorisnikRacunDto.KLASA_STEDNJE).contains(partijaDto.getSifraKlasePartije());
    }
    public Boolean getIsStednjaKKA() {
        return partijaDto != null && partijaDto.getSifraTipaPartije() != null && (partijaDto.getSifraTipaPartije().compareTo(1290) == 0 || partijaDto.getSifraTipaPartije().compareTo(1292) == 0);
    }


    public Boolean getIsTransakcijski(Boolean zaTransakcije) {
        boolean res = false;
        res = partijaDto != null && (Arrays.asList(WebKorisnikRacunDto.KLASA_TRANSAKCIJSKI_PRAVNE).contains(partijaDto.getSifraKlasePartije()) ||
                Arrays.asList(WebKorisnikRacunDto.KLASA_TRANSAKCIJSKI_STANOVNISTVO).contains(partijaDto.getSifraKlasePartije()));
        if(zaTransakcije) {
            res = res && (partijaDto.getSifraProizvoda() == null || partijaDto.getSifraProizvoda().compareTo(167) != 0); //Vjeverica
            res = res && (partijaDto.getBroj() != null && !Arrays.asList(WebKorisnikRacunDto.PARTIJA_PREFIX_STEDNJE_KNJIZICE).contains(partijaDto.getBroj().substring(0, 2))); //Stednje knjizice
            res = res && (partijaDto.getSifraTipaPartije() != null && partijaDto.getSifraTipaPartije().compareTo(1290) != 0 && partijaDto.getSifraTipaPartije().compareTo(1292) != 0); //Stedna knjizica 33, 73
        }
        return res;
    }

    public Boolean getIsTransakcijski() {
        return this.getIsTransakcijski(Boolean.TRUE);
    }

    public Boolean getIsKredit() {
        return partijaDto != null && Arrays.asList(WebKorisnikRacunDto.KLASA_KREDITI).contains(partijaDto.getSifraKlasePartije());
    }

    public String getNazivLikvidatora() {
        return nazivLikvidatora;
    }

    public void setNazivLikvidatora(String nazivLikvidatora) {
        this.nazivLikvidatora = nazivLikvidatora;
    }

    public String getOibKorisnika() {
        return oibKorisnika;
    }

    public void setOibKorisnika(String oibKorisnika) {
        this.oibKorisnika = oibKorisnika;
    }

    public String getMbrKorisnika() {
        return mbrKorisnika;
    }

    public void setMbrKorisnika(String mbrKorisnika) {
        this.mbrKorisnika = mbrKorisnika;
    }

    public String getNazivKorisnika() {
        return nazivKorisnika;
    }

    public void setNazivKorisnika(String nazivKorisnika) {
        this.nazivKorisnika = nazivKorisnika;
    }

    public String getOibVlasnikaPartije() {
        return oibVlasnikaPartije;
    }

    public void setOibVlasnikaPartije(String oibVlasnikaPartije) {
        this.oibVlasnikaPartije = oibVlasnikaPartije;
    }

    public String getMbrVlasnikaPartije() {
        return mbrVlasnikaPartije;
    }

    public void setMbrVlasnikaPartije(String mbrVlasnikaPartije) {
        this.mbrVlasnikaPartije = mbrVlasnikaPartije;
    }

    public String getNazivVlasnikaPartije() {
        return nazivVlasnikaPartije;
    }

    public void setNazivVlasnikaPartije(String nazivVlasnikaPartije) {
        this.nazivVlasnikaPartije = nazivVlasnikaPartije;
    }

    public Integer getSifraFizKomitenta() {
        return sifraFizKomitenta;
    }

    public void setSifraFizKomitenta(Integer sifraFizKomitenta) {
        this.sifraFizKomitenta = sifraFizKomitenta;
    }

    public Boolean getTrebaPotpisTokenom() {
        return trebaPotpisTokenom || (this.potpis != null && this.potpis.equals("T"));
    }

    public void setTrebaPotpisTokenom(Boolean trebaPotpisTokenom) {
        this.trebaPotpisTokenom = trebaPotpisTokenom;
    }

    public String getStatusInfo(){
        return this.getStatus() != null
                ? this.getStatus().equals(IBKorisnikRacun.STATUS_ZATVOREN) ? "ZATVORENO" :
                this.getStatus().equals(IBKorisnikRacun.STATUS_NEAKTIVAN) ? "NEAKTIVAN" :
                        this.getStatus().equals(IBKorisnikRacun.STATUS_BLOKIRAN) ? "BLOKIRAN" :
                                "AKTIVAN"
                : "-";
    }

    public Integer getSifraVlasnikaPartije() {
        return sifraVlasnikaPartije;
    }

    public void setSifraVlasnikaPartije(Integer sifraVlasnikaPartije) {
        this.sifraVlasnikaPartije = sifraVlasnikaPartije;
    }

    public List<WebKorisnikRacunUslugaDto> getListaUslugaDto() {
        return listaUslugaDto;
    }

    public void setListaUslugaDto(List<WebKorisnikRacunUslugaDto> listaUslugaDto) {
        this.listaUslugaDto = listaUslugaDto;
    }

    public Boolean getDodajListuUslugaDto() {
        return dodajListuUslugaDto;
    }

    public void setDodajListuUslugaDto(Boolean dodajListuUslugaDto) {
        this.dodajListuUslugaDto = dodajListuUslugaDto;
    }

    public Integer getSifraIdentUredjaja() {
        return sifraIdentUredjaja;
    }

    public void setSifraIdentUredjaja(Integer sifraIdentUredjaja) {
        this.sifraIdentUredjaja = sifraIdentUredjaja;
    }

    public String getSerijskiBrojIdentUredjaja() {
        return serijskiBrojIdentUredjaja;
    }

    public void setSerijskiBrojIdentUredjaja(String serijskiBrojIdentUredjaja) {
        this.serijskiBrojIdentUredjaja = serijskiBrojIdentUredjaja;
    }

    public String getSektorVlasnikaPartije() {
        return sektorVlasnikaPartije;
    }

    public void setSektorVlasnikaPartije(String sektorVlasnikaPartije) {
        this.sektorVlasnikaPartije = sektorVlasnikaPartije;
    }

    public Boolean isStranaFizickaOsoba(){
        return sektorVlasnikaPartije != null && sektorVlasnikaPartije.equals(GenericBassxConstants.CoreHub.SIFRA_SEKTORA_STANOVNISTVO_NEREZIDENT);
    }

    public Boolean isDomacaFizickaOsoba(){
        return sektorVlasnikaPartije != null && sektorVlasnikaPartije.equals(GenericBassxConstants.CoreHub.SIFRA_SEKTORA_STANOVNISTVO_REZIDENT);
    }

    public String getUloga() {
        return isAdmin != null ? isAdmin.equals(IBKorisnikRacun.OZNAKA_YES) ? "ADMINISTRATOR" : "KORISNIK" : "-";
    }

    public String getInfoObrade() {
        return infoObrade;
    }

    public void setInfoObrade(String infoObrade) {
        this.infoObrade = infoObrade;
    }

    public String getStatusIdent() {
        return statusIdent;
    }

    public void setStatusIdent(String statusIdent) {
        this.statusIdent = statusIdent;
    }

    public String getStatusKorisnik() {
        return statusKorisnik;
    }

    public void setStatusKorisnik(String statusKorisnik) {
        this.statusKorisnik = statusKorisnik;
    }
}