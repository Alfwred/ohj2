package fxPepe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import fi.jyu.mit.fxgui.Dialogs;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Kystään kerhon nimi ja luodaan tätä varten dialogi.
 *
 * @author vesal
 * @version 2.1.2016
 */
public class PepePeliController implements Initializable {
    
    @FXML void handlePeliMuokkaaSuorita() {
        Dialogs.showMessageDialog("Suorita pelin lisäys! Ei toimi vielä!");
        //close();
    }

    @FXML private TextField peliNimiMuokkaa;
    private String vastaus = "Tallentaisi, mutta ei vielä tallenna!";


    private String getResult() {
        return vastaus;
    }


    private void setDefault(String oletus) {
        peliNimiMuokkaa.setText(oletus);
    }


    //private void close() {
    //    ((Stage)textVastaus.getScene().getWindow()).close();
    //}


    //@FXML private void handleOK() {
    //    vastaus = textVastaus.getText();
    //    close();
    //}


    //@FXML private void handleCancel() {
    //    close();
    //}


    /**
     * Mitä tehdään kun dialogi on näytetty
     */
    public void handleShown() {
        peliNimiMuokkaa.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        //
    }


    /**
     * Luodaan nimenkysymisdialogi ja palautetaan siihen kirjoitettu nimi tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mitä nimeä näytetään oletuksena
     * @return null jos painetaan Cancel, muuten kirjoitettu nimi
     */
    public static String kysyNimi(Stage modalityStage, String oletus) {
        try {
            // Selvitetään tiedoston osoite
            URL url = PepePeliController.class.getResource("PepePeliView.fxml");
            // Luodaan olio joka lataa tiedoston
            FXMLLoader ldr = new FXMLLoader(url);
            // Ja ladataan tiedoston kuvaama ulkoasu
            Parent root =  ldr.load();
            // selvitetään luotu kontrolleriolio
            final PepePeliController dialogCtrl = (PepePeliController)ldr.getController();
            // luodaan uusi ikkuna
            Stage stage = new Stage();
            // ja sen sisällä näkymä, joka näyttää suunnitellun ulkoasun
            stage.setScene(new Scene(root));
            // Vaihdetaan ikkunan otsikkkoa
            stage.setTitle("Kerho");
            // Jos on annettu ikkuna jolle ollaa modaaleja, asetetaan modaalisuus suhteessa
            if ( modalityStage != null ) {
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(modalityStage);
            } else  // Muutan ollaan modaalisia sovellukseööe
                stage.initModality(Modality.APPLICATION_MODAL);
            // Pyydetään dialogia laittamaan oletustiedot näkyville
            dialogCtrl.setDefault(oletus);
            // laitetaan ikkunalle käsittelijä siitä kun se näytetään
            stage.setOnShown((e)-> {
                // ja annetaan käyttö liittymän päättää mikä komponentti oletukseksi
                dialogCtrl.handleShown();
            });
            // Pistetään dialogi näkyville ja odotetaan kunnes se suljetaan
            stage.showAndWait();
            // Kysytään käyttöliittymältä tulos ja palautetaan se
            return dialogCtrl.getResult();
        } catch (IOException e) {
            System.err.println(e.toString());
            return null;
        }
    }
}