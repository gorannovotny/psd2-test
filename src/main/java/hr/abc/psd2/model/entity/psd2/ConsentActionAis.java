package hr.abc.psd2.model.entity.psd2;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "psd2_consent_action_ais")
public class ConsentActionAis {

    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_act", nullable = false)
    private Integer idAct;  //action id

    @Column(name = "state_act", nullable = false, length = 25)
    private String stateAct;    //state consent action

    @Column(name = "date_act", nullable = false)
    private LocalDateTime dateAct;       //date consent action

    @Column(name = "conid_act", nullable = false, length = 40)
    private String conidAct;    //id consent action

    @Column(name = "tpp_id", nullable = false, length = 40)
    private String tppId;    //TPP id

    //constructor
    public ConsentActionAis(){
        super();
    }

    //getter and setters

    public Integer getIdAct() {
        return idAct;
    }

    public void setIdAct(Integer idAct) {
        this.idAct = idAct;
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

    public String getConidAct() {
        return conidAct;
    }

    public void setConidAct(String conidAct) {
        this.conidAct = conidAct;
    }

    public String getTppId() {
        return tppId;
    }

    public void setTppId(String tppId) {
        this.tppId = tppId;
    }
}
