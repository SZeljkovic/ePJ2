package org.unibl.etf.pj2.iznajmljivanje;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Klasa koja se koristi za predstavljanje iznajmljivanja za automobile i samim tim nasljedjuje sve atribute klase Iznajmljivanje a pri tome dodaje i specijalizovane za automobile.
 */
public class IznajmljivanjeAutomobila extends Iznajmljivanje {
	private Pasos pasos;
	private LicnaKarta licnaKarta;
	private String vozacka;
	
	
	public Pasos getPasos() {
		return pasos;
	}
	public void setPasos(Pasos pasos) {
		this.pasos = pasos;
	}
	public LicnaKarta getLicnaKarta() {
		return licnaKarta;
	}
	public void setLicnaKarta(LicnaKarta licnaKarta) {
		this.licnaKarta = licnaKarta;
	}
	public String getVozacka() {
		return vozacka;
	}
	public void setVozacka(String vozacka) {
		this.vozacka = vozacka;
	}
	
	/**
	 * Konstruktor koji se koristi za strane drzavljane, a koji ostavljaju pasos i vozacku dozvolu
	 * @param vrijemeIznajmljivanja Datum i vrijeme iznajmljivanja
	 * @param korisnik ID korisnika koji iznajmljuje prevozno sredstvo
	 * @param idPrevoznogSredstva ID prevoznog sredstva koje se koristi u iznajmljivanju
	 * @param pocetnaLokacija Koordinate pocetne lokacije sa koje prevozno sredstvo krece
	 * @param destinacija Koordinate destinacije na kojoj se prevozno sredstvo zaustavlja
	 * @param trajanje Trajanje iznajmljivanja u sekundama
	 * @param promocija Atribut koji govori o tome da li za trenutno iznajmljivanje postoji promocija ili ne
	 * @param pokvarenoPs Atribut koji govori o tome da li je prevozno sredstvo na ovom iznajmljivanju pokvareno
	 * @param pasos Predstavlja objekat klase Pasos koji strani drzavljani moraju ostaviti
	 * @param vozacka Broj vozacke dozvole koju svaki od korisnika koji iznajmljuju automobil moraju ostaviti
	 */
	public IznajmljivanjeAutomobila(LocalDateTime vrijemeIznajmljivanja, String korisnik, String idPrevoznogSredstva, String pocetnaLokacija, String destinacija, int trajanje, boolean promocija, boolean pokvarenoPs, Pasos pasos, String vozacka) {
		super(vrijemeIznajmljivanja, korisnik, idPrevoznogSredstva, pocetnaLokacija, destinacija, trajanje, promocija, pokvarenoPs);
		this.pasos=pasos;
		this.vozacka=vozacka;
	}
	
	/**
	 * Konstruktor koji se koristi za domace drzavljane, a koji ostavljaju licnu kartu i vozacku dozvolu
	 * @param vrijemeIznajmljivanja Datum i vrijeme iznajmljivanja
	 * @param korisnik ID korisnika koji iznajmljuje prevozno sredstvo
	 * @param idPrevoznogSredstva ID prevoznog sredstva koje se koristi u iznajmljivanju
	 * @param pocetnaLokacija Koordinate pocetne lokacije sa koje prevozno sredstvo krece
	 * @param destinacija Koordinate destinacije na kojoj se prevozno sredstvo zaustavlja
	 * @param trajanje Trajanje iznajmljivanja u sekundama
	 * @param promocija Atribut koji govori o tome da li za trenutno iznajmljivanje postoji promocija ili ne
	 * @param pokvarenoPs Atribut koji govori o tome da li je prevozno sredstvo na ovom iznajmljivanju pokvareno
	 * @param licnaKarta Predstavlja objekat klase LicnaKarta koju domaci drzavljani moraju ostaviti
	 * @param vozacka Broj vozacke dozvole koju svaki od korisnika koji iznajmljuju automobil moraju ostaviti
	 */
	public IznajmljivanjeAutomobila(LocalDateTime vrijemeIznajmljivanja, String korisnik, String idPrevoznogSredstva, String pocetnaLokacija, String destinacija, int trajanje, boolean promocija,boolean pokvarenoPs, LicnaKarta licnaKarta, String vozacka) {
		super(vrijemeIznajmljivanja, korisnik, idPrevoznogSredstva, pocetnaLokacija, destinacija, trajanje, promocija, pokvarenoPs);
		this.licnaKarta=licnaKarta;
		this.vozacka=vozacka;
	}
	
	@Override
	public String toString() {
	    return "IznajmljivanjeAutomobila{" +
	           "vrijemeIznajmljivanja=" + vrijemeIznajmljivanja.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) +
	           ", korisnik='" + korisnik + '\'' +
	           ", idPrevoznogSredstva='" + idPrevoznogSredstva + '\'' +
	           ", pocetnaLokacija='" + pocetnaLokacija + '\'' +
	           ", destinacija='" + destinacija + '\'' +
	           ", trajanje=" + trajanje +
	           ", promocija=" + promocija +
	           ", pokvarenoPs=" + pokvarenoPs +
	           ", pasos=" + pasos +
	           ", licnaKarta=" + licnaKarta +
	           ", vozacka='" + vozacka + '\'' +
	           '}';
	}

}
