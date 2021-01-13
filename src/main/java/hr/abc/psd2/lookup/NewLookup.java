package hr.abc.psd2.lookup;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.abc.psd2.util.MathFunctions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class NewLookup<K> implements Serializable {

    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private static final long serialVersionUID = 1L;
    protected static final String TEXT_ALIGN_CENTER = "center";
    protected static final String TEXT_ALIGN_RIGHT = "right";
    private String labelId;
    private String labelDesignation;
    private String labelDescription;
    private String labelExtraDesc;
    private String labelComplexDesc;

    private String sqlId;
    private String sqlDesignation;
    private String sqlDescription;
    private String sqlExtraDesc;
    private String sqlComplexDesc;
    private String sqlFrom;
    private String sqlWhere;

    private LazyDataModel<NewLookupDetail<K>> lookupList;
    private DataSource dataSource;
    private NewLookupDetail<K> selected;

    private K id;
    private String designation;
    private String description;
    private String extraDesc;
    private String complexDesc;

    private boolean caseInSensitive;
    private String globalFilter = "";

    private String textAlignDesignation;
    private String textAlignDescription;
    private String textAlignExtraDesc;
    private String textAlignComplexDesc;

    protected abstract void setUp(String condition);

    public NewLookup(K id, String designation, String description, String extraDesc, String complexDesc) {
        this.selected = new NewLookupDetail<>(id, designation, description, extraDesc, complexDesc);
    }

    public NewLookup(K id, DataSource ds) {
        this(id, null, null, null, null);
        setUp(null);
        this.setDataSource(ds);
        setSelected(fetchById(id));
        kopirajSelection();
    }

    public void onChange(String required, String condition) {
        this.setUp(condition);
        if ("true".equals(required) || StringUtils.isNotBlank(this.getDesignation())) {
            this.selected = fetchByDesignation(this.getDesignation());
            PrimeFaces instance = PrimeFaces.current();
            if (selected.getId() != null) {
                kopirajSelection();
                instance.ajax().addCallbackParam("thereCanBeOnlyOne","true");

            } else {
                instance.ajax().addCallbackParam("thereCanBeOnlyOne","false");
                this.lookupList = createLazyList(this.getDesignation());
            }
        } else {
            this.setId(null);
            this.setDesignation(null);
            this.setDescription(null);
            this.setExtraDesc(null);
            this.setComplexDesc(null);
        }
    }

    public void onClick(String condition) {
        this.setUp(condition);
        this.lookupList = createLazyList("");
    }

    public void setSelected(NewLookupDetail<K> selected) {
        if (selected == null)
            this.selected = new NewLookupDetail<>(null, null, null, null, null);
        else
            this.selected = selected;
    }

    public void kopirajSelection() {
        this.id = this.getSelected().getId();
        this.designation = this.getSelected().getDesignation();
        this.description = this.getSelected().getDescription();
        this.extraDesc = this.getSelected().getExtraDesc();
        this.complexDesc = this.getSelected().getComplexDesc();
        this.globalFilter = "";
    }

    public K getId() {
        return this.id;
    }

    public void setId(K id) {
        this.id = id;
    }

    public String getDesignation() {
        if (this.designation == null && this.id != null) {
            if (dataSource != null) {
                if (StringUtils.isBlank(this.sqlFrom)) this.setUp("");
                this.selected = fetchById(this.id);
                kopirajSelection();
            }
        }
        return this.designation;
    }

    public void setDesignation(String d) {
        this.designation = d;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String d) {
        this.description = d;
    }

    public String getExtraDesc() {
        return extraDesc;
    }

    public void setExtraDesc(String extraDesc) {
        this.extraDesc = extraDesc;
    }

    public String getComplexDesc() { return complexDesc; }

    public void setComplexDesc(String complexDesc) { this.complexDesc = complexDesc; }

    private LazyDataModel<NewLookupDetail<K>> createLazyList(String field) {
//        String match = (isCaseInSensitive() ? field.toUpperCase() : field).concat("%");
        String match = "%" + (isCaseInSensitive() ? field.toUpperCase() : field) + "%";
        log.debug("LazyLoad basic SQL-> {}", basicSql());
        log.debug("LazyLoad lookup SQL-> {}", lookupSql(basicSql()));
        log.debug("LazyLoad count SQL-> {}", countSql(basicSql()));
        log.debug("LazyLoad paging SQL-> {}", pagingSql(lookupSql(basicSql())));

        LazyDataModel<NewLookupDetail<K>> lazyList = new LazyDataModel<NewLookupDetail<K>>() {
            @Override
            public List<NewLookupDetail<K>> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, FilterMeta> filters) {
                List<NewLookupDetail<K>> list = new ArrayList<>();
                String filter;
                Object filterValue = filters.getOrDefault("globalFilter", new FilterMeta("globalFilter", "")).getFilterValue();
                if (filterValue == null)  {
                    filter = "%";
                } else {
                    filter = "%" + ((isCaseInSensitive() ? filterValue.toString().toUpperCase() : filterValue.toString())) + "%";
                }
                String sql = pagingSql(lookupSql(basicSql()));
                try (Connection con = dataSource.getConnection();
                     PreparedStatement ps = con.prepareStatement(sql)
                ) {
                    ps.setString(1, match);
                    ps.setString(2, match);
                    ps.setString(3, match);
                    ps.setString(4, match);
                    ps.setString(5, filter);
                    ps.setString(6, filter);
                    ps.setString(7, filter);
                    ps.setString(8, filter);
                    ps.setInt(9, first);
                    ps.setInt(10, first);
                    ps.setInt(11, pageSize);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        K locId = (K) rs.getObject("id");
                        list.add(new NewLookupDetail<>(locId, rs.getString("designation"), rs.getString("description"), rs.getString("extradesc"), rs.getString("complexdesc")));
                    }
                } catch (Exception e) {
                    LoggerFactory.getLogger(this.getClass()).error("LazyLoad->", e);
                }
                Integer x = 0;
                String countSql = countSql(basicSql());
                try (Connection con = dataSource.getConnection();
                     PreparedStatement ps = con.prepareStatement(countSql)
                ) {
                    ps.setString(1, match);
                    ps.setString(2, match);
                    ps.setString(3, match);
                    ps.setString(4, match);
                    ps.setString(5, filter);
                    ps.setString(6, filter);
                    ps.setString(7, filter);
                    ps.setString(8, filter);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        x = MathFunctions.resolveOracleNumberToInteger(rs.getObject(1));
                    }
                } catch (Exception e) {
                    LoggerFactory.getLogger(this.getClass()).error("LazyLoadCount->", e);
                    x = 0;
                }
                setRowCount(x);
                return list;
            }

            @Override
            public Object getRowKey(NewLookupDetail<K> object) {
                return object.getId();
            }

            @Override
            public NewLookupDetail<K> getRowData(String rowKey) {
                NewLookupDetail<K> retVal = getWrappedData().stream()
                        .filter(a -> a.getId().toString().equals(rowKey))
                        .findAny()
                        .orElse(null);
                return retVal;
            }

        };
        return lazyList;
    }

    protected String basicSql() {
        StringBuilder sql = new StringBuilder("SELECT ");
        sql.append(sqlId).append(" id, ");
        if (isCaseInSensitive()) {
            sql.append("UPPER(").append(sqlDesignation).append(")");
        } else {
            sql.append(sqlDesignation);
        }
        sql.append(" designation, ");
        if (StringUtils.isNotBlank(sqlDescription)) {
            if (isCaseInSensitive()) {
                sql.append("UPPER(").append(sqlDescription).append(")");
            } else {
                sql.append(sqlDescription);
            }
        } else {
            sql.append("''");
        }
        sql.append(" description, ");
        if (StringUtils.isNotBlank(sqlExtraDesc)) {
            if (isCaseInSensitive()) {
                sql.append("UPPER(").append(sqlExtraDesc).append(")");
            } else {
                sql.append(sqlExtraDesc);
            }
        } else {
            sql.append("''");
        }
        sql.append(" extradesc, ");
        if (StringUtils.isNotBlank(sqlComplexDesc)) {
            if (isCaseInSensitive()) {
                sql.append("UPPER(").append(sqlComplexDesc).append(")");
            } else {
                sql.append(sqlComplexDesc);
            }
        } else {
            sql.append("''");
        }
        sql.append(" complexdesc");
        sql.append(" FROM ").append(sqlFrom);
        if (StringUtils.isNotBlank(sqlWhere)) {
            sql.append(" WHERE ").append(sqlWhere);
        }
        return sql.toString();
    }

    private String lookupSql(String sql) {
        StringBuilder lookupSql = new StringBuilder("SELECT * FROM ( ");
        lookupSql.append(sql).append(" ) ");
        lookupSql.append(" WHERE (designation LIKE ? OR description LIKE ? OR extradesc LIKE ? or complexdesc LIKE ?) AND ( ");
        lookupSql.append("designation LIKE ? OR description LIKE ? OR extradesc LIKE ? OR complexdesc LIKE ?) ");
        lookupSql.append(" ORDER BY designation");
        return lookupSql.toString();
    }

    private String countSql(String sql) {
        StringBuilder countSql = new StringBuilder("SELECT count(*) FROM ( ");
        countSql.append(sql).append(" ) ");
        countSql.append(" WHERE (designation LIKE ? OR description LIKE ? OR extradesc LIKE ? OR complexdesc LIKE ? ) AND ( ");
        countSql.append("designation LIKE ? OR description LIKE ? OR extradesc LIKE ? OR complexdesc LIKE ? ) ");
        return countSql.toString();
    }

    private String pagingSql(String sql) {
// Ovak radi u Oracle 12
//        StringBuilder pagingSql = new StringBuilder(sql);
//        pagingSql.append(" OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
        // Ovo je za Oracle <12
        StringBuilder pagingSql = new StringBuilder("SELECT id,designation,description,extradesc, complexdesc FROM (");
        pagingSql.append("select id,designation,description,extradesc,complexdesc, row_number() OVER (ORDER BY id) rn FROM (");
        pagingSql.append(sql);
        pagingSql.append(")) where rn between ? + 1 AND ? + ?");
        return pagingSql.toString();
    }

    public NewLookupDetail<K> fetchById(K locId) {
        NewLookupDetail<K> result = null;
        String sql = basicSql() + (StringUtils.isNotBlank(sqlWhere) ? " and " : " where ") + sqlId + " = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setObject(1, locId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = new NewLookupDetail<>(locId, rs.getString("designation"), rs.getString("description"), rs.getString("extradesc"), rs.getString("complexdesc"));
            }
        } catch (Exception e) {
            log.error("FETCH BY ID ->", e);
        }
        return result;
    }

    public NewLookupDetail<K> fetchByDesignation(String field) {
//        String match = (isCaseInSensitive() ? field.toUpperCase() : field) + "%";
        String match = "%" + (isCaseInSensitive() ? field.toUpperCase() : field) + "%";
        NewLookupDetail<K> result = null;
        String sql;
        if (isCaseInSensitive()) {
            sql = basicSql() + (StringUtils.isNotBlank(sqlWhere) ? " and " : " where ") + "UPPER(" + sqlDesignation + ") LIKE ?" + (StringUtils.isNotBlank(sqlDescription) ? (" or UPPER(" + sqlDescription + ") LIKE ?") : "") + (StringUtils.isNotBlank(sqlExtraDesc) ? (" or UPPER(" + sqlExtraDesc + ") LIKE ?") : "") + (StringUtils.isNotBlank(sqlComplexDesc) ? (" or UPPER(" + sqlComplexDesc + ") LIKE ?") : "");
        } else {
            sql = basicSql() + (StringUtils.isNotBlank(sqlWhere) ? " and " : " where ") + sqlDesignation + " LIKE ?" + (StringUtils.isNotBlank(sqlDescription) ? (" or " + sqlDescription + " LIKE ?") : "") + (StringUtils.isNotBlank(sqlExtraDesc) ? (" or " + sqlExtraDesc + " LIKE ?") : "") + (StringUtils.isNotBlank(sqlComplexDesc) ? (" or " + sqlComplexDesc + " LIKE ?") : "");
        }
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)
        ) {
            int i = 1;
            ps.setObject(i++, match);
            if (StringUtils.isNotBlank(sqlDescription)) ps.setObject(i++, match);
            if (StringUtils.isNotBlank(sqlExtraDesc)) ps.setObject(i++, match);
            if (StringUtils.isNotBlank(sqlComplexDesc)) ps.setObject(i, match);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = new NewLookupDetail<K>((K) rs.getObject("id"), rs.getString("designation"), rs.getString("description"), rs.getString("extradesc"), rs.getString("complexdesc"));
                if ( rs.next()) {
                    result = new NewLookupDetail<>(null, null, null, null, null);
                }
            } else {
                result = new NewLookupDetail<>(null, null, null, null, null);
            }
        } catch (Exception e) {
            log.error("FETCH BY DESIGNATION ->", e);
        }
        return result;
    }

    // labels
    public String getLabelId() { return labelId; }

    public void setLabelId(String labelId) { this.labelId = labelId; }

    public String getLabelDesignation() { return this.labelDesignation; }

    public void setLabelDesignation(String labelDesignation) { this.labelDesignation = labelDesignation; }

    public String getLabelDescription() { return this.labelDescription; }

    public void setLabelDescription(String labelDescription) { this.labelDescription = labelDescription; }

    public String getLabelExtraDesc() { return this.labelExtraDesc; }

    public void setLabelExtraDesc(String labelExtraDesc) { this.labelExtraDesc = labelExtraDesc; }

    public String getLabelComplexDesc() { return labelComplexDesc; }

    public void setLabelComplexDesc(String labelComplexDesc) { this.labelComplexDesc = labelComplexDesc; }

    // sqls
    public String getSqlId() { return this.sqlId; }

    public void setSqlId(String sqlId) { this.sqlId = sqlId; }

    public String getSqlDesignation() { return this.sqlDesignation; }

    public void setSqlDesignation(String sqlDesignation) { this.sqlDesignation = sqlDesignation; }

    public String getSqlDescription() { return this.sqlDescription; }

    public void setSqlDescription(String sqlDescription) { this.sqlDescription = sqlDescription; }

    public String getSqlExtraDesc() { return this.sqlExtraDesc; }

    public void setSqlExtraDesc(String sqlExtraDesc) { this.sqlExtraDesc = sqlExtraDesc; }

    public String getSqlComplexDesc() { return sqlComplexDesc; }

    public void setSqlComplexDesc(String sqlComplexDesc) { this.sqlComplexDesc = sqlComplexDesc; }

    public String getSqlFrom() { return this.sqlFrom; }

    public void setSqlFrom(String sqlFrom) { this.sqlFrom = sqlFrom; }

    public String getSqlWhere() { return this.sqlWhere; }

    public void setSqlWhere(String sqlWhere) { this.sqlWhere = sqlWhere; }

    public LazyDataModel<NewLookupDetail<K>> getLookupList() { return this.lookupList; }

    public void setLookupList(LazyDataModel<NewLookupDetail<K>> lookupList) { this.lookupList = lookupList; }

    public DataSource getDataSource() { return this.dataSource; }

    public void setDataSource(DataSource dataSource) { this.dataSource = dataSource; }

    public NewLookupDetail<K> getSelected() { return this.selected; }

    public boolean isCaseInSensitive() { return caseInSensitive; }

    public void setCaseInSensitive(boolean caseInSensitive) { this.caseInSensitive = caseInSensitive; }

    public String getTextAlignDesignation() { return textAlignDesignation; }

    public void setTextAlignDesignation(String textAlignDesignation) { this.textAlignDesignation = textAlignDesignation; }

    public String getTextAlignDescription() { return textAlignDescription; }

    public void setTextAlignDescription(String textAlignDescription) { this.textAlignDescription = textAlignDescription; }

    public String getTextAlignExtraDesc() { return textAlignExtraDesc; }

    public void setTextAlignExtraDesc(String textAlignExtraDesc) { this.textAlignExtraDesc = textAlignExtraDesc; }

    public String getTextAlignComplexDesc() { return textAlignComplexDesc; }

    public void setTextAlignComplexDesc(String textAlignComplexDesc) { this.textAlignComplexDesc = textAlignComplexDesc; }

    public String getGlobalFilter() { return globalFilter; }

    public void setGlobalFilter(String globalFilter) { this.globalFilter = globalFilter; }
}
