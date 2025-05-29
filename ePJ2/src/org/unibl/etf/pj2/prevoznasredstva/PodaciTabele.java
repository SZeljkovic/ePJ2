package org.unibl.etf.pj2.prevoznasredstva;

import java.util.List;

/**
 * Klasa koja sluzi kao skladiste podataka i za jednostavniji prikaz podataka o prevoznim sredstvima u tabelama.
 */
public class PodaciTabele {
	private static List<Automobil> automobili;
	private static List<Bicikl> bicikli;
	private static List<Trotinet> trotineti;
	private static List<PrevoznoSredstvo> pokvarenaPrevoznaSredstva;

    public static List<Automobil> getAutomobili() {
        return automobili;
    }

	public static void setAutomobili(List<Automobil> automobili) {
		PodaciTabele.automobili = automobili;
	}

	public static List<Bicikl> getBicikli() {
		return bicikli;
	}

	public static void setBicikli(List<Bicikl> bicikli) {
		PodaciTabele.bicikli = bicikli;
	}

	public static List<Trotinet> getTrotineti() {
		return trotineti;
	}

	public static void setTrotineti(List<Trotinet> trotineti) {
		PodaciTabele.trotineti = trotineti;
	}

	public static List<PrevoznoSredstvo> getPokvarenaPrevoznaSredstva() {
		return pokvarenaPrevoznaSredstva;
	}

	public static void setPokvarenaPrevoznaSredstva(List<PrevoznoSredstvo> pokvarenaPrevoznaSredstva) {
		PodaciTabele.pokvarenaPrevoznaSredstva = pokvarenaPrevoznaSredstva;
	}
	
	
    
}
