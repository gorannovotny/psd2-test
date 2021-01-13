package hr.abc.psd2.dao;

import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Dependent
@Transactional
public class Psd2UtilDao  {

    @Inject EntityManager entityManager;


    public Psd2UtilDao() {

    }


    private static final long serialVersionUID = 1L;



    public Integer getMaxBrojIbPotpisnika(List<Integer> sifraPar, List<String> partiPar) {
        Integer result = null;
        /*try {
            String sql = "select max(value_dod) from partija" +
                    " left join dod_podaci on partija.sifra_par = parti_dod where";
            if (sifraPar != null && !sifraPar.isEmpty()) {
                sql = sql.concat(" tippo_dod = " + TipDodpodDto.TIP_BROJ_POTPISA_IB + " and partija.sifra_par in (:sifraPar)");
            } else if (partiPar != null && !partiPar.isEmpty()) {
                sql = sql.concat(" tippo_dod = " + TipDodpodDto.TIP_BROJ_POTPISA_IB + " and partija.parti_par in (:partiPar)");
            }

            Query query = entityManager.createNativeQuery(sql);
            if (sifraPar != null && !sifraPar.isEmpty()) {
                query.setParameter("sifraPar", sifraPar);
            } else if (partiPar != null && !partiPar.isEmpty()) {
                query.setParameter("partiPar", partiPar);
            }
            Object resultObject = query.getSingleResult();
            if (resultObject != null) {
                result = Integer.valueOf((String) resultObject);
            } else {
                result = new Integer(1);
            }
        } catch (Exception e) {
            log.error("Exception is:", e);
        }*/
        //TODO add more conditions...
        return result;
    }

    /**
     * Unos x-request-id-a.
     *
     * @param xRequestId
     * @return
     * @author tkorpar
     */
    public void insertXRequestId(String xRequestId) {
        // SQL upit za insert u bazu podataka
        String sqlInsertXRequestId = "insert into psd2_x_request_id (request_id) values (:xRequestId)";
        // priprema upita
        Query queryInsertXRequestId = entityManager.createNativeQuery(sqlInsertXRequestId);
        queryInsertXRequestId.setParameter("xRequestId", xRequestId);
        // izvršavanje na bazi podataka
        queryInsertXRequestId.executeUpdate();
    }

}

