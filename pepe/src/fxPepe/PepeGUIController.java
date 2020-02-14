package fxPepe;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.*;


/**
 * PEPE:n controlleri
 * @author anssi
 * @version 14 Feb 2020
 *
 */
public class PepeGUIController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        // TODO
    }
    
    /**
     * Avataan info-ikkuna
     */
    @FXML void handleInfo() {
        Dialogs.showMessageDialog("Info! Ei toimi vielä!");
    }
    
    /**
     * Järjestetään pelilista valitun sarakkeen mukaan laskevaan järjestykseen
     */
    @FXML
    void handleListaLaskeva() {
        Dialogs.showMessageDialog("Lista laskeva! Ei toimi vielä!");
    }
    
    /**
     * Järjestetään pelilista valitun sarakkeen mukaan nousevaan järjestykseen
     */
    @FXML void handleListaNouseva() {
        Dialogs.showMessageDialog("Lista nouseva! Ei toimi vielä!");
    }
    
    /**
     * Avaa suodatusvaihtoehdot
     */
    @FXML void handleListaSuodata() {
        Dialogs.showMessageDialog("Suodata lista! Ei toimi vielä!");
    }
    
    /**
     * Avaa pelinlisäysikkunan
     */
    @FXML void handlePeliLisaa() {
        Dialogs.showMessageDialog("Lisää peli! Ei toimi vielä!");
    }
    
    /**
     * Lisätään peli
     */
    @FXML void handlePeliLisaaSuorita() {
        Dialogs.showMessageDialog("Suorita pelin lisäys! Ei toimi vielä!");
    }
    
    /**
     * Avataan muokkausikkuna listasta valistusta pelistä
     */
    @FXML void handlePeliMuokkaa() {
        String kerhonNimi = PepePeliController.kysyNimi(null, "Tomb Raider II");
        Dialogs.showMessageDialog(kerhonNimi);
        
    }
    
    /**
     * Poistetaann valittu peli
     */
    @FXML void handlePeliPoista() {
        Dialogs.showMessageDialog("Poista peli! Ei toimi vielä!");
    }
    
    /**
     * Avataan resurssihallinta, josta voidaan avata peli
     */
    @FXML void handleRekisteriAvaa() {
        Dialogs.showMessageDialog("Avaa rekisteri! Ei toimi vielä!");
    }
    
    /**
     * Tallennetaan pelirekisteri
     */
    @FXML void handleRekisteriTallenna() {
        tallenna();
    }
    
    /**
     * Lopetetaan ohjelma
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