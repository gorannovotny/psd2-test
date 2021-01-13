package hr.abc.psd2.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.model.dto.ais.ConsentActionAisDto;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class ConsentActionAisDao implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int IDX_SIFRA_ACT = 0;
    private static final int IDX_STATE_ACT = 1;
    private static final int IDX_DATE_ACT = 2;
    private static final int IDX_SICIN_ACT = 3;
    private static final int IDX_TPPID_ACT = 4;
    private static final int IDX_REQ_ACT = 5;


    private static final Logger logger = LoggerFactory.getLogger(ConsentActionAisDao.class);

    @Inject
    private EntityManager entityManager;


    //Insert ConsentActionAis

    public ConsentActionAisDto logConsentActionAis(ConsentActionAisDto consentActionAisDto) throws AbitRuleException {
        //SQL String
        String sqlInsertConsentActionAis = "INSERT into psd2_consent_action_ais (sifra_act, state_act, date_act, sicon_act, tppid_act, reqid_act)" +
                " VALUES (0, :state_act, :date_act, :conid_act, :tpp_id, :reqid_act)";
        try {
            //prepared query
            Query queryInsertConsentActionAis = entityManager.createNativeQuery(sqlInsertConsentActionAis);

            //setting query params
            queryInsertConsentActionAis.setParameter("state_act", consentActionAisDto.getStateAct());
            queryInsertConsentActionAis.setParameter("date_act", Timestamp.valueOf(LocalDateTime.now()));
            queryInsertConsentActionAis.setParameter("conid_act", consentActionAisDto.getSiconAct());
            queryInsertConsentActionAis.setParameter("tpp_id", consentActionAisDto.getTppidAct());
            queryInsertConsentActionAis.setParameter("reqid_act", consentActionAisDto.getxRequestId());

            //execute query
            queryInsertConsentActionAis.executeUpdate();

            //dohvat unesenog loga
            String sqlPk = "SELECT DBINFO('sqlca.sqlerrd1') from systables where tabid = 1";
            Object primaryKey = entityManager.createNativeQuery(sqlPk).getSingleResult();
            consentActionAisDto.setSifraAct((Integer) primaryKey);

        } catch (Exception e) {
            log.error("Exception is:", e);

        }
        return consentActionAisDto;
    }

    public List<ConsentActionAisDto> getActionByConsentID(String consentId) {

        List<ConsentActionAisDto> actionDtoList = null;
        String sql = "select * from psd2_consent_action_ais where sicon_act = :sicon_act";
        try {
            Query selectQuery = entityManager.createNativeQuery(sql);
            selectQuery.setParameter("sicon_act", consentId);
            List<Object[]> resultList = selectQuery.getResultList();
            if (resultList != null) {
                actionDtoList = formListOfActionDto(resultList);
            }

        } catch (Exception e) {
            log.error("Exception is:", e);
        }
        return actionDtoList;
    }

    private ConsentActionAisDto formActionDto(Object[] actionObject) {
        ConsentActionAisDto actionDto = null;
        if (actionObject != null) {
            actionDto = new ConsentActionAisDto();
            actionDto.setSifraAct((Integer) actionObject[IDX_SIFRA_ACT]);
            actionDto.setStateAct((String) actionObject[IDX_STATE_ACT]);
            actionDto.setDateAct(((Timestamp) actionObject[IDX_DATE_ACT]).toLocalDateTime());
            actionDto.setSiconAct((Integer) actionObject[IDX_SICIN_ACT]);
            actionDto.setTppidAct((String) actionObject[IDX_TPPID_ACT]);
            actionDto.setxRequestId((String) actionObject[IDX_REQ_ACT]);
        }
        return actionDto;
    }

    private List<ConsentActionAisDto> formListOfActionDto(List<Object[]> actionObjectList) {
        List<ConsentActionAisDto> consentActionAisDtoList = null;
        if (actionObjectList != null) {
            consentActionAisDtoList = new ArrayList<>();
            for (Object[] actionObj : actionObjectList) {
                consentActionAisDtoList.add(formActionDto(actionObj));
            }
        }
        return consentActionAisDtoList;
    }

}
