package hr.abc.psd2.model.dto.pis;

import java.io.Serializable;

/**
 * Created by Tomica on 10-Dec-18.
 */
public class AddressDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String streetName;
    private String buildingNumber;
    private String townName;
    private String postCode;
    private String country;

    public AddressDto() {
    }

    public AddressDto(String streetName, String buildingNumber, String townName, String postCode, String country) {
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.townName = townName;
        this.postCode = postCode;
        this.country = country;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
