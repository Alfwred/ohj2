package pepe.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import pepe.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.04.23 03:24:42 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class AlustaTest {



  // Generated by ComTest BEGIN
  /** testParsiAlusta75 */
  @Test
  public void testParsiAlusta75() {    // Alusta: 75
    Alusta alusta = new Alusta(); 
    alusta.parsiAlusta("10|PS2|PlayStation 2"); 
    assertEquals("From: Alusta line: 78", 10, alusta.getTunniste()); 
    assertEquals("From: Alusta line: 79", "PS2", alusta.getLyhenne()); 
    assertEquals("From: Alusta line: 80", "PlayStation 2", alusta.getNimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testTarkista100 */
  @Test
  public void testTarkista100() {    // Alusta: 100
    Alusta alusta = new Alusta(); 
    assertEquals("From: Alusta line: 102", false, alusta.tarkista("|")); 
    assertEquals("From: Alusta line: 103", false, alusta.tarkista("")); 
    assertEquals("From: Alusta line: 104", false, alusta.tarkista("PlayStation|")); 
    assertEquals("From: Alusta line: 105", true, alusta.tarkista("PlayStation")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testRekisteroi147 */
  @Test
  public void testRekisteroi147() {    // Alusta: 147
    Alusta a1 = new Alusta(); 
    assertEquals("From: Alusta line: 149", 0, a1.getTunniste()); 
    a1.rekisteroi(); 
    Alusta a2 = new Alusta(); 
    a2.rekisteroi(); 
    int n1 = a1.getTunniste(); 
    int n2 = a2.getTunniste(); 
    assertEquals("From: Alusta line: 155", n2-1, n1); 
  } // Generated by ComTest END
}