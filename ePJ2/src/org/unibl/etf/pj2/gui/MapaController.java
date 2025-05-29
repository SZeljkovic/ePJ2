package org.unibl.etf.pj2.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import org.unibl.etf.pj2.simulacija.Mapa;


/**
 * Klasa kontroler za glavnu scenu Mapa.fxml, a radi se o prikazu mape po kojoj se krecu prevozna sredstva, uz opciju klika na buttone za prelaz na ostale scene.
 */
public class MapaController {
    @FXML
    private AnchorPane glavnianchorpane;
    @FXML
    private Button prevoznasredstva;
    @FXML
    private Button kvarovi;
    @FXML
    private Button rezultatiposlovanja;
    @FXML
    private GridPane gridPaneMapa;
    
    private Mapa mapa;
    
    /**
     * Metoda za inicijalizaciju mape, tacnije za povezivanje sa GridPaneom iz FXML-a.
     */
    public void initialize() {
        mapa = new Mapa(gridPaneMapa); 
    }
    
    /**
     * Metoda getter za mapu.
     * @return Objekat mape.
     */
    public Mapa getMapa() {
        return mapa;
    }
    
    /**
     * Metoda kojom se ide na scenu PrevoznaSredstva.fxml gdje se moze izabrati za koju vrstu prevoznih sredstava je moguce pregledati podatke.
     * @param event Na klik
     */
    @FXML
    public void btnPrevoznaSredstva(ActionEvent event) {
        SceneLoader.popupScene("PrevoznaSredstva.fxml", glavnianchorpane);
    }
    
    /**
     * Metoda kojom se ide na scenu Kvarovi.fxml gdje se moze pogledati lista svih kvarova, kao i cijene popravki prevoznih sredstava.
     * @param event Na klik
     */
    @FXML
    public void btnKvarovi(ActionEvent event) {
        SceneLoader.popupScene("Kvarovi.fxml", glavnianchorpane);
    	
    }
    
    /**
     * Metoda kojom se ide na scenu RezultatiPoslovanja.fxml gdje se moze odabrati da li se zele prikazati dnevni ili sumarni rezultati poslovanja.
     * @param event Na klik
     */
    @FXML
    public void btnRezultatiPoslovanja(ActionEvent event) {
        SceneLoader.popupScene("RezultatiPoslovanja.fxml", glavnianchorpane);
    }

}

