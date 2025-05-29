package org.unibl.etf.pj2.simulacija;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.application.Platform;
import javafx.geometry.Pos;

/**
 * Klasa koja predstavlja jedno polje mape.
 */
public class PoljeMape {
    private int x;
    private int y;
    private Pane pane;
    private Label label;
    private Rectangle rectangle;
    
    /**
     * Konstruktor za klasu
     * @param x X koordinata polja
     * @param y Y koordinata polja
     * @param pane Pojedinacni Pane element
     */
    public PoljeMape(int x, int y, Pane pane) {
        this.x = x;
        this.y = y;
        this.pane = pane;

        this.rectangle = new Rectangle(40, 40);
        this.rectangle.setFill(Color.WHITE);
        
        this.rectangle.setStroke(Color.BLACK); // Crni obrub
        this.rectangle.setStrokeWidth(0.5);
        
        this.label = new Label();
        this.label.setTextFill(Color.BLACK);
        this.label.setAlignment(Pos.CENTER);
        
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle, label);
        pane.getChildren().add(stackPane);
        
        StackPane.setAlignment(label, Pos.CENTER);
    }
    
    /**
     * Metoda kojom se azurira polje.
     * @param identifikator Identifikator prevoznog sredstva
     * @param boja Boja u koju zelimo azurirati
     * @param nivoBaterije Nivo baterije koji prosljedjujemo. (Ako je nivo baterije prevoznog sredstva -1, tada znaci da na polju nema vozila i ne ispisuje se nista)
     */
    public synchronized void azurirajPolje(String identifikator, String boja, int nivoBaterije) {
    	Platform.runLater(() -> {
    		this.rectangle.setFill(Color.valueOf(boja));
    		if (nivoBaterije == -1) {
                this.label.setText(""); 
            } else {
                this.label.setText(identifikator + "\n" + String.valueOf(nivoBaterije) + "%");
            }
        });
    }
}

