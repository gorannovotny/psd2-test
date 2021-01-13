package hr.abc.psd2.model.entity.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "likvidator")
public class Likvidator {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifra_lik", nullable = false)
    private Integer sifraLik;
    @Column(name = "login_lik", nullable = false, length = 35, unique = true)
    private String loginLik;
    @Column(name = "nivop_lik")
    private Integer nivopLik;
    @JoinColumn(name = "komit_lik", referencedColumnName = "sifra_kom")
    @ManyToOne
    private Komitent komitLik;
    @Column(name = "statu_lik", nullable = false, columnDefinition = "CHAR(1)")
    private String statuLik;

    protected Likvidator() {
    }

    protected Likvidator(Integer sifraLik, String loginLik) {
        this.sifraLik = sifraLik;
        this.loginLik = loginLik;
    }

    public Integer getSifraLik() {
        return sifraLik;
    }


    protected void setSifraLik(Integer sifraLik) {
        this.sifraLik = sifraLik;
    }

    public String getLoginLik() {
        return loginLik;
    }

    protected void setLoginLik(String loginLik) {
        this.loginLik = loginLik;
    }

    public Integer getNivopLik() {
        return nivopLik;
    }

    protected void setNivopLik(Integer nivopLik) {
        this.nivopLik = nivopLik;
    }

    public Komitent getKomitLik() {
        return komitLik;
    }

    protected void setKomitLik(Komitent komitLik) {
        this.komitLik = komitLik;
    }

    public String getStatuLik() {
        return statuLik;
    }

    protected void setStatuLik(String statuLik) {
        this.statuLik = statuLik;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[sifraLik=" + sifraLik + "]";
    }
}
