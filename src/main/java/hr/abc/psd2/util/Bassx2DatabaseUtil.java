package hr.abc.psd2.util;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

/**
 * Klasa sadrži jednostavne dohvate iz baze podataka u utility modul kako se ne bi prosljeđivali DAO-i.
 *
 * @author Matija Hlapčić
 */
public class Bassx2DatabaseUtil extends Bassx2MinDatabaseUtil {

    private static boolean isProdukcijaByHostname(EntityManager em, String hostname) {
        String hostnameProd = getParameterValue("HOSTNAME_PROD", em);
        return StringUtils.equals(hostname, hostnameProd);
    }

    private static boolean isAnalizaByHostname(EntityManager em, String hostname) {
        String hostnameAna = getParameterValue("HOSTNAME_ANA", em);
        return StringUtils.equals(hostname, hostnameAna);
    }

    private static boolean isTestByHostname(EntityManager em, String hostname) {
        String hostnameTest = getParameterValue("HOSTNAME_TEST", em);
        return StringUtils.equals(hostname, hostnameTest);
    }

  
    /**
     * Provjera da li je datoteka sa istim HASH-om učitana u određenu tablicu u bazi.
     *
     * @param tableName
     * @param fieldName
     * @param hash
     * @param em
     * @return hashExists
     * @author Mario
     */
    public static boolean hashExistsInDatabase(String tableName, String fieldName, String hash, String extraCondition, EntityManager em) {
        boolean hashExists = false;
        String sqlHash = "select count(*) from " + tableName + " where " + fieldName + " = :hash";
        if (StringUtils.isNotBlank(extraCondition)) sqlHash += " " + extraCondition.trim();
        Query queryHash = em.createNativeQuery(sqlHash);
        queryHash.setParameter("hash", hash);
        try {
            Object countMessagesObject = queryHash.getSingleResult();
            if (countMessagesObject != null) {
                if ((MathFunctions.resolveOracleNumberToInteger(countMessagesObject)).compareTo(0) != 0) hashExists = true;
            }
        } catch (Exception e) {
            LoggerFactory.getLogger(Bassx2DatabaseUtil.class.getName()).debug(e.getMessage());
        }
        return hashExists;
    }
}
