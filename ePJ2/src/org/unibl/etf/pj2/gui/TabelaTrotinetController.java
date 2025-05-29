package org.unibl.etf.pj2.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;
import org.unibl.etf.pj2.prevoznasredstva.PodaciTabele;
import org.unibl.etf.pj2.prevoznasredstva.Trotinet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Klasa kontroler za scenu TabelaTrotinet.fxml a na kojoj su prikazani svi trotineti.
 */
public class TabelaTrotinetController {
	@FXML
	private AnchorPane trotinetAnchor;
	@FXML
	private Button nazadtrotinet;
	@FXML
    private TableView<Trotinet> tableView;
    @FXML
    private TableColumn<Trotinet, String> idColumn;
    @FXML
    private TableColumn<Trotinet, String> proizvodjacColumn;
    @FXML
    private TableColumn<Trotinet, String> modelColumn;
    @FXML
    private TableColumn<Trotinet, Double> cijenaNabavkeColumn;
    @FXML
    private TableColumn<Trotinet, Double> maksimalnaBrzinaColumn;
    @FXML
    private TableColumn<Trotinet, Boolean> pokvarenColumn;
    
    /**
     * Metoda za inicijalizaciju/popunjavanje tabele trotineta.
     */
    @FXML
    public void initialize() {
        // Povezivanje kolona sa svojstvima modela
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        proizvodjacColumn.setCellValueFactory(new PropertyValueFactory<>("proizvodjac"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        cijenaNabavkeColumn.setCellValueFactory(new PropertyValueFactory<>("cijenaNabavke"));
        maksimalnaBrzinaColumn.setCellValueFactory(new PropertyValueFactory<>("maksimalnaBrzina"));
        pokvarenColumn.setCellValueFactory(new PropertyValueFactory<>("pokvaren"));
        
        loadTrotineti();
    }
    
    /**
     * Metoda kojom se ucitavaju trotineti iz liste svih trotineta klase PodaciTabele, a koja se koristi pri inicijalizaciji tabele.
     */
    private void loadTrotineti() {
        List<Trotinet> trotineti = PodaciTabele.getTrotineti();
        ObservableList<Trotinet> observableTrotineti = FXCollections.observableArrayList(trotineti);
        tableView.setItems(observableTrotineti);
    }

	/**
	 * Metoda koja sluzi za povratak na scenu PrevoznaSredstva.fxml upotrebom buttona nazadtrotinet.
	 * @param event Na klik
	 */
	@FXML
	public void btnNazadTrotinet(ActionEvent event) {
		SceneLoader.loadScene("PrevoznaSredstva.fxml", trotinetAnchor);
	}
}

