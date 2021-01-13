package hr.abc.psd2.model.dto.error;

import java.io.Serializable;
import java.util.List;

import hr.abc.psd2.model.dto.ais.Links;

/**
 * Created by Tomica on 25-Oct-18.
 */

public class ErrorInformationDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<ErrorMessageDto> tppMessages;
    private Links _links;

    public ErrorInformationDto() {
        super();
    }


    public List<ErrorMessageDto> getTppMessages() {
        return tppMessages;
    }

    public void setTppMessages(List<ErrorMessageDto> tppMessages) {
        this.tppMessages = tppMessages;
    }

    public Links get_links() {
        return _links;
    }

    public void set_links(Links _links) {
        this._links = _links;
    }
}
