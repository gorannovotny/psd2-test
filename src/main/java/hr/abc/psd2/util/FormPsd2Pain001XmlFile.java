package hr.abc.psd2.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
//import org.olap4j.impl.ArrayMap;

import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.model.dto.core.NalogSepaDto;
import hr.abc.psd2.model.dto.pis.Psd2DatotekaDto;
import hr.abc.psd2.model.dto.pis.TppNalogPlatniDto;
import hr.abc.psd2.model.dto.pis.pain.AccountIdentification4Choice2;
import hr.abc.psd2.model.dto.pis.pain.AccountIdentification4ChoiceHR;
import hr.abc.psd2.model.dto.pis.pain.ActiveOrHistoricCurrencyAndAmount;
import hr.abc.psd2.model.dto.pis.pain.AmountType3Choice;
import hr.abc.psd2.model.dto.pis.pain.BranchAndFinancialInstitutionIdentification41;
import hr.abc.psd2.model.dto.pis.pain.BranchAndFinancialInstitutionIdentification42;
import hr.abc.psd2.model.dto.pis.pain.CashAccount162;
import hr.abc.psd2.model.dto.pis.pain.CashAccount16HR;
import hr.abc.psd2.model.dto.pis.pain.CategoryPurpose1Choice;
import hr.abc.psd2.model.dto.pis.pain.ChargeBearerType1Code;
import hr.abc.psd2.model.dto.pis.pain.CreditTransferTransactionInformation10;
import hr.abc.psd2.model.dto.pis.pain.CreditorReferenceInformation2;
import hr.abc.psd2.model.dto.pis.pain.CreditorReferenceType1Choice;
import hr.abc.psd2.model.dto.pis.pain.CreditorReferenceType2;
import hr.abc.psd2.model.dto.pis.pain.CustomerCreditTransferInitiationV03;
import hr.abc.psd2.model.dto.pis.pain.Document;
import hr.abc.psd2.model.dto.pis.pain.DocumentType3Code;
import hr.abc.psd2.model.dto.pis.pain.FinancialInstitutionIdentification71;
import hr.abc.psd2.model.dto.pis.pain.FinancialInstitutionIdentification72;
import hr.abc.psd2.model.dto.pis.pain.GenericOrganisationIdentification1;
import hr.abc.psd2.model.dto.pis.pain.GenericPersonIdentification1;
import hr.abc.psd2.model.dto.pis.pain.GroupHeader32;
import hr.abc.psd2.model.dto.pis.pain.OrganisationIdentification4;
import hr.abc.psd2.model.dto.pis.pain.Party6Choice;
import hr.abc.psd2.model.dto.pis.pain.PartyIdentification321;
import hr.abc.psd2.model.dto.pis.pain.PartyIdentification322;
import hr.abc.psd2.model.dto.pis.pain.PaymentIdentification1;
import hr.abc.psd2.model.dto.pis.pain.PaymentInstructionInformation3;
import hr.abc.psd2.model.dto.pis.pain.PaymentMethod3Code;
import hr.abc.psd2.model.dto.pis.pain.PaymentTypeInformation191;
import hr.abc.psd2.model.dto.pis.pain.PaymentTypeInformation192;
import hr.abc.psd2.model.dto.pis.pain.PersonIdentification5;
import hr.abc.psd2.model.dto.pis.pain.PostalAddress6;
import hr.abc.psd2.model.dto.pis.pain.Priority2Code;
import hr.abc.psd2.model.dto.pis.pain.Purpose2Choice;
import hr.abc.psd2.model.dto.pis.pain.RemittanceInformation5;
import hr.abc.psd2.model.dto.pis.pain.StructuredRemittanceInformation7;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Dependent
public class FormPsd2Pain001XmlFile implements Serializable{

    private static final long serialVersionUID = 1L;

	/**
     * Formiranje izlazne datoteke iz liste dto-a (TppNalogPlatniDto)
     *
     */
    public Document formXmlFromDtos(List<TppNalogPlatniDto> listDtos, Psd2DatotekaDto psd2DatotekaDto, EntityManager entityManager) throws AbitRuleException {
        Document pain = new Document();
        pain.setCstmrCdtTrfInitn(new CustomerCreditTransferInitiationV03());
        //header
        pain.getCstmrCdtTrfInitn().setGrpHdr(grupHeader(listDtos, psd2DatotekaDto));
        //mapa grupa (PmtInf) - lista naloga
        if (listDtos != null && !listDtos.isEmpty()) {
            listDtos = zamijeniUSifruValute(listDtos, entityManager);
            Map<String, LinkedList<TppNalogPlatniDto>> mapNalogaPoGrupama = new LinkedHashMap();
            Set<String> listaGrupa = new LinkedHashSet<>();
            for (TppNalogPlatniDto tppNalogPlatniDto : listDtos) {
                listaGrupa.add(tppNalogPlatniDto.getPainPaymentInformationId());
            }
            for (String grupa : listaGrupa) {
                LinkedList<TppNalogPlatniDto> listNalogaGrupa = new LinkedList<>();
                for (TppNalogPlatniDto tppNalogPlatniDto: listDtos) {
                    if (StringUtils.equals(tppNalogPlatniDto.getPainPaymentInformationId(), grupa)) {
                        listNalogaGrupa.add(tppNalogPlatniDto);
                    }
                }
                mapNalogaPoGrupama.put(grupa, listNalogaGrupa);
            }
            //PmtInf
            for (String grupa : mapNalogaPoGrupama.keySet()) {
                LinkedList<TppNalogPlatniDto> listNalogaGrupa = mapNalogaPoGrupama.get(grupa);
                PaymentInstructionInformation3 pmtInf = new PaymentInstructionInformation3();
                pmtInf.setPmtInfId(grupa);
                pmtInf.setPmtMtd(PaymentMethod3Code.valueOf(listNalogaGrupa.getFirst().getPainPaymentMethod()));
                pmtInf.setBtchBookg(listNalogaGrupa.getFirst().getBatchBooking());
                pmtInf.setNbOfTxs(Integer.toString(listNalogaGrupa.size()));
                BigDecimal suma = BigDecimal.ZERO;
                for (TppNalogPlatniDto dto : listNalogaGrupa) {
                    if (dto.getIznos() != null) {
                        suma = suma.add(dto.getIznos());
                    }
                }
                pmtInf.setCtrlSum(suma.setScale(2, RoundingMode.HALF_UP));
                //PmtTpInf
                PaymentTypeInformation191 paymentTypeInformation191 = new PaymentTypeInformation191();
                if (listNalogaGrupa.getFirst().isHitno()) {
                    paymentTypeInformation191.setInstrPrty(Priority2Code.HIGH);
                } else {
                    paymentTypeInformation191.setInstrPrty(Priority2Code.NORM);
                }
                if (StringUtils.isNotBlank(listNalogaGrupa.getFirst().getSifraNamjene())) {
                    CategoryPurpose1Choice categoryPurpose1Choice = new CategoryPurpose1Choice();
                    categoryPurpose1Choice.setCd(listNalogaGrupa.getFirst().getSifraNamjene());
                    paymentTypeInformation191.setCtgyPurp(categoryPurpose1Choice);
                }
                pmtInf.setPmtTpInf(paymentTypeInformation191);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                pmtInf.setReqdExctnDt(DateUtil.resolveXMLGregorianCalendarWithFormat(listNalogaGrupa.getFirst().getDatumValute(), df));
                //Dbtr
                PartyIdentification322 partyIdentification322 = new PartyIdentification322();
                partyIdentification322.setNm(listNalogaGrupa.getFirst().getNazivDebtor());
                PostalAddress6 postalAddress6 = new PostalAddress6();
                if (StringUtils.isNotBlank(listNalogaGrupa.getFirst().getDrzavaDebtor())) {
                    postalAddress6.setCtry(listNalogaGrupa.getFirst().getDrzavaDebtor());
                }
                if (StringUtils.isNotBlank(listNalogaGrupa.getFirst().getAdresaDebtor())) {
                    postalAddress6.getAdrLine().add(listNalogaGrupa.getFirst().getAdresaDebtor());
                }
                if (StringUtils.isNotBlank(listNalogaGrupa.getFirst().getGradDebtor())) {
                    postalAddress6.getAdrLine().add(listNalogaGrupa.getFirst().getGradDebtor());
                }
                partyIdentification322.setPstlAdr(postalAddress6);
                pmtInf.setDbtr(partyIdentification322);
                //DbtrAcct
                CashAccount16HR cashAccount16HR = new CashAccount16HR();
                cashAccount16HR.setId(new AccountIdentification4ChoiceHR());
                cashAccount16HR.getId().setIBAN(listNalogaGrupa.getFirst().getIbanZaduzenja());
                cashAccount16HR.setCcy(listNalogaGrupa.getFirst().getSifraValute());
                pmtInf.setDbtrAcct(cashAccount16HR);
                pmtInf.setDbtrAcct(cashAccount16HR);
                //DbtrAgt
                if (StringUtils.isNotBlank(listNalogaGrupa.getFirst().getSwiftNalogodavateljaKorisnika())) {
                    BranchAndFinancialInstitutionIdentification41 bic = new BranchAndFinancialInstitutionIdentification41();
                    bic.setFinInstnId(new FinancialInstitutionIdentification71());
                    bic.getFinInstnId().setBIC(listNalogaGrupa.getFirst().getSwiftNalogodavateljaKorisnika());
                    pmtInf.setDbtrAgt(bic);
                }
                //CdtTrfTxInf nalozi
                for (TppNalogPlatniDto tppNalogPlatniDto : listNalogaGrupa) {
                    CreditTransferTransactionInformation10 creditTransferTransactionInf = new CreditTransferTransactionInformation10();
                    creditTransferTransactionInf = formCreditor(tppNalogPlatniDto);
                    pmtInf.getCdtTrfTxInf().add(creditTransferTransactionInf);
                }
                pain.getCstmrCdtTrfInitn().getPmtInf().add(pmtInf);
            }
        }
           return pain;
    }


    public static List<TppNalogPlatniDto> zamijeniUSifruValute(List<TppNalogPlatniDto> nalogUPPDtos, EntityManager em) {
        String oznakaValute = null;
        Map<String, String> mapVal = new LinkedHashMap<>();
        List<Objects[]> listVal = em.createNativeQuery("select sifra_val,valuta.oznka_val from valuta").getResultList();
        for (Object[] obj : listVal) {
            mapVal.put((String) obj[0], (String) obj[1]);
        }
        for (TppNalogPlatniDto dto : nalogUPPDtos) {
            if (StringUtils.isNumeric(dto.getSifraValutePokrica())) {
                dto.setSifraValutePokrica(mapVal.get(dto.getSifraValutePokrica()));
            }
            if (StringUtils.isNumeric(dto.getSifraValute())) {
                dto.setSifraValute(mapVal.get(dto.getSifraValute()));
            }
            if (StringUtils.isBlank(dto.getSifraValutePokrica())) {
                dto.setSifraValutePokrica(dto.getSifraValute());
            }
        }
        return nalogUPPDtos;
    }


//    private static void marshallToFile(Object inputObject, String fileName, String encoding) throws AbitRuleException {
//        try {
//            Marshaller marshaller = JAXBContext.newInstance(inputObject.getClass()).createMarshaller();
//            QName qName = new QName(XMLConstants.NULL_NS_URI, inputObject.getClass().getSimpleName());
//            JAXBElement<Object> rootElement = new JAXBElement<Object>(qName, (Class) inputObject.getClass(), inputObject);
//            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
//            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
//            marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new DefaultNamespacePrefixMapper());
//            marshaller.marshal(rootElement, new FileOutputStream(fileName));
//        } catch (FileNotFoundException fe) {
//            log.getLogger(XmlUtil.class.getName()).log(Level.WARNING, fe.getMessage());
//            throw new AbitRuleException(InternationalizationUtil.getLocalMessage("util_xmlUtil_marshallToFile_fileNotFound", new String[] { fileName }, Bassx2Constants.CoreHub.MESSAGE_BUNDLE));
//        } catch (JAXBException xe) {
//            log.getLogger(XmlUtil.class.getName()).log(Level.SEVERE, xe.getMessage());
//            throw new AbitRuleException(InternationalizationUtil.getLocalMessage("util_xmlUtil_marshallToFile_jaxbException", new String[] { fileName }, Bassx2Constants.CoreHub.MESSAGE_BUNDLE));
//        }
//    }

    /**
     * funkcija radi sa parametrom useNsPrefix = false
     * if (useNsPrefix) marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new DefaultNamespacePrefixMapper());
     * u kombinaciji sa pacakge-info gdje su postavljeni ostali prefiksi
     * @param doc
     * @param path
     * @ author Ivica
     */
    public String formFile(Document doc, String path) throws AbitRuleException {
        path = path + "/" + doc.getCstmrCdtTrfInitn().getGrpHdr().getMsgId();
        path = path + ".xml";
        //String contents = XmlUtil.marshalToString(doc, true, true, "UTF-8", false);
        String contents = null; // TODO Ivana
        File file = new File(path);
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(contents);
            out.close();
        } catch (IOException e) {
            //log.getLogger(XmlUtil.class.getName()).log(Level.WARNING, e.getMessage());
            log.warn("Warning" , e); // TODO Ivana
            throw new AbitRuleException(InternationalizationUtil.getLocalMessage("util_xmlUtil_marshallToFile_fileNotFound", new String[]{path}, Bassx2Constants.CoreHub.MESSAGE_BUNDLE));
        }
        return path;
    }

    private GroupHeader32 grupHeader(List<TppNalogPlatniDto> listaSepaDtos, Psd2DatotekaDto psd2DatotekaDto) throws AbitRuleException {
        GroupHeader32 groupHeader32 = new GroupHeader32();
        BigDecimal suma = BigDecimal.ZERO;
        for (TppNalogPlatniDto dto : listaSepaDtos) {
            if (dto.getIznos() != null) {
                suma = suma.add(dto.getIznos());
            }
        }
        groupHeader32.setMsgId(psd2DatotekaDto.getNazivDatoteke());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        groupHeader32.setCreDtTm(DateUtil.resolveXMLGregorianCalendarWithFormat(psd2DatotekaDto.getVrijemeNastankaDatoteke(), df));
        groupHeader32.setNbOfTxs(String.valueOf(listaSepaDtos.size()));
        groupHeader32.setCtrlSum(suma.setScale(2, RoundingMode.HALF_UP));
        groupHeader32.setInitgPty(new PartyIdentification321());
        groupHeader32.getInitgPty().setNm(StringUtils.isNotBlank(psd2DatotekaDto.getInicijatorPlacanjaNaziv()) ? psd2DatotekaDto.getInicijatorPlacanjaNaziv() : "");
        if (StringUtils.isNotBlank(psd2DatotekaDto.getInicijatorPlacanjaId())) {
            groupHeader32.getInitgPty().setId(new Party6Choice());
            groupHeader32.getInitgPty().getId().setOrgId(new OrganisationIdentification4());
            groupHeader32.getInitgPty().getId().getOrgId().setOthr(new GenericOrganisationIdentification1());
            groupHeader32.getInitgPty().getId().getOrgId().getOthr().setId(psd2DatotekaDto.getInicijatorPlacanjaId());

        }

        return groupHeader32;
    }


    /**
     * Creditor
     */
    private CreditTransferTransactionInformation10 formCreditor(TppNalogPlatniDto tppNalogPlatniDto) {
        CreditTransferTransactionInformation10 creditTransferTransactionInf = new CreditTransferTransactionInformation10();
        if (tppNalogPlatniDto != null) {
            creditTransferTransactionInf.setPmtId(new PaymentIdentification1());
            if (StringUtils.isBlank(tppNalogPlatniDto.getModelZaduzenja())){
                tppNalogPlatniDto.setModelZaduzenja("HR00");
            }
            if (StringUtils.isBlank(tppNalogPlatniDto.getPozivNaBrojZaduzenja())) {
                tppNalogPlatniDto.setPozivNaBrojZaduzenja("");
            }
            creditTransferTransactionInf.getPmtId().setEndToEndId(StringUtils.strip(StringUtils.stripToEmpty(tppNalogPlatniDto.getModelZaduzenja()) + StringUtils.stripToEmpty(tppNalogPlatniDto.getPozivNaBrojZaduzenja())));

            if (StringUtils.equals(Bassx2Constants.PlatniPromet.DEVIZNI_NALOG_TROSAK_INO_BANKE_SHA, tppNalogPlatniDto.getTrosakInoBanke())) {
                creditTransferTransactionInf.setChrgBr(ChargeBearerType1Code.SHAR);
            }

            if (StringUtils.equals(Bassx2Constants.PlatniPromet.DEVIZNI_NALOG_TROSAK_INO_BANKE_OUR, tppNalogPlatniDto.getTrosakInoBanke())) {
                creditTransferTransactionInf.setChrgBr(ChargeBearerType1Code.DEBT);
            }
            if (StringUtils.equals(Bassx2Constants.PlatniPromet.DEVIZNI_NALOG_TROSAK_INO_BANKE_BEN, tppNalogPlatniDto.getTrosakInoBanke())) {
                creditTransferTransactionInf.setChrgBr(ChargeBearerType1Code.CRED);
            }
//            if (StringUtils.equals(Bassx2Constants.PlatniPromet.DEVIZNI_NALOG_TROSAK_INO_BANKE_SLEV, sepaDto.getTrosakInoBanke())) {
//                creditTransferTransactionInf.setChrgBr(ChargeBearerType1Code.SLEV);
//            }
            if (StringUtils.isBlank(tppNalogPlatniDto.getTrosakInoBanke())) {
                creditTransferTransactionInf.setChrgBr(ChargeBearerType1Code.SLEV);
                if (StringUtils.equals(Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_SWIFT_NALOG, tppNalogPlatniDto.getNacinIzvrsenja())) {
                    creditTransferTransactionInf.setChrgBr(ChargeBearerType1Code.SHAR);
                } else if (StringUtils.isNumeric(tppNalogPlatniDto.getSifraValute()) && !StringUtils.equals(Bassx2Constants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY, tppNalogPlatniDto.getSifraValute()) && !StringUtils.equals(Bassx2Constants.Core.VALUTA_SIFRAVAL_EUR, tppNalogPlatniDto.getSifraValute())) {
                    creditTransferTransactionInf.setChrgBr(ChargeBearerType1Code.SHAR);
                } else if (!StringUtils.isNumeric(tppNalogPlatniDto.getSifraValute()) && !StringUtils.equals(Bassx2Constants.Core.VALUTA_OZNKAVAL_HOME_CURRENCY, tppNalogPlatniDto.getSifraValute()) && !StringUtils.equals(Bassx2Constants.Core.VALUTA_OZNKAVAL_EUR, tppNalogPlatniDto.getSifraValute())) {
                    creditTransferTransactionInf.setChrgBr(ChargeBearerType1Code.SHAR);
                }
                //if (PlatniUtil.isRacunMojeBanke(tppNalogPlatniDto.getIbanOdobrenja())){
                if (true){ // TODO Ivana - dodati pravi uvjet
                    creditTransferTransactionInf.setChrgBr(ChargeBearerType1Code.SHAR);
                }
            }

            creditTransferTransactionInf.setAmt(new AmountType3Choice());
            creditTransferTransactionInf.getAmt().setInstdAmt(new ActiveOrHistoricCurrencyAndAmount());
            creditTransferTransactionInf.getAmt().getInstdAmt().setValue(tppNalogPlatniDto.getIznos());
            creditTransferTransactionInf.getAmt().getInstdAmt().setCcy(tppNalogPlatniDto.getSifraValute());
            creditTransferTransactionInf.setCdtr(creditor(tppNalogPlatniDto));

            creditTransferTransactionInf.setCdtrAcct(new CashAccount162());
            creditTransferTransactionInf.getCdtrAcct().setId(new AccountIdentification4Choice2());
            creditTransferTransactionInf.getCdtrAcct().getId().setIBAN(tppNalogPlatniDto.getIbanOdobrenja());

            if (StringUtils.isNotBlank(tppNalogPlatniDto.getKategorijaNamjene())) {
                creditTransferTransactionInf.setPmtTpInf(new PaymentTypeInformation192());
                creditTransferTransactionInf.getPmtTpInf().setCtgyPurp(new CategoryPurpose1Choice());
                creditTransferTransactionInf.getPmtTpInf().getCtgyPurp().setCd(tppNalogPlatniDto.getKategorijaNamjene());
            }
            if (StringUtils.isNotBlank(tppNalogPlatniDto.getSwiftNalogodavateljaKorisnika())) {
                creditTransferTransactionInf.setCdtrAgt(new BranchAndFinancialInstitutionIdentification42());
                creditTransferTransactionInf.getCdtrAgt().setFinInstnId(new FinancialInstitutionIdentification72());
                creditTransferTransactionInf.getCdtrAgt().getFinInstnId().setBIC(tppNalogPlatniDto.getSwiftNalogodavateljaKorisnika());
            }

            creditTransferTransactionInf.setUltmtCdtr(ulitmateCreditor(tppNalogPlatniDto));
            if (StringUtils.isNotBlank(tppNalogPlatniDto.getSifraNamjene())) {
                creditTransferTransactionInf.setPurp(new Purpose2Choice());
                creditTransferTransactionInf.getPurp().setCd(tppNalogPlatniDto.getSifraNamjene());
            }
            creditTransferTransactionInf.setRmtInf(remittance(tppNalogPlatniDto));
        }
        return creditTransferTransactionInf;
    }

    /**
     * Kome
     */
    private PartyIdentification322 creditor(TppNalogPlatniDto sepaDto) {
        PartyIdentification322 partyIdentification = new PartyIdentification322();
        partyIdentification.setNm(sepaDto.getNazivCreditor());
        partyIdentification.setPstlAdr(new PostalAddress6());
        if (StringUtils.isNotBlank(sepaDto.getAdresaCreditor())) {
            partyIdentification.getPstlAdr().getAdrLine().add(sepaDto.getAdresaCreditor());
        }
        if (StringUtils.isNotBlank(sepaDto.getGradCreditor())) {
            partyIdentification.getPstlAdr().getAdrLine().add(sepaDto.getGradCreditor());
        }
        partyIdentification.getPstlAdr().setCtry(sepaDto.getDrzavaCreditor());
        return partyIdentification;
    }

    /**
     * Ultimate Debtor
     */
    private PartyIdentification321 ulitmateCreditor(TppNalogPlatniDto sepaDto) {
        PartyIdentification321 ultmtCre = null;

        if (StringUtils.isNotBlank(sepaDto.getUltimateCreditorId())) {
            ultmtCre = new PartyIdentification321();
            ultmtCre.setNm(sepaDto.getUltimateCreditorNaziv());
            ultmtCre.setId(new Party6Choice());
//            if (StringUtils.equals(sepaDto.getUltimateCreditorIdType(), SepaConstants.SEPA_IDENTIFICATION_TYPE_ORGANISATION)) {
//                // +++ Organisation Identification
//                ultmtCre.getId().setOrgId(new OrganisationIdentification4());
//                ultmtCre.getId().getOrgId().setOthr(new GenericOrganisationIdentification1());
//                ultmtCre.getId().getOrgId().getOthr().setId(sepaDto.getUltimateCreditorId());
//            } else {
//                // +++ Private Identification
//                ultmtCre.getId().setPrvtId(new PersonIdentification5());
//                ultmtCre.getId().getPrvtId().setOthr(new GenericPersonIdentification1());
//                ultmtCre.getId().getPrvtId().getOthr().setId(sepaDto.getUltimateCreditorId());
//
//            }
        }
        return ultmtCre;
    }

    /**
     * Opis plaćanja - detalji plaćanja
     */
    private RemittanceInformation5 remittance(TppNalogPlatniDto sepaDto) {
        RemittanceInformation5 rmtInf = new RemittanceInformation5();
        rmtInf.setStrd(new StructuredRemittanceInformation7());
        //rmtInf.setUstrd(StringUtils.stripToEmpty(sepaDto.getKnjizniNalog().getSvrha()));
        rmtInf.getStrd().setAddtlRmtInf(sepaDto.getSvrha());
//        if (StringUtils.equals(Bassx2Constants.PlatniPromet.MODELPNB_MODEL_99, sepaDto.getModelOdobrenja()) || StringUtils.isNotBlank(sepaDto.getPozivNaBrojOdobrenja())){
//            rmtInf.getStrd().setCdtrRefInf(new CreditorReferenceInformation2());
//            rmtInf.getStrd().getCdtrRefInf().setTp(new CreditorReferenceType2());
//            rmtInf.getStrd().getCdtrRefInf().getTp().setCdOrPrtry(new CreditorReferenceType1Choice());
//            rmtInf.getStrd().getCdtrRefInf().getTp().getCdOrPrtry().setCd(DocumentType3Code.SCOR);
//            if (StringUtils.isBlank(sepaDto.getModelOdobrenja())) {
//                sepaDto.setModelOdobrenja("HR00");
//            }
//            rmtInf.getStrd().getCdtrRefInf().setRef(StringUtils.strip(StringUtils.stripToEmpty(sepaDto.getModelOdobrenja()) + StringUtils.stripToEmpty(sepaDto.getPozivNaBrojOdobrenja())));
//        }
        if (StringUtils.isNotBlank(sepaDto.getModelOdobrenja())) {
            if (StringUtils.isBlank(sepaDto.getPozivNaBrojOdobrenja())) {
                sepaDto.setPozivNaBrojOdobrenja("");
            }
            rmtInf.getStrd().setCdtrRefInf(new CreditorReferenceInformation2());
            rmtInf.getStrd().getCdtrRefInf().setTp(new CreditorReferenceType2());
            rmtInf.getStrd().getCdtrRefInf().getTp().setCdOrPrtry(new CreditorReferenceType1Choice());
            rmtInf.getStrd().getCdtrRefInf().getTp().getCdOrPrtry().setCd(DocumentType3Code.SCOR);
            rmtInf.getStrd().getCdtrRefInf().setRef(StringUtils.strip(StringUtils.stripToEmpty(sepaDto.getModelOdobrenja()) + StringUtils.stripToEmpty(sepaDto.getPozivNaBrojOdobrenja())));
        }
        return rmtInf;
    }


    /**
     * Debtor
     */
    private PaymentInstructionInformation3 formDebtor(NalogSepaDto sepaDto) throws AbitRuleException {
        PaymentInstructionInformation3 pmtInf = new PaymentInstructionInformation3();

        pmtInf.setPmtInfId(sepaDto.getKnjizniNalog().getSifra().toString());
        pmtInf.setPmtMtd(PaymentMethod3Code.TRF);
        pmtInf.setNbOfTxs("1");
        pmtInf.setCtrlSum(sepaDto.getIznos());
        //pmtInf.isBtchBookg()
        pmtInf.setPmtTpInf(new PaymentTypeInformation191());
        pmtInf.getPmtTpInf().setInstrPrty(Priority2Code.NORM);
        if (sepaDto.getHitnost() != null && sepaDto.getHitnost().equals(Bassx2Constants.PlatniPromet.PLATNI_NALOG_HITNOST_HITNO)) {
            pmtInf.getPmtTpInf().setInstrPrty(Priority2Code.HIGH);
        }
        //pmtInf.getPmtTpInf().setSvcLvl(new ServiceLevel8Choice());
        SimpleDateFormat df = new SimpleDateFormat(GenericDateUtil.DATE_FORMAT_YEAR_MONTH_DAY);
        pmtInf.setReqdExctnDt(DateUtil.resolveXMLGregorianCalendarWithFormat(sepaDto.getKnjizniNalog().getDatumValute(), df));
        pmtInf.setDbtr(debtor(sepaDto));
        pmtInf.setDbtrAcct(new CashAccount16HR());
        pmtInf.getDbtrAcct().setId(new AccountIdentification4ChoiceHR());
        pmtInf.getDbtrAcct().getId().setIBAN(sepaDto.getIbanZaduzenja());
        pmtInf.getDbtrAcct().setCcy(sepaDto.getSifraValutePokrica());
        pmtInf.setDbtrAgt(new BranchAndFinancialInstitutionIdentification41());
        pmtInf.getDbtrAgt().setFinInstnId(new FinancialInstitutionIdentification71());
      //  pmtInf.getDbtrAgt().getFinInstnId().setBIC(Bassx2Constants.CoreHub.ERSTE_BANK_SWIFT_BIC8);
        pmtInf.setUltmtDbtr(ulitmateDebtor(sepaDto));
        return pmtInf;
    }


//    /**
//     * Debtor
//     */
//    private PaymentInstructionInformation3 formDebtorPsd2(TppNalogPlatniDto sepaDto) throws AbitRuleException {
//        PaymentInstructionInformation3 pmtInf = new PaymentInstructionInformation3();
//
//        pmtInf.setPmtInfId(sepaDto.getKnjizniNalog().getSifra().toString());
//        pmtInf.setPmtMtd(PaymentMethod3Code.TRF);
//        pmtInf.setNbOfTxs("1");
//        pmtInf.setCtrlSum(sepaDto.getIznos());
//        //pmtInf.isBtchBookg()
//        pmtInf.setPmtTpInf(new PaymentTypeInformation191());
//        pmtInf.getPmtTpInf().setInstrPrty(Priority2Code.NORM);
//        if (sepaDto.getHitnost() != null && sepaDto.getHitnost().equals(Bassx2Constants.PlatniPromet.PLATNI_NALOG_HITNOST_HITNO)) {
//            pmtInf.getPmtTpInf().setInstrPrty(Priority2Code.HIGH);
//        }
//        //pmtInf.getPmtTpInf().setSvcLvl(new ServiceLevel8Choice());
//        SimpleDateFormat df = new SimpleDateFormat(GenericDateUtil.DATE_FORMAT_YEAR_MONTH_DAY);
//        pmtInf.setReqdExctnDt(DateUtil.resolveXMLGregorianCalendarWithFormat(sepaDto.getKnjizniNalog().getDatumValute(), df));
//        pmtInf.setDbtr(debtor(sepaDto));
//        pmtInf.setDbtrAcct(new CashAccount16HR());
//        pmtInf.getDbtrAcct().setId(new AccountIdentification4ChoiceHR());
//        pmtInf.getDbtrAcct().getId().setIBAN(sepaDto.getIbanZaduzenja());
//        pmtInf.getDbtrAcct().setCcy(sepaDto.getSifraValutePokrica());
//        pmtInf.setDbtrAgt(new BranchAndFinancialInstitutionIdentification41());
//        pmtInf.getDbtrAgt().setFinInstnId(new FinancialInstitutionIdentification71());
//        //  pmtInf.getDbtrAgt().getFinInstnId().setBIC(Bassx2Constants.CoreHub.ERSTE_BANK_SWIFT_BIC8);
//        pmtInf.setUltmtDbtr(ulitmateDebtor(sepaDto));
//        return pmtInf;
//    }


    /**
     * Tere
     */
    private PartyIdentification322 debtor(NalogSepaDto sepaDto) {
        PartyIdentification322 partyIdentification = new PartyIdentification322();
        partyIdentification.setNm(sepaDto.getNazivDebtor());
        partyIdentification.setPstlAdr(new PostalAddress6());
        if (StringUtils.isNotBlank(sepaDto.getAdresaDebtor())) {
            partyIdentification.getPstlAdr().getAdrLine().add(sepaDto.getAdresaDebtor());
        }
        if (StringUtils.isNotBlank(sepaDto.getGradDebtor())) {
            partyIdentification.getPstlAdr().getAdrLine().add(sepaDto.getGradDebtor());
        }
        partyIdentification.getPstlAdr().setCtry(sepaDto.getDrzavaDebtor());
        return partyIdentification;
    }


    /**
     * Ultimate Debtor
     */
    private PartyIdentification321 ulitmateDebtor(NalogSepaDto sepaDto) {
        PartyIdentification321 ultmtDeb = null;
        if (StringUtils.isNotBlank(sepaDto.getUltimateDebtorId())) {
            ultmtDeb = new PartyIdentification321();
            ultmtDeb.setNm(sepaDto.getUltimateDebtorNaziv());
            ultmtDeb.setId(new Party6Choice());
            if (StringUtils.equals(sepaDto.getUltimateDebtorIdType(), SepaConstants.SEPA_IDENTIFICATION_TYPE_ORGANISATION)) {
                // +++ Organisation Identification
                ultmtDeb.getId().setOrgId(new OrganisationIdentification4());
                ultmtDeb.getId().getOrgId().setOthr(new GenericOrganisationIdentification1());
                ultmtDeb.getId().getOrgId().getOthr().setId(sepaDto.getUltimateDebtorId());
            } else {
                // +++ Private Identification
                ultmtDeb.getId().setPrvtId(new PersonIdentification5());
                ultmtDeb.getId().getPrvtId().setOthr(new GenericPersonIdentification1());
                ultmtDeb.getId().getPrvtId().getOthr().setId(sepaDto.getUltimateDebtorId());
            }
        }
        return ultmtDeb;
    }

    private List<NalogSepaDto> setValidPattern(List<NalogSepaDto> nalogSepaDtos) {
        for (NalogSepaDto dto :nalogSepaDtos) {
//            dto.getKnjizniNalog().setSvrha(DeviznoUtil.checkIrregularCharacters(dto.getKnjizniNalog().getSvrha(),Bassx2Constants.PlatniPromet.PAIN001_ALLOWED_PATTERN));
//            dto.setNazivCreditor(DeviznoUtil.checkIrregularCharacters(dto.getNazivCreditor(),Bassx2Constants.PlatniPromet.PAIN001_ALLOWED_PATTERN));
//            dto.setNazivDebtor(DeviznoUtil.checkIrregularCharacters(dto.getNazivDebtor(),Bassx2Constants.PlatniPromet.PAIN001_ALLOWED_PATTERN));
            //vodeći/završni space znak
            dto.getKnjizniNalog().setSvrha(StringUtils.strip(dto.getKnjizniNalog().getSvrha()));
            dto.setNazivCreditor(StringUtils.strip(dto.getNazivCreditor()));
            dto.setNazivDebtor(StringUtils.strip(dto.getNazivDebtor()));
        }
        return nalogSepaDtos;
    }



//    public String getRbrPoruke(Integer tipNaloga,EntityManager em) {
//        String rez;
//        Double obj = (double) em.createNativeQuery("select count(*) + 1 from nalog where date(nalog.vrije_nal) =:dat and tipna_nal = :tip and statu_nal !='" + Bassx2Constants.Core.NALOG_STATUNAL_POVUCEN + "'")
//                .setParameter("dat", Calendar.getInstance().getTime(), TemporalType.DATE)
//                .setParameter("tip", tipNaloga)
//                .getSingleResult();
//        Integer rbr =  obj.intValue();
//        rez = StringUtils.repeat("0", 4 - rbr.toString().length()).concat(rbr.toString());
//        return rez;
//    }

}
