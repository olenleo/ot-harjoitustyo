
/**
 *
 * @author Leo Niemi
 */
import rytmipeli.aaniefektit.Aanikirjasto;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class AaniKirjastoTest {
 Aanikirjasto ak;

    public AaniKirjastoTest() {
        ak = new Aanikirjasto();
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void getSoundPalauttaaMerkkijononKunTypeOnMOLEMMAT() {
        assertTrue(ak.getSound("MOLEMMAT").contains(".wav"));
    }
    
    @Test
    public void getSoundPalauttaaVirheenKunTypeOnVAARIN() {
        assertTrue(ak.getSound("foo").contains("Virhe"));
    }
    
}
