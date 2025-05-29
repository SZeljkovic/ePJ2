package org.unibl.etf.pj2.iznajmljivanje;
/**
 * Klasa kojom se predstavlja pasos, a sluzi pri iznajmljivanju automobila.
 */
public class Pasos {
	private String ime;
	private String drzavaIzdanja;
	private String broj;
	
	
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getDrzavaIzdanja() {
		return drzavaIzdanja;
	}
	public void setDrzavaIzdanja(String drzavaIzdanja) {
		this.drzavaIzdanja = drzavaIzdanja;
	}
	public String getBroj() {
		return broj;
	}
	public void setBroj(String broj) {
		this.broj = broj;
	}
	
	/**
	 * 
	 * @param ime Ime korisnika koji ostavlja pasos pri iznajmljivanju automobila
	 * @param drzavaIzdanja Drzava iz koje je korisnik
	 * @param broj Broj pasosa
	 */
	public Pasos(String ime, String drzavaIzdanja, String broj) {
		this.ime=ime;
		this.drzavaIzdanja=drzavaIzdanja;
		this.broj=broj;
	}
}
