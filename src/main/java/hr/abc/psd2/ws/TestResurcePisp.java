package hr.abc.psd2.ws;


import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import hr.abc.psd2.dao.NalogTppDao;
import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.model.dto.ais.AccountReferenceDto;
import hr.abc.psd2.model.dto.ais.Amount;
import hr.abc.psd2.model.dto.pis.SinglePaymentDto;
import hr.abc.psd2.security.Globals;

@Path("/testResurcePisp")
@Produces("application/json")


public class TestResurcePisp {
    @Inject    NalogTppDao nalogTppDao;
    @GET
    @Path("/test1")
    public String testValidateAndSetSepaNalog() {
        SinglePaymentDto pnDto = initNalog();
        try {
            nalogTppDao.validateAndSetSepaNalog(pnDto,bgGlobals());
        } catch (AbitRuleException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "TEST OK";
    }

    private SinglePaymentDto initNalog(){
        SinglePaymentDto pnDto = SinglePaymentDto.builder().build();
        pnDto.setDebtorAccount(new AccountReferenceDto());
        pnDto.getDebtorAccount().setIban("HR4024080021100002694");
        pnDto.setCreditorAccount(new AccountReferenceDto());
        pnDto.getCreditorAccount().setIban("HR6023600001102288181");
        pnDto.setInstructedAmount(new Amount());
        pnDto.getInstructedAmount().setAmount("0.03");
        pnDto.getInstructedAmount().setCurrency("191");
        pnDto.setRemittanceInformationUnstructured("Test PISP - Vanjča");
        pnDto.setCreditorName("Test Cred name <");
        pnDto.setDebtorId("12345 test");
        pnDto.setPaymentProduct("sepa-credit-transfers");
        return pnDto;
    }

    private Globals bgGlobals(){
        return Globals.createBackgroundGlobals(2003, "130", 3);
    }

}
