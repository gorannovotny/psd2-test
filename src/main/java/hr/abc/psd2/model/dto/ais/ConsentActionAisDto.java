package hr.abc.psd2.model.dto.ais;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by dKosutar on 21-Nov-18.
 *
 * PSD2 Consent Action ConsentActionAis DTO
 *
 */

public class ConsentActionAisDto implements Serializable {

    private static final long serialVersionUID = 1L;

    //fields
    private Integer sifraAct;
    private String stateAct; //za sada ćemo logirati samo uspješne (bez greške) akcije, ako bude potrebe onda ćemo proširiti na sve; sve akvije logiramo kroz ElasticSearch
    private LocalDateTime dateAct;
    private Integer siconAct;
    private String tppidAct;
    private String xRequestId;
    private String IpAdressPsu;

    //constructor
    public ConsentActionAisDto(){
        super();
    }


    public ConsentActionAisDto(String stateAct, LocalDateTime dateAct, Integer siconAct, String tppidAct, String xRequestId, String IpAdressPsu) {
        this.stateAct = stateAct;
        this.dateAct = dateAct;
        this.siconAct = siconAct;
        this.tppidAct = tppidAct;
        this.xRequestId = xRequestId;
        this.IpAdressPsu = IpAdressPsu;
    }

    //getters and setters


    public Integer getSifraAct() {
        return sifraAct;
    }

    public void setSifraAct(Integer sifraAct) {
        this.sifraAct = sifraAct;
    }

    public String getStateAct() {
        return stateAct;
    }

    public void setStateAct(String stateAct) {
        this.stateAct = stateAct;
    }

    public LocalDateTime getDateAct() {
        return dateAct;
    }

    public void setDateAct(LocalDateTime dateAct) {
        this.dateAct = dateAct;
    }

    public Integer getSiconAct() {
        return siconAct;
    }

    public void setSiconAct(Integer siconAct) {
        this.siconAct = siconAct;
    }

    public String getTppidAct() {
        return tppidAct;
    }

    public void setTppidAct(String tppidAct) {
        this.tppidAct = tppidAct;
    }

    public String getxRequestId() {
        return xRequestId;
    }

    public void setxRequestId(String xRequestId) {
        this.xRequestId = xRequestId;
    }

    public String getIpAdressPsu() {
        return IpAdressPsu;
    }

    public void setIpAdressPsu(String ipAdressPsu) {
        IpAdressPsu = ipAdressPsu;
    }
}
