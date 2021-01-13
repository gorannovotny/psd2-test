package hr.abc.psd2.model.dto.core;

import org.apache.commons.lang3.StringUtils;

import hr.abc.psd2.model.dto.GenericDto;

import java.io.Serializable;

/**
 * Data transfer object za entitet devizni nalog SWIFT koji proširuje devizni nalog za polja iz SWIFT poruke.
 * 
 * @author Matija Hlapčić
 */
public class NalogDevizniSwiftDto extends GenericDto<Integer> implements Serializable {

	// fields
	private static final long serialVersionUID = 1L;
	private String posrednik56A; // Korespodent - posrednik 56A (stari intcoNal)
	private String racunKorespodentaPosrednika57A; // Račun kod korespodenta - posrednika 57A (stari intacNal)
	private String korespodentPrimatelja54A; // Korespodent primatelja 54A (stari recco_nal)
	private String korespodentPosiljatelja53B; // korespodent pošiljatelja 53B (opisno polje)
	private String informacijePosiljatelja72; // sender reciver information 72
	private String troskoviPosiljatelja71F; // troškovi pošiljatelja
	
	// constructors
	public NalogDevizniSwiftDto() {
		super();
	}
	
	// utility methods
	/**
	 * Metoda provjerava da li je bilo promjena po DTO.
	 * 
	 * Ako je barem jedno polje popunjeno, tada se vraća true.
	 *  
	 * @return
	 * @author Matija Hlapčić
	 */
	public boolean existChange() {
		boolean result = false;
		result = result || 
				StringUtils.isNotBlank(getPosrednik56A()) || 
				StringUtils.isNotBlank(getRacunKorespodentaPosrednika57A()) ||
				StringUtils.isNotBlank(getKorespodentPrimatelja54A()) || 
				StringUtils.isNotBlank(getKorespodentPosiljatelja53B()) || 
				StringUtils.isNotBlank(getInformacijePosiljatelja72()) ||
				StringUtils.isNotBlank(getTroskoviPosiljatelja71F()) || 
				getSifra() != null;
		return result;
	}

	// builder
	// private contructor for builder
	private NalogDevizniSwiftDto(NalogDevizniSwiftDtoBuilder builder) {
		super();
		setSifra(builder.sifra);
		setPosrednik56A(builder.posrednik56A);
		setRacunKorespodentaPosrednika57A(builder.racunKorespodentaPosrednika57A);
		setKorespodentPrimatelja54A(builder.korespodentPrimatelja54A);
		setKorespodentPosiljatelja53B(builder.korespodentPosiljatelja53B);
		setInformacijePosiljatelja72(builder.informacijePosiljatelja72);
		setTroskoviPosiljatelja71F(builder.troskoviPosiljatelja71F);
	}

	// builder
	public static class NalogDevizniSwiftDtoBuilder {
		// fields
		private Integer sifra;
		private String posrednik56A, korespodentPrimatelja54A, racunKorespodentaPosrednika57A, korespodentPosiljatelja53B, informacijePosiljatelja72, troskoviPosiljatelja71F;

		// constructor
		public NalogDevizniSwiftDtoBuilder() {
			super();
		}

		// methods for optional parameters
		public NalogDevizniSwiftDtoBuilder sifra(Integer sifra) {
			this.sifra = sifra;
			return this;
		}

		public NalogDevizniSwiftDtoBuilder posrednik56A(String posrednik56A) {
			this.posrednik56A = posrednik56A;
			return this;
		}

		public NalogDevizniSwiftDtoBuilder racunKorespodentaPosrednika57A(String racunKorespodentaPosrednika57A) {
			this.racunKorespodentaPosrednika57A = racunKorespodentaPosrednika57A;
			return this;
		}

		public NalogDevizniSwiftDtoBuilder korespodentPrimatelja54A(String korespodentPrimatelja54A) {
			this.korespodentPrimatelja54A = korespodentPrimatelja54A;
			return this;
		}

		public NalogDevizniSwiftDtoBuilder korespodentPosiljatelja53B(String korespodentPosiljatelja53B) {
			this.korespodentPosiljatelja53B = korespodentPosiljatelja53B;
			return this;
		}

		public NalogDevizniSwiftDtoBuilder informacijePosiljatelja72(String informacijePosiljatelja72) {
			this.informacijePosiljatelja72 = informacijePosiljatelja72;
			return this;
		}

		public NalogDevizniSwiftDtoBuilder troskoviPosiljatelja71F(String troskoviPosiljatelja71F) {
			this.troskoviPosiljatelja71F = troskoviPosiljatelja71F;
			return this;
		}

		// build method
		public NalogDevizniSwiftDto build() {
			return new NalogDevizniSwiftDto(this);
		}
	}

	// getters & setters
	public String getPosrednik56A() {
		return posrednik56A;
	}

	public void setPosrednik56A(String posrednik56a) {
		posrednik56A = posrednik56a;
	}

	public String getRacunKorespodentaPosrednika57A() {
		return racunKorespodentaPosrednika57A;
	}

	public void setRacunKorespodentaPosrednika57A(String racunKorespodentaPosrednika57A) {
		this.racunKorespodentaPosrednika57A = racunKorespodentaPosrednika57A;
	}

	public String getKorespodentPrimatelja54A() {
		return korespodentPrimatelja54A;
	}

	public void setKorespodentPrimatelja54A(String korespodentPrimatelja54A) {
		this.korespodentPrimatelja54A = korespodentPrimatelja54A;
	}

	public String getKorespodentPosiljatelja53B() {
		return korespodentPosiljatelja53B;
	}

	public void setKorespodentPosiljatelja53B(String korespodentPosiljatelja53B) {
		this.korespodentPosiljatelja53B = korespodentPosiljatelja53B;
	}

	public String getInformacijePosiljatelja72() {
		return informacijePosiljatelja72;
	}

	public void setInformacijePosiljatelja72(String informacijePosiljatelja72) {
		this.informacijePosiljatelja72 = informacijePosiljatelja72;
	}

	public String getTroskoviPosiljatelja71F() {
		return troskoviPosiljatelja71F;
	}

	public void setTroskoviPosiljatelja71F(String troskoviPosiljatelja71F) {
		this.troskoviPosiljatelja71F = troskoviPosiljatelja71F;
	}

}
