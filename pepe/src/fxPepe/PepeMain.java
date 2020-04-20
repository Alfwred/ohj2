package fxPepe;
	
import fi.jyu.mit.fxgui.Dialogs;
import javafx.application.Application;
import javafx.stage.Stage;
import pepe.Pepe;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * 
 * @author anssi
 * @version 14 Feb 2020
 * 
 * Pääohjelma Pepe-ohjelman käynnistämiseksi
 *
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
	    
	    // Voiko sulkea -dialogi
	    //primaryStage.setOnCloseRequest((event) -> {
	    //   if ( !pepeCtrl.voikoSulkea() ) event.consume();
	    //});
	    
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
