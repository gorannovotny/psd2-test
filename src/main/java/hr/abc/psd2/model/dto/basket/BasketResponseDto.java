package hr.abc.psd2.model.dto.basket;

import hr.abc.psd2.model.dto.ais.Links;
import hr.abc.psd2.model.dto.error.ErrorInformationDto;
import hr.abc.psd2.model.dto.pis.TppNalogPlatniDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomica on 14-May-19.
 */
public class BasketResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String transactionStatus;
    private String basketId;
    private String psuMessage;
    private ArrayList<String> tppMessages;
    private List<TppNalogPlatniDto> listaTppNaloga;

    private Links _links;

    private Boolean isValid; //ukoiko kod validacije naidemo na grešku --> false
    private ErrorInformationDto errorInformationDto; //Additional Error Information

    public BasketResponseDto() {
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getBasketId() {
        return basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

    public String getPsuMessage() {
        return psuMessage;
    }

    public void setPsuMessage(String psuMessage) {
        this.psuMessage = psuMessage;
    }

    public ArrayList<String> getTppMessages() {
        return tppMessages;
    }

    public void setTppMessages(ArrayList<String> tppMessages) {
        this.tppMessages = tppMessages;
    }

    public Links get_links() {
        return _links;
    }

    public void set_links(Links _links) {
        this._links = _links;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public ErrorInformationDto getErrorInformationDto() {
        return errorInformationDto;
    }

    public void setErrorInformationDto(ErrorInformationDto errorInformationDto) {
        this.errorInformationDto = errorInformationDto;
    }

    public List<TppNalogPlatniDto> getListaTppNaloga() {
        return listaTppNaloga;
    }

    public void setListaTppNaloga(List<TppNalogPlatniDto> listaTppNaloga) {
        this.listaTppNaloga = listaTppNaloga;
    }
}
