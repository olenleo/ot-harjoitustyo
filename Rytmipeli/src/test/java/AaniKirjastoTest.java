
/**
 *
 * @author Leo Niemi
 */
import rytmipeli.aaniefektit.SoundLibrary;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class AaniKirjastoTest {
 SoundLibrary soundlibrary;

    public AaniKirjastoTest() {
        soundlibrary = new SoundLibrary();
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
        assertTrue(soundlibrary.getSound("MOLEMMAT").contains(".wav"));
    }
    
    @Test
    public void getSoundPalauttaaVirheenKunTypeOnVAARIN() {
        assertTrue(soundlibrary.getSound("foo").contains("Virhe"));
    }
    
}
