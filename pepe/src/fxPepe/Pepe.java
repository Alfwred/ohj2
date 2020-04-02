package fxPepe;

import java.util.List;

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
     * Poistaa pelikannasta ja peliTiedoista ne joilla on nro. Kesken.
     * @param nro viitenumero, jonka mukaan poistetaan
     * @return montako pelia poistettiin
     */
    public int poista(@SuppressWarnings("unused") int nro) {
        return 0;
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
     * Palauttaa parametrina pyydetyn pelin
     * @param i Järjestysnumero
     * @return Peli, joka pyydettiin
     */
    public Peli annaPeli(int i) {
        return pelit.annaPeli(i);
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
     * @param nimi jota käyteään lukemisessa
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String nimi) throws SailoException {
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
    public void talleta() throws SailoException {
        pelit.talleta();
        // TODO: yritä tallettaa toinen vaikka toinen epäonnistuisi
    }
    
    
    /**
     * Väliaikainen viritys!
     * StringGridiin lisaysta varten metodi, joka luo String[] pelistä
     * @param peli Peli
     * @return String[]-taulukko pelin tiedoista
     */
    public String[] getKenttia(Peli peli) {
        String[] t = {Integer.toString(peli.getTunniste()), annaNimike(peli).toString(), annaAlusta(peli).toString()};
        return t;
    }
    
    //loytynyt.tulosta(System.out);
    
    //pepe.annaNimike(loytynyt).tulosta(System.out);
    //pepe.annaAlusta(loytynyt).tulosta(System.out);
    

    /**
     * Testiohjelma pepesta
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        
        Pepe pepe = new Pepe();

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
    }

}