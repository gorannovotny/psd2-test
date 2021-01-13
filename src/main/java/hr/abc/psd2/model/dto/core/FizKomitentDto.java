package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.util.GenericBassxConstants;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FizKomitentDto extends KomitentDto implements Serializable {

    private static final long serialVersionUID = 1L;

    // table name, alias & fields
    public static final String FIZKOM_TABLE_NAME = "fiz_komitent";
    public static final String FIZKOM_TABLE_ALIAS = "FizKomitent";  // isto kao i entity - ne mijenjati i paziti u DB na tip_dodpod gdje se u tabli_tip koristi ovaj alias
    public static final String FIZKOM_SIFRA_FIELD = "sifra_kom";
    public static final String IMEEE_FIELD = "imeee_kom";
    public static final String PREZI_FIELD = "prezi_kom";
    public static final String DRZRO_FIELD = "drzro_kom";
    public static final String MJERO_FIELD = "mjero_kom";
    public static final String DATRO_FIELD = "datro_kom";
    public static final String PIOSB_FIELD = "piosb_kom";
    // constants
    public static final int JMBG_LENGTH = 13;
    public static final int DATRO_YEARS_BACK = -100;
    public static final String FIZKOM_ROLE_OCCASIONAL = "20"; // povremeni
    public static final String FIZKOM_ROLE_GUARANTOR = "30"; // jamac
    public static final String FIZKOM_ROLE_CO_DEBTOR = "40"; // sudužnik
    public static final String FIZKOM_ROLE_LEGAL_REPRESENTATIVE = "50"; // zakonski zastupnik /član uprave / direktor
    public static final String FIZKOM_ROLE_ARTISAN = "60"; // obrtnik ili osoba koja obavlja drugu samostalnu djelatnost
    public static final String FIZKOM_ROLE_AUTHORIZED_REPRESENTATIVE = "70"; // osoba ovlaštena za zastupanje / prokurist /punomoćnik
    public static final String FIZKOM_ROLE_REAL_OWNER = "80"; // stvarni vlasnik PS - fizička osoba
    public static final String FIZKOM_ROLE_LIEN_DEBTOR = "90"; // založni dužnik
    public static final String FIZKOM_ROLE_SERVICE_USER = "100"; // ovlašteni korisnik usluge/servisa
    public static final String FIZKOM_ROLE_CURRENT_ACCOUNT_USER = "110"; // osoba ovlaštena za raspolaganje sredstvima po tr.rn.
    public static final String FIZKOM_ROLE_CUSTOMER_SUPPLIER = "120"; // kupac/dobavljač
    // fields
    private String ime;
    private String prezime;
    private String sifraDrzaveRodjenja;
    private String mjestoRodjenja;
    private Date datumRodjenja;
    private String politickiIzlozen;
    // help fields
    private String automatizacijaSms;
    private String automatizacijaEmail;
    private String telefonKucni;
    private String mobitel3DSecure;
    private LokacijaDto lokacijaBoravisteDto = new LokacijaDto();
    private List<IdentifikacijaDto> listIdentifikacija;
    private List<DodPodaciDto> listDrzavljanstvo;


    // constructors
    public FizKomitentDto() {
        super();
    }

    public FizKomitentDto(boolean isFirstCall) {
        super(isFirstCall);
    }

    public FizKomitentDto(Integer sifra, String maticniBroj, String oib, String naziv) {
        super(sifra, maticniBroj, oib, naziv);
    }

    public FizKomitentDto(String maticniBroj, String naziv,	Integer tipRizika, String sifraDrzave, String nazivDrzave,
                          String sifraPoslovnice, String nazivPoslovnice,	String sifraSektora, String nazivSektora, String loginOsobnogBankara, String status, String napomena,
                          Integer sifraOsobnogBankara, Integer version, Date datumPristupanja) {
        super(maticniBroj, naziv, tipRizika, sifraDrzave, nazivDrzave, sifraPoslovnice,
                nazivPoslovnice, sifraSektora, nazivSektora, loginOsobnogBankara,
                status, napomena, sifraOsobnogBankara, version, datumPristupanja);
    }

    public FizKomitentDto(String maticniBroj, String oib, String naziv) {
        super(maticniBroj, oib, naziv);
    }

    @Override
    public String toString() { return getClass().getSimpleName() + "[" + FIZKOM_TABLE_ALIAS +"=" + sifra + "]"; }

    // getters & setters
    @Override
    @IFilter(entityField = FIZKOM_TABLE_ALIAS + "." + FIZKOM_SIFRA_FIELD)
    public Integer getSifra() {
        return super.getSifra();
    }

    public void setSifra(Integer sifra) {
        this.sifra = sifra;
    }

    @IFilter(entityField = FIZKOM_TABLE_ALIAS + "." + IMEEE_FIELD)
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    @IFilter(entityField = FIZKOM_TABLE_ALIAS + "." + PREZI_FIELD)
    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @IFilter(entityField = FIZKOM_TABLE_ALIAS + "." + DRZRO_FIELD)
    public String getSifraDrzaveRodjenja() {
        return sifraDrzaveRodjenja;
    }

    public void setSifraDrzaveRodjenja(String sifraDrzaveRodjenja) {
        this.sifraDrzaveRodjenja = sifraDrzaveRodjenja;
    }

    @IFilter(entityField = FIZKOM_TABLE_ALIAS + "." + MJERO_FIELD)
    public String getMjestoRodjenja() {
        return mjestoRodjenja;
    }

    public void setMjestoRodjenja(String mjestoRodjenja) {
        this.mjestoRodjenja = mjestoRodjenja;
    }

    @IFilter(entityField = FIZKOM_TABLE_ALIAS + "." + DATRO_FIELD)
    public Date getDatumRodjenja() { return datumRodjenja; }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    @IFilter(entityField = FIZKOM_TABLE_ALIAS + "." + PIOSB_FIELD)
    public String getPolitickiIzlozen() { return politickiIzlozen; }

    public void setPolitickiIzlozen(String politickiIzlozen) { this.politickiIzlozen = politickiIzlozen; }

    public String getAutomatizacijaSms() { return automatizacijaSms; }

    public void setAutomatizacijaSms(String automatizacijaSms) { this.automatizacijaSms = automatizacijaSms; }

    public String getAutomatizacijaEmail() { return automatizacijaEmail; }

    public void setAutomatizacijaEmail(String automatizacijaEmail) { this.automatizacijaEmail = automatizacijaEmail; }

    public String getTelefonKucni() {
        return telefonKucni;
    }

    public void setTelefonKucni(String telefonKucni) {
        this.telefonKucni = telefonKucni;
    }

    public String getMobitel3DSecure() { return mobitel3DSecure; }

    public void setMobitel3DSecure(String mobitel3DSecure) { this.mobitel3DSecure = mobitel3DSecure; }

    public LokacijaDto getLokacijaBoravisteDto() { return lokacijaBoravisteDto; }

    public void setLokacijaBoravisteDto(LokacijaDto lokacijaBoravisteDto) { this.lokacijaBoravisteDto = lokacijaBoravisteDto; }

    public List<IdentifikacijaDto> getListIdentifikacija() {
        if (listIdentifikacija == null) {
            listIdentifikacija = new ArrayList<>();
        }
        return listIdentifikacija;
    }

    public List<DodPodaciDto> getListDrzavljanstvo() {
        if (listDrzavljanstvo == null) {
            listDrzavljanstvo = new ArrayList<>();
        }
        return listDrzavljanstvo;
    }

    // opisi za prikaz na formi u dataTable
    public String getOpisPolitickiIzlozen() {
        if(StringUtils.isNotBlank(politickiIzlozen)){
            if (politickiIzlozen.equals(GenericBassxConstants.CoreHub.GENERIC_YES_CHAR_1)) {
                return GenericBassxConstants.CoreHub.GENERIC_DESCRIPTION_YES;
            } else {
                return GenericBassxConstants.CoreHub.GENERIC_DESCRIPTION_NO;
            }
        } else {
            return null;
        }
    }
}