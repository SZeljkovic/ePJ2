package org.unibl.etf.pj2.simulacija;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Klasa koja predstavlja mapu na kojoj se krecu prevozna sredstva. To je jedan GridPane, a sadrzi 20x20 polja.
 */
public class Mapa {
	private GridPane gridPane;
    private PoljeMape[][] polja;
    private HashMap<String, List<String>> vozilaNaPolju;  // Mapa koja prati vozila na svakom polju
    
    /**
     * Konstruktor za klasu, ujedno inicijalizuje samu mapu.
     * @param gridPane Grid pane koji se dodjeljuje mapi, i predstavlja polja 20x20.
     */
    public Mapa(GridPane gridPane) {
        this.gridPane = gridPane;
        this.polja = new PoljeMape[20][20];
        this.vozilaNaPolju = new HashMap<>();
        initializeMap();
    }
    
    /**
     * Funkcija za inicijalizaciju mape koju pozivamo u konstruktoru, a koja postavlja svaki pojedinacni Pane, i po potrebi postavlja boju polja za uzi dio grada.
     */
    private void initializeMap() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                Pane pane = new Pane();
                PoljeMape polje = new PoljeMape(i, j, pane);
                
                if((i >= 5 && i <= 5 && j >= 5 && j <= 14) || (i >= 14 && i <= 14 && j >= 5 && j <= 14) ||
                        (i >= 5 && i <= 14 && j >= 5 && j <= 14)) {
                    polje.azurirajPolje("", "#ADD8E6", -1);  //Polja se oboje u svijetlo plavu boju (light blue)
                }
                
                polja[i][j] = polje;
                gridPane.add(pane, j, i);
            }
        }
    }
    
    /**
     * Metoda koja se koristi za azuriranje vizuelnog prikaza polja. To ukljucuje promjenu boje, postavljanje IDa prevoznog sredstva i nivoa baterije
     * @param x X koordinata polja
     * @param y Y koordinata polja
     * @param identifikator Identifikator prevoznog sredstva
     * @param boja Boja na koju je polje potrebno postaviti
     * @param nivoBaterije Nivo baterije prevoznog sredstva
     */
    public synchronized void azurirajPolje(int x, int y, String identifikator, String boja, int nivoBaterije) {
        String kljucPolja = x + "," + y;

        if (!vozilaNaPolju.containsKey(kljucPolja)) {
            vozilaNaPolju.put(kljucPolja, new ArrayList<>());
        }

        List<String> vozila = vozilaNaPolju.get(kljucPolja);

        if (identifikator.isEmpty()) {
            // Ako je identifikator prazan, uklonimo vozilo sa tog polja
            vozila.clear();

            // Ako je polje u centralnoj zoni, vracamo boju na #ADD8E6
            if (x >= 5 && x <= 14 && y >= 5 && y <= 14) {
                boja = "#ADD8E6";
            } else {
                boja = "white";
            }
        } else {
            // Dodamo vozilo na listu ako vec nije tu
            if (!vozila.contains(identifikator)) {
                vozila.add(identifikator);
            }
        }

        // Azuriramo prikaz na polju
        StringBuilder prikaz = new StringBuilder();
        for (String id : vozila) {
            prikaz.append(id).append("\n");
        }
        final String prikazTekst = prikaz.toString().trim();
        final String finalBoja = boja;  // Definisemo novu promijenljivu koja ce biti effectively final

        Platform.runLater(() -> {
            polja[x][y].azurirajPolje(prikazTekst, finalBoja, nivoBaterije);
        });
    }

    /**
     * Metoda kojom se resetuje izgled mape nakon kruga iznajmljivanja. Centralni dio mape postavlja na svijetlo plavu, a ostalo na bijelu boju.
     */
    public synchronized void resetMap() {
        vozilaNaPolju.clear();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if ((i >= 5 && i <= 5 && j >= 5 && j <= 14) || (i >= 14 && i <= 14 && j >= 5 && j <= 14) ||
                    (i >= 5 && i <= 14 && j >= 5 && j <= 14)) {
                    // Svijetlo plavi dio ostaje nepromijenjen
                    polja[i][j].azurirajPolje("", "#ADD8E6", -1);
                } else {
                    polja[i][j].azurirajPolje("", "white", -1);
                }
            }
        }
    }

}

