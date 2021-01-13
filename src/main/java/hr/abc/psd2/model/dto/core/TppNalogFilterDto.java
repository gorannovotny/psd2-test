package hr.abc.psd2.model.dto.core;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TppNalogFilterDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date datumOd;
    private Date datumDo;
    private String oznakaValute;
    private String sifraValute;
    private String status;
    private String ibanZaduzenja;
    private String ibanOdobrenja;
    private BigDecimal iznosOd;
    private BigDecimal iznosDo;
    private String nazivCreditor;
    private String svrha;
    private String valutaZaduzenja;
    private String valutaOdobrenja;
    private BigDecimal iznosZaduzenja;
    private BigDecimal iznosOdobranja;
    private String oznakaValuteNaknade;

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public String getOznakaValute() {
        return oznakaValute;
    }

    public void setOznakaValute(String oznakaValute) {
        this.oznakaValute = oznakaValute;
    }

    public String getSifraValute() {
        return sifraValute;
    }

    public void setSifraValute(String sifraValute) {
        this.sifraValute = sifraValute;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIbanZaduzenja() {
        return ibanZaduzenja;
    }

    public void setIbanZaduzenja(String ibanZaduzenja) {
        this.ibanZaduzenja = ibanZaduzenja;
    }

    public BigDecimal getIznosOd() {
        return iznosOd;
    }

    public void setIznosOd(BigDecimal iznosOd) {
        this.iznosOd = iznosOd;
    }

    public BigDecimal getIznosDo() {
        return iznosDo;
    }

    public void setIznosDo(BigDecimal iznosDo) {
        this.iznosDo = iznosDo;
    }

    public String getIbanOdobrenja() {
        return ibanOdobrenja;
    }

    public void setIbanOdobrenja(String ibanOdobrenja) {
        this.ibanOdobrenja = ibanOdobrenja;
    }

    public String getNazivCreditor() {
        return nazivCreditor;
    }

    public void setNazivCreditor(String nazivCreditor) {
        this.nazivCreditor = nazivCreditor;
    }

    public String getSvrha() {
        return svrha;
    }

    public void setSvrha(String svrha) {
        this.svrha = svrha;
    }

    public String getValutaZaduzenja() {
        return valutaZaduzenja;
    }

    public void setValutaZaduzenja(String valutaZaduzenja) {
        this.valutaZaduzenja = valutaZaduzenja;
    }

    public String getValutaOdobrenja() {
        return valutaOdobrenja;
    }

    public void setValutaOdobrenja(String valutaOdobrenja) {
        this.valutaOdobrenja = valutaOdobrenja;
    }

    public BigDecimal getIznosZaduzenja() {
        return iznosZaduzenja;
    }

    public void setIznosZaduzenja(BigDecimal iznosZaduzenja) {
        this.iznosZaduzenja = iznosZaduzenja;
    }

    public BigDecimal getIznosOdobranja() {
        return iznosOdobranja;
    }

    public void setIznosOdobranja(BigDecimal iznosOdobranja) {
        this.iznosOdobranja = iznosOdobranja;
    }

    public String getOznakaValuteNaknade() {
        return oznakaValuteNaknade;
    }

    public void setOznakaValuteNaknade(String oznakaValuteNaknade) {
        this.oznakaValuteNaknade = oznakaValuteNaknade;
    }
}
