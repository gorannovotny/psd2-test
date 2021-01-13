package hr.abc.psd2.resources.ws.impl;

import hr.abc.psd2.bean.IConfirmationOfFundsBean;
import hr.abc.psd2.bean.IPsd2UtilBean;
import hr.abc.psd2.bean.impl.paba.ConfirmationOfFundsServiceBeanPaba;
import hr.abc.psd2.bean.impl.paba.Psd2UtilBeanPaba;
import hr.abc.psd2.model.dto.cof.ConsentCofDto;
import hr.abc.psd2.model.dto.error.ErrorCode;
import hr.abc.psd2.model.dto.pspic.ConfirmationOfFoundsRequestDto;
import hr.abc.psd2.model.dto.pspic.ConfirmationOfFoundsResponseDto;
import hr.abc.psd2.resources.ws.IConfirmationOfFundsServiceResource;
import hr.abc.psd2.util.Psd2Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.abc.psd2.util.Psd2Util;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Web service resource for Confirmation of Funds Service .
 * <p>
 * Created by dKosutar on 25.03.2019..
 */

@Slf4j
public class ConfirmationOfFundsServiceResource implements IConfirmationOfFundsServiceResource {

   
    @EJB
    IConfirmationOfFundsBean foundsBean;

    @EJB
    IPsd2UtilBean psd2UtilBean;

    /**
     * summary: foundsConfirmations
     * description:
     * Creates a confirmation of funds request at the ASPSP.
     * Checks whether a specific amount is available at point of time of the request on an account linked to a given tuple card issuer(TPP)/card number,
     * or addressed by IBAN and TPP respectively
     */
    @Override
    public Response foundsConfirmations(ConfirmationOfFoundsRequestDto confirmationOfFoundsRequestDto, ContainerRequestContext ctx) {
        Response response = null;
        ConfirmationOfFoundsResponseDto foundsResponse;

        try {
            MultivaluedMap<String, String> requestHeaders = ctx.getHeaders();
            String xRequestId = requestHeaders.getFirst(Psd2Constants.RequestHeaderAttributes.X_REQUEST_ID.getValue());
            String tppId = requestHeaders.getFirst(Psd2Constants.RequestHeaderAttributes.TPP_ID.getValue());
            String consentId = requestHeaders.getFirst(Psd2Constants.RequestHeaderAttributes.CONSENT_ID.getValue());

            //validate request body
            // confirmationOfFoundsRequestDto = psd2UtilBean.validateConfirmationOfFoundsRequestBody(confirmationOfFoundsRequestDto);
            confirmationOfFoundsRequestDto = psd2UtilBean.validateConfirmationOfFoundsRequestBody(confirmationOfFoundsRequestDto);

            if (confirmationOfFoundsRequestDto.getValid()) {
                // ConsentCofDto consentCofDto = psd2UtilBean.validateConsentCof(consentId, tppId); // stari
                ConsentCofDto consentCofDto = psd2UtilBean.validateConsentCof(consentId, tppId); // prazni
                if (consentCofDto.getIsValid()) {
                    // foundsResponse = foundsBean.resolveFounds(confirmationOfFoundsRequestDto, consentCofDto); // stari
                    foundsResponse = foundsBean.resolveFounds(confirmationOfFoundsRequestDto, consentCofDto); // prazni
                    if (foundsResponse.getValid()) {
                        foundsResponse.setValid(null);
                        response = Response.status(Response.Status.OK).entity(foundsResponse).build();
                    } else {
                        //proses error for response object
                        if (foundsResponse.getErrorInformationDto().getTppMessages() != null && !foundsResponse.getErrorInformationDto().getTppMessages().isEmpty() &&
                                foundsResponse.getErrorInformationDto().getTppMessages().get(0).getCode().equals("CONSENT_INVALID")) {
                            response = Response.status(Response.Status.BAD_REQUEST).entity(foundsResponse.getErrorInformationDto()).build();
                        }
                    }
                } else {
                    if (consentCofDto.getErrorInformationDto().getTppMessages() != null && !consentCofDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            consentCofDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.STATUS_INVALID.getValue())) {
                        response = Response.status(Response.Status.CONFLICT).entity(consentCofDto.getErrorInformationDto()).build();
                    }else if (consentCofDto.getErrorInformationDto().getTppMessages() != null && !consentCofDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            consentCofDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals("CONSENT_UNKNOWN")){
                        response = Response.status(Response.Status.BAD_REQUEST).entity(consentCofDto.getErrorInformationDto()).build();
                    }else if (consentCofDto.getErrorInformationDto().getTppMessages() != null && !consentCofDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            consentCofDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.FORMAT_ERROR.getValue())){
                        response = Response.status(Response.Status.BAD_REQUEST).entity(consentCofDto.getErrorInformationDto()).build();
                    }
                }
            } else {
                //proses error for request object
                if (confirmationOfFoundsRequestDto.getErrorInformationDto().getTppMessages() != null && !confirmationOfFoundsRequestDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                        confirmationOfFoundsRequestDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.FORMAT_ERROR.getValue())) {
                    response = Response.status(Response.Status.BAD_REQUEST).entity(confirmationOfFoundsRequestDto.getErrorInformationDto()).build();
                }else if (confirmationOfFoundsRequestDto.getErrorInformationDto().getTppMessages() != null && !confirmationOfFoundsRequestDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                        confirmationOfFoundsRequestDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.PARAMETER_NOT_SUPPORTED.getValue())){
                    response = Response.status(Response.Status.BAD_REQUEST).entity(confirmationOfFoundsRequestDto.getErrorInformationDto()).build();
                } else {
                    response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();

                }

            }


        } catch (Exception e) {
            log.error("Exception is:", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
        }
        return response;
    }
}
