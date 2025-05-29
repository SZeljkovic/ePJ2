package org.unibl.etf.pj2.iznajmljivanje;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.unibl.etf.pj2.prevoznasredstva.Kvar;
/**
 * Klasa koja predstavlja jedno iznajmljivanje prevoznog sredstva.
 */
public class Iznajmljivanje {
	protected LocalDateTime vrijemeIznajmljivanja;
	protected String korisnik;
	protected String idPrevoznogSredstva;
	protected String pocetnaLokacija;
	protected String destinacija;
	protected int trajanje;
	protected boolean promocija = false;
	protected boolean pokvarenoPs;
	protected Kvar kvar;
	protected static int brojIznajmljivanja; //Staticka varijabla koja prati broj iznajmljivanja i koristi se za definisanje popusta

	public String getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(String korisnik) {
		this.korisnik = korisnik;
	}

	public LocalDateTime getVrijemeIznajmljivanja() {
		return vrijemeIznajmljivanja;
	}

	public void setVrijemeIznajmljivanja(LocalDateTime vrijemeIznajmljivanja) {
		this.vrijemeIznajmljivanja = vrijemeIznajmljivanja;
	}

	public String getPocetnaLokacija() {
		return pocetnaLokacija;
	}

	public void setPocetnaLokacija(String pocetnaLokacija) {
		this.pocetnaLokacija = pocetnaLokacija;
	}

	public String getDestinacija() {
		return destinacija;
	}

	public void setDestinacija(String destinacija) {
		this.destinacija = destinacija;
	}

	public int getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}
	
	public boolean isPromocija() {
		return promocija;
	}

	public void setPromocija(boolean promocija) {
		this.promocija = promocija;
	}

	public boolean isPokvarenoPs() {
		return pokvarenoPs;
	}

	public void setPokvarenoPs(boolean pokvarenoPs) {
		this.pokvarenoPs = pokvarenoPs;
	}

	public Kvar getKvar() {
		return kvar;
	}

	public void setKvar(Kvar kvar) {
		this.kvar = kvar;
	}

	public static int getBrojIznajmljivanja() {
		return brojIznajmljivanja;
	}

	public static void setBrojIznajmljivanja(int brojIznajmljivanja) {
		Iznajmljivanje.brojIznajmljivanja = brojIznajmljivanja;
	}

	public String getIdPrevoznogSredstva() {
		return idPrevoznogSredstva;
	}

	public void setIdPrevoznogSredstva(String idPrevoznogSredstva) {
		this.idPrevoznogSredstva = idPrevoznogSredstva;
	}

	/**
	 * Glavni konstruktor za konstruisanje objekata klase, koristi ga klasa IznajmljivanjeLoader.
	 * @param vrijemeIznajmljivanja Datum i vrijeme iznajmljivanja
	 * @param korisnik ID korisnika koji iznajmljuje prevozno sredstvo
	 * @param idPrevoznogSredstva ID prevoznog sredstva koje se koristi u iznajmljivanju
	 * @param pocetnaLokacija Koordinate pocetne lokacije sa koje prevozno sredstvo krece 
	 * @param destinacija Koordinate destinacije na kojoj se prevozno sredstvo zaustavlja
	 * @param trajanje Trajanje iznajmljivanja u sekundama
	 * @param promocija Atribut koji govori o tome da li za trenutno iznajmljivanje postoji promocija ili ne
	 * @param pokvarenoPs Atribut koji govori o tome da li je prevozno sredstvo na ovom iznajmljivanju pokvareno
	 */
	public Iznajmljivanje(LocalDateTime vrijemeIznajmljivanja, String korisnik, String idPrevoznogSredstva, String pocetnaLokacija, String destinacija, int trajanje, boolean promocija, boolean pokvarenoPs) {
		this.vrijemeIznajmljivanja = vrijemeIznajmljivanja;
		this.korisnik = korisnik;
		this.idPrevoznogSredstva=idPrevoznogSredstva;
		this.pocetnaLokacija = pocetnaLokacija;
		this.destinacija = destinacija;
		this.trajanje = trajanje;
		this.promocija=promocija;
		this.pokvarenoPs = pokvarenoPs;
		brojIznajmljivanja++;
	}
	
	@Override
	public String toString() {
	    return "Iznajmljivanje{" +
	           "vrijemeIznajmljivanja=" + vrijemeIznajmljivanja.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) +
	           ", korisnik='" + korisnik + '\'' +
	           ", idPrevoznogSredstva='" + idPrevoznogSredstva + '\'' +
	           ", pocetnaLokacija='" + pocetnaLokacija + '\'' +
	           ", destinacija='" + destinacija + '\'' +
	           ", trajanje=" + trajanje +
	           ", promocija=" + promocija +
	           ", pokvarenoPs=" + pokvarenoPs +
	           ", kvar=" + kvar +
	           '}';
	}

	
}
