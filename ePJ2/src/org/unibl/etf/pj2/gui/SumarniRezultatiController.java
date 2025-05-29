package org.unibl.etf.pj2.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import org.unibl.etf.pj2.iznajmljivanje.Racun;
import org.unibl.etf.pj2.prevoznasredstva.PodaciTabele;
import org.unibl.etf.pj2.prevoznasredstva.PrevoznoSredstvo;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Klasa kontroler za scenu SumarniRezultati.fxml, a na kojoj se prikazuju sumarni rezultati poslovanja.
 */
public class SumarniRezultatiController {
    @FXML
    private AnchorPane sumarniAnchor;
    @FXML
    private Button nazadsumarni;
    
    @FXML
    private Label ukupanPrihodLabel;
    @FXML
    private Label ukupanPopustLabel;
    @FXML
    private Label ukupnoPromocijeLabel;
    @FXML
    private Label ukupnoVoznjiUziDioLabel;
    @FXML
    private Label ukupnoVoznjiSiriDioLabel;
    @FXML
    private Label ukupanIznosOdrzavanjeLabel;
    @FXML
    private Label ukupanIznosPopravkeLabel;
    @FXML
    private Label ukupniTroskoviLabel;
    @FXML
    private Label ukupanPorezLabel;
    
    /**
     * Metoda za inicijalizaciju/popunjavanje labela sumarnog izvjestaja
     */
    @FXML
    public void initialize() {
        double ukupanPrihod = Racun.getUkupanPrihod();
        double ukupanPopust = Racun.getUkupanPopust();
        double ukupnoPromocije = Racun.getUkupnePromocije();
        double ukupnoVoznjiUziDio = Racun.getUkupnoUziDeoGrada();
        double ukupnoVoznjiSiriDio = Racun.getUkupnoSiriDeoGrada();
        double ukupanIznosOdrzavanje = ukupanPrihod * 0.2;
        double ukupanIznosPopravke = izracunajUkupanIznosPopravke();
        double ukupniTroskovi = ukupanPrihod * 0.2;
        double odrzavanjeIPopravke = ukupanIznosOdrzavanje + ukupanIznosPopravke;
        double ukupanPorez = Math.abs((ukupanPrihod - ukupniTroskovi - odrzavanjeIPopravke) * 0.1);

        // Postavljanje vrednosti u labele
        ukupanPrihodLabel.setText("Ukupan Prihod: " + ukupanPrihod + " KM");
        ukupanPopustLabel.setText("Ukupan Popust: " + ukupanPopust + " KM");
        ukupnoPromocijeLabel.setText("Ukupno Promocije: " + ukupnoPromocije + " KM");
        ukupnoVoznjiUziDioLabel.setText("Ukupni iznos vožnji za uži dio grada: " + ukupnoVoznjiUziDio + " KM");
        ukupnoVoznjiSiriDioLabel.setText("Ukupni iznos vožnji za širi dio grada: " + ukupnoVoznjiSiriDio + " KM");
        ukupanIznosOdrzavanjeLabel.setText("Ukupan Iznos za Održavanje: " + ukupanIznosOdrzavanje + " KM");
        ukupanIznosPopravkeLabel.setText("Ukupan Iznos za Popravke: " + ukupanIznosPopravke + " KM");
        ukupniTroskoviLabel.setText("Ukupni Troškovi Kompanije: " + ukupniTroskovi + " KM");
        ukupanPorezLabel.setText("Ukupan Porez: " + ukupanPorez + " KM");
    }
    
    /**
     * Metoda za racunanje ukupnog iznosa popravki za pokvarena prevozna sredstva, upotrebom metode izracunajCijenuPopravke() koje svako prevozno sredstvo implementira.
     * @return Ukupan iznos cijene popravki za sumarni izvjestaj
     */
    private double izracunajUkupanIznosPopravke() {
    	double ukupnePopravke = 0;
        for (PrevoznoSredstvo p : PodaciTabele.getPokvarenaPrevoznaSredstva()) {
            	ukupnePopravke = ukupnePopravke + p.izracunajCijenuPopravke();
        }
        return ukupnePopravke;
    }
    
    /**
     * Metoda za povratak na scenu RezultatiPoslovanja.fxml upotrebom buttona nazadsumarni
     * @param event Na klik
     */
    @FXML
    public void btnNazadSumarni(ActionEvent event) {
        SceneLoader.loadScene("RezultatiPoslovanja.fxml", sumarniAnchor);
    }
}
