/*
 * 
 */
import rytmipeli.sovelluslogiikka.ApplicationLogic;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Leo Niemi
 */
public class SovellusLogiikkaTest {

    ApplicationLogic sl;

    public SovellusLogiikkaTest() {
        sl = new ApplicationLogic();
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
    public void lukuAlussa1() {
        assertEquals(1, sl.getCurrentBeat());
    }

    @Test
    public void alussaTahtiOnTrue() {
        assertTrue(sl.checkPrimaryBeat(sl.getCurrentBeat()));
    }

    @Test
    public void tarkistaTahtiToimii() {
        sl.setCurrentBeat(4);
        assertFalse(sl.checkPrimaryBeat(sl.getCurrentBeat()));
        sl.increaseCurrentBeat();
        assertTrue(sl.checkPrimaryBeat(sl.getCurrentBeat()));
    }

    @Test
    public void merkkijonotestiToimii() {
        assertTrue(sl.checkStringForPolyrhytm(17));
    }

    @Test
    public void lukuKasvatusToimii() {
        sl.increaseCurrentBeat();
        assertEquals(2, sl.getCurrentBeat());
    }

    @Test
    public void isku1PalauttaaTrue() {
        assertTrue(sl.checkPrimaryBeat(sl.getCurrentBeat()));
    }

    @Test
    public void asetaLukuToimii() {
        sl.setCurrentBeat(100);
        assertEquals(100, sl.getCurrentBeat());
    }

    @Test
    public void isku17vaatiimolemmat() {
        sl.setCurrentBeat(17);
        assertEquals("MOLEMMAT", sl.checkCurrentBeat(sl.getCurrentBeat()));

    }

    @Test
    public void tarkistamod7toimii() {
        sl.setCurrentBeat(6);
        assertFalse(sl.checkMod7(sl.getCurrentBeat()));
        sl.increaseCurrentBeat();
        assertTrue(sl.checkMod7(sl.getCurrentBeat()));

    }

    @Test
    public void tarkistaLaattaMetodiPalauttaaTahti() {
        assertEquals("TAHTI", sl.checkCurrentBeat(sl.getCurrentBeat()));
    }

    @Test
    public void tarkistaLaattaMetodiPalauttaaSeuraava() {
        sl.increaseCurrentBeat();
        assertEquals("SEURAAVA", sl.checkCurrentBeat(sl.getCurrentBeat()));
    }

    @Test
    public void tarkistaLaattaMetodipalauttaLaattaKunMerkkijonoSisaltaa7() {
        sl.setCurrentBeat(27);
        assertEquals("LAATTA", sl.checkCurrentBeat(sl.getCurrentBeat()));
    }

    @Test
    public void tarkistaLaattaMetodiPalauttaaLaattaKun_n_Mod_7() {
        sl.setCurrentBeat(14);
        assertEquals("LAATTA", sl.checkCurrentBeat(sl.getCurrentBeat()));
    }

    @Test
    public void tarkistaLaattaMetodiPalauttaaMolemmat_Sisaltaa7() {
        sl.setCurrentBeat(17);
        assertEquals("MOLEMMAT", sl.checkCurrentBeat(sl.getCurrentBeat()));
    }

    @Test
    public void tarkistaLaattaMetodiPalauttaaMolemmatKun_n_Mod_7() {
        sl.setCurrentBeat(21);
        assertEquals("MOLEMMAT", sl.checkCurrentBeat(sl.getCurrentBeat()));
    }

    @Test
    public void alustaPeliMetodiToimii() {
        sl.setCurrentBeat(2);
        sl.initializeGame();
        assertEquals(1, sl.getCurrentBeat());
    }

    @Test
    public void getElamatToimii() {
        assertEquals(3, sl.getLives());
    }

    @Test
    public void vahennaElamaToimii() {
        sl.reduceLives();
        assertEquals(2, sl.getLives());
    }
}
