package pepe.test;
// Generated by ComTest BEGIN
import fxPepe.SailoException;
import static org.junit.Assert.*;
import org.junit.*;
import pepe.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.04.23 03:48:13 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class NimikkeetTest {



  // Generated by ComTest BEGIN
  /** 
   * testLisaa38 
   * @throws SailoException when error
   */
  @Test
  public void testLisaa38() throws SailoException {    // Nimikkeet: 38
    Nimikkeet nimikkeet = new Nimikkeet(); 
    Nimike cnc = new Nimike(), ra = new Nimike(); 
    assertEquals("From: Nimikkeet line: 43", 0, nimikkeet.getLkm()); 
    nimikkeet.lisaa(cnc); assertEquals("From: Nimikkeet line: 44", 1, nimikkeet.getLkm()); 
    nimikkeet.lisaa(ra); assertEquals("From: Nimikkeet line: 45", 2, nimikkeet.getLkm()); 
    nimikkeet.lisaa(cnc); assertEquals("From: Nimikkeet line: 46", 3, nimikkeet.getLkm()); 
    assertEquals("From: Nimikkeet line: 47", cnc, nimikkeet.anna(0)); 
    assertEquals("From: Nimikkeet line: 48", ra, nimikkeet.anna(1)); 
    assertEquals("From: Nimikkeet line: 49", cnc, nimikkeet.anna(2)); 
    assertEquals("From: Nimikkeet line: 50", false, nimikkeet.anna(1) == cnc); 
    assertEquals("From: Nimikkeet line: 51", true, nimikkeet.anna(1) == ra); 
    try {
    assertEquals("From: Nimikkeet line: 52", cnc, nimikkeet.anna(3)); 
    fail("Nimikkeet: 52 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    nimikkeet.lisaa(cnc); assertEquals("From: Nimikkeet line: 53", 4, nimikkeet.getLkm()); 
    nimikkeet.lisaa(cnc); assertEquals("From: Nimikkeet line: 54", 5, nimikkeet.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testPoista70 
   * @throws SailoException when error
   */
  @Test
  public void testPoista70() throws SailoException {    // Nimikkeet: 70
    Nimikkeet nimikkeet = new Nimikkeet(); 
    Nimike cnc = new Nimike(), ra = new Nimike(); 
    assertEquals("From: Nimikkeet line: 74", 0, nimikkeet.getLkm()); 
    nimikkeet.lisaa(cnc); assertEquals("From: Nimikkeet line: 75", 1, nimikkeet.getLkm()); 
    nimikkeet.lisaa(ra); assertEquals("From: Nimikkeet line: 76", 2, nimikkeet.getLkm()); 
    nimikkeet.poista(ra.getTunniste()); assertEquals("From: Nimikkeet line: 77", 1, nimikkeet.getLkm()); 
    nimikkeet.poista(cnc.getTunniste()); assertEquals("From: Nimikkeet line: 78", 1, nimikkeet.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testAnna95 
   * @throws SailoException when error
   */
  @Test
  public void testAnna95() throws SailoException {    // Nimikkeet: 95
    Nimikkeet nimikkeet = new Nimikkeet(); 
    Nimike cnc = new Nimike("CNC"), ra = new Nimike("RA"), d2 = new Nimike("D2"); 
    assertEquals("From: Nimikkeet line: 99", 0, nimikkeet.getLkm()); 
    nimikkeet.lisaa(cnc); assertEquals("From: Nimikkeet line: 100", 1, nimikkeet.getLkm()); 
    nimikkeet.lisaa(ra); assertEquals("From: Nimikkeet line: 101", 2, nimikkeet.getLkm()); 
    nimikkeet.lisaa(d2); assertEquals("From: Nimikkeet line: 102", 3, nimikkeet.getLkm()); 
    assertEquals("From: Nimikkeet line: 103", "CNC", nimikkeet.anna(0).getNimi()); 
    assertEquals("From: Nimikkeet line: 104", "RA", nimikkeet.anna(1).getNimi()); 
    assertEquals("From: Nimikkeet line: 105", "D2", nimikkeet.anna(2).getNimi()); 
  } // Generated by ComTest END
}