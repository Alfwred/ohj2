/**
 * 
 */
package fxPepe;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author anssi
 * @version 18 Feb 2020
 *
 */
public class Alusta {
    
    private int     aTunniste;              // Alustan id (järjestysnumero)
    private String  lyhenne;                // Pelin lyhenne
    private String  nimi;                   // Pelin koko nimi
    
    private static int seuraavaTunniste = 1000;

    /**
     * 
     */
    public Alusta() {
        // Myöhemmin
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
        
        if (this.aTunniste == 1000) {
            lyhenne = "GC";
            nimi = "Gamecube";
        }
        if (this.aTunniste == 1001) {
            lyhenne = "N64";
            nimi = "Nintendo 64";
        }
        if (this.aTunniste == 1002) {
            lyhenne = "PS1";
            nimi = "PlaySation";
        }
        if (this.aTunniste == 1003) {
            lyhenne = "PS2";
            nimi = "PlaySation 2";
        }
        if (this.aTunniste == 1004) {
            lyhenne = "PS4";
            nimi = "PlaySation 4";
        }

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
        return  this.lyhenne;
    }
    
    /**
     * Antaa alustalle seuraavan tunnistenumeron
     * @return aTunniste Uusi tunnusnumero
     * @example
     * <pre name="test">
     *   Alusta a1 = new Alusta();
     *   a1.getAlustanTunniste() === 0;
     *   a1.rekisteroi();
     *   Alusta a2 = new Alusta();
     *   a2.rekisteroi();
     *   int n1 = a1.getAlustanTunniste();
     *   int n2 = a2.getAlustanTunniste();
     *   n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        aTunniste = seuraavaTunniste;
        seuraavaTunniste++;
        return aTunniste;
    }
    
    
    /**
     * Palauttaa alustan oman tunniste id:n
     * @return alustan aTunniste id
     */
    public int getTunniste() {
        return aTunniste;
    }
    
    
    /**
     * Palauttaa alustan nimen (lyhenne)
     * @return Alustan nimi
     */
    public String getNimi() {
        return lyhenne;
    }
    
      
    /**
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
