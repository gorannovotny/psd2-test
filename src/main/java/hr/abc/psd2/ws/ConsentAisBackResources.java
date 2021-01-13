package hr.abc.psd2.ws;

import static hr.abc.psd2.model.RequestType.PATH;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.abc.psd2.bean.IConsentAisBean;
import hr.abc.psd2.bean.impl.paba.ConsentAisBeanPaba;
import hr.abc.psd2.dao.ConsentActionAisDao;
import hr.abc.psd2.dao.ConsentAisDao;
import hr.abc.psd2.model.RequestType;
import hr.abc.psd2.model.dto.ConsentRequestResponseDto;
import hr.abc.psd2.model.dto.ais.ConsentActionAisDto;
import hr.abc.psd2.model.dto.ais.ConsentAisDto;
import hr.abc.psd2.util.Psd2Util;

/**
 * Created by Tomica on 09-Oct-18.
 */
@Path("/consent")
public class ConsentAisBackResources {

    private static final Logger logger = LoggerFactory.getLogger(ConsentAisBackResources.class);

    @EJB
    private IConsentAisBean consentAisBean;

    @Inject
    private ConsentAisDao consentDao;

    @Inject
    private ConsentActionAisDao actionAisDao;

    /**
     * summary: Create consent
     * description:
     * This method create a consent resource, defining access rights to dedicated accounts of
     * a given PSU-ID. These accounts are addressed explicitly in the method as
     * parameters as a core function.
     */
    @POST
    @Path("/createAisConsent")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ConsentRequestResponseDto createAisConsent(ConsentRequestResponseDto consentRequestResponseDto) {
        ConsentRequestResponseDto consentRequestResponseDtoResult = new ConsentRequestResponseDto();
        try {
            consentRequestResponseDtoResult = consentRequestResponseDto;
            // consentRequestResponseDtoResult = consentAisBean.createConsentAis(consentRequestResponseDtoResult); // stari
            consentRequestResponseDtoResult = consentAisBean.createConsentAis(consentRequestResponseDtoResult); // prazni
        // } catch (AbitRuleException e) {
        } catch (Exception e) {
            logger.error("Exception is:", e);
            consentRequestResponseDtoResult.setIsValid(false);
            consentRequestResponseDtoResult.setErrorInformationDto(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<String>(Arrays.asList("Internal server error"))));
        }
        return consentRequestResponseDto;
    }

    /**
     * summary: Consent status request
     * description:
     * Read the status of an account information consent resource.
     */
    @SuppressWarnings("unchecked")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{consentId}/{tppId}/status")
    public ConsentRequestResponseDto getConsentStatus(@PathParam("consentId") String consentId, @PathParam("tppId") String tppId) {
        ConsentRequestResponseDto consentRequestResponseDtoResult = null;
        try {
            // consentRequestResponseDtoResult = consentDao.getConsentStatusBySifra(Integer.valueOf(consentId), tppId); // stari dao
            consentRequestResponseDtoResult = consentAisBean.getConsentStatusBySifra(Integer.valueOf(consentId), tppId); // umjesto preko dao-a ici ce preko servisa
        //} catch (AbitRuleException e) {
        } catch (Exception e) {
            logger.error("Exception is:", e);
            consentRequestResponseDtoResult.setIsValid(false);
            consentRequestResponseDtoResult.setErrorInformationDto(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<String>(Arrays.asList("Internal server error"))));
        }
        return consentRequestResponseDtoResult;
    }


    /**
     * summary: Get Consent Request
     * description:
     * Returns the content of an account information consent object.
     * This is returning the data for the TPP especially in cases,
     * where the consent was directly managed between ASPSP and PSU e.g. in a re-direct SCA Approach.
     */
    @SuppressWarnings("unchecked")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{consentId}/{tppId}")
    public ConsentRequestResponseDto getConsentInformation(@PathParam("consentId") String consentId, @PathParam("tppId") String tppId) {
        ConsentRequestResponseDto consentRequestResponseDtoResult = null;
        try {
            // consentRequestResponseDtoResult = consentDao.getConsentReqestBySifra(Integer.valueOf(consentId), tppId); // stari
            consentRequestResponseDtoResult = consentAisBean.getConsentRequestBySifra(Integer.valueOf(consentId), tppId); // umjesto preko dao-a ici ce preko servisa
        //} catch (AbitRuleException e) {
        } catch (Exception e) {
            logger.error("Exception is:", e);
            consentRequestResponseDtoResult.setIsValid(false);
            consentRequestResponseDtoResult.setErrorInformationDto(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<String>(Arrays.asList("Internal server error"))));
        }
        return consentRequestResponseDtoResult;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/getBySifra/{sifra}")
    public ConsentAisDto getConsentBySifra(@PathParam("sifra") String sifra) {
        ConsentAisDto consnAisDto = null;
        try {
            // consnAisDto = consentDao.getConsentAisBySifra(Integer.valueOf(sifra)); // stari
            consnAisDto = consentAisBean.getConsentAisBySifra(Integer.valueOf(sifra)); // umjesto preko dao-a ici ce preko servisa
        //} catch (AbitRuleException e) {
        } catch (Exception e) {
            logger.error("Exception is:", e);
            consnAisDto.setIsValid(false);
            consnAisDto.setErrorInformationDto(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<>(Arrays.asList("Internal server error"))));

        }
        return consnAisDto;
    }


    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/getListOfConsentsByKom/{sifraKom}")
    public List<ConsentAisDto> getListOfConsentsByKom(@PathParam("sifraKom") String sifraKom) {
        ConsentAisDto consentAisDto = null;
        List<ConsentAisDto> consnAisDtoList = new ArrayList<>();
        try {
            // consnAisDtoList = consentDao.getConsentAisDtoListByKom(Integer.valueOf(sifraKom)); // stari
            consnAisDtoList = consentAisBean.getConsentAisDtoListByKom(Integer.valueOf(sifraKom)); // umjesto preko dao-a ici ce preko servisa
        // } catch (AbitRuleException e) {
        } catch (Exception e) {
            logger.error("Exception is:", e);
            consentAisDto.setIsValid(false);
            consentAisDto.setErrorInformationDto(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<>(Arrays.asList("Internal server error"))));

        }
        return consnAisDtoList;
    }


    /**
     * summary: Delete Consent
     * description:
     * The TPP can delete an account information consent object if needed.
     */
    @SuppressWarnings("unchecked")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{consentId}/{tppId}")
    public ConsentRequestResponseDto deleteConsent(@PathParam("consentId") String consentId, @PathParam("tppId") String tppId) {
        ConsentRequestResponseDto consentRequestResponseDtoResult = null;
        try {
            // consentRequestResponseDtoResult = consentAisBean.deleteConsent(consentId, tppId); // stari
            consentRequestResponseDtoResult = consentAisBean.deleteConsent(consentId, tppId); // prazni
        // } catch (AbitRuleException e) {
        } catch (Exception e) {
            logger.error("Exception is:", e);
            consentRequestResponseDtoResult.setIsValid(false);
            consentRequestResponseDtoResult.setErrorInformationDto(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<String>(Arrays.asList("Internal server error"))));
        }
        return consentRequestResponseDtoResult;
    }


    /**
     * summary: Validate Consent
     */
    @SuppressWarnings("unchecked")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/validateConsent/{consentId}/{tppId}")
    public ConsentAisDto validConsent(@PathParam("consentId") String consentId, @PathParam("tppId") String tppId) {
        ConsentAisDto consentAisDtoResult = null;
        try {
            // consentAisDtoResult = consentDao.validateConsent(Integer.valueOf(consentId), tppId, null); //stari
            consentAisDtoResult = consentAisBean.validateConsent(Integer.valueOf(consentId), tppId, PATH); // umjesto preko dao-a ici ce preko servisa
        // } catch (AbitRuleException e) {
        } catch (Exception e) {
            logger.error("Exception is:", e);
            consentAisDtoResult = new ConsentAisDto();
            consentAisDtoResult.setIsValid(false);
            consentAisDtoResult.setErrorInformationDto(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<String>(Arrays.asList("Internal server error"))));
        }
        return consentAisDtoResult;
    }


    /**
     * summary: Log Consent Usage Action
     */
    @SuppressWarnings("unchecked")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/logConsentAction")
    public void logConsentAction(ConsentActionAisDto consentActionAisDto) {
        try {
            // consentAisBean.checkConsentAndLogAction(consentActionAisDto); // stari
            consentAisBean.checkConsentAndLogAction(consentActionAisDto); // prazni
        // } catch (AbitRuleException e) {
        } catch (Exception e) {
            logger.error("Exception is:", e);
        }
    }


















}




