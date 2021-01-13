package hr.abc.psd2.lookup;

import java.io.Serializable;

import javax.sql.DataSource;

import hr.abc.psd2.model.dto.core.TipVezeDto;

public class TipVezeNewLookup extends NewLookup<Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public TipVezeNewLookup() {
        super(null,null,null,null, null);
    }

    public TipVezeNewLookup(Integer id, DataSource ds) {
        super(id, ds);
    }

    @Override
    protected void setUp(String condition) {
        // ovo je default - ne mijenjati !!!
        setLabelDesignation("Naziv tipa");
        setLabelDescription("Oznaka tipa");
        setLabelExtraDesc("Opis tipa");
        setLabelComplexDesc("Grupa");
        setTextAlignDescription(TEXT_ALIGN_CENTER);
        setSqlId(TipVezeDto.SIFRA_FIELD);
        setSqlDesignation(TipVezeDto.NAZIV_FIELD);
        setSqlDescription(TipVezeDto.OZNKA_FIELD);
        setSqlExtraDesc(TipVezeDto.OPISV_FIELD);
        setSqlComplexDesc("nacin_grv||' - '||oznka_grv||' - '||naziv_grv");
        //TODO varti ovo - samo za test
        //setSqlFrom(TipVezeDto.TABLE_NAME + " join grupa_veze on sifra_grv = " + TipVezeDto.GRUPA_FIELD + " and nacin_grv in ('KK','PP')");
        setSqlFrom(TipVezeDto.TABLE_NAME + " join grupa_veze on sifra_grv = " + TipVezeDto.GRUPA_FIELD + " and nacin_grv in ('KK','PP','KP')");
        // TODO - B3 - ovo postaviti kad kompar iz B2 prijeđe u B3
//        setSqlFrom(TipVezeDto.TABLE_NAME + " join grupa_veze on sifra_grv = " + TipVezeDto.GRUPA_FIELD);
        setSqlWhere("");
        setCaseInSensitive(true);
    }
}
