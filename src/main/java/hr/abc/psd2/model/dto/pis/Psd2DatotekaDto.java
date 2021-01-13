package hr.abc.psd2.model.dto.pis;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.model.dto.GenericDto;

import java.util.Date;
import java.util.Map;

public class Psd2DatotekaDto extends GenericDto<Integer> {
	private static final long serialVersionUID = 1L;

	@IFilter(entityField="o.filen_dat")
	private String nazivDatoteke;

	@IFilter(entityField="o.check_dat")
	private String cheksum;

	@IFilter(entityField="o.vrije_dat")
	private Date vrijemeNastankaDatoteke;

	@IFilter(entityField="ibk.sifra_kom")
	private Integer sifraIbKorisnika;

	@IFilter(entityField="kom.naziv_kom")
	private String nazivKorisnika;

	@IFilter(entityField="o.statu_dat")
	private String statusObrade;

	@IFilter(entityField="o.iname_dat")
	private String inicijatorPlacanjaNaziv;

	@IFilter(entityField="o.iorid_dat")
	private String inicijatorPlacanjaId;



	private String iban;

	private Map<String, Integer> statistika;

	private AbitRuleException greskeKodObrade;

	private Integer version; // entity versioning

	//Za potrebe prikaza rezultata obrade
	private Integer brojUspjesnih;
	private Integer brojGresaka;
	private Integer brojNeobradjenih;

	private Date datumOd;
	private Date datumDo;

	// public no argument constructor
	public Psd2DatotekaDto() {
		super();
	}

	// getters & setters
	@IFilter(entityField="o.sifra_dat")
	public Integer getSifra() {
		return (Integer) this.sifra;
	}

	public void setSifra(Integer sifra){
		this.sifra = sifra;
	}

	public String getNazivDatoteke() {
		return nazivDatoteke;
	}

	public void setNazivDatoteke(String nazivDatoteke) {
		this.nazivDatoteke = nazivDatoteke;
	}

	public Date getVrijemeNastankaDatoteke() {
		return vrijemeNastankaDatoteke;
	}

	public void setVrijemeNastankaDatoteke(Date vrijemeNastankaDatoteke) {
		this.vrijemeNastankaDatoteke = vrijemeNastankaDatoteke;
	}

	public Map<String, Integer> getStatistika() {
		return statistika;
	}

	public void setStatistika(Map<String, Integer> statistika) {
		this.statistika = statistika;
	}

	public AbitRuleException getGreskeKodObrade() {
		return greskeKodObrade;
	}

	public void setGreskeKodObrade(AbitRuleException greskeKodObrade) {
		this.greskeKodObrade = greskeKodObrade;
	}

	public String getCheksum() {
		return cheksum;
	}

	public void setCheksum(String cheksum) {
		this.cheksum = cheksum;
	}

	public Integer getBrojUspjesnih() {
		return brojUspjesnih;
	}

	public void setBrojUspjesnih(Integer brojUspjesnih) {
		this.brojUspjesnih = brojUspjesnih;
	}

	public Integer getBrojGresaka() {
		return brojGresaka;
	}

	public void setBrojGresaka(Integer brojGresaka) {
		this.brojGresaka = brojGresaka;
	}

	public Integer getBrojNeobradjenih() {
		return brojNeobradjenih;
	}

	public void setBrojNeobradjenih(Integer brojNeobradjenih) {
		this.brojNeobradjenih = brojNeobradjenih;
	}

	public String getRezultatObrade(){
		String res = "";
		if(brojUspjesnih != null && brojUspjesnih.intValue() > 0){
			res = "Uspješno obrađeno slogova : " +brojUspjesnih.intValue()+".";
		}
		if(brojNeobradjenih != null && brojNeobradjenih.intValue() > 0){
			if(res != null && !res.trim().equals("")) res += " ";
			res = "Neobrađeno slogova : " +brojNeobradjenih.intValue()+".";
		}
		if(brojGresaka != null && brojGresaka.intValue() > 0){
			if(res != null && !res.trim().equals("")) res += " ";
			res += "Ukupno slogova s greškama : " +brojGresaka.intValue()+".";
		}
		return res;
	}

	public Date getDatumOd() {
		return datumOd;
	}

	public void setDatumOd(Date datumOd) {
		this.datumOd = datumOd;
	}

	public Date getDatumDo() {
		return datumDo;
	}

	public void setDatumDo(Date datumDo) {
		this.datumDo = datumDo;
	}

	public Integer getSifraIbKorisnika() {
		return sifraIbKorisnika;
	}

	public void setSifraIbKorisnika(Integer sifraIbKorisnika) {
		this.sifraIbKorisnika = sifraIbKorisnika;
	}

	public String getNazivKorisnika() {
		return nazivKorisnika;
	}

	public void setNazivKorisnika(String nazivKorisnika) {
		this.nazivKorisnika = nazivKorisnika;
	}

	public String getStatusObrade() {
		return statusObrade;
	}

	public void setStatusObrade(String statusObrade) {
		this.statusObrade = statusObrade;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getInicijatorPlacanjaId() {
		return inicijatorPlacanjaId;
	}

	public void setInicijatorPlacanjaId(String inicijatorPlacanjaId) {
		this.inicijatorPlacanjaId = inicijatorPlacanjaId;
	}

	public String getInicijatorPlacanjaNaziv() {
		return inicijatorPlacanjaNaziv;
	}

	public void setInicijatorPlacanjaNaziv(String inicijatorPlacanjaNaziv) {
		this.inicijatorPlacanjaNaziv = inicijatorPlacanjaNaziv;
	}
}
