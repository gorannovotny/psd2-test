package hr.abc.psd2.util;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Tomica on 10-Oct-18.
 */
@Slf4j
public class Psd2Util_origin {

    public static String getInterfaceEnvironment(EntityManager entityManager) {
        String interfaceEnvironment = null;
        try {
            if (Bassx2DatabaseUtil.isProdukcija(entityManager))
                interfaceEnvironment = "PSD2 API - PRODUKCIJA";
            else if (Bassx2DatabaseUtil.isAnaliza(entityManager))
                interfaceEnvironment = "PSD2 API - ANALIZA";
            else if (Bassx2DatabaseUtil.isTest(entityManager))
                interfaceEnvironment = "PSD2 API - TEST";
            else
                interfaceEnvironment = "PSD2 API - RAZVOJ";
        } catch (Exception e) {
            interfaceEnvironment = null;
            log.error("Exception is:", e);
        }
        return interfaceEnvironment;
    }


    public static String getVrijednostParametraBySifra(String sifraParametra, EntityManager entityManager) {
        String value = null;
        if (StringUtils.isNotBlank(sifraParametra)) {
            String sql = "select p.vrjed_par from parametar p where p.sifra_par = :parameterName ";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("parameterName", sifraParametra);
            try {
                value = (String) query.getSingleResult();
            } catch (Exception e) {
                log.error("Exception is:", e);
            }
        }
        return value;
    }

    public static String getModulWsLink(String modul, EntityManager entityManager) {
        String link = null;
        link = getVrijednostParametraBySifra(Psd2Constants.PARAMETAR_PSD2_LINK, entityManager);
        switch (modul) {
            case (Psd2Constants.MODUL_PSD2_BACK):
                link = link + Psd2Constants.LINK_PSD2_BACK;
                break;
            case (Psd2Constants.MODUL_IB):
                link = link + Psd2Constants.LINK_IB;
                break;
            case (Psd2Constants.MODUL_PLATNI):
                link = link + Psd2Constants.LINK_PLATNI;
                break;
        }
        return link;
    }
    public static String getObjectAsString(Object o){
        String objectStr = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
           objectStr = mapper.writeValueAsString(o);

        }catch (Exception e){
            log.error("Exception is:", e);
        }

        return objectStr;
    }



}
