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
public class PepeAlustaController implements ModalControllerInterface<Pepe>, Initializable  {
    
    private Pepe pepe;
    private Alusta alustaValittu;
    
    @FXML private TextField alustaNimi;
    @FXML private TextField alustaLyhenne;
    @FXML private Button handleUUSI;
    @FXML private Button handlePOISTA;
    @FXML private Button handleOK;
    @FXML private StringGrid<Alusta> gridAlustat;
    

    @FXML void handleUUSI() { 
        Alusta uusi = new Alusta("UUDEN ALUSTAN LYHENNE", "Uuden alustan nimi");
        uusi.rekisteroi();
        pepe.lisaa(uusi);
        
        // Muutokset
        pepe.asetaMuutokset(uusi, "lyhenne", alustaLyhenne.getText());
        pepe.asetaMuutokset(uusi, "nimi", alustaNimi.getText());
        
        // Päivitetään listaus
        hae();
    }
    
    
    @FXML void handlePOISTA() {
        alustaValittu = gridAlustat.getObject();
        pepe.poista(alustaValittu);
        
        // Päivitetään listaus
        hae();
    }
    
    
    @FXML void handleOK() {
        ModalController.closeStage(alustaNimi);
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
        // Kommentti
    }

    
    @Override
    public void setDefault(Pepe pepe) {
        this.pepe = pepe;
        hae();
    }
    
    
    /**
     * Hakee alustat StringGridiin
     */
    protected void hae() {
        gridAlustat.clear();        
        List<Alusta> alustat = pepe.annaAlustat();
        for (Alusta alusta : alustat) gridAlustat.add(alusta, pepe.haeKentat(alusta));
    }
}
