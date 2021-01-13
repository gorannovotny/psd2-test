package hr.abc.psd2.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.IdentifiableType;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.lookup.NewLookup;
import hr.abc.psd2.model.dto.GenericDto;
import hr.abc.psd2.model.dto.core.NalogNefinDto;
import hr.abc.psd2.model.dto.core.PrometNefinDto;
import hr.abc.psd2.util.GenericBassxConstants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class GenericMinDao<E, T extends GenericDto<PK>, PK extends Serializable> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
    private EntityManager entityManager;

    @Resource(lookup = "PrimDataSource")
    private DataSource dataSource;

    /**
     * Returns entity from DTO
     *
     * @param dto
     * @return
     * @author Mata
     */
    protected abstract E formEntity(T dto);

    /**
     * Returns DTO from entity
     *
     * @param entity
     * @return
     * @author Mata
     */
    protected abstract T formDTO(E entity);
    
    /**
     * Entity class for persistence layer.
     *
     * @return
     * @author Mata
     */
    protected abstract Class<?> entityClass();
    
    /**
     * DTO class for persistence layer.
     *
     * @return
     * @author Mata
     */
    protected abstract Class<?> dtoClass();
    
    /**
     * Validation before create.
     *
     * @param dto
     * @throws AbitRuleException
     * @author Matija Hlapčić
     */
    protected void validateBeforeCreate(T dto) throws AbitRuleException {
        // specific validation before create
    }

    /**
     * Validation before edit.
     *
     * @param dto
     * @throws AbitRuleException
     * @author Matija Hlapčić
     */
    protected void validateBeforeEdit(T dto) throws AbitRuleException {
        // specific validation before create
    }

    /**
     * Validation before delete.
     *
     * @param primaryKey
     * @throws AbitRuleException
     * @author Matija Hlapčić
     */
    protected void validateBeforeDelete(PK primaryKey) throws AbitRuleException {
        // specific validation before create
    }
    
    /**
     * Create new entity
     * alter table ib_zahtjev drop constraint ibkor_zak
     * @param dto
     * @return T
     * @throws AbitRuleException
     * @author Mata
     */
    public T create(T dto) throws AbitRuleException {
        try {
            validateBeforeCreate(dto);
            E entity = formEntity(dto);
            getEntityManager().persist(entity);
            getEntityManager().flush();
            return formDTO(entity);
        } catch (AbitRuleException arex) {
            log.info(arex.getMessage(), arex);
            throw arex;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
    
    /**
     * Edit existing entity with support for native sql
     *
     * @param dto
     * @throws AbitRuleException
     * @author Mata
     */
    public T edit(T dto) throws AbitRuleException {
        validateBeforeEdit(dto);
        E entity = formEntity(dto);
        getEntityManager().merge(entity);
        getEntityManager().flush();
        return findByPrimaryKey(dto.getSifra()); // refresh version-a na entitetu
    }
    
    /**
     * Create new entity add change log
     *
     * @param dto
     * @param log
     * @return T
     * @throws AbitRuleException
     * @author Mata
     */
    public T create(T dto, NalogNefinDto log) throws AbitRuleException {
        boolean logInside = false;
        if (dto.getNalogNefin() != null) {
            logInside = true;
            log = dto.getNalogNefin();
        }
        T dto1 = create(dto);
        E entity = formEntity(dto1);
        // Ako unutar dto postoji nalogNefin - on je jači
        log = logCreate(entity, log);
        if (logInside) {
            dto1.setNalogNefin(log);
        }
        return dto1;
    }

    /**
     * Edit existing entity and add change log
     *
     * @param dto
     * @param log
     * @throws AbitRuleException
     * @author Goran
     */
    public T edit(T dto, NalogNefinDto log) throws AbitRuleException {
        E entity = formEntity(dto);
        // Ako unutar dto postoji nalogNefin - on je jači
        boolean logInside = false;
        if (dto.getNalogNefin() != null) {
            logInside = true;
            log = dto.getNalogNefin();
        }
        log = logEdit(entity, log);
        dto = edit(dto);
        if (logInside) {
            dto.setNalogNefin(log);
        }
        return dto;
    }
    
    /**
     * Delete existing entity
     *
     * @param primaryKey
     * @author Mata
     */
    public void delete(PK primaryKey) throws AbitRuleException {
        validateBeforeDelete(primaryKey);
        Object tempEntity = getEntityManager().find(entityClass(), primaryKey);
        getEntityManager().remove(tempEntity);
    }
    
    /**
     * Delete existing entity with logging
     *
     * @param primaryKey
     * @param log
     * @return log (Vraća log jer nema dto u koji bi stavio log)
     * @author Goran
     */
    public NalogNefinDto delete(PK primaryKey, NalogNefinDto log) throws AbitRuleException {
        @SuppressWarnings("unchecked")
        E entity = (E) getEntityManager().find(entityClass(), primaryKey);
        log = logDelete(entity, log);
        this.delete(primaryKey);
        return log;
    }

    /**
     * Logiranje delete u bazi. Logiraju se sva polja
     *
     * @param after Entity koji se briše
     * @param log nefinancijski nalog u koji se sprema promjena
     * @author Goran
     */
    protected NalogNefinDto logDelete(Object after, NalogNefinDto nlog) {
        try {
            // nađi stari entitet
            Object key = getEntityManager().getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(after);
            Object before = getEntityManager().find(after.getClass(), key);
            // učitaj shemu entiteta
            EntityType<?> shema = getEntityManager().getMetamodel().entity(after.getClass());
            // rekurzivno hodaj po poljima u cijeloj hijerarhiji i dodaji u log
            nlog = walkAttributes(shema.getJavaType().getSimpleName(), resolveFieldValueToString(key), shema, before, null, PrometNefinDto.OPERATION_DELETE, nlog);
        } catch (Exception e) {
            log.error("LOGDELETE", e);
        }
        return nlog;
    }

    /**
     * Method forms basic SQL which can be overrided because of lazy loading.
     *
     * @param filterDto optional; filter basic SQL list with some conditions
     * @return
     * @author Matija Hlapčić
     */
    public String getBasicSql(T filterDto) {
        return "select " + StringUtils.uncapitalize(entityClass().getSimpleName()) + " from " + entityClass().getSimpleName() + " " + StringUtils.uncapitalize(entityClass().getSimpleName()) + " ";
    }

    /**
     * <p>
     * Method returns default "order by" SQL clause which can be overrided
     * </p>
     *
     * @return order by sql clause
     * @author Kresimir krizanic
     */
    public String getDefaultOrderBySql() {
        return " ";
    }
    
    /**
     * Returns list of entities.
     *
     * @return
     * @author Mata
     */
    public List<T> findAll() {
        log.info(getBasicSql(null));
        return findAll(null);
    }
    
    /**
     * Returns list of entities.
     *
     * @param filterDto
     * @return
     * @author Mata
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll(T filterDto) {
        String sql = getBasicSql(filterDto);
        if (StringUtils.isNotBlank(sql)) {
            sql = sql.concat(getDefaultOrderBySql());
        }
        List<E> listEntity = getEntityManager().createNativeQuery(sql).getResultList();
        return formSimpleListDto(listEntity);
    }
    
    /**
     * Returns list of entities. Konvertira listu entiteta u listu Dto
     *
     * @return
     * @author Ivica
     */
    public List<T> formListDto(List<E> listEntity) {
        List<T> listDto = new ArrayList<>();
        if (listEntity != null && !listEntity.isEmpty()) {
            for (E e : listEntity) {
                listDto.add(formDTO(e));
            }
        }
        return listDto;
    }
    
    /**
     * Returns minimal DTO from entity
     *
     * @param entity
     * @return
     * @author Mata
     */
    protected T formSimpleDTO(E entity) {
        return formDTO(entity);
    }
    
    /**
     * Returns simple and minimal list of entities.
     *
     * By default used in findAll method.
     *
     * @return
     * @author Matija Hlapčić
     */
    public List<T> formSimpleListDto(List<E> listEntity) {
        List<T> listDto = new ArrayList<>();
        if (listEntity != null && !listEntity.isEmpty()) {
            for (E e : listEntity) {
                listDto.add(formSimpleDTO(e));
            }
        }
        return listDto;
    }

    /**
     * Find entity by primary key and fill full DTO.
     *
     * @param primaryKey
     * @return
     * @author Mata
     */
    public T findByPrimaryKey(PK primaryKey) {
        return formDTO(findEntityByPrimaryKey(primaryKey));
    }
    
    @SuppressWarnings("unchecked")
    protected E findEntityByPrimaryKey(PK primaryKey) {
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
            String sql = (getBasicSql(null).toLowerCase().contains("where") ? getBasicSql(null).concat(" and ") : getBasicSql(null).concat(" where ")).concat(fieldName).concat(" = ").concat(":primaryKey");
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
            return (E) query.getSingleResult();
        }
        return null;
    }
    
    /**
     * Build search query and filter list of enteties. Returns list of
     * corresponding DTO's.
     *
     * @param filterDto
     * @return
     * @author Matija Hlapčić
     */
    public List<T> filterList(T filterDto) {
        Query query = filter(filterDto);
        @SuppressWarnings("unchecked")
        List<E> listEntity = query.getResultList();
        return formSimpleListDto(listEntity);
    }
    
    /**
     * Find entity by primary key and fill simple DTO.
     *
     * @param primaryKey
     * @return
     * @author Matija Hlapčić
     */
    public T findByPrimaryKeySimple(PK primaryKey) {
        return formSimpleDTO(findEntityByPrimaryKey(primaryKey));
    }

    /**
     * Build search query and filter list of entities. Returns list of
     * corresponding DTO's.
     *
     * @param filterDto
     * @return
     * @author Matija Hlapčić
     */
    protected Query filter(T filterDto) {
        return filter(filterDto, null);
    }

    /**
     * Build search query and filter list of entities. Returns list of
     * corresponding DTO's.
     *
     * @param filterDto
     * @param customSql
     * @return Query
     * @author Matija Hlapčić
     */
    protected Query filter(T filterDto, String customSql) {
        return this.filter(filterDto, customSql, null);
    }
    
    /**
     * Build search query and filter list of entities. Returns list of
     * corresponding DTO's.
     *
     * @param filterDto
     * @param customSelectSql
     * @param extraConditionSql
     * @return Query
     */
    protected Query filter(T filterDto, String customSelectSql, String extraConditionSql) {
    	return filter(filterDto, customSelectSql, extraConditionSql, true);
    }

    /**
     * Build search query and filter list of entities. Returns list of
     * corresponding DTO's.
     *
     * @param filterDto
     * @param customSelectSql
     * @param extraConditionSql
     * @param existFilterConstraint
     * @return Query
     */
    protected Query filter(T filterDto, String customSelectSql, String extraConditionSql, boolean existFilterConstraint) {
        // basic sql query
        StringBuffer sql = new StringBuffer(StringUtils.isNotBlank(customSelectSql) ? customSelectSql : getBasicSql(filterDto));
        if (!sql.toString().toLowerCase().contains("where")) {
            sql.append(" where 1 = 1 ");
        }
        sql.append(StringUtils.isNotBlank(extraConditionSql) ? extraConditionSql : "");

        String sqlBeforeFilter = sql.toString();

        // map of named parameters
        Map<String, Object> mapOfNamedParameters = new HashMap<>();
        // building where condition
        if (filterDto != null) {
            // fields
            for (Field f : filterDto.getClass().getDeclaredFields()) {
                parseFilterFromField(f, filterDto, sql, mapOfNamedParameters); // parsing filter
            }
            // methods
            for (Method m : filterDto.getClass().getMethods()) {
                parseFilterFromMethod(m, filterDto, sql, mapOfNamedParameters); // parsing filter
            }
            // parsing from super classes
            if (filterDto.getClass().getSuperclass() != null && !filterDto.getClass().getSuperclass().getCanonicalName().equals(GenericDto.class.getCanonicalName())) {
                for (Field f : filterDto.getClass().getSuperclass().getDeclaredFields()) {
                    parseFilterFromField(f, filterDto, sql, mapOfNamedParameters); // parsing filter
                }
                if (filterDto.getClass().getSuperclass().getSuperclass() != null && !filterDto.getClass().getSuperclass().getSuperclass().getCanonicalName().equals(GenericDto.class.getCanonicalName()))
                {
                    for (Field f : filterDto.getClass().getSuperclass().getSuperclass().getDeclaredFields()) {
                        parseFilterFromField(f, filterDto, sql, mapOfNamedParameters); // parsing filter
                    }
                }
            }
        }

        // ako je upit nakon filtra isti početnom - diže se flag prvog zvanja
        boolean isNoFilter = false;
        if (existFilterConstraint && sqlBeforeFilter.equals(sql.toString())) {
        	isNoFilter = true;
        }

        // default order by
        if (StringUtils.isNotBlank(sql)) {
            sql.append(" ").append(getDefaultOrderBySql());
        }

        // form query
        String sqlText = sql.toString();
        if (isNoFilter) sqlText = sqlText.replaceFirst("(?i)select", "select first " + GenericBassxConstants.CoreHub.ROWS_LIMIT_DEFAULT);
        Query query = getEntityManager().createNativeQuery(sqlText);
        // set parameters
        if (!mapOfNamedParameters.isEmpty()) {
            for (Map.Entry<String, Object> m : mapOfNamedParameters.entrySet()) {
                if (m != null && StringUtils.isNotBlank(m.getKey())) {
                    // date field
                    if (m.getKey().endsWith("_date")) {
                        query.setParameter(m.getKey(), (Date) m.getValue(), TemporalType.DATE);
                    } else {
                        query.setParameter(m.getKey(), m.getValue());
                    }
                }
            }
        }
        return query;
    }
    
    /**
     * Forming SQL query condition.
     *
     * @param fieldName
     * @param fieldValue
     * @param mapOfNamedParameters
     * @return
     * @author Matija Hlapčić
     */
    private String resolveCondition(String fieldName, Object fieldValue, Map<String, Object> mapOfNamedParameters) {
        StringBuffer condition = new StringBuffer("");
        if (StringUtils.isNotBlank(fieldName) && fieldValue != null && StringUtils.isNotBlank(fieldValue.toString())) {
            if (fieldValue instanceof String) {
                condition.append(" and upper(to_char(").append(fieldName).append(")) ");
                if (fieldValue.toString().equals("=")) {
                    condition.append(" is null ");
                } else if (fieldValue.toString().equals("!=")) {
                    condition.append(" is not null ");
                } else if (fieldValue.toString().startsWith(">")) {
                    condition.append(" > :").append(fieldName.replaceAll("\\.", ""));
                    mapOfNamedParameters.put(fieldName.replaceAll("\\.", ""), fieldValue.toString().replace(">", ""));
                } else if (fieldValue.toString().startsWith("<")) {
                    condition.append(" < :").append(fieldName.replaceAll("\\.", ""));
                    mapOfNamedParameters.put(fieldName.replaceAll("\\.", ""), fieldValue.toString().replace("<", ""));
                } else if (fieldValue.toString().contains("..")) {
                    condition.append(" between :").append(fieldName.replaceAll("\\.", "")).append("_from").append(" and :").append(fieldName.replaceAll("\\.", "")).append("_to");
                    mapOfNamedParameters.put(fieldName.replaceAll("\\.", "") + "_from", fieldValue.toString().split("\\.\\.")[0]);
                    mapOfNamedParameters.put(fieldName.replaceAll("\\.", "") + "_to", fieldValue.toString().split("\\.\\.")[1]);
                } else if (fieldValue.toString().contains("!=")) {
                    condition.append(" != :").append(fieldName.replaceAll("\\.", ""));
                    mapOfNamedParameters.put(fieldName.replaceAll("\\.", ""), fieldValue.toString().replaceAll("!=", ""));
                } else {
                    if (fieldValue.toString().contains("*")) {
                        condition.append(" like :").append(fieldName.replaceAll("\\.", ""));
                        mapOfNamedParameters.put(fieldName.replaceAll("\\.", ""), fieldValue.toString().toUpperCase().replaceAll("\\*", "%"));
                    } else {
                        condition.append(" = :").append(fieldName.replaceAll("\\.", ""));
                        mapOfNamedParameters.put(fieldName.replaceAll("\\.", ""), fieldValue.toString().toUpperCase());
                    }
                }
            } else if (fieldValue instanceof Integer) {
                condition.append(" and ").append(fieldName).append(" = :").append(fieldName.replaceAll("\\.", ""));
                mapOfNamedParameters.put(fieldName.replaceAll("\\.", ""), ((Integer) fieldValue).intValue());
            } else if (fieldValue instanceof Short) {
                condition.append(" and ").append(fieldName).append(" = :").append(fieldName.replaceAll("\\.", ""));
                mapOfNamedParameters.put(fieldName.replaceAll("\\.", ""), fieldValue);
            } else if (fieldValue instanceof Long) {
                condition.append(" and ").append(fieldName).append(" = :").append(fieldName.replaceAll("\\.", ""));
                mapOfNamedParameters.put(fieldName.replaceAll("\\.", ""), fieldValue);
            } else if (fieldValue instanceof Date) {
                condition.append(" and ").append(fieldName).append(" = :").append(fieldName.replaceAll("\\.", "")).append("_date");
                mapOfNamedParameters.put(fieldName.replaceAll("\\.", "") + "_date", fieldValue);
            } else if (fieldValue instanceof BigDecimal) {
                condition.append(" and ").append(fieldName).append(" = :").append(fieldName.replaceAll("\\.", ""));
                mapOfNamedParameters.put(fieldName.replaceAll("\\.", ""), ((BigDecimal) fieldValue).setScale(8, RoundingMode.HALF_UP));
            } else {
                condition.append(" and ").append(fieldName).append(" = :").append(fieldName.replaceAll("\\.", ""));
                mapOfNamedParameters.put(fieldName.replaceAll("\\.", ""), fieldValue.toString());
            }
        }
        return condition.toString();
    }
    
    /**
     * Returns entity manager.
     *
     * @return
     * @author Mata
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    /**
     * Parsing search filter from DTO field.
     *
     * @param f filter object field
     * @param filterDto filter object
     * @param sql preperaed statement
     * @param mapOfNamedParameters
     * @author Matija Hlapčić
     */
    private void parseFilterFromField(Field f, T filterDto, StringBuffer sql, Map<String, Object> mapOfNamedParameters) {
        IFilter filter = f.getAnnotation(IFilter.class);
        if (filter != null) {
            Object fieldValue = null;
            String fieldName = filter.entityField();
            try {
                Method getterMethod = filterDto.getClass().getMethod("get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1));
                fieldValue = getterMethod != null ? getterMethod.invoke(filterDto) : null;
                if (fieldValue instanceof NewLookup) {
                    fieldValue = ((NewLookup) fieldValue).getId();
                }
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NullPointerException e) {
                log.info(e.getMessage());
            }
            // building condition
            sql.append(resolveCondition(fieldName, fieldValue, mapOfNamedParameters));
        }
    }

    /**
     * Parsing search filter from DTO method.
     *
     * @param m filter object method
     * @param filterDto filter object
     * @param sql preperaed statement
     * @param mapOfNamedParameters
     * @author Matija Hlapčić
     */
    private void parseFilterFromMethod(Method m, T filterDto, StringBuffer sql, Map<String, Object> mapOfNamedParameters) {
        IFilter filter = m.getAnnotation(IFilter.class);
        if (filter != null && m.getName().startsWith("get")) {
            String fieldName = filter.entityField();
            Object fieldValue = null;
            try {
                fieldValue = m != null ? m.invoke(filterDto) : null;
                if (fieldValue instanceof NewLookup) {
                    fieldValue = ((NewLookup) fieldValue).getId();
                }
            } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NullPointerException e) {
                log.info(e.getMessage());
            }
            // building condition
            sql.append(resolveCondition(fieldName, fieldValue, mapOfNamedParameters));
        }
    }
    
    /**
     * Logiranje inserta u bazu. Logira se samo ključ
     *
     * @param after entity koji se insertira
     * @param log nefinancijski nalog u koji se sprema promjena
     * @author Goran
     */
    protected NalogNefinDto logCreate(Object after, NalogNefinDto log) {
        Object key = getEntityManager().getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(after);
        log.addPrometNefin(new PrometNefinDto(after.getClass().getSimpleName(), after.getClass().getSimpleName(), resolveFieldValueToString(key), null, null, null, PrometNefinDto.OPERATION_INSERT));
        return log;
    }

    /**
     * Logiranje Update u bazi.
     *
     * @param after Entity koji se insertira
     * @param log nefinancijski nalog u koji se sprema promjena
     * @author Goran
     */
    protected NalogNefinDto logEdit(Object after, NalogNefinDto nlog) {
        try {
            // nađi stari entitet
            Object key = getEntityManager().getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(after);
            Object before = getEntityManager().find(after.getClass(), key);
            // učitaj shemu entiteta
            EntityType<?> shema = getEntityManager().getMetamodel().entity(after.getClass());
            // rekurzivno hodaj po poljima u cijeloj hijerarhiji i dodaji u log
            nlog = walkAttributes(shema.getJavaType().getSimpleName(), resolveFieldValueToString(key), shema, before, after, PrometNefinDto.OPERATION_UPDATE, nlog);
        } catch (Exception e) {
            log.error("LOGEDIT", e);
        }
        return nlog;
    }
    
    /**
     * "Serijalizacija" sadržaja polja u String
     *
     * @author Goran
     */
    private String resolveFieldValueToString(Object o) {
        // ovo bi možda trebalo u neku util klasu
        Object ret = o;
// TODO Hibernate dependent
//		if (o instanceof HibernateProxy) {
//			HibernateProxy proxy = (HibernateProxy) o;
//			ret = proxy.getHibernateLazyInitializer().getIdentifier();
//		}
        if (o != null && o.getClass().isAnnotationPresent(Entity.class)) {
            ret = getEntityManager().getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(o);
        }
        if (o != null && o instanceof Date) {
            ret = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(o);
        }
        return ret == null ? null : ret.toString().trim();
    }
    
    /**
     * rekurzivna funkcija za češljanje svih polja entita u svrhu logiranja
     *
     * @author Goran
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private NalogNefinDto walkAttributes(String tablica, String kljuc, IdentifiableType mt, Object before, Object after, String operation, NalogNefinDto log) throws Exception {
        if (mt.getSupertype() != null) {
            log = walkAttributes(tablica, kljuc, mt.getSupertype(), before, after, operation, log);
        }
        Set<Attribute> polja = mt.getDeclaredAttributes();
        for (Attribute a : polja) {
            if (mt.hasVersionAttribute() && mt.getVersion(Integer.class).equals(a)) {
                continue;
            }
            Field newField = mt.getJavaType().getDeclaredField(a.getName());
            Field oldField = mt.getJavaType().getDeclaredField(a.getName());
            newField.setAccessible(true);
            oldField.setAccessible(true);
            Object oldFieldValue = oldField.get(before);
            String stara = resolveFieldValueToString(oldFieldValue);
            if (after != null) {
                Object newFieldValue = newField.get(after);
                String nova = resolveFieldValueToString(newFieldValue);
                if (StringUtils.isEmpty(stara) && StringUtils.isNotEmpty(nova)) {
                    log.addPrometNefin(new PrometNefinDto(tablica, mt.getJavaType().getSimpleName(), kljuc, a.getName(), stara, nova, operation));
                } else if (StringUtils.isNotEmpty(stara) && StringUtils.isEmpty(nova)) {
                    log.addPrometNefin(new PrometNefinDto(tablica, mt.getJavaType().getSimpleName(), kljuc, a.getName(), stara, nova, operation));
                } else if (StringUtils.isNotEmpty(stara) && StringUtils.isNotEmpty(nova) && !stara.equals(nova)) {
                    log.addPrometNefin(new PrometNefinDto(tablica, mt.getJavaType().getSimpleName(), kljuc, a.getName(), stara, nova, operation));
                }
            } else {
                log.addPrometNefin(new PrometNefinDto(tablica, mt.getJavaType().getSimpleName(), kljuc, a.getName(), stara, null, operation));
            }
        }
        return log;
    }

    public DataSource getDataSource() { return dataSource; }

    public void setDataSource(DataSource dataSource) { this.dataSource = dataSource; }
}
