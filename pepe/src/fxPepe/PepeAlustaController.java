package fxPepe;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.StringGrid;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
public class PepeAlustaController implements ModalControllerInterface<Object[]>, Initializable  {
    
    static Object[] kuljetin;
    @FXML private TextField alustaNimi;
    @FXML private TextField alustaLyhenne;
    @FXML private Button handleOK;
    @FXML private StringGrid<Alusta> gridAlustat;
    
    @FXML void handleAlustaOK() { 
        // PASKAA
    }
    

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        // PASKAA
    }
    

    @Override
    public Object[] getResult() {
        return kuljetin;
    }
    

    @Override
    public void handleShown() {
        // Fokus pelinNimike-tekstikenttään
        alustaNimi.requestFocus();
    }

    
    @Override
    public void setDefault(Object[] oliot) {
        kuljetin = oliot;
        naytaAlusta((Pepe) kuljetin[0], (Alusta) kuljetin[1]);
        hae();
    }
    
    
    /**
     * Hakee pelien tiedot StrinGridiin
     */
    protected void hae() {
        Pepe pepe = (Pepe) kuljetin[0];
        gridAlustat.clear();        
        List<Alusta> alustat = pepe.annaAlustat();
        for (Alusta alusta : alustat) gridAlustat.add(alusta, pepe.haeKentat(alusta));
    }
    

    /**
     * Näytetään pelin tiedot UI-komponentteihin
     * @param pepe Pepe
     * @param alusta Alusta
     */
    public void naytaAlusta(Pepe pepe, Alusta alusta) {
        alustaLyhenne.setText(alusta.getLyhenne());
        alustaNimi.setText(alusta.getNimi());
    }
}
