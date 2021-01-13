package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Transfer object za knjižni nalog.
 * 
 * @author Matija Hlapčić
 */
public class NalogKnjizniDto extends NalogDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// fields
	private Date datumKnjizenja;
	private Date datumValute;
	private Date vrijemeKnjizenja;
	private Short prioritet;
	private Integer sifraNalogaGlavneKnjige;
	private List<PrometDto> listaPrometa;
	// pomoćna polja
	private Integer sifraKlaseNaloga; // šifra klase naloga je povezana u knjižilici s pravilima knjiženja za odgovarajuću transakciju
	private boolean checkRaspolozivo = false; // da li se u osnovnom algoritmu provjerava raspoloživo po dijelu
	private Boolean allowEmptyPrometList = false; // da li se u osnovnom algoritmu provjerava postojanje prometa
	private Integer sifraPosebnePartijeKnjizenja; // dodatna partija knjiženja koja se koristi za specifične svrhe - npr. partija s koje se radi pokriće, partija na koju se knjiži tečajna razlika, partija s koje se naplaćuje naknada/kamata, itd.
	private String grupaKlaseNaloga;
	private String sifraValute, sifraValutePokrica; // pomoćno polje za valutu - koristi se kod formi koje rade s knjižnim nalogom, ali korisno je imati valutu 
	private BigDecimal iznos; // pomoćno polje za iznos - koristi se kod formi koje rade s knjižnim nalogom, ali korisno je imati iznos
	private BigDecimal iznosNaknade; // pomoćno polje za iznos naknade - koristi se kod formi koje rade s knjižnim nalogom, a imaju iznos naknade
	private String sifraKategorijaNamjene;
	// IP polja
	private Date paymentDate, postingDate;
	private Integer id;
	
	// constructors
	public NalogKnjizniDto() {
		super();
	}
	
	public NalogKnjizniDto(boolean isFirstCall) {
		super(isFirstCall);
	}

	public NalogKnjizniDto(Integer sifra, Date vrijemeNastanka, String svrha, String status, Integer sifraLikvidatora, Integer sifraModula, String sifraPoslovnice, Integer sifraTipaNaloga, Integer version, Integer sifraNalogaStorna, String oznakaStorno, Integer sifraNalogaPrethodnika,
                           Date datumKnjizenja, Date datumValute, Date vrijemeKnjizenja, Short prioritet, Integer sifraNalogaGlavneKnjige, List<PrometDto> listaPrometa) {
		super(sifra, vrijemeNastanka, svrha, status, sifraLikvidatora, sifraModula, sifraPoslovnice, sifraTipaNaloga, version, sifraNalogaStorna, oznakaStorno, sifraNalogaPrethodnika);
		this.datumKnjizenja = datumKnjizenja;
		this.datumValute = datumValute;
		this.vrijemeKnjizenja = vrijemeKnjizenja;
		this.prioritet = prioritet;
		this.sifraNalogaGlavneKnjige = sifraNalogaGlavneKnjige;
		if (this.getListaPrometa() == null) {
			this.listaPrometa = new ArrayList<>();
		}
	}

	// getters & setters
	@Override
	@IFilter(entityField="nalogKnjizni.sifra_nal")
	public Integer getSifra() {
		return super.getSifra();
	}
	
	@IFilter(entityField="nalogKnjizni.datkn_nal")
	public Date getDatumKnjizenja() {
		if (postingDate != null) {
			datumKnjizenja = postingDate;
		}
		return datumKnjizenja;
	}

	public void setDatumKnjizenja(Date datumKnjizenja) {
		this.datumKnjizenja = datumKnjizenja;
	}

	@IFilter(entityField="nalogKnjizni.datva_nal")
	public Date getDatumValute() {
		if (paymentDate != null) {
			datumValute = paymentDate;
		}
		return datumValute;
	}

	public void setDatumValute(Date datumValute) {
		this.datumValute = datumValute;
	}

	@IFilter(entityField="nalogKnjizni.vrikn_nal")
	public Date getVrijemeKnjizenja() {
		return vrijemeKnjizenja;
	}

	public void setVrijemeKnjizenja(Date vrijemeKnjizenja) {
		this.vrijemeKnjizenja = vrijemeKnjizenja;
	}

	@IFilter(entityField="nalogKnjizni.prior_nal")
	public Short getPrioritet() {
		return prioritet;
	}

	public void setPrioritet(Short prioritet) {
		this.prioritet = prioritet;
	}

	public Integer getSifraNalogaGlavneKnjige() {
		return sifraNalogaGlavneKnjige;
	}

	public void setSifraNalogaGlavneKnjige(Integer sifraNalogaGlavneKnjige) {
		this.sifraNalogaGlavneKnjige = sifraNalogaGlavneKnjige;
	}

	public List<PrometDto> getListaPrometa() {
		if (listaPrometa == null) {
			listaPrometa = new ArrayList<>();
		}
		return listaPrometa;
	}

	public void setListaPrometa(List<PrometDto> listaPrometa) {
		this.listaPrometa = listaPrometa;
	}

	public Integer getSifraKlaseNaloga() {
		return sifraKlaseNaloga;
	}

	public void setSifraKlaseNaloga(Integer sifraKlaseNaloga) {
		this.sifraKlaseNaloga = sifraKlaseNaloga;
	}

	public boolean isCheckRaspolozivo() {
		return checkRaspolozivo;
	}

	public void setCheckRaspolozivo(boolean checkRaspolozivo) {
		this.checkRaspolozivo = checkRaspolozivo;
	}

	public Integer getSifraPosebnePartijeKnjizenja() {
		return sifraPosebnePartijeKnjizenja;
	}

	public void setSifraPosebnePartijeKnjizenja(Integer sifraPosebnePartijeKnjizenja) {
		this.sifraPosebnePartijeKnjizenja = sifraPosebnePartijeKnjizenja;
	}

	@IFilter(entityField="klasaNaloga.grupa_kna")
	public String getGrupaKlaseNaloga() {
		return grupaKlaseNaloga;
	}

	public void setGrupaKlaseNaloga(String grupaKlaseNaloga) {
		this.grupaKlaseNaloga = grupaKlaseNaloga;
	}

	@IFilter(entityField = "valuta.sifra_val")
	public String getSifraValute() {
		return sifraValute;
	}

	public void setSifraValute(String sifraValute) {
		this.sifraValute = sifraValute;
	}

	@IFilter(entityField = "platniNalog.iznos_trn")
	public BigDecimal getIznos() {
		return iznos;
	}

	public void setIznos(BigDecimal iznos) {
		this.iznos = iznos;
	}

	@IFilter(entityField = "platniNalog.valpk_trn")
	public String getSifraValutePokrica() {
		return sifraValutePokrica;
	}

	public void setSifraValutePokrica(String sifraValutePokrica) {
		this.sifraValutePokrica = sifraValutePokrica;
	}

	public BigDecimal getIznosNaknade() {
		return iznosNaknade;
	}

	public void setIznosNaknade(BigDecimal iznosNaknade) {
		this.iznosNaknade = iznosNaknade;
	}

	public Boolean getAllowEmptyPrometList() {
		return allowEmptyPrometList;
	}

	public void setAllowEmptyPrometList(Boolean allowEmptyPrometList) {
		this.allowEmptyPrometList = allowEmptyPrometList;
	}

    public String getSifraKategorijaNamjene() {
        return sifraKategorijaNamjene;
    }

    public void setSifraKategorijaNamjene(String sifraKategorijaNamjene) {
        this.sifraKategorijaNamjene = sifraKategorijaNamjene;
    }

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Date getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
