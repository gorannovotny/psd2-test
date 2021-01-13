package hr.abc.psd2.bean.impl;

import hr.abc.psd2.bean.IConfirmationOfFundsBean;
import hr.abc.psd2.model.dto.cof.ConsentCofDto;
import hr.abc.psd2.model.dto.pspic.ConfirmationOfFoundsRequestDto;
import hr.abc.psd2.model.dto.pspic.ConfirmationOfFoundsResponseDto;
import hr.abc.psd2.util.Bassx2Constants;
import hr.abc.psd2.util.GenericBassxConstants;
import hr.abc.psd2.util.InternationalizationUtil;
import hr.abc.psd2.util.Psd2Util;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class AbstractConfirmationOfFundsBean implements IConfirmationOfFundsBean , Serializable {

    private static final long serialVersionUID = 1L;

    protected abstract String getPartiParBySifraPar(String sifraPar);

    protected abstract List<String> getSifraValuteListBySifraPar(String iban);

    @Override
    public ConfirmationOfFoundsResponseDto resolveFounds(ConfirmationOfFoundsRequestDto foundsRequestDto, ConsentCofDto consentCofDto) {
        ConfirmationOfFoundsResponseDto response = new ConfirmationOfFoundsResponseDto();

        List<Integer> sifraPartijeList = new ArrayList<>();
        Map<Integer, BigDecimal> raspolozivo;
        BigDecimal trazeniIznos = new BigDecimal(foundsRequestDto.getInstructedAmount().getAmount());
        String sifraValTrazenogIznosa = foundsRequestDto.getAccount().getSifraVal();
        BigDecimal iznosResult = new BigDecimal("0");
        try {
            //get trazeni iznos
            BigDecimal trazeniIznosKN = getIznosInHrk(sifraValTrazenogIznosa, trazeniIznos);

            //provjerimo dal se access cof consent odnsi na IBAN iz requesta...
            if (StringUtils.equals(consentCofDto.getConsentAccountCofDto().getIbanAcc(),foundsRequestDto.getAccount().getIban())) {
                // dohvat broja partije (partiPar) iz sifre partije...
                String partiPar = getPartiParBySifraPar(String.valueOf(consentCofDto.getConsentAccountCofDto().getSifraPar()));

                //za fizičku osobu
                //if (!PartijaKomitentUtil.isPravnaOsobaByBrojPartije(partiPar, entityManager)) {
                if (true) { // TODO Ivana
                    sifraPartijeList.add(consentCofDto.getConsentAccountCofDto().getSifraPar());
                    //raspolozivo = RetailSaldoUtil.getRaspolozivoPoPartijamaZaSveValuteUKunskojProtuvrijednostiMap(sifraPartijeList, false, false, true, true, entityManager);
                    raspolozivo = null; // TODO Ivana
                    if (raspolozivo != null && !raspolozivo.isEmpty()) {
                        iznosResult = raspolozivo.get(sifraPartijeList.get(0));
                    }

                    //za pravnu
                } else {
                    List<String> listOznakaNadDijela = new ArrayList<String>();
                    listOznakaNadDijela.add(GenericBassxConstants.Core.NADDIO_OZNKANAD_GLAVNICA);
                    listOznakaNadDijela.add(GenericBassxConstants.Core.NADDIO_OZNKANAD_REZERVACIJA);
                    List<String> listaValutaPoPartiji = getSifraValuteListBySifraPar(consentCofDto.getConsentAccountCofDto().getIbanAcc());
                    for (String valuta : listaValutaPoPartiji) {
                        //BigDecimal raspolzivoPoValuti = RaspolozivoUtil.getRaspolozivo(consentCofDto.getConsentAccountCofDto().getSifraPar(), listOznakaNadDijela, valuta, GenericDateUtil.today(), null, false, entityManager);
                        BigDecimal raspolzivoPoValuti = null; // TODO Ivana
                        if (raspolzivoPoValuti.compareTo(BigDecimal.ZERO) <= 0) {
                            raspolzivoPoValuti = BigDecimal.ZERO;
                        }
                        BigDecimal raspolozivoHrk = getIznosInHrk(valuta, raspolzivoPoValuti); //raspoloživo u HRK
                        iznosResult = iznosResult.add(raspolozivoHrk);
                    }
                }
                //compare to trazeni iznos...
                if (iznosResult != null) {
                    if (iznosResult.compareTo(trazeniIznosKN) >= 0) {
                        response.setValid(true);
                        response.setFundsAvailable(true);
                    } else {
                        response.setValid(true);
                        response.setFundsAvailable(false);
                    }
                }
            } else{
                response.setValid(false);
                response.setErrorInformationDto(Psd2Util.setErrorInformationDto("CONSENT_INVALID", new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_conf_funds_no_funds_acess", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
            }
//        } catch (AbitRuleException a) {
//            a.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private BigDecimal getIznosInHrk(String sifraValute, BigDecimal iznos) {
        BigDecimal tecaj;
        BigDecimal resultAmmount = null;
        try {
            if (sifraValute.equals(Bassx2Constants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY)) {
                tecaj = BigDecimal.ONE;
            } else {
                //tecaj = TecajeviUtil.getIznosTecaja(DateUtil.today(), sifraValute, Bassx2Constants.Core.TIP_TECAJA_SREDNJI, entityManager);
                tecaj = null; // TODO Ivana
            }
            resultAmmount = iznos.multiply(tecaj).setScale(2, BigDecimal.ROUND_HALF_UP);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultAmmount;
    }
}
