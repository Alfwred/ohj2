package pepe.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import pepe.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.04.23 03:25:17 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class NimikeTest {



  // Generated by ComTest BEGIN
  /** testParsiNimike66 */
  @Test
  public void testParsiNimike66() {    // Nimike: 66
    Nimike nimike = new Nimike(); 
    nimike.parsiNimike("1|Testi Peli"); 
    assertEquals("From: Nimike line: 69", 1, nimike.getTunniste()); 
    assertEquals("From: Nimike line: 70", "Testi Peli", nimike.getNimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testTarkista153 */
  @Test
  public void testTarkista153() {    // Nimike: 153
    Alusta alusta = new Alusta(); 
    assertEquals("From: Nimike line: 155", false, alusta.tarkista("|")); 
    assertEquals("From: Nimike line: 156", false, alusta.tarkista("")); 
    assertEquals("From: Nimike line: 157", false, alusta.tarkista("Final Fantasy 7 Remake|")); 
    assertEquals("From: Nimike line: 158", true, alusta.tarkista("Final Fantasy 7 Remake")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testVertaa180 */
  @Test
  public void testVertaa180() {    // Nimike: 180
    Nimike nimike = new Nimike("Tomb Raider"); 
    assertEquals("From: Nimike line: 182", true, nimike.vertaa("T")); 
    assertEquals("From: Nimike line: 183", true, nimike.vertaa("To")); 
    assertEquals("From: Nimike line: 184", true, nimike.vertaa("Tom")); 
    assertEquals("From: Nimike line: 185", true, nimike.vertaa("Tomb")); 
    assertEquals("From: Nimike line: 186", true, nimike.vertaa("R")); 
    assertEquals("From: Nimike line: 187", true, nimike.vertaa("Ra")); 
    assertEquals("From: Nimike line: 188", true, nimike.vertaa("Rai")); 
    assertEquals("From: Nimike line: 189", true, nimike.vertaa("Raid")); 
    assertEquals("From: Nimike line: 190", true, nimike.vertaa("Raide")); 
    assertEquals("From: Nimike line: 191", true, nimike.vertaa("Raider")); 
    assertEquals("From: Nimike line: 192", true, nimike.vertaa("")); 
    assertEquals("From: Nimike line: 193", true, nimike.vertaa(" ")); 
    assertEquals("From: Nimike line: 194", false, nimike.vertaa("  ")); 
    assertEquals("From: Nimike line: 195", false, nimike.vertaa("Topi")); 
  } // Generated by ComTest END
}