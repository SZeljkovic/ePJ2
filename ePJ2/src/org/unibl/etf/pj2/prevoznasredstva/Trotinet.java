package org.unibl.etf.pj2.prevoznasredstva;

import java.io.Serializable;

/**
 * Klasa kojom se predstavlja trotinet kao vrsta prevoznog sredstva za iznajmljivanje
 */
public class Trotinet extends PrevoznoSredstvo implements Serializable{
	private double maksimalnaBrzina;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Konstruktor za trotinet, sa svim parametrima ispunjenim
	 * @param id ID prevoznog sredstva (trotinet)
	 * @param proizvodjac Proizvodjac prevoznog sredstva (trotinet)
	 * @param model Model prevoznog sredstva (trotinet)
	 * @param cijenaNabavke Cijena nabavke prevoznog sredstva (trotinet)
	 * @param nivoBaterije Nivo baterije prevoznog sredstva (trotinet)
	 * @param pokvaren Da li je prevozno sredstvo pokvareno (trotinet) ?
	 * @param maksimalnaBrzina Maksimalna brzina trotineta (u km/h)
	 */
	public Trotinet(String id, String proizvodjac, String model, double cijenaNabavke, int nivoBaterije, boolean pokvaren, double maksimalnaBrzina) {
		super(id,proizvodjac,model,cijenaNabavke,nivoBaterije,pokvaren);
		this.maksimalnaBrzina=maksimalnaBrzina;
	}
	
	/**
	 * Konstruktor bez parametra nivoBaterije (***Pogledati dokumentaciju za konstruktor sa svim parametrima***)
	 */
	public Trotinet(String id, String proizvodjac, String model, double cijenaNabavke, boolean pokvaren, double maksimalnaBrzina) {
		super(id,proizvodjac,model,cijenaNabavke,pokvaren);
		this.maksimalnaBrzina=maksimalnaBrzina;
	}
	
	/**
	 * Konstruktor bez parametara nivoBaterije i pokvaren (***Pogledati dokumentaciju za konstruktor sa svim parametrima***)
	 */
	public Trotinet(String id, String proizvodjac, String model, double cijenaNabavke, double maksimalnaBrzina) {
		super(id,proizvodjac,model,cijenaNabavke);
		this.maksimalnaBrzina=maksimalnaBrzina;
	}
	
	public double getMaksimalnaBrzina() {
		return maksimalnaBrzina;
	}

	public void setMaksimalnaBrzina(double maksimalnaBrzina) {
		this.maksimalnaBrzina = maksimalnaBrzina;
	}
	
	/**
	 * Metoda specificna za svaku vrstu prevoznog sredstva, u ovom slucaju za trotinet, po specifikaciji, vraca cijenu nabavke * 0.02 kao cijenu popravke.
	 */
	@Override
	public double izracunajCijenuPopravke() {
		return this.cijenaNabavke*0.02;
	}
	
	@Override
	public String toString() {
	    return super.toString() + 
	           "\nMaksimalna Brzina: " + maksimalnaBrzina;
	}
}
