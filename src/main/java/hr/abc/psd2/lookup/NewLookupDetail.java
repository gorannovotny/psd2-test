package hr.abc.psd2.lookup;

import java.io.Serializable;

public class NewLookupDetail<K> implements Serializable {
    private static final long serialVersionUID = 1L;
    private K id;
    private String designation;
    private String description;
    private String extraDesc;
    private String complexDesc;

    NewLookupDetail(K id, String designation, String description, String extraDesc, String complexDesc) {
        this.id = id;
        this.designation = designation;
        this.description = description;
        this.extraDesc = extraDesc;
        this.complexDesc = complexDesc;
    }

    public K getId() {
        return this.id;
    }

    public void setId(K id) {
        this.id = id;
    }

    public String getDesignation() {
        return this.designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtraDesc() {
        return this.extraDesc;
    }

    public void setExtraDesc(String extraDescription) {
        this.extraDesc = extraDescription;
    }

    public String getComplexDesc() { return complexDesc; }

    public void setComplexDesc(String complexDesc) { this.complexDesc = complexDesc; }
}
