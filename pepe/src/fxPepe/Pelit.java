/**
 * 
 */
package fxPepe;

import java.util.*;

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
     * TODO Kesken.
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        tiedostonNimi = hakemisto + ".har";
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
     * TODO: Tämän merkitys on vielä epäselvä
     * Haetaan tunnisteen mukainen peli
     * @param tunniste pelin tunniste, jolle pelin tietoja haetaan
     * @return tietorakenne jossa viiteet löydetteyihin peleihin
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     *  Pelit pelit = new Pelit();
     *  
     *  Peli peli21 = new Peli();
     *  peli21.rekisteroi();
     *  peli21.taytaPeliTiedoilla();
     *  pelit.lisaa(peli21);
     *  
     *  Peli peli11 = new Peli();
     *  peli11.rekisteroi();
     *  peli11.taytaPeliTiedoilla();
     *  pelit.lisaa(peli11);
     *  
     *  Peli peli12 = new Peli();
     *  peli12.rekisteroi();
     *  peli12.taytaPeliTiedoilla();
     *  pelit.lisaa(peli12);
     *  
     *  Peli peli25 = new Peli();
     *  peli25.rekisteroi();
     *  peli25.taytaPeliTiedoilla();
     *  pelit.lisaa(peli25);
     *  
     *  Peli peli51 = new Peli();
     *  peli51.rekisteroi();
     *  peli51.taytaPeliTiedoilla();
     *  pelit.lisaa(peli51);
     *  
     *  List<Peli> loytyneet;
     *  loytyneet = pelit.annaPeli(1);
     *  loytyneet.size() === 1; 
     *  loytyneet = pelit.annaPeli(2);
     *  loytyneet.size() === 1; 
     *  loytyneet.get(0) == peli11 === true;
     *  loytyneet = pelit.annaPeli(5);
     *  loytyneet.size() === 1; 
     *  loytyneet.get(0) == peli51 === true;
     * </pre> 
    public List<Peli> annaPeli(int tunniste) {
        List<Peli> loydetyt = new ArrayList<Peli>();
        for (Peli peli : alkiot)
            if (peli.getTunniste() == tunniste) loydetyt.add(peli);
        return loydetyt;
    }
    */
    
    
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
