/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hr.abc.psd2.model.entity.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dio_partije")
public class DioPartije {

    @Deprecated
    public static final String OZNKADIO_OSNOVNI = "O";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifra_dio", nullable = false)
    private Integer sifraDio;

    // TODO Zasad je O - ima smisla , X - tu samo zbog migracije
    @Column(name = "oznka_dio", nullable = false, length = 10)
    private String oznkaDio;

    @JoinColumn(name = "konto_dio", nullable = false, referencedColumnName = "sifra_kon")
    @ManyToOne(fetch = FetchType.LAZY)
    private Konto kontoDio;

    @JoinColumn(name = "sifnd_dio", nullable = false, referencedColumnName = "sifra_nad")
    @ManyToOne(fetch = FetchType.LAZY)
    private NadDio sifndDio;

    @JoinColumn(name = "tippa_dio", nullable = false, referencedColumnName = "sifra_tip")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipPartije tippaDio;

    @JoinColumn(name = "sifva_dio", nullable = false, referencedColumnName = "sifra_val")
    @ManyToOne(fetch = FetchType.LAZY)
    private Valuta sifvaDio;

    protected DioPartije() {
    }

    @Deprecated
    protected DioPartije(Integer sifraDio, String oznkaDio) {
        this.sifraDio = sifraDio;
        this.oznkaDio = oznkaDio;
    }

    public DioPartije(Integer sifraDio, String oznkaDio, Konto kontoDio, NadDio sifndDio, TipPartije tippaDio, Valuta sifvaDio) {
        super();
        this.sifraDio = sifraDio;
        this.oznkaDio = oznkaDio;
        this.kontoDio = kontoDio;
        this.sifndDio = sifndDio;
        this.tippaDio = tippaDio;
        this.sifvaDio = sifvaDio;
    }

    public Integer getSifraDio() {
        return sifraDio;
    }

    @Deprecated
    protected void setSifraDio(Integer sifraDio) {
        this.sifraDio = sifraDio;
    }

    public String getOznkaDio() {
        return oznkaDio;
    }

    protected void setOznkaDio(String oznkaDio) {
        this.oznkaDio = oznkaDio;
    }

    public Konto getKontoDio() {
        return kontoDio;
    }

    protected void setKontoDio(Konto kontoDio) {
        this.kontoDio = kontoDio;
    }

    public NadDio getSifndDio() {
        return sifndDio;
    }

    protected void setSifndDio(NadDio sifndDio) {
        this.sifndDio = sifndDio;
    }

    public TipPartije getTippaDio() {
        return tippaDio;
    }

    protected void setTippaDio(TipPartije tippaDio) {
        this.tippaDio = tippaDio;
    }

    public Valuta getSifvaDio() {
        return sifvaDio;
    }

    protected void setSifvaDio(Valuta sifvaDio) {
        this.sifvaDio = sifvaDio;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[sifraDio=" + sifraDio + "]";
    }

    protected boolean isKunski() {
        // za osnovni dio se gleda da li postoji revalorizacija (na naddijelu)
        boolean result = this.getSifndDio().getRevalNad().equalsIgnoreCase("NE");
        // za protukonta (ne osnovne) se gleda da li se radi kunskom kontu
        result = result || (!this.getOznkaDio().equals(DioPartije.OZNKADIO_OSNOVNI) && this.getKontoDio().getVkontKon().equals("H"));
        return result;
    }
}
