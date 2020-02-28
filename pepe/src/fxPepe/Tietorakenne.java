package fxPepe;

/**
 * Dynaaminen tietorakenne PeliTiedoille
 * @author anssi
 * @version 28 Feb 2020
 *
 * @param <TYPE> Tallennettava tyyppi, tässä tapauksessa PeliTieto
 */
public class Tietorakenne<TYPE> {

    /** Luokka täyden taulukon poikkeusta varten  */
    public static class TaulukkoTaysiException extends Error {
        private static final long serialVersionUID = 1L;
        
        TaulukkoTaysiException(String viesti) {
            super(viesti);
        }
    }
    
    private TYPE alkiot[];
    private int lkm;

    /**
     * Alustetaan 5 kokoinen taulukko 
     */
    public Tietorakenne() {
        this(5);
    }
    
    
    /**
     * Alutetaan valitun kokoinen taulukko
     * @param koko taulukon koko
     */
    @SuppressWarnings("unchecked")
    public Tietorakenne(int koko) {
        alkiot = (TYPE[]) new Object[koko];
        // alkiot = new TYPE[koko];
    }
    
    
    /**
     * Lisätään taulukkon uusi alkio
     * @param alkio lisättävän alkion viite
     * @throws TaulukkoTaysiException jos taulukko jo täysi
     */
    public void lisaa(TYPE alkio) throws TaulukkoTaysiException {
        if (lkm >= alkiot.length) throw new TaulukkoTaysiException("Tila loppu");
        alkiot[lkm++] = alkio;
    }
    
    
    /**
     * Muutetaan paikan i arvo
     * @param i mihin paikkaa asetetaan
     * @param alkio uuden alkion viite
     * @throws IndexOutOfBoundsException jos indeksiväärin
     */
    public void set(int i, TYPE alkio) throws IndexOutOfBoundsException {
        if ((i < 0) || (lkm <= i)) throw new IndexOutOfBoundsException("i = " + i);
        alkiot[i] = alkio;
    }


    /**
     * Paikassa i olevan alkion viite
     * @param i mistä paikasta
     * @return paikassa i oleva viite
     * @throws IndexOutOfBoundsException jos indeksi väärin
     */
    public TYPE get(int i) throws IndexOutOfBoundsException {
        if ((i < 0) || (lkm <= i)) throw new IndexOutOfBoundsException("i = " + i);
        return alkiot[i];
    }
    
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        String erotin = "";
        for (int i = 0; i < lkm; i++) {
            s.append(erotin + alkiot[i]);
            erotin = ",";
        }
        return s.toString();
    }
    
    /**
     * Testataan taulukon toimintaa
     * @param args ei käytössä
     * @throws TaulukkoTaysiException jos ei mahu
     */
    public static void main(String[] args) throws  TaulukkoTaysiException {
        Tietorakenne<Integer> luvut = new Tietorakenne<Integer>();
        // luvut.lisaa(new Integer(0)); => luo uuden olion => Javassa olioiden luonti on hidasta
        luvut.lisaa(0); 
        luvut.lisaa(2);
        luvut.lisaa(99);
        System.out.println(luvut);
        
        luvut.set(1, 4);
        System.out.println(luvut);
        
        // luku = luvut.get(2).intValue();
        int luku = luvut.get(2);
        System.out.println("Paikassa 2 on " + luku);
        
        // Yritetään lisätä taulukon paikkaan 21, jossa ei vielä ole mitään
        try {
            luvut.set(21, 4);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Virhe: " + e.getMessage());
        }
        
        Tietorakenne<String> jonot = new Tietorakenne<String>();
        jonot.lisaa("kissa");
        jonot.lisaa("kana");
        jonot.lisaa("koira");
        System.out.println(jonot);
    }


}
