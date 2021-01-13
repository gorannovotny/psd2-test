package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.lookup.KomitentNewLookup;
import hr.abc.psd2.lookup.PartijaNewLookup;
import hr.abc.psd2.lookup.TipDodatniNewLookup;
import hr.abc.psd2.model.dto.GenericDto;
import hr.abc.psd2.util.GenericBassxConstants.Core.GrupaDodPod;

import java.io.Serializable;
import java.util.Objects;

/**
 * Data transfer object for business logic and representation of entity DodPodaci.
 * 
 * @author Mario Mihinjač
 */
public class DodPodaciDto extends GenericDto<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	// table name, alias & fields
	public static final String TABLE_NAME = "dod_podaci";
	public static final String TABLE_ALIAS = "DodPodaci";  // isto kao i entity - ne mijenjati i paziti u DB na tip_dodpod gdje se u tabli_tip koristi ovaj alias
	public static final String SIFRA_FIELD = "sifra_dod";
	public static final String TIPPO_FIELD = "tippo_dod";
	public static final String KOMIT_FIELD = "komit_dod";
	public static final String PARTI_FIELD = "parti_dod";
	public static final String VALUE_FIELD = "value_dod";
	public static final String VALU2_FIELD = "valu2_dod";
	// constants
    public static final String IZVOR_SREDSTAVA_GENERIC_DISPLAY = "navedite izvor";
    public static final String IZVOR_SREDSTAVA_PREMIJE_OSIGURANJA = "50";
    public static final String IZVOR_SREDSTAVA_UZDRZAVANJE = "70";
    public static final String IZVOR_SREDSTAVA_OSTALO = "110";
	// fields
    private TipDodatniNewLookup tipDodatniNewLookup;
    private KomitentNewLookup komitentNewLookup;
    private PartijaNewLookup partijaNewLookup;
	private String vrijednost;
	private String dodatnaVrijednost;
    // TODO - treba početi koristiti lookup-e
    private Integer tipDodatnogPodatka;
    private Integer sifraKomitenta;
    private Integer sifraPartije;
	// help fields
    private String nazivTipa;
	private String nazivKomitenta;
    private String oibKomitenta;
	private String maticniBrojKomitenta;
	private String brojPartije;
    private String nazivPartije;
	private boolean isEdit = false;
	private GrupaDodPod formaTip;
	private String opisVrijednosti;
    private String opisDodatneVrijednosti;
    private boolean extraValueInput;
	
	// constructors
	public DodPodaciDto() { super(); }

    public DodPodaciDto(Integer tipDodatnogPodatka, Integer sifraKomitenta, Integer sifraPartije, String vrijednost, String dodatnaVrijednost) {
        this.getTipDodatniNewLookup().setId(tipDodatnogPodatka);
        this.getKomitentNewLookup().setId(sifraKomitenta);
        this.getPartijaNewLookup().setId(sifraPartije);
        this.vrijednost = vrijednost;
        this.dodatnaVrijednost = dodatnaVrijednost;
    }

    public DodPodaciDto(String vrijednost, String opisVrijednosti) {
        this.vrijednost = vrijednost;
        this.opisVrijednosti = opisVrijednosti;
    }

    public boolean dalSuIstiWeb(DodPodaciDto obj){
        boolean rez = true;
        if (rez && !this.getSifraPartije().equals(obj.getSifraPartije()))
            rez = false;
        if (rez && !this.getTipDodatnogPodatka().equals(obj.getTipDodatnogPodatka()))
            rez = false;
        if (rez && !this.getVrijednost().equals(obj.getVrijednost()))
            rez = false;
        return rez;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + TABLE_ALIAS +"=" + sifra + "]";
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null && !(o instanceof DodPodaciDto)) return false;
		DodPodaciDto that = (DodPodaciDto) o;
		return Objects.equals(getSifra(), that.getSifra()) &&
                Objects.equals(getTipDodatniNewLookup().getId(), that.getTipDodatniNewLookup().getId()) &&
                Objects.equals(getKomitentNewLookup().getId(), that.getKomitentNewLookup().getId()) &&
                Objects.equals(getPartijaNewLookup().getId(), that.getPartijaNewLookup().getId()) &&
				Objects.equals(getVrijednost(), that.getVrijednost()) &&
                Objects.equals(getDodatnaVrijednost(), that.getDodatnaVrijednost());
	}

    @Override
    public int hashCode() { return Objects.hash(getClass().getSimpleName(), getSifra(), getTipDodatniNewLookup().getId(), getKomitentNewLookup().getId(), getPartijaNewLookup().getId(), getVrijednost(), getDodatnaVrijednost()); }

    // getters & setters
    @Override
	@IFilter(entityField = TABLE_ALIAS + "." + SIFRA_FIELD)
	public Integer getSifra() { return super.getSifra(); }

    @IFilter(entityField = TABLE_ALIAS + "." + TIPPO_FIELD)
    public TipDodatniNewLookup getTipDodatniNewLookup() {
	    if (tipDodatniNewLookup == null) {
	        tipDodatniNewLookup = new TipDodatniNewLookup();
        }
        return tipDodatniNewLookup;
    }

    public void setTipDodatniNewLookup(TipDodatniNewLookup tipDodatniNewLookup) { this.tipDodatniNewLookup = tipDodatniNewLookup; }

    @IFilter(entityField = TABLE_ALIAS + "." + KOMIT_FIELD)
    public KomitentNewLookup getKomitentNewLookup() {
	    if (komitentNewLookup == null) {
	        komitentNewLookup = new KomitentNewLookup();
        }
        return komitentNewLookup;
    }

    public void setKomitentNewLookup(KomitentNewLookup komitentNewLookup) { this.komitentNewLookup = komitentNewLookup; }

    @IFilter(entityField = TABLE_ALIAS + "." + PARTI_FIELD)
    public PartijaNewLookup getPartijaNewLookup() {
	    if (partijaNewLookup == null) {
	        partijaNewLookup = new PartijaNewLookup();
        }
        return partijaNewLookup;
    }

    public void setPartijaNewLookup(PartijaNewLookup partijaNewLookup) { this.partijaNewLookup = partijaNewLookup; }

    @IFilter(entityField = TABLE_ALIAS + "." + VALUE_FIELD)
    public String getVrijednost() { return vrijednost; }

    public void setVrijednost(String vrijednost) { this.vrijednost = vrijednost; }

    @IFilter(entityField = TABLE_ALIAS + "." + VALU2_FIELD)
    public String getDodatnaVrijednost() { return dodatnaVrijednost; }

    public void setDodatnaVrijednost(String dodatnaVrijednost) { this.dodatnaVrijednost = dodatnaVrijednost; }

    @IFilter(entityField = TABLE_ALIAS + "." + TIPPO_FIELD)
    public Integer getTipDodatnogPodatka() { return tipDodatnogPodatka; }

    public void setTipDodatnogPodatka(Integer tipDodatnogPodatka) { this.tipDodatnogPodatka = tipDodatnogPodatka; }

    @IFilter(entityField = TABLE_ALIAS + "." + KOMIT_FIELD)
    public Integer getSifraKomitenta() { return sifraKomitenta; }

    public void setSifraKomitenta(Integer sifraKomitenta) { this.sifraKomitenta = sifraKomitenta; }

    @IFilter(entityField = TABLE_ALIAS + "." + PARTI_FIELD)
    public Integer getSifraPartije() { return sifraPartije; }

    public void setSifraPartije(Integer sifraPartije) { this.sifraPartije = sifraPartije; }

	public String getNazivTipa() { return nazivTipa; }

    public void setNazivTipa(String nazivTipa) { this.nazivTipa = nazivTipa; }

    @IFilter(entityField= KomitentDto.TABLE_ALIAS + "." + KomitentDto.NAZIV_FIELD)
    public String getNazivKomitenta() { return nazivKomitenta; }

    public void setNazivKomitenta(String nazivKomitenta) { this.nazivKomitenta = nazivKomitenta; }

    @IFilter(entityField= KomitentDto.TABLE_ALIAS + "." + KomitentDto.OIB_FIELD)
    public String getOibKomitenta() { return oibKomitenta; }

    public void setOibKomitenta(String oibKomitenta) { this.oibKomitenta = oibKomitenta; }

    @IFilter(entityField= KomitentDto.TABLE_ALIAS + "." + KomitentDto.MATBR_FIELD)
	public String getMaticniBrojKomitenta() { return maticniBrojKomitenta; }

	public void setMaticniBrojKomitenta(String maticniBrojKomitenta) { this.maticniBrojKomitenta = maticniBrojKomitenta; }

	@IFilter(entityField = PartijaDto.TABLE_ALIAS + "." + PartijaDto.PARTI_FIELD)
	public String getBrojPartije() { return brojPartije; }

	public void setBrojPartije(String brojPartije) { this.brojPartije = brojPartije; }

	//MOZDA NETREBA TODO
	public String getNazivPartije() { return nazivPartije; }

	public void setNazivPartije(String nazivPartije) { this.nazivPartije = nazivPartije; }
	//MOZDA NETREBA TODO

	public boolean isEdit() { return isEdit; }

	public void setEdit(boolean isEdit) { this.isEdit = isEdit; }

	public GrupaDodPod getFormaTip() { return formaTip; }

	public void setFormaTip(GrupaDodPod formaTip) { this.formaTip = formaTip; }

    public String getOpisVrijednosti() { return opisVrijednosti; }

    public void setOpisVrijednosti(String opisVrijednosti) { this.opisVrijednosti = opisVrijednosti; }

    public String getOpisDodatneVrijednosti() { return opisDodatneVrijednosti; }

    public void setOpisDodatneVrijednosti(String opisDodatneVrijednosti) { this.opisDodatneVrijednosti = opisDodatneVrijednosti; }

	public boolean isExtraValueInput() { return extraValueInput; }

	public void setExtraValueInput(boolean extraValueInput) { this.extraValueInput = extraValueInput; }
}