package fxPepe;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.*;


/**
 * PEPE:n controlleri
 * @author anssi
 * @version 14 Feb 2020
 *
 */
public class PepeGUIController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        // TODO
    }
    
    /**
     * Avataan info-ikkuna
     */
    @FXML void handleInfo() {
        Dialogs.showMessageDialog("Info! Ei toimi vielä!");
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
        String kerhonNimi = PepePeliController.kysyNimi(null, "Tomb Raider II");
        Dialogs.showMessageDialog(kerhonNimi);
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
        Dialogs.showMessageDialog("Suorita pelin lisäys! Ei toimi vielä!");
        
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

    /**
     * Tietojen tallennus
     */
    private void tallenna() {
        Dialogs.showMessageDialog("Tallennetetaan! Mutta ei toimi vielä");
    }
    
    /**
     * Tarkistetaan onko tallennus tehty
     * @return true jos saa sulkaa sovelluksen, false jos ei
     */
    public boolean voikoSulkea() {
        tallenna();
        return true;
    }
    
  //===========================================================================================    
 // Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia    
     
     private Kerho kerho;
     private Jasen jasenKohdalla;
     private TextArea areaJasen = new TextArea();
     
     
     /**
      * Tekee tarvittavat muut alustukset, nyt vaihdetaan GridPanen tilalle
      * yksi iso tekstikenttä, johon voidaan tulostaa jäsenten tiedot.
      * Alustetaan myös jäsenlistan kuuntelija 
      */
     protected void alusta() {
         panelJasen.setContent(areaJasen);
         areaJasen.setFont(new Font("Courier New", 12));
         panelJasen.setFitToHeight(true);
         
         chooserJasenet.clear();
         chooserJasenet.addSelectionListener(e -> naytaJasen());
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
      * Alustaa kerhon lukemalla sen valitun nimisestä tiedostosta
      * @param nimi tiedosto josta kerhon tiedot luetaan
      */
     protected void lueTiedosto(String nimi) {
         kerhonnimi = nimi;
         setTitle("Kerho - " + kerhonnimi);
         String virhe = "Ei osata lukea vielä";  // TODO: tähän oikea tiedoston lukeminen
         // if (virhe != null) 
             Dialogs.showMessageDialog(virhe);
     }

     
     /**
      * Kysytään tiedoston nimi ja luetaan se
      * @return true jos onnistui, false jos ei
      */
     public boolean avaa() {
         String uusinimi = KerhonNimiController.kysyNimi(null, kerhonnimi);
         if (uusinimi == null) return false;
         lueTiedosto(uusinimi);
         return true;
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
     protected void naytaJasen() {
         jasenKohdalla = chooserJasenet.getSelectedObject();

         if (jasenKohdalla == null) return;

         areaJasen.setText("");
         try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaJasen)) {
             jasenKohdalla.tulosta(os);
         }
     }


     /**
      * Hakee jäsenten tiedot listaan
      * @param jnro jäsenen numero, joka aktivoidaan haun jälkeen
      */
     protected void hae(int jnro) {
         chooserJasenet.clear();

         int index = 0;
         for (int i = 0; i < kerho.getJasenia(); i++) {
             Jasen jasen = kerho.annaJasen(i);
             if (jasen.getTunnusNro() == jnro) index = i;
             chooserJasenet.add(jasen.getNimi(), jasen);
         }
         chooserJasenet.setSelectedIndex(index); // tästä tulee muutosviesti joka näyttää jäsenen
     }


     /**
      * Luo uuden jäsenen jota aletaan editoimaan 
      */
     protected void uusiJasen() {
         Jasen uusi = new Jasen();
         uusi.rekisteroi();
         uusi.vastaaAkuAnkka();
         try {
             kerho.lisaa(uusi);
         } catch (SailoException e) {
             Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
             return;
         }
         hae(uusi.getTunnusNro());
     }
     

     /**
      * @param kerho Kerho jota käytetään tässä käyttöliittymässä
      */
     public void setKerho(Kerho kerho) {
         this.kerho = kerho;
         naytaJasen();
     }

     
     /**
      * Näytetään ohjelman suunnitelma erillisessä selaimessa.
      */
     private void avustus() {
         Desktop desktop = Desktop.getDesktop();
         try {
             URI uri = new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/2019k/ht/vesal");
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
      * @param jasen tulostettava jäsen
      */
     public void tulosta(PrintStream os, final Jasen jasen) {
         os.println("----------------------------------------------");
         jasen.tulosta(os);
         os.println("----------------------------------------------");
     }
     
     /**
      * Tulostaa listassa olevat jäsenet tekstialueeseen
      * @param text alue johon tulostetaan
      */
     public void tulostaValitut(TextArea text) {
         try (PrintStream os = TextAreaOutputStream.getTextPrintStream(text)) {
             os.println("Tulostetaan kaikki jäsenet");
             for (int i = 0; i < kerho.getJasenia(); i++) {
                 Jasen jasen = kerho.annaJasen(i);
                 tulosta(os, jasen);
                 os.println("\n\n");
             }
         }
     }

}