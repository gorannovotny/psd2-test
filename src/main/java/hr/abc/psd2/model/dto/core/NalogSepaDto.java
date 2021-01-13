package hr.abc.psd2.model.dto.core;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import hr.abit.b3.biz.platni.util.PlatniUtil;
import org.apache.commons.lang3.StringUtils;

import com.nimbusds.oauth2.sdk.util.CollectionUtils;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.model.dto.GenericDto;
import hr.abc.psd2.util.Bassx2MinConstants;
import hr.abc.psd2.util.GenericBassxConstants;

/**
 * Created by ivica on 10/12/15.
 */
public class NalogSepaDto extends GenericDto<Integer> implements Serializable {


    private static final long serialVersionUID = 1L;
    //private Integer version;
    // fields -- sepa nalog
    private String rlrefTrn; // releted reference --vezna referenca
    private String grpidTrn; // grupni id
    private String hsvppTrn; // HSVP - polje
    private String hsvpoTrn; // HSVP - polje
    private String tipPoruke; // tip poruke
    private String ciklusTrn; // ciklus naloga
    private Boolean BtchBookg;
    // fields -- platni nalog
    private Integer sifraTrn; // šifra platnog naloga
    private BigDecimal iznos; // iznos naloga
    private String sifraValute; // valuta u kojoj je zadana transakcija
    private String sifraValutePokrica; // valuta pokrića naloga
    private String ibanZaduzenja; // IBAN zaduženja
    private String nazivDebtor, adresaDebtor, gradDebtor, drzavaDebtor, idDebtor, idTypeDebtor, ultimateDebtorNaziv, ultimateDebtorId, ultimateDebtorIdType; // terećenje
    private String ibanOdobrenja; // IBAN odobrenja
    private String nazivCreditor, adresaCreditor, gradCreditor, drzavaCreditor, idCreditor, idTypeCreditor, ultimateCreditorNaziv, ultimateCreditorId, ultimateCreditorIdType; // odobrenje
    private String modelZaduzenja; // model zaduženja
    private String pozivNaBrojZaduzenja; // poziv na broj zaduženja
    private String modelOdobrenja; // model odobrenja
    private String pozivNaBrojOdobrenja; // poziv na broj odobrenja
    private String nacinIzvrsenja; // način izvršenja (vanjski sustavi, interni nalog, MQ, itd.)
    private String smjer; // smjer zadavanja naloga (D ili P)
    private BigDecimal tecajZaduzenja;
    private BigDecimal tecajOdobrenja;
    private String referenca; // referenca
    private String greska; // greška
    private String medij; // medij - porijeklo naloga
    private String ident; // ident - poziv na broj
    private Integer hitnost; // 1 - hitno, 0 - default 2 -i nstant
    private String vrstaTranskacije; // vrsta transakcije (M - međunarodna P - prekogranična N - nacionalna)
    private NalogKnjizniDto knjizniNalog; // knjižni nalog koji je vezan za platni nalog
    private String swiftBicNalogodavateljaKorisnika, swiftBicNalogodavateljaKorisnikaNaziv, swiftBicKrajnjegKorisnika52a; // SWIFT BIC nalogodavatelja/korisnika - krajnje odredište gdje se isplaćuju sredstva
    private String trosakInoBanke; // trošak ino banke BEN, SHA, OUR
    private String sifraNamjene; // šifra namjene - Purpose code
    private String kategorijaNamjene; // kategorija namjene
    private String sifraOpisaPlacanja; // šifra opisa plačanja 190 - sužbeni put, 270 - božićnica ...
    private Integer sifraPrethodnogNaloga; // PrethTrn - platni nalog
    private Integer nalogodavateljSpp; // nalogodavatelj SPP-a
    private String valutaNaplateNaknade;
    private Integer sifraOznakePosla; //šifra oznake posla - šifarnik
    // tranzitna polja
    private String terecenjeOpis, odobrenjeOpis;
    private String tipTecajaZaduzenja;
    private String tipTecajaOdobrenja;
    private String oznakaValute;
    private String painPaymentInfoId;
    private BigDecimal iznosInoTroskaPrimatelja;
    private boolean swiftMessageSimulation = false; // da li se radi o simulaciji SWIFT poruke - kako se ne bi generirao nepotrebno UETR
    // polja za filter
    private Date datumValute;
    private Integer sifraKnjiznogNaloga;
    // utility
    private Map<String, Object> utilMap = new HashMap<String, Object>();// utility mapa za raznu upotrebu
    private String brojPartijeZaduzenja, brojPartijeOdobrenja; // jednom kad se IBAN raspakira, u ovim tranzitnim poljima se čisti broj partije može dalje koristiit
    private PartijaDto partijaZaduzenja, partijaOdobrenja, partijaPokrica;
    //private NalogPlatniDto inPlatni; //samo za drženje ulaznih varijabli ne expozati u getter i setter jer će se polja poduplatni
    private List<NalogHub3Dto> listNalogHub3Dto;
    private List<ObracunNaknadeDto> stavkeNaknade;
    private BigDecimal iznosNaknade;
    private String statusObrade; // status obrade koji ide nezavisno od statusa knjižnog naloga - npr. rasknjiženje izvoda, raspored priljeva, itd.
    private String painMessageId;
    private String painInitiatingPartyName;
    private String painInitiatingPartyOrgId;
    private String painPaymentInformationId;
    private String painPaymentMethod;
    private String origiMsgId;
    private Object returnedValue;
    // polja za IP
    private LocalDateTime vrijemePotpisaNaloga;
    private Integer id, instantMessageId;
    private String uuid, originalInstructionIdentification;
    public NalogSepaDto() {
    	super();
    }

    public NalogSepaDto(boolean isFirstCall) {
		super(isFirstCall);
	}

	public NalogSepaDto(NalogPlatniDto np) {
        this.sifraTrn = np.getSifra();
        this.iznos = np.getIznos();
        this.sifraValute = np.getSifraValute();
        this.sifraValutePokrica = np.getSifraValutePokrica();
        this.ibanZaduzenja = np.getIbanZaduzenja();
        this.nazivDebtor = np.getNazivDebtor();
        this.adresaDebtor = np.getAdresaDebtor();
        this.gradDebtor = np.getGradDebtor();
        this.drzavaDebtor = np.getDrzavaDebtor();
        this.idDebtor = np.getIdDebtor();
        this.ultimateDebtorNaziv = np.getUltimateDebtorNaziv();
        this.ultimateDebtorId = np.getUltimateDebtorId();
        this.ibanOdobrenja = np.getIbanOdobrenja();
        this.nazivCreditor = np.getNazivCreditor();
        this.adresaCreditor = np.getAdresaCreditor();
        this.gradCreditor = np.getGradCreditor();
        this.drzavaCreditor = np.getDrzavaCreditor();
        this.idCreditor = np.getIdCreditor();
        this.ultimateCreditorNaziv = np.getUltimateCreditorNaziv();
        this.ultimateCreditorId = np.getUltimateCreditorId();
        this.modelZaduzenja = np.getModelZaduzenja();
        this.pozivNaBrojZaduzenja = np.getPozivNaBrojZaduzenja();
        this.modelOdobrenja = np.getModelOdobrenja();
        this.pozivNaBrojOdobrenja = np.getPozivNaBrojOdobrenja();
        this.nacinIzvrsenja = np.getNacinIzvrsenja();
        this.smjer = np.getSmjer();
        this.tecajZaduzenja = np.getTecajZaduzenja();
        this.tecajOdobrenja = np.getTecajOdobrenja();
        this.referenca = np.getReferenca();
        this.greska = np.getGreska();
        this.medij = np.getMedij();
        this.vrstaTranskacije = np.getVrstaTransakcije();
        this.ident = np.getIdent();
        this.hitnost = np.getHitnost();
        this.knjizniNalog = np.getKnjizniNalog();
        this.swiftBicNalogodavateljaKorisnika = np.getSwiftNalogodavateljaKorisnika();
        this.swiftBicNalogodavateljaKorisnikaNaziv = np.getSwiftNalogodavateljaKorisnikaNaziv();
        this.trosakInoBanke = np.getTrosakInoBanke();
        this.sifraNamjene = np.getSifraNamjene();
        this.kategorijaNamjene = np.getKategorijaNamjene();
        this.sifraOpisaPlacanja = np.getSifraOpisaPlacanja();
        this.sifraPrethodnogNaloga = np.getSifraPrethodnogNaloga();
        this.utilMap = np.getUtilMap();
        //this.version = np.getVersion();
        this.partijaZaduzenja = np.getPartijaZaduzenja();
        this.partijaOdobrenja = np.getPartijaOdobrenja();
        //this.inPlatni = np;
        this.nalogodavateljSpp = np.getNalogodavateljSpp();
        this.hsvppTrn = np.getHsvppTrn();
        this.hsvpoTrn = np.getHsvpoTrn();
		this.statusObrade = np.getStatusObrade();
        this.BtchBookg = np.getBatchBooking();
        this.painPaymentInfoId = np.getPainPaymentInfoId();
        this.setSifraOznakePosla(np.getSifraOznakePosla());
        this.setGrpidTrn(np.getGrpidTrn());
    }

    public NalogSepaDto(NalogDevizniDto nd) {
        this.sifra = nd.getSifra();
    	this.sifraTrn = nd.getSifra();
        this.iznos = nd.getIznos();
        this.sifraValute = nd.getSifraValute();
        this.sifraValutePokrica = nd.getSifraValutePokrica();
        this.ibanZaduzenja = nd.getIbanZaduzenja();
        this.nazivDebtor = nd.getNazivDebtor();
        this.adresaDebtor = nd.getAdresaDebtor();
        this.gradDebtor = nd.getGradDebtor();
        this.drzavaDebtor = nd.getDrzavaDebtor();
        this.ultimateDebtorNaziv = nd.getUltimateDebtorNaziv();
        this.ultimateDebtorId = nd.getUltimateDebtorId();
        this.ibanOdobrenja = nd.getIbanOdobrenja();
        this.nazivCreditor = nd.getNazivCreditor();
        this.adresaCreditor = nd.getAdresaCreditor();
        this.gradCreditor = nd.getGradCreditor();
        this.drzavaCreditor = nd.getDrzavaCreditor();
        this.ultimateCreditorNaziv = nd.getUltimateCreditorNaziv();
        this.ultimateCreditorId = nd.getUltimateCreditorId();
        this.modelZaduzenja = nd.getModelZaduzenja();
        this.pozivNaBrojZaduzenja = nd.getPozivNaBrojZaduzenja();
        this.modelOdobrenja = nd.getModelOdobrenja();
        this.pozivNaBrojOdobrenja = nd.getPozivNaBrojOdobrenja();
        this.nacinIzvrsenja = nd.getNacinIzvrsenja();
        this.smjer = nd.getSmjer();
        this.tecajZaduzenja = nd.getTecajZaduzenja();
        this.tecajOdobrenja = nd.getTecajOdobrenja();
        this.referenca = nd.getReferenca();
        this.medij = nd.getMedij();
        this.knjizniNalog = nd.getKnjizniNalog();
        this.swiftBicNalogodavateljaKorisnika = nd.getSwiftNalogodavateljaKorisnika();
        this.swiftBicNalogodavateljaKorisnikaNaziv = nd.getSwiftNalogodavateljaKorisnikaNaziv();
        this.trosakInoBanke = nd.getTrosakInoBanke();
        this.sifraPrethodnogNaloga = nd.getSifraPrethodnogNaloga();
        this.oznakaValute = nd.getOznakaValute();
        this.nalogodavateljSpp = nd.getNalogodavateljSpp();
        this.utilMap = nd.getUtilMap();
        if (nd.getBrojDanaUnaprijed() != null && nd.getBrojDanaUnaprijed().compareTo(0) == 0) {
            this.hitnost = GenericBassxConstants.PlatniPromet.PLATNI_NALOG_HITNOST_HITNO;
        }
    }

    public NalogPlatniDto getNalogPlatniDto() {
        NalogPlatniDto inPlatni = new NalogPlatniDto();
        inPlatni.setSifra(getSifraTrn());
        inPlatni.setIznos(getIznos());
        inPlatni.setSifraValute(getSifraValute());
        inPlatni.setSifraValutePokrica(getSifraValutePokrica());
        inPlatni.setIbanZaduzenja(getIbanZaduzenja());
        inPlatni.setNazivDebtor(getNazivDebtor());
        inPlatni.setAdresaDebtor(getAdresaDebtor());
        inPlatni.setGradDebtor(getGradDebtor());
        inPlatni.setDrzavaDebtor(getDrzavaDebtor());
        inPlatni.setIdDebtor(getIdDebtor());
        inPlatni.setUltimateDebtorNaziv(getUltimateDebtorNaziv());
        inPlatni.setUltimateDebtorId(getUltimateDebtorId());
        inPlatni.setIbanOdobrenja(getIbanOdobrenja());
        inPlatni.setNazivCreditor(getNazivCreditor());
        inPlatni.setAdresaCreditor(getAdresaCreditor());
        inPlatni.setGradCreditor(getGradCreditor());
        inPlatni.setDrzavaCreditor(getDrzavaCreditor());
        inPlatni.setIdCreditor(getIdCreditor());
        inPlatni.setUltimateCreditorNaziv(getUltimateCreditorNaziv());
        inPlatni.setUltimateCreditorId(getUltimateCreditorId());
        inPlatni.setModelZaduzenja(getModelZaduzenja());
        inPlatni.setPozivNaBrojZaduzenja(getPozivNaBrojZaduzenja());
        inPlatni.setModelOdobrenja(getModelOdobrenja());
        inPlatni.setPozivNaBrojOdobrenja(getPozivNaBrojOdobrenja());
        inPlatni.setNacinIzvrsenja(getNacinIzvrsenja());
        inPlatni.setSmjer(getSmjer());
        inPlatni.setTecajZaduzenja(getTecajZaduzenja());
        inPlatni.setTecajOdobrenja(getTecajOdobrenja());
        inPlatni.setReferenca(getReferenca());
        inPlatni.setGreska(getGreska());
        inPlatni.setMedij(getMedij());
        inPlatni.setVrstaTransakcije(getVrstaTranskacije());
        inPlatni.setIdent(getIdent());
        inPlatni.setHitnost(getHitnost());
        inPlatni.setKnjizniNalog(getKnjizniNalog());
        inPlatni.setSwiftNalogodavateljaKorisnika(getSwiftBicNalogodavateljaKorisnika());
        inPlatni.setSwiftNalogodavateljaKorisnikaNaziv(getSwiftBicNalogodavateljaKorisnikaNaziv());
        inPlatni.setTrosakInoBanke(getTrosakInoBanke());
        inPlatni.setSifraNamjene(getSifraNamjene());
        inPlatni.setKategorijaNamjene(getKategorijaNamjene());
        inPlatni.setSifraOpisaPlacanja(getSifraOpisaPlacanja());
        inPlatni.setSifraPrethodnogNaloga(getSifraPrethodnogNaloga());
        inPlatni.setUtilMap(getUtilMap());
        //Postavimo i naknadu info u utilMap
//        if(getStavkeNaknade() != null){
//            if(inPlatni.getUtilMap() == null) inPlatni.setUtilMap(new HashMap());
//            inPlatni.getUtilMap().put("naknadaInfoIb", PlatniUtil.getNaknadaInfo(getStavkeNaknade()));
//        }
        //inPlatni.setVersion(getVersion());
        inPlatni.setPartijaZaduzenja(getPartijaZaduzenja());
        inPlatni.setPartijaOdobrenja(getPartijaOdobrenja());
        inPlatni.setNalogodavateljSpp(getNalogodavateljSpp());
        inPlatni.setStatusObrade(getStatusObrade());
        inPlatni.setBatchBooking(getBtchBookg());
        inPlatni.setPainPaymentInfoId(getPainPaymentInfoId());
        inPlatni.setSifraOznakePosla(getSifraOznakePosla());
        inPlatni.setPainMessageId(getPainMessageId());
        inPlatni.setPainInitiatingPartyName(getPainInitiatingPartyName());
        inPlatni.setPainInitiatingPartyOrgId(getPainInitiatingPartyOrgId());
        inPlatni.setPainPaymentInformationId(getPainPaymentInformationId());
        inPlatni.setPainPaymentMethod(getPainPaymentMethod());
        return inPlatni;
    }

    public void setNalogPlatniDto(NalogPlatniDto np) {
        this.setSifraTrn(np.getSifra());
        this.setIznos(np.getIznos());
        this.setSifraValute(np.getSifraValute());
        this.setSifraValutePokrica(np.getSifraValutePokrica());
        this.setIbanZaduzenja(np.getIbanZaduzenja());
        this.setNazivDebtor(np.getNazivDebtor());
        this.setAdresaDebtor(np.getAdresaDebtor());
        this.setGradDebtor(np.getGradDebtor());
        this.setDrzavaDebtor(np.getDrzavaDebtor());
        this.setIdDebtor(np.getIdDebtor());
        this.setUltimateDebtorNaziv(np.getUltimateDebtorNaziv());
        this.setUltimateDebtorId(np.getUltimateDebtorId());
        this.setIbanOdobrenja(np.getIbanOdobrenja());
        this.setNazivCreditor(np.getNazivCreditor());
        this.setAdresaCreditor(np.getAdresaCreditor());
        this.setGradCreditor(np.getGradCreditor());
        this.setDrzavaCreditor(np.getDrzavaCreditor());
        this.setIdCreditor(np.getIdCreditor());
        this.setUltimateCreditorNaziv(np.getUltimateCreditorNaziv());
        this.setUltimateCreditorId(np.getUltimateCreditorId());
        this.setModelZaduzenja(np.getModelZaduzenja());
        this.setPozivNaBrojZaduzenja(np.getPozivNaBrojZaduzenja());
        this.setModelOdobrenja(np.getModelOdobrenja());
        this.setPozivNaBrojOdobrenja(np.getPozivNaBrojOdobrenja());
        this.setNacinIzvrsenja(np.getNacinIzvrsenja());
        this.setSmjer(np.getSmjer());
        this.setTecajZaduzenja(np.getTecajZaduzenja());
        this.setTecajOdobrenja(np.getTecajOdobrenja());
        this.setReferenca(np.getReferenca());
        this.setGreska(np.getGreska());
        this.setMedij(np.getMedij());
        this.setVrstaTranskacije(np.getVrstaTransakcije());
        this.setIdent(np.getIdent());
        this.setHitnost(np.getHitnost());
        this.setKnjizniNalog(np.getKnjizniNalog());
        this.setSwiftBicNalogodavateljaKorisnika(np.getSwiftNalogodavateljaKorisnika());
        this.setSwiftBicNalogodavateljaKorisnikaNaziv(np.getSwiftNalogodavateljaKorisnikaNaziv());
        this.setTrosakInoBanke(np.getTrosakInoBanke());
        this.setSifraNamjene(np.getSifraNamjene());
        this.setKategorijaNamjene(np.getKategorijaNamjene());
        this.setSifraOpisaPlacanja(np.getSifraOpisaPlacanja());
        this.setSifraPrethodnogNaloga(np.getSifraPrethodnogNaloga());
        this.setUtilMap(np.getUtilMap());
        this.setPartijaZaduzenja(np.getPartijaZaduzenja());
        this.setPartijaOdobrenja(np.getPartijaOdobrenja());
        this.setNalogodavateljSpp(np.getNalogodavateljSpp());
        //this.setVersion());
        this.setStatusObrade(np.getStatusObrade());
        this.setBtchBookg(np.getBatchBooking());
        this.setPainPaymentInfoId(np.getPainPaymentInfoId());
        this.setSifraOznakePosla(np.getSifraOznakePosla());
    }

    @Override
    @IFilter(entityField = "nalogSepa.sifra_sct")
    public Integer getSifra() {
        return super.getSifra();
    }

    /*********
     * SEPA dio
     ***********/


    @IFilter(entityField = "nalogSepa.rlref_sct")
    public String getRlrefTrn() {
        return rlrefTrn;
    }

    public void setRlrefTrn(String rlrefTrn) {
        this.rlrefTrn = rlrefTrn;
    }

    @IFilter(entityField = "nalogSepa.grpid_sct")
    public String getGrpidTrn() {
        return grpidTrn;
    }

    public void setGrpidTrn(String grpidTrn) {
        this.grpidTrn = grpidTrn;
    }

    @IFilter(entityField = "nalogSepa.hsvpp_sct")
    public String getHsvppTrn() {
        return hsvppTrn;
    }

    public void setHsvppTrn(String hsvppTrn) {
        this.hsvppTrn = hsvppTrn;
    }

    public Boolean getBtchBookg() {
        return BtchBookg;
    }

    public void setBtchBookg(Boolean btchBookg) {
        BtchBookg = btchBookg;
    }

    @IFilter(entityField = "nalogSepa.hsvpo_sct")
    public String getHsvpoTrn() {
        return hsvpoTrn;
    }

    public void setHsvpoTrn(String hsvpoTrn) {
        this.hsvpoTrn = hsvpoTrn;
    }

    @IFilter(entityField = "nalogSepa.ciklus_sct")
    public String getCiklusTrn() {
        return ciklusTrn;
    }

    public void setCiklusTrn(String ciklusTrn) {
        this.ciklusTrn = ciklusTrn;
    }

    /*********
     * Platni nalog dio
     ***********/


    @IFilter(entityField = "nalogPlatni.sifra_trn")
    public Integer getSifraTrn() {
        return sifraTrn;
    }

    public void setSifraTrn(Integer sifraTrn) {
        this.sifraTrn = sifraTrn;
    }

    @IFilter(entityField = "nalogPlatni.iznos_trn")
    public BigDecimal getIznos() {
        return iznos;
    }

    public void setIznos(BigDecimal iznos) {
        this.iznos = iznos;
    }

    @IFilter(entityField = "nalogPlatni.valut_trn")
    public String getSifraValute() {
        return sifraValute;
    }

    public void setSifraValute(String sifraValute) {
        this.sifraValute = sifraValute;
    }

    @IFilter(entityField = "nalogPlatni.valpk_trn")
    public String getSifraValutePokrica() {
        return sifraValutePokrica;
    }

    public void setSifraValutePokrica(String sifraValutePokrica) {
        this.sifraValutePokrica = sifraValutePokrica;
    }

    @IFilter(entityField = "nalogPlatni.ibanz_trn")
    public String getIbanZaduzenja() {
        return ibanZaduzenja;
    }

    public void setIbanZaduzenja(String ibanZaduzenja) {
        this.ibanZaduzenja = ibanZaduzenja;
    }

    @IFilter(entityField = "nalogPlatni.ibano_trn")
    public String getIbanOdobrenja() {
        return ibanOdobrenja;
    }

    public void setIbanOdobrenja(String ibanOdobrenja) {
        this.ibanOdobrenja = ibanOdobrenja;
    }

    @IFilter(entityField = "nalogPlatni.mozad_trn")
    public String getModelZaduzenja() {
        return modelZaduzenja;
    }

    public void setModelZaduzenja(String modelZaduzenja) {
        this.modelZaduzenja = modelZaduzenja;
    }

    @IFilter(entityField = "nalogPlatni.pozad_trn")
    public String getPozivNaBrojZaduzenja() {
        return pozivNaBrojZaduzenja;
    }

    public void setPozivNaBrojZaduzenja(String pozivNaBrojZaduzenja) {
        this.pozivNaBrojZaduzenja = pozivNaBrojZaduzenja;
    }

    @IFilter(entityField = "nalogPlatni.moodo_trn")
    public String getModelOdobrenja() {
        return modelOdobrenja;
    }

    public void setModelOdobrenja(String modelOdobrenja) {
        this.modelOdobrenja = modelOdobrenja;
    }

    @IFilter(entityField = "nalogPlatni.poodo_trn")
    public String getPozivNaBrojOdobrenja() {
        return pozivNaBrojOdobrenja;
    }

    public void setPozivNaBrojOdobrenja(String pozivNaBrojOdobrenja) {
        this.pozivNaBrojOdobrenja = pozivNaBrojOdobrenja;
    }

    @IFilter(entityField = "nalogPlatni.naciz_trn")
    public String getNacinIzvrsenja() {
        return nacinIzvrsenja;
    }

    public void setNacinIzvrsenja(String nacinIzvrsenja) {
        this.nacinIzvrsenja = nacinIzvrsenja;
    }

    @IFilter(entityField = "nalogPlatni.smjer_trn")
    public String getSmjer() {
        return smjer;
    }

    public void setSmjer(String smjer) {
        this.smjer = smjer;
    }

    @IFilter(entityField = "nalogPlatni.gresk_trn")
    public String getGreska() {
        return greska;
    }

    @IFilter(entityField = "nalogPlatni.sinam_trn")
    public String getSifraNamjene() {
        return sifraNamjene;
    }

    public void setSifraNamjene(String sifraNamjene) {
        this.sifraNamjene = sifraNamjene;
    }

    @IFilter(entityField = "nalogPlatni.siozp_trn")
    public Integer getSifraOznakePosla() {
        return sifraOznakePosla;
    }

    public void setSifraOznakePosla(Integer sifraOznakePosla) {
        this.sifraOznakePosla = sifraOznakePosla;
    }

    public void setGreska(String greska) {
    	setGreska(greska, true);
    }
    
    public void setGreska(String greska, boolean cutTo80Chars) {
        if (cutTo80Chars && greska != null && greska.length() > 80) {
            greska = greska.substring(0, 80);
        }
        this.greska = greska;
    }

//    @IFilter(entityField = "nalogKnjizni.sifra_nal")
//    public Integer getSifraKnjiznogNaloga() {
//        return sifraKnjiznogNaloga;
//    }
//    public void setSifraKnjiznogNaloga(Integer sifraKnjiznogNaloga) {
//        this.sifraKnjiznogNaloga = sifraKnjiznogNaloga;
//    }

    public NalogKnjizniDto getKnjizniNalog() {
        if (knjizniNalog == null) {
            setKnjizniNalog(new NalogKnjizniDto());
        }
        return knjizniNalog;
    }

    public void setKnjizniNalog(NalogKnjizniDto knjizniNalog) {
        this.knjizniNalog = knjizniNalog;
    }

    @IFilter(entityField = "nalogPlatni.ktnamTrn")
    public String getKategorijaNamjene() {
        return kategorijaNamjene;
    }

    public void setKategorijaNamjene(String kategorijaNamjene) {
        this.kategorijaNamjene = kategorijaNamjene;
    }

    @IFilter(entityField = "nalogPlatni.sifop_trn")
    public String getSifraOpisaPlacanja() {
        return sifraOpisaPlacanja;
    }

    public void setSifraOpisaPlacanja(String sifraOpisaPlacanja) {
        this.sifraOpisaPlacanja = sifraOpisaPlacanja;
    }

//    public Integer getVersion() {        return version;    }
//
//    public void setVersion(Integer version) {
//        this.version = version;
//    }

    @IFilter(entityField = "nalogPlatni.medij_trn")
    public String getMedij() {
        return medij;
    }

    public void setMedij(String medij) {
        this.medij = medij;
    }

    public String getVrstaTranskacije() {
        return vrstaTranskacije;
    }

    public void setVrstaTranskacije(String vrstaTranskacije) {
        this.vrstaTranskacije = vrstaTranskacije;
    }

    public Map<String, Object> getUtilMap() {
        return utilMap;
    }

    public void setUtilMap(Map<String, Object> utilMap) {
        this.utilMap = utilMap;
    }

    @IFilter(entityField = "nalogPlatni.nazad_trn")
    public String getNazivDebtor() {
        return nazivDebtor;
    }

    public void setNazivDebtor(String nazivDebtor) {
        this.nazivDebtor = nazivDebtor;
    }

    @IFilter(entityField = "nalogPlatni.adzad_trn")
    public String getAdresaDebtor() {
        return adresaDebtor;
    }

    public void setAdresaDebtor(String adresaDebtor) {
        this.adresaDebtor = adresaDebtor;
    }

    @IFilter(entityField = "nalogPlatni.grzad_trn")
    public String getGradDebtor() {
        return gradDebtor;
    }

    public void setGradDebtor(String gradDebtor) {
        this.gradDebtor = gradDebtor;
    }

    @IFilter(entityField = "nalogPlatni.drzad_trn")
    public String getDrzavaDebtor() {
        return drzavaDebtor;
    }

    public void setDrzavaDebtor(String drzavaDebtor) {
        this.drzavaDebtor = drzavaDebtor;
    }

    @IFilter(entityField = "nalogPlatni.idzad_trn")
    public String getIdDebtor() {
        return idDebtor;
    }

    public void setIdDebtor(String idDebtor) {
        this.idDebtor = idDebtor;
    }

    public String getIdTypeDebtor() {
        return idTypeDebtor;
    }

    public void setIdTypeDebtor(String idTypeDebtor) {
        this.idTypeDebtor = idTypeDebtor;
    }

    @IFilter(entityField = "nalogPlatni.ulnzd_trn")
    public String getUltimateDebtorNaziv() {
        return ultimateDebtorNaziv;
    }

    public void setUltimateDebtorNaziv(String ultimateDebtorNaziv) {
        this.ultimateDebtorNaziv = ultimateDebtorNaziv;
    }

    @IFilter(entityField = "nalogPlatni.ulizd_trn")
    public String getUltimateDebtorId() {
        return ultimateDebtorId;
    }

    public void setUltimateDebtorId(String ultimateDebtorId) {
        this.ultimateDebtorId = ultimateDebtorId;
    }

    public String getUltimateDebtorIdType() {
        return ultimateDebtorIdType;
    }

    public void setUltimateDebtorIdType(String ultimateDebtorIdType) {
        this.ultimateDebtorIdType = ultimateDebtorIdType;
    }

    @IFilter(entityField = "nalogPlatni.naodo_trn")
    public String getNazivCreditor() {
        return nazivCreditor;
    }

    public void setNazivCreditor(String nazivCreditor) {
        this.nazivCreditor = nazivCreditor;
    }

    @IFilter(entityField = "nalogPlatni.adodo_trn")
    public String getAdresaCreditor() {
        return adresaCreditor;
    }

    public void setAdresaCreditor(String adresaCreditor) {
        this.adresaCreditor = adresaCreditor;
    }

    @IFilter(entityField = "nalogPlatni.grodo_trn")
    public String getGradCreditor() {
        return gradCreditor;
    }

    public void setGradCreditor(String gradCreditor) {
        this.gradCreditor = gradCreditor;
    }

    @IFilter(entityField = "nalogPlatni.drodo_trn")
    public String getDrzavaCreditor() {
        return drzavaCreditor;
    }

    public void setDrzavaCreditor(String drzavaCreditor) {
        this.drzavaCreditor = drzavaCreditor;
    }

    @IFilter(entityField = "nalogPlatni.idodo_trn")
    public String getIdCreditor() {
        return idCreditor;
    }

    public void setIdCreditor(String idCreditor) {
        this.idCreditor = idCreditor;
    }

    public String getIdTypeCreditor() {
        return idTypeCreditor;
    }

    public void setIdTypeCreditor(String idTypeCreditor) {
        this.idTypeCreditor = idTypeCreditor;
    }

    @IFilter(entityField = "nalogPlatni.ulnod_trn")
    public String getUltimateCreditorNaziv() {
        return ultimateCreditorNaziv;
    }

    public void setUltimateCreditorNaziv(String ultimateCreditorNaziv) {
        this.ultimateCreditorNaziv = ultimateCreditorNaziv;
    }

    @IFilter(entityField = "nalogPlatni.uliod_trn")
    public String getUltimateCreditorId() {
        return ultimateCreditorId;
    }

    public void setUltimateCreditorId(String ultimateCreditorId) {
        this.ultimateCreditorId = ultimateCreditorId;
    }

    public String getUltimateCreditorIdType() {
        return ultimateCreditorIdType;
    }

    public void setUltimateCreditorIdType(String ultimateCreditorIdType) {
        this.ultimateCreditorIdType = ultimateCreditorIdType;
    }

    @IFilter(entityField = "nalogPlatni.bicko_trn")
    public String getSwiftBicNalogodavateljaKorisnika() {
        return swiftBicNalogodavateljaKorisnika;
    }

    public void setSwiftBicNalogodavateljaKorisnika(String swiftBicNalogodavateljaKorisnika) {
        this.swiftBicNalogodavateljaKorisnika = swiftBicNalogodavateljaKorisnika;
    }

    public String getSwiftBicNalogodavateljaKorisnikaNaziv() {
        return swiftBicNalogodavateljaKorisnikaNaziv;
    }

    public void setSwiftBicNalogodavateljaKorisnikaNaziv(String swiftBicNalogodavateljaKorisnikaNaziv) {
        this.swiftBicNalogodavateljaKorisnikaNaziv = swiftBicNalogodavateljaKorisnikaNaziv;
    }

    @IFilter(entityField = "nalogPlatni.tropc_trn")
    public String getTrosakInoBanke() {
        return trosakInoBanke;
    }

    public void setTrosakInoBanke(String trosakInoBanke) {
        this.trosakInoBanke = trosakInoBanke;
    }

    @IFilter(entityField = "nalogPlatni.refer_trn")
    public String getReferenca() {
        return referenca;
    }

    public void setReferenca(String referenca) {
        this.referenca = referenca;
    }

    @IFilter(entityField = "nalogPlatni.stobr_trn")
    public String getStatusObrade() {
        return statusObrade;
    }

    public void setStatusObrade(String statusObrade) {
        this.statusObrade = statusObrade;
    }

    public BigDecimal getTecajZaduzenja() {
        return tecajZaduzenja;
    }

    public void setTecajZaduzenja(BigDecimal tecajZaduzenja) {
        this.tecajZaduzenja = tecajZaduzenja;
    }

    public BigDecimal getTecajOdobrenja() {
        return tecajOdobrenja;
    }

    public void setTecajOdobrenja(BigDecimal tecajOdobrenja) {
        this.tecajOdobrenja = tecajOdobrenja;
    }

    @IFilter(entityField = "nalogPlatni.preth_trn")
    public Integer getSifraPrethodnogNaloga() {
        return sifraPrethodnogNaloga;
    }

    public void setSifraPrethodnogNaloga(Integer sifraPrethodnogNaloga) {
        this.sifraPrethodnogNaloga = sifraPrethodnogNaloga;
    }

    public Integer getHitnost() {
        return hitnost;
    }

    public void setHitnost(Integer hitnost) {
        this.hitnost = hitnost;
    }

    public PartijaDto getPartijaZaduzenja() {
        return partijaZaduzenja;
    }

    public void setPartijaZaduzenja(PartijaDto partijaZaduzenja) {
        this.partijaZaduzenja = partijaZaduzenja;
    }

    public PartijaDto getPartijaOdobrenja() {
        return partijaOdobrenja;
    }

    public void setPartijaOdobrenja(PartijaDto partijaOdobrenja) {
        this.partijaOdobrenja = partijaOdobrenja;
    }

    public void addExceptions(Exception e) {
        if (e != null) {
            List<Exception> lista = (List<Exception>) this.getUtilMap().get(Bassx2MinConstants.PlatniPromet.UTILMAP_KEY_EXCEPTION);
            if (lista == null) {
                lista = new ArrayList<Exception>();
            }
            lista.add(e);
            this.getUtilMap().put(Bassx2MinConstants.PlatniPromet.UTILMAP_KEY_EXCEPTION, lista);
        }
    }

    public String odljevPriljev() {
        String rez = null;
        //if (PlatniUtil.isRacunMojeBanke(getIbanZaduzenja())) {
        if (true) { // TODO Ivana - dodati pravi uvjet
            rez = Bassx2MinConstants.PlatniPromet.ODLJEV;
        }
        //if (PlatniUtil.isRacunMojeBanke(getIbanOdobrenja())) {
        if (true) { // TODO Ivana - dodati pravi uvjet
            rez = Bassx2MinConstants.PlatniPromet.PRILJEV;
        }
        //if (PlatniUtil.isRacunMojeBanke(getIbanOdobrenja()) && PlatniUtil.isRacunMojeBanke(getIbanZaduzenja())) {
        if (true) { // TODO Ivana - dodati pravi uvjet
            rez = null;
        }
        return rez;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getTerecenjeOpis() {
        return terecenjeOpis;
    }

    public void setTerecenjeOpis(String terecenjeOpis) {
        this.terecenjeOpis = terecenjeOpis;
    }

    public String getOdobrenjeOpis() {
        return odobrenjeOpis;
    }

    public void setOdobrenjeOpis(String odobrenjeOpis) {
        this.odobrenjeOpis = odobrenjeOpis;
    }

    public void resolveHitnost() {
        //setHitnost(PlatniUtil.resolveHitnost(nacinIzvrsenja));
        setHitnost(null); // TODO Ivana
    }

    public void napuniTerecenje() {
        String rez = getNazivDebtor() != null ? getNazivDebtor() : "";
        rez = rez.concat(Bassx2MinConstants.Core.NEW_LINE);
        rez = rez.concat(getAdresaDebtor() != null ? getAdresaDebtor().concat(Bassx2MinConstants.Core.NEW_LINE) : "");
        rez = rez.concat(getGradDebtor() != null ? getGradDebtor().concat(Bassx2MinConstants.Core.NEW_LINE) : "");
        rez = rez.concat(getDrzavaDebtor() != null ? getDrzavaDebtor().concat(Bassx2MinConstants.Core.NEW_LINE) : "");
        setTerecenjeOpis(rez);
    }

    public void napuniOdobrenje() {
        String rez = getNazivCreditor() != null ? getNazivCreditor() : "";
        rez = rez.concat(Bassx2MinConstants.Core.NEW_LINE);
        rez = rez.concat(getAdresaCreditor() != null ? getAdresaCreditor().concat(Bassx2MinConstants.Core.NEW_LINE) : "");
        rez = rez.concat(getGradCreditor() != null ? getGradCreditor().concat(Bassx2MinConstants.Core.NEW_LINE) : "");
        rez = rez.concat(getDrzavaCreditor() != null ? getDrzavaCreditor().concat(Bassx2MinConstants.Core.NEW_LINE) : "");
        setOdobrenjeOpis(rez);
    }

    public void rastepiTerecenje() {
        if (StringUtils.isNotBlank(terecenjeOpis)) {
            for (String s : terecenjeOpis.split(Bassx2MinConstants.Core.NEW_LINE)) {
                if (StringUtils.isBlank(nazivDebtor)) {
                    setNazivDebtor(s);
                } else if (StringUtils.isBlank(adresaDebtor)) {
                    setAdresaDebtor(s);
                } else if (StringUtils.isBlank(gradDebtor)) {
                    setGradDebtor(s);
                } else if (StringUtils.isBlank(drzavaDebtor)) {
                    setDrzavaDebtor(s);
                }
            }
        }
    }

    public void rastepiOdobrenje() {
        if (StringUtils.isNotBlank(odobrenjeOpis)) {
            for (String s : odobrenjeOpis.split(Bassx2MinConstants.Core.NEW_LINE)) {
                if (StringUtils.isBlank(nazivCreditor)) {
                    setNazivCreditor(s);
                } else if (StringUtils.isBlank(adresaCreditor)) {
                    setAdresaCreditor(s);
                } else if (StringUtils.isBlank(gradCreditor)) {
                    setGradCreditor(s);
                } else if (StringUtils.isBlank(drzavaCreditor)) {
                    setDrzavaCreditor(s);
                }
            }
        }
    }

    public String getTipTecajaZaduzenja() {
        return tipTecajaZaduzenja;
    }

    public void setTipTecajaZaduzenja(String tipTecajaZaduzenja) {
        this.tipTecajaZaduzenja = tipTecajaZaduzenja;
    }

    public String getTipTecajaOdobrenja() {
        return tipTecajaOdobrenja;
    }

    public void setTipTecajaOdobrenja(String tipTecajaOdobrenja) {
        this.tipTecajaOdobrenja = tipTecajaOdobrenja;
    }

    @IFilter(entityField = "nalogKnjizni.datva_nal")
    public Date getDatumValute() {
        return datumValute;
    }

    public void setDatumValute(Date datumValute) {
        this.datumValute = datumValute;
    }

    @IFilter(entityField = "nalogKnjizni.sifra_nal")
    public Integer getSifraKnjiznogNaloga() {
        return sifraKnjiznogNaloga;
    }

    public void setSifraKnjiznogNaloga(Integer sifraKnjiznogNaloga) {
        this.sifraKnjiznogNaloga = sifraKnjiznogNaloga;
    }

    public String getOznakaValute() {
        return oznakaValute;
    }

    public void setOznakaValute(String oznakaValute) {
        this.oznakaValute = oznakaValute;
    }

    // filter - getteri koji se koriste kod fomiranja sql-a za dohvat
    @IFilter(entityField = "nalogKnjizni.datkn_nal")
    public Date getDatumKnjizenja() {
        return this.getKnjizniNalog().getDatumKnjizenja();
    }

    @IFilter(entityField = "nalogKnjizni.vrikn_nal")
    public Date getVrijemeKnjizenja() {
        return this.getKnjizniNalog().getVrijemeKnjizenja();
    }

    @IFilter(entityField = "nalogKnjizni.prior_nal")
    public Short getPrioritet() {
        return this.getKnjizniNalog().getPrioritet();
    }
    @IFilter(entityField = "kategorijaNamjene.sifra_kan")
    public String getSifraKategorijaNamjene() {
        return this.getKnjizniNalog().getSifraKategorijaNamjene();
    }

    @IFilter(entityField = "nalog.vrije_nal")
    public Date getVrijemeNastanka() {
        return this.getKnjizniNalog().getVrijemeNastanka();
    }

    @IFilter(entityField = "nalog.svrha_nal")
    public String getSvrha() {
        return this.getKnjizniNalog().getSvrha();
    }

    @IFilter(entityField = "nalog.statu_nal")
    public String getStatus() {
        return this.getKnjizniNalog().getStatus();
    }

    @IFilter(entityField = "likvidator.sifra_lik")
    public Integer getSifraLikvidatora() {
        return this.getKnjizniNalog().getSifraLikvidatora();
    }

    @IFilter(entityField = "nalog.aplik_nal")
    public Integer getSifraModula() {
        return this.getKnjizniNalog().getSifraModula();
    }

    @IFilter(entityField = "nalog.pojed_nal")
    public String getSifraPoslovnice() {
        return this.getKnjizniNalog().getSifraPoslovnice();
    }

    @IFilter(entityField = "nalog.tipna_nal")
    public Integer getSifraTipaNaloga() {
        return this.getKnjizniNalog().getSifraTipaNaloga();
    }

    @IFilter(entityField = "nalog.stornNal")
    public Integer getSifraNalogaStorna() {
        return this.getKnjizniNalog().getSifraNalogaStorna();
    }

    @IFilter(entityField = "nalog.stozn_nal")
    public String getOznakaStorno() {
        return this.getKnjizniNalog().getOznakaStorno();
    }


    @IFilter(entityField = "likvidator.login_lik")
    public String getLoginLikvidatora() {
        return this.getKnjizniNalog().getLoginLikvidatora();
    }

    @IFilter(entityField = "pojed.naziv_poj")
    public String getNazivPoslovnice() {
        return this.getKnjizniNalog().getNazivPoslovnice();
    }

    @IFilter(entityField = "modul.naziv_mod")
    public String getNazivModula() {
        return this.getKnjizniNalog().getNazivModula();
    }

    @IFilter(entityField = "nalog.prena_nal")
    public Integer getSifraNalogaPrethodnika() {
        return this.getKnjizniNalog().getSifraNalogaPrethodnika();
    }

    public String getBrojPartijeZaduzenja() {
        return brojPartijeZaduzenja;
    }

    public void setBrojPartijeZaduzenja(String brojPartijeZaduzenja) {
        this.brojPartijeZaduzenja = brojPartijeZaduzenja;
    }

    public String getBrojPartijeOdobrenja() {
        return brojPartijeOdobrenja;
    }

    public void setBrojPartijeOdobrenja(String brojPartijeOdobrenja) {
        this.brojPartijeOdobrenja = brojPartijeOdobrenja;
    }

    public List<NalogHub3Dto> getListNalogHub3Dto() {
        return listNalogHub3Dto;
    }

    public void setListNalogHub3Dto(List<NalogHub3Dto> listNalogHub3Dto) {
        this.listNalogHub3Dto = listNalogHub3Dto;
    }
	
	public Integer getNalogodavateljSpp() {
		return nalogodavateljSpp;
	}

	public void setNalogodavateljSpp(Integer nalogodavateljSpp) {
		this.nalogodavateljSpp = nalogodavateljSpp;
	}

    public BigDecimal getIznosNaknade() {
        BigDecimal iznosNaknade = null;
        if (CollectionUtils.isNotEmpty(stavkeNaknade)) {
            iznosNaknade = BigDecimal.ZERO;
            for (ObracunNaknadeDto obrDto : getStavkeNaknade()){
                iznosNaknade = iznosNaknade.add(obrDto.getBrutoIznos() !=null ? obrDto.getBrutoIznos() : BigDecimal.ZERO);
            }
        }
        return iznosNaknade;
    }

    public BigDecimal getIznosNaknadeNaplatiOdmah() {
        BigDecimal iznosNaknade = null;
        if (CollectionUtils.isNotEmpty(stavkeNaknade)) {
            iznosNaknade = BigDecimal.ZERO;
            for (ObracunNaknadeDto stavka : getStavkeNaknade()){
                if (stavka.getNaplataNaknade() != null && stavka.getNaplataNaknade().equals(Bassx2MinConstants.Core.TIP_NAPLATE_NAKNADE_ODMAH)) {
                    iznosNaknade = iznosNaknade.add(stavka.getBrutoIznos() !=null ? stavka.getBrutoIznos() : BigDecimal.ZERO);
                }
            }
        }
        return iznosNaknade;
    }


    public List<ObracunNaknadeDto> getStavkeNaknade() {
        return stavkeNaknade;
    }
    public void setStavkeNaknade(List<ObracunNaknadeDto> stavkeNaknade) {
        this.stavkeNaknade = stavkeNaknade;
    }

    public void setIznosNaknade(BigDecimal iznosNaknade) {
        this.iznosNaknade = iznosNaknade;
    }

	public String getTipPoruke() {
		return tipPoruke;
	}

	public void setTipPoruke(String tipPoruke) {
		this.tipPoruke = tipPoruke;
	}
	
	public String getValutaNaplateNaknade() {
		return valutaNaplateNaknade;
	}

	public void setValutaNaplateNaknade(String valutaNaplateNaknade) {
		this.valutaNaplateNaknade = valutaNaplateNaknade;
	}

    public PartijaDto getPartijaPokrica() {
        return partijaPokrica;
    }

    public void setPartijaPokrica(PartijaDto partijaPokrica) {
        this.partijaPokrica = partijaPokrica;
    }

    public String getPainPaymentInfoId() {
        return painPaymentInfoId;
    }

    public void setPainPaymentInfoId(String painPaymentInfoId) {
        this.painPaymentInfoId = painPaymentInfoId;
    }

	public BigDecimal getIznosInoTroskaPrimatelja() {
		return iznosInoTroskaPrimatelja;
	}

	public void setIznosInoTroskaPrimatelja(BigDecimal iznosInoTroskaPrimatelja) {
		this.iznosInoTroskaPrimatelja = iznosInoTroskaPrimatelja;
	}

	public boolean isSwiftMessageSimulation() {
		return swiftMessageSimulation;
	}

	public void setSwiftMessageSimulation(boolean swiftMessageSimulation) {
		this.swiftMessageSimulation = swiftMessageSimulation;
	}

    public String getPainMessageId() {
        return painMessageId;
    }

    public void setPainMessageId(String painMessageId) {
        this.painMessageId = painMessageId;
    }

    public String getPainInitiatingPartyName() {
        return painInitiatingPartyName;
    }

    public void setPainInitiatingPartyName(String painInitiatingPartyName) {
        this.painInitiatingPartyName = painInitiatingPartyName;
    }

    public String getPainInitiatingPartyOrgId() {
        return painInitiatingPartyOrgId;
    }

    public void setPainInitiatingPartyOrgId(String painInitiatingPartyOrgId) {
        this.painInitiatingPartyOrgId = painInitiatingPartyOrgId;
    }

    public String getPainPaymentInformationId() {
        return painPaymentInformationId;
    }

    public void setPainPaymentInformationId(String painPaymentInformationId) {
        this.painPaymentInformationId = painPaymentInformationId;
    }

    public String getPainPaymentMethod() {
        return painPaymentMethod;
    }

    public void setPainPaymentMethod(String painPaymentMethod) {
        this.painPaymentMethod = painPaymentMethod;
    }

	public Object getReturnedValue() {
		return returnedValue;
	}

	public void setReturnedValue(Object returnedValue) {
		this.returnedValue = returnedValue;
	}

	public String getSwiftBicKrajnjegKorisnika52a() {
		return swiftBicKrajnjegKorisnika52a;
	}

	public void setSwiftBicKrajnjegKorisnika52a(String swiftBicKrajnjegKorisnika52a) {
		this.swiftBicKrajnjegKorisnika52a = swiftBicKrajnjegKorisnika52a;
	}

	public LocalDateTime getVrijemePotpisaNaloga() {
		return vrijemePotpisaNaloga;
	}

	public void setVrijemePotpisaNaloga(LocalDateTime vrijemePotpisaNaloga) {
		this.vrijemePotpisaNaloga = vrijemePotpisaNaloga;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getOriginalInstructionIdentification() {
		return originalInstructionIdentification;
	}

	public void setOriginalInstructionIdentification(String originalInstructionIdentification) {
		this.originalInstructionIdentification = originalInstructionIdentification;
	}

	public Integer getInstantMessageId() {
		return instantMessageId;
	}

	public void setInstantMessageId(Integer instantMessageId) {
		this.instantMessageId = instantMessageId;
	}

	public String getOrigiMsgId() {
		return origiMsgId;
	}

	public void setOrigiMsgId(String origiMsgId) {
		this.origiMsgId = origiMsgId;
	}
	
}
