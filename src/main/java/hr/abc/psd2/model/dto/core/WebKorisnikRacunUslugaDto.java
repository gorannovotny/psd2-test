package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.model.dto.GenericDto;

import java.io.Serializable;

/**
 * DTO za ib_korisnik_racun_usluga
 *
 * @author Kristijan Novak
 *
 */
public class WebKorisnikRacunUslugaDto extends GenericDto<Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    //IB_KORISNIK_RACUN_USLUGA fields
    private Integer sifraIBKorisnikRacun;//ib_korisnik_racun_usluga.ibrac_ibu sifra ib_korisnik_racun (vanjski kljuc na tablicu ib_korisnik_racun)
    private String sifraIBUsluga;//ib_korisnik_racun_usluga.ibusl_ibu sifra ib_usluga (vanjski kljuc na tablicib_usluga)

    private String opisUsluge;
    private String tip;

    private Boolean ovlast;
    //constructors
    public WebKorisnikRacunUslugaDto() {
	super();
    }

    public WebKorisnikRacunUslugaDto(Integer sifra) {
	super(sifra);
    }

    public WebKorisnikRacunUslugaDto(boolean isFirstCall) {
	super(isFirstCall);
    }

    public WebKorisnikRacunUslugaDto(Integer sifra, Integer sifraIBKorisnikRacun, String sifraIBUsluga) {
	super(sifra);
	this.sifraIBKorisnikRacun = sifraIBKorisnikRacun;
	this.sifraIBUsluga = sifraIBUsluga;
    }

    //getters & setters
    @Override
    @IFilter(entityField = "sifra_ibu")
    public Integer getSifra() {
	return super.getSifra();
    }

    @IFilter(entityField = "ibrac_ibu")
    public Integer getSifraIBKorisnikRacun() {
	return sifraIBKorisnikRacun;
    }

    public void setSifraIBKorisnikRacun(Integer sifraIBKorisnikRacun) {
	this.sifraIBKorisnikRacun = sifraIBKorisnikRacun;
    }

    @IFilter(entityField = "ibusl_ibu")
    public String getSifraIBUsluga() {
	return sifraIBUsluga;
    }

    public void setSifraIBUsluga(String sifraIBUsluga) {
	this.sifraIBUsluga = sifraIBUsluga;
    }

    public String getOpisUsluge() {
	return opisUsluge;
    }

    public void setOpisUsluge(String opisUsluge) {
	this.opisUsluge = opisUsluge;
    }

    public String getTip() {
	return tip;
    }

    public void setTip(String tip) {
	this.tip = tip;
    }

    public Boolean getOvlast() {
        return ovlast;
    }

    public void setOvlast(Boolean ovlast) {
        this.ovlast = ovlast;
    }
}
