/**
 * 
 */
package pepe;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Luokka pelien alustoista.
 * @author Anssi Lepikko
 * @version 23.4.2020
 *
 */
public class Alusta {
    
    private int     aTunniste;  // Alustan id (järjestysnumero)
    private String  lyhenne;    // Pelin lyhenne
    private String  nimi;       // Pelin koko nimi
    
    private static int seuraavaTunniste = 0;
    
    /**
     * Oletusmuodostaja
     */
    public Alusta() {
        // Ei tarvetta
    }
    
    /**
     * Alustetaan alusta
     * @param lyhenne Alustan lyhenne
     * @param nimi Alustan nimi
     */
    public Alusta(String lyhenne, String nimi) {
        this.lyhenne = lyhenne;
        this.nimi = nimi;
    }
    
    
    /**
     * Alustetaan alusta
     * @param aTunniste alustan tunniste
     */
    public Alusta(int aTunniste) {
        this.aTunniste = aTunniste;
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot alustalle
     */
    public void taytaTestiAlustaTiedoilla() {
            lyhenne = "TESTI";
            nimi = "Testialusta";
    }
    
    
    /**
    * Apumetodi, jolla saadaan täytettyä testiarvot alustalle
    * Tunniste arvotaan, jotta kahdella alustalla olisi
    * samoja tietoja.
    */
    public void taytaAlustaTiedoilla() {
        taytaTestiAlustaTiedoilla();
    }
    
    
    /**
     * Lukee alustan merkkijonosyötteestä
     * @param merkkijono Syote, mikä muutetaan Nimike-olioksi
     * @example
     * <pre name="test">
     * Alusta alusta = new Alusta();
     * alusta.parsiAlusta("10|PS2|PlayStation 2");
     * alusta.getTunniste() === 10;
     * alusta.getLyhenne() === "PS2";
     * alusta.getNimi() === "PlayStation 2";
     * </pre>
     */
    public void parsiAlusta(String merkkijono) {
        // https://regex101.com/r/8faG5b/4/
        Pattern esiintyma = Pattern.compile("^(\\d+)\\|(.+)\\|(.+)$");
        Matcher etsija = esiintyma.matcher(merkkijono);
        if (etsija.find()) {
            setTunniste(Integer.parseInt(etsija.group(1)));
            this.lyhenne = etsija.group(2);
            this.nimi = etsija.group(3);
        }
    }
    
    
    /**
     * Tarkistaa syötetyn merkkijonon oikeellisuuden
     * @param merkkijono Mitä tarkastellaan
     * @return Tosi, jos oikeellinen ja epätosi, jos väärä
     * @example
     * <pre name="test">
     * Alusta alusta = new Alusta();
     * alusta.tarkista("|") === false;
     * alusta.tarkista("") === false;
     * alusta.tarkista("PlayStation|") === false;
     * alusta.tarkista("PlayStation") === true;
     * </pre>
     */
    public boolean tarkista(String merkkijono) {
        // Tyhjän merkkijonon käsittely
        if (merkkijono.equalsIgnoreCase("")) return false;
        
        // Palautetaan false, jos merkkijonossa esiintyy tolppamerkki
        Pattern esiintyma = Pattern.compile("\\|");
        Matcher etsija = esiintyma.matcher(merkkijono);
        if (etsija.find()) return false;
        return true;
    }
    
    
    /**
    * Tulostetaan alustan tiedot
    * @param out tietovirta johon tulostetaan
    */
    public void tulosta(PrintStream out) {
        out.println(this.aTunniste + "|" + this.lyhenne + " " + this.nimi);
    }
    
    
    /**
    * Tulostetaan alusta
    * @param os tietovirta johon tulostetaan
    */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    @Override
    public String toString() {
        return this.lyhenne;
    }
    
    /**
     * Antaa alustalle seuraavan tunnistenumeron
     * @return aTunniste Uusi tunnusnumero
     * @example
     * <pre name="test">
     *   Alusta a1 = new Alusta();
     *   a1.getTunniste() === 0;
     *   a1.rekisteroi();
     *   Alusta a2 = new Alusta();
     *   a2.rekisteroi();
     *   int n1 = a1.getTunniste();
     *   int n2 = a2.getTunniste();
     *   n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        this.aTunniste = seuraavaTunniste;
        seuraavaTunniste++;
        return this.aTunniste;
    }
    
    
    /**
     * Asettaa tunnusnumeron ja samalla varmistaa että
     * seuraava numero on aina suurempi kuin tähän mennessä suurin.
     * @param id asetettava tunnusnumero
     */
    private void setTunniste(int id) {
        this.aTunniste = id;
        if ( this.aTunniste >= seuraavaTunniste ) seuraavaTunniste = this.aTunniste + 1;
    }
    
    
    /**
     * Palauttaa alustan oman tunniste id:n
     * @return alustan aTunniste id
     */
    public int getTunniste() {
        return this.aTunniste;
    }
    
    
    /**
     * Palauttaa alustan nimen (lyhenne)
     * @return Alustan nimi
     */
    public String getNimi() {
        return this.nimi;
    }
    
    
    /**
     * Palauttaa alustan nimen
     * @return Alustan lyhenne
     */
    public String getLyhenne() {
        return this.lyhenne;
    }
    
    
    /**
     * Asettaa alustan lyhenteen
     * @param merkkijono Lyhenne
     */
    public void setLyhenne(String merkkijono) {
        this.lyhenne = merkkijono;
    }
    
    
    /**
     * Asettaa alustan nimen
     * @param merkkijono Lyhenne
     */
    public void setNimi(String merkkijono) {
        this.nimi = merkkijono;
    }
    
    
    /**
     * Tuloste olion tallentamista varten
     * @return Merkkijono oliosta
     */
    public String annaTallennusMuoto() {
        return this.aTunniste + "|" + this.lyhenne + "|" + this.nimi;
    }
    
      
    /**
     * Testipääohjelma alusta-luokalle
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        Alusta a1 = new Alusta();
        Alusta a2 = new Alusta();
        a1.rekisteroi();
        a2.rekisteroi();
        a1.tulosta(System.out);
        a1.taytaAlustaTiedoilla();
        a1.tulosta(System.out);
        a2.tulosta(System.out);
        a2.taytaAlustaTiedoilla();
        a2.tulosta(System.out);
    
    }

}
