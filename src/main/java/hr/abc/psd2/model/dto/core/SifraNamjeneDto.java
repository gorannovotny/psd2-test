package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.model.dto.GenericDto;

import java.io.Serializable;

/**
 *
 * @author Karlo
 */
public class SifraNamjeneDto extends GenericDto<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @IFilter(entityField = "sifra_namjene.klasf_nam")
    private String klasfNam;

    @IFilter(entityField = "sifra_namjene.naziv_nam")
    private String nazivNam;

    public SifraNamjeneDto() {
        super();
    }

    public SifraNamjeneDto(String sifra, String klasfNam, String nazivNam) {
        super();
        this.sifra = sifra;
        this.klasfNam = klasfNam;
        this.nazivNam = nazivNam;
    }

    @IFilter(entityField = "sifra_namjene.sifra_nam")
    @Override
    public String getSifra() {
        return super.getSifra();
    }

    @Override
    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getKlasfNam() {
        return klasfNam;
    }

    public void setKlasfNam(String klasifNam) {
        this.klasfNam = klasifNam;
    }

    public String getNazivNam() {
        return nazivNam;
    }

    public void setNazivNam(String nazivNam) {
        this.nazivNam = nazivNam;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[sifra=" + sifra + "]";
    }
    
}
