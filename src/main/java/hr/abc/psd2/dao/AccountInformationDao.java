package hr.abc.psd2.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Dependent
public class AccountInformationDao {

    @Inject
    private EntityManager entityManager;

    /**
     * summary: Get account list
     */


    public List<String> getSifraValuteListBySifraPar(Integer sifraPar) {
        List<String> sifraValList = new ArrayList<>();
        try {
            //prespori sql i nepotreban!!
//            String sql = "select DISTINCT sifva_dio" +
//                    " from promet prm " +
//                    " inner join dio_partije dp on dp.sifra_dio = prm.diopa_prm" +
//                    " inner join partija p on p.sifra_par = prm.spart_prm" +
//                    " inner join komitent kom on kom.sifra_kom = p.vlasn_par" +
//                    " inner join tip_partije tp on tp.sifra_tip = p.tippa_par" +
//                    " left join ib_korisnik_racun ibr on ibr.parti_ibr = p.sifra_par" +
//                    " inner join valuta val on sifva_dio = sifra_val" +
//                    " where p.sifra_par = :sifra_par";
            String sql = "select DISTINCT sifva_dio" +
                    " from promet prm" +
                    " inner join dio_partije dp on dp.sifra_dio = prm.diopa_prm" +
                    " inner join partija p on p.sifra_par = prm.spart_prm" +
                    " left join ib_korisnik_racun ibr on ibr.parti_ibr = p.sifra_par" +
                    " inner join valuta val on sifva_dio = sifra_val" +
                    " where p.sifra_par = :sifra_par";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("sifra_par", sifraPar);
            sifraValList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sifraValList;
    }

    public String getPartiParBySifraPar(String sifraPar) {
        String parti_par = null;
        String sql = "select parti_par from partija where sifra_par = :sifra_par";
        try {
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("sifra_par", sifraPar);
            parti_par = (String) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return parti_par;
    }

}
