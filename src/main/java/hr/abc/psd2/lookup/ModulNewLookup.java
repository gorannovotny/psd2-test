package hr.abc.psd2.lookup;

import java.io.Serializable;

import javax.sql.DataSource;

import hr.abc.psd2.model.dto.core.ModulDto;

public class ModulNewLookup extends NewLookup<Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public ModulNewLookup() {
        super(null,null,null,null, null);
    }

    public ModulNewLookup(Integer id, DataSource ds) {
        super(id, ds);
    }

    @Override
    protected void setUp(String condition) {
        // ovo je default - ne mijenjati !!!
        setLabelDesignation("Naziv modula");
        setLabelDescription("Šifra modula");
        setTextAlignDescription(TEXT_ALIGN_CENTER);
        setSqlId(ModulDto.SIFRA_FIELD);
        setSqlDesignation(ModulDto.NAZIV_FIELD);
        setSqlDescription(ModulDto.SIFRA_FIELD + "||''");
        setSqlFrom(ModulDto.TABLE_NAME);
        setSqlWhere("");
        setCaseInSensitive(true);
    }
}
