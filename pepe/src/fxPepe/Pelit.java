/**
 * 
 */
package fxPepe;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author anssi
 * @version 28 Feb 2020
 *
 */
public class Pelit implements Iterable<Peli> {
    
    private String tiedostonNimi = "";
    
    /** Taulukko PeliTiedoista */
    private final ArrayList<Peli> alkiot = new ArrayList<Peli>();


    /**
     * 
     */
    public Pelit() {
        // Toistaiseksi tyhja
    }
    
    
    /**
     * Lisää uuden peliTiedon tietorakenteeseen. Ottaa peliTiedon omistukseensa.
     * @param tieto lisättävä peliTieto. Huomio: tietorakenne muuttuu omistajaksi
     */
    public void lisaa(Peli tieto) {
        alkiot.add(tieto);
    }


    /**
     * Lukee pelitietokannan tiedostosta 
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta1(String hakemisto) throws SailoException {
        tiedostonNimi = hakemisto + ".txt";
        throw new SailoException("Ei osata vielä lukea tiedostoa " + tiedostonNimi);
    }
    
    
    /**
     * Tallentaa pelitietokannan tiedostoon  
     * TODO Kesken.
     * @throws SailoException jos talletus epäonnistuu
     */
    public void talleta() throws SailoException {
        throw new SailoException("Ei osata vielä tallettaa tiedostoa " + tiedostonNimi);
    }


    /**
     * Palauttaa Pepen PeliTietojen lukumäärän
     * @return PeliTietojen lukumäärä
     */
    public int getLkm() {
        return alkiot.size();
    }


    /**
     * Iteraattori kaikkien peliTietojen läpikäymiseen
     * @return peliTietoiteraattori
     * 
     * @example
     * <pre name="test">
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     *  Pelit pelit = new Pelit();
     *  Peli p21 = new Peli(1); pelit.lisaa(p21);
     *  Peli p11 = new Peli(2); pelit.lisaa(p11);
     *  Peli p22 = new Peli(3); pelit.lisaa(p22);
     *  Peli p12 = new Peli(4); pelit.lisaa(p12);
     *  Peli p23 = new Peli(5); pelit.lisaa(p23);
     * 
     *  Iterator<Peli> i2=pelit.iterator();
     *  i2.next() === p21;
     *  i2.next() === p11;
     *  i2.next() === p22;
     *  i2.next() === p12;
     *  i2.next() === p23;
     *  i2.next() === p12;  #THROWS NoSuchElementException  
     *  
     *  int n = 0;
     *  int pnrot[] = {1,2,3,4,5};
     *  
     *  for ( Peli peli:pelit ) { 
     *    peli.getTunniste() === pnrot[n]; n++;  
     *  }
     *  
     *  n === 5;
     *  
     * </pre>
     */
    @Override
    public Iterator<Peli> iterator() {
        return alkiot.iterator();
    }
    
    
    /**
     * Palauttaa pelin tunnisteen perusteella
     * @param tunniste Haettavan pelin tunniste
     * @return Peli
     */
    public Peli annaPeli(int tunniste) {
        for (Peli peli : alkiot)
            if (peli.getTunniste() == tunniste) return peli;
        return null;
    }
    

    /**
     * Testiohjelma PeliTiedoille
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Pelit pelit = new Pelit();
        
        Peli peli1 = new Peli();
        peli1.rekisteroi();
        peli1.taytaPeliTiedoilla();
        
        Peli peli2 = new Peli();
        peli2.rekisteroi();
        peli2.taytaPeliTiedoilla();
        
        
        pelit.lisaa(peli1);
        pelit.lisaa(peli2);
        
        System.out.println("Haetaan pelejä pelin tunnisteen perusteella:");    
        pelit.annaPeli(1).tulosta(System.out);
        
    }

}
