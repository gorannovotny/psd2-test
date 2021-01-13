package hr.abc.psd2.model.dto.core;

//import hr.abit.awf.converter.GenericLazyDataModel;
//import hr.abit.awf.dataObject.LookupDetailDto;
//import hr.abit.b3.biz.core.lookup.dataObject.DeviznaPartijaLazyLookupDto;
//import hr.abit.b3.biz.core.lookup.dataObject.NadDioLookupDto;
//import hr.abit.b3.biz.core.lookup.dataObject.ValutaLookupDto;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Transfer object koji reprezentira jedan transakcijski promet.
 * 
 * @author Matija Hlapčić
 */
public class PrometWithLookupsDto extends PrometDto implements Serializable {

	private static final long serialVersionUID = 1L;

	// lookups
//	private transient DeviznaPartijaLazyLookupDto partijaLookupDto;
//	private transient ValutaLookupDto valutaLookupDto; // TODO Ivana - provjeriti jel treba
//	private transient NadDioLookupDto nadDioLookupDto;
	
	public PrometWithLookupsDto() {
		super();
	}

	public PrometWithLookupsDto(String stranaKnjizenja) {
		super(stranaKnjizenja);
	}


	 // TODO Ivana - provjeriti
//	public PrometWithLookupsDto(DeviznaPartijaLazyLookupDto partijaLookupDto, ValutaLookupDto valutaLookupDto, NadDioLookupDto nadDioLookupDto) {
//		super();
//		this.partijaLookupDto = partijaLookupDto;
//		if (this.partijaLookupDto != null) {
//			this.partijaLookupDto.setShowId(true);
//			this.partijaLookupDto.setShowDesignation(true);
//			this.partijaLookupDto.setShowDescription(true);
//			this.partijaLookupDto.setShowDescriptionOnForm(false);
//			this.partijaLookupDto.setShowExtraDescription(true);
//			this.partijaLookupDto.setShowComplexDescription(true);
//		}
//		this.valutaLookupDto = valutaLookupDto;
//		if (this.valutaLookupDto != null) {
//			this.valutaLookupDto.setShowId(true);
//			this.valutaLookupDto.setShowDesignation(true);
//			this.valutaLookupDto.setShowDescription(true);
//			this.valutaLookupDto.setShowDescriptionOnForm(false);
//		}
//		this.nadDioLookupDto = nadDioLookupDto;
//		if (this.nadDioLookupDto != null) {
//			this.nadDioLookupDto.setShowId(true);
//			this.nadDioLookupDto.setShowDesignation(true);
//			this.nadDioLookupDto.setShowDescription(true);
//			this.nadDioLookupDto.setShowExtraDescription(true);
//			this.nadDioLookupDto.setShowComplexDescription(true);
//			this.nadDioLookupDto.setShowDescriptionOnForm(false);
//		}
//	}

	 // TODO Ivana - provjeriti
//	public PrometWithLookupsDto(String stranaKnjizenja, DeviznaPartijaLazyLookupDto partijaLookupDto, ValutaLookupDto valutaLookupDto, NadDioLookupDto nadDioLookupDto) {
//		super();
//		setStranaKnjizenja(stranaKnjizenja);
//		this.partijaLookupDto = partijaLookupDto;
//		if (this.partijaLookupDto != null) {
//			this.partijaLookupDto.setShowId(true);
//			this.partijaLookupDto.setShowDesignation(true);
//			this.partijaLookupDto.setShowDescription(true);
//			this.partijaLookupDto.setShowDescriptionOnForm(false);
//			this.partijaLookupDto.setShowExtraDescription(true);
//			this.partijaLookupDto.setShowComplexDescription(true);
//		}
//		this.valutaLookupDto = valutaLookupDto;
//		if (this.valutaLookupDto != null) {
//			this.valutaLookupDto.setShowId(true);
//			this.valutaLookupDto.setShowDesignation(true);
//			this.valutaLookupDto.setShowDescription(true);
//			this.valutaLookupDto.setShowDescriptionOnForm(false);
//		}
//		this.nadDioLookupDto = nadDioLookupDto;
//		if (this.nadDioLookupDto != null) {
//			this.nadDioLookupDto.setShowId(true);
//			this.nadDioLookupDto.setShowDesignation(true);
//			this.nadDioLookupDto.setShowDescription(true);
//			this.nadDioLookupDto.setShowExtraDescription(true);
//			this.nadDioLookupDto.setShowComplexDescription(true);
//			this.nadDioLookupDto.setShowDescriptionOnForm(false);
//		}
//	}

	// TODO Ivana - provjeriti
//	public PrometWithLookupsDto(boolean isFirstCall, BigDecimal valutniDugovniIznos, BigDecimal valutniPotrazniIznos, BigDecimal domicilniDugovniIznos, BigDecimal domicilniPotrazniIznos, Integer sifraDijelaPartije, Integer sifraKnjiznogNaloga, Integer sifraPartije, String brojRacuna, String oznakaNadDijela, String stranaKnjizenja, String sifraValute, BigDecimal iznos, BigDecimal iznosDomicilni, String nazivKomitenta, Integer sifraTipaPartije, String sifraKonta, String poslovnica, String statusNaloga, Date datumValute, BigDecimal iznosValutni, String indikatorAktive, BigDecimal iznosTecaja, DeviznaPartijaLazyLookupDto partijaLookupDto, ValutaLookupDto valutaLookupDto, NadDioLookupDto nadDioLookupDto, boolean valutnaKlauzula) {
//		super(isFirstCall, valutniDugovniIznos, valutniPotrazniIznos, domicilniDugovniIznos, domicilniPotrazniIznos, sifraDijelaPartije, sifraKnjiznogNaloga, sifraPartije, brojRacuna, oznakaNadDijela, stranaKnjizenja, sifraValute, iznos, iznosDomicilni, nazivKomitenta, sifraTipaPartije, sifraKonta, poslovnica, statusNaloga, datumValute, iznosValutni, indikatorAktive, iznosTecaja, valutnaKlauzula);
//		this.partijaLookupDto = partijaLookupDto;
//		if (this.partijaLookupDto != null) {
//			this.partijaLookupDto.setShowId(true);
//			this.partijaLookupDto.setShowDesignation(true);
//			this.partijaLookupDto.setShowDescription(true);
//			this.partijaLookupDto.setShowDescriptionOnForm(false);
//			this.partijaLookupDto.setShowExtraDescription(true);
//			this.partijaLookupDto.setShowComplexDescription(true);
//		}
//		this.valutaLookupDto = valutaLookupDto;
//		if (this.valutaLookupDto != null) {
//			this.valutaLookupDto.setShowId(true);
//			this.valutaLookupDto.setShowDesignation(true);
//			this.valutaLookupDto.setShowDescription(true);
//			this.valutaLookupDto.setShowDescriptionOnForm(false);
//		}
//		this.nadDioLookupDto = nadDioLookupDto;
//		if (this.nadDioLookupDto != null) {
//			this.nadDioLookupDto.setShowId(true);
//			this.nadDioLookupDto.setShowDesignation(true);
//			this.nadDioLookupDto.setShowDescription(true);
//			this.nadDioLookupDto.setShowExtraDescription(true);
//			this.nadDioLookupDto.setShowComplexDescription(true);
//			this.nadDioLookupDto.setShowDescriptionOnForm(false);
//		}
//	}

	// adapter for PrometDto
	public static List<PrometDto> adaptListPromet(List<PrometWithLookupsDto> extendedListPromet) {
		List<PrometDto> result = null;
		if (extendedListPromet != null && !extendedListPromet.isEmpty()) {
			result = new ArrayList<>();
			for (PrometWithLookupsDto p : extendedListPromet) {
				result.add(adaptPromet(p));
			}
		}
		return result;
	}
	
	public static PrometDto adaptPromet(PrometWithLookupsDto p) {
		PrometDto result = null;
		if (p != null) {
			result = new PrometDto(p.isFirstCall(), p.getValutniDugovniIznos(), p.getValutniPotrazniIznos(), p.getDomicilniDugovniIznos(), p.getDomicilniPotrazniIznos(), p.getSifraDijelaPartije(), p.getSifraKnjiznogNaloga(), p.getSifraPartije(), p.getBrojRacuna(), p.getOznakaNadDijela(), p.getStranaKnjizenja(), p.getSifraValute(), p.getIznos(), p.getIznosDomicilni(), p.getNazivKomitenta(), p.getSifraTipaPartije(), p.getSifraKonta(), p.getPoslovnica(), p.getStatusNaloga(), p.getDatumValute(), p.getIznosValutni(), p.getIndikatorAktive(), p.getIznosTecaja(), p.isValutnaKlauzula());
		}
		return result;
	}
	
	// adapter for PrometWithLookupsDto
	public static List<PrometWithLookupsDto> adaptListPrometWithLookups(List<PrometDto> listPromet) {
		List<PrometWithLookupsDto> result = null;
		if (listPromet != null && !listPromet.isEmpty()) {
			result = new ArrayList<>();
			for (PrometDto p : listPromet) {
				result.add(adaptPrometWithLookups(p));
			}
		}
		return result;
	}
	
	public static PrometWithLookupsDto adaptPrometWithLookups(PrometDto p) {
		PrometWithLookupsDto result = null;
		if (p != null) {
			//result = new PrometWithLookupsDto(p.isFirstCall(), p.getValutniDugovniIznos(), p.getValutniPotrazniIznos(), p.getDomicilniDugovniIznos(), p.getDomicilniPotrazniIznos(), p.getSifraDijelaPartije(), p.getSifraKnjiznogNaloga(), p.getSifraPartije(), p.getBrojRacuna(), p.getOznakaNadDijela(), p.getStranaKnjizenja(), p.getSifraValute(), p.getIznos(), p.getIznosDomicilni(), p.getNazivKomitenta(), p.getSifraTipaPartije(), p.getSifraKonta(), p.getPoslovnica(), p.getStatusNaloga(), p.getDatumValute(), p.getIznosValutni(), p.getIndikatorAktive(), p.getIznosTecaja(), null, null, null, p.isValutnaKlauzula());
			result = null; // TODO Ivana - provjeriti
		}
		return result;
	}
	
	// postavljanje fieldova koji se ne serijaliziraju - lookups
	private void writeObject(ObjectOutputStream out) throws IOException {

	}
	
	private void readObject(ObjectInputStream in) throws IOException {
		
	}
	// postavljanje fieldova koji se ne serijaliziraju - lookups

	// getters & setters

	/**
	 * // TODO Ivana - provjeriti
	 * @param partijaLookupDto
	 */
//	public DeviznaPartijaLazyLookupDto getPartijaLookupDto() {
//		if (partijaLookupDto == null) {
//			partijaLookupDto = new DeviznaPartijaLazyLookupDto(new GenericLazyDataModel<LookupDetailDto>(new ArrayList<LookupDetailDto>(1)), null);
//			partijaLookupDto.setShowId(true);
//			partijaLookupDto.setShowDesignation(true);
//			partijaLookupDto.setShowDescription(true);
//			partijaLookupDto.setShowDescriptionOnForm(false);
//			partijaLookupDto.setShowExtraDescription(true);
//			partijaLookupDto.setShowComplexDescription(true);
//		}
//		partijaLookupDto.setShowDescriptionOnForm(false);
//		if (partijaLookupDto != null && partijaLookupDto.getDescription() != null && StringUtils.isNotBlank(partijaLookupDto.getDescription().toString()) && !partijaLookupDto.getDescription().toString().equalsIgnoreCase("null")) {
//			String nazivKomitentaTemp = partijaLookupDto.getDescription().toString().length() > 35 ? partijaLookupDto.getDescription().toString().substring(0, 35) : partijaLookupDto.getDescription().toString();
//			setNazivKomitenta(nazivKomitentaTemp.replace("(", "-"));
//		}
//		return partijaLookupDto;
//	}

	/**
	 * TODO Ivana - provjeriti
	 * @return
	 */
//	public void setPartijaLookupDto(DeviznaPartijaLazyLookupDto partijaLookupDto) {
//		this.partijaLookupDto = partijaLookupDto;
//	}

	/**
	 * TODO Ivana - provjeriti
	 * @param valutaLookupDto
	 */
//	public ValutaLookupDto getValutaLookupDto() {
//		if (valutaLookupDto == null) {
//			valutaLookupDto = new ValutaLookupDto(new GenericLazyDataModel<LookupDetailDto>(new ArrayList<LookupDetailDto>(1)), null);
//			valutaLookupDto.setShowId(true);
//			valutaLookupDto.setShowDesignation(true);
//			valutaLookupDto.setShowDescription(true);
//			valutaLookupDto.setShowDescriptionOnForm(false);
//		}
//		valutaLookupDto.setShowDescriptionOnForm(false);
//		return valutaLookupDto;
//	}

	/**
	 * TODO Ivana - provjeriti
	 * @return
	 */
//	public void setValutaLookupDto(ValutaLookupDto valutaLookupDto) {
//		this.valutaLookupDto = valutaLookupDto;
//	}

	/**
	 * TODO Ivana - provjeriti
	 * @param nadDioLookupDto
	 */
//	public NadDioLookupDto getNadDioLookupDto() {
//		if (nadDioLookupDto == null) {
//			nadDioLookupDto = new NadDioLookupDto(new GenericLazyDataModel<LookupDetailDto>(new ArrayList<LookupDetailDto>(1)), null);
//			nadDioLookupDto.setShowId(true);
//			nadDioLookupDto.setShowDesignation(true);
//			nadDioLookupDto.setShowDescription(true);
//			nadDioLookupDto.setShowExtraDescription(true);
//			nadDioLookupDto.setShowComplexDescription(true);
//			nadDioLookupDto.setShowDescriptionOnForm(false);
//		}
//		nadDioLookupDto.setShowDescriptionOnForm(false);
//		return nadDioLookupDto;
//	}

	/**
	 * TODO Ivana - provjeriti
	 * @param nadDioLookupDto
	 */
//	public void setNadDioLookupDto(NadDioLookupDto nadDioLookupDto) {
//		this.nadDioLookupDto = nadDioLookupDto;
//	}

}
