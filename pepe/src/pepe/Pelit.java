/**
 * 
 */
package pepe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;
import fxPepe.SailoException;

/**
 * @author anssi
 * @version 28 Feb 2020
 *
 */
public class Pelit implements Iterable<Peli> {
    
    
    /** Taulukko PeliTiedoista */
    private final List<Peli> alkiot = new ArrayList<Peli>();


    /**
     * 
     */
    public Pelit() {
        // Toistaiseksi tyhja
    }
    
    
    /**
     * Lisää uuden pelin tietorakenteeseen. Ottaa peliTiedon omistukseensa.
     * @param peli lisättävä peli. Huomio: tietorakenne muuttuu omistajaksi
     */
    public void lisaa(Peli peli) {
        alkiot.add(peli);
    }
    
    
    /**
     * Poistaa pelin
     * @param peli Poistettava peli
     * @return tosi jos löytyi poistettava peli
     */
    public boolean poista(Peli peli) {
        boolean poistettu = alkiot.remove(peli);
        return poistettu;
    }
    
    
    /**
     * Luetaan nimikkeet tiedostosta
     * @throws SailoException Virhe, jos ongelma
     */
    public void lueTiedostosta() throws SailoException {
        File tiedosto = new File("pelit.txt");
        try (Scanner sc = new Scanner(new FileInputStream(tiedosto),StandardCharsets.UTF_8)) {
            while (sc.hasNextLine()) {
                String rivi = sc.nextLine();
                if (rivi.charAt(0) == ';') continue;
                var uusi = new Peli();
                uusi.parsiPeli(rivi);
                lisaa(uusi);            
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Tallentaa pelit tiedostoon  
     * @throws SailoException jos talletus epäonnistuu
     */
    public void tallenna() throws SailoException {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream("pelit.txt", false))) {
            for (Peli peli : this) {
                pw.println(peli.toString());
            }
        } catch (IOException ex) {
            System.err.println("Virhe luettaessa tiedostoa! " + ex.getMessage());
            return;
        }

    }


    /**
     * Palauttaa Pepen pelien lukumäärän
     * @return pelien lukumäärä
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
     * @return Lista peleistä
     */
    public List<Peli> annaPelit() {
        return alkiot;
    }
    
    /**
     * Palauttaa pelin tunnisteen perusteella
     * @param tunniste Haettavan pelin tunniste
     * @return Peli
     */
    public Peli annaPeli(int tunniste) {
        for (Peli peli : alkiot) {
            if (peli.getTunniste() == tunniste) return peli;
        }
        return null;
    }

    
    /**
     * Testiohjelma PeliTiedoille
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Pelit pelit = new Pelit();    
        try {
            pelit.lueTiedostosta();
            for (int i = 0; i < pelit.getLkm(); i++) {
                Peli peli = pelit.annaPeli(i);
                peli.tulosta(System.out);
            }
        } catch (SailoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        /**
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
        */
    }

}
