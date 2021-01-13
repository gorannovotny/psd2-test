package hr.abc.psd2.util;

import javax.ws.rs.core.Response;


/**
 class contains HTTP response codes for Account Information Service defined by
 BG XS2A specification
 Created by dKosutar  2019-03-06
 */
public enum AisSpecificHttpErrorCodes implements Response.StatusType {

    /**
     * The access on the account has been exceeding the consented multiplicity per day.
     */
    ACCESS_EXCEEDED(429, "ACCESS_EXCEEDED"),
    CONSENT_INVALID(401, "CONSENT_INVALID"),
    ;
    private final int code;
    private final String reason;
    private final Response.Status.Family family;




    AisSpecificHttpErrorCodes(final int statusCode, final String reasonPhrase) {
        this.code = statusCode;
        this.reason = reasonPhrase;
        this.family = Response.Status.Family.familyOf(statusCode);
    }

    /**
     * Get the class of status code.
     *
     * @return the class of status code.
     */
    @Override
    public Response.Status.Family getFamily() {
        return family;
    }

    /**
     * Get the associated status code.
     *
     * @return the status code.
     */
    @Override
    public int getStatusCode() {
        return code;
    }

    /**
     * Get the reason phrase.
     *
     * @return the reason phrase.
     */
    @Override
    public String getReasonPhrase() {
        return toString();
    }

    /**
     * Get the reason phrase.
     *
     * @return the reason phrase.
     */
    @Override
    public String toString() {
        return reason;
    }

    /**
     * Convert a numerical status code into the corresponding Status.
     *
     * @param statusCode the numerical status code.
     * @return the matching Status or null is no matching Status is defined.
     */

    }


