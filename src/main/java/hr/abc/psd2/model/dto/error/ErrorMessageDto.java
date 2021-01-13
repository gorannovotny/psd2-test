package hr.abc.psd2.model.dto.error;

import java.io.Serializable;

/**
 * Created by Tomica on 25-Oct-18.
 */
public class ErrorMessageDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private String category = "ERROR";
    private String code;
    private String text;

    public ErrorMessageDto() {
        super();
    }

    public ErrorMessageDto(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
