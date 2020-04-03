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
    
    private TextArea areaPeli = new TextArea();
    
    private String pepennimi = "pelirekisteri";

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
            naytaVirhe(null);
        else
            naytaVirhe("Ei osata vielä hakea " + hakukentta + ": " + ehto);
    }
    
    /**
     * Avataan info-ikkuna
     */
    @FXML void handleInfo() {
        avustus();
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
        //PepePeliController.lisaaPeli(null, "Tomb Raider II");
        uusiPeli();
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
        //Dialogs.showMessageDialog("Suorita pelin lisäys! Ei toimi vielä!");
        
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
     * @throws SailoException Virhe
     */
    @FXML void handleRekisteriTallenna() throws SailoException {
        tallenna();
    }
    
    /**
     * Lopetetaan ohjelma
     * @throws SailoException 
     */
    @FXML private void handleLopeta() throws SailoException {
        tallenna();
        Platform.exit();
    }
    
    
//===========================================================================================    
// Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia
    
    private Pepe pepe;
    private Peli peliValittu;

    
    
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
        
        chooserPelit.clear();
        chooserPelit.addSelectionListener(e -> naytaPeli());
        
        gridPelit.clear();
        
    }

    
    private void naytaVirhe(String virhe) {
        if ( virhe == null || virhe.isEmpty() ) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
    }
    
    private void setTitle(String title) {
        ModalController.getStage(hakuehto).setTitle(title);
    }
    
    
    /**
     * Alustaa pepen lukemalla sen valitun nimisestä tiedostosta
     * @param nimi tiedosto josta pepen tiedot luetaan
     */
    protected void lueTiedosto(String nimi) {
        try {
            pepe.lueTiedostosta();
            hae();
        } catch (SailoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Tietojen tallennus
     * @throws SailoException 
     */
    private void tallenna() throws SailoException {
        pepe.tallenna();
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
        
        if (peliValittu == null) {
            areaPeli.setText("null");
            return;
        }

        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaPeli)) {
            
            peliValittu.tulosta(os);
            pepe.annaNimike(peliValittu).tulosta(os);
            pepe.annaAlusta(peliValittu).tulosta(os);
            
        }
    }
    
    
    /**
     * Näyttää listasta valitun jäsenen tiedot, tilapäisesti yhteen isoon edit-kenttään
     */
    protected void naytaPeli() {
        peliValittu = chooserPelit.getSelectedObject();
        if (peliValittu == null) return;
        areaPeli.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaPeli)) {
            peliValittu.tulosta(os);   
        }
    }
    
    
    /**
     * Hakee pelien tiedot StrinGridiin
     */
    protected void hae() {
        gridPelit.clear();
        for (int i = 0; i < pepe.getPeleja(); i++) {
            Peli peli = pepe.annaPeli(i);
            gridPelit.add(peli, pepe.getKenttia(peli));
        }
    }


    /**
     * Luo uuden pelin, jota aletaan editoimaan
     */
    protected void uusiPeli() {
        
        // Alustojen testiluonti
        Alusta alusta = new Alusta();
        alusta.rekisteroi();
        alusta.taytaAlustaTiedoilla();
         
        // Nimikkeiden testiluonti
        Nimike nimike = new Nimike();
        nimike.rekisteroi();
        nimike.taytaNimikeTiedoilla();
            
        // Pelien luonti, nimikkeiden ja alustojen sitominen
        Peli uusi = new Peli(0);
        uusi.rekisteroi();
        uusi.taytaTestiPeliTiedoilla(nimike.getTunniste(), alusta.getTunniste());
        uusi.tulosta(System.out);
        
        try {
            pepe.lisaa(alusta);
            pepe.lisaa(nimike);
            pepe.lisaa(uusi);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
            return;
        }
        hae();
    }
    

    /**
     * @param pepe Pepe jota käytetään tässä käyttöliittymässä
     */
    public void setPepe(Pepe pepe) {
        this.pepe = pepe;
        naytaPeli();
        lueTiedosto("tiedostonimi");
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