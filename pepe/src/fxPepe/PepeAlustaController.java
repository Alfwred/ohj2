package fxPepe;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.StringGrid;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import pepe.*;


/**
 * Alustojen käyttöliittymäluokka.
 * @author Anssi Lepikko
 * @version 23.4.2020
 *
 */
public class PepeAlustaController implements ModalControllerInterface<Pepe>, Initializable  {
    
    private Pepe pepe;
    
    @FXML private TextField alustaNimi;
    @FXML private TextField alustaLyhenne;
    @FXML private Button handleUUSI;
    @FXML private Button handlePOISTA;
    @FXML private Button handleOK;
    @FXML private Text textIlmoitus;
    @FXML private StringGrid<Alusta> gridAlustat;
    
    @FXML void handleUUSI() { uusiAlusta(); }
    @FXML void handlePOISTA() { poistaAlusta(); }
    @FXML void handleOK() { pepe.setLippu(true); ModalController.closeStage(alustaNimi); }
    
    @Override public void initialize(URL url, ResourceBundle bundle) { /** Ilman käyttötarkoitusta */ }
    @Override public Pepe getResult() { return pepe; }
    @Override public void handleShown()  { /** Ilman käyttötarkoitusta */ }

    @Override
    public void setDefault(Pepe pepe) {
        this.pepe = pepe;
        haeGridiin();
    }
    
    
    /**
     * Hakee alustat StringGridiin
     */
    private void haeGridiin() {
        gridAlustat.clear();        
        List<Alusta> alustat = pepe.annaAlustat();
        for (Alusta alusta : alustat) gridAlustat.add(alusta, pepe.haeKentat(alusta));
    }
    
    
    /**
     * Uuden alustan luonti
     */
    private void uusiAlusta() {
        Alusta uusi = new Alusta("LYHENNE", "Nimi");
        uusi.rekisteroi();
        pepe.lisaa(uusi);
        
        // Muutokset
        String ilmoitus;
        
        alustaLyhenne.getStyleClass().removeAll("virhe");
        ilmoitus = pepe.asetaMuutokset(uusi, "lyhenne", alustaLyhenne.getText());
        if (ilmoitus.equalsIgnoreCase("VIRHE")) {
            alustaLyhenne.getStyleClass().add("virhe");
            naytaIlmoitus("Alustan lyhenne virheellinen!");
            pepe.poista(uusi);
            return;
        }
        
        alustaNimi.getStyleClass().removeAll("virhe");
        ilmoitus = pepe.asetaMuutokset(uusi, "nimi", alustaNimi.getText());
        if (ilmoitus.equalsIgnoreCase("VIRHE")) {
            alustaNimi.getStyleClass().add("virhe");
            naytaIlmoitus("Alustan nimi virheellinen!");
            pepe.poista(uusi);
            return;
        }
        
        alustaLyhenne.clear();
        alustaNimi.clear();
        naytaIlmoitus("Uusi alusta luotu!");
        
        // Alustoihin tehty muutoksia ja muutokset ok
        pepe.setLippu(true);
        
        // Päivitetään listaus
        haeGridiin();
    }
    
    
    /**
     * Alustan poisto
     */
    private void poistaAlusta() {
        if (gridAlustat.getObject() == null) return;
        naytaIlmoitus("Alusta " + gridAlustat.getObject() + " poistettu!");
        pepe.poista(gridAlustat.getObject());
        
        // Alustoihin tehty muutoksia ja muutokset ok
        pepe.setLippu(true);
        
        // Päivitetään listaus
        haeGridiin();
    }
    
    
    /**
     * Näytetään ilmoitus käyttöliittymässä
     * @param merkkijono Ilmoitus
     */
    private void naytaIlmoitus(String merkkijono) {
        if ( merkkijono == null || merkkijono.isEmpty() ) {
            textIlmoitus.setText("");
            textIlmoitus.getStyleClass().removeAll("virhe");
            return;
        }
        textIlmoitus.setText(merkkijono);
        textIlmoitus.getStyleClass().add("virhe");
    }
}
