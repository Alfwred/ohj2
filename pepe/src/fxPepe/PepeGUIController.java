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
    @FXML private ScrollPane panelPeli;
    @FXML private ListChooser<Peli> chooserPelit;
    @FXML private StringGrid<Peli> gridPelit;
    @FXML private TextArea areaPeli = new TextArea();

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
        
     // Mitä tehdään kun hiirellä klikataan
     gridPelit.setOnMouseClicked( e -> { if ( e.getClickCount() == 1 ) naytaGridinValinta(); });
     
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
    
    @FXML void handleListaLaskeva() { Dialogs.showMessageDialog("Lista laskeva! Ei toimi vielä!"); }
    
    @FXML void handleListaNouseva() { Dialogs.showMessageDialog("Lista nouseva! Ei toimi vielä!"); }

    @FXML void handleListaSuodata() { Dialogs.showMessageDialog("Suodata lista! Ei toimi vielä!"); }
    
    @FXML void handlePeliLisaa() { uusiPeli(); }
    
    @FXML void handlePeliMuokkaa() { muokkaaPeli(); }
    
    @FXML void handlePeliPoista() { poistaPeli(); }
    
    @FXML void handleAlustaLisaa() { uusiAlusta(); }
    
    @FXML void handleAlustaPoista() { uusiAlusta(); }
    
    @FXML void handleRekisteriAvaa() { Dialogs.showMessageDialog("Ei toimi"); }
    
    @FXML void handleRekisteriTallenna() throws SailoException { tallenna(); }
    
    @FXML private void handleLopeta() throws SailoException { tallenna(); Platform.exit(); }
    
//===========================================================================================
    
    private Pepe pepe;
    private Peli peliValittu;
    private Object[] kuljetin = new Object[3];

    
    /**
     * Tekee tarvittavat muut alustukset, nyt vaihdetaan GridPanen tilalle
     * yksi iso tekstikenttä, johon voidaan tulostaa jäsenten tiedot.
     * Alustetaan myös jäsenlistan kuuntelija 
     */
    protected void alusta() {
        panelPeli.setContent(areaPeli);
        areaPeli.setFont(new Font("Courier New", 12));
        panelPeli.setFitToHeight(true);
        panelPeli.setFitToWidth(true);        
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
     * Näyttää listasta valitun pelin tiedot, tilapäisesti yhteen isoon edit-kenttään
     */
    private void naytaGridinValinta() {
        areaPeli.setText("");
        peliValittu = gridPelit.getObject();    
        if (peliValittu == null) return;

        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaPeli)) {
            
            peliValittu.tulosta(os);
            pepe.annaNimike(peliValittu).tulosta(os);
            pepe.annaAlusta(peliValittu).tulosta(os);
            
        }
    }
    
    
    /**
     * 
     */
    protected void poistaPeli() {
        peliValittu = gridPelit.getObject();
        if (peliValittu == null) return;
        
        naytaIlmoitus("Peli poistettu! " + pepe.annaNimike(peliValittu).getNimi());
        pepe.poista(peliValittu);

        hae();
    }
    
    /**
     * Pakkaa tarvittavat oliot, lähettää ja avaa ne muokkausdialogissa
     */
    private void muokkaaPeli() {
        
        // Tarvittavat oliot kuljettimeen
        kuljetin[0] = pepe;
        kuljetin[1] = peliValittu;
        kuljetin[2] = false;
        
        // Siirrytään muokkausikkunaan ja viedään tiedot kuljettimessa
        ModalController.showModal(PepeGUIController.class.getResource("PepePeliView.fxml"), "Muokkaa peliä", null, kuljetin);
        
        // Mitä muokkaamisen jälkeen
        if ((boolean)kuljetin[2] == false) naytaIlmoitus("Pelin muokkaus peruutettu!");
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
        
        // Tarvittavat oliot kuljettimeen
        kuljetin[0] = pepe;
        kuljetin[1] = uusi;
        kuljetin[2] = false;
        
        // Siirrytään muokkausikkunaan ja viedään tiedot kuljettimessa
        ModalController.showModal(PepeGUIController.class.getResource("PepePeliView.fxml"), "Lisää peli", null, kuljetin);
        
        // Poistetaan juuri luotu peli, jos muokkausvaihe perutetaan
        if ((boolean)kuljetin[2] == false) {
            naytaIlmoitus("Pelin luonti peruutettu!");
            pepe.poista(uusi);
        }
        else naytaIlmoitus("Uusi peli luotu! " + pepe.annaNimike(uusi).getNimi());
        
        // Päivitetään pelilista
        hae();
    }
    
    
    /**
     * Lisätään uusi alusta
     */
    protected void uusiAlusta() {
        Alusta uusi = new Alusta("UUSI", "Uusi Alusta");
        uusi.rekisteroi();
        pepe.lisaa(uusi);
        
        // Tarvittavat oliot kuljettimeen
        kuljetin[0] = pepe;
        kuljetin[1] = uusi;
        kuljetin[2] = false;
        
        // Siirrytään muokkausikkunaan ja viedään tiedot kuljettimessa
        ModalController.showModal(PepeGUIController.class.getResource("PepeAlustaView.fxml"), "Muokkaa alustaa", null, kuljetin);
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
     * Näytetään ohjelman suunnitelma erillisessä selaimessa.
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