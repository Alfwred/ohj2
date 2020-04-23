package fxPepe;
	
import javafx.application.Application;
import javafx.stage.Stage;
import pepe.Pepe;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * PEPE:n pääohjelma, joka käynnistää käyttöliittymän
 * @author Anssi Lepikko
 * @version 23.4.2020
 */
public class PepeMain extends Application {
	@Override
	public void start(Stage primaryStage) {
	    try {
	    FXMLLoader ldr = new FXMLLoader(getClass().getResource("PepeGUIView.fxml"));
	    final Pane root = (Pane)ldr.load();
	    final PepeGUIController pepeCtrl = (PepeGUIController)ldr.getController();
	    
	    final Scene scene = new Scene(root);
	    scene.getStylesheets().add(getClass().getResource("pepe.css").toExternalForm());
	    primaryStage.setScene(scene);
	    primaryStage.setTitle("PEPE");
	    
	    Pepe pepe = new Pepe();  
        pepeCtrl.setPepe(pepe);
	    
	    primaryStage.show();
    } catch(Exception e) {
        e.printStackTrace();
    }
}
	/**
	 * Käynnistetään käyttöliittymä
	 * @param args Komentorivin parametrit
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
