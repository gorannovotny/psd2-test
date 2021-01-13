package hr.abc.psd2.lookup;

import java.io.Serializable;

import javax.sql.DataSource;

public class MfiNewLookup extends NewLookup<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    public MfiNewLookup() {
        super(null,null,null,null, null);
    }

    public MfiNewLookup(String id, DataSource ds) {
        super(id, ds);
    }

    @Override
    protected void setUp(String condition) {
        // ovo je default - ne mijenjati !!!
        setLabelDesignation("Naziv MFI");
        setLabelDescription("Id MFI");
        setTextAlignDescription(TEXT_ALIGN_CENTER);
        setSqlId("mfiid_mfi");
        setSqlDesignation("name_mfi");
        setSqlDescription("mfiid_mfi");
        setSqlFrom("mfi");
        setSqlWhere("");
        setCaseInSensitive(true);
    }
}
