package hr.abc.psd2.model.dto.ais;

import java.io.Serializable;
import java.util.List;

/**
 * @author dhorvat
 */
public class AccountTransactionListDto implements Serializable {
    private static final long serialVersionUID = 1L;

    //tu ide iban
    private String account;

    private List<AccountTransactionDto> booked;
    private List<AccountTransactionDto> pending;
    private Links _links;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public List<AccountTransactionDto> getBooked() {
        return booked;
    }

    public void setBooked(List<AccountTransactionDto> booked) {
        this.booked = booked;
    }

    public List<AccountTransactionDto> getPending() {
        return pending;
    }

    public void setPending(List<AccountTransactionDto> pending) {
        this.pending = pending;
    }

    public Links get_links() {
        return _links;
    }

    public void set_links(Links _links) {
        this._links = _links;
    }
}
