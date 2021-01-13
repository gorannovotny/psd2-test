package hr.abc.psd2.model.dto.error;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import hr.abc.psd2.model.dto.ais.Links;

import java.io.Serializable;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ErrorInformationXmlDto implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement(name = "tppMessages")
    private List<ErrorMessageDto> tppMessages;
    private Links _links;

    public ErrorInformationXmlDto() {
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
