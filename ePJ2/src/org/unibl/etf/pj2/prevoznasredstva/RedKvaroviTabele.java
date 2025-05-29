package org.unibl.etf.pj2.prevoznasredstva;

import java.time.LocalDateTime;

/**
 * Klasa koja sluzi da se predstavi jedan red tabele za kvarove prevoznih sredstava, a samim tim predstavlja "wrapper" za razlicite tipove podataka.
 */
public class RedKvaroviTabele {
	private String id;
    private String vrsta;
    private LocalDateTime vrijemeKvara;
    private String opisKvara;
    
    /**
     * Konstruktor za klasu
     * @param id ID pokvarenog prevoznog sredstva
     * @param vrsta Vrsta prevoznog sredstva
     * @param vrijemeKvara Vrijeme kada se desio kvar (za koje iznajmljivanje)
     * @param opisKvara Tekstualni opis kvara
     */
	public RedKvaroviTabele(String id, String vrsta, LocalDateTime vrijemeKvara, String opisKvara) {
		this.id = id;
		this.vrsta = vrsta;
		this.vrijemeKvara = vrijemeKvara;
		this.opisKvara = opisKvara;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVrsta() {
		return vrsta;
	}

	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}

	public LocalDateTime getVrijemeKvara() {
		return vrijemeKvara;
	}

	public void setVrijemeKvara(LocalDateTime vrijemeKvara) {
		this.vrijemeKvara = vrijemeKvara;
	}

	public String getOpisKvara() {
		return opisKvara;
	}

	public void setOpisKvara(String opisKvara) {
		this.opisKvara = opisKvara;
	}
    
	
    
}
