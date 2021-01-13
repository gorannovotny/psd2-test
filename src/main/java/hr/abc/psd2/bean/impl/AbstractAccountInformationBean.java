package hr.abc.psd2.bean.impl;

import hr.abc.psd2.bean.IAccountInformationBean;
import hr.abc.psd2.config.AppConfig;
import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.model.ConsentTypeAccess;
import hr.abc.psd2.model.dto.ais.*;
import hr.abc.psd2.model.dto.core.Psd2WebPregledRacunaDto;
import hr.abc.psd2.model.dto.core.WebKorisnikRacunDto;
import hr.abc.psd2.model.dto.error.ErrorCode;
import hr.abc.psd2.model.sepa.camt.Document;
import hr.abc.psd2.util.*;
import hr.sberbank.model.account.Transaction;
import hr.sberbank.model.account.TransactionDetails;
import hr.sberbank.model.account.TransactionList;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.keyvalue.MultiKey;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import static hr.abc.psd2.model.ConsentTypeAccess.BALANCE;
import static hr.abc.psd2.model.ConsentTypeAccess.TRANSACTION;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;

public abstract class AbstractAccountInformationBean implements IAccountInformationBean , Serializable {

    private static final long serialVersionUID = 1L;

    protected abstract List<Psd2WebPregledRacunaDto> getPopisRacunaFromIb (List<Integer> listSifraParOvlastenja);

    protected abstract List<Psd2WebPregledRacunaDto> getAccounts (String iban, String currency);

    protected abstract Document getIzvodiCAMT(String sifraPartije, String patriPar, Date datumOd, Date datumDo, String sifraValute, List<String> listaValuta, Boolean isPravna);

    protected abstract List<String> getSifraValuteListBySifraPar(String iban);

    protected abstract List<PrometStanjePartijeDto> getPrometiKontraStrana(PrometStanjePartijeDto filterDto, String reportType, boolean isPravna);

    protected abstract String getPartiParBySifraPar(String sifraPar);

    protected abstract List<TransactionDetails> getTransactionsByAccount(String account);

    protected abstract AccountBalanceResponse loadAccountBalances(List<ConsentAccountAisDto> accountAisDtoList, String resourceID) throws AbitRuleException ;
    
    @Inject
    AppConfig appConfig;

    @Override
    public AccountResponseDto formAccountListResponse(ConsentAisDto consent, Boolean withBalance) {
        AccountResponseDto accountResponse = new AccountResponseDto();
        List<ConsentAccountAisDto> acessList = consent.getConsentAccountAisDtoList();
        try {
            List<AccountDto> psdAccountList = getPsd2AccountList(consent);
            List<AccountDto> resultList = new ArrayList<>();
            HashMap<String, AccountDto> accountMap = new HashMap<>();
            HashMap<String, List<AccountDto>> mappedSubacount = new HashMap<>();
            List<AccountDto> accountList = new ArrayList<>();
            List<AccountDto> subAcountList = new ArrayList<>();

            //da izbjegnem duplikate --> po jednoj partiji može bite više access consenta!!!
            Set<AccountDto> filterAccountSet = new HashSet<>();

            for (AccountDto acountDto : psdAccountList) {
                for (ConsentAccountAisDto access : acessList) {
                    /*//if has consent
                    if (acountDto.getResourceId().equals(String.valueOf(access.getSifraPar()))) {
                        //ako je MVR --> prikazujem kao agregacijski tip
                        if (access.getCurrencyAcc().equals("MVR")) {
                            if (filterAccountSet.add(acountDto)) {
                                subAcountList.add(acountDto);
                            }
                            mappedSubacount.put(acountDto.getResourceId(), subAcountList);
                        } else {
                            if (filterAccountSet.add(acountDto)) {
                                accountList.add(acountDto);
                            }
                        }
                    }*/
                    if(access.getCurrencyAcc().equals("MVR")) {
                        //if has consent
                        if(acountDto.getIban().equals(access.getIbanAcc())) {
                            if (filterAccountSet.add(acountDto)) {
                                subAcountList.add(acountDto);
                            }
                            mappedSubacount.put(acountDto.getIban(), subAcountList);
                        }

                    }
                    else {
                        if(acountDto.getIban().equals(access.getIbanAcc()) && acountDto.getCurrency().equals(access.getCurrencyAcc())) {
                            if (filterAccountSet.add(acountDto)) {
                                accountList.add(acountDto);
                            }
                        }
                    }
                }
            }

            if (mappedSubacount != null && !mappedSubacount.isEmpty()) {

                //filtritaj mapu...
               /* for (String accountId : mappedSubacount.keySet()) {
                    List<AccountDto> updetedSubAccounts = new ArrayList<>();
                    for (AccountDto acountDto : subAcountList) {
                        if (accountId.equals(acountDto.getResourceId())) {
                            updetedSubAccounts.add(acountDto);
                        }
                    }
                    mappedSubacount.replace(accountId, updetedSubAccounts);
                }*/

                for (String accountId : mappedSubacount.keySet()) {
                    if (mappedSubacount.get(accountId) != null && !(mappedSubacount.get(accountId).isEmpty())) {


                        /*for (AccountDto acountDto : mappedSubacount.get(accountId)) {
                            if (!accountId.equals(acountDto.getResourceId())) {
                                mappedSubacount.remove(accountId, acountDto);
                            }
                        }*/
                        AccountDto agregationAccount = new AccountDto();
                        Set<Links> aggregationAccountLinkSet = new HashSet<>();
                        List<Links> aggregatioAccountResultLinkList = new ArrayList<>();
                        agregationAccount.setResourceId(mappedSubacount.get(accountId).get(0).getResourceId());
                        agregationAccount.setIban(mappedSubacount.get(accountId).get(0).getIban());
                        agregationAccount.setCurrency("XXX");
                        agregationAccount.setProduct(mappedSubacount.get(accountId).get(0).getProduct());
                        agregationAccount.setCashAccountType(mappedSubacount.get(accountId).get(0).getCashAccountType());
                        agregationAccount.setName("Aggregation Account");

                        //postavljam link
                        for (ConsentAccountAisDto access : acessList) {
                            if (access.getTypeAcc().equals(BALANCE.getValue()) && withBalance) {
                                Links balanceLink = Psd2Util.formAccountInfServiceLinks(agregationAccount.getResourceId(), BALANCE.getValue());
                                aggregationAccountLinkSet.add(balanceLink);
                            }
                            if (access.getTypeAcc().equals(TRANSACTION.getValue())) {
                                Links transactionLink = Psd2Util.formAccountInfServiceLinks(agregationAccount.getResourceId(), TRANSACTION.getValue());
                                aggregationAccountLinkSet.add(transactionLink);
                            }
                        }
                        aggregatioAccountResultLinkList.addAll(aggregationAccountLinkSet);
                        agregationAccount.set_links(aggregatioAccountResultLinkList);
                        //dodajem ga na resoult listu za prikaz
                        resultList.add(agregationAccount);
                        List<AccountDto> updetedSubAccounts = new ArrayList<>();
                        for(AccountDto accountDto:mappedSubacount.get(accountId)) {
                            if(! accountDto.getResourceId().equals(mappedSubacount.get(accountId).get(0).getResourceId())) {
                                updetedSubAccounts.add(accountDto);
                            }
                        }
                        mappedSubacount.replace(accountId, updetedSubAccounts);
                        //postavljam sub-account
                        for (AccountDto accountDto : mappedSubacount.get(accountId)) {
                            AccountDto subAccount = new AccountDto();
                            Set<Links> linkSubAccSet = new HashSet<>();
                            List<Links> subAccountlinkResoultList = new ArrayList<>();

                            subAccount.setResourceId(accountDto.getResourceId() + accountDto.getSifraVal());
                            subAccount.setIban(accountDto.getIban());
                            subAccount.setCurrency(accountDto.getCurrency());
                            subAccount.setCashAccountType(accountDto.getCashAccountType());
                            subAccount.setName(accountDto.getName());
                            //linkovi...
                            for (ConsentAccountAisDto access : acessList) {
                                if (access.getTypeAcc().equals(BALANCE.getValue()) && withBalance) {
                                    Links balanceLink = Psd2Util.formAccountInfServiceLinks(subAccount.getResourceId(), BALANCE.getValue());
                                    linkSubAccSet.add(balanceLink);
                                }
                                if (access.getTypeAcc().equals(TRANSACTION.getValue())) {
                                    Links transactionLink = Psd2Util.formAccountInfServiceLinks(subAccount.getResourceId(), TRANSACTION.getValue());
                                    linkSubAccSet.add(transactionLink);
                                }
                            }
                            subAccountlinkResoultList.addAll(linkSubAccSet);
                            subAccount.set_links(subAccountlinkResoultList);
                            resultList.add(subAccount);
                        }
                    }
                }
            }
            //ako se radi o običnom računu!!
            if (accountList != null && !accountList.isEmpty()) {
                for (AccountDto acount : accountList) {
                    AccountDto accountDto = new AccountDto();
                    Set<Links> regularAccountLinkSet = new HashSet<>();
                    List<Links> regularAccountResultLinkList = new ArrayList<>();
                    accountDto.setResourceId(acount.getResourceId());
                    accountDto.setIban(acount.getIban());
                    accountDto.setCurrency(acount.getCurrency());
                    accountDto.setCashAccountType(acount.getCashAccountType());
                    accountDto.setName(acount.getName());
                    //linkovi..
                    for (ConsentAccountAisDto access : acessList) {
                        if (access.getTypeAcc().equals(BALANCE.getValue()) && withBalance && access.getCurrencyAcc().equals(accountDto.getCurrency())) {
                            Links regularAccBalanceLink = Psd2Util.formAccountInfServiceLinks(accountDto.getResourceId(), BALANCE.getValue());
                            regularAccountLinkSet.add(regularAccBalanceLink);
                        }
                        if (access.getTypeAcc().equals(TRANSACTION.getValue()) && access.getCurrencyAcc().equals(accountDto.getCurrency())) {
                            Links regularAccTransactionLink = Psd2Util.formAccountInfServiceLinks(accountDto.getResourceId(), TRANSACTION.getValue());
                            regularAccountLinkSet.add(regularAccTransactionLink);
                        }
                    }
                    regularAccountResultLinkList.addAll(regularAccountLinkSet);
                    accountDto.set_links(regularAccountResultLinkList);
                    resultList.add(accountDto);
                }
            }

            if (resultList != null & !resultList.isEmpty()) {
                accountResponse.setAccounts(resultList);
                accountResponse.setIsValid(true);
            }

        } catch (Exception e) {
            //za sada
            e.printStackTrace();
        }
        return accountResponse;
    }

    public List<AccountDto> getPsd2AccountList(ConsentAisDto consentAisDto) throws AbitRuleException {
        List<AccountDto> listPsd2Accounts = null;
        if (consentAisDto != null && consentAisDto.getConsentAccountAisDtoList() != null && !consentAisDto.getConsentAccountAisDtoList().isEmpty()) {
            //form list sifra partija & map sifra_partije - valuta consenta
            //List<Integer> sifrePartijaList = new ArrayList<>();
            List<String> listOfIban = new ArrayList<>();
            Map<MultiKey, String> partijaValutaMap = new HashMap<>();
            List<Psd2WebPregledRacunaDto> listaWebRacuna=new ArrayList<>();
            for (ConsentAccountAisDto consentAccountAisDto : consentAisDto.getConsentAccountAisDtoList()) {
                //sifrePartijaList.add(consentAccountAisDto.getSifraPar());
                if(consentAccountAisDto.getCurrencyAcc().equals("MVR")) {
                    listaWebRacuna.addAll(getAccounts(consentAccountAisDto.getIbanAcc(),null));
                }
                else listaWebRacuna.addAll(getAccounts(consentAccountAisDto.getIbanAcc(), consentAccountAisDto.getCurrencyAcc()));
                //listOfIban.add(consentAccountAisDto.getIbanAcc());
                //MultiKey multikey = new MultiKey(consentAccountAisDto.getSifraPar(), consentAccountAisDto.getCurrencyAcc());
                //partijaValutaMap.put(multikey, consentAccountAisDto.getCurrencyAcc());
            }
            //get lista racuna with details
            // List<Psd2WebPregledRacunaDto> listaWebRacuna = LookupHelper.getIInternetProxyRemote().getPopisRacunaFromIb(sifrePartijaList); // stari
            //List<Psd2WebPregledRacunaDto> listaWebRacuna = getPopisRacunaFromIb(sifrePartijaList); // prazni stari
            //List<Psd2WebPregledRacunaDto> listaWebRacuna = getAccounts(listOfIban); // prazni
            if (listaWebRacuna != null && !listaWebRacuna.isEmpty()) {
                listPsd2Accounts = new ArrayList<>();
                for (Psd2WebPregledRacunaDto webPregledRacunaDto : listaWebRacuna) {
                    //currency
                    //String oznakaVal = ((String) PlatniUtil.getFieldValueFromTableRecord("valuta" , "oznka_val", "sifra_val", webPregledRacunaDto.getValuta(), entityManager));
                    //String oznakaVal = appConfig.currencyMap().get(webPregledRacunaDto.getValuta()); //TODO Ivana
                    //MultiKey key = new MultiKey(webPregledRacunaDto.getSifraPartije(), oznakaVal);
                    //MultiKey keyMVR = new MultiKey(webPregledRacunaDto.getSifraPartije(), "MVR");
                    //if (partijaValutaMap.containsKey(key) || partijaValutaMap.containsKey(keyMVR)) {
                        AccountDto accountDto = new AccountDto();
                        //resourceID
                        accountDto.setResourceId(webPregledRacunaDto.getSifraPartije().toString());
                        //iban
                        /*if (webPregledRacunaDto.getBrojPartije() != null && webPregledRacunaDto.getBrojPartije().trim().length() == 10) {
                            accountDto.setIban(IbanFunctions.getIbanHr(GenericBassxConstants.CoreHub.VBDI_BANKE, webPregledRacunaDto.getBrojPartije()));
                        }*/
                        accountDto.setIban(webPregledRacunaDto.getIban());
                        //currency
                        //accountDto.setCurrency((String) PlatniUtil.getFieldValueFromTableRecord("valuta", "oznka_val", "sifra_val", webPregledRacunaDto.getValuta(), entityManager));
                        accountDto.setCurrency(webPregledRacunaDto.getValuta()); //TODO Ivana
                        accountDto.setSifraVal(webPregledRacunaDto.getValuta());
                        //product
                        accountDto.setProduct("multi currency account");
                        //cashAccountType
                        accountDto.setCashAccountType("current account");
                        //name
                        //accountDto.setName(webPregledRacunaDto.getNazivTipaPartije());
                        //add to list
                        listPsd2Accounts.add(accountDto);
                    //}
                }
            }
        }
        return listPsd2Accounts;
    }

    @Override
    public AccountResponseDto formAccountDetailsResponse(String accountResourceId, ConsentAisDto consent, Boolean withBalance) {
        AccountResponseDto accountDetailsResponseDto = new AccountResponseDto();

        AccountResponseDto accountListResponseDto = formAccountListResponse(consent, withBalance);
        List<AccountDto> accountList = accountListResponseDto.getAccounts();
        List<AccountDto> resultAccountList = new ArrayList<>();
        Boolean hasMvr = false;
        for (AccountDto account : accountList) {
            if (StringUtils.equals(account.getResourceId(), accountResourceId)) {
                if (StringUtils.equals(account.getName(), "Aggregation Account")) {
                    hasMvr = true;
                }
            }
        }
        if (hasMvr) {
            for (AccountDto account : accountList) {
                if (StringUtils.equals(account.getResourceId(), accountResourceId) && StringUtils.equals(account.getName(), "Aggregation Account")) {
                    resultAccountList.add(account);
                }
            }
        } else {
            for (AccountDto account : accountList) {
                if (StringUtils.equals(account.getResourceId(), accountResourceId)) {
                    resultAccountList.add(account);
                }
            }
        }

        if (resultAccountList != null && !resultAccountList.isEmpty()) {
            accountDetailsResponseDto.setIsValid(true);
            accountDetailsResponseDto.setAccounts(resultAccountList);
        } else {
            accountDetailsResponseDto.setIsValid(false);
            accountDetailsResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unknown_account", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
        }
        return accountDetailsResponseDto;
    }

    @Override
    public AccountBalanceResponse getAccountBalances(List<ConsentAccountAisDto> accountAisDtoList, String resourceId) {
        AccountBalanceResponse accountBalanceResponse = new AccountBalanceResponse();
        try {
            accountBalanceResponse = loadAccountBalances(accountAisDtoList, resourceId);
            if (accountBalanceResponse != null) {
                accountBalanceResponse.setIsValid(true);
            }
        } catch (Exception e) {
            accountBalanceResponse.setIsValid(false);
            accountBalanceResponse.setErrorInformationDto(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<String>(Arrays.asList("Internal server error"))));
        }
        return accountBalanceResponse;
    }

   

    

    @Override
    public AccountTransactionReq validateAisTransactionRequest(UriInfo queryParam, String resourceId) {
        AccountTransactionReq accountTransactionsReq = new AccountTransactionReq();
        accountTransactionsReq.setValidReq(false);
        Boolean xmlFormat;

        try {
            //validate accountID
            if (StringUtils.isNotBlank(resourceId) && resourceId.length() > 3) {
                String accountId = resourceId;
                accountTransactionsReq.setAccountId(accountId);
                //String sifraValute = resourceId.substring(resourceId.length() - 3);
                //accountTransactionsReq.setSifraValute(sifraValute);
                //validateMandatory query
                if (StringUtils.isNotBlank(queryParam.getQueryParameters().getFirst("dateFrom"))) {
                    LocalDate dateFrom = Psd2Util.getPsd2DateFromString(queryParam.getQueryParameters().getFirst("dateFrom"));
                    //date from mora biti max 2 godine unazad
                    if (LocalDate.now().minusYears(2).isEqual(dateFrom) || dateFrom.isAfter(LocalDate.now().minusYears(2))) {
                        accountTransactionsReq.setDateFrom(Psd2Util.getDateFromString(queryParam.getQueryParameters().getFirst("dateFrom")));
                        accountTransactionsReq.setValidReq(true);
                    } else {

                        accountTransactionsReq.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_ais_dateFrom_limit_error", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    }

                } else if (StringUtils.isNotBlank(queryParam.getQueryParameters().getFirst("dateTo"))) {

                    accountTransactionsReq.setDateTo(Psd2Util.getDateFromString(queryParam.getQueryParameters().getFirst("dateTo")));
                } else {

                    accountTransactionsReq.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_ais_mandatory_req_query_param", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
            } else {
                accountTransactionsReq.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_account_id_mandatory", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));

            }
            //validate withBalance query param
            if (StringUtils.isNotBlank(queryParam.getQueryParameters().getFirst("withBalance"))) {
                if (StringUtils.equals(queryParam.getQueryParameters().getFirst("withBalance"), "true")) {
                    accountTransactionsReq.setWithBalance(true);
                } else {
                    accountTransactionsReq.setWithBalance(false);
                }
            } else {
                accountTransactionsReq.setWithBalance(false);
            }
        } catch (DateTimeException dte) {
            accountTransactionsReq.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_ais_unknown_date_format", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
        } catch (NumberFormatException nfe) {
            accountTransactionsReq.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unknown_account", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
        } catch (ParseException pe) {
            accountTransactionsReq.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_ais_unknown_date_format", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
        }
        return accountTransactionsReq;
    }

    @Override
    public PrometStanjePartijeDto formFilterPartije(AccountTransactionReq accountTransactionReq) {
        PrometStanjePartijeDto filterPartije = new PrometStanjePartijeDto();
        if (accountTransactionReq != null) {
            filterPartije.setIbanPartije(accountTransactionReq.getAccountId());
            filterPartije.setDatumPocetka(accountTransactionReq.getDateFrom());
            filterPartije.setWithBalance(accountTransactionReq.getWithBalance());
            if (accountTransactionReq.getDateTo() != null) {
                filterPartije.setDatumKraja(accountTransactionReq.getDateTo());
            } else {
                //default dateTo == today date!!
                filterPartije.setDatumKraja(new Date());
            }
        }
        return filterPartije;
    }

    @Override
    public AccountTransactionsResponse getTransactionList(String acceptedFormat, ConsentAisDto consentAisDtoWs, PrometStanjePartijeDto filterPartijeDto, String resourceId) {
        AccountTransactionsResponse accountTransactionsResponse = new AccountTransactionsResponse();
        List<ConsentAccountAisDto> accessList = consentAisDtoWs.getConsentAccountAisDtoList();
        Boolean hasAcessConsent = false;
        try {
            if (StringUtils.equals(acceptedFormat, MediaType.APPLICATION_JSON) || StringUtils.isBlank(acceptedFormat)) {
                //Boolean isPravna = PartijaKomitentUtil.isPravnaOsobaByBrojPartije(accountInformationDao.getPartiParBySifraPar(String.valueOf(filterPartijeDto.getSifraPartije())), entityManager);
                Boolean isPravna = null; // TODO Ivana
                //provjera tipa privole
                AccountResponseDto accountResponseDto = formAccountDetailsResponse(resourceId, consentAisDtoWs, filterPartijeDto.isWithBalance());
                for (ConsentAccountAisDto access : accessList) {
                    for(AccountDto accountDto:accountResponseDto.getAccounts()) {
                        if (access.getTypeAcc().equals(TRANSACTION.getValue()) && access.getIbanAcc().equals(accountDto.getIban()) && accountDto.getResourceId().equals(resourceId)) {
                            hasAcessConsent = true;
                            String brojPartije = getBrojPartijeByIban(access.getIbanAcc());
                            filterPartijeDto.setBrojPartije(brojPartije);
                            filterPartijeDto.setOznakaNadDijela(GenericBassxConstants.Core.NADDIO_OZNKANAD_GLAVNICA);

                            List<TransactionDetails> listaTransakcija=getTransactionsByAccount(resourceId);

                            // napuni response
                            if(!CollectionUtils.isEmpty(listaTransakcija)) {
                                accountTransactionsResponse.setTransactions(formTransactionResoultList(listaTransakcija, filterPartijeDto));
                                if (filterPartijeDto.isWithBalance()) {

                                        if (access.getTypeAcc().equals(BALANCE.getValue()) && access.getIbanAcc().equals(accountDto.getIban()) && accountDto.getResourceId().equals(resourceId)) {
                                            accountTransactionsResponse.set_links(Psd2Util.formAccountInfServiceLinks(resourceId, BALANCE.getValue()));
                                            break;
                                        }

                                }
                                accountTransactionsResponse.setValid(true);
                            }

                            else {
                                accountTransactionsResponse.setValid(false);
                                accountTransactionsResponse.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_transaction_content", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                            }

                            //daj transakcije po svim valutama ako ima MVR acces i ako account id requesta 999 (zadnje 3 znamenke)
                            /*if (access.getCurrencyAcc().equals("MVR") && StringUtils.equals(filterPartijeDto.getSifraValute(), "999")) {
                                List<String> listaValuta = getSifraValuteListBySifraPar(access.getIbanAcc());
                                if (listaValuta != null && !listaValuta.isEmpty()) {
                                    List<PrometStanjePartijeDto> stanjePartijeDtoList = null;
                                    List<PrometStanjePartijeDto> resultList = new ArrayList<>();
                                    for (String sifraVal : listaValuta) {
                                        filterPartijeDto.setSifraValute(sifraVal);
                                        //get transaction list per valute fom db!!
                                        //stanjePartijeDtoList = LookupMinHelper.getICoreDaoMinProxyRemote().getPrometiKontraStrana(filterPartijeDto, GenericBassxConstants.Core.REPORT_TYPE_PARTIJA, isPravna); // stari
                                        stanjePartijeDtoList = getPrometiKontraStrana(filterPartijeDto, GenericBassxConstants.Core.REPORT_TYPE_PARTIJA, isPravna); // prazni

                                        //add list od transactions to temp list
                                        if (stanjePartijeDtoList != null && !stanjePartijeDtoList.isEmpty()) {
                                            for (PrometStanjePartijeDto promet : stanjePartijeDtoList) {
                                                //add all transactions to one resuslt list
                                                resultList.add(promet);
                                            }
                                            //form response from transactions result
                                            accountTransactionsResponse.setTransactions(formTransactionResoultList(resultList, filterPartijeDto));
                                            if (filterPartijeDto.isWithBalance()) {
                                                for (ConsentAccountAisDto accessAis : accessList) {
                                                    if (accessAis.getTypeAcc().equals(Psd2Util.ConsentTypeAccess.BALANCE.getValue()) && accessAis.getSifraPar().equals(filterPartijeDto.getSifraPartije())) {
                                                        accountTransactionsResponse.set_links(Psd2Util.formAccountInfServiceLinks(resourceId, Psd2Util.ConsentTypeAccess.BALANCE.getValue()));
                                                        break;
                                                    }
                                                }
                                            }
                                            accountTransactionsResponse.setValid(true);
                                        }
                                    }
                                    if (resultList != null && resultList.isEmpty()) {
                                        accountTransactionsResponse.setValid(false);
                                        accountTransactionsResponse.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_transaction_content", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));

                                    }
                                }
                            } else {
                                //String oznakaValute = ((String) PlatniUtil.getFieldValueFromTableRecord("valuta", "oznka_val", "sifra_val", filterPartijeDto.getSifraValute(), entityManager));
                                String oznakaValute = null; // TODO Ivana
                                if ((oznakaValute != null && (((StringUtils.equals(access.getCurrencyAcc(), oznakaValute)) || StringUtils.equals(access.getCurrencyAcc(), "MVR"))))) {
                                    //List<PrometStanjePartijeDto> stanjePartijeDtoList = LookupMinHelper.getICoreDaoMinProxyRemote().getPrometiKontraStrana(filterPartijeDto, GenericBassxConstants.Core.REPORT_TYPE_PARTIJA, isPravna); // stari
                                    List<PrometStanjePartijeDto> stanjePartijeDtoList = getPrometiKontraStrana(filterPartijeDto, GenericBassxConstants.Core.REPORT_TYPE_PARTIJA, isPravna); // prazni
                                    if (stanjePartijeDtoList != null && !stanjePartijeDtoList.isEmpty()) {
                                        accountTransactionsResponse.setTransactions(formTransactionResoultList(stanjePartijeDtoList, filterPartijeDto));
                                        if (filterPartijeDto.isWithBalance()) {
                                            for (ConsentAccountAisDto accessAis : accessList) {
                                                if (accessAis.getTypeAcc().equals(Psd2Util.ConsentTypeAccess.BALANCE.getValue()) && accessAis.getSifraPar().equals(filterPartijeDto.getSifraPartije())) {
                                                    accountTransactionsResponse.set_links(Psd2Util.formAccountInfServiceLinks(resourceId, Psd2Util.ConsentTypeAccess.BALANCE.getValue()));
                                                    break;
                                                }
                                            }
                                        }
                                        accountTransactionsResponse.setValid(true);
                                    } else {
                                        accountTransactionsResponse.setValid(false);
                                        accountTransactionsResponse.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_transaction_content", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                                    }
                                } else {
                                    accountTransactionsResponse.setValid(false);
                                    accountTransactionsResponse.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_curency_access", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                                }
                            }*/
                            break;
                        }
                    }
                }
                if (!hasAcessConsent) {
                    accountTransactionsResponse.setValid(false);
                    accountTransactionsResponse.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.AIS_CONSENT_INVALID.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_ais_invalid_trans_consent", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
                //ako nije json
            } else if (StringUtils.equals(acceptedFormat, MediaType.APPLICATION_XML)) {
                boolean hasTransactionConsent = false;
                String partiPar = getPartiParBySifraPar(String.valueOf(filterPartijeDto.getSifraPartije()));
                //Boolean isPravna = PartijaKomitentUtil.isPravnaOsobaByBrojPartije(partiPar, entityManager);
                Boolean isPravna = null; // TODO Ivana
                String sifraValute = null;
                List<String> listaValuta = new ArrayList<>();
                for (ConsentAccountAisDto acess : accessList) {
                    if (acess.getTypeAcc().equals(TRANSACTION.getValue()) && acess.getSifraPar().equals(filterPartijeDto.getSifraPartije())) {
                        hasTransactionConsent = true;
                        if (StringUtils.equals(acess.getCurrencyAcc(), "MVR") && StringUtils.equals(filterPartijeDto.getSifraValute(), "999")) {
                            //ako je account consent za MvR Račun
                            listaValuta = getSifraValuteListBySifraPar(acess.getIbanAcc());
                        } else {
                            //String oznakaValute = ((String) PlatniUtil.getFieldValueFromTableRecord("valuta", "oznka_val", "sifra_val", filterPartijeDto.getSifraValute(), entityManager));
                            String oznakaValute = null; // TODO Ivana
                            if ((oznakaValute != null && (((StringUtils.equals(acess.getCurrencyAcc(), oznakaValute)) || StringUtils.equals(acess.getCurrencyAcc(), "MVR"))))) {
                                sifraValute = filterPartijeDto.getSifraValute();
                            } else {
                                accountTransactionsResponse.setValid(false);
                                accountTransactionsResponse.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_curency_access", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                            }
                        }
                    }
                }
                if (hasTransactionConsent) {
                    if (isPravna) {
                        Document document = null;
                        if (listaValuta != null && !listaValuta.isEmpty()) {
                            document = formCAMTTransations(String.valueOf(filterPartijeDto.getSifraPartije()), partiPar, null, listaValuta, filterPartijeDto, isPravna);
                        } else if (StringUtils.isNotBlank(sifraValute)) {
                            document = formCAMTTransations(String.valueOf(filterPartijeDto.getSifraPartije()), partiPar, sifraValute, null, filterPartijeDto, isPravna);
                        }
                        if (document != null) {
                            accountTransactionsResponse.setValid(true);
                            accountTransactionsResponse.setDocument(document);
                        }
                    } else {
                        accountTransactionsResponse.setValid(false);
                        accountTransactionsResponse.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.REQUESTED_FORMATS_INVALID.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_camt_for_fizicka", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    }
                } else {
                    accountTransactionsResponse.setValid(false);
                    accountTransactionsResponse.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.AIS_CONSENT_INVALID.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_ais_invalid_trans_consent", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
            }
        } catch (Exception e) {
            accountTransactionsResponse.setValid(false);
            accountTransactionsResponse.setErrorInformationDto(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<String>(Arrays.asList("Internal server error"))));
        }
        return accountTransactionsResponse;
    }

    private AccountTransactionListDto formTransactionResoultList(List<TransactionDetails> listOfTransactions, PrometStanjePartijeDto filterPartijeDto) {
        AccountTransactionListDto accountTransactionListDto = new AccountTransactionListDto();
        List<AccountTransactionDto> transsactionList = new ArrayList<>();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");

        for(TransactionDetails details : listOfTransactions) {
            AccountTransactionDto accountTransactionDto = new AccountTransactionDto();
            AccountDto accountDto = new AccountDto();
            Amount amount = new Amount();
            accountTransactionDto.setTransactionId(details.getId());
            if(details.getDebitOrCredit().equals(Psd2Constants.DEBIT)) {
                accountTransactionDto.setCreditorName(details.getCreditorName());
                amount.setAmount(StringFunctions.bigDecimalToString(details.getAmount()));
                accountDto.setIban(details.getCreditorAccount());
                accountTransactionDto.setCreditorAccount(accountDto);
            }
            else if (details.getDebitOrCredit().equals(Psd2Constants.CREDIT)) {
                accountTransactionDto.setDebtorName(details.getDebtorName());
                amount.setAmount(StringFunctions.bigDecimalToString(details.getAmount()));
                accountDto.setIban(details.getDebtorAccount());
                accountTransactionDto.setDebtorAccount(accountDto);
            }
            amount.setCurrency(details.getCurrency());
            accountTransactionDto.setTransactionAmount(amount);
            accountTransactionDto.setBookingDate(formatter.format(details.getExecutionDate()));
            accountTransactionDto.setValueDate(formatter.format(details.getValueDate()));
            accountTransactionDto.setRemittanceInformationUnstructured(details.getRemittanceInformationUnstructured());
            transsactionList.add(accountTransactionDto);

        }


        /*for (PrometStanjePartijeDto promet : stanjePartijeDtoList) {
            AccountTransactionDto accountTransactionDto = new AccountTransactionDto();
            AccountDto accountDto = new AccountDto();
            Amount amount = new Amount();
            accountTransactionDto.setTransactionId(String.valueOf(promet.getSifraNaloga()));
            if (promet.getValutniDugovniIznos().compareTo(BigDecimal.ZERO) != 0) {
                accountTransactionDto.setCreditorName(promet.getNazivKomitenta());
                amount.setAmount(StringFunctions.bigDecimalToString(promet.getValutniDugovniIznos(), StringFunctions.DEFAULT_NUMBER_PATTERN));
                accountDto.setIban(promet.getIbanPartije());
                accountTransactionDto.setCreditorAccount(accountDto);
            } else if (promet.getValutniPotrazniIznos().compareTo(BigDecimal.ZERO) != 0) {
                accountTransactionDto.setDebtorName(promet.getNazivKomitenta());
                amount.setAmount(StringFunctions.bigDecimalToString(promet.getValutniPotrazniIznos(), StringFunctions.DEFAULT_NUMBER_PATTERN));
                accountDto.setIban(promet.getIbanPartije());
                accountTransactionDto.setDebtorAccount(accountDto);
            }
            //amount.setCurrency((String) PlatniUtil.getFieldValueFromTableRecord("valuta", "oznka_val", "sifra_val", promet.getSifraValute(), entityManager));
            amount.setCurrency(null); // TODO Ivana
            accountTransactionDto.setTransactionAmount(amount);
            accountTransactionDto.setBookingDate(formatter.format(promet.getDatumKnjizenja()));
            accountTransactionDto.setValueDate(formatter.format(promet.getDatumValute()));
            accountTransactionDto.setRemittanceInformationUnstructured(promet.getSvrha());
            transsactionList.add(accountTransactionDto);
        }

        if (filterPartijeDto.getBrojPartije() != null && filterPartijeDto.getBrojPartije().trim().length() == 10) {
            accountTransactionListDto.setAccount(IbanFunctions.getIbanHr(GenericBassxConstants.CoreHub.VBDI_BANKE, filterPartijeDto.getBrojPartije()));
        }*/
        accountTransactionListDto.setBooked(transsactionList);
        return accountTransactionListDto;
    }

    private String getBrojPartijeByIban(String ibanAcc) {
        String brojPartije = null;
        if (StringUtils.isNotBlank(ibanAcc) && ibanAcc.length() >= 10) {
            brojPartije = ibanAcc.substring(ibanAcc.length() - 10);
        }
        return brojPartije;
    }

    public Document formCAMTTransations(String sifraPartije, String partiPar, String valuta, List<String> listaVal, PrometStanjePartijeDto filterPartijeDto, Boolean isPravna) {
        Document doc = null;
        List<Integer> listPar = new ArrayList<>();
        listPar.add(Integer.parseInt(sifraPartije));
        try {
            //doc = LookupHelper.getIInternetProxyRemote().getIzvodiCAMT(sifraPartije, partiPar, filterPartijeDto.getDatumPocetka(), filterPartijeDto.getDatumKraja(), valuta, listaVal, isPravna); // stari
            doc = getIzvodiCAMT(sifraPartije, partiPar, filterPartijeDto.getDatumPocetka(), filterPartijeDto.getDatumKraja(), valuta, listaVal, isPravna); // prazni
            //} catch (AbitRuleException e) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }
}
