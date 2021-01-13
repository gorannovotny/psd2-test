package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.model.dto.GenericDto;
import hr.abc.psd2.model.entity.core.IBIdentifikacija;
import hr.abc.psd2.model.entity.core.IBKorisnik;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * DTO za ib_korisnik
 *
 * @author Kristijan Novak
 *
 */
public class WebKorisnikDto extends GenericDto<Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    //IB_KOMITENT fields
    @IFilter(entityField = "ibk.stibk_kom")
    private String status;//ib_komitent.stibk_kom status ib_komitent (N, A)
    private Date vrijemeUnosa;//ib_komitent.vrije_kom vrijeme
    @IFilter(entityField = "fiz.sifra_kom")
    private Integer sifraFizKomitenta;//ib_komitent.fikom_kom sifra fiz_komitent (vanjski kljuc na tablicu fiz_komitent)
    private Integer sifraPravKomitenta;//ib_komitent.prkom_kom sifra prav_komitent (vanjski kljuc na tablicu prav_komitent)
    private Integer sifraPartijeNaknade;//ib_komitent.parti_kom sifra partije naknade (vanjski kljuc na tablicu fiz_komitent)
    @IFilter(entityField = "partija.parti_par")
    private String partiParPartijeNaknade;//ib_komitent.parti_kom sifra partije naknade (vanjski kljuc na tablicu fiz_komitent)
    private String nazivVlasnikaPartijeNaknade;
    private Integer sifraLikvidatora;//ib_komitent.likvi_kom sifra likvidator (vanjski kljuc na tablicu likvidator)
    private String odgovor;//ib_komitent.odgov_kom odgovor
    private String pitanje;//ib_komitent.pitan_kom pitanje
    @IFilter(entityField = "ibk.datak_kom")
    private Date datumAktivacije;//ib_komitent.datak_kom
    @IFilter(entityField = "ibk.datza_kom")
    private Date datumZatvaranja;//ib_komitent.datza_kom
    private String statusOvlastenja;

    //Ovlastenja
    private Map<PartijaDto, KomitentDto> ovlastenja;
    private List<WebKorisnikRacunDto> listPartijaOvlastenja; 
    private String oznakaPartijeOvlastenjaFilter;
    
    //komitent fields
    @IFilter(entityField = "fiz.naziv_kom")
    private String nazivFizickeOsobe;
    @IFilter(entityField = "prav.naziv_kom")
    private String nazivPravneOsobe;
    private String nazivPrijavljenogKorisnika;
    @IFilter(entityField = "fiz.oib_kom")
    private String oibFizickeOsobe;
    @IFilter(entityField = "fiz.matbr_kom")
    private String mbrFizickeOsobe;
    @IFilter(entityField = "prav.oib_kom")
    private String oibPravneOsobe;

    private String nazivLikvidatora;

    //ib_ident fields
    @IFilter(entityField = "serij_ide")
    private String identSerijskiBroj;

    private WebIdentifikacijaDto ibIdent;

    //constructors
    public WebKorisnikDto() {
        super();
    }

    public WebKorisnikDto(Integer sifra) {
	super(sifra);
    }

    public WebKorisnikDto(boolean isFirstCall) {
        super(isFirstCall);
    }

    //getters & setters
    @Override
    @IFilter(entityField = "ibk.sifra_kom")
    public Integer getSifra() {
	return super.getSifra();
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public Date getVrijemeUnosa() {
	return vrijemeUnosa;
    }

    public void setVrijemeUnosa(Date vrijemeUnosa) {
	this.vrijemeUnosa = vrijemeUnosa;
    }

    public Integer getSifraFizKomitenta() {
	return sifraFizKomitenta;
    }

    public void setSifraFizKomitenta(Integer sifraFizKomitenta) {
	this.sifraFizKomitenta = sifraFizKomitenta;
    }

    public Integer getSifraPravKomitenta() {
	return sifraPravKomitenta;
    }

    public void setSifraPravKomitenta(Integer sifraPravKomitenta) {
	this.sifraPravKomitenta = sifraPravKomitenta;
    }

    public Integer getSifraPartijeNaknade() {
	return sifraPartijeNaknade;
    }

    public void setSifraPartijeNaknade(Integer sifraPartije) {
	this.sifraPartijeNaknade = sifraPartije;
    }

    public Integer getSifraLikvidatora() {
	return sifraLikvidatora;
    }

    public void setSifraLikvidatora(Integer sifraLikvidatora) {
	this.sifraLikvidatora = sifraLikvidatora;
    }

    public String getOdgovor() {
	return odgovor;
    }

    public void setOdgovor(String odgovor) {
	this.odgovor = odgovor;
    }

    public String getPitanje() {
	return pitanje;
    }

    public void setPitanje(String pitanje) {
	this.pitanje = pitanje;
    }

    public Date getDatumAktivacije() {
	return datumAktivacije;
    }

    public void setDatumAktivacije(Date datumAktivacije) {
	this.datumAktivacije = datumAktivacije;
    }

    public Date getDatumZatvaranja() {
	return datumZatvaranja;
    }

    public void setDatumZatvaranja(Date datumZatvaranja) {
	this.datumZatvaranja = datumZatvaranja;
    }

    public String getNazivFizickeOsobe() {
        return nazivFizickeOsobe;
    }

    public void setNazivFizickeOsobe(String nazivFizickeOsobe) {
        this.nazivFizickeOsobe = nazivFizickeOsobe;
    }

    public String getNazivPravneOsobe() {
        return nazivPravneOsobe;
    }

    public void setNazivPravneOsobe(String nazivPravneOsobe) {
        this.nazivPravneOsobe = nazivPravneOsobe;
    }

    public String getNazivPrijavljenogKorisnika() {
        String res = this.getNazivFizickeOsobe();
        if(res == null){
            res = this.getNazivPravneOsobe() != null ? this.getNazivPravneOsobe() : "NEPOZNAT";
        }else{
            res = this.getNazivFizickeOsobe() + (this.getNazivPravneOsobe() != null ? (" / " + this.nazivPravneOsobe) : "");
        }
        return res;
    }

    public void setNazivPrijavljenogKorisnika(String nazivpriojavljenogKorisnika) {
        this.nazivPrijavljenogKorisnika = nazivpriojavljenogKorisnika;
    }

    public String getOibFizickeOsobe() {
        return oibFizickeOsobe;
    }

    public void setOibFizickeOsobe(String oibFizickeOsobe) {
        this.oibFizickeOsobe = oibFizickeOsobe;
    }

    public String getOibPravneOsobe() {
        return oibPravneOsobe;
    }

    public void setOibPravneOsobe(String oibPravneOsobe) {
        this.oibPravneOsobe = oibPravneOsobe;
    }

    public String getPartiParPartijeNaknade() {
        return partiParPartijeNaknade;
    }

    public void setPartiParPartijeNaknade(String partiParPartijeNaknade) {
        this.partiParPartijeNaknade = partiParPartijeNaknade;
    }

  
    /**
     * Metoda vraća mapu [Partija,Vlasnik partije] za sva ovlaštenja koja prijavljeni korisnik ima
     * @return mapa ovlaštenja [Partija,Vlasnik partije]
     */
    public Map<PartijaDto, KomitentDto> getOvlastenja() {
        return ovlastenja;
    }

    public void setOvlastenja(Map<PartijaDto, KomitentDto> ovlastenja) {
        this.ovlastenja = ovlastenja;
    }
    
	
	public String getNazivVlasnikaPartijeNaknade() {
		return nazivVlasnikaPartijeNaknade;
	}
	public void setNazivVlasnikaPartijeNaknade(String nazivVlasnikaPartijeNaknade) {
		this.nazivVlasnikaPartijeNaknade = nazivVlasnikaPartijeNaknade;
	}
	public List<WebKorisnikRacunDto> getListPartijaOvlastenja() {
		return listPartijaOvlastenja;
	}
	public void setListPartijaOvlastenja(List<WebKorisnikRacunDto> listPartijaOvlastenja) {
		this.listPartijaOvlastenja = listPartijaOvlastenja;
	}
	public String getOznakaPartijeOvlastenjaFilter() {
		return oznakaPartijeOvlastenjaFilter;
	}
	public void setOznakaPartijeOvlastenjaFilter(String oznakaPartijeOvlastenjaFilter) {
		this.oznakaPartijeOvlastenjaFilter = oznakaPartijeOvlastenjaFilter;
	}

    public String getIdentSerijskiBroj() {
        return identSerijskiBroj;
    }

    public void setIdentSerijskiBroj(String identSerijskiBroj) {
        this.identSerijskiBroj = identSerijskiBroj;
    }

    public String getStatusOvlastenja() {
        return statusOvlastenja == null || statusOvlastenja.equals(IBKorisnik.OVLAS_VLASNIK) ? IBKorisnik.OVLAS_VLASNIK : IBKorisnik.OVLAS_SAMO_OVLASTENIK;
    }

    public void setStatusOvlastenja(String statusOvlastenja) {
        this.statusOvlastenja = statusOvlastenja;
    }

    public WebIdentifikacijaDto getIbIdent() {
    	if (ibIdent == null) ibIdent = new WebIdentifikacijaDto();
        return ibIdent;
    }

    public void setIbIdent(WebIdentifikacijaDto ibIdent) {
        this.ibIdent = ibIdent;
    }

    public String getMbrFizickeOsobe() {
        return mbrFizickeOsobe;
    }

    public void setMbrFizickeOsobe(String mbrFizickeOsobe) {
        this.mbrFizickeOsobe = mbrFizickeOsobe;
    }

    public String getNazivLikvidatora() {
        return nazivLikvidatora;
    }

    public void setNazivLikvidatora(String nazivLikvidatora) {
        this.nazivLikvidatora = nazivLikvidatora;
    }

    public String getStatusInfo(){
        return this.getStatus() != null
                ? this.getStatus().equals(IBIdentifikacija.STATUS_DEAKTIVIRAN) ? "DEAKTIVIRAN" :
                this.getStatus().equals(IBIdentifikacija.STATUS_NEAKTIVAN) ? "NEAKTIVAN" :
                        this.getStatus().equals(IBIdentifikacija.STATUS_BLOKIRAN) ? "BLOKIRAN" :
                                "AKTIVAN"
                : "-";
    }
}
