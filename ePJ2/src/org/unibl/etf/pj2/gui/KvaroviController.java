package org.unibl.etf.pj2.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.stream.Collectors;

import org.unibl.etf.pj2.prevoznasredstva.PodaciTabele;
import org.unibl.etf.pj2.prevoznasredstva.PrevoznoSredstvo;
import org.unibl.etf.pj2.prevoznasredstva.RedKvaroviTabele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Klasa kontroler za scenu Kvarovi.fxml, a sluzi za prikaz liste svih kvarova. (vrsta prevoznog sredstva, ID, datum kvara, opis kvara)
 */
public class KvaroviController {
	@FXML
	private AnchorPane kvaroviAnchor;
	@FXML
	private Button nazadkvarovi;
	@FXML
	private Button cijenepopravki;
	@FXML
    private TableView<RedKvaroviTabele> tableViewKvarovi;
    @FXML
    private TableColumn<RedKvaroviTabele, String> idColumn;
    @FXML
    private TableColumn<RedKvaroviTabele, String> vrstaColumn;
    @FXML
    private TableColumn<RedKvaroviTabele, String> vrijemeKvaraColumn;
    @FXML
    private TableColumn<RedKvaroviTabele, String> opisKvaraColumn;
    
    /**
     * Metoda za inicijalizaciju tabele kvarova.
     */
    @FXML
    public void initialize() {
        // Povezivanje kolona sa svojstvima modela
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        vrstaColumn.setCellValueFactory(new PropertyValueFactory<>("vrsta"));
        vrijemeKvaraColumn.setCellValueFactory(new PropertyValueFactory<>("vrijemeKvara"));
        opisKvaraColumn.setCellValueFactory(new PropertyValueFactory<>("opisKvara"));
        
        loadKvarovi();
    }
    
    /**
     * Metoda kojom se ucitava lista pokvarenih prevoznih sredstava, te se dalje izdvajaju detalji koji se prikazuju u tabeli kvarova.
     */
    private void loadKvarovi() {
        List<PrevoznoSredstvo> pokvarenaSredstva = PodaciTabele.getPokvarenaPrevoznaSredstva();
        
        // Kreiranje liste RedKvaroviTabele
        List<RedKvaroviTabele> kvarovi = pokvarenaSredstva.stream()
                .map(ps -> new RedKvaroviTabele(
                        ps.getId(),
                        ps.getClass().getSimpleName(), // Vrsta prevoznog sredstva
                        ps.getKvar().getVrijemeKvara(),
                        ps.getKvar().getOpisKvara()))
                .collect(Collectors.toList());

        ObservableList<RedKvaroviTabele> observableKvarovi = FXCollections.observableArrayList(kvarovi);
        tableViewKvarovi.setItems(observableKvarovi);
    }
	
	/**
	 * Metoda koja pritiskom na button cijenepopravki, vodi na scenu CijenePopravki.fxml gdje su prikazane cijene popravki pokvarenih prevoznih sredstava.
	 * @param event Na klik
	 */
	@FXML
	public void btnCijenaPopravki(ActionEvent event) {
		SceneLoader.loadScene("CijenePopravki.fxml", kvaroviAnchor);
	}
}
