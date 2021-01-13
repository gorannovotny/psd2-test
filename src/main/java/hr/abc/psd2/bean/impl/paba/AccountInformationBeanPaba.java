package hr.abc.psd2.bean.impl.paba;

import static hr.abc.psd2.bean.impl.ServiceConstants.SERVICE_PABA;
import static hr.abc.psd2.bean.impl.ServiceConstants.TRUE;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

import hr.sberbank.model.account.TransactionDetails;
import org.apache.commons.lang3.StringUtils;

import hr.abc.psd2.bean.IAccountInformationBean;
import hr.abc.psd2.bean.impl.AbstractAccountInformationBean;
import hr.abc.psd2.dao.AccountInformationDao;
import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.model.dto.ais.AccountBalanceDto;
import hr.abc.psd2.model.dto.ais.AccountBalanceResponse;
import hr.abc.psd2.model.dto.ais.AccountReferenceDto;
import hr.abc.psd2.model.dto.ais.Amount;
import hr.abc.psd2.model.dto.ais.ConsentAccountAisDto;
import hr.abc.psd2.model.dto.ais.PrometStanjePartijeDto;
import hr.abc.psd2.model.dto.core.Psd2WebPregledRacunaDto;
import hr.abc.psd2.model.dto.core.WebKorisnikRacunDto;
import hr.abc.psd2.model.sepa.camt.Document;
import hr.abc.psd2.service2.ICoreSrvc;
import hr.abc.psd2.service2.IInternetSrvc;
import hr.abc.psd2.util.GenericBassxConstants;
import hr.abc.psd2.util.IbanFunctions;
import hr.abc.psd2.util.Psd2Constants;
import io.quarkus.arc.properties.IfBuildProperty;

@Dependent
@IfBuildProperty(name = SERVICE_PABA, stringValue = TRUE)
@Transactional
public class AccountInformationBeanPaba extends AbstractAccountInformationBean implements IAccountInformationBean, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AccountInformationDao accountInformationDao;

	@Inject
	private IInternetSrvc internetSrvc; // prazni
	@Inject
	private ICoreSrvc coreSrvc; // prazni

	public AccountBalanceResponse loadAccountBalances(List<ConsentAccountAisDto> accountAisDtoList, String resourceID) throws AbitRuleException {
		AccountBalanceResponse accountBalanceResponse = null;
		List<Psd2WebPregledRacunaDto> listaRacunaResult = null;
		if (accountAisDtoList != null && !accountAisDtoList.isEmpty()) {
			listaRacunaResult = new ArrayList<>();
			Integer sifraPartije = accountAisDtoList.get(0).getSifraPar();
			Set<String> valutaSet = new HashSet<>();
			for (ConsentAccountAisDto accountAisDto : accountAisDtoList) {
				valutaSet.add(accountAisDto.getCurrencyAcc());
			}
			//is Pravna
			//Boolean isPravna = PartijaKomitentUtil.isPravnaOsobaBySifraPartije(sifraPartije, entityManager);
			Boolean isPravna = null; //TODO Ivana
			//get lista racuna with details
			//List<Psd2WebPregledRacunaDto> listaWebRacuna = LookupHelper.getIInternetProxyRemote().getPopisRacunaFromIb(new ArrayList<>(Arrays.asList(sifraPartije))); //stari
			List<Psd2WebPregledRacunaDto> listaWebRacuna = getPopisRacunaFromIb(new ArrayList<>(Arrays.asList(sifraPartije))); // prazni
			if (!isPravna) { //RETAIL RAČUNI
				Map<String, BigDecimal> mapaValutaPoPartiji = null;
				//Map<Integer, Map<String, BigDecimal>> raspolozivoRetail = RetailSaldoUtil.getRaspolozivoPoAVFOPartijamaIValutamaMap(new ArrayList<>(sifraPartije), entityManager);
				Map<Integer, Map<String, BigDecimal>> raspolozivoRetail = null; // TODO Ivana
				for (Psd2WebPregledRacunaDto racun : listaWebRacuna) {
					//budući da objekt Psd2WebPregledRacunaDto ne sadrži oznaku valuta nego sifru!!
					//String oznakaValute = (String) PlatniUtil.getFieldValueFromTableRecord("valuta", "oznka_val", "sifra_val", racun.getValuta(), entityManager);
					String oznakaValute = null; //TODO Ivana
					if (valutaSet.contains(oznakaValute) || valutaSet.contains("MVR")) {
						//Budući da se samo vide transakcijski računi, provjerimo klasu partije
						if (!Arrays.asList(WebKorisnikRacunDto.KLASA_TRANSAKCIJSKI_STANOVNISTVO).contains(racun.getSifraKlasePartije()))
							continue;
						if (racun.getBrojPartije() != null && racun.getBrojPartije().trim().length() == 10) {
							racun.setIban(IbanFunctions.getIbanHr(GenericBassxConstants.CoreHub.VBDI_BANKE, racun.getBrojPartije()));
						}
						mapaValutaPoPartiji = new HashMap<>();
						mapaValutaPoPartiji = raspolozivoRetail.get(racun.getSifraPartije());
						racun.setRaspolozivo(mapaValutaPoPartiji != null && mapaValutaPoPartiji.get(racun.getValuta()) != null && mapaValutaPoPartiji.get(racun.getValuta()).compareTo(BigDecimal.ZERO) >= 0 ? mapaValutaPoPartiji.get(racun.getValuta()) : BigDecimal.ZERO);
						listaRacunaResult.add(racun);

					}
				}
			} else {    //AVPO RAČUNI
				List<String> listOznakaNadDijela = new ArrayList<String>();
				listOznakaNadDijela.add(GenericBassxConstants.Core.NADDIO_OZNKANAD_GLAVNICA);
				listOznakaNadDijela.add(GenericBassxConstants.Core.NADDIO_OZNKANAD_REZERVACIJA);
				for (Psd2WebPregledRacunaDto racun : listaWebRacuna) {
					//budući da objekt Psd2WebPregledRacunaDto ne sadrži oznaku valuta nego sifru!!
					//String oznakaValute = (String) PlatniUtil.getFieldValueFromTableRecord("valuta", "oznka_val", "sifra_val", racun.getValuta(), entityManager);
					String oznakaValute = null; // TODO Ivana
					if (valutaSet.contains(oznakaValute) || valutaSet.contains("MVR")) {
						if (!Arrays.asList(WebKorisnikRacunDto.KLASA_TRANSAKCIJSKI_PRAVNE).contains(racun.getSifraKlasePartije()))
							continue;
						if (racun.getBrojPartije() != null && racun.getBrojPartije().trim().length() == 10) {
							racun.setIban(IbanFunctions.getIbanHr(GenericBassxConstants.CoreHub.VBDI_BANKE, racun.getBrojPartije()));
						}
						//racun.setRaspolozivo(RaspolozivoUtil.getRaspolozivo(racun.getSifraPartije(), listOznakaNadDijela, racun.getValuta(), GenericDateUtil.today(), null, false, entityManager));
						racun.setRaspolozivo(null); // TODO Ivana
						listaRacunaResult.add(racun);
					}
				}
				if (listaRacunaResult != null && !listaRacunaResult.isEmpty()) {
					for (Psd2WebPregledRacunaDto pregled : listaRacunaResult) {
						if (pregled.getStanje() == null)
							pregled.setStanje(BigDecimal.ZERO);
						if (pregled.getRaspolozivo() == null)
							pregled.setRaspolozivo(BigDecimal.ZERO);
					}
				}
			}
			//form PSD2 response object
			if (listaRacunaResult != null && !listaRacunaResult.isEmpty()) {
				String sifraValReq = resourceID.substring(resourceID.length() - 3);
				listaRacunaResult = filtrirajListuRacuna(listaRacunaResult, sifraValReq);
				accountBalanceResponse = new AccountBalanceResponse();
				AccountReferenceDto accountReferenceDto = new AccountReferenceDto();
				accountReferenceDto.setIban(listaRacunaResult.get(0).getIban());
				accountBalanceResponse.setAccount(accountReferenceDto);
				List<AccountBalanceDto> balances = new ArrayList<>();
				for (Psd2WebPregledRacunaDto webPregledRacunaDto : listaRacunaResult) {
					//expected balance
					AccountBalanceDto balance = new AccountBalanceDto();
					balance.setBalanceType(Psd2Constants.BalanceType.EXPECTED.getValue());
					Amount amount = new Amount();
					amount.setAmount(webPregledRacunaDto.getRaspolozivo().toString());
					//amount.setCurrency((String) PlatniUtil.getFieldValueFromTableRecord("valuta", "oznka_val", "sifra_val", webPregledRacunaDto.getValuta(), entityManager));
					amount.setCurrency(null); // TODO Ivana
					balance.setBalanceAmount(amount);
					balances.add(balance);
				}
				accountBalanceResponse.setBalances(balances);
			}

		}
		return accountBalanceResponse;
	}

	private List<Psd2WebPregledRacunaDto> filtrirajListuRacuna(List<Psd2WebPregledRacunaDto> listaRacuna, String sifraValute) {
		List<Psd2WebPregledRacunaDto> resultList = new ArrayList<>();
		//ako je MVR ostavi balance po svim valutama !!
		if (StringUtils.equals(sifraValute, "999")) {
			resultList = listaRacuna;
		} else {
			for (Psd2WebPregledRacunaDto racun : listaRacuna) {
				if (StringUtils.equals(racun.getValuta(), sifraValute)) {
					resultList.add(racun);
				}
			}
		}
		return resultList;
	}

	@Override
	protected List<Psd2WebPregledRacunaDto> getAccounts(String iban, String currency) {
		return null;
	}

	public List<Psd2WebPregledRacunaDto> getPregledRacuna(List<Integer> listaPartija) throws AbitRuleException {
		List<Psd2WebPregledRacunaDto> listaRacuna = null;
		if (listaPartija != null && !listaPartija.isEmpty()) {
			// listaRacuna = LookupHelper.getIInternetProxyRemote().getPopisRacunaFromIb(listaPartija); // stari
			listaRacuna = internetSrvc.getPopisRacunaFromIb(listaPartija); // prazni
		}
		return listaRacuna;

	}

	@Override
	protected List<Psd2WebPregledRacunaDto> getPopisRacunaFromIb(List<Integer> listSifraParOvlastenja) {
		return internetSrvc.getPopisRacunaFromIb(listSifraParOvlastenja);
	}

	@Override
	protected Document getIzvodiCAMT(String sifraPartije, String patriPar, Date datumOd, Date datumDo, String sifraValute, List<String> listaValuta, Boolean isPravna) {
		return internetSrvc.getIzvodiCAMT(sifraPartije, patriPar, datumOd, datumDo, sifraValute, listaValuta, isPravna);
	}

	@Override
	protected List<String> getSifraValuteListBySifraPar(String iban) {
		//promijenjeno u iban
		return null; // accountInformationDao.getSifraValuteListBySifraPar(iban);
	}

	@Override
	protected List<PrometStanjePartijeDto> getPrometiKontraStrana(PrometStanjePartijeDto filterDto, String reportType, boolean isPravna) {
		return coreSrvc.getPrometiKontraStrana(filterDto, reportType, isPravna);
	}

	@Override
	protected String getPartiParBySifraPar(String sifraPar) {
		return accountInformationDao.getPartiParBySifraPar(sifraPar);
	}

	@Override
	protected List<TransactionDetails> getTransactionsByAccount(String account) {
		return null;
	}

}
