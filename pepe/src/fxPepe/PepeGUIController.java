package fxPepe;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.StringGrid;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import pepe.Alusta;
import pepe.Nimike;
import pepe.Peli;
import pepe.Pepe;
import fxPepe.SailoException;


/**
 * Luokka Pepen käyttöliittymän tapahtumien hoitamiseksi.
 * @author Anssi Lepikko
 * @version 14 Feb 2020
 *
 */
public class PepeGUIController implements Initializable {
    
    @FXML private TextField hakuehto;
    @FXML private Label labelVirhe;
    @FXML private StringGrid<Peli> gridPelit;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
        
     // Mitä tehdään kun hiirellä klikataan. Tämä oli tarpeellinen, kun tulostettin pelin tiedot testikenttään.
     //gridPelit.setOnMouseClicked( e -> { if ( e.getClickCount() == 1 ) naytaGridinValinta(); });
     
    }
    
    @FXML private void handleHakuehto() {
        String hakukentta = hakuehto.getSelectedText();
        String ehto = hakuehto.getText(); 
        if ( ehto.isEmpty() )
            naytaIlmoitus(null);
        else
            naytaIlmoitus("Ei osata vielä hakea " + hakukentta + ": " + ehto);
    }
    
    @FXML void handleInfo() { avustus(); }
    @FXML void handleListaLaskeva() { naytaIlmoitus("Ei toimi vielä!"); }
    @FXML void handleListaNouseva() { naytaIlmoitus("Ei toimi vielä!"); }
    @FXML void handleListaSuodata() { naytaIlmoitus("Ei toimi vielä!"); }
    @FXML void handlePeliLisaa() { uusiPeli(); }
    @FXML void handlePeliMuokkaa() { muokkaaPeli(); }
    @FXML void handlePeliPoista() { poistaPeli(); }
    @FXML void handleAlustaMuokkaa() { muokkaaAlusta(); }
    @FXML void handleRekisteriAvaa() { Dialogs.showMessageDialog("Ei toimi"); }
    @FXML void handleRekisteriTallenna() throws SailoException { tallenna(); }
    @FXML private void handleLopeta() throws SailoException { tallenna(); Platform.exit(); }
    
//===========================================================================================
    
    private Pepe pepe;
    private Peli peliValittu;

    
    /**
     * Tekee tarvittavat muut alustukset, nyt vaihdetaan GridPanen tilalle
     * yksi iso tekstikenttä, johon voidaan tulostaa jäsenten tiedot.
     * Alustetaan myös jäsenlistan kuuntelija 
     */
    protected void alusta() {
        gridPelit.clear();
        
    }

    
    private void naytaIlmoitus(String virhe) {
        if ( virhe == null || virhe.isEmpty() ) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("Virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("Virhe");
    }
    
    
    /**
     * Alustaa pepen lukemalla pelikannan
     */
    protected void lueTiedosto() {
        try {
            pepe.lueTiedostosta();
            naytaIlmoitus("Pelikanta luettu!");
        } catch (SailoException e) {
            e.printStackTrace();
        }
    }

    
    /**
     * Tietojen tallennus
     * @throws SailoException 
     */
    private void tallenna() throws SailoException {
        pepe.tallenna();
        naytaIlmoitus("Pelikanta tallennettu!");
    }


    /**
     * Tarkistetaan onko tallennus tehty
     * @return true jos saa sulkea sovelluksen, false jos ei
     * @throws SailoException Virhe
     */
    public boolean voikoSulkea() throws SailoException {
        tallenna();
        return true;
    }
    
    
    /**
     * Poistaavalitun pelin
     */
    protected void poistaPeli() {
        
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
        hae();
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
        hae(); 
    }
    
    
    /**
     * Hakee pelien tiedot StrinGridiin
     */
    protected void hae() {
        gridPelit.clear();        
        List<Peli> pelit = pepe.annaPelit();
        for (Peli peli : pelit) gridPelit.add(peli, pepe.haeKentat(peli));
    }


    /**
     * Luo uuden pelin, jota aletaan editoimaan
     */
    protected void uusiPeli() {
        
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
        hae();
    }
    
    
    /**
     * Avataan alusten muokkausdialogi
     */
    protected void muokkaaAlusta() {
        
        // Lipun arvon asetus, jotta voidaan alempana testa muutoksia
        pepe.setLippu(false);
        
        // Siirrytään muokkausikkunaan ja viedään tiedot kuljettimessa
        ModalController.showModal(PepeGUIController.class.getResource("PepeAlustaView.fxml"), "Muokkaa alustaa", null, pepe);
        
        // Testataan muokattiinko mitään
        if (pepe.getLippu() == false) naytaIlmoitus("Alustojen muokkaus peruutettu!");
        else naytaIlmoitus("Alustojen muokkaus onnistunut!");
        
        // Päivitetään pelilista
        hae();
    }
    

    /**
     * @param pepe Pepe jota käytetään tässä käyttöliittymässä
     */
    public void setPepe(Pepe pepe) {
        this.pepe = pepe;
        lueTiedosto();
        hae();
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