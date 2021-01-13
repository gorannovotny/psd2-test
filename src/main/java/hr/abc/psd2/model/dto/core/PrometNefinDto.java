package hr.abc.psd2.model.dto.core;

import java.io.Serializable;

public class PrometNefinDto implements Serializable {

	private static final long serialVersionUID = 1L;
	// table name, alias & fields
	public static final String TABLE_NAME = "promet_nefin";
	public static final String TABLE_ALIAS = "PrometNefin";  // isto kao i entity - ne mijenjati i paziti u DB na tip_dodpod gdje se u tabli_tip koristi ovaj alias
	public static final String SIFRA_FIELD = "sifra_prn";
	public static final String TABLE_FIELD = "table_prn";
	public static final String SUPER_FIELD = "super_prn";
	public static final String KEYVA_FIELD = "keyva_prn";
	public static final String FIELD_FIELD = "field_prn";
	public static final String VALUE_FIELD = "value_prn";
	public static final String NEWVL_FIELD = "newvl_prn";
	public static final String OPERA_FIELD = "opera_prn";
	public static final String NALOG_FIELD = "nalog_prn";
	// constants
	public static final String OPERATION_INSERT = "I";
	public static final String OPERATION_UPDATE = "U";
	public static final String OPERATION_DELETE = "D";

	private String tableName;
	private String superName;
	private String keyValue;
	private String fieldName;
	private String fieldValue;
	private String fieldNewValue;
	private String operation;

	public PrometNefinDto(String tableName, String superName, String keyValue, String fieldName, String fieldValue, String fieldNewValue, String operation) {
		this.tableName = tableName;
		this.superName = superName;
		this.keyValue = keyValue;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.fieldNewValue = fieldNewValue;
		this.operation = operation;
	}

	public String getTableName() { return tableName; }

	public String getSuperName() {
		return superName;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public String getFieldName() { return fieldName; }

	public String getFieldValue() {
		return fieldValue;
	}

	public String getFieldNewValue() { return fieldNewValue; }

	public String getOperation() { return operation; }

	public String toString() {
		return tableName + "(" + keyValue +")[" + fieldName + "]=" + fieldValue;
	}
}
