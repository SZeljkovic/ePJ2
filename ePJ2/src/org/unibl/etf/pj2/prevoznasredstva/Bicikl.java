package org.unibl.etf.pj2.prevoznasredstva;

import java.io.Serializable;

/**
 * Klasa kojom se predstavlja bicikl kao jedna od vrste prevoznog sredstva za iznajmljivanje.
 */
public class Bicikl extends PrevoznoSredstvo implements Serializable {
	private double autonomija;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Konstruktor za bicikl, sa ispunjenim svim parametrima.
	 * @param id ID prevoznog sredstva (bicikl)
	 * @param proizvodjac Proizvodjac prevoznog sredstva (bicikl)
	 * @param model Model prevoznog sredstva (bicikl)
	 * @param cijenaNabavke Cijena nabavke prevoznog sredstva (bicikl)
	 * @param nivoBaterije Nivo baterije prevoznog sredstva (bicikl)
	 * @param pokvaren Da li je prevozno sredstvo pokvareno (bicikl) ?
	 * @param autonomija Autonomija (domet) bicikla
	 */
	public Bicikl(String id, String proizvodjac, String model, double cijenaNabavke, int nivoBaterije, boolean pokvaren, double autonomija) {
		super(id,proizvodjac,model,cijenaNabavke,nivoBaterije,pokvaren);
		this.autonomija=autonomija;
	}
	
	/**
	 * Konstruktor bez parametra nivoBaterije (***Pogledati dokumentaciju za konstruktor sa svim parametrima***)
	 */
	public Bicikl(String id, String proizvodjac, String model, double cijenaNabavke, boolean pokvaren, double autonomija) {
		super(id,proizvodjac,model,cijenaNabavke,pokvaren);
		this.autonomija=autonomija;
	}
	
	/**
	 * Konstruktor bez parametara nivoBaterije i pokvaren (***Pogledati dokumentaciju za konstruktor sa svim parametrima***)
	 */
	public Bicikl(String id, String proizvodjac, String model, double cijenaNabavke, double autonomija) {
		super(id,proizvodjac,model,cijenaNabavke);
		this.autonomija=autonomija;
	}

	public double getAutonomija() {
		return autonomija;
	}

	public void setAutonomija(double autonomija) {
		this.autonomija = autonomija;
	}
	
	/**
	 * Metoda specificna za svaku vrstu prevoznog sredstva, u ovom slucaju za bicikl, po specifikaciji, vraca cijenu nabavke * 0.04 kao cijenu popravke.
	 */
	@Override
	public double izracunajCijenuPopravke() {
		return this.cijenaNabavke*0.04;
	}
	
	@Override
	public String toString() {
	    return super.toString() +  
	           "\nAutonomija: " + autonomija;
	}

	
}
