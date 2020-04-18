package fxPepe;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pepe.*;


/**
 * @author anssi
 * @version 6 Apr 2020
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

    @FXML
    void handlePeliOK() {
        pepe.asetaMuutokset(peli, "nimike", peliNimike.getText());
        pepe.asetaMuutokset(peli, "alusta", peliAlusta.getValue().getTunniste() + "");
        pepe.asetaMuutokset(peli, "julkaisuvuosi", peliJulkaisuvuosi.getText());
        pepe.asetaMuutokset(peli, "hankintavuosi", peliHankintavuosi.getText());
        pepe.asetaMuutokset(peli, "kunto", peliKuntoLevy.getValue().getLuokitus() + "" + peliKuntoOhje.getValue().getLuokitus() + "" + peliKuntoKotelo.getValue().getLuokitus());
        pepe.asetaMuutokset(peli, "lisatiedot", peliLisatiedot.getText());
        
        // Peliin tehty muutoksia ja muutokset ok
        pepe.setLippu(true);
        ModalController.closeStage(peliNimike);
    }
    

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        // Kommentti
    }
    

    @Override
    public Pepe getResult() {
        return pepe;
    }
    

    @Override
    public void handleShown() {
        // Fokus pelinNimike-tekstikenttään
        peliNimike.requestFocus();
        
    }

    
    @Override
    public void setDefault(Pepe pepe) {
        this.pepe = pepe;
        this.peli = pepe.getPeliViite();
        naytaPeli();
    }
    

    /**
     * Näytetään valitun pelin tiedot UI-komponentteihin
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
}
