package hr.abc.psd2.util;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

/**
 * Klasa sadrži jednostavne dohvate iz baze podataka u utility modul kako se ne bi prosljeđivali DAO-i - za IB.
 *
 * @author Kristijan Novak
 */
public class Bassx2MinDatabaseUtil {

    /**
     * Metoda vraća vrijednost parametra.
     *
     * @param parameterName
     * @param em
     * @return
     * @author Matija Hlapčić
     */
    public static String getParameterValue(String parameterName, EntityManager em) {
        String value = null;
        if (StringUtils.isNotBlank(parameterName) && em != null) {
            String sql = "select vrjed_par from parametar where sifra_par = :parameterName " ;
            Query query = em.createNativeQuery(sql);
            query.setParameter("parameterName", parameterName.trim());
            try {
                value = (String) query.getSingleResult();
            } catch (Exception e) {
                LoggerFactory.getLogger(Bassx2MinDatabaseUtil.class.getName()).debug(e.getMessage());
            }
        }
        return value;
    }

    /**
     * Provjera podatka o radnoj okolini.
     *
     * @param em
     * @return true/false
     * @author Goran
     */
    public static boolean isProdukcija(EntityManager em) {
        return Bassx2MinDatabaseUtil.isProdukcijaByDbHostname(em);
    }
    public static boolean isAnaliza(EntityManager em) {
        return Bassx2MinDatabaseUtil.isAnalizaByDbHostname(em);
    }
    public static boolean isTest(EntityManager em) {
        return Bassx2MinDatabaseUtil.isTestByDbHostname(em);
    }

    private static boolean isProdukcijaByDbHostname(EntityManager em) {
        String hostnameProd = getParameterValue("DBHOSTNAME_PR", em);
        String hostname = getDbHostname(em);
        return StringUtils.equals(hostname, hostnameProd);
    }

    private static boolean isAnalizaByDbHostname(EntityManager em) {
        String hostnameProd = getParameterValue("DBHOSTNAME_AN", em);
        String hostname = getDbHostname(em);
        return StringUtils.equals(hostname, hostnameProd);
    }

    private static boolean isTestByDbHostname(EntityManager em) {
        String hostnameProd = getParameterValue("DBHOSTNAME_TE", em);
        String hostname = getDbHostname(em);
        return StringUtils.equals(hostname, hostnameProd);
    }

    public static String getDbHostname(EntityManager em) {
        try {
            String actualDbHostname = (String) em.createNativeQuery("select dbinfo('dbhostname') from systables where tabid = 1").getSingleResult();
            return actualDbHostname;
        } catch (Exception e) {
            LoggerFactory.getLogger(Bassx2MinDatabaseUtil.class.getName()).warn(e.getMessage(), e);
            return null;
        }
    }

    public static String getDbSchema(EntityManager em) {
        try {
            String actualDbSchema = (String) em.createNativeQuery("select dbinfo('dbname') from systables where tabid = 1").getSingleResult();
            return actualDbSchema;
        } catch (Exception e) {
            LoggerFactory.getLogger(Bassx2MinDatabaseUtil.class.getName()).warn(e.getMessage(), e);
            return null;
        }
    }


}
