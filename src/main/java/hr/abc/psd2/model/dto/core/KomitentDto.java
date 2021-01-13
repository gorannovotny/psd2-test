package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.model.dto.GenericDto;
import hr.abc.psd2.util.GenericBassxConstants;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KomitentDto extends GenericDto<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

    // table name, alias & fields
    public static final String TABLE_NAME = "komitent";
    public static final String TABLE_ALIAS = "Komitent"; // isto kao i entity - ne mijenjati i paziti u DB na tip_dodpod gdje se u tabli_tip koristi ovaj alias
    public static final String SIFRA_FIELD = "sifra_kom";
    public static final String OIB_FIELD   = "oib_kom";
    public static final String MATBR_FIELD = "matbr_kom";
    public static final String NAZIV_FIELD = "naziv_kom";
    public static final String DRZAV_FIELD = "drzav_kom";
    public static final String SEKTR_FIELD = "sektr_kom";
    public static final String POJED_FIELD = "pojed_kom";
    public static final String BANKR_FIELD = "bankr_kom";
    public static final String DATPR_FIELD = "datpr_kom";
    public static final String STATU_FIELD = "statu_kom";
    public static final String PRVST_FIELD = "prvst_kom";
    public static final String EXTBL_FIELD = "extbl_kom";
    public static final String INTBL_FIELD = "intbl_kom";
    public static final String RIZIK_FIELD = "rizik_kom";
    public static final String PRIRZ_FIELD = "prirz_kom";
    public static final String VERSI_FIELD = "versi_kom";
    // constants
    public static final int OIB_LENGTH = 11;
    // statuses
    public static final String STATUS_ACTIVE = "A";
    public static final String STATUS_OCCASIONAL = "P";
    public static final String STATUS_OCCASIONAL_NO_ANALYSIS = "Q";
    public static final String STATUS_INACTIVE = "N";
    public static final String STATUS_CURRENTLY_INACTIVE = "K";
    public static final String STATUS_CLOSED = "Z";
    public static final String STATUS_DELETED = "X";
    // roles
    public static final String ROLE_REGULAR = "10";
    // legal statuses
    public static final String LEGAL_STATUS_VALID = "V";
    public static final String LEGAL_STATUS_LIQUIDATION = "L";
    public static final String LEGAL_STATUS_BANKRUPTCY = "S";
    public static final String LEGAL_STATUS_PRE_BANKRUPTCY_SETTLEMENT = "P";
    public static final String LEGAL_STATUS_CLOSED = "Z";
    public static final String LEGAL_STATUS_DELETED = "X";

    public static final String B2_STATUS_ROLE = "uloga";
    public static final String B2_STATUS_BUSINESS_RELATION = "svrhaOdnos";
    public static final String B2_STATUS_SOURCE_OF_FUNDS = "izvorSredstava";
    public static final String B2_STATUS_PREFFERED_COMMUNICATION = "preferiraniKanal";
    public static final String CLIENT_BLOCKADE_TYPE_EXTERNAL = "extBl";
    public static final String CLIENT_BLOCKADE_TYPE_INTERNAL = "intBl";

	// fields
    private String oib;
    private String maticniBroj;
    private String naziv;
    private String sifraDrzave;
    private String sifraSektora;
    private String sifraPoslovnice;
    private Integer sifraOsobnogBankara;
    private Date datumPristupanja;
    private String status;
    private String pravniStatus;
    private String eksternaBlokada;
    private String internaBlokada;
    private Integer tipRizika;
    private String opcinaZaPrirez;
    private Integer version;
    // help fields
    private LokacijaDto lokacijaDto = new LokacijaDto();
    private LokacijaDto lokacijaKorespodencijaDto = new LokacijaDto();
    private String kanalKomunikacije;
    private String email;
    private String mobitel;
    private String telefonPosao;
    private String faks;
    private String napomena;
    private String porezniBroj;
    private BigDecimal postotakPoreza;
    private List<DodPodaciDto> listDodatniPodaciTable;
    private List<DodPodaciDto> listUloge;
    private List<DodPodaciDto> listSvrhaOdnos;
    private List<DodPodaciDto> listIzvorSredstava;
    private List<DodPodaciDto> listDodatniPodaciAll;
    private List<VezaDto> listVezaDto;
    private String nazivDrzave;
    private String nazivSektora;
    private String nazivPoslovnice;
    private String loginOsobnogBankara;
    private String nazivOsobnogBankara;
    private String adresa;
    private String mjesto;
    private String posta;
    private String iban;
    private String opisStatusa;
    private String opisPravnogStatusa;
    private String opisEksterneBlokade;
    private String opisInterneBlokade;
    private String opisTipaRizika;
    private String opisKanalaKomunikacije;
    private boolean pravnaOsoba;
    private boolean imaPartije;
    private Date datumUnosaOd;
    private Date datumUnosaDo;

    private String brojIdentifikacije; // ???

	// constructors
	public KomitentDto() { super(); }

	public KomitentDto(boolean isFirstCall) {
        super(isFirstCall);
    }

    public KomitentDto(String maticniBroj, String oib, String naziv) {
        super();
        this.maticniBroj = maticniBroj;
        this.oib = oib;
        this.naziv = naziv;
    }

	public KomitentDto(Integer sifra, String maticniBroj, String oib, String naziv) {
		super();
		this.sifra = sifra;
		this.maticniBroj = maticniBroj;
		this.oib = oib;
		this.naziv = naziv;

	}
	public KomitentDto(String maticniBroj, String naziv, Integer tipRizika,
                       String sifraDrzave, String nazivDrzave, String sifraPoslovnice,
                       String nazivPoslovnice, String sifraSektora, String nazivSektora,
                       String loginOsobnogBankara, String status, String napomena,
                       Integer sifraOsobnogBankara, Integer version, Date datumPristupanja) {
		super();
		this.maticniBroj = maticniBroj;
		this.naziv = naziv;
		this.tipRizika = tipRizika;
		this.sifraDrzave = sifraDrzave;
		this.nazivDrzave = nazivDrzave;
		this.sifraPoslovnice = sifraPoslovnice;
		this.nazivPoslovnice = nazivPoslovnice;
		this.sifraSektora = sifraSektora;
		this.nazivSektora = nazivSektora;
		this.loginOsobnogBankara = loginOsobnogBankara;
		this.status = status;
		this.napomena = napomena;
		this.sifraOsobnogBankara = sifraOsobnogBankara;
		this.version = version;
		this.datumPristupanja = datumPristupanja;
	}

    @Override
    public String toString() { return getClass().getSimpleName() + "[" + TABLE_ALIAS + "=" + sifra + "]"; }

    // getters & setters
    @Override
	@IFilter(entityField = TABLE_ALIAS + "." + SIFRA_FIELD)
	public Integer getSifra() { return super.getSifra(); }

	public void setSifra(Integer sifra) { this.sifra = sifra; }

    @IFilter(entityField = TABLE_ALIAS + "." + OIB_FIELD)
    public String getOib() { return oib; }

    public void setOib(String oib) { this.oib = oib; }

    @IFilter(entityField = TABLE_ALIAS + "." + MATBR_FIELD)
    public String getMaticniBroj() { return maticniBroj; }

    public void setMaticniBroj(String maticniBroj) { this.maticniBroj = maticniBroj; }

    @IFilter(entityField = TABLE_ALIAS + "." + NAZIV_FIELD)
    public String getNaziv() { return naziv; }

    public void setNaziv(String naziv) { this.naziv = naziv; }

    @IFilter(entityField = TABLE_ALIAS + "." + DRZAV_FIELD)
    public String getSifraDrzave() { return sifraDrzave; }

    public void setSifraDrzave(String sifraDrzave) { this.sifraDrzave = sifraDrzave; }

    @IFilter(entityField = TABLE_ALIAS + "." + SEKTR_FIELD)
    public String getSifraSektora() { return sifraSektora; }

    public void setSifraSektora(String sifraSektora) { this.sifraSektora = sifraSektora; }

    @IFilter(entityField = TABLE_ALIAS + "." + POJED_FIELD)
    public String getSifraPoslovnice() { return sifraPoslovnice; }

    public void setSifraPoslovnice(String sifraPoslovnice) { this.sifraPoslovnice = sifraPoslovnice; }

    @IFilter(entityField = TABLE_ALIAS + "." + BANKR_FIELD)
    public Integer getSifraOsobnogBankara() { return sifraOsobnogBankara; }

    public void setSifraOsobnogBankara(Integer sifraOsobnogBankara) { this.sifraOsobnogBankara = sifraOsobnogBankara; }

    @IFilter(entityField = TABLE_ALIAS + "." + DATPR_FIELD)
    public Date getDatumPristupanja() { return datumPristupanja; }

    public void setDatumPristupanja(Date datumPristupanja) { this.datumPristupanja = datumPristupanja; }

    @IFilter(entityField = TABLE_ALIAS + "." + STATU_FIELD)
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    @IFilter(entityField = TABLE_ALIAS + "." + PRVST_FIELD)
    public String getPravniStatus() { return pravniStatus; }

    public void setPravniStatus(String pravniStatus) { this.pravniStatus = pravniStatus; }

    @IFilter(entityField = TABLE_ALIAS + "." + EXTBL_FIELD)
    public String getEksternaBlokada() { return eksternaBlokada; }

    public void setEksternaBlokada(String eksternaBlokada) { this.eksternaBlokada = eksternaBlokada; }

    @IFilter(entityField = TABLE_ALIAS + "." + INTBL_FIELD)
    public String getInternaBlokada() { return internaBlokada; }

    public void setInternaBlokada(String internaBlokada) { this.internaBlokada = internaBlokada; }

    @IFilter(entityField = TABLE_ALIAS + "." + RIZIK_FIELD)
    public Integer getTipRizika() { return tipRizika; }

    public void setTipRizika(Integer tipRizika) { this.tipRizika = tipRizika; }

    @IFilter(entityField = TABLE_ALIAS + "." + PRIRZ_FIELD)
    public String getOpcinaZaPrirez() { return opcinaZaPrirez; }

    public void setOpcinaZaPrirez(String opcinaZaPrirez) { this.opcinaZaPrirez = opcinaZaPrirez; }

    @IFilter(entityField = TABLE_ALIAS + "." + VERSI_FIELD)
    public Integer getVersion() { return version; }

    public void setVersion(Integer version) { this.version = version; }

    public LokacijaDto getLokacijaDto() { return lokacijaDto; }

    public void setLokacijaDto(LokacijaDto lokacijaDto) { this.lokacijaDto = lokacijaDto; }

    public LokacijaDto getLokacijaKorespodencijaDto() { return lokacijaKorespodencijaDto; }

    public void setLokacijaKorespodencijaDto(LokacijaDto lokacijaKorespodencijaDto) { this.lokacijaKorespodencijaDto = lokacijaKorespodencijaDto; }

    public String getKanalKomunikacije() { return kanalKomunikacije; }

    public void setKanalKomunikacije(String kanalKomunikacije) { this.kanalKomunikacije = kanalKomunikacije; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getMobitel() { return mobitel; }

    public void setMobitel(String mobitel) { this.mobitel = mobitel; }

    public String getTelefonPosao() { return telefonPosao; }

    public void setTelefonPosao(String telefonPosao) { this.telefonPosao = telefonPosao; }

    public String getFaks() { return faks; }

    public void setFaks(String faks) { this.faks = faks; }

    public String getNapomena() { return napomena; }

    public void setNapomena(String napomena) { this.napomena = napomena; }

    public String getPorezniBroj() { return porezniBroj; }

    public void setPorezniBroj(String porezniBroj) { this.porezniBroj = porezniBroj; }

    public BigDecimal getPostotakPoreza() { return postotakPoreza; }

    public void setPostotakPoreza(BigDecimal postotakPoreza) { this.postotakPoreza = postotakPoreza; }

    public List<DodPodaciDto> getListDodatniPodaciTable() {
	    if (listDodatniPodaciTable == null) {
	        listDodatniPodaciTable = new ArrayList<>();
        }
	    return listDodatniPodaciTable;
	}

    public List<DodPodaciDto> getListUloge() {
	    if (listUloge == null) {
	        listUloge = new ArrayList<>();
        }
	    return listUloge;
	}

    public List<DodPodaciDto> getListSvrhaOdnos() {
	    if (listSvrhaOdnos == null) {
	        listSvrhaOdnos =new ArrayList<>();
        }
        return listSvrhaOdnos;
    }

    public List<DodPodaciDto> getListIzvorSredstava() {
	    if (listIzvorSredstava == null) {
	        listIzvorSredstava = new ArrayList<>();
        }
        return listIzvorSredstava;
    }

    public List<DodPodaciDto> getListDodatniPodaciAll() {
        if (listDodatniPodaciAll == null) {
            listDodatniPodaciAll = new ArrayList<>();
        }
        return listDodatniPodaciAll;
    }

    public void setListDodatniPodaciAll(List<DodPodaciDto> listDodatniPodaciAll) { this.listDodatniPodaciAll = listDodatniPodaciAll; }

    public List<VezaDto> getListVezaDto() {
	    if (listVezaDto == null) {
	        listVezaDto = new ArrayList<>();
        }
	    return listVezaDto;
	}

    public void setListVezaDto(List<VezaDto> listVezaDto) { this.listVezaDto = listVezaDto; }

    public String getNazivDrzave() { return nazivDrzave; }

    public void setNazivDrzave(String nazivDrzave) { this.nazivDrzave = nazivDrzave; }

    public String getNazivSektora() { return nazivSektora; }

    public void setNazivSektora(String nazivSektora) { this.nazivSektora = nazivSektora; }

    public String getNazivPoslovnice() { return nazivPoslovnice; }

    public void setNazivPoslovnice(String nazivPoslovnice) { this.nazivPoslovnice = nazivPoslovnice; }

    public String getLoginOsobnogBankara() { return loginOsobnogBankara; }

    public void setLoginOsobnogBankara(String loginOsobnogBankara) { this.loginOsobnogBankara = loginOsobnogBankara; }

    public String getNazivOsobnogBankara() { return nazivOsobnogBankara; }

    public void setNazivOsobnogBankara(String nazivOsobnogBankara) { this.nazivOsobnogBankara = nazivOsobnogBankara; }

    public String getAdresa() { return adresa; }

    public void setAdresa(String adresa) { this.adresa = adresa; }

    public String getMjesto() { return mjesto; }

    public void setMjesto(String mjesto) { this.mjesto = mjesto; }

    public String getPosta() { return posta; }

    public void setPosta(String posta) { this.posta = posta; }

    public String getIban() { return iban; }

    public void setIban(String iban) { this.iban = iban; }

    public String getOpisStatusa() { return opisStatusa; }

    public void setOpisStatusa(String opisStatusa) { this.opisStatusa = opisStatusa; }

    public String getOpisPravnogStatusa() { return opisPravnogStatusa; }

    public void setOpisPravnogStatusa(String opisPravnogStatusa) { this.opisPravnogStatusa = opisPravnogStatusa; }

    public String getOpisEksterneBlokade() { return opisEksterneBlokade; }

    public void setOpisEksterneBlokade(String opisEksterneBlokade) { this.opisEksterneBlokade = opisEksterneBlokade; }

    public String getOpisInterneBlokade() { return opisInterneBlokade; }

    public void setOpisInterneBlokade(String opisInterneBlokade) { this.opisInterneBlokade = opisInterneBlokade; }

    public String getOpisTipaRizika() { return opisTipaRizika; }

    public void setOpisTipaRizika(String opisTipaRizika) { this.opisTipaRizika = opisTipaRizika; }

    public String getOpisKanalaKomunikacije() { return opisKanalaKomunikacije; }

    public void setOpisKanalaKomunikacije(String opisKanalaKomunikacije) { this.opisKanalaKomunikacije = opisKanalaKomunikacije; }

    public String getBrojIdentifikacije() { return brojIdentifikacije; }

    public void setBrojIdentifikacije(String brojIdentifikacije) { this.brojIdentifikacije = brojIdentifikacije; }

    public boolean isPravnaOsoba() { return pravnaOsoba; }

    public void setPravnaOsoba(boolean pravnaOsoba) { this.pravnaOsoba = pravnaOsoba; }

    public boolean isImaPartije() { return imaPartije; }

    public void setImaPartije(boolean imaPartije) { this.imaPartije = imaPartije; }

    public Date getDatumUnosaOd() { return datumUnosaOd; }

    public void setDatumUnosaOd(Date datumUnosaOd) { this.datumUnosaOd = datumUnosaOd; }

    public Date getDatumUnosaDo() { return datumUnosaDo; }

    public void setDatumUnosaDo(Date datumUnosaDo) { this.datumUnosaDo = datumUnosaDo; }

    // provjera blokade
    public boolean isBlocked() { return isBlocked(true, true); }

    public boolean isInternoBlocked() {
        return isBlocked(true, false);
    }

    public boolean isEksternoBlocked() {
        return isBlocked(false, true);
    }

    public boolean isBlocked(boolean interno, boolean eksterno) {
        if (eksterno && getEksternaBlokada() != null) return true;
        if (interno && getInternaBlokada() != null) return true;
        return false;
    }

    public boolean isClosed() { return (getStatus() != null && getStatus().equals(GenericBassxConstants.Core.KOMITENT_STATUS_ZATVOREN)); }
}