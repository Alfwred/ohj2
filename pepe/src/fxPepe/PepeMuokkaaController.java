package fxPepe;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
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
public class PepeMuokkaaController implements ModalControllerInterface<Object[]>, Initializable  {
    
    static List<Alusta> alustat;
    static Kunto kunto = new Kunto();
    static List<Kuntoluokitus> kuntoluokitukset = kunto.annaKunnot();

    @FXML
    private TextField peliNimike;
    
    @FXML
    private ComboBox<Alusta> peliAlusta;

    @FXML
    private TextField peliJulkaisuvuosi;

    @FXML
    private TextField peliHankintavuosi;
    
    @FXML
    private ComboBox<Kuntoluokitus> peliKuntoLevy;

    @FXML
    private ComboBox<Kuntoluokitus> peliKuntoOhje;

    @FXML
    private ComboBox<Kuntoluokitus> peliKuntoKotelo;

    @FXML
    private TextField peliLisatiedot;

    @FXML
    void handlePeliMuokkaaSuorita() {
        //
    }
    

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        //
    }
    

    @Override
    public Object[] getResult() {
        // TODO Auto-generated method stub
        return null;
    }
    

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    
    @Override
    public void setDefault(Object[] kuljetin) {
        Pepe pepe = (Pepe) kuljetin[0];
        Peli peli = (Peli) kuljetin[1];
        Nimike nimike = (Nimike) kuljetin[2];
        Alusta alusta = (Alusta) kuljetin[3];
        alustat = pepe.annaAlustat();
        naytaPeli(peli, nimike, alusta);
    }
    

    /**
     * Näytetään pelin tiedot UI-komponentteihin
     * @param peli Peli
     * @param nimike Pelin nimike
     * @param alusta Pelin alusta
     */
    public void naytaPeli(Peli peli, Nimike nimike, Alusta alusta) {
        if (peli == null) return;
            peliNimike.setText(nimike.getNimi());
            peliAlusta.getItems().addAll(alustat);
            peliAlusta.getSelectionModel().select(alusta);
            peliJulkaisuvuosi.setText(peli.getJulkaisuvuosi() + "");
            peliHankintavuosi.setText(peli.getHankintavuosi() + "");
            peliLisatiedot.setText(peli.getLisatiedot());
            peliKuntoLevy.getItems().addAll(kuntoluokitukset);
            peliKuntoLevy.getSelectionModel().select(kunto.annaKunto(peli.getLevy()));
            peliKuntoOhje.getSelectionModel().select(kunto.annaKunto(peli.getOhje()));
            peliKuntoKotelo.getSelectionModel().select(kunto.annaKunto(peli.getKotelo()));
    }
}
