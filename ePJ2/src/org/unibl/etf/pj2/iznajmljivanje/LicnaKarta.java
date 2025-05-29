package org.unibl.etf.pj2.iznajmljivanje;

/**
 * Klasa kojom se predstavlja licna karta a koja sluzi da se prosljedjuje pri iznajmljivanju automobila
 */
public class LicnaKarta {
	private String ime;
	private String broj;
	
	
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getBroj() {
		return broj;
	}
	public void setBroj(String broj) {
		this.broj = broj;
	}
	
	/**
	 * 
	 * @param ime Ime osobe na licnoj karti
	 * @param broj Broj licne karte
	 */
	public LicnaKarta(String ime, String broj) {
		this.ime=ime;
		this.broj=broj;
	}
	
	@Override
	public String toString() {
		return "LicnaKarta [ime=" + ime + ", broj=" + broj + "]";
	}
	
	
}
