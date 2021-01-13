package hr.abc.psd2.lookup;

import java.io.Serializable;

import javax.sql.DataSource;

public class DrzavaNewLookup extends NewLookup<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    public DrzavaNewLookup() {
        super(null,null,null,null, null);
    }

    public DrzavaNewLookup(String id, String designation, String description, String extraDesc, String complexDesc) {
        super(id, designation, description, extraDesc, complexDesc);
    }

    public DrzavaNewLookup(String id, DataSource ds) {
        super(id, ds);
    }

    @Override
    protected void setUp(String condition) {
        if ("Offshore".equals(condition)) {
            setLabelDesignation("Naziv Države");
            setLabelDescription("Šifra Države");
            setLabelExtraDesc("Dvoslovna Oznaka");
            setLabelComplexDesc("Offshore");
            setTextAlignDescription(TEXT_ALIGN_CENTER);
            setTextAlignExtraDesc(TEXT_ALIGN_CENTER);
            setTextAlignComplexDesc(TEXT_ALIGN_CENTER);
            setSqlId("sifra_drz");
            setSqlDesignation("naziv_drz");
            setSqlDescription("sifra_drz");
            setSqlExtraDesc("sidva_drz");
            setSqlComplexDesc("offsh_drz");
            setSqlFrom("drzava");
            setSqlWhere("");
        } else if ("Engleski".equals(condition)) {
            setLabelDesignation("Name");
            setLabelDescription("Code");
            setLabelExtraDesc("Two Letter Code");
            setLabelComplexDesc("Three Letter Code");
            setTextAlignDescription(TEXT_ALIGN_CENTER);
            setTextAlignExtraDesc(TEXT_ALIGN_CENTER);
            setTextAlignComplexDesc(TEXT_ALIGN_CENTER);
            setSqlId("sifra_drz");
            setSqlDesignation("nazen_drz");
            setSqlDescription("sifra_drz");
            setSqlExtraDesc("sidva_drz");
            setSqlComplexDesc("sitri_drz");
            setSqlFrom("drzava");
            setSqlWhere("");
        } else {
            // ovo je default - ne mijenjati !!!
            setLabelDesignation("Naziv Države");
            setLabelDescription("Šifra Države");
            setLabelExtraDesc("Dvoslovna Oznaka");
            setLabelComplexDesc("Troslovna Oznaka");
            setTextAlignDescription(TEXT_ALIGN_CENTER);
            setTextAlignExtraDesc(TEXT_ALIGN_CENTER);
            setTextAlignComplexDesc(TEXT_ALIGN_CENTER);
            setSqlId("sifra_drz");
            setSqlDesignation("naziv_drz");
            setSqlDescription("sifra_drz");
            setSqlExtraDesc("sidva_drz");
            setSqlComplexDesc("sitri_drz");
            setSqlFrom("drzava");
            setSqlWhere("");
        }
        setCaseInSensitive(true);
    }
}
