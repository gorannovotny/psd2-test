package hr.abc.psd2.model.entity.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.apache.commons.lang3.StringUtils;

/**
 * Svaki financijski i nefinancijski događaj u financijskoj instituciji prati se s nalozima.
 *
 * @author Matija Hlapčić
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "nalog", indexes = { @Index(name="ix_n_statu_nal", columnList="statu_nal") })
public class Nalog {

	// fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sifra_nal", nullable = false)
	private Integer sifraNal; // broj naloga
	@Column(name = "vrije_nal", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date vrijeNal; // vrijeme nastanka naloga
	@Column(name = "svrha_nal", length = 210)
	private String svrhaNal; // opis naloga
	@Column(name = "statu_nal", columnDefinition = "CHAR(1)")
	private String statuNal; // status naloga
	@JoinColumn(name = "slikv_nal", referencedColumnName = "sifra_lik", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Likvidator slikvNal; // likvidator koji je napravio nalog
	@JoinColumn(name = "aplik_nal", referencedColumnName = "sifra_mod", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Modul aplikNal; // aplikacija u kojoj je napravljen nalog
	@JoinColumn(name = "pojed_nal", referencedColumnName = "sifra_poj", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Pojed pojedNal; // organizacijska jedinica ili poslovnica u kojoj je napravljen nalog
	@JoinColumn(name = "tipna_nal", referencedColumnName = "sifra_pod", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private TipNaloga tipnaNal; // tip naloga koji identificira tip transakcije
	@JoinColumn(name = "storn_nal", referencedColumnName = "sifra_nal")  //nalog s kojim je dotični nalog storniran
	@ManyToOne(fetch = FetchType.LAZY)
	private Nalog stornNal;
    @JoinColumn(name = "prena_nal", referencedColumnName = "sifra_nal")
    @ManyToOne(fetch = FetchType.LAZY)
    private Nalog prenaNal;
	@Version
	@Column(name = "versn_nal", nullable = false)
	private Integer version;
	@Column(name = "stozn_nal", columnDefinition = "CHAR(1)")
	private String stoznNal; // oznaka koja pokazuje da se radi o nalogu storno
    @Column(name = "datkn_nal")
    @Temporal(TemporalType.DATE)
    private Date datknNal; // datum knjiženja
    @Column(name = "datva_nal", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datvaNal; // datum valute
    @Column(name = "vrikn_nal")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vriknNal; // vrijeme knjiženja
    @Column(name = "prior_nal", nullable = false)
    private Short priorNal; // prioritet naloga
    @JoinColumn(name = "gktem_nal", referencedColumnName = "sifra_nal")
    @ManyToOne(fetch = FetchType.LAZY)
    private Nalog gktemNal; // nalog prijenosa u glavnu knjigu
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nalogPrm", fetch = FetchType.LAZY)
    private Collection<Promet> prometCollection = new ArrayList<Promet>(); // lista prometa vezana za dani nalog

	// constructors
	protected Nalog() {
		super();
	}

	public Nalog(Integer sifraNal, Date vrijeNal, String svrhaNal, String statuNal, Likvidator slikvNal, Modul aplikNal, Pojed pojedNal, TipNaloga tipnaNal, Nalog stornNal, Nalog prenaNal, Integer version, String stoznNal) {
		super();
		this.sifraNal = sifraNal;
		this.vrijeNal = vrijeNal;
		this.svrhaNal = svrhaNal;
		this.statuNal = statuNal;
		this.slikvNal = slikvNal;
		this.aplikNal = aplikNal;
		this.pojedNal = pojedNal;
		this.tipnaNal = tipnaNal;
		this.stornNal = stornNal;
		this.prenaNal = prenaNal;
		this.version = version;
		this.stoznNal = stoznNal;
	}
	
	public Nalog(Integer sifraNal, Date vrijeNal, String svrhaNal, String statuNal, Likvidator slikvNal, Modul aplikNal, Pojed pojedNal, TipNaloga tipnaNal, Nalog stornNal, Nalog prenaNal, Integer version, String stoznNal, Date datknNal, Date datvaNal) {
		super();
		this.sifraNal = sifraNal;
		this.vrijeNal = vrijeNal;
		this.svrhaNal = svrhaNal;
		this.statuNal = statuNal;
		this.slikvNal = slikvNal;
		this.aplikNal = aplikNal;
		this.pojedNal = pojedNal;
		this.tipnaNal = tipnaNal;
		this.stornNal = stornNal;
		this.prenaNal = prenaNal;
		this.version = version;
		this.stoznNal = stoznNal;
		this.datknNal = datknNal;
		this.datvaNal = datvaNal;
	}

	public Nalog(Integer sifraNal, Date vrijeNal, String svrhaNal, String statuNal, Likvidator slikvNal, Modul aplikNal, Pojed pojedNal, TipNaloga tipnaNal, Nalog stornNal, Nalog prenaNal, Integer version, String stoznNal, Date datknNal, Date datvaNal, Date vriknNal, Short priorNal, Nalog gktemNal) {
		super();
		this.sifraNal = sifraNal;
		this.vrijeNal = vrijeNal;
		this.svrhaNal = svrhaNal;
		this.statuNal = statuNal;
		this.slikvNal = slikvNal;
		this.aplikNal = aplikNal;
		this.pojedNal = pojedNal;
		this.tipnaNal = tipnaNal;
		this.stornNal = stornNal;
		this.prenaNal = prenaNal;
		this.version = version;
		this.stoznNal = stoznNal;
		this.datknNal = datknNal;
		this.datvaNal = datvaNal;
		this.vriknNal = vriknNal;
		this.priorNal = priorNal;
		this.gktemNal = gktemNal;
	}

	public Nalog(TipNaloga tipnaNal, Date vrijeNal, Date datvaNal, Date datknNal, Short priorNal, String svrhaNal, Modul aplikNal, Pojed pojedNal, Likvidator slikvNal, String statuNal, Nalog prenaNal) {
		super();

		this.svrhaNal = svrhaNal;
		this.vrijeNal = vrijeNal;
		this.tipnaNal = tipnaNal;
		this.datknNal = datknNal;
		this.datvaNal = datvaNal;
		this.priorNal=priorNal;
		this.aplikNal=aplikNal;
		this.pojedNal=pojedNal;
		this.slikvNal=slikvNal;
		this.statuNal=statuNal;
		this.prenaNal=prenaNal;
	}

	// getters & setters
	public Integer getSifraNal() {
		return sifraNal;
	}

	protected void setSifraNal(Integer sifraNal) {
		this.sifraNal = sifraNal;
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
		this.svrhaNal = StringUtils.trim(svrhaNal);
		this.svrhaNal = StringUtils.left(this.svrhaNal, 140);
	}

	public String getStatuNal() {
		return statuNal;
	}

	protected void setStatuNal(String statuNal) {
		this.statuNal = statuNal;
	}

	public Likvidator getSlikvNal() {
		return slikvNal;
	}

	protected void setSlikvNal(Likvidator slikvNal) {
		this.slikvNal = slikvNal;
	}

	public Modul getAplikNal() {
		return aplikNal;
	}

	protected void setAplikNal(Modul aplikNal) {
		this.aplikNal = aplikNal;
	}

	public Pojed getPojedNal() {
		return pojedNal;
	}

	protected void setPojedNal(Pojed pojedNal) {
		this.pojedNal = pojedNal;
	}

	public TipNaloga getTipnaNal() {
		return tipnaNal;
	}

	protected void setTipnaNal(TipNaloga tipnaNal) {
		this.tipnaNal = tipnaNal;
	}


	public Nalog getStornNal() {
		return stornNal;
	}


	protected void setStornNal(Nalog stornNal) {
		this.stornNal = stornNal;
	}

	public Integer getVersion() {
		return version;
	}

	protected void setVersion(Integer version) {
		this.version = version;
	}

	public String getStoznNal() {
		return stoznNal;
	}

	protected void setStoznNal(String stoznNal) {
		this.stoznNal = stoznNal;
	}
	
    public Nalog getPrenaNal() {
        return prenaNal;
    }

    protected void setPrenaNal(Nalog prenaNal) {
        this.prenaNal = prenaNal;
    }

	public Date getDatknNal() {
		return datknNal;
	}

	protected void setDatknNal(Date datknNal) {
		this.datknNal = datknNal;
	}

	public Date getDatvaNal() {
		return datvaNal;
	}

	protected void setDatvaNal(Date datvaNal) {
		this.datvaNal = datvaNal;
	}

	public Date getVriknNal() {
		return vriknNal;
	}

	protected void setVriknNal(Date vriknNal) {
		this.vriknNal = vriknNal;
	}

	public Short getPriorNal() {
		return priorNal;
	}

	protected void setPriorNal(Short priorNal) {
		this.priorNal = priorNal;
	}

	public Nalog getGktemNal() {
		return gktemNal;
	}

	protected void setGktemNal(Nalog gktemNal) {
		this.gktemNal = gktemNal;
	}

	public Collection<Promet> getPrometCollection() {
		return prometCollection;
	}

	protected void setPrometCollection(Collection<Promet> prometCollection) {
		this.prometCollection = prometCollection;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[sifraNal=" + sifraNal + "]";
	}
	
}
