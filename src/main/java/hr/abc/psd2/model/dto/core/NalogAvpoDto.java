package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.model.dto.GenericDto;
import hr.abc.psd2.model.dto.core.NalogPlatniDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ivica on 10/12/15.
 */
public class NalogAvpoDto extends GenericDto<Integer> implements Serializable {


    private static final long serialVersionUID = 1L;
    private NalogSepaDto nalogSepa;
    private String valutaNaplateNaknade; // kako bi mogli poslati drugačiju valutu za naplatu naknade od defaultne - IB šalje za sad kune dok se ne složi multivalutna naplata

    // fields -- platni nalog
    public NalogAvpoDto() {

    }

    public NalogAvpoDto(NalogSepaDto nalogSepa) {
        this.nalogSepa = nalogSepa;
    }

    public NalogAvpoDto(NalogPlatniDto np) {
        this.nalogSepa = new NalogSepaDto(np);
    }

    public NalogPlatniDto getNalogPlatniDto() {
        return nalogSepa.getNalogPlatniDto();
    }

    public void setNalogPlatniDto(NalogPlatniDto np) {
        nalogSepa.setNalogPlatniDto(np);
    }


    /*********
     * AVPO dio
     ***********/
    @Override
    @IFilter(entityField="nalogAvpo.sifra_nav")
    public Integer getSifra() {
        return (Integer) super.getSifra();
    }

    /*********
     * Platni nalog dio
     ***********/

    public NalogSepaDto getNalogSepa() {
        if (nalogSepa == null) {
            setNalogSepa(new NalogSepaDto());
        }
        return nalogSepa;
    }

    public void setNalogSepa(NalogSepaDto nalogSepa) {
        this.nalogSepa = nalogSepa;
    }

    /*********
     * SEPA dio
     * filter - getteri koji se koriste kod fomiranja sql-a za dohvat
     ***********/


    @IFilter(entityField = "nalogSepa.rlref_sct")
    public String getRlrefTrn() {
        return getNalogSepa().getRlrefTrn();
    }

    @IFilter(entityField = "nalogSepa.grpid_sct")
    public String getGrpidTrn() {
        return getNalogSepa().getGrpidTrn();
    }

    @IFilter(entityField = "nalogSepa.hsvpp_sct")
    public String getHsvppTrn() {
        return getNalogSepa().getHsvppTrn();
    }

    @IFilter(entityField = "nalogSepa.hsvpo_sct")
    public String getHsvpoTrn() {
        return getNalogSepa().getHsvpoTrn();
    }

    @IFilter(entityField = "nalogSepa.ciklus_sct")
    public String getCiklusTrn() {
        return getNalogSepa().getCiklusTrn();
    }

    /*********
     * Platni nalog dio
     ***********/

    @IFilter(entityField = "nalogPlatni.sifra_trn")
    public Integer getSifraTrn() {
        return getNalogSepa().getSifraTrn();
    }

    @IFilter(entityField = "nalogPlatni.iznos_trn")
    public BigDecimal getIznos() {
        return getNalogSepa().getIznos();
    }

    @IFilter(entityField = "nalogPlatni.valut_trn")
    public String getSifraValute() {
        return getNalogSepa().getSifraValute();
    }

    @IFilter(entityField = "nalogPlatni.valpk_trn")
    public String getSifraValutePokrica() {
        return getNalogSepa().getSifraValutePokrica();
    }

    @IFilter(entityField = "nalogPlatni.ibanz_trn")
    public String getIbanZaduzenja() {
        return getNalogSepa().getIbanZaduzenja();
    }

    @IFilter(entityField = "nalogPlatni.ibano_trn")
    public String getIbanOdobrenja() {
        return getNalogSepa().getIbanOdobrenja();
    }

    @IFilter(entityField = "nalogPlatni.mozad_trn")
    public String getModelZaduzenja() {
        return getNalogSepa().getModelZaduzenja();
    }

    @IFilter(entityField = "nalogPlatni.pozad_trn")
    public String getPozivNaBrojZaduzenja() {
        return getNalogSepa().getPozivNaBrojZaduzenja();
    }

    @IFilter(entityField = "nalogPlatni.moodo_trn")
    public String getModelOdobrenja() {
        return getNalogSepa().getModelOdobrenja();
    }

    @IFilter(entityField = "nalogPlatni.poodo_trn")
    public String getPozivNaBrojOdobrenja() {
        return getNalogSepa().getPozivNaBrojOdobrenja();
    }


    @IFilter(entityField = "nalogPlatni.naciz_trn")
    public String getNacinIzvrsenja() {
        return getNalogSepa().getNacinIzvrsenja();
    }

    @IFilter(entityField = "nalogPlatni.smjer_trn")
    public String getSmjer() {
        return getNalogSepa().getSmjer();
    }

    @IFilter(entityField = "nalogPlatni.gresk_trn")
    public String getGreska() {
        return getNalogSepa().getGreska();
    }

    @IFilter(entityField = "nalogPlatni.sinam_trn")
    public String getSifraNamjene() {
        return getNalogSepa().getSifraNamjene();
    }

    @IFilter(entityField = "nalogPlatni.ktnamTrn")
    public String getKategorijaNamjene() {
        return getNalogSepa().getKategorijaNamjene();
    }

    @IFilter(entityField = "nalogPlatni.sifop_trn")
    public String getSifraOpisaPlacanja() {
        return getNalogSepa().getSifraOpisaPlacanja();
    }

    @IFilter(entityField = "nalogPlatni.medij_trn")
    public String getMedij() {
        return getNalogSepa().getMedij();
    }

    @IFilter(entityField = "nalogPlatni.nazad_trn")
    public String getNazivDebtor() {
        return getNalogSepa().getNazivDebtor();
    }

    @IFilter(entityField = "nalogPlatni.adzad_trn")
    public String getAdresaDebtor() {
        return getNalogSepa().getAdresaDebtor();
    }

    @IFilter(entityField = "nalogPlatni.grzad_trn")
    public String getGradDebtor() {
        return getNalogSepa().getGradDebtor();
    }

    @IFilter(entityField = "nalogPlatni.drzad_trn")
    public String getDrzavaDebtor() {
        return getNalogSepa().getDrzavaDebtor();
    }

    @IFilter(entityField = "nalogPlatni.ulnzd_trn")
    public String getUltimateDebtorNaziv() {
        return getNalogSepa().getUltimateDebtorNaziv();
    }

    @IFilter(entityField = "nalogPlatni.ulizd_trn")
    public String getUltimateDebtorId() {
        return getNalogSepa().getUltimateDebtorId();
    }

    @IFilter(entityField = "nalogPlatni.naodo_trn")
    public String getNazivCreditor() {
        return getNalogSepa().getNazivCreditor();
    }

    @IFilter(entityField = "nalogPlatni.adodo_trn")
    public String getAdresaCreditor() {
        return getNalogSepa().getAdresaCreditor();
    }

    @IFilter(entityField = "nalogPlatni.grodo_trn")
    public String getGradCreditor() {
        return getNalogSepa().getGradCreditor();
    }

    @IFilter(entityField = "nalogPlatni.drodo_trn")
    public String getDrzavaCreditor() {
        return getNalogSepa().getDrzavaCreditor();
    }

    @IFilter(entityField = "nalogPlatni.ulnod_trn")
    public String getUltimateCreditorNaziv() {
        return getNalogSepa().getUltimateCreditorNaziv();
    }

    @IFilter(entityField = "nalogPlatni.uliod_trn")
    public String getUltimateCreditorId() {
        return getNalogSepa().getUltimateCreditorId();
    }

    @IFilter(entityField = "nalogPlatni.bicko_trn")
    public String getSwiftBicNalogodavateljaKorisnika() {
        return getNalogSepa().getSwiftBicNalogodavateljaKorisnika();
    }

    @IFilter(entityField = "nalogPlatni.tropc_trn")
    public String getTrosakInoBanke() {
        return getNalogSepa().getTrosakInoBanke();
    }

    @IFilter(entityField = "nalogPlatni.refer_trn")
    public String getReferenca() {
        return getNalogSepa().getReferenca();
    }

    @IFilter(entityField = "nalogPlatni.preth_trn")
    public Integer getSifraPrethodnogNaloga() {
        return getNalogSepa().getSifraPrethodnogNaloga();
    }

    @IFilter(entityField = "nalogKnjizni.datva_nal")
    public Date getDatumValute() {
        return getNalogSepa().getKnjizniNalog().getDatumValute();
    }

    @IFilter(entityField = "nalogKnjizni.sifra_nal")
    public Integer getSifraKnjiznogNaloga() {
        return getNalogSepa().getKnjizniNalog().getSifra();
    }

    @IFilter(entityField="nalogKnjizni.datkn_nal")
    public Date getDatumKnjizenja() {
        return this.getNalogSepa().getKnjizniNalog().getDatumKnjizenja();
    }

    @IFilter(entityField="nalogKnjizni.vrikn_nal")
    public Date getVrijemeKnjizenja() {
        return this.getNalogSepa().getKnjizniNalog().getVrijemeKnjizenja();
    }

    @IFilter(entityField="nalogKnjizni.prior_nal")
    public Short getPrioritet() {
        return this.getNalogSepa().getKnjizniNalog().getPrioritet();
    }

    @IFilter(entityField="nalog.vrije_nal")
    public Date getVrijemeNastanka() {
        return this.getNalogSepa().getKnjizniNalog().getVrijemeNastanka();
    }

    @IFilter(entityField="nalog.svrha_nal")
    public String getSvrha() {
        return this.getNalogSepa().getKnjizniNalog().getSvrha();
    }

    @IFilter(entityField="nalog.statu_nal")
    public String getStatus() {
        return this.getNalogSepa().getKnjizniNalog().getStatus();
    }

    @IFilter(entityField="likvidator.sifra_lik")
    public Integer getSifraLikvidatora() {
        return this.getNalogSepa().getKnjizniNalog().getSifraLikvidatora();
    }

    @IFilter(entityField="nalog.aplik_nal")
    public Integer getSifraModula() {
        return this.getNalogSepa().getKnjizniNalog().getSifraModula();
    }

    @IFilter(entityField="nalog.pojed_nal")
    public String getSifraPoslovnice() {
        return this.getNalogSepa().getKnjizniNalog().getSifraPoslovnice();
    }

    @IFilter(entityField="nalog.tipna_nal")
    public Integer getSifraTipaNaloga() {
        return this.getNalogSepa().getKnjizniNalog().getSifraTipaNaloga();
    }

    @IFilter(entityField="nalog.stornNal")
    public Integer getSifraNalogaStorna() {
        return this.getNalogSepa().getKnjizniNalog().getSifraNalogaStorna();
    }

    @IFilter(entityField="nalog.stozn_nal")
    public String getOznakaStorno() {
        return this.getNalogSepa().getKnjizniNalog().getOznakaStorno();
    }

    @IFilter(entityField="likvidator.login_lik")
    public String getLoginLikvidatora() {
        return this.getNalogSepa().getKnjizniNalog().getLoginLikvidatora();
    }

    @IFilter(entityField="pojed.naziv_poj")
    public String getNazivPoslovnice() {
        return this.getNalogSepa().getKnjizniNalog().getNazivPoslovnice();
    }

    @IFilter(entityField="modul.naziv_mod")
    public String getNazivModula() {
        return this.getNalogSepa().getKnjizniNalog().getNazivModula();
    }

    @IFilter(entityField="nalog.prena_nal")
    public Integer getSifraNalogaPrethodnika() {
        return this.getNalogSepa().getKnjizniNalog().getSifraNalogaPrethodnika();
    }

	public String getValutaNaplateNaknade() {
		return valutaNaplateNaknade;
	}

	public void setValutaNaplateNaknade(String valutaNaplateNaknade) {
		this.valutaNaplateNaknade = valutaNaplateNaknade;
	}

}
