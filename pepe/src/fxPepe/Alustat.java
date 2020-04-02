/**
 * 
 */
package fxPepe;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
     * Palauttaa alustojen lukumäärän
     * @return alustojen lukumäärä
     */
    public int getLkm() {
        return alkiot.size();
    }


    /**
     * Iteraattori kaikkien alustojen läpikäymiseen
     * @return alustojen iteraattori
     * 
     * @example
     * <pre name="test">
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     *  Alustat alustat = new Alustat();
     *  Alusta alusta21 = new Alusta(1); alustat.lisaa(alusta21);
     *  Alusta alusta11 = new Alusta(2); alustat.lisaa(alusta11);
     *  Alusta alusta22 = new Alusta(3); alustat.lisaa(alusta22);
     *  Alusta alusta12 = new Alusta(4); alustat.lisaa(alusta12);
     *  Alusta alusta23 = new Alusta(5); alustat.lisaa(alusta23);
     * 
     *  Iterator<Alusta> i2=alustat.iterator();
     *  i2.next() === alusta21;
     *  i2.next() === alusta11;
     *  i2.next() === alusta22;
     *  i2.next() === alusta12;
     *  i2.next() === alusta23;
     *  i2.next() === alusta11;  #THROWS NoSuchElementException  
     *  
     *  int n = 0;
     *  int anrot[] = {1,2,3,4,5};
     *  
     *  for (Alusta alusta:alustat) { 
     *    alusta.getTunniste() === anrot[n]; n++;  
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
     * Haetaan tunnistetta vastaava Alusta-olio
     * @param tunniste Alustan tunniste, jolla alustaa haetaan
     * @return Alusta-olio
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     *  Alustat alustat = new Alustat();
     *  Alusta a1 = new Alusta(); alustat.lisaa(a1);
     *  Alusta a2 = new Alusta(); alustat.lisaa(a2);
     *  a1.rekisteroi(); a1.taytaAlustaTiedoilla();
     *  a2.rekisteroi(); a2.taytaAlustaTiedoilla();
     *  
     *  alustat.annaAlusta(a1.getTunniste()).getTunniste() === a1.getTunniste();
     *  alustat.annaAlusta(a2.getTunniste()).getTunniste() === a2.getTunniste();
     * </pre> 
     */
    public Alusta annaAlusta(int tunniste) {
        for (Alusta alusta : alkiot)
            if (alusta.getTunniste() == tunniste) return alusta;
        return null;
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
        
        Alusta a3 = new Alusta();
        a3.rekisteroi();
        a3.taytaTestiAlustaTiedoilla();
        
        a1.tulosta(System.out);
        a2.tulosta(System.out);
        a3.tulosta(System.out);

        alustat.lisaa(a1);
        alustat.lisaa(a2);
        alustat.lisaa(a3);
        
        System.out.println("Alustojen lukumäärä: " + alustat.getLkm());
        System.out.println("Haetaan alustoja alustan tunnisteen perusteella:");
        
        alustat.annaAlusta(a1.getTunniste()).tulosta(System.out);
        alustat.annaAlusta(a2.getTunniste()).tulosta(System.out);
        alustat.annaAlusta(a3.getTunniste()).tulosta(System.out);
    }

}
