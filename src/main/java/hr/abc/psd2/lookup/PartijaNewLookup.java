package hr.abc.psd2.lookup;

import java.io.Serializable;

import javax.sql.DataSource;

import hr.abc.psd2.model.dto.core.KomitentDto;
import hr.abc.psd2.model.dto.core.PartijaDto;
import hr.abc.psd2.util.GenericBassxConstants;

public class PartijaNewLookup extends NewLookup<Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public PartijaNewLookup() {
        super(null,null,null,null, null);
    }

    public PartijaNewLookup(Integer id, DataSource ds) {
        super(id, ds);
    }

    @Override
    protected void setUp(String condition) {
        // ovo je default - ne mijenjati !!!
        setLabelDesignation("Partija");
        setLabelDescription("Naziv komitenta");
        setLabelExtraDesc("OIB");
        setTextAlignDesignation(TEXT_ALIGN_CENTER);
        setTextAlignExtraDesc(TEXT_ALIGN_CENTER);
        setSqlId(PartijaDto.TABLE_ALIAS + "." + PartijaDto.SIFRA_FIELD);
        setSqlDesignation(PartijaDto.TABLE_ALIAS + "." + PartijaDto.PARTI_FIELD);
        setSqlDescription(KomitentDto.TABLE_ALIAS + "." + KomitentDto.NAZIV_FIELD);
        setSqlExtraDesc(KomitentDto.TABLE_ALIAS + "." + KomitentDto.OIB_FIELD);
        setSqlFrom(PartijaDto.TABLE_NAME + " " + PartijaDto.TABLE_ALIAS + " join " + KomitentDto.TABLE_NAME + " " + KomitentDto.TABLE_ALIAS + " on " + KomitentDto.TABLE_ALIAS + "." + KomitentDto.SIFRA_FIELD + " = " + PartijaDto.TABLE_ALIAS + "." + PartijaDto.VLASN_FIELD);
        setSqlWhere("");
        setCaseInSensitive(true);
        if ("AvistaPO".equals(condition)) {
            setLabelComplexDesc("Status");
            setTextAlignComplexDesc(TEXT_ALIGN_CENTER);
            setSqlComplexDesc(PartijaDto.TABLE_ALIAS + "." + PartijaDto.STATU_FIELD);
            setSqlFrom(getSqlFrom() + " join partija_avpo p1 on p1." + PartijaDto.SIFRA_FIELD + " = " + PartijaDto.TABLE_ALIAS + "." + PartijaDto.SIFRA_FIELD);
        } else if ("BankAccount".equals(condition)) {
        	setSqlFrom(getSqlFrom().concat(" inner join tip_partije on sifra_tip = ").concat(PartijaDto.TABLE_ALIAS).concat(".").concat(PartijaDto.TIPPA_FIELD));        	
        	setSqlWhere(" klapa_tip in (".concat(GenericBassxConstants.Core.KLASA_PARTIJE_RACUN_BANKE.toString()).concat(", ").concat(GenericBassxConstants.Core.KLASA_PARTIJE_SIFRAKLP_NAS_KONTOKORENTNI_RACUN.toString()).concat(") "));
        } else {
            setLabelComplexDesc("Status");
            setTextAlignComplexDesc(TEXT_ALIGN_CENTER);
            setSqlComplexDesc(PartijaDto.TABLE_ALIAS + "." + PartijaDto.STATU_FIELD);
        }
    }
}
