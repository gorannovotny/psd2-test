package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.model.dto.GenericDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class NalogDto extends GenericDto<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date vrijemeNastanka; // vrijeme nastanka naloga
	private String svrha; // opis naloga
	private String status; // status naloga
	private Integer sifraLikvidatora; // likvidator koji je napravio nalog
	private Integer sifraModula; // aplikacija u kojoj je napravljen nalog
	private String nazivModula;
	private String sifraPoslovnice; // organizacijska jedinica ili poslovnica u kojoj je napravljen nalog
	private String nazivPoslovnice;
	private Integer sifraTipaNaloga; // tip naloga koji identificira tip transakcije
	private String nazivTipaNaloga;
	private Integer version; // verzioniranje entiteta
	private boolean isPeglaUkljucena = false; // da li će se kod verifikacije naloga peglati domicilni iznosi prometa koji su moguće bili zadani prije datuma valute
	private BigDecimal iznosZaVerifikaciju; // samo ime kaže :) iznos koji metoda za verifikaciju provjerava ako je napunjen u odnosu na parametrizaciju u bazi podataka
	private boolean ostaviPromete = false; // da li se prometi kod povlačenja ostavljaju
	private Integer sifraNalogaStorna; // šifra prema referenci naloga storna
	private String oznakaStorno; // oznaka da se radi o nalogu storno
	private Integer sifraNalogaPrethodnika; // šifra naloga prethodnika
	private String loginLikvidatora;

	// constructors
	public NalogDto() {
		super();
	}
	
	public NalogDto(Integer sifra) {
		super(sifra);
	}

	public NalogDto(boolean isFirstCall) {
		super(isFirstCall);
	}

	public NalogDto(Integer sifra, Date vrijemeNastanka, String svrha, String status, Integer sifraLikvidatora, Integer sifraModula, String sifraPoslovnice, Integer sifraTipaNaloga, Integer version, Integer sifraNalogaStorna, String oznakaStorno, Integer sifraNalogaPrethodnika) {
		super(sifra);
		this.vrijemeNastanka = vrijemeNastanka;
		this.svrha = svrha;
		this.status = status;
		this.sifraLikvidatora = sifraLikvidatora;
		this.sifraModula = sifraModula;
		this.sifraPoslovnice = sifraPoslovnice;
		this.sifraTipaNaloga = sifraTipaNaloga;
		this.version = version;
		this.sifraNalogaStorna = sifraNalogaStorna;
		this.oznakaStorno = oznakaStorno;
		this.sifraNalogaPrethodnika = sifraNalogaPrethodnika;
	}
	
	public NalogDto(Date vrijemeNastanka, String svrha, String status, Integer sifraLikvidatora, Integer sifraModula, String sifraPoslovnice, Integer sifraTipaNaloga) {
		super();
		this.vrijemeNastanka = vrijemeNastanka;
		this.svrha = svrha;
		this.status = status;
		this.sifraLikvidatora = sifraLikvidatora;
		this.sifraModula = sifraModula;
		this.sifraPoslovnice = sifraPoslovnice;
		this.sifraTipaNaloga = sifraTipaNaloga;
	}

	// getters & setters
	@Override
	@IFilter(entityField="nalog.sifra_nal")
	public Integer getSifra() {
		return super.getSifra();
	}
	
	@IFilter(entityField="nalog.vrije_nal")
	public Date getVrijemeNastanka() {
		return vrijemeNastanka;
	}

	public void setVrijemeNastanka(Date vrijemeNastanka) {
		this.vrijemeNastanka = vrijemeNastanka;
	}

	@IFilter(entityField="nalog.svrha_nal")
	public String getSvrha() {
		return svrha;
	}

	public void setSvrha(String svrha) {
		this.svrha = svrha;
	}

	@IFilter(entityField="nalog.statu_nal")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@IFilter(entityField="likvidator.sifra_lik")
	public Integer getSifraLikvidatora() {
		return sifraLikvidatora;
	}

	public void setSifraLikvidatora(Integer sifraLikvidatora) {
		this.sifraLikvidatora = sifraLikvidatora;
	}

	@IFilter(entityField="nalog.aplik_nal")
	public Integer getSifraModula() {
		return sifraModula;
	}

	public void setSifraModula(Integer sifraModula) {
		this.sifraModula = sifraModula;
	}

	@IFilter(entityField="nalog.pojed_nal")
	public String getSifraPoslovnice() {
		return sifraPoslovnice;
	}

	public void setSifraPoslovnice(String sifraPoslovnice) {
		this.sifraPoslovnice = sifraPoslovnice;
	}

	@IFilter(entityField="tipNaloga.sifra_pod")
	public Integer getSifraTipaNaloga() {
		return sifraTipaNaloga;
	}
	
	@IFilter(entityField="nalog.stornNal")	
	public Integer getSifraNalogaStorna() {
		return sifraNalogaStorna;
	}

	public void setSifraNalogaStorna(Integer sifraNalogaStorna) {
		this.sifraNalogaStorna = sifraNalogaStorna;
	}

	@IFilter(entityField="nalog.stozn_nal")	
	public String getOznakaStorno() {
		return oznakaStorno;
	}

	public void setOznakaStorno(String oznakaStorno) {
		this.oznakaStorno = oznakaStorno;
	}
	
	public void setSifraTipaNaloga(Integer sifraTipaNaloga) {
		this.sifraTipaNaloga = sifraTipaNaloga;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public boolean isPeglaUkljucena() {
		return isPeglaUkljucena;
	}

	public void setPeglaUkljucena(boolean isPeglaUkljucena) {
		this.isPeglaUkljucena = isPeglaUkljucena;
	}

	@IFilter(entityField = "nalogDevizniCommon.iznos_nal")
	public BigDecimal getIznosZaVerifikaciju() {
		return iznosZaVerifikaciju;
	}

	public void setIznosZaVerifikaciju(BigDecimal iznosZaVerifikaciju) {
		this.iznosZaVerifikaciju = iznosZaVerifikaciju;
	}

	public boolean isOstaviPromete() {
		return ostaviPromete;
	}

	public void setOstaviPromete(boolean ostaviPromete) {
		this.ostaviPromete = ostaviPromete;
	}

	public String getNazivTipaNaloga() {
		return nazivTipaNaloga;
	}

	public void setNazivTipaNaloga(String nazivTipaNaloga) {
		this.nazivTipaNaloga = nazivTipaNaloga;
	}

	@IFilter(entityField="likvidator.login_lik")
	public String getLoginLikvidatora() {
		return loginLikvidatora;
	}

	public void setLoginLikvidatora(String loginLikvidatora) {
		this.loginLikvidatora = loginLikvidatora;
	}

	@IFilter(entityField="pojed.naziv_poj")
	public String getNazivPoslovnice() {
		return nazivPoslovnice;
	}

	public void setNazivPoslovnice(String nazivPoslovnice) {
		this.nazivPoslovnice = nazivPoslovnice;
	}

	@IFilter(entityField="modul.naziv_mod")
	public String getNazivModula() {
		return nazivModula;
	}

	public void setNazivModula(String nazivModula) {
		this.nazivModula = nazivModula;
	}
	@IFilter(entityField="nalog.prena_nal")	
	public Integer getSifraNalogaPrethodnika() {
		return sifraNalogaPrethodnika;
	}

	public void setSifraNalogaPrethodnika(Integer sifraNalogaPrethodnika) {
		this.sifraNalogaPrethodnika = sifraNalogaPrethodnika;
	}

}
