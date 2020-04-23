package fxPepe;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.StringGrid;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pepe.Nimike;
import pepe.Peli;
import pepe.Pepe;
import fxPepe.SailoException;


/**
 * Luokka Pepen käyttöliittymän tapahtumien hoitamiseksi.
 * @author Anssi Lepikko
 * @version 23.4.2020
 *
 */
public class PepeGUIController implements Initializable {
    
    @FXML private TextField hakuehto;
    @FXML private Label labelVirhe;
    @FXML private StringGrid<Peli> gridPelit;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        // Avataan muokkausikkuna, kun tuplaklikataan peliä
        gridPelit.setOnMouseClicked( e -> { if ( e.getClickCount() == 2 ) muokkaaPeli(); });
    }
    
    @FXML void handleHakuehto() { haku(); }
    @FXML void handleInfo() { avustus(); }
    @FXML void handlePeliLisaa() { uusiPeli(); }
    @FXML void handlePeliMuokkaa() { muokkaaPeli(); }
    @FXML void handlePeliPoista() { poistaPeli(); }
    @FXML void handleAlustaMuokkaa() { muokkaaAlustoja(); }
    @FXML void handleRekisteriTallenna() throws SailoException { tallenna(); }
    @FXML void handleLopeta() throws SailoException { tallenna(); Platform.exit(); }
    
//===========================================================================================
    
    private Pepe pepe;
    private Peli peliValittu;

    /**
     * Näyttää ilmoituksen käyttöliittymässä
     * @param merkkijono Ilmoitus
     */
    private void naytaIlmoitus(String merkkijono) {
        if ( merkkijono == null || merkkijono.isEmpty() ) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("Virhe");
            return;
        }
        labelVirhe.setText(merkkijono);
        labelVirhe.getStyleClass().add("Virhe");
    }
    
    
    /**
     * Lukee PEPE:n pelikannan
     */
    private void lueTiedosto() {
        try {
            pepe.lueTiedostosta();
            naytaIlmoitus("Pelikanta luettu!");
        } catch (SailoException e) {
            e.printStackTrace();
        }
    }

    
    /**
     * Pelikannan tallennus
     * @throws SailoException Virhe
     */
    private void tallenna() throws SailoException {
        pepe.tallenna();
        naytaIlmoitus("Pelikanta tallennettu!");
    }
    
    
    /**
     * Poistaa gridistä valitun pelin
     */
    private void poistaPeli() {
        peliValittu = gridPelit.getObject();
        
        // Ei poisteta, jos ei ole valittu peliä
        if (peliValittu == null) {
            naytaIlmoitus("Valitse listasta peli, jonka haluat poistaa!");
            return;
        }
        
        // Poistetaan peli
        naytaIlmoitus("Peli poistettu! " + pepe.annaNimike(peliValittu).getNimi());
        pepe.poista(peliValittu);

        // Päivitetään pelilista
        haeGridiin();
    }
    
    
    /**
     * Avaa valitun pelin muokkausdialogissa
     */
    private void muokkaaPeli() {
        
        // Sijoitetaan pelin viite talteen Pepeen
        pepe.setPeliViite(gridPelit.getObject());
        
        // Ei muokata, jos ei ole valittu peliä
        if (gridPelit.getObject() == null) {
            naytaIlmoitus("Valitse listasta peli, jota haluat muokata!");
            return;
        }
        
        // Lipun arvon asetus, jotta voidaan alempana testa muutoksia
        pepe.setLippu(false);
        
        // Siirrytään muokkausikkunaan
        ModalController.showModal(PepeGUIController.class.getResource("PepePeliView.fxml"), "Muokkaa peliä", null, pepe);
        
        // Testataan muokattiinko mitään
        if (pepe.getLippu() == false) naytaIlmoitus("Pelin muokkaus peruutettu!");
        else naytaIlmoitus("Peli muokattu onnistuneesti!");
        
        // Päivitetään pelilista
        haeGridiin(); 
    }
    
    
    /**
     * Hakee pelien tiedot StrinGridiin
     */
    private void haeGridiin() {
        gridPelit.clear();        
        List<Peli> pelit = pepe.annaPelit();
        for (Peli peli : pelit) gridPelit.add(peli, pepe.haeKentat(peli));
    }
    
    /**
     * Hakukentän lukeminen
     */
    private void haku() {
        String ehto = hakuehto.getText(); 
        if ( ehto.isEmpty() )
            naytaIlmoitus("");
        else
            naytaIlmoitus("Haetaan hakuehdolla: " + ehto);
            etsi(ehto);
    }
    
    /**
     * Etsii pelit tietyin ehdoin
     * @param merkkijono Hakuehto, jolla pelejä haetaan
     */
    private void etsi(String merkkijono) {
        gridPelit.clear();        
        List<Peli> pelit = pepe.etsiPelit(merkkijono);
        for (Peli peli : pelit) gridPelit.add(peli, pepe.haeKentat(peli));
    }


    /**
     * Luodaan uusi peli ja avataan muokkausikkuna
     */
    private void uusiPeli() {
        // Nimikkeen luonti
        Nimike nimike = new Nimike("Uusi peli");
        nimike.rekisteroi();
            
        // Pelien luonti ja nimikkeen sitominen
        Peli uusi = new Peli(nimike.getTunniste(), 0);
        uusi.rekisteroi();
        
        try {
            pepe.lisaa(nimike);
            pepe.lisaa(uusi);
        } catch (SailoException e) {
            naytaIlmoitus("Virhe pelin luonnissa! " + e.getMessage());
            return;
        }
        
        // Sijoitetaan pelin viite talteen Pepeen
        pepe.setPeliViite(uusi);
        
        // Lipun arvon asetus, jotta voidaan alempana testa muutoksia
        pepe.setLippu(false);
        
        // Siirrytään muokkausikkunaan
        ModalController.showModal(PepeGUIController.class.getResource("PepePeliView.fxml"), "Lisää peli", null, pepe);
        
        // Testataan muokattiinko mitään
        if (pepe.getLippu() == false) {
            pepe.poista(uusi);
            naytaIlmoitus("Pelin muokkaus peruutettu!");
        }
        else naytaIlmoitus("Uusi peli luotu: " + pepe.annaNimike(uusi).getNimi());
        
        // Päivitetään pelilista
        haeGridiin();
    }
    
    
    /**
     * Avataan alusten muokkausdialogi
     */
    private void muokkaaAlustoja() {
        // Lipun arvon asetus, jotta voidaan alempana testa muutoksia
        pepe.setLippu(false);
        
        // Siirrytään muokkausikkunaan ja viedään tiedot kuljettimessa
        ModalController.showModal(PepeGUIController.class.getResource("PepeAlustaView.fxml"), "Muokkaa alustaa", null, pepe);
        
        // Testataan muokattiinko mitään
        if (pepe.getLippu() == false) naytaIlmoitus("Alustojen muokkaus peruutettu!");
        else naytaIlmoitus("Alustojen muokkaus onnistunut!");
        
        // Päivitetään pelilista
        haeGridiin();
    }
    

    /**
     * @param pepe PEPE-olio jota käytetään tässä käyttöliittymässä
     */
    public void setPepe(Pepe pepe) {
        this.pepe = pepe;
        lueTiedosto();
        haeGridiin();
    }

    
    /**
     * Näytetään ohjelman suunnitelma erillisessä selaimessa
     */
    private void avustus() {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/2020k/ht/anaaalle");
            desktop.browse(uri);
        } catch (URISyntaxException e) {
            return;
        } catch (IOException e) {
            return;
        }
    }

}