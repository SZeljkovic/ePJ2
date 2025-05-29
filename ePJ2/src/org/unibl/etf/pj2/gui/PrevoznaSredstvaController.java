package org.unibl.etf.pj2.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Klasa kontroler za scenu PrevoznaSredstva.fxml na kojoj je ponudjena opcija pregleda jednog od 3 tipa prevoznih sredstava.
 */
public class PrevoznaSredstvaController {
	@FXML
	private AnchorPane prevoznaSredstvaAnchor;
	@FXML
	private Button nazadprevozna;
	@FXML
	private Button psautomobil;
	@FXML
	private Button pstrotinet;
	@FXML
	private Button psbicikl;
	
	/**
	 * Metoda kojom se, upotrebom tastera psautomobil, prelazi na scenu TabelaAutomobil.fxml gdje je prikazana lista svih automobila.
	 * @param event Na klik
	 */
	@FXML
	public void btnPsAutomobil(ActionEvent event) {
		SceneLoader.loadScene("TabelaAutomobil.fxml", prevoznaSredstvaAnchor);
	}
	
	/**
	 * Metoda kojom se, upotrebom tastera pstrotinet, prelazi na scenu TabelaTrotinet.fxml gdje je prikazana lista svih trotineta.
	 * @param event Na klik
	 */
	@FXML
	public void btnPsTrotinet(ActionEvent event) {
		SceneLoader.loadScene("TabelaTrotinet.fxml", prevoznaSredstvaAnchor);
	}
	
	/**
	 * Metoda kojom se, upotrebom tastera psbicikl, prelazi na scenu TabelaBicikl.fxml gdje je prikazana lista svih bicikala.
	 * @param event Na klik
	 */
	@FXML
	public void btnPsBicikl(ActionEvent event) {
		SceneLoader.loadScene("TabelaBicikl.fxml", prevoznaSredstvaAnchor);
	}
	
}