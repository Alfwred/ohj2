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
 * Tietorakenneluokka alusta-olioille
 * @author Anssi Lepikko
 * @version 23.4.2020
 *
 */
public class Alustat implements Iterable<Alusta> {
    
    /** Taulukko alustoista */
    private final List<Alusta> alkiot = new ArrayList<Alusta>();


    /**
     * Oletusmuodostaja
     */
    public Alustat() {
        // Ei tarvetta
    }
    
    
    /**
     * Lisää uuden alustan tietorakenteeseen. Ottaa alustan omistukseensa.
     * @param alusta lisättävä alusta. Huomio: tietorakenne muuttuu omistajaksi
     */
    public void lisaa(Alusta alusta) {
        alkiot.add(alusta);
    }
    
    
    /**
     * Poistaa alustan
     * @param alusta Poistettava alusta
     * @return tosi jos löytyi poistettava alusta
     * Alustat alusta = new Alustat();
     * Alusta alusta = new Alusta(10,"PC","Personal Computer");
     * alustat.lisaa(alusta);
     * alustat.poista(alusta) === true;
     */
    public boolean poista(Alusta alusta) {
        boolean poistettu = alkiot.remove(alusta);
        return poistettu;
    }


    /**
     * Luetaan alustat tiedostosta
     * @throws SailoException Virhe, jos ongelma
     */
    public void lueTiedostosta() throws SailoException {
        File tiedosto = new File("alustat.txt");
        try (Scanner sc = new Scanner(new FileInputStream(tiedosto),StandardCharsets.UTF_8)) {
            while (sc.hasNextLine()) {
                String rivi = sc.nextLine();
                var uusi = new Alusta();
                uusi.parsiAlusta(rivi);
                lisaa(uusi);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /**
     * Tallentaa alustat tiedostoon  
     * @throws SailoException jos talletus epäonnistuu
     */
    public void tallenna() throws SailoException {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream("alustat.txt", false))) {
            for (Alusta alusta : this) {
                pw.println(alusta.annaTallennusMuoto());
            }
        } catch (IOException ex) {
            System.err.println("Virhe luettaessa tiedostoa! " + ex.getMessage());
            return;
        }
    }


    /**
     * Palauttaa alustojen lukumäärän
     * @return alustojen lukumäärä
     */
    public int getLkm() {
        return alkiot.size();
    }


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
     * @return Lista alustoista
     */
    public List<Alusta> annaAlustat() {
        return alkiot;
    }


    /**
     * Testipääohjelma alustat-luokalle
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Alustat alustat = new Alustat();
        
        try {
            alustat.lueTiedostosta();
            
            for (int i = 0; i < alustat.getLkm(); i++) {
                Alusta alusta = alustat.annaAlusta(i);
                alusta.tulosta(System.out);
            }
        } catch (SailoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
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
