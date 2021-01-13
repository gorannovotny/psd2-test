package hr.abc.psd2.model;

import org.apache.commons.lang3.EnumUtils;

import hr.abc.psd2.util.Psd2Util;

public enum ConsentStatus {
    RECEIVED("received"),
    REJECTED("rejected"),
    VALID("valid"),
    REVOKED_BY_PSU("revokedByPsu"),
    EXPIRED("expired"),
    TERMINATED_BY_TPP("terminatedByTpp");

    private String value;

    ConsentStatus(String status) {
        this.value = status;
    }

    public String getValue() {
        return value;
    }
    
    
	public static boolean contains(String status) {		
//		return EnumUtils.isValidEnum(ConsentStatus.class, status);
		boolean ret = false;
		for (ConsentStatus item : ConsentStatus.values()) {
			if (item.getValue().equals(status)) {
				ret = true;
				break;
			}
		}
		return ret;
	}
}
