package org.unibl.etf.pj2.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.Map;

import org.unibl.etf.pj2.iznajmljivanje.DnevniRezultat;
import org.unibl.etf.pj2.iznajmljivanje.Racun;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Klasa kontroler za scenu DnevniRezultati.fxml, a radi se o tabelarnom prikazu dnevnih rezultata poslovanja
 */
public class DnevniRezultatiController {
	@FXML
	private AnchorPane dnevniAnchor;
	@FXML
	private Button nazaddnevni;
	@FXML
	private TableView<DnevniRezultat> tableView;
	@FXML
	private TableColumn<DnevniRezultat, LocalDate> datumColumn;
	@FXML
	private TableColumn<DnevniRezultat, Double> ukupanPopustColumn;
	@FXML
	private TableColumn<DnevniRezultat, Double> ukupanPrihodColumn;
	@FXML
	private TableColumn<DnevniRezultat, Double> siriDioColumn;
	@FXML
	private TableColumn<DnevniRezultat, Double> uziDioColumn;
	@FXML
	private TableColumn<DnevniRezultat, Double> ukupnoPromocijeColumn;
	@FXML
	private TableColumn<DnevniRezultat, Double> ukupnoOdrzavanje;
	@FXML
	private TableColumn<DnevniRezultat, Double> ukupnoPopravke;
	
	private ObservableList<DnevniRezultat> rezultati = FXCollections.observableArrayList();
	
	/**
	 * Metoda za inicijalizaciju podataka u tabelu dnevnih rezultata iz klase DnevniRezultat
	 */
	@FXML
	public void initialize() {
	    // Postavljanje kolona za prikaz podataka iz DnevniRezultat
	    datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
	    ukupanPopustColumn.setCellValueFactory(new PropertyValueFactory<>("popust"));
	    ukupanPrihodColumn.setCellValueFactory(new PropertyValueFactory<>("ukupanPrihod"));
	    siriDioColumn.setCellValueFactory(new PropertyValueFactory<>("ukupnoSiriDioGrada"));
	    uziDioColumn.setCellValueFactory(new PropertyValueFactory<>("ukupnoUziDioGrada"));
	    ukupnoPromocijeColumn.setCellValueFactory(new PropertyValueFactory<>("ukupnoPromocije"));
	    ukupnoOdrzavanje.setCellValueFactory(new PropertyValueFactory<>("ukupanIznosZaOdrzavanje"));
	    ukupnoPopravke.setCellValueFactory(new PropertyValueFactory<>("ukupanIznosZaPopravke"));

	    // Preuzmimanje rezultata po datumu iz klase Racun
	    Map<LocalDate, DnevniRezultat> rezultatiPoDatumu = Racun.getRezultatiPoDatumu();

	    // Kreiranje ObservableList na osnovu rezultata
	    for (Map.Entry<LocalDate, DnevniRezultat> entry : rezultatiPoDatumu.entrySet()) {
	        rezultati.add(entry.getValue());
	    }

	    // Postavljanje ObservableList kao podatke za TableView
	    tableView.setItems(rezultati);
	}


	/**
	 * Metoda kojom se vraca na scenu RezultatiPoslovanja.fxml klikom na button nazaddnevni
	 * @param event Na klik
	 */
	@FXML
	public void btnDnevniNazad(ActionEvent event) {
		SceneLoader.loadScene("RezultatiPoslovanja.fxml", dnevniAnchor);
	}
	

}

