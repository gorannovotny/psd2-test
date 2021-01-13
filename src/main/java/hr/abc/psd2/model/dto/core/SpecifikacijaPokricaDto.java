package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.model.dto.GenericDto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Specifikacija pokrića s IB-a i drugih kanala za pokriće doznake/raspored priljeva.
 * 
 * @author Mata
 */
public class SpecifikacijaPokricaDto extends GenericDto<Integer> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer sifraDeviznogNalogaNaloga;
	private Integer sifraKnjznogNaloga;
	private Integer sifraPartijeSpk;
	private String sifraTipTecajaSpk;
	private String sifraValutaSpk;
	private BigDecimal iznosValNalogSpk;	
	private BigDecimal iznosValPartijaSpk;
	private String vrstaSpk;
	private String referencaSpk;
	private String referencaPartijeSpk;
	private BigDecimal iznosDogTecajaSpk;
	private Integer redniBrojSpk;
	private String opisSpk;
	private String tipNaplateSpk;
	private String maticniBrojKomitentaSpk;
	private String nazivKomitentaSpk;
	
	public SpecifikacijaPokricaDto() {
		super();	
	}
	
	public SpecifikacijaPokricaDto(
			Integer sifra,
			Integer sifraDeviznogNalogaNaloga, 
			Integer sifraPartijeSpk,
			String sifraValutaSpk,
			String sifraTipTecajaSpk,
			BigDecimal iznosValNalogSpk,
			BigDecimal iznosValPartijaSpk,
			String vrstaSpk,
			String referencaSpk,
			String referencaPartijeSpk,
			BigDecimal iznosDogTecajaSpk,
			Integer redniBrojSpk,
			String opisSpk,
			String tipNaplateSpk,
			String maticniBrojKomitentaSpk,
			String nazivKomitentaSpk,
			Integer sifraKnjiznogNalogaNaloga) {
		super();	
		setSifra(sifra);
		this.sifraDeviznogNalogaNaloga = sifraDeviznogNalogaNaloga;
		this.sifraPartijeSpk = sifraPartijeSpk;
		this.sifraTipTecajaSpk = sifraTipTecajaSpk;
		this.sifraValutaSpk = sifraValutaSpk;
		this.iznosValNalogSpk = iznosValNalogSpk;
		this.iznosValPartijaSpk = iznosValPartijaSpk;
		this.vrstaSpk = vrstaSpk;
		this.referencaSpk = referencaSpk;
		this.referencaPartijeSpk = referencaPartijeSpk;
		this.iznosDogTecajaSpk = iznosDogTecajaSpk;
		this.redniBrojSpk = redniBrojSpk;
		this.opisSpk = opisSpk;
		this.tipNaplateSpk = tipNaplateSpk;
		this.maticniBrojKomitentaSpk = maticniBrojKomitentaSpk;
		this.nazivKomitentaSpk = nazivKomitentaSpk;
		this.sifraKnjznogNaloga  = sifraKnjiznogNalogaNaloga;
	}
	
	public SpecifikacijaPokricaDto(boolean isFirstCall) {
		super(isFirstCall);
	}

	@Override
	@IFilter(entityField="specifikacijaPokrica.sifra_spk")
	public Integer getSifra() {
		return super.getSifra();
	}
	
	@IFilter(entityField="specifikacijaPokrica.nalog_spk")
	public Integer getSifraDeviznogNalogaNaloga() {
		return sifraDeviznogNalogaNaloga;
	}
	
	public void setSifraDeviznogNalogaNaloga(Integer sifraDeviznogNalogaNaloga) {
		this.sifraDeviznogNalogaNaloga = sifraDeviznogNalogaNaloga;
	}
	
	@IFilter(entityField="specifikacijaPokrica.spart_spk")
	public Integer getSifraPartijeSpk() {
		return sifraPartijeSpk;
	}


	public void setSifraPartijeSpk(Integer sifraPartijeSpk) {
		this.sifraPartijeSpk = sifraPartijeSpk;
	}
	
	@IFilter(entityField="specifikacijaPokrica.tipte_spk")
	public String getSifraTipTecajaSpk() {
		return sifraTipTecajaSpk;
	}

	public void setSifraTipTecajaSpk(String sifraTipTecajaSpk) {
		this.sifraTipTecajaSpk = sifraTipTecajaSpk;
	}
	
	@IFilter(entityField="specifikacijaPokrica.sifva_spk")
	public String getSifraValutaSpk() {
		return sifraValutaSpk;
	}

	public void setSifraValutaSpk(String sifraValutaSpk) {
		this.sifraValutaSpk = sifraValutaSpk;
	}
	
	@IFilter(entityField="specifikacijaPokrica.iznvn_spk")
	public BigDecimal getIznosValNalogSpk() {
		return iznosValNalogSpk;
	}

	public void setIznosValNalogSpk(BigDecimal iznosValNalogSpk) {
		this.iznosValNalogSpk = iznosValNalogSpk;
	}
	
	@IFilter(entityField="specifikacijaPokrica.iznvp_spk")
	public BigDecimal getIznosValPartijaSpk() {
		return iznosValPartijaSpk;
	}

	public void setIznosValPartijaSpk(BigDecimal iznosValPartijaSpk) {
		this.iznosValPartijaSpk = iznosValPartijaSpk;
	}
	
	@IFilter(entityField="specifikacijaPokrica.vrsta_spk")
	public String getVrstaSpk() {
		return vrstaSpk;
	}

	public void setVrstaSpk(String vrstaSpk) {
		this.vrstaSpk = vrstaSpk;
	}
	
	@IFilter(entityField="specifikacijaPokrica.refer_spk")
	public String getReferencaSpk() {
		return referencaSpk;
	}

	public void setReferencaSpk(String referencaSpk) {
		this.referencaSpk = referencaSpk;
	}
	
	@IFilter(entityField="specifikacijaPokrica.accnt_spk")
	public String getReferencaPartijeSpk() {
		return referencaPartijeSpk;
	}
		
	public void setReferencaPartijeSpk(String referencaPartijeSpk) {
		this.referencaPartijeSpk = referencaPartijeSpk;
	}
	
	@IFilter(entityField="specifikacijaPokrica.dogte_spk")
	public BigDecimal getIznosDogTecajaSpk() {
		return iznosDogTecajaSpk;
	}
	
	public void setIznosDogTecajaSpk(BigDecimal iznosDogTecajaSpk) {
		this.iznosDogTecajaSpk = iznosDogTecajaSpk;
	}
	
	@IFilter(entityField="specifikacijaPokrica.redbr_spk")
	public Integer getRedniBrojSpk() {
		return redniBrojSpk;
	}

	public void setRedniBrojSpk(Integer redniBrojSpk) {
		this.redniBrojSpk = redniBrojSpk;
	}
	
	@IFilter(entityField="specifikacijaPokrica.opisp_spk")
	public String getOpisSpk() {
		return opisSpk;
	}

	public void setOpisSpk(String opisSpk) {
		this.opisSpk = opisSpk;
	}
	
	@IFilter(entityField="specifikacijaPokrica.tipna_spk")
	public String getTipNaplateSpk() {
		return tipNaplateSpk;
	}

	public void setTipNaplateSpk(String tipNaplateSpk) {
		this.tipNaplateSpk = tipNaplateSpk;
	}

	@IFilter(entityField="komitent.matbr_kom")
	public String getMaticniBrojKomitentaSpk() {
		return maticniBrojKomitentaSpk;
	}

	public void setMaticniBrojKomitentaSpk(String maticniBrojKomitentaSpk) {
		this.maticniBrojKomitentaSpk = maticniBrojKomitentaSpk;
	}
	
	@IFilter(entityField="komitent.naziv_kom")
	public String getNazivKomitentaSpk() {
		return nazivKomitentaSpk;
	}

	public void setNazivKomitentaSpk(String nazivKomitentaSpk) {
		this.nazivKomitentaSpk = nazivKomitentaSpk;
	}

	@IFilter(entityField="specifikacijaPokrica.nalkn_spk")
	public Integer getSifraKnjznogNaloga() {
		return sifraKnjznogNaloga;
	}

	public void setSifraKnjznogNaloga(Integer sifraKnjznogNaloga) {
		this.sifraKnjznogNaloga = sifraKnjznogNaloga;
	}
}
