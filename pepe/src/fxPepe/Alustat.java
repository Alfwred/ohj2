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
public class Alustat implements Iterable<Alusta> {
    
    private String tiedostonNimi = "";
    
    /** Taulukko alustoista */
    private final ArrayList<Alusta> alkiot = new ArrayList<Alusta>();


    /**
     * 
     */
    public Alustat() {
        // Toistaiseksi tyhja
    }
    
    
    /**
     * Lisää uuden alustan tietorakenteeseen. Ottaa alustan omistukseensa.
     * @param alusta lisättävä alusta. Huomio: tietorakenne muuttuu omistajaksi
     */
    public void lisaa(Alusta alusta) {
        alkiot.add(alusta);
    }


    /**
     * Lukee alustakannan tiedostosta 
     * TODO Kesken.
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        tiedostonNimi = hakemisto + ".har";
        throw new SailoException("Ei osata vielä lukea tiedostoa " + tiedostonNimi);
    }


    /**
     * Tallentaa alustakannan tiedostoon 
     * TODO Kesken.
     * @throws SailoException jos talletus epäonnistuu
     */
    public void talleta() throws SailoException {
        throw new SailoException("Ei osata vielä tallettaa tiedostoa " + tiedostonNimi);
    }


    /**
     * Palauttaa Pepen alustojen lukumäärän
     * @return alustojen lukumäärä
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
    public Iterator<Alusta> iterator() {
        return alkiot.iterator();
    }


    /**
     * Haetaan kaikki pelin alustat
     * @param tunniste pelin tunniste, jolle alustoja haetaan
     * @return tietorakenne jossa viiteet löydetteyihin alustoihin
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
    public List<Alusta> annaAlustaTiedot(int tunniste) {
        List<Alusta> loydetyt = new ArrayList<Alusta>();
        for (Alusta tieto : alkiot)
            if (tieto.getAlustanTunniste() == tunniste) loydetyt.add(tieto);
        return loydetyt;
    }


    /**
     * Testiohjelma alustoille
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Alustat alustat = new Alustat();
        Alusta a1 = new Alusta();
        a1.rekisteroi();
        a1.taytaTestiAlustaTiedoilla();
        Alusta a2 = new Alusta();
        a2.rekisteroi();
        a2.taytaTestiAlustaTiedoilla();
        
        a1.tulosta(System.out);
        a2.tulosta(System.out);

        alustat.lisaa(a1);
        alustat.lisaa(a2);
        


        System.out.println("============= Alustat testi =================");
        
        List<Alusta> alustalista = alustat.annaAlustaTiedot(1);
        for (Alusta alusta : alustalista) {
            alusta.tulosta(System.out);
        }

    }

}
