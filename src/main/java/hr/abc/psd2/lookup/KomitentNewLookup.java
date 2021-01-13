package hr.abc.psd2.lookup;

import java.io.Serializable;

import javax.sql.DataSource;

import hr.abc.psd2.model.dto.core.FizKomitentDto;
import hr.abc.psd2.model.dto.core.KomitentDto;
import hr.abc.psd2.model.dto.core.PravKomitentDto;

public class KomitentNewLookup extends NewLookup<Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public KomitentNewLookup() {
        super(null,null,null,null, null);
    }

    public KomitentNewLookup(Integer id, DataSource ds) {
        super(id, ds);
    }

    public KomitentNewLookup(Integer id, String designation, String description, String extraDesc, String complexDesc, DataSource dataSource) {
        super(id, designation, description, extraDesc, complexDesc);
        setUp(null);
        kopirajSelection();
    }

    @Override
    protected void setUp(String condition) {
        // ovo je default - ne mijenjati !!!
        setCaseInSensitive(true);
        setLabelDesignation("Naziv");
        setLabelDescription("OIB");
        setLabelExtraDesc("Matični Broj");
        setLabelComplexDesc("Šifra");
        setTextAlignDescription(TEXT_ALIGN_CENTER);
        setTextAlignExtraDesc(TEXT_ALIGN_CENTER);
        if ("basicFO".equals(condition)) {
            setSqlFrom(KomitentDto.TABLE_NAME + " " + KomitentDto.TABLE_ALIAS + " join " + FizKomitentDto.FIZKOM_TABLE_NAME + " " + FizKomitentDto.FIZKOM_TABLE_ALIAS + " on " + FizKomitentDto.FIZKOM_TABLE_ALIAS + "." + FizKomitentDto.SIFRA_FIELD + " = " + KomitentDto.TABLE_ALIAS + "." + KomitentDto.SIFRA_FIELD);
        } else if ("basicPO".equals(condition)) {
            setSqlFrom(KomitentDto.TABLE_NAME + " " + KomitentDto.TABLE_ALIAS + " join " + PravKomitentDto.PRAVKOM_TABLE_NAME + " " + PravKomitentDto.PRAVKOM_TABLE_ALIAS + " on " + PravKomitentDto.PRAVKOM_TABLE_ALIAS + "." + PravKomitentDto.SIFRA_FIELD + " = " + KomitentDto.TABLE_ALIAS + "." + KomitentDto.SIFRA_FIELD);
        } else {
            setSqlFrom(KomitentDto.TABLE_NAME + " " + KomitentDto.TABLE_ALIAS);
        }
        setSqlId(KomitentDto.TABLE_ALIAS + "." + KomitentDto.SIFRA_FIELD);
        setSqlDesignation(KomitentDto.TABLE_ALIAS + "." + KomitentDto.NAZIV_FIELD);
        setSqlDescription(KomitentDto.TABLE_ALIAS + "." + KomitentDto.OIB_FIELD);
        setSqlExtraDesc(KomitentDto.TABLE_ALIAS + "." + KomitentDto.MATBR_FIELD);
        setSqlComplexDesc(KomitentDto.TABLE_ALIAS + "." + KomitentDto.SIFRA_FIELD + "||''");
        setSqlWhere("");
    }
}
