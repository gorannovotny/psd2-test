package hr.abc.psd2.model.entity.core;

import java.lang.reflect.Field;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Klasa entiteta za tablicu ib_komitent
 *
 * @author Vlatko Spoljaric
 *
 */
@Entity
@Table(name = "ib_komitent")
public class IBKorisnik {
	public static String STATUS_AKTIVAN = "A";
	public static String STATUS_NEAKTIVAN = "N";
	public static String STATUS_BLOKIRAN = "B";
	
	public static String OVLAS_SAMO_OVLASTENIK="O";
	public static String OVLAS_VLASNIK="V";

    public static final Map<String, String> NEFIN_POLJA_OPIS = Collections
            .unmodifiableMap(new HashMap<String, String>() {
                {
                    put("sifraKom", "Šifra");
                    put("stibkKom", "Status");
                    put("ovlasKom", "Vrsta ovlaštenja");
                    put("vrijeKom", "Vrijeme kreiranja");
                }
            });

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifra_kom", nullable = false) //sifra ib_komitent
    private Integer sifraKom;

    @Column(name = "stibk_kom", length = 1, nullable = false) //status ib_komitent (N, A)
    private String stibkKom;

    @Column(name = "vrije_kom", length = 25, nullable = false) //vrijeme
    @Temporal(TemporalType.TIMESTAMP)
    private Date vrijeKom;

    @JoinColumn(name = "komit_kom", referencedColumnName = "sifra_kom", nullable = true) //sifra fiz_komitent (vanjski kljuc na tablicu fiz_komitent)
    @OneToOne(fetch = FetchType.LAZY)
    private FizKomitent fizkomKom;

    @JoinColumn(name = "likvi_kom", referencedColumnName = "sifra_lik", nullable = false) //sifra likvidator (vanjski kljuc na tablicu likvidator)
    @ManyToOne(fetch = FetchType.LAZY)
    private Likvidator likviKom;

    @Column(name = "ovlas_kom", length = 1) //sigurnosno pitanje
    private String ovlasKom;

//    @Column(name = "pitan_kom", length = 60) //sigurnosno pitanje
//    private String pitanKom;
//
//    @Column(name = "odgov_kom", length = 60) //odgovor na pitanje
//    private String odgovKom;
//
//    @JoinColumn(name = "parti_kom", referencedColumnName = "sifra_par", nullable = false)
//    @OneToOne(fetch = FetchType.LAZY)
//    private Partija partiKom;

    public IBKorisnik() {
	super();
    }

    public Integer getSifraKom() {
	return sifraKom;
    }

    public void setSifraKom(Integer sifraKom) {
	this.sifraKom = sifraKom;
    }

    public String getStibkKom() {
	return stibkKom;
    }

    public void setStibkKom(String stibkKom) {
	this.stibkKom = stibkKom;
    }


    public Date getVrijeKom() {
	return vrijeKom;
    }

    public void setVrijeKom(Date vrijeKom) {
	this.vrijeKom = vrijeKom;
    }

    public FizKomitent getFizkomKom() {
	return fizkomKom;
    }

    public void setFizkomKom(FizKomitent fizkomKom) {
	this.fizkomKom = fizkomKom;
    }

    public Likvidator getLikviKom() {
	    return likviKom;
    }

    public void setLikviKom(Likvidator likviKom) {
	    this.likviKom = likviKom;
    }

    public String getOvlasKom() {
        return ovlasKom;
    }

    public void setOvlasKom(String ovlasKom) {
        this.ovlasKom = ovlasKom;
    }

    @Override
	public String toString() {
		return this.getClass().getSimpleName() + "[sifraKom=" + sifraKom + "]";
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
