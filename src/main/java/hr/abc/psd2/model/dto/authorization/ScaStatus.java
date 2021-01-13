package hr.abc.psd2.model.dto.authorization;

/**
 * Created by Tomica on 10-Jan-19.
 */
public enum ScaStatus {

    /**
     * An authorisation or cancellation-authorisation resource has been created
     * successfully.
     */
    RECEIVED("received"),
    /**
     * The PSU related to the authorisation or cancellation-authorisation resource has
     * been identified.
     */
    PSUIDENTIFIED("psuIdentified"),
    /**
     * The PSU related to the authorisation or cancellation-authorisation resource
     * has been identified and authenticated e.g. by a password or by an access token.
     */
    PSUAUTHENTICATED("psuAuthenticated"),
    /**
     * The PSU/TPP has selected the related SCA routine.
     * If the SCA method is chosen implicitly since only one SCA method is available,
     * then this is the first status to be reported instead of {@link #RECEIVED}
     */
    SCAMETHODSELECTED("scaMethodSelected"),
    /**
     * The addressed SCA routine has been started.
     */
    STARTED("started"),
    /**
     * The SCA routine has been finalised successfully.
     */
    FINALISED("finalised"),
    /**
     * The SCA routine failed.
     */
    FAILED("failed"),
    /**
     * SCA was exempted for the related transaction,
     * the related authorisation is successful.
     */
    EXEMPTED("exempted");

    private String value;

    ScaStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ScaStatus getByName(String name) {
        for(ScaStatus e : values()) {
            if(e.value.equals(name)) return e;
        }
        return null;
    }
}
