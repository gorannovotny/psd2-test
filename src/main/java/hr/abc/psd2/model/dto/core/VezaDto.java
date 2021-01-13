package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.lookup.KomitentNewLookup;
import hr.abc.psd2.lookup.PartijaNewLookup;
import hr.abc.psd2.lookup.TipVezeNewLookup;
import hr.abc.psd2.model.dto.GenericDto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Data transfer object for business logic and representation of entity Veza.
 * 
 * @author Mario Mihinjač
 */
public class VezaDto extends GenericDto<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	// table name, alias & fields
	public static final String TABLE_NAME = "veza";
	public static final String TABLE_ALIAS = "Veza";      // isto kao i entity - ne mijenjati i paziti u DB na tip_dodpod gdje se u tabli_tip koristi ovaj alias
	public static final String SIFRA_FIELD = "sifra_vez";
	public static final String DATOD_FIELD = "datod_vez";
	public static final String DATDO_FIELD = "datdo_vez";
	public static final String KOMI1_FIELD = "komi1_vez";
	public static final String KOMI2_FIELD = "komi2_vez";
	public static final String PART1_FIELD = "part1_vez";
	public static final String PART2_FIELD = "part2_vez";
	public static final String TIPVE_FIELD = "tipve_vez";
	// fields
	private Date datumOd;
	private Date datumDo;
	private KomitentNewLookup komitentNewLookup1;
	private KomitentNewLookup komitentNewLookup2;
	private PartijaNewLookup partijaNewLookup1;
	private PartijaNewLookup partijaNewLookup2;
	private TipVezeNewLookup tipVezeNewLookup;
	// TODO - treba početi koristiti lookup-e
	private Integer sifraPrvogKomitenta;
	private Integer sifraDrugogKomitenta;
	private Integer sifraPrvePartije;
	private Integer sifraDrugePartije;
	private Integer tipVeze;
	// help fields
	private String oibPrvogKomitenta;
	private String maticniBrojPrvogKomitenta;
	private String nazivPrvogKomitenta;
	private String oibDrugogKomitenta;
	private String maticniBrojDrugogKomitenta;
	private String nazivDrugogKomitenta;
	private String oznakaPrvePartije;
	private String alterParPrvePartije;
	private String oibVlasnikaPrvePartije;
	private String matbrVlasnikaPrvePartije;
	private String nazivVlasnikaPrvePartije;
	private String oznakaDrugePartije;
	private String alterParDrugePartije;
	private String oibVlasnikaDrugePartije;
	private String matbrVlasnikaDrugePartije;
	private String nazivVlasnikaDrugePartije;
	private String tipVezeNaziv;
	private String tipVezeOpis;
	private String tipVezeIndikator;
	private String tipVezeFilter; // polje za filtriranje više tipova veze odjednom
	private List<Integer> listTipVeze;
	private Integer grupaVeze;
	private String sifraValute;
	private Boolean skipExistenceValidation;

	// constructors
	public VezaDto() {
		super();
	}

	public VezaDto(Integer sifra) {
		super(sifra);
	}

	public VezaDto(Integer sifra, Date datumOd, Date datumDo, Integer sifraPrvogKomitenta, Integer sifraDrugogKomitenta, Integer sifraPrvePartije, Integer sifraDrugePartije, Integer tipVeze, List<Integer> listTipVeze) {
		this.sifra = sifra;
		this.datumOd = datumOd;
		this.datumDo = datumDo;
		this.getKomitentNewLookup1().setId(sifraPrvogKomitenta);
		this.getKomitentNewLookup2().setId(sifraDrugogKomitenta);
		this.getPartijaNewLookup1().setId(sifraPrvePartije);
		this.getPartijaNewLookup2().setId(sifraDrugePartije);
		this.getTipVezeNewLookup().setId(tipVeze);
		this.listTipVeze = listTipVeze;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[" + TABLE_ALIAS + "=" + sifra + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null && !(o instanceof VezaDto))
			return false;
		VezaDto that = (VezaDto) o;
		return Objects.equals(getSifra(), that.getSifra()) &&
				Objects.equals(getDatumOd(), that.getDatumOd()) &&
				Objects.equals(getDatumDo(), that.getDatumDo()) &&
				Objects.equals(getKomitentNewLookup1().getId(), that.getKomitentNewLookup1().getId()) &&
				Objects.equals(getKomitentNewLookup2().getId(), that.getKomitentNewLookup2().getId()) &&
				Objects.equals(getPartijaNewLookup1().getId(), that.getPartijaNewLookup1().getId()) &&
				Objects.equals(getPartijaNewLookup2().getId(), that.getPartijaNewLookup2().getId()) &&
				Objects.equals(getTipVezeNewLookup().getId(), that.getTipVezeNewLookup().getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getClass().getSimpleName(), getSifra(), getDatumOd(), getDatumDo(), getKomitentNewLookup1().getId(), getKomitentNewLookup2().getId(), getPartijaNewLookup1().getId(), getPartijaNewLookup2().getId(), getTipVezeNewLookup().getId());
	}

	// getters & setters
	@Override
	@IFilter(entityField = TABLE_ALIAS + "." + SIFRA_FIELD)
	public Integer getSifra() {
		return super.getSifra();
	}

	public void setSifra(Integer sifra) {
		this.sifra = sifra;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + DATOD_FIELD)
	public Date getDatumOd() {
		return datumOd;
	}

	public void setDatumOd(Date datumOd) {
		this.datumOd = datumOd;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + DATDO_FIELD)
	public Date getDatumDo() {
		return datumDo;
	}

	public void setDatumDo(Date datumDo) {
		this.datumDo = datumDo;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + KOMI1_FIELD)
	public KomitentNewLookup getKomitentNewLookup1() {
		if (komitentNewLookup1 == null) {
			komitentNewLookup1 = new KomitentNewLookup();
		}
		return komitentNewLookup1;
	}

	public void setKomitentNewLookup1(KomitentNewLookup komitentNewLookup1) {
		this.komitentNewLookup1 = komitentNewLookup1;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + KOMI2_FIELD)
	public KomitentNewLookup getKomitentNewLookup2() {
		if (komitentNewLookup2 == null) {
			komitentNewLookup2 = new KomitentNewLookup();
		}
		return komitentNewLookup2;
	}

	public void setKomitentNewLookup2(KomitentNewLookup komitentNewLookup2) {
		this.komitentNewLookup2 = komitentNewLookup2;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + PART1_FIELD)
	public PartijaNewLookup getPartijaNewLookup1() {
		if (partijaNewLookup1 == null) {
			partijaNewLookup1 = new PartijaNewLookup();
		}
		return partijaNewLookup1;
	}

	public void setPartijaNewLookup1(PartijaNewLookup partijaNewLookup1) {
		this.partijaNewLookup1 = partijaNewLookup1;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + PART2_FIELD)
	public PartijaNewLookup getPartijaNewLookup2() {
		if (partijaNewLookup2 == null) {
			partijaNewLookup2 = new PartijaNewLookup();
		}
		return partijaNewLookup2;
	}

	public void setPartijaNewLookup2(PartijaNewLookup partijaNewLookup2) {
		this.partijaNewLookup2 = partijaNewLookup2;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + TIPVE_FIELD)
	public TipVezeNewLookup getTipVezeNewLookup() {
		if (tipVezeNewLookup == null) {
			tipVezeNewLookup = new TipVezeNewLookup();
		}
		return tipVezeNewLookup;
	}

	public void setTipVezeNewLookup(TipVezeNewLookup tipVezeNewLookup) {
		this.tipVezeNewLookup = tipVezeNewLookup;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + KOMI1_FIELD)
	public Integer getSifraPrvogKomitenta() {
		return sifraPrvogKomitenta;
	}

	public void setSifraPrvogKomitenta(Integer sifraPrvogKomitenta) {
		this.sifraPrvogKomitenta = sifraPrvogKomitenta;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + KOMI2_FIELD)
	public Integer getSifraDrugogKomitenta() {
		return sifraDrugogKomitenta;
	}

	public void setSifraDrugogKomitenta(Integer sifraDrugogKomitenta) {
		this.sifraDrugogKomitenta = sifraDrugogKomitenta;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + PART1_FIELD)
	public Integer getSifraPrvePartije() {
		return sifraPrvePartije;
	}

	public void setSifraPrvePartije(Integer sifraPrvePartije) {
		this.sifraPrvePartije = sifraPrvePartije;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + PART2_FIELD)
	public Integer getSifraDrugePartije() {
		return sifraDrugePartije;
	}

	public void setSifraDrugePartije(Integer sifraDrugePartije) {
		this.sifraDrugePartije = sifraDrugePartije;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + TIPVE_FIELD)
	public Integer getTipVeze() {
		return tipVeze;
	}

	public void setTipVeze(Integer tipVeze) {
		this.tipVeze = tipVeze;
	}

	@IFilter(entityField = KomitentDto.TABLE_ALIAS + "1." + KomitentDto.OIB_FIELD)
	public String getOibPrvogKomitenta() {
		return oibPrvogKomitenta;
	}

	public void setOibPrvogKomitenta(String oibPrvogKomitenta) {
		this.oibPrvogKomitenta = oibPrvogKomitenta;
	}

	@IFilter(entityField = KomitentDto.TABLE_ALIAS + "1." + KomitentDto.MATBR_FIELD)
	public String getMaticniBrojPrvogKomitenta() {
		return maticniBrojPrvogKomitenta;
	}

	public void setMaticniBrojPrvogKomitenta(String maticniBrojPrvogKomitenta) {
		this.maticniBrojPrvogKomitenta = maticniBrojPrvogKomitenta;
	}

	@IFilter(entityField = KomitentDto.TABLE_ALIAS + "1." + KomitentDto.NAZIV_FIELD)
	public String getNazivPrvogKomitenta() {
		return nazivPrvogKomitenta;
	}

	public void setNazivPrvogKomitenta(String nazivPrvogKomitenta) {
		this.nazivPrvogKomitenta = nazivPrvogKomitenta;
	}

	@IFilter(entityField = KomitentDto.TABLE_ALIAS + "2." + KomitentDto.OIB_FIELD)
	public String getOibDrugogKomitenta() {
		return oibDrugogKomitenta;
	}

	public void setOibDrugogKomitenta(String oibDrugogKomitenta) {
		this.oibDrugogKomitenta = oibDrugogKomitenta;
	}

	@IFilter(entityField = KomitentDto.TABLE_ALIAS + "2." + KomitentDto.MATBR_FIELD)
	public String getMaticniBrojDrugogKomitenta() {
		return maticniBrojDrugogKomitenta;
	}

	public void setMaticniBrojDrugogKomitenta(String maticniBrojDrugogKomitenta) {
		this.maticniBrojDrugogKomitenta = maticniBrojDrugogKomitenta;
	}

	@IFilter(entityField = KomitentDto.TABLE_ALIAS + "2." + KomitentDto.NAZIV_FIELD)
	public String getNazivDrugogKomitenta() {
		return nazivDrugogKomitenta;
	}

	public void setNazivDrugogKomitenta(String nazivDrugogKomitenta) {
		this.nazivDrugogKomitenta = nazivDrugogKomitenta;
	}

	@IFilter(entityField = PartijaDto.TABLE_ALIAS + "1." + PartijaDto.PARTI_FIELD)
	public String getOznakaPrvePartije() {
		return oznakaPrvePartije;
	}

	public void setOznakaPrvePartije(String oznakaPrvePartije) {
		this.oznakaPrvePartije = oznakaPrvePartije;
	}

	@IFilter(entityField = PartijaDto.TABLE_ALIAS + "1." + PartijaDto.ALTER_FIELD)
	public String getAlterParPrvePartije() {
		return alterParPrvePartije;
	}

	public void setAlterParPrvePartije(String alterParPrvePartije) {
		this.alterParPrvePartije = alterParPrvePartije;
	}

	@IFilter(entityField = KomitentDto.TABLE_ALIAS + "3." + KomitentDto.OIB_FIELD)
	public String getOibVlasnikaPrvePartije() {
		return oibVlasnikaPrvePartije;
	}

	public void setOibVlasnikaPrvePartije(String oibVlasnikaPrvePartije) {
		this.oibVlasnikaPrvePartije = oibVlasnikaPrvePartije;
	}

	@IFilter(entityField = KomitentDto.TABLE_ALIAS + "3." + KomitentDto.MATBR_FIELD)
	public String getMatbrVlasnikaPrvePartije() {
		return matbrVlasnikaPrvePartije;
	}

	public void setMatbrVlasnikaPrvePartije(String matbrVlasnikaPrvePartije) {
		this.matbrVlasnikaPrvePartije = matbrVlasnikaPrvePartije;
	}

	@IFilter(entityField = KomitentDto.TABLE_ALIAS + "3." + KomitentDto.NAZIV_FIELD)
	public String getNazivVlasnikaPrvePartije() {
		return nazivVlasnikaPrvePartije;
	}

	public void setNazivVlasnikaPrvePartije(String nazivVlasnikaPrvePartije) {
		this.nazivVlasnikaPrvePartije = nazivVlasnikaPrvePartije;
	}

	@IFilter(entityField = PartijaDto.TABLE_ALIAS + "2." + PartijaDto.PARTI_FIELD)
	public String getOznakaDrugePartije() {
		return oznakaDrugePartije;
	}

	public void setOznakaDrugePartije(String oznakaDrugePartije) {
		this.oznakaDrugePartije = oznakaDrugePartije;
	}

	@IFilter(entityField = PartijaDto.TABLE_ALIAS + "2." + PartijaDto.ALTER_FIELD)
	public String getAlterParDrugePartije() {
		return alterParDrugePartije;
	}

	public void setAlterParDrugePartije(String alterParDrugePartije) {
		this.alterParDrugePartije = alterParDrugePartije;
	}

	@IFilter(entityField = KomitentDto.TABLE_ALIAS + "4." + KomitentDto.OIB_FIELD)
	public String getOibVlasnikaDrugePartije() {
		return oibVlasnikaDrugePartije;
	}

	public void setOibVlasnikaDrugePartije(String oibVlasnikaDrugePartije) {
		this.oibVlasnikaDrugePartije = oibVlasnikaDrugePartije;
	}

	@IFilter(entityField = KomitentDto.TABLE_ALIAS + "4." + KomitentDto.MATBR_FIELD)
	public String getMatbrVlasnikaDrugePartije() {
		return matbrVlasnikaDrugePartije;
	}

	public void setMatbrVlasnikaDrugePartije(String matbrVlasnikaDrugePartije) {
		this.matbrVlasnikaDrugePartije = matbrVlasnikaDrugePartije;
	}

	@IFilter(entityField = KomitentDto.TABLE_ALIAS + "4." + KomitentDto.NAZIV_FIELD)
	public String getNazivVlasnikaDrugePartije() {
		return nazivVlasnikaDrugePartije;
	}

	public void setNazivVlasnikaDrugePartije(String nazivVlasnikaDrugePartije) {
		this.nazivVlasnikaDrugePartije = nazivVlasnikaDrugePartije;
	}

	@IFilter(entityField = TipVezeDto.TABLE_ALIAS + "." + TipVezeDto.NAZIV_FIELD)
	public String getTipVezeNaziv() {
		return tipVezeNaziv;
	}

	public void setTipVezeNaziv(String tipVezeNaziv) {
		this.tipVezeNaziv = tipVezeNaziv;
	}

	public String getTipVezeOpis() {
		return tipVezeOpis;
	}

	public void setTipVezeOpis(String tipVezeOpis) {
		this.tipVezeOpis = tipVezeOpis;
	}

	public String getTipVezeIndikator() {
		return tipVezeIndikator;
	}

	public void setTipVezeIndikator(String tipVezeIndikator) {
		this.tipVezeIndikator = tipVezeIndikator;
	}

	public String getTipVezeFilter() {
		return tipVezeFilter;
	}

	public void setTipVezeFilter(String tipVezeFilter) {
		this.tipVezeFilter = tipVezeFilter;
	}

	@IFilter(entityField = TipVezeDto.TABLE_ALIAS + "." + TipVezeDto.GRUPA_FIELD)
	public Integer getGrupaVeze() {
		return grupaVeze;
	}

	public void setGrupaVeze(Integer grupaVeze) {
		this.grupaVeze = grupaVeze;
	}

	public List<Integer> getListTipVeze() {
		return listTipVeze;
	}

	public void setListTipVeze(List<Integer> listTipVeze) {
		this.listTipVeze = listTipVeze;
	}

	public String getSifraValute() {
		return sifraValute;
	}

	public void setSifraValute(String sifraValute) {
		this.sifraValute = sifraValute;
	}

	public Boolean getSkipExistenceValidation() {
		if (skipExistenceValidation == null) {
			skipExistenceValidation = Boolean.FALSE;
		}
		return skipExistenceValidation;
	}

	public void setSkipExistenceValidation(Boolean skipExistenceValidation) {
		this.skipExistenceValidation = skipExistenceValidation;
	}
}