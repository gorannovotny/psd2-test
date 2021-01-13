package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.lookup.DrzavaNewLookup;
import hr.abc.psd2.lookup.KomitentNewLookup;
import hr.abc.psd2.model.dto.GenericDto;

import java.io.Serializable;
import java.util.Date;

/**
 * Data transfer object for business logic and representation of entity KomitentLokacija.
 * 
 * @author Mario Mihinjač
 */
public class IdentifikacijaDto extends GenericDto<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	// table name, alias & fields
	public static final String TABLE_NAME = "identifikacija";
	public static final String TABLE_ALIAS = "Identifikacija"; // isto kao i entity - ne mijenjati i paziti u DB na tip_dodpod gdje se u tabli_tip koristi ovaj alias
	public static final String SIFRA_FIELD = "sifra_ide";
    public static final String TIPID_FIELD = "tipid_ide";
    public static final String BRDOK_FIELD = "brdok_ide";
    public static final String IZDAV_FIELD = "izdav_ide";
    public static final String DRZAV_FIELD = "drzav_ide";
    public static final String DATIZ_FIELD = "datiz_ide";
    public static final String DATVA_FIELD = "datva_ide";
    public static final String KOMIT_FIELD = "komit_ide";
    // constants
    public static final String TIP_IDENTIFIKACIJE_OSOBNA = "1";
    public static final String TIP_IDENTIFIKACIJE_PUTOVNICA = "2";
    public static final String TIP_IDENTIFIKACIJE_RODNI_LIST = "3";
	// fields
	private String sifraTipaIdentifikacije;
	private String brojIdentifikacije;
    private String izdavateljIdentifikacije;
    private String drzavaIzdavatelja;
    private Date datumIzdavanja;
    private Date datumValjanosti;
    private Integer sifraKomitenta;
    // help fields
	private String nazivTipaIdentifikacije;
    private String oibKomitenta;
	private String nazivKomitenta;
    private String maticniBrojKomitenta;
	private KomitentNewLookup komitentNewLookup;
	private DrzavaNewLookup drzavaNewLookup;

	// constructors
	public IdentifikacijaDto() { super(); }

    @Override
    public String toString() { return getClass().getSimpleName() + "[" + TABLE_ALIAS + "=" + sifra + "]"; }

	// getters & setters
    @Override
    @IFilter(entityField = TABLE_ALIAS + "." + SIFRA_FIELD)
	public Integer getSifra() {
		return super.getSifra();
	}

    @IFilter(entityField = TABLE_ALIAS + "." + TIPID_FIELD)
	public String getSifraTipaIdentifikacije() {
		return sifraTipaIdentifikacije;
	}

	public void setSifraTipaIdentifikacije(String sifraTipaIdentifikacije) {
		this.sifraTipaIdentifikacije = sifraTipaIdentifikacije;
	}

    @IFilter(entityField = TABLE_ALIAS + "." + BRDOK_FIELD)
    public String getBrojIdentifikacije() {
        return brojIdentifikacije;
    }

    public void setBrojIdentifikacije(String brojIdentifikacije) {
        this.brojIdentifikacije = brojIdentifikacije;
    }

    @IFilter(entityField = TABLE_ALIAS + "." + IZDAV_FIELD)
    public String getIzdavateljIdentifikacije() {
        return izdavateljIdentifikacije;
    }

    public void setIzdavateljIdentifikacije(String izdavateljIdentifikacije) {
        this.izdavateljIdentifikacije = izdavateljIdentifikacije;
    }

    @IFilter(entityField = TABLE_ALIAS + "." + DRZAV_FIELD)
    public String getDrzavaIzdavatelja() { return drzavaIzdavatelja; }

    public void setDrzavaIzdavatelja(String drzavaIzdavatelja) { this.drzavaIzdavatelja = drzavaIzdavatelja; }

    @IFilter(entityField = TABLE_ALIAS + "." + DATIZ_FIELD)
    public Date getDatumIzdavanja() { return datumIzdavanja; }

    public void setDatumIzdavanja(Date datumIzdavanja) { this.datumIzdavanja = datumIzdavanja; }

    @IFilter(entityField = TABLE_ALIAS + "." + DATVA_FIELD)
    public Date getDatumValjanosti() {
        return datumValjanosti;
    }

    public void setDatumValjanosti(Date datumValjanosti) {
        this.datumValjanosti = datumValjanosti;
    }

    @IFilter(entityField = TABLE_ALIAS + "." + KOMIT_FIELD)
	public Integer getSifraKomitenta() {
		return sifraKomitenta;
	}

	public void setSifraKomitenta(Integer sifraKomitenta) {
		this.sifraKomitenta = sifraKomitenta;
	}

	public String getNazivTipaIdentifikacije() {
		return nazivTipaIdentifikacije;
	}

	public void setNazivTipaIdentifikacije(String nazivTipaIdentifikacije) { this.nazivTipaIdentifikacije = nazivTipaIdentifikacije; }

    @IFilter(entityField = KomitentDto.TABLE_ALIAS + "." + KomitentDto.OIB_FIELD)
    public String getOibKomitenta() { return oibKomitenta; }

    public void setOibKomitenta(String oibKomitenta) { this.oibKomitenta = oibKomitenta; }

    @IFilter(entityField = KomitentDto.TABLE_ALIAS + "." + KomitentDto.NAZIV_FIELD)
	public String getNazivKomitenta() {
		return nazivKomitenta;
	}

	public void setNazivKomitenta(String nazivKomitenta) { this.nazivKomitenta = nazivKomitenta; }

    @IFilter(entityField = KomitentDto.TABLE_ALIAS + "." + KomitentDto.MATBR_FIELD)
    public String getMaticniBrojKomitenta() { return maticniBrojKomitenta; }

    public void setMaticniBrojKomitenta(String maticniBrojKomitenta) { this.maticniBrojKomitenta = maticniBrojKomitenta; }

    @IFilter(entityField = TABLE_ALIAS + "." + KOMIT_FIELD)
    public KomitentNewLookup getKomitentNewLookup() {
	    if (komitentNewLookup == null) {
	        komitentNewLookup = new KomitentNewLookup();
        }
	    return komitentNewLookup;
	}

    public void setKomitentNewLookup(KomitentNewLookup komitentNewLookup) { this.komitentNewLookup = komitentNewLookup; }

    @IFilter(entityField = TABLE_ALIAS + "." + DRZAV_FIELD)
    public DrzavaNewLookup getDrzavaNewLookup() {
	    if (drzavaNewLookup == null) {
	        drzavaNewLookup = new DrzavaNewLookup();
        }
	    return drzavaNewLookup;
	}

    public void setDrzavaNewLookup(DrzavaNewLookup drzavaNewLookup) { this.drzavaNewLookup = drzavaNewLookup; }
}