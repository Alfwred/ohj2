package fxPepe;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.*;

/**
 * Luokka kerhon käyttöliittymän tapahtumien hoitamiseksi.
 * @author vesal
 * @version 3.1.2018
 */
public class PepeGUIController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        //
    }

    /**
     * Käsitellään uuden jäsenen lisääminen
     */
    @FXML private void handleUusiJasen() {
        Dialogs.showMessageDialog("Ei osata vielä lisätä");
    }
    
    
    /**
     * Käsitellään tallennuskäsky
     */
    @FXML private void handleTallenna() {
        tallenna();
    }
    
    
    /**
     * Käsitellään lopetuskäsky
     */
    @FXML private void handleLopeta() {
        tallenna();
        Platform.exit();
    }

    
    /**
     * Tietojen tallennus
     */
    private void tallenna() {
        Dialogs.showMessageDialog("Tallennetetaan! Mutta ei toimi vielä");
    }
    /**
     * Tarkistetaan onko tallennus tehty
     * @return true jos saa sulkaa sovelluksen, false jos ei
     */
    public boolean voikoSulkea() {
        tallenna();
        return true;
    }

}