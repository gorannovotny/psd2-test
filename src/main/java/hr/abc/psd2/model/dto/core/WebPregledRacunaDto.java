package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.model.dto.GenericDto;
import hr.abc.psd2.util.GenericBassxConstants;
import hr.abc.psd2.util.IbanFunctions;
//import hr.abit.b3.biz.ib.util.AuthenticationUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 *
 * @author flegar
 */
public class WebPregledRacunaDto extends GenericDto<Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer sifraPartije;
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

    private List<WebKorisnikRacunUslugaDto> listKorisnikUsluge = new ArrayList<>();

    public WebPregledRacunaDto() {
	super();
    }

    public Integer getSifraPartije() {
	return sifraPartije;
    }

    public void setSifraPartije(Integer sifraPartije) {
	this.sifraPartije = sifraPartije;
    }

    public String getBrojPartije() {
	return brojPartije;
    }

    public void setBrojPartije(String brojPartije) {
	this.brojPartije = brojPartije;
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

    public String getValutaOznakaHrk2Letters() {
        return valutaOznaka != null && valutaOznaka.equals("HRK") ? "kn" : valutaOznaka;
    }

    public void setValutaOznaka(String valutaOznaka) {
	this.valutaOznaka = valutaOznaka;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
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

    public void setNeotpisano(BigDecimal nepotpisano) {
	this.nepotpisano = nepotpisano;
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

    public String getMaticniBroj() {
	return maticniBroj;
    }

    public void setMaticniBroj(String maticniBroj) {
	this.maticniBroj = maticniBroj;
    }

    public Integer getSifraKorisnikRacun() {
	return sifraKorisnikRacun;
    }

    public void setSifraKorisnikRacun(Integer sifraKorisnikRacun) {
	this.sifraKorisnikRacun = sifraKorisnikRacun;
    }

    public List<WebKorisnikRacunUslugaDto> getListKorisnikUsluge() {
	return listKorisnikUsluge;
    }

    public void setListKorisnikUsluge(List<WebKorisnikRacunUslugaDto> listKorisnikUsluge) {
	    this.listKorisnikUsluge = listKorisnikUsluge;
    }

    public Integer getSifraIbKorisnika() {
        return sifraIbKorisnika;
    }

    public void setSifraIbKorisnika(Integer sifraIbKorisnika) {
        this.sifraIbKorisnika = sifraIbKorisnika;
    }

	public String getNazivVlasnikaRacuna() {
		return nazivVlasnikaRacuna;
	}

	public void setNazivVlasnikaRacuna(String nazivVlasnikaRacuna) {
		this.nazivVlasnikaRacuna = nazivVlasnikaRacuna;
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
        //nazivTipaPartije = AuthenticationUtils.resolveNazivRacuna(this.brojPartije, this.valuta, this.sifraProizvoda, this.valutaOznaka);
        nazivTipaPartije = null; // TODO Ivana
        return nazivTipaPartije;
    }
    /*
	public String getNazivTipaPartije() {
        if(this.getBrojPartije() != null){
            switch(this.brojPartije.substring(0,2)){
                case "11":
                    nazivTipaPartije="Poslovni račun KN";
                    if(this.valuta != null){
                        if(!this.valuta.equals(GenericBassxConstants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY)){
                            nazivTipaPartije = "Poslovni račun "+this.valutaOznaka;
                        }
                    }
                    break;
                case "13":
                    nazivTipaPartije="Račun za posebne namjene KN";
                    if(this.valuta != null){
                        if(!this.valuta.equals(GenericBassxConstants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY)){
                            nazivTipaPartije = "Račun za posebne namjene "+this.valutaOznaka;
                        }
                    }
                    //if(this.nazivVlasnikaRacuna != null && this.nazivVlasnikaRacuna.contains("SZP")){
                    //    nazivTipaPartije = this.nazivVlasnikaRacuna;
                    //}
                    break;
                case "14":
                    nazivTipaPartije="Račun organizacijskog dijela KN";
                    if(this.valuta != null){
                        if(!this.valuta.equals(GenericBassxConstants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY)){
                            nazivTipaPartije = "Račun organizacijskog dijela "+this.valutaOznaka;
                        }
                    }
                    break;
                case "15":
                    nazivTipaPartije="Račun za posebne namjene KN";
                    if(this.valuta != null){
                        if(!this.valuta.equals(GenericBassxConstants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY)){
                            nazivTipaPartije = "Račun za posebne namjene "+this.valutaOznaka;
                        }
                    }
                    break;
                case "17":
                    nazivTipaPartije="Račun za posebne namjene KN";
                    if(this.valuta != null){
                        if(!this.valuta.equals(GenericBassxConstants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY)){
                            nazivTipaPartije = "Račun za posebne namjene "+this.valutaOznaka;
                        }
                    }
                    break;
                case "18":
                    nazivTipaPartije="Transakcijski račun KN";
                    if(this.valuta != null){
                        if(!this.valuta.equals(GenericBassxConstants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY)){
                            nazivTipaPartije = "Transakcijski račun "+this.valutaOznaka;
                        }
                    }
                    break;
                case "31":
                    nazivTipaPartije="Žiro račun KN";
                    if(this.valuta != null){
                        if(!this.valuta.equals(GenericBassxConstants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY)){
                            nazivTipaPartije = "Žiro račun "+this.valutaOznaka;
                        }
                    }
                    break;
                case "32":
                    if (this.sifraProizvoda != null && this.sifraProizvoda.compareTo(167) == 0) { //TODO - konstanta ?
                        nazivTipaPartije = "Dječja štednja KN";
                        if(this.valuta != null){
                            if(!this.valuta.equals(GenericBassxConstants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY)){
                                nazivTipaPartije = "Dječja štednja "+this.valutaOznaka;
                            }
                        }
                    } else {
                        nazivTipaPartije = "Tekući račun KN";
                        if(this.valuta != null){
                            if(!this.valuta.equals(GenericBassxConstants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY)){
                                nazivTipaPartije = "Tekući račun "+this.valutaOznaka;
                            }
                        }
                    }
                    break;
                case "33":
                    if (this.sifraProizvoda != null && this.sifraProizvoda.compareTo(160) == 0) { //TODO - konstanta ?
                        nazivTipaPartije = "Štedni račun KN";
                    } else if (this.sifraProizvoda != null && this.sifraProizvoda.compareTo(161) == 0) { //TODO - konstanta ?
                        nazivTipaPartije = "Štedna knjižica KN";
                    }
                    break;
                case "35":
                    nazivTipaPartije="Račun za posebne namjene KN";
                    if(this.valuta != null){
                        if(!this.valuta.equals(GenericBassxConstants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY)){
                            nazivTipaPartije = "Račun za posebne namjene "+this.valutaOznaka;
                        }
                    }
                    break;
                case "73":
                    if(this.valuta != null){
                        if(!this.valuta.equals(GenericBassxConstants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY)){
                            if (this.sifraProizvoda != null && this.sifraProizvoda.compareTo(159) == 0) { //TODO - konstanta ?
                                nazivTipaPartije = "Štedna knjižica " + this.valutaOznaka;
                            }
                        }
                    }
                    break;
                case "75":
                    if(this.valuta != null){
                        if(!this.valuta.equals(GenericBassxConstants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY)){
                            nazivTipaPartije = "Oročena štednja " + this.valutaOznaka;
                        }
                    }
                    break;
                case "85":
                    nazivTipaPartije="Oročena štednja KN";
                    break;
            }
        }
		return nazivTipaPartije;
	}
	*/

	public void setNazivTipaPartije(String nazivTipaPartije) {
		this.nazivTipaPartije = nazivTipaPartije;
	}

    public String getNazivRacuna() {
        return nazivRacuna;
    }

    public void setNazivRacuna(String nazivRacuna) {
        this.nazivRacuna = nazivRacuna;
    }

    public String getIbanStrukturirani(){
        String res = iban;
        if(iban != null && IbanFunctions.isIban(iban)){
            res = iban.substring(0,2) + " "+iban.substring(2,4)+" "+iban.substring(4,8)+" "+iban.substring(8,12)+" "+iban.substring(12,16)+" "+iban.substring(16,20)+" "+iban.substring(20);
        }
        return res;
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

    public static List<WebPregledRacunaDto> sortRacuniByVrstaRacuna(List<WebPregledRacunaDto> racuni) {
        Collections.sort(racuni, new Comparator<WebPregledRacunaDto>() {
            @Override
            public int compare(WebPregledRacunaDto arg0, WebPregledRacunaDto arg1) {
                int res = 0;
                String grupaRacuna0 = "", grupaRacuna1 = "";
                if(IbanFunctions.isIban(arg0.getIban()) && IbanFunctions.isIban(arg1.getIban())){
                	grupaRacuna0 = arg0.getIban().substring(11);
                	grupaRacuna1 = arg1.getIban().substring(11);
                }else if(!IbanFunctions.isIban(arg0.getIban()) && IbanFunctions.isIban(arg1.getIban())){
                	grupaRacuna0 = arg0.getIban().substring(2);
                	grupaRacuna1 = arg1.getIban().substring(11);
                }else if(IbanFunctions.isIban(arg0.getIban()) && !IbanFunctions.isIban(arg1.getIban())){
                	grupaRacuna0 = arg0.getIban().substring(11);
                	grupaRacuna1 = arg1.getIban().substring(2);
                }
                // 32 uvijek na vrhu
                if (grupaRacuna0.startsWith("32") && grupaRacuna1.startsWith("32")) { // da bi se valute poredile
                	res = 0;
                } else if (grupaRacuna0.startsWith("32")) {
                	res = -1;
                } else if (grupaRacuna1.startsWith("32")) {
                	res = 1;
                }
                // inače po redu
                if (res == 0) {
                	res = grupaRacuna0.compareTo(grupaRacuna1);
                }
                if(res == 0){
                    res = arg0.getValuta().equals(GenericBassxConstants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY) ? -1
                        : arg1.getValuta().equals(GenericBassxConstants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY) ? 1
                        : arg0.getValutaRedosljed() == null && arg1.getValutaRedosljed() == null ? arg0.getValuta().compareTo(arg1.getValuta())
                        : arg0.getValutaRedosljed() == null ? 1
                        : arg1.getValutaRedosljed() == null ? -1
                        : arg0.getValutaRedosljed().compareTo(arg1.getValutaRedosljed());
                }
                return res;
            }
        });
        return racuni;
    }

    public static List<WebPregledRacunaDto> sortRacuniByValutaHrkFirst(List<WebPregledRacunaDto> banke) {
        Collections.sort(banke, new Comparator<WebPregledRacunaDto>() {
            @Override
            public int compare(WebPregledRacunaDto arg0, WebPregledRacunaDto arg1) {
                int result = arg0.getValuta().equals(GenericBassxConstants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY) ? -1
                        : arg1.getValuta().equals(GenericBassxConstants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY) ? 1
                        : arg0.getValutaRedosljed() == null && arg1.getValutaRedosljed() == null ? arg0.getValuta().compareTo(arg1.getValuta())
                        : arg0.getValutaRedosljed() == null ? 1
                        : arg1.getValutaRedosljed() == null ? -1
                        : arg0.getValutaRedosljed().compareTo(arg1.getValutaRedosljed());
                return result;
            }
        });
        return banke;
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
        return sifraProizvoda != null && sifraProizvoda.compareTo(167) == 0;
    }

    public void setDjecjaStednja(Boolean djecjaStednja) {
        this.djecjaStednja = djecjaStednja;
    }

    public BigDecimal getDopustenoPrekoracenje() {
		return dopustenoPrekoracenje;
	}

	public void setDopustenoPrekoracenje(BigDecimal dopustenoPrekoracenje) {
		this.dopustenoPrekoracenje = dopustenoPrekoracenje;
	}

	public Date getDopustenoPrekoracenjeDatumDo() {
		return dopustenoPrekoracenjeDatumDo;
	}

	public void setDopustenoPrekoracenjeDatumDo(Date dopustenoPrekoracenjeDatumDo) {
		this.dopustenoPrekoracenjeDatumDo = dopustenoPrekoracenjeDatumDo;
	}

	public void setNepotpisano(BigDecimal nepotpisano) {
		this.nepotpisano = nepotpisano;
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

	public Integer getOdbijeniNalozi() {
		return odbijeniNalozi;
	}

	public void setOdbijeniNalozi(Integer odbijeniNalozi) {
		this.odbijeniNalozi = odbijeniNalozi;
	}
	
}
