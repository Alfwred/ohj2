package fxPepe;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import pepe.*;


/**
 * Pelien käyttöliittymäluokka.
 * @author Anssi Lepikko
 * @version 23.4.2020
 *
 */
public class PepePeliController implements ModalControllerInterface<Pepe>, Initializable  {
    
    private Pepe pepe;
    private Peli peli;
    
    private List<Alusta> alustat;
    private List<Kuntoluokitus> kuntoluokitukset;
    
    @FXML private TextField peliNimike;
    @FXML private ComboBox<Alusta> peliAlusta;
    @FXML private TextField peliJulkaisuvuosi;
    @FXML private TextField peliHankintavuosi;
    @FXML private ComboBox<Kuntoluokitus> peliKuntoLevy;
    @FXML private ComboBox<Kuntoluokitus> peliKuntoOhje;
    @FXML private ComboBox<Kuntoluokitus> peliKuntoKotelo;
    @FXML private TextField peliLisatiedot;
    @FXML private Text textIlmoitus;

    @FXML void handleOK() { hyvaksyMuutokset(); }
    
    @Override public void initialize(URL url, ResourceBundle bundle)  { /** Ilman käyttötarkoitusta */ }
    @Override public Pepe getResult() { return pepe; }
    @Override public void handleShown() { peliNimike.requestFocus(); }

    
    @Override
    public void setDefault(Pepe pepe) {
        this.pepe = pepe;
        this.peli = pepe.getPeliViite();
        naytaPeli();
    }
    

    /**
     * Näytetään valitun pelin tiedot komponentteihin
     */
    public void naytaPeli() {
        alustat = pepe.annaAlustat();
        kuntoluokitukset = pepe.annaKuntoluokitukset();
        
        peliNimike.setText(pepe.annaNimike(peli).getNimi());
        peliAlusta.getItems().addAll(alustat);
        peliAlusta.getSelectionModel().select(pepe.annaAlusta(peli));
        peliJulkaisuvuosi.setText(peli.getJulkaisuvuosi() + "");
        peliHankintavuosi.setText(peli.getHankintavuosi() + "");
        peliLisatiedot.setText(peli.getLisatiedot());
        peliKuntoLevy.getItems().addAll(kuntoluokitukset);
        peliKuntoLevy.getSelectionModel().select(pepe.haeLuokitus(peli.getLevy()));
        peliKuntoOhje.getItems().addAll(kuntoluokitukset);
        peliKuntoOhje.getSelectionModel().select(pepe.haeLuokitus(peli.getOhje()));
        peliKuntoKotelo.getItems().addAll(kuntoluokitukset);
        peliKuntoKotelo.getSelectionModel().select(pepe.haeLuokitus(peli.getKotelo()));
    }
    
    
    /**
     * Asettaa muutokset peliin
     */
    private void hyvaksyMuutokset() {
        // Muutokset
        String ilmoitus;
        
        peliNimike.getStyleClass().remove("virhe");
        ilmoitus = pepe.asetaMuutokset(peli, "nimike", peliNimike.getText());
        if (ilmoitus.equalsIgnoreCase("VIRHE")) {
            peliNimike.getStyleClass().add("virhe");
            naytaIlmoitus("Virheellinen nimike!");
            return;
        }
        
        peliAlusta.getStyleClass().remove("virhe");
        if (peliAlusta.getValue() == null) {
            peliAlusta.getStyleClass().add("virhe");
            naytaIlmoitus("Valitse alusta!");
            return;
        }    
        
        pepe.asetaMuutokset(peli, "alusta", peliAlusta.getValue().getTunniste() + "");

        peliJulkaisuvuosi.getStyleClass().remove("virhe");
        ilmoitus = pepe.asetaMuutokset(peli, "julkaisuvuosi", peliJulkaisuvuosi.getText());
        if (ilmoitus.equalsIgnoreCase("VIRHE")) {
            peliJulkaisuvuosi.getStyleClass().add("virhe");
            naytaIlmoitus("Virheellinen julkaisuvuosi!");
            return;
        }
        
        peliHankintavuosi.getStyleClass().remove("virhe");
        ilmoitus = pepe.asetaMuutokset(peli, "hankintavuosi", peliHankintavuosi.getText());
        if (ilmoitus.equalsIgnoreCase("VIRHE")) {
            peliHankintavuosi.getStyleClass().add("virhe");
            naytaIlmoitus("Virheellinen hankintavuosi!");
            return;
        }
        
        ilmoitus = pepe.asetaMuutokset(peli, "kunto", peliKuntoLevy.getValue().getLuokitus() + "" + peliKuntoOhje.getValue().getLuokitus() + "" + peliKuntoKotelo.getValue().getLuokitus());
        
        peliLisatiedot.getStyleClass().remove("virhe");
        ilmoitus = pepe.asetaMuutokset(peli, "lisatiedot", peliLisatiedot.getText());
        if (ilmoitus.equalsIgnoreCase("VIRHE")) {
            peliLisatiedot.getStyleClass().add("virhe");
            naytaIlmoitus("Virheelliset lisätiedot!");
            return;
        }
        
        // Peliin tehty muutoksia ja muutokset ok
        pepe.setLippu(true);
        ModalController.closeStage(peliNimike);
    }
    
    
    /**
     * Näytetään ilmoitus käyttöliittymässä
     * @param merkkijono Ilmoitus
     */
    private void naytaIlmoitus(String merkkijono) {
        if ( merkkijono == null || merkkijono.isEmpty() ) {
            textIlmoitus.setText("");
            textIlmoitus.getStyleClass().removeAll("Virhe");
            return;
        }
        textIlmoitus.setText(merkkijono);
        textIlmoitus.getStyleClass().add("Virhe");
    }
}
