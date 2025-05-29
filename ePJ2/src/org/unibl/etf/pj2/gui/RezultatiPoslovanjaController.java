package org.unibl.etf.pj2.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Klasa kontroler za scenu RezultatiPoslovanja.fxml na kojoj se moze odabrati da li se zele pregledati sumarni ili dnevni rezultati poslovanja.
 */
public class RezultatiPoslovanjaController {
	@FXML
	private AnchorPane rezultatiPoslovanjaAnchor;
	@FXML
	private Button nazadrezultati;
	@FXML
	private Button sumarnibutton;
	@FXML
	private Button dnevnibutton;
	
	/**
	 * Metoda koja klikom na dugme sumarnibutton prikazuje sumarne rezultate poslovanja na sceni SumarniRezultati.fxml
	 * @param event Na klik
	 */
		@FXML
	public void btnSumarniRezultati(ActionEvent event) {
		SceneLoader.loadScene("SumarniRezultati.fxml", rezultatiPoslovanjaAnchor);
	}
	
	/**
	 * Metoda koja klikom na dugme dnevnibutton prikazuje tabelu dnevnih rezultata poslovanja na sceni DnevniRezultati.fxml
	 * @param event Na klik
	 */
	@FXML
	public void btnDnevniRezultati(ActionEvent event) {
		SceneLoader.loadScene("DnevniRezultati.fxml", rezultatiPoslovanjaAnchor);
	}
	
}

