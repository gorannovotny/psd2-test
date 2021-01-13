package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.lookup.MfiNewLookup;
import hr.abc.psd2.lookup.SwiftBicNewLookup;

import java.io.Serializable;


public class PravKomitentDto extends KomitentDto implements Serializable {

	private static final long serialVersionUID = 1L;

	// table name, alias & fields
	public static final String PRAVKOM_TABLE_NAME = "prav_komitent";
	public static final String PRAVKOM_TABLE_ALIAS = "PravKomitent"; // isto kao i entity - ne mijenjati i paziti u DB na tip_dodpod gdje se u tabli_tip koristi ovaj alias
	public static final String PRAVKOM_SIFRA_FIELD = "sifra_kom";
	public static final String PUNIN_FIELD = "punin_kom";
	public static final String OBORG_FIELD = "oborg_kom";
	public static final String DJELA_FIELD = "djela_kom";
    public static final String VELIC_FIELD = "velic_kom";
    // constants
    public static final int MBR_LENGTH = 8;
    public static final String PRAVKOM_ROLE_REAL_OWNER = "31";
    public static final String PRAVKOM_ROLE_SUPPLIER = "41";
    public static final String PRAVKOM_ROLE_GUARANTOR = "71";
    public static final String PRAVKOM_ROLE_CO_DEBTOR = "81";
    // fields
    private String puniNaziv;
    private String oblikOrganiziranja;
    private String djelatnost;
    private Integer velicina;
    // help fields
	private Integer podsektor;
    private String kontaktOsoba;
    private String nazivOblikaOrganiziranja;
    private String nazivDjelatnosti;
    private MfiNewLookup mfiNewLookup;
    private String pasivnaNefinancijskaPO;
    private SwiftBicNewLookup swiftBicNewLookup;

    // constructors
	public PravKomitentDto() {
		super();
	}

	// getters & setters
	@Override
    @IFilter(entityField = PRAVKOM_TABLE_ALIAS + "." + PRAVKOM_SIFRA_FIELD)
	public Integer getSifra() {
		return super.getSifra();
	}

    @IFilter(entityField = PRAVKOM_TABLE_ALIAS + "." + PUNIN_FIELD)
    public String getPuniNaziv() { return puniNaziv; }

    public void setPuniNaziv(String puniNaziv) { this.puniNaziv = puniNaziv; }

    @IFilter(entityField = PRAVKOM_TABLE_ALIAS + "." + OBORG_FIELD)
    public String getOblikOrganiziranja() { return oblikOrganiziranja; }

    public void setOblikOrganiziranja(String oblikOrganiziranja) { this.oblikOrganiziranja = oblikOrganiziranja; }

    @IFilter(entityField = PRAVKOM_TABLE_ALIAS + "." + DJELA_FIELD)
    public String getDjelatnost() { return djelatnost; }

    public void setDjelatnost(String djelatnost) { this.djelatnost = djelatnost; }

    @IFilter(entityField = PRAVKOM_TABLE_ALIAS + "." + VELIC_FIELD)
    public Integer getVelicina() { return velicina; }

    public void setVelicina(Integer velicina) { this.velicina = velicina; }

    public Integer getPodsektor() { return podsektor; }

    public void setPodsektor(Integer podsektor) { this.podsektor = podsektor; }

    public MfiNewLookup getMfiNewLookup() {
	    if (mfiNewLookup == null) {
	        mfiNewLookup = new MfiNewLookup();
        }
        return mfiNewLookup;
    }

    public void setMfiNewLookup(MfiNewLookup mfiNewLookup) { this.mfiNewLookup = mfiNewLookup; }

    public SwiftBicNewLookup getSwiftBicNewLookup() {
        if (swiftBicNewLookup == null) {
            swiftBicNewLookup = new SwiftBicNewLookup();
        }
        return swiftBicNewLookup;
    }

    public void setSwiftBicNewLookup(SwiftBicNewLookup swiftBicNewLookup) { this.swiftBicNewLookup = swiftBicNewLookup; }

    public String getKontaktOsoba() { return kontaktOsoba; }

    public void setKontaktOsoba(String kontaktOsoba) { this.kontaktOsoba = kontaktOsoba; }

    public String getNazivOblikaOrganiziranja() { return nazivOblikaOrganiziranja; }

	public void setNazivOblikaOrganiziranja(String nazivOblikaOrganiziranja) { this.nazivOblikaOrganiziranja = nazivOblikaOrganiziranja; }

	public String getNazivDjelatnosti() { return nazivDjelatnosti; }

	public void setNazivDjelatnosti(String nazivDjelatnosti) { this.nazivDjelatnosti = nazivDjelatnosti; }

    public String getPasivnaNefinancijskaPO() { return pasivnaNefinancijskaPO; }

    public void setPasivnaNefinancijskaPO(String pasivnaNefinancijskaPO) { this.pasivnaNefinancijskaPO = pasivnaNefinancijskaPO; }

}
