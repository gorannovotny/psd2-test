package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.model.dto.GenericDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO za zadavanje i dohvat povlaštenog tečaja izdanog od strane riznice.
 * 
 * @author Matija Hlapčić
 */
public class PovlasteniTecajDto extends GenericDto<Integer> implements Serializable {

	// fields
	private static final long serialVersionUID = 1L;
	private Integer sifraKomitenta;
	private String oibKomitenta, nazivKomitenta, sifraValute, opisValute, status, tipTecaja;
	private Date startTime, endTime, date;
	private BigDecimal tecaj, iznosTransakcije;

	// constructors
	public PovlasteniTecajDto() {
		super();
	}
	
	public PovlasteniTecajDto(boolean isFirstCall) {
		super(isFirstCall);
	}

	public PovlasteniTecajDto(Integer sifra, Integer sifraKomitenta, String oibKomitenta, String nazivKomitenta, String sifraValute, String opisValute, Date startTime, Date endTime, BigDecimal tecaj, BigDecimal iznosTransakcije, String status, String tipTecaja) {
		super(sifra);
		this.sifraKomitenta = sifraKomitenta;
		this.oibKomitenta = oibKomitenta;
		this.nazivKomitenta = nazivKomitenta;
		this.sifraValute = sifraValute;
		this.opisValute = opisValute;
		this.startTime = startTime;
		this.endTime = endTime;
		this.tecaj = tecaj;
		this.iznosTransakcije = iznosTransakcije;
		this.status = status;
		this.tipTecaja = tipTecaja;
	}

	public PovlasteniTecajDto(PovlasteniTecajWsDto povlasteniTecajWsDto) {
		super();
		this.sifraKomitenta = povlasteniTecajWsDto.getCustomer_id();
		this.sifraValute = povlasteniTecajWsDto.getCurrency_code();
		this.startTime = povlasteniTecajWsDto.getValid_from();
		this.endTime = povlasteniTecajWsDto.getValid_to();
		this.tecaj = povlasteniTecajWsDto.getRate();
		this.iznosTransakcije = povlasteniTecajWsDto.getAmount();
		this.tipTecaja = povlasteniTecajWsDto.getRate_type();
	}

	// getters & setters
	@Override
	@IFilter(entityField = "povlasteniTecaj.sifra_ptc")
	public Integer getSifra() {
		return super.getSifra();
	}
	
	@IFilter(entityField = "komitent.sifra_kom")
	public Integer getSifraKomitenta() {
		return sifraKomitenta;
	}

	public void setSifraKomitenta(Integer sifraKomitenta) {
		this.sifraKomitenta = sifraKomitenta;
	}

	@IFilter(entityField = "komitent.oib_kom")
	public String getOibKomitenta() {
		return oibKomitenta;
	}

	public void setOibKomitenta(String oibKomitenta) {
		this.oibKomitenta = oibKomitenta;
	}

	@IFilter(entityField = "komitent.naziv_kom")
	public String getNazivKomitenta() {
		return nazivKomitenta;
	}

	public void setNazivKomitenta(String nazivKomitenta) {
		this.nazivKomitenta = nazivKomitenta;
	}

	@IFilter(entityField = "valuta.sifra_val")
	public String getSifraValute() {
		return sifraValute;
	}

	public void setSifraValute(String sifraValute) {
		this.sifraValute = sifraValute;
	}

	@IFilter(entityField = "valuta.naziv_val")
	public String getOpisValute() {
		return opisValute;
	}

	public void setOpisValute(String opisValute) {
		this.opisValute = opisValute;
	}

	@IFilter(entityField = "povlasteniTecaj.start_ptc")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@IFilter(entityField = "povlasteniTecaj.endtm_ptc")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@IFilter(entityField = "povlasteniTecaj.tecaj_ptc")
	public BigDecimal getTecaj() {
		return tecaj;
	}

	public void setTecaj(BigDecimal tecaj) {
		this.tecaj = tecaj;
	}

	@IFilter(entityField = "povlasteniTecaj.iznos_ptc")
	public BigDecimal getIznosTransakcije() {
		return iznosTransakcije;
	}

	public void setIznosTransakcije(BigDecimal iznosTransakcije) {
		this.iznosTransakcije = iznosTransakcije;
	}

	@IFilter(entityField = "povlasteniTecaj.statu_ptc")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@IFilter(entityField = "povlasteniTecaj.tiptc_ptc")
	public String getTipTecaja() {
		return tipTecaja;
	}

	public void setTipTecaja(String tipTecaja) {
		this.tipTecaja = tipTecaja;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
