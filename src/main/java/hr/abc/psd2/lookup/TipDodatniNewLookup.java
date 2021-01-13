package hr.abc.psd2.lookup;

import java.io.Serializable;

import javax.sql.DataSource;

public class TipDodatniNewLookup extends NewLookup<Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public TipDodatniNewLookup() {
        super(null,null,null,null, null);
    }

    public TipDodatniNewLookup(Integer id, DataSource ds) {
        super(id, ds);
    }

    @Override
    protected void setUp(String condition) {
        // ovo je default - ne mijenjati !!!
        setLabelDesignation("Naziv tipa");
        setLabelDescription("Šifra tipa");
        setSqlId("sifra_tip");
        setSqlDesignation("naziv_tip");
        setSqlDescription("sifra_tip||''");
        setSqlFrom("tip_dodpod");
        setSqlWhere("");
        setCaseInSensitive(true);
    }
}
