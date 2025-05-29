package org.unibl.etf.pj2.iznajmljivanje;

import java.time.LocalDate;
/**
 * Klasa koja predstavlja jedinstveni tip podatka koji se prikazuje u tabeli za dnevni izvjestaj rezultata poslovanja.
 */
public class DnevniRezultat {
    private LocalDate datum;
    private double popust;
    private double ukupanPrihod;
    private double ukupnoPromocije;
    private double ukupnoSiriDioGrada;
    private double ukupnoUziDioGrada;
    private double ukupanIznosZaOdrzavanje;
    private double ukupanIznosZaPopravke;

    /**
     * Konstruktor za kreiranje objekata ove klase, kao ovakvog, koristi ga klasa DnevniRezultatiController.
     * @param datum Datum koji se prosljedjuje a za koji se vezu dnevni rezultati poslovanja
     * @param popust Ukupan popust za navedeni datum
     * @param ukupanPrihod Ukupan prihod za navedeni datum
     * @param ukupnoPromocije Ukupna kolicina promocija za navedeni datum
     * @param ukupnoSiriDioGrada Ukupni iznos voznji u sirem dijelu grada za navedeni datum
     * @param ukupnoUziDioGrada Ukupni iznos voznji u uzem dijelu grada za navedeni datum
     * @param ukupanIznosZaPopravke Ukupni iznos za popravke za navedeni datum
     */
    public DnevniRezultat(LocalDate datum, double popust, double ukupanPrihod, double ukupnoPromocije, 
                          double ukupnoSiriDioGrada, double ukupnoUziDioGrada, double ukupanIznosZaPopravke) {
        this.datum = datum;
        this.popust = popust;
        this.ukupanPrihod = ukupanPrihod;
        this.ukupnoPromocije = ukupnoPromocije;
        this.ukupnoSiriDioGrada = ukupnoSiriDioGrada;
        this.ukupnoUziDioGrada = ukupnoUziDioGrada;
        this.ukupanIznosZaOdrzavanje = ukupanPrihod * 0.2; // Racunas automatski kao 20% prihoda
        this.ukupanIznosZaPopravke = ukupanIznosZaPopravke; // Postavi po potrebi
    }

    public LocalDate getDatum() {
        return datum;
    }

    public double getPopust() {
        return popust;
    }

    public double getUkupanPrihod() {
        return ukupanPrihod;
    }

    public double getUkupnoPromocije() {
        return ukupnoPromocije;
    }

    public double getUkupnoSiriDioGrada() {
        return ukupnoSiriDioGrada;
    }

    public double getUkupnoUziDioGrada() {
        return ukupnoUziDioGrada;
    }

    public double getUkupanIznosZaOdrzavanje() {
        return ukupanIznosZaOdrzavanje;
    }

    public double getUkupanIznosZaPopravke() {
        return ukupanIznosZaPopravke;
    }

    /**
     * Metoda za azuriranje postojecih vrijednosti atributa klase
     * @param dodatniPopust Iznos popusta koji se dodaje za svako iznajmljivanje
     * @param dodatniPrihod Iznos prihoda koji se dodaje za svako iznajmljivanje
     * @param dodatnePromocije Iznos promocije koji se dodaje za svako iznajmljivanje
     * @param dodatnoSiriDio Iznos voznje za siri dio grada koji se dodaje za svako iznajmljivanje
     * @param dodatnoUziDio Iznos voznje za uzi dio grada koji se dodaje za svako iznajmljivanje
     * @param ukupanIznosZaPopravke Iznos za popravke prevoznog sredstva ako se ono pokvarilo za to iznajmljivanje
     */
    public void azuriraj(double dodatniPopust, double dodatniPrihod, double dodatnePromocije, double dodatnoSiriDio, double dodatnoUziDio, double ukupanIznosZaPopravke) {
        this.popust += dodatniPopust;
        this.ukupanPrihod += dodatniPrihod;
        this.ukupnoPromocije += dodatnePromocije;
        this.ukupnoSiriDioGrada += dodatnoSiriDio;
        this.ukupnoUziDioGrada += dodatnoUziDio;
        this.ukupanIznosZaOdrzavanje = this.ukupanPrihod * 0.2;
        this.ukupanIznosZaPopravke += ukupanIznosZaPopravke;
    }
}
