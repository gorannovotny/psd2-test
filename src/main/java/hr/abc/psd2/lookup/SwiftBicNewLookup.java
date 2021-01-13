package hr.abc.psd2.lookup;

import java.io.Serializable;

import javax.sql.DataSource;

public class SwiftBicNewLookup extends NewLookup<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    public SwiftBicNewLookup() {
        super(null,null,null,null, null);
    }

    public SwiftBicNewLookup(String id, DataSource ds) {
        super(id, ds);
    }

    @Override
    protected void setUp(String condition) {
        // ovo je default - ne mijenjati !!!
        setLabelDesignation("BIC");
        setLabelDescription("Naziv");
        setLabelExtraDesc("Država");
        setLabelExtraDesc("Tip");
        setTextAlignDesignation(TEXT_ALIGN_CENTER);
        setTextAlignComplexDesc(TEXT_ALIGN_CENTER);
        setSqlId("bicco_bic");
        setSqlDesignation("bicco_bic");
        setSqlDescription("iname_bic");
        setSqlExtraDesc("cname_bic");
        setSqlComplexDesc("stype_bic");
        setSqlFrom("swift_bic");
        setSqlWhere("");
        setCaseInSensitive(true);
    }
}
