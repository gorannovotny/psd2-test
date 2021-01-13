package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.model.dto.GenericDto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Snjezana
 *
 */
public class PraznikDto extends GenericDto<Integer> implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private Date datumPra;
    private String kanalPra;
    private String nazivPra;
	
	// constructors
	public PraznikDto() {
		super();
	}

    public PraznikDto(Date datumPra, String kanalPra, String nazivPra) {
        this.datumPra = datumPra;
        this.kanalPra = kanalPra;
        this.nazivPra = nazivPra;
    }

    // getters & setters
    @Override
    @IFilter(entityField = "praznik.sifra_pra")
    public Integer getSifra() {
            return sifra;
        }

    @IFilter(entityField = "praznik.datum_pra")
    public Date getDatumPra() {
        return datumPra;
    }

    public void setDatumPra(Date datumPra) {
        this.datumPra = datumPra;
    }

    @IFilter(entityField = "praznik.kanal_pra")
    public String getKanalPra() {
        return kanalPra;
    }

    public void setKanalPra(String kanalPra) {
        this.kanalPra = kanalPra;
    }

    @IFilter(entityField = "praznik.naziv_pra")
    public String getNazivPra() {
        return nazivPra;
    }

    public void setNazivPra(String nazivPra) {this.nazivPra = nazivPra; }
}
