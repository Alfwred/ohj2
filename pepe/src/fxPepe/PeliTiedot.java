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
public class PeliTiedot implements Iterable<PeliTieto> {
    
    private String tiedostonNimi = "";
    
    /** Taulukko PeliTiedoista */
    private final Collection<PeliTieto> alkiot = new ArrayList<PeliTieto>();


    /**
     * 
     */
    public PeliTiedot() {
        // Toistaiseksi tyhja
    }
    
    
    /**
     * Lisää uuden peliTiedon tietorakenteeseen. Ottaa peliTiedon omistukseensa.
     * @param tieto lisättävä peliTieto. Huomio: tietorakenne muuttuu omistajaksi
     */
    public void lisaa(PeliTieto tieto) {
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
     * Tallentaa pelitietokannan tiedostoon.  
     * TODO Kesken.
     * @throws SailoException jos talletus epäonnistuu
     */
    public void talleta() throws SailoException {
        throw new SailoException("Ei osata vielä tallettaa tiedostoa " + tiedostonNimi);
    }


    /**
     * Palauttaa Pepen PeliTietojen lukumäärän
     * @return harrastusten lukumäärä
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
     *  PeliTiedot tiedot = new PeliTiedot();
     *  PeliTieto tieto21 = new PeliTieto(2); tiedot.lisaa(tieto21);
     *  PeliTieto tieto11 = new PeliTieto(1); tiedot.lisaa(tieto11);
     *  PeliTieto tieto22 = new PeliTieto(2); tiedot.lisaa(tieto22);
     *  PeliTieto tieto12 = new PeliTieto(1); tiedot.lisaa(tieto12);
     *  PeliTieto tieto23 = new PeliTieto(2); tiedot.lisaa(tieto23);
     * 
     *  Iterator<PeliTieto> i2=tiedot.iterator();
     *  i2.next() === tieto21;
     *  i2.next() === tieto11;
     *  i2.next() === tieto22;
     *  i2.next() === tieto12;
     *  i2.next() === tieto23;
     *  i2.next() === tieto12;  #THROWS NoSuchElementException  
     *  
     *  int n = 0;
     *  int pnrot[] = {2,1,2,1,2};
     *  
     *  for ( PeliTieto tieto:tiedot ) { 
     *    tieto.getPelinTunniste() === pnrot[n]; n++;  
     *  }
     *  
     *  n === 5;
     *  
     * </pre>
     */
    @Override
    public Iterator<PeliTieto> iterator() {
        return alkiot.iterator();
    }


    /**
     * Haetaan kaikki pelin peliTiedot
     * @param tunniste pelin tunniste, jolle harrastuksia haetaan
     * @return tietorakenne jossa viiteet löydetteyihin peliTietoihin
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     *  PeliTiedot tiedot = new PeliTiedot();
     *  PeliTieto tieto21 = new PeliTieto(2); tiedot.lisaa(tieto21);
     *  PeliTieto tieto11 = new PeliTieto(1); tiedot.lisaa(tieto11);
     *  PeliTieto tieto22 = new PeliTieto(2); tiedot.lisaa(tieto22);
     *  PeliTieto tieto12 = new PeliTieto(1); tiedot.lisaa(tieto12);
     *  PeliTieto tieto23 = new PeliTieto(2); tiedot.lisaa(tieto23);
     *  PeliTieto tieto51 = new PeliTieto(5); tiedot.lisaa(tieto51);
     *  
     *  List<PeliTieto> loytyneet;
     *  loytyneet = tiedot.annaPeliTiedot(3);
     *  loytyneet.size() === 0; 
     *  loytyneet = tiedot.annaPeliTiedot(1);
     *  loytyneet.size() === 2; 
     *  loytyneet.get(0) == tieto11 === true;
     *  loytyneet.get(1) == tieto12 === true;
     *  loytyneet = tiedot.annaPeliTiedot(5);
     *  loytyneet.size() === 1; 
     *  loytyneet.get(0) == tieto51 === true;
     * </pre> 
     */
    public List<PeliTieto> annaPeliTiedot(int tunniste) {
        List<PeliTieto> loydetyt = new ArrayList<PeliTieto>();
        for (PeliTieto tieto : alkiot)
            if (tieto.getPelinTunniste() == tunniste) loydetyt.add(tieto);
        return loydetyt;
    }


    /**
     * Testiohjelma PeliTiedoille
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        PeliTiedot pelientiedot = new PeliTiedot();
        PeliTieto peli1 = new PeliTieto();
        peli1.taytaTestiPeliTietoTiedoilla(2);
        PeliTieto peli2 = new PeliTieto();
        peli2.taytaTestiPeliTietoTiedoilla(1);

        pelientiedot.lisaa(peli1);
        pelientiedot.lisaa(peli2);


        System.out.println("============= PeliTiedot testi =================");
        
        List<PeliTieto> pelientiedot2 = pelientiedot.annaPeliTiedot(1);
        for (PeliTieto tieto : pelientiedot2) {
            tieto.tulosta(System.out);
        }

    }

}
