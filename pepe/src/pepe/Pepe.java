package pepe;

import java.util.List;
import fxPepe.SailoException;

/**
 * Pepe-luokka, joka huolehtii pelikannasta. Pääosin kaikki metodit
 * ovat vain "välittäjämetodeja" pelikantaan.
 * @author anssi
 * @version 23 Feb 2020
 *
 */
public class Pepe {
    
    private Pelit pelit = new Pelit();
    private Nimikkeet nimikkeet = new Nimikkeet();
    private Alustat alustat = new Alustat();


    /**
     * Palautaa pepen pelien määrän
     * @return pelien määrä
     */
    public int getPeleja() {
        return pelit.getLkm();
    }


    /**
     * Lisää pepeen uuden pelin
     * @param peli lisättävä peli
     * @throws SailoException jos lisäystä ei voida tehdä
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Pepe pepe = new Pepe();
     * Peli cnc = new Peli(), d2 = new Peli();
     * cnc.rekisteroi(); d2.rekisteroi();
     * pepe.getPeleja() === 0;
     * pepe.lisaa(cnc); pepe.getPeleja() === 1;
     * pepe.lisaa(d2); pepe.getPeleja() === 2;
     * pepe.lisaa(cnc); pepe.getPeleja() === 3;
     * pepe.getPeleja() === 3;
     * pepe.annaPeli(0) === cnc;
     * pepe.annaPeli(1) === d2;
     * pepe.annaPeli(2) === cnc;
     * pepe.annaPeli(3) === cnc; #THROWS IndexOutOfBoundsException 
     * pepe.lisaa(cnc); pepe.getPeleja() === 4;
     * pepe.lisaa(cnc); pepe.getPeleja() === 5;
     * pepe.lisaa(cnc);            #THROWS SailoException
     * </pre>
     */
    public void lisaa(Peli peli) throws SailoException {
        pelit.lisaa(peli);
    }
    
    
    /** 
     * Poistaa tämän pelin (ja nimikkeen, jos mikään muu peli
     * ei viittaa samaan nimikkeeseen)
     * @param peli Poistettava peli
     * @example
     * TODO paskat testit
     * <pre name="test">
     * #THROWS Exception
     *   alustaKerho();
     *   kerho.annaHarrastukset(aku1).size() === 2;
     *   kerho.poistaHarrastus(pitsi11);
     *   kerho.annaHarrastukset(aku1).size() === 1;
     */ 
    public void poista(Peli peli) {
        int lkm = annaSamatNimikkeet(peli);
        if (lkm <= 1) nimikkeet.poista(peli.getNimike());
        pelit.poista(peli);
    }
    
    
    /**
     * Etsii kuinka monessa pelissä on sama nimike
     * @param peli Peli, jonka nimikettä tarkastellaan
     * @return Nimikkeiden lukumäärä
     */
    public int annaSamatNimikkeet(Peli peli) {
        int lkm = 0;
        for (Peli loydetty : pelit)
            if (loydetty.getNimike() == peli.getNimike()) lkm++;;
            System.out.println(lkm);
        return lkm;
    }
   

    /**
     * Listään uusi nimike Pepeen
     * @param nimike lisättävä nimike
     * @throws SailoException jos lisäystä ei voida tehdä
     */
    public void lisaa(Nimike nimike) throws SailoException {
        nimikkeet.lisaa(nimike);
    }
    
    
    /**
     * Listään uusi alusta Pepeen
     * @param alusta Lisättävä alusta 
     */
    public void lisaa(Alusta alusta) {
        alustat.lisaa(alusta);
    }
        
    
    /**
     * Palauttaa pelin parametrina annetun tunnisteen perusteella
     * @param tunniste Pelin tunniste
     * @return Peli
     */
    public Peli annaPeli(int tunniste) {
        return pelit.annaPeli(tunniste);
    }
    
    
    /**
     * @return Lista peleistä
     */
    public List<Peli> annaPelit() {
        return pelit.annaPelit();
    }
    
    
    /**
     * @return Lista alustoista
     */
    public List<Alusta> annaAlustat() {
        return alustat.annaAlustat();
    }
    
    
    /**
     * Paluttaa pelille kuuluvan nimikkeen
     * @param peli Peli, jonka nimikettä haetaan
     * @return Pelin nimike
     * @throws IndexOutOfBoundsException jos i väärin
     */
    public Nimike annaNimike(Peli peli) throws IndexOutOfBoundsException {
        return nimikkeet.annaNimike(peli.getNimike());
    }

    
    /**
     * Haetaan Pelin alusta
     * @param peli Peli, jonka alustaa haetaan
     * @return Pelin alusta
     */
    public Alusta annaAlusta(Peli peli) {
        return alustat.annaAlusta(peli.getAlusta());
    }
    

    /**
     * Lukee pepen tiedot tiedostosta
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta() throws SailoException {
        nimikkeet = new Nimikkeet();
        alustat = new Alustat();
        pelit = new Pelit();
        nimikkeet.lueTiedostosta();
        alustat.lueTiedostosta();
        pelit.lueTiedostosta();
    }

    
    /**
     * Tallettaa pepen tiedot tiedostoon
     * @throws SailoException jos tallettamisessa ongelmia
     */
    public void tallenna() throws SailoException {
        pelit.tallenna();
        nimikkeet.tallenna();
        alustat.tallenna();
    }
    
    
    /**
     * StringGridiin lisaysta varten metodi, joka luo String[] pelistä
     * @param peli Peli
     * @return String[] pelin tiedoista
     */
    public String[] getKenttia(Peli peli) {
        String[] t = {
                Integer.toString(peli.getTunniste()),
                annaNimike(peli).getNimi(),
                annaAlusta(peli).getNimi(),
                Integer.toString(peli.getJulkaisuvuosi()),
                Integer.toString(peli.getHankintavuosi()),
                Integer.toString(peli.getLevy()),
                Integer.toString(peli.getKotelo()),
                Integer.toString(peli.getOhje()),
                peli.getLisatiedot()};
        return t;
    }
    
    
    /**
     * Testiohjelma pepesta
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        
        Pepe pepe = new Pepe();
        
        try {
            pepe.lueTiedostosta();
            for (int i = 0; i  < pepe.getPeleja(); i++) {
                Peli loytynyt = pepe.annaPeli(i);
                loytynyt.tulosta(System.out);
            }
        } catch (SailoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
        /**
        try {
            
            // Alustojen testiluonti
            Alusta a1 = new Alusta();
            Alusta a2 = new Alusta();
            a1.rekisteroi();
            a2.rekisteroi();
            a1.taytaTestiAlustaTiedoilla();
            a2.taytaTestiAlustaTiedoilla();
            
            pepe.lisaa(a1);
            pepe.lisaa(a2);
            
            // Nimikkeiden testiluonti
            Nimike n1 = new Nimike();
            Nimike n2 = new Nimike();
            n1.rekisteroi();
            n2.rekisteroi();
            n1.taytaTestiNimikeTiedoilla();
            n2.taytaTestiNimikeTiedoilla();
            
            pepe.lisaa(n1);
            pepe.lisaa(n2);
            
            
            // Pelien luonti, nimikkeiden ja alustojen sitominen
            Peli peli1 = new Peli(0);
            peli1.rekisteroi();
            peli1.taytaTestiPeliTiedoilla(n1.getTunniste(), a1.getTunniste());
            pepe.lisaa(peli1);
            
            Peli peli2 = new Peli(1);
            peli2.rekisteroi();
            peli2.taytaTestiPeliTiedoilla(n2.getTunniste(), a2.getTunniste());
            pepe.lisaa(peli2);


            System.out.println("============= Pepen testi =================");
            
            // pepe.annaPeli(0).tulosta(System.out);
            // pepe.annaNimike(pepe.annaPeli(0)).tulosta(System.out);
            // pepe.annaAlusta(pepe.annaPeli(0)).tulosta(System.out);
            
            for (int i = 0; i  < pepe.getPeleja(); i++) {
                Peli loytynyt = pepe.annaPeli(i);
                loytynyt.tulosta(System.out);
                
                pepe.annaNimike(loytynyt).tulosta(System.out);
                pepe.annaAlusta(loytynyt).tulosta(System.out);
            }
            
        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
        */
    }

}