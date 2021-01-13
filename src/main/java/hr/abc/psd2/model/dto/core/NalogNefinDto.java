package hr.abc.psd2.model.dto.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NalogNefinDto extends NalogDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<PrometNefinDto> log;

	// constructors
	public NalogNefinDto() {
		super();
	}

	public NalogNefinDto(Integer sifra, Date vrijemeNastanka, String svrha, String status, Integer sifraLikvidatora, Integer sifraModula, String sifraPoslovnice, Integer sifraTipaNaloga, Integer version, Integer sifraNalogaStorna, String oznakaStorno, Integer sifraNalogaPrethodnika) {
		super(sifra, vrijemeNastanka, svrha, status, sifraLikvidatora, sifraModula, sifraPoslovnice, sifraTipaNaloga, version, sifraNalogaStorna, oznakaStorno, sifraNalogaPrethodnika);
		this.log = new ArrayList<>();
	}

	// getters & setters
	public List<PrometNefinDto> getLog() {
		return log;
	}
	
	public void addPrometNefin(PrometNefinDto p){
		log.add(p);
	}

}
