package org.unibl.etf.pj2.prevoznasredstva;

import java.io.Serializable;

/**
 * Bazna apstraktna klasa kojom se predstavlja prevozno sredstvo za iznajmljivanje, iz koje se dalje izvode tipovi prevoznih sredstava.
 */
public abstract class PrevoznoSredstvo implements Serializable{
	protected String id;
	protected String proizvodjac;
	protected String model;
	protected double cijenaNabavke;
	protected int nivoBaterije = 100;
	protected boolean pokvaren = false;
	protected Kvar kvar;
	
	private static final long serialVersionUID = 1L;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProizvodjac() {
		return proizvodjac;
	}

	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getCijenaNabavke() {
		return cijenaNabavke;
	}

	public void setCijenaNabavke(double cijenaNabavke) {
		this.cijenaNabavke = cijenaNabavke;
	}

	public int getNivoBaterije() {
		return nivoBaterije;
	}

	public void setNivoBaterije(int nivoBaterije) {
		this.nivoBaterije = nivoBaterije;
	}
	
	public boolean isPokvaren() {
		return pokvaren;
	}

	public void setPokvaren(boolean pokvaren) {
		this.pokvaren = pokvaren;
	}

	public Kvar getKvar() {
		return kvar;
	}

	public void setKvar(Kvar kvar) {
		this.kvar = kvar;
	}
	/**
	 * Konstruktor za prevozno sredstvo sa svim parametrima popunjenim
	 * @param id ID prevoznog sredstva
	 * @param proizvodjac Proizvodjac prevoznog sredstva
	 * @param model Model prevoznog sredstva
	 * @param cijenaNabavke Cijena nabavke prevoznog sredstva
	 * @param nivoBaterije Nivo baterije prevoznog sredstva
	 * @param pokvaren Da li je prevozno sredstvo pokvareno?
	 */
	public PrevoznoSredstvo(String id, String proizvodjac, String model, double cijenaNabavke, int nivoBaterije, boolean pokvaren){
		this.id=id;
		this.proizvodjac=proizvodjac;
		this.model=model;
		this.cijenaNabavke=cijenaNabavke;
		this.nivoBaterije=nivoBaterije;
		this.pokvaren=pokvaren;
	}
	
	/**
	 * Konstruktor bez parametra nivoBaterije (***Pogledati dokumentaciju za konstruktor sa svim parametrima***)
	 */
	public PrevoznoSredstvo(String id, String proizvodjac, String model, double cijenaNabavke, boolean pokvaren){
		this.id=id;
		this.proizvodjac=proizvodjac;
		this.model=model;
		this.cijenaNabavke=cijenaNabavke;
		this.pokvaren=pokvaren;
	}
	
	/**
	 * Konstruktor bez parametara nivoBaterije i pokvaren (***Pogledati dokumentaciju za konstruktor sa svim parametrima***)
	 */
	public PrevoznoSredstvo(String id, String proizvodjac, String model, double cijenaNabavke){
		this.id=id;
		this.proizvodjac=proizvodjac;
		this.model=model;
		this.cijenaNabavke=cijenaNabavke;
	}
	
	/**
	 * Metoda kojom se puni nivo baterije prevoznog sredstva za datu kolicinu (maksimalno do 100%)
	 * @param kolicina Kolicina za koju je potrebno napuniti/postaviti nivo baterije prevoznog sredstva
	 */
	public void puniBateriju(int kolicina) {
		this.nivoBaterije = Math.min(100, this.nivoBaterije+kolicina);
	}
	
	/**
	 * Metoda kojom se prazni nivo baterije prevoznog sredstva za datu kolicinu (minimalno je 0%)
	 * @param kolicina Kolicina za koju se prazni nivo baterije. Nivo baterije minimalno moze biti 0%.
	 */
	public void prazniBateriju(int kolicina) {
		this.nivoBaterije = Math.max(0, this.nivoBaterije-kolicina);
	}
	
	/**
	 * Apstraktna metoda za racunanje cijene popravke, svaki tip prevoznog sredstva ce implementirati na razlicit nacin.
	 * @return Zavisno od tipa prevoznog sredstva i koeficijenta, cijena popravke.
	 */
	public abstract double izracunajCijenuPopravke();
	
	
	
	@Override
	public String toString() {
	    String result = "ID: " + id +
	                    "\nProizvodjac: " + proizvodjac +
	                    "\nModel: " + model +
	                    "\nCijena nabavke: " + cijenaNabavke +
	                    "\nNivo baterije: " + nivoBaterije + "%" +
	                    "\nPokvaren: " + (pokvaren ? "Da" : "Ne");

	    if (pokvaren && kvar != null) {
	        result += "\nKvar: " + kvar.toString();
	    }

	    return result;
	}

	
}
