package hr.abc.psd2.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.abc.psd2.annotation.CdiIntercept;
import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.model.dto.GenericDto;
import hr.abc.psd2.util.Bassx2Constants;
import hr.abc.psd2.util.DateUtil;
import hr.abc.psd2.util.MathFunctions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CdiIntercept
public abstract class GenericDao<E, T extends GenericDto<PK>, PK extends Serializable> extends GenericMinDao<E, T, PK> implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * provjerava postojanje sloga na temelju primarnog ključa
     *
     * @param primaryKey
     * @return true/false
     */
    public Boolean exists(PK primaryKey) {
        // finding name of primary key
        String fieldName = null;
        if (dtoClass() != null) {
            IFilter filter = null;
            for (Method m : dtoClass().getMethods()) {
                // parsing filter
                filter = m.getAnnotation(IFilter.class);
                if (filter != null && m.getName().equals("getSifra")) {
                    fieldName = filter.entityField();
                    break;
                }
            }
        }
        // concating basic query
        if (StringUtils.isNotBlank(fieldName) && primaryKey != null) {
            String basSql = " select " + fieldName + " from " + entityClass().getSimpleName() + " " + StringUtils.uncapitalize(entityClass().getSimpleName()) + " ";
            String sql = basSql.concat(" where ").concat(fieldName).concat(" = ").concat(":primaryKey");
            Query query = getEntityManager().createNativeQuery(sql);
            try {
                query.setParameter("primaryKey", primaryKey);
            } catch (IllegalArgumentException iae) {
                if (StringUtils.isNumeric(primaryKey.toString())) {
                    query.setParameter("primaryKey", Integer.parseInt(primaryKey.toString()));
                } else if (primaryKey.toString().length() == 1) {
                    query.setParameter("primaryKey", primaryKey.toString().trim().charAt(0));
                }
            }

            try {
                query.getSingleResult();
            } catch (NoResultException nre) {
                return false;
            }

        }
        return true;
    }

    /**
     * Rekontrukcija DTO-a preko nefinancijskog loga.
     *
     * Ako se odabere datum, tada se traži promijena na zadani datum, inače se
     * uzima prethodna verzija DTO-a. Ako se odabere tip naloga, gledaju se samo
     * nalozi s tim tipom naloga, inače se uzimaju sve promijene. Ako ne postoji
     * ni jedna promijena tada se vraća trenutni DTO. Ako se dogodi iznimka,
     * vraća se null.
     *
     * @param primaryKey
     * @param date datum promijene - gleda se kako je entitet izgledao nakon
     * poslanog datuma
     * @param tipNalogaPromijene
     * @return
     */
    public T reconstructDto(PK primaryKey, Date date, Integer tipNalogaPromijene) {
        T resultDto = null;
        if (primaryKey != null) {
            // get current entity
            @SuppressWarnings("unchecked")
            E tempEntity = (E) getEntityManager().find(entityClass(), primaryKey);
            if (tempEntity != null) {
                // detacham entity kako se ne bi automatski iz session bean-a mergal u bazu
                getEntityManager().detach(tempEntity);
                // get change from nefin log
                String sqlNefinLog = null;
                if (date != null) {
                //  String datum = "(date '" + DateUtil.addNumberOfDays(date, 1) + "')";
                	date = DateUtil.addNumberOfDays(date, 1);
                    if (tipNalogaPromijene != null) {
                   //     sqlNefinLog = "select pn.field_prn, pn.value_prn from promet_nefin pn, nalog n where n.sifra_nal = pn.nalog_prn and pn.table_prn = :entityClassName and keyva_prn = :primaryKey and n.vrije_nal > " + datum + " and pn.sifra_prn in (select min(pni.sifra_prn) from promet_nefin pni, nalog ni where ni.sifra_nal = pni.nalog_prn and pni.field_prn = pn.field_prn and pni.table_prn = :entityClassName and keyva_prn = :primaryKey and ni.tipna_nal = :tipNalogaPromijene and ni.vrije_nal > " + datum + ") ";
                        sqlNefinLog = "select pn.field_prn, pn.value_prn from promet_nefin pn, nalog n where n.sifra_nal = pn.nalog_prn and pn.table_prn = :entityClassName and keyva_prn = :primaryKey and n.vrije_nal >  :date  and pn.sifra_prn in (select min(pni.sifra_prn) from promet_nefin pni, nalog ni where ni.sifra_nal = pni.nalog_prn and pni.field_prn = pn.field_prn and pni.table_prn = :entityClassName and keyva_prn = :primaryKey and ni.tipna_nal = :tipNalogaPromijene and ni.vrije_nal > :date ) ";
                    } else {
                   //     sqlNefinLog = "select pn.field_prn, pn.value_prn from promet_nefin pn, nalog n where n.sifra_nal = pn.nalog_prn and pn.table_prn = :entityClassName and keyva_prn = :primaryKey and n.vrije_nal > " + datum + " and pn.sifra_prn in (select min(pni.sifra_prn) from promet_nefin pni, nalog ni where ni.sifra_nal = pni.nalog_prn and pni.field_prn = pn.field_prn and pni.table_prn = :entityClassName and keyva_prn = :primaryKey and ni.vrije_nal > " + datum + ") ";
                        sqlNefinLog = "select pn.field_prn, pn.value_prn from promet_nefin pn, nalog n where n.sifra_nal = pn.nalog_prn and pn.table_prn = :entityClassName and keyva_prn = :primaryKey and n.vrije_nal > :date  and pn.sifra_prn in (select min(pni.sifra_prn) from promet_nefin pni, nalog ni where ni.sifra_nal = pni.nalog_prn and pni.field_prn = pn.field_prn and pni.table_prn = :entityClassName and keyva_prn = :primaryKey and ni.vrije_nal > :date  ) ";
                    }
                } else {
                    if (tipNalogaPromijene != null) {
                        sqlNefinLog = "select field_prn, value_prn from promet_nefin where table_prn = :entityClassName and keyva_prn = :primaryKey and nalog_prn in (select max(nalog_prn) from promet_nefin, nalog where sifra_nal = nalog_prn and table_prn = :entityClassName and keyva_prn = :primaryKey and tipna_nal = :tipNalogaPromijene) ";
                    } else {
                        sqlNefinLog = "select field_prn, value_prn from promet_nefin where table_prn = :entityClassName and keyva_prn = :primaryKey and nalog_prn in (select max(nalog_prn) from promet_nefin where table_prn = :entityClassName and keyva_prn = :primaryKey) ";
                    }
                }
                Query queryNefinLog = getEntityManager().createNativeQuery(sqlNefinLog);
                queryNefinLog.setParameter("entityClassName", entityClass().getSimpleName());
                queryNefinLog.setParameter("primaryKey", primaryKey.toString());
                if (date != null) queryNefinLog.setParameter("date", date, TemporalType.DATE);
                if (tipNalogaPromijene != null) {
                    queryNefinLog.setParameter("tipNalogaPromijene", tipNalogaPromijene);
                }
                @SuppressWarnings("unchecked")
                List<Object[]> listOfChangedValues = queryNefinLog.getResultList();
                String methodName = null;
                Method tempMethod = null;
                Field tempFiled = null;
                if (listOfChangedValues != null && !listOfChangedValues.isEmpty()) {
                    for (Object[] c : listOfChangedValues) {
                        try {
                            if (c != null && StringUtils.isNotBlank(c[0].toString())) {
                                methodName = "set" + ((String) c[0]).substring(0, 1).toUpperCase() + ((String) c[0]).substring(1);
                                try {
                                    tempFiled = tempEntity.getClass().getDeclaredField((String) c[0]);
                                } catch (NoSuchFieldException nse) {
                                    tempFiled = tempEntity.getClass().getSuperclass().getDeclaredField((String) c[0]);
                                }
                                if (tempFiled != null) {
                                    try {
                                        tempMethod = tempEntity.getClass().getDeclaredMethod(methodName, tempFiled.getType());
                                    } catch (NoSuchMethodException nse) {
                                        tempMethod = tempEntity.getClass().getSuperclass().getDeclaredMethod(methodName, tempFiled.getType());
                                    }
                                    if (tempMethod != null) {
                                        tempMethod.setAccessible(true);
                                        try {
                                            tempMethod.invoke(tempEntity, resolveValueByType(tempFiled.getType(), (String) c[1]));
                                        } catch (IllegalArgumentException iae) {
                                            if (StringUtils.isNotBlank((String) c[1])) {
                                                String value = ((String) c[1]).substring(((String) c[1]).indexOf("=") + 1, ((String) c[1]).indexOf("]"));
                                                tempMethod.invoke(tempEntity, getEntityManager().getReference(tempFiled.getType(), resolveValueByEnttiyType(tempFiled.getType(), value)));
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                        	log.warn(e.getMessage());
                            tempEntity = null;
                            break;
                        }
                    }
                }
                // form previous DTO
                resultDto = formDTO(tempEntity);
            }
        }
        return resultDto;
    }

    /**
     * Povlači se zadnja promijena iz nefin loga.
     *
     * Prometi nefin loga se brišu, a nalogu se postavlja status 8.
     *
     * @param primaryKey
     * @param tipNalogaPromijene
     * @author Matija Hlapčić
     */
    public void removeLastChangeFromNefinLog(PK primaryKey, Integer tipNalogaPromijene) {
        Integer sifraNefinancijskogNaloga = null;
        // dohvat zadnjeg nefinancijskog naloga
        String sqlNalogNefin = null;
        if (tipNalogaPromijene != null) {
            sqlNalogNefin = "select distinct nalog_prn from promet_nefin where table_prn = :entityClassName and keyva_prn = :primaryKey and nalog_prn in (select max(nalog_prn) from promet_nefin, nalog where sifra_nal = nalog_prn and table_prn = :entityClassName and keyva_prn = :primaryKey and tipna_nal = :tipNalogaPromijene) ";
        } else {
            sqlNalogNefin = "select distinct nalog_prn from promet_nefin where table_prn = :entityClassName and keyva_prn = :primaryKey and nalog_prn in (select max(nalog_prn) from promet_nefin where table_prn = :entityClassName and keyva_prn = :primaryKey) ";
        }
        Query queryNalogNefin = getEntityManager().createNativeQuery(sqlNalogNefin);
        queryNalogNefin.setParameter("entityClassName", entityClass().getSimpleName());
        queryNalogNefin.setParameter("primaryKey", primaryKey.toString().trim());
        if (tipNalogaPromijene != null) {
            queryNalogNefin.setParameter("tipNalogaPromijene", tipNalogaPromijene);
        }
        try {
            sifraNefinancijskogNaloga = MathFunctions.resolveOracleNumberToInteger(queryNalogNefin.getSingleResult());
        } catch (Exception e) {
            log.error(e.getMessage());
            sifraNefinancijskogNaloga = null;
        }
        if (sifraNefinancijskogNaloga != null) {
            // brisanje prometa nefin loga
            String sqlBrisanjePrometa = "delete from promet_nefin where nalog_prn = :sifraNefinancijskogNaloga ";
            Query queryBrisanjePrometa = getEntityManager().createNativeQuery(sqlBrisanjePrometa);
            queryBrisanjePrometa.setParameter("sifraNefinancijskogNaloga", sifraNefinancijskogNaloga);
            queryBrisanjePrometa.executeUpdate();
            // ažuriranje naloga nefin loga
            String sqlUpdateNaloga = "update nalog set statu_nal = :stPovucen where sifra_nal = :sifraNefinancijskogNaloga ";
            Query queryUpdateNaloga = getEntityManager().createNativeQuery(sqlUpdateNaloga);
            queryUpdateNaloga.setParameter("stPovucen", Bassx2Constants.Core.NALOG_STATUNAL_POVUCEN);
            queryUpdateNaloga.setParameter("sifraNefinancijskogNaloga", sifraNefinancijskogNaloga);
            queryUpdateNaloga.executeUpdate();
        }
    }

    /**
     * Razrješavanje vrijednsoti u ovisnosti od tipa polja klase.
     *
     * @param type
     * @param value
     * @return
     * @author Matija Hlapčić
     */
    private Object resolveValueByType(Class<?> type, String value) throws IllegalArgumentException {
        Object result = null;
        if (type != null) {
            if (type.equals(String.class)) {
                result = value;
            } else if (type.equals(Integer.class)) {
                result = Integer.parseInt(value);
            } else if (type.equals(BigDecimal.class)) {
                result = new BigDecimal(value);
            } else if (type.equals(Date.class)) {
                result = DateUtil.getDateFromString(value, DateUtil.DEFAULT_DATE_FORMAT);
            } else if (type.equals(Character.class)) {
                result = value.trim().charAt(0);
            } else if (type.equals(Short.class)) {
                result = new Short(value);
            } else if (type.equals(Short.class)) {
                result = new Long(value);
            } else {
                throw new IllegalArgumentException("Wrong type");
            }
        }
        return result;
    }

    /**
     * Dohvat entiteta, tipa primarnog ključa i razrješavanje vrijednosti u
     * odgovarajućem tipu podatka.
     *
     * @param entityType
     * @param value
     * @author Matija Hlapčić
     */
    private Object resolveValueByEnttiyType(Class<?> entityType, String value) {
        Object result = null;
        if (entityType != null && StringUtils.isNotBlank(value)) {
            try {
                Field field = null;
                Id idField = null;
                for (Field f : entityType.getDeclaredFields()) {
                    idField = f.getAnnotation(Id.class);
                    if (idField != null) {
                        field = f;
                        break;
                    }
                }
                if (field != null) {
                    result = resolveValueByType(field.getType(), value);
                }
            } catch (Exception e) {
                log.warn(e.getMessage());
                result = null;
            }
        }
        return result;
    }

}
