package fxPepe;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import fxPepe.Peli;
import fxPepe.Pepe;
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
    
    private String pepennimi = "pelirekisteri";

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
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
     */
    @FXML void handleRekisteriTallenna() {
        tallenna();
    }
    
    /**
     * Lopetetaan ohjelma
     */
    @FXML private void handleLopeta() {
        tallenna();
        Platform.exit();
    }
    
    
//===========================================================================================    
// Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia
    
    private Pepe pepe;
    private Peli peliValittu;
    private TextArea areaPeli = new TextArea();
    
    
    /**
     * Tekee tarvittavat muut alustukset, nyt vaihdetaan GridPanen tilalle
     * yksi iso tekstikenttä, johon voidaan tulostaa jäsenten tiedot.
     * Alustetaan myös jäsenlistan kuuntelija 
     */
    protected void alusta() {
        panelPeli.setContent(areaPeli);
        areaPeli.setFont(new Font("Courier New", 12));
        panelPeli.setFitToHeight(true);
        
        chooserPelit.clear();
        chooserPelit.addSelectionListener(e -> naytaPeli());
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
        pepennimi = nimi;
        setTitle("Pepe - " + pepennimi);
        String virhe = "Ei osata lukea vielä";  // TODO: tähän oikea tiedoston lukeminen
        // if (virhe != null) 
            Dialogs.showMessageDialog(virhe);
    }

    /**
     * Tietojen tallennus
     */
    private void tallenna() {
        Dialogs.showMessageDialog("Tallennetetaan! Mutta ei toimi vielä");
    }


    /**
     * Tarkistetaan onko tallennus tehty
     * @return true jos saa sulkea sovelluksen, false jos ei
     */
    public boolean voikoSulkea() {
        tallenna();
        return true;
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
     * Hakee pelien tiedot listaan
     * @param pnro pelin numero, joka aktivoidaan haun jälkeen
     */
    protected void hae(int pnro) {
        chooserPelit.clear();

        int index = 0;
        for (int i = 0; i < pepe.getPeleja(); i++) {
            Peli peli = pepe.annaPeli(i);
            if (peli.getTunniste() == pnro) index = i;
            chooserPelit.add(peli.getNimi(), peli);
        }
        chooserPelit.setSelectedIndex(index); // tästä tulee muutosviesti joka näyttää jäsenen
    }


    /**
     * Luo uuden pelin, jota aletaan editoimaan 
     */
    protected void uusiPeli() {
        Peli uusi = new Peli();
        uusi.rekisteroi();
        uusi.taytaPeliTiedoilla();
        try {
            pepe.lisaa(uusi);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
            return;
        }
        hae(uusi.getTunniste());
    }
    

    /**
     * @param pepe Pepe jota käytetään tässä käyttöliittymässä
     */
    public void setPepe(Pepe pepe) {
        this.pepe = pepe;
        naytaPeli();
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

    /**
     * Tulostaa jäsenen tiedot
     * @param os tietovirta johon tulostetaan
     * @param peli tulostettava jäsen
     */
    public void tulosta(PrintStream os, final Peli peli) {
        os.println("----------------------------------------------");
        peli.tulosta(os);
        os.println("----------------------------------------------");
    }
    
    
    /**
     * Tulostaa listassa olevat jäsenet tekstialueeseen
     * @param text alue johon tulostetaan
     */
    public void tulostaValitut(TextArea text) {
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(text)) {
            os.println("Tulostetaan kaikki jäsenet");
            for (int i = 0; i < pepe.getPeleja(); i++) {
                Peli jasen = pepe.annaPeli(i);
                tulosta(os, jasen);
                os.println("\n\n");
            }
        }
    }
}