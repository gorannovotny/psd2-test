package hr.abc.psd2.model.entity.core;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * Entitet za ib naloge (kopija entiteta DevizniNalog bez FK-eva)
 *
 * @author Jasna
 */
@Entity
@Table(name = "ib_nalog")
public class IBNalog {

	public static String STATUS_IB_OBRADA = "O";
	public static String STATUS_IB_OBRISAN = "X";
	public static String STATUS_IB_IZVRSENI = "I";
	public static String STATUS_IB_NEIZVRSENI = "N";
    public static String STATUS_IB_DEFAULT = "D";
    public static String STATUS_IB_OBRADA_I_NAJAVA = "ON";
	public static String SMJER_ZADANO_DUGOVNO = "D";
	public static String SMJER_ZADANO_POTRAZNO = "P";
	public static String VRSTA_NALOG = "K";
	public static String VRSTA_KUPOPRODAJA = "D";
	public static String[] DRZAVE_IBAN_BEZ_KONTROLE = {"UA","RU","JP"};

	public static String FILTER_NACIN_UNOSA_DATOTEKA = "D";

	public static final Map<String, String> NEFIN_POLJA_OPIS = Collections
			.unmodifiableMap(new HashMap<String, String>() {
				{
					put("sifraNal", "Šifra");
					put("iznosNal", "Iznos");
					put("valutNal", "Valuta");
					put("ibanzNal", "IBAN zaduženja");
					put("ibanoNal", "IBAN odobrenja");
					put("nazadNal", "Naziv platitelja"); // naziv debtor
					put("adzadNal", "Adresa platiteljla"); // adresa debtor adresa linija 1
					put("grzadNal", "Grad platitelja"); // grad debtor adresa linija 2
					put("drzadNal", "Država platitelja"); // država debtor
					put("ulnzdNal", "Stvarni platitelj"); // ultimate debtor
					put("ulizdNal", "Stvarni ID platitelja"); // ultimate id debtor
					put("naodoNal", "Naziv primatelja"); // naziv creditor
					put("adodoNal", "Adresa primatelja"); // adresa creditor adresa linija 1
					put("grodoNal", "Grad primatelja"); // grad creditor adresa linija 2
					put("drodoNal", "Država primatelja"); // država creditor
					put("ulnodNal", "Stvarni primatelj"); // ultimate creditor
					put("uliodNal", "Stvarni ID primatelja"); // ultimate id creditor
					put("tropcNal", "Trošak ino banke"); // trošak banke primatelja (BEN | SHA | OUR)
					put("bickoNal", "BIC"); // BIC banke pošiljatelja/primatelja (nalo
					put("svrhaNal", "Opis plaćanja"); // opis naloga
					put("greskNal", "Greška na nalogu"); // opis naloga
					put("pozadNal", "Poziv na broj zaduženja"); // poziv na broj zaduženja
					put("poodoNal", "Poziv na broj odobrenja"); // poziv na broj odobrenja
					put("mozadNal", "Model zaduženja"); // model zaduženja
					put("moodoNal", "Model odobrenja"); // model odobrenja
					put("ktnamNal", "Kategorija namjene"); // kategorija namjene
					put("sinamNal", "Šifra namjene"); // šifra namjene
					put("smjerNal", "Smjer zadavanja naloga"); // smjer zadavanja naloga (D ili P)
					put("tipteNal", "Tip tečaja"); // tip tečaja
					put("medijNal", "Medij"); // medij (papir, elektornski, ...)
					put("bankaNal", "Banka nalogodavatelja"); // banka korisnika/nalogodavatelja - opisno polje koje sadrži podatke koje daje korisnik/nalogodavatelj i koji se ne smiju mjenjati (stari swiftNal)
					put("koresNal", "SWIFT/BIC korenspodenta"); // SWIFT BIC korespodenta nalogodavatelja/korisnika - kontokorent odnosno rambursna banka
					put("vrstaNal", "Vrsta naloga"); // vrsta naloga (K - kunski, D - devizni))
					put("statuIbNal", "Status"); // IB status
					put("batchNal", "Batch booking"); // Batch booking - Y = Da, null = Ne
					put("komitNal", "Šifra IB korisnika");
					put("datotNal", "Šifra datoteke naloga");
					put("hitnoNal", "Oznaka hitnosti"); // hitnost
					put("nlgdvNal", "Šifra nalogodavatelja"); // nalogodavatelj
					put("nalknNal", "Šifra knjižnog naloga"); // knjižni nalog uz koji je vezan platni nalog
					put("batchZbirniNal", "Šifra zbirnog naloga BB"); // sifra zbirnog batch booking naloga
					put("siozpNal", "Šifra oznake posla"); // sifra oznake posla
				}
			});

    //polja iz nalog_devizni
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifra_nal", nullable = false)
    private Integer sifraNal; // broj naloga
    @Column(name = "iznos_nal", columnDefinition = "DEC(17,2)", nullable = false)
    private BigDecimal iznosNal; // iznos naloga
    @Column(name = "valut_nal", length = 3, nullable = false)
    private String valutNal; // valuta u kojoj je zadana transakcija
    @Column(name = "valpk_nal", length = 3)
    private String valpkNal; // valuta pokrića
    @Column(name = "ibanz_nal", length = 34, nullable = false)
    private String ibanzNal; // IBAN zaduženja
    @Column(name = "nazad_nal", length = 70)
    private String nazadNal; // naziv debtor
    @Column(name = "adzad_nal", length = 70)
    private String adzadNal; // adresa debtor adresa linija 1
    @Column(name = "grzad_nal", length = 70)
    private String grzadNal; // grad debtor adresa linija 2
    @Column(name = "drzad_nal", length = 70)
    private String drzadNal; // država debtor
    @Column(name = "ulnzd_nal", length = 70)
    private String ulnzdNal; // ultimate debtor
    @Column(name = "ulizd_nal", length = 35)
    private String ulizdNal; // ultimate id debtor
    @Column(name = "ibano_nal", length = 34, nullable = false)
    private String ibanoNal; // IBAN odobrenja
    @Column(name = "naodo_nal", length = 70)
    private String naodoNal; // naziv creditor
    @Column(name = "adodo_nal", length = 70)
    private String adodoNal; // adresa creditor adresa linija 1
    @Column(name = "grodo_nal", length = 70)
    private String grodoNal; // grad creditor adresa linija 2
    @Column(name = "drodo_nal", length = 70)
    private String drodoNal; // država creditor
    @Column(name = "ulnod_nal", length = 70)
    private String ulnodNal; // ultimate creditor
    @Column(name = "uliod_nal", length = 35)
    private String uliodNal; // ultimate id creditor
    @Column(name = "tropc_nal", length = 3)
    private String tropcNal; // trošak banke primatelja (BEN | SHA | OUR)
//    @Column(name = "bicko_nal", length = 255)
//    private String bickoNal; // BIC banke pošiljatelja/primatelja (nalogodavatelja/korisnika)
    @JoinColumn(name = "nalkn_nal", referencedColumnName = "sifra_nal")
    @ManyToOne(fetch = FetchType.LAZY)
    private Nalog nalknNal; // knjižni nalog uz koji je vezan platni nalog
	
	//polja iz nalog_knjizni
	@Column(name = "datva_nal")
	@Temporal(TemporalType.DATE)
	private Date datvaNal; // Datum valute
	
	//polja iz nalog
	@Column(name = "vrije_nal", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date vrijeNal; // vrijeme nastanka naloga
	@Column(name = "svrha_nal", length = 140)
	private String svrhaNal; // opis naloga
	@Column(name = "gresk_nal", length = 140)
	private String greskNal; // opis naloga
	@Version
	@Column(name = "versn_nal", nullable = false)
	private Integer version;
	
	//polja iz nalog_platni
	@Column(name = "pozad_nal", length = 44)
    private String pozadNal; // poziv na broj zaduženja
	@Column(name = "poodo_nal", length = 44)
    private String poodoNal; // poziv na broj odobrenja
	@Column(name = "mozad_nal", length = 4)
	private String mozadNal; // model zaduženja
	@Column(name = "moodo_nal", length = 4)
    private String moodoNal; // model odobrenja
	@Column(name = "ktnam_nal", length = 4)
    private String ktnamNal; // kategorija namjene
	@Column(name = "hitno_nal", length = 1)
    private Integer hitnoNal; // hitnost
	@Column(name = "nlgdv_nal")
    private Integer nlgdvNal; // nalogodavatelj
	@Column(name = "sinam_nal", length = 4)
    private String sinamNal; // šifra namjene
	@Column(name = "smjer_nal", length = 1)
    private String smjerNal; // smjer zadavanja naloga (D ili P)
	@Column(name = "tipte_nal", length = 3)
    private String tipteNal; // tip tečaja
	
	@Column(name = "medij_nal", length = 1)
    private String medijNal; // medij (papir, elektornski, ...)
	@Column(name = "banka_nal", length = 210)
	private String bankaNal; // banka korisnika/nalogodavatelja - opisno polje koje sadrži podatke koje daje korisnik/nalogodavatelj i koji se ne smiju mjenjati (stari swiftNal)
	@Column(name = "kores_nal", length = 11)
	private String koresNal; // SWIFT BIC korespodenta nalogodavatelja/korisnika - kontokorent odnosno rambursna banka
	
	@Column(name = "vrsta_nal", length = 1)
    private String vrstaNal; // vrsta naloga (K - kunski, D - devizni)
	@JoinColumn(name = "komit_nal", referencedColumnName = "sifra_kom", nullable = false) //sifra ib_komitent (vanjski kljuc na tablicu ib_komitent)
	@ManyToOne(fetch = FetchType.LAZY)
	private IBKorisnik komitNal;

	@JoinColumn(name = "datot_nal", referencedColumnName = "sifra_dat", nullable = true) //sifra ib_datoteke
	@ManyToOne(fetch = FetchType.LAZY)
	private IBDatoteka datotNal;
	
	@Column(name = "nakna_nal", columnDefinition = "DEC(17,2)")
    private BigDecimal naknaNal; // iznos naknade

	@Column(name = "parza_nal", nullable = false)
	private Integer parzaNal;

	@Column(name = "smloc_nal", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date smlocNal; // vrijeme do kad je nalog lockan zbog potpisa smart tokenom

	@Column(name = "statu_nal", length = 1)
	private String statuIbNal; // IB status

	@Column(name = "batch_nal", length = 1)
	private String batchNal; // Batch booking - Y = Da, null = Ne

	@Column(name = "batzb_nal", length = 1)
	private Integer batchZbirniNal; // sifra zbirnog batch booking naloga

    @Column(name = "siozp_nal")
    private Integer siozpNal; // sifra oznake posla
	
    // no argument constructor
    public IBNalog() {
        super();
    }
    
    // getters & setters
    protected void setSifraNal(Integer sifraNal) {
		this.sifraNal = sifraNal;
	}

	protected void setIznosNal(BigDecimal iznosNal) {
		this.iznosNal = iznosNal;
	}

	protected void setValutNal(String valutNal) {
		this.valutNal = valutNal;
	}

	protected void setIbanzNal(String ibanzNal) {
		this.ibanzNal = ibanzNal;
	}

	protected void setNazadNal(String nazadNal) {
		this.nazadNal = nazadNal;
	}

	protected void setAdzadNal(String adzadNal) {
		this.adzadNal = adzadNal;
	}

	protected void setGrzadNal(String grzadNal) {
		this.grzadNal = grzadNal;
	}

	protected void setDrzadNal(String drzadNal) {
		this.drzadNal = drzadNal;
	}

	protected void setUlnzdNal(String ulnzdNal) {
		this.ulnzdNal = ulnzdNal;
	}

	protected void setUlizdNal(String ulizdNal) {
		this.ulizdNal = ulizdNal;
	}

	protected void setIbanoNal(String ibanoNal) {
		this.ibanoNal = ibanoNal;
	}

	protected void setNaodoNal(String naodoNal) {
		this.naodoNal = naodoNal;
	}

	protected void setAdodoNal(String adodoNal) {
		this.adodoNal = adodoNal;
	}

	protected void setGrodoNal(String grodoNal) {
		this.grodoNal = grodoNal;
	}

	protected void setDrodoNal(String drodoNal) {
		this.drodoNal = drodoNal;
	}

	protected void setUlnodNal(String ulnodNal) {
		this.ulnodNal = ulnodNal;
	}

	protected void setUliodNal(String uliodNal) {
		this.uliodNal = uliodNal;
	}

	protected void setTropcNal(String tropcNal) {
		this.tropcNal = tropcNal;
	}

	protected void setNalknNal(Nalog nalknNal) {
		this.nalknNal = nalknNal;
	}

	public Integer getSifraNal() {
		return sifraNal;
	}

	public BigDecimal getIznosNal() {
		return iznosNal;
	}

	public String getValutNal() {
		return valutNal;
	}

	public String getIbanzNal() {
		return ibanzNal;
	}

	public String getNazadNal() {
		return nazadNal;
	}

	public String getAdzadNal() {
		return adzadNal;
	}

	public String getGrzadNal() {
		return grzadNal;
	}

	public String getDrzadNal() {
		return drzadNal;
	}

	public String getUlnzdNal() {
		return ulnzdNal;
	}

	public String getUlizdNal() {
		return ulizdNal;
	}

	public String getIbanoNal() {
		return ibanoNal;
	}

	public String getNaodoNal() {
		return naodoNal;
	}

	public String getAdodoNal() {
		return adodoNal;
	}

	public String getGrodoNal() {
		return grodoNal;
	}

	public String getDrodoNal() {
		return drodoNal;
	}

	public String getUlnodNal() {
		return ulnodNal;
	}

	public String getUliodNal() {
		return uliodNal;
	}

	public String getTropcNal() {
		return tropcNal;
	}

	public Nalog getNalknNal() {
		return nalknNal;
	}

	public Date getDatvaNal() {
		return datvaNal;
	}

	protected void setDatvaNal(Date datvaNal) {
		this.datvaNal = datvaNal;
	}
	
	public Date getVrijeNal() {
		return vrijeNal;
	}

	protected void setVrijeNal(Date vrijeNal) {
		this.vrijeNal = vrijeNal;
	}

	public String getSvrhaNal() {
		return svrhaNal;
	}

	protected void setSvrhaNal(String svrhaNal) {
		this.svrhaNal = svrhaNal;
	}
	
	public String getPozadNal() {
		return pozadNal;
	}

	protected void setPozadNal(String pozadNal) {
		this.pozadNal = pozadNal;
	}

	public String getPoodoNal() {
		return poodoNal;
	}

	protected void setPoodoNal(String poodoNal) {
		this.poodoNal = poodoNal;
	}

	public String getMozadNal() {
		return mozadNal;
	}

	protected void setMozadNal(String mozadNal) {
		this.mozadNal = mozadNal;
	}

	public String getMoodoNal() {
		return moodoNal;
	}

	protected void setMoodoNal(String moodoNal) {
		this.moodoNal = moodoNal;
	}

	public String getKtnamNal() {
		return ktnamNal;
	}

	protected void setKtnamNal(String ktnamNal) {
		this.ktnamNal = ktnamNal;
	}
	
	public Integer getHitnoNal() {
		return hitnoNal;
	}

	protected void setHitnoNal(Integer hitnoNal) {
		this.hitnoNal = hitnoNal;
	}

	public String getMedijNal() {
		return medijNal;
	}

	protected void setMedijNal(String medijNal) {
		this.medijNal = medijNal;
	}

	public String getBankaNal() {
		return bankaNal;
	}

	protected void setBankaNal(String bankaNal) {
		this.bankaNal = bankaNal;
	}

	public String getKoresNal() {
		return koresNal;
	}

	protected void setKoresNal(String koresNal) {
		this.koresNal = koresNal;
	}

	public Integer getVersion() {
		return version;
	}

	protected void setVersion(Integer version) {
		this.version = version;
	}

	public String getVrstaNal() {
		return vrstaNal;
	}

	protected void setVrstaNal(String vrstaNal) {
		this.vrstaNal = vrstaNal;
	}

	public IBKorisnik getKomitNal() {
		return komitNal;
	}

	protected void setKomitNal(IBKorisnik komitNal) {
		this.komitNal = komitNal;
	}

	public Integer getNlgdvNal() {
		return nlgdvNal;
	}

	protected void setNlgdvNal(Integer nlgdvNal) {
		this.nlgdvNal = nlgdvNal;
	}

	public String getSinamNal() {
		return sinamNal;
	}

	protected void setSinamNal(String sinamNal) {
		this.sinamNal = sinamNal;
	}

	public String getSmjerNal() {
		return smjerNal;
	}

	protected void setSmjerNal(String smjerNal) {
		this.smjerNal = smjerNal;
	}
	
	public String getValpkNal() {
		return valpkNal;
	}

	protected void setValpkNal(String valpkNal) {
		this.valpkNal = valpkNal;
	}
	
	public String getTipteNal() {
		return tipteNal;
	}

	protected void setTipteNal(String tipteNal) {
		this.tipteNal = tipteNal;
	}
	
	public BigDecimal getNaknaNal() {
		return naknaNal;
	}

	protected void setNaknaNal(BigDecimal naknaNal) {
		this.naknaNal = naknaNal;
	}

	public String getGreskNal() {
		return greskNal;
	}

	public void setGreskNal(String greskNal) {
		this.greskNal = greskNal;
	}

	public IBDatoteka getDatotNal() {
		return datotNal;
	}

	public void setDatotNal(IBDatoteka datotNal) {
		this.datotNal = datotNal;
	}

	public Integer getParzaNal() {
		return parzaNal;
	}

	public void setParzaNal(Integer parzaNal) {
		this.parzaNal = parzaNal;
	}

	public Date getSmlocNal() {
		return smlocNal;
	}

	public void setSmlocNal(Date smlocNal) {
		this.smlocNal = smlocNal;
	}

	public String getStatuIbNal() {
		return statuIbNal;
	}

	public void setStatuIbNal(String statuIbNal) {
		this.statuIbNal = statuIbNal;
	}

	public Integer getBatchZbirniNal() {
		return batchZbirniNal;
	}

	public void setBatchZbirniNal(Integer batchZbirniNal) {
		this.batchZbirniNal = batchZbirniNal;
	}

	public String getBatchNal() {
		return batchNal;
	}

	public void setBatchNal(String batchNal) {
		this.batchNal = batchNal;
	}

    public Integer getSiozpNal() {
        return siozpNal;
    }

    public void setSiozpNal(Integer siozpNal) {
        this.siozpNal = siozpNal;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[sifraNal=" + sifraNal + "]";
    }

	public Map<String, String> getEntityParametarNameValue(){
		Map<String, String> res = new HashMap<>();
		Class<?> kls = this.getClass();
		Field[] fields = kls.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if(!NEFIN_POLJA_OPIS.containsKey(field.getName())) continue;
				res.put(field.getName(), field.get(this) != null ? field.get(this).toString() : "");
			}catch(IllegalAccessException ex){
				//Nista ne radimo
			}
		}
		return res;
	}
    
}
