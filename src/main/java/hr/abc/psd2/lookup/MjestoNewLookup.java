package hr.abc.psd2.lookup;

import java.io.Serializable;

import javax.sql.DataSource;

public class MjestoNewLookup extends NewLookup<Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public MjestoNewLookup() {
        super(null,null,null,null, null);
    }

    public MjestoNewLookup(Integer id, DataSource ds) {
        super(id, ds);
    }

    @Override
    protected void setUp(String condition) {
        if ("Simple".equals(condition)) {
            setLabelDesignation("Naziv Mjesta");
            setLabelDescription("Oznaka Mjesta");
            setTextAlignDescription(TEXT_ALIGN_CENTER);
            setSqlId("sifra_mje");
            setSqlDesignation("naziv_mje");
            setSqlDescription("oznka_mje");
            setSqlFrom("mjesto");
            setSqlWhere("");
        } else {
            // ovo je default - ne mijenjati !!!
            setLabelDesignation("Naziv Mjesta");
            setLabelDescription("Oznaka Mjesta");
            setLabelExtraDesc("Općina");
            setLabelComplexDesc("Pošta");
            setTextAlignDescription(TEXT_ALIGN_CENTER);
            setSqlId("sifra_mje");
            setSqlDesignation("naziv_mje");
            setSqlDescription("oznka_mje");
            setSqlExtraDesc("sifra_opc||' '|| naziv_opc");
            setSqlComplexDesc("sifra_pos||' '|| naziv_pos");
            setSqlFrom("mjesto join opcina on sifra_opc = opcin_mje join posta on sifra_pos = pttbr_mje");
            setSqlWhere("");
        }
        setCaseInSensitive(true);
    }
}
