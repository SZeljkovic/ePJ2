package org.unibl.etf.pj2.prevoznasredstva;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Klasa koja predstavlja jedan kvar, a sluzi kako bi se kvar mogao predstaviti za prevozno sredstvo
 */
public class Kvar implements Serializable{
	protected String opisKvara;
	protected LocalDateTime vrijemeKvara;
	
	private static final long serialVersionUID = 1L;
	
	public String getOpisKvara() {
		return opisKvara;
	}
	public void setOpisKvara(String opisKvara) {
		this.opisKvara = opisKvara;
	}
	public LocalDateTime getVrijemeKvara() {
		return vrijemeKvara;
	}
	public void setVrijemeKvara(LocalDateTime vrijemeKvara) {
		this.vrijemeKvara = vrijemeKvara;
	}
	
	/**
	 * Konstruktor za klasu
	 * @param opisKvara Tekstualni opis kvara
	 * @param vrijemeKvara Vrijeme kada se desio kvar
	 */
	public Kvar(String opisKvara, LocalDateTime vrijemeKvara) {
		this.opisKvara=opisKvara;
		this.vrijemeKvara=vrijemeKvara;
	}
	
	public void ispis() {
		System.out.println("Opis Kvara: " + opisKvara);
		System.out.println("Vrijeme kvara: " + vrijemeKvara);
	}

}
