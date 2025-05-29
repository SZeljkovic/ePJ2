package org.unibl.etf.pj2.prevoznasredstva;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Klasa koja predstavlja automobil kao jednu od vrste prevoznog sredstva koje se koristi za iznajmljivanje
 */
public class Automobil extends PrevoznoSredstvo implements Serializable{
	private LocalDate datumNabavke;
	private String opis;
	private int brojLjudi = 1;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Konstruktor sa ispunjenim svim parametrima, bez oslanjanja na default vrijednosti
	 * @param id ID prevoznog sredstva (automobila)
	 * @param proizvodjac Proizvodjac prevoznog sredstva (automobila)
	 * @param model Model prevoznog sredstva (automobila)
	 * @param cijenaNabavke Cijena nabavke prevoznog sredstva (automobila) u KM
	 * @param nivoBaterije Nivo baterije prevoznog sredstva (automobila)
	 * @param pokvaren Da li je pokvareno prevozno sredstvo (automobil) ?
	 * @param datumNabavke Datum nabavke automobila
	 * @param opis Dodatne informacije/opis za automobile
	 * @param brojLjudi Broj ljudi koje automobil moze prevoziti, uz napomenu da mora imati bar jednog covjeka - vozaca
	 */
	public Automobil(String id, String proizvodjac, String model, double cijenaNabavke, int nivoBaterije, boolean pokvaren, LocalDate datumNabavke, String opis,int brojLjudi){
		super(id,proizvodjac,model,cijenaNabavke,nivoBaterije,pokvaren);
		this.datumNabavke=datumNabavke;
		this.opis=opis;
		this.brojLjudi=brojLjudi;
	}
	
	/**
	 * Konstruktor bez parametra nivoBaterije (***Pogledati dokumentaciju za konstruktor sa svim parametrima***)
	 */
	public Automobil(String id, String proizvodjac, String model, double cijenaNabavke, boolean pokvaren, LocalDate datumNabavke, String opis,int brojLjudi){
		super(id,proizvodjac,model,cijenaNabavke,pokvaren);
		this.datumNabavke=datumNabavke;
		this.opis=opis;
		this.brojLjudi=brojLjudi;
	}
	
	/**
	 * Konstruktor bez parametara nivoBaterije i pokvaren (***Pogledati dokumentaciju za konstruktor sa svim parametrima***)
	 */
	public Automobil(String id, String proizvodjac, String model, double cijenaNabavke, LocalDate datumNabavke, String opis,int brojLjudi){
		super(id,proizvodjac,model,cijenaNabavke);
		this.datumNabavke=datumNabavke;
		this.opis=opis;
		this.brojLjudi=brojLjudi;
	}
	
	/**
	 * Konstruktor bez parametara nivoBaterije, pokvaren i brojLjudi (***Pogledati dokumentaciju za konstruktor sa svim parametrima***)
	 */
	public Automobil(String id, String proizvodjac, String model, double cijenaNabavke, LocalDate datumNabavke, String opis){
		super(id,proizvodjac,model,cijenaNabavke);
		this.datumNabavke=datumNabavke;
		this.opis=opis;
	}
	
	public LocalDate getDatumNabavke() {
		return datumNabavke;
	}

	public void setDatumNabavke(LocalDate datumNabavke) {
		this.datumNabavke = datumNabavke;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	public int getBrojLjudi() {
		return brojLjudi;
	}

	public void setBrojLjudi(int brojLjudi) {
		this.brojLjudi = brojLjudi;
	}
	
	/**
	 * Metoda specificna za svaku vrstu prevoznog sredstva, u ovom slucaju za automobil, po specifikaciji, vraca cijenu nabavke * 0.07 kao cijenu popravke.
	 */
	@Override
	public double izracunajCijenuPopravke() {
		return this.cijenaNabavke*0.07;
	}
	
	@Override
	public String toString() {
	    return super.toString() + 
	           "\nDatum nabavke: " + datumNabavke +
	           "\nOpis: " + opis +
	           "\nBroj ljudi: " + brojLjudi;
	}

}
