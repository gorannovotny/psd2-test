package hr.abc.psd2.model.dto.core;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Psd2WebPregledRacunaDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String sifraPartije;
    private Integer sifraKorisnikRacun;
    private String brojPartije;
    private Integer sifraIbKorisnika;
    private String maticniBroj;
    private String iban;
    private String valuta;
    private String valutaOznaka;
    private String valutaNaziv;
    private Integer valutaRedosljed;
    private String status;
    private Integer sifraKlasePartije;
    private String nazivKlasePartije;
    private BigDecimal stanje;
    private BigDecimal raspolozivo;
    private BigDecimal nepotpisano;
    private BigDecimal dopustenoPrekoracenje, neproknjizenoKarticno, naloziObrada, naloziNajavaT1, naloziNajava;
    private String nazivVlasnikaRacuna;
    private String nazivKomitenta;
    private String oibKomitenta;
    private String opisStatusa;
    private String nazivTipaPartije;
    private String nazivRacuna;
    private Integer sifraVlasnikaRacuna;
    private Integer sifraProizvoda;
    private Integer odbijeniNalozi;
    private Date dopustenoPrekoracenjeDatumDo;

    private Boolean mozeNoviNalog = Boolean.FALSE;
    private Boolean mozePotpis = Boolean.FALSE;
    private Boolean mozePregledNaloga = Boolean.FALSE;
    private Boolean mozePregledPrometa = Boolean.FALSE;
    private Boolean mozePregledRacuna = Boolean.FALSE;
    private Boolean djecjaStednja = Boolean.FALSE;

    public Psd2WebPregledRacunaDto(String sifraPartije, String valuta, String nazivTipaPartije, String iban) {
        this.sifraPartije=sifraPartije;
        this.valuta = valuta;
        this.nazivTipaPartije = nazivTipaPartije;
        this.iban=iban;
    }

    public Psd2WebPregledRacunaDto() {
    }


    public String getSifraPartije() {
        return sifraPartije;
    }

    public void setSifraPartije(String sifraPartije) {
        this.sifraPartije = sifraPartije;
    }

    public Integer getSifraKorisnikRacun() {
        return sifraKorisnikRacun;
    }

    public void setSifraKorisnikRacun(Integer sifraKorisnikRacun) {
        this.sifraKorisnikRacun = sifraKorisnikRacun;
    }

    public String getBrojPartije() {
        return brojPartije;
    }

    public void setBrojPartije(String brojPartije) {
        this.brojPartije = brojPartije;
    }

    public Integer getSifraIbKorisnika() {
        return sifraIbKorisnika;
    }

    public void setSifraIbKorisnika(Integer sifraIbKorisnika) {
        this.sifraIbKorisnika = sifraIbKorisnika;
    }

    public String getMaticniBroj() {
        return maticniBroj;
    }

    public void setMaticniBroj(String maticniBroj) {
        this.maticniBroj = maticniBroj;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public String getValutaOznaka() {
        return valutaOznaka;
    }

    public void setValutaOznaka(String valutaOznaka) {
        this.valutaOznaka = valutaOznaka;
    }

    public String getValutaNaziv() {
        return valutaNaziv;
    }

    public void setValutaNaziv(String valutaNaziv) {
        this.valutaNaziv = valutaNaziv;
    }

    public Integer getValutaRedosljed() {
        return valutaRedosljed;
    }

    public void setValutaRedosljed(Integer valutaRedosljed) {
        this.valutaRedosljed = valutaRedosljed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSifraKlasePartije() {
        return sifraKlasePartije;
    }

    public void setSifraKlasePartije(Integer sifraKlasePartije) {
        this.sifraKlasePartije = sifraKlasePartije;
    }

    public String getNazivKlasePartije() {
        return nazivKlasePartije;
    }

    public void setNazivKlasePartije(String nazivKlasePartije) {
        this.nazivKlasePartije = nazivKlasePartije;
    }

    public BigDecimal getStanje() {
        return stanje;
    }

    public void setStanje(BigDecimal stanje) {
        this.stanje = stanje;
    }

    public BigDecimal getRaspolozivo() {
        return raspolozivo;
    }

    public void setRaspolozivo(BigDecimal raspolozivo) {
        this.raspolozivo = raspolozivo;
    }

    public BigDecimal getNepotpisano() {
        return nepotpisano;
    }

    public void setNepotpisano(BigDecimal nepotpisano) {
        this.nepotpisano = nepotpisano;
    }

    public BigDecimal getDopustenoPrekoracenje() {
        return dopustenoPrekoracenje;
    }

    public void setDopustenoPrekoracenje(BigDecimal dopustenoPrekoracenje) {
        this.dopustenoPrekoracenje = dopustenoPrekoracenje;
    }

    public BigDecimal getNeproknjizenoKarticno() {
        return neproknjizenoKarticno;
    }

    public void setNeproknjizenoKarticno(BigDecimal neproknjizenoKarticno) {
        this.neproknjizenoKarticno = neproknjizenoKarticno;
    }

    public BigDecimal getNaloziObrada() {
        return naloziObrada;
    }

    public void setNaloziObrada(BigDecimal naloziObrada) {
        this.naloziObrada = naloziObrada;
    }

    public BigDecimal getNaloziNajavaT1() {
        return naloziNajavaT1;
    }

    public void setNaloziNajavaT1(BigDecimal naloziNajavaT1) {
        this.naloziNajavaT1 = naloziNajavaT1;
    }

    public BigDecimal getNaloziNajava() {
        return naloziNajava;
    }

    public void setNaloziNajava(BigDecimal naloziNajava) {
        this.naloziNajava = naloziNajava;
    }

    public String getNazivVlasnikaRacuna() {
        return nazivVlasnikaRacuna;
    }

    public void setNazivVlasnikaRacuna(String nazivVlasnikaRacuna) {
        this.nazivVlasnikaRacuna = nazivVlasnikaRacuna;
    }

    public String getNazivKomitenta() {
        return nazivKomitenta;
    }

    public void setNazivKomitenta(String nazivKomitenta) {
        this.nazivKomitenta = nazivKomitenta;
    }

    public String getOibKomitenta() {
        return oibKomitenta;
    }

    public void setOibKomitenta(String oibKomitenta) {
        this.oibKomitenta = oibKomitenta;
    }

    public String getOpisStatusa() {
        return opisStatusa;
    }

    public void setOpisStatusa(String opisStatusa) {
        this.opisStatusa = opisStatusa;
    }

    public String getNazivTipaPartije() {
        return nazivTipaPartije;
    }

    public void setNazivTipaPartije(String nazivTipaPartije) {
        this.nazivTipaPartije = nazivTipaPartije;
    }

    public String getNazivRacuna() {
        return nazivRacuna;
    }

    public void setNazivRacuna(String nazivRacuna) {
        this.nazivRacuna = nazivRacuna;
    }

    public Integer getSifraVlasnikaRacuna() {
        return sifraVlasnikaRacuna;
    }

    public void setSifraVlasnikaRacuna(Integer sifraVlasnikaRacuna) {
        this.sifraVlasnikaRacuna = sifraVlasnikaRacuna;
    }

    public Integer getSifraProizvoda() {
        return sifraProizvoda;
    }

    public void setSifraProizvoda(Integer sifraProizvoda) {
        this.sifraProizvoda = sifraProizvoda;
    }

    public Integer getOdbijeniNalozi() {
        return odbijeniNalozi;
    }

    public void setOdbijeniNalozi(Integer odbijeniNalozi) {
        this.odbijeniNalozi = odbijeniNalozi;
    }

    public Date getDopustenoPrekoracenjeDatumDo() {
        return dopustenoPrekoracenjeDatumDo;
    }

    public void setDopustenoPrekoracenjeDatumDo(Date dopustenoPrekoracenjeDatumDo) {
        this.dopustenoPrekoracenjeDatumDo = dopustenoPrekoracenjeDatumDo;
    }

    public Boolean getMozeNoviNalog() {
        return mozeNoviNalog;
    }

    public void setMozeNoviNalog(Boolean mozeNoviNalog) {
        this.mozeNoviNalog = mozeNoviNalog;
    }

    public Boolean getMozePotpis() {
        return mozePotpis;
    }

    public void setMozePotpis(Boolean mozePotpis) {
        this.mozePotpis = mozePotpis;
    }

    public Boolean getMozePregledNaloga() {
        return mozePregledNaloga;
    }

    public void setMozePregledNaloga(Boolean mozePregledNaloga) {
        this.mozePregledNaloga = mozePregledNaloga;
    }

    public Boolean getMozePregledPrometa() {
        return mozePregledPrometa;
    }

    public void setMozePregledPrometa(Boolean mozePregledPrometa) {
        this.mozePregledPrometa = mozePregledPrometa;
    }

    public Boolean getMozePregledRacuna() {
        return mozePregledRacuna;
    }

    public void setMozePregledRacuna(Boolean mozePregledRacuna) {
        this.mozePregledRacuna = mozePregledRacuna;
    }

    public Boolean getDjecjaStednja() {
        return djecjaStednja;
    }

    public void setDjecjaStednja(Boolean djecjaStednja) {
        this.djecjaStednja = djecjaStednja;
    }
}
