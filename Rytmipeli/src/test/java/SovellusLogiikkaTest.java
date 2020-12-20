/*
 * 
 */
import rytmipeli.sovelluslogiikka.SovellusLogiikka;

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

    SovellusLogiikka sl;

    public SovellusLogiikkaTest() {
        sl = new SovellusLogiikka();
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
        assertEquals(1, sl.getLuku());
    }

    @Test
    public void alussaTahtiOnTrue() {
        assertTrue(sl.tarkistaTahti(sl.getLuku()));
    }

    @Test
    public void tarkistaTahtiToimii() {
        sl.asetaLuku(4);
        assertFalse(sl.tarkistaTahti(sl.getLuku()));
        sl.kasvataLukua();
        assertTrue(sl.tarkistaTahti(sl.getLuku()));
    }

    @Test
    public void merkkijonotestiToimii() {
        assertTrue(sl.tarkistaMerkkiJono(17));
    }

    @Test
    public void lukuKasvatusToimii() {
        sl.kasvataLukua();
        assertEquals(2, sl.getLuku());
    }

    @Test
    public void isku1PalauttaaTrue() {
        assertTrue(sl.tarkistaTahti(sl.getLuku()));
    }

    @Test
    public void asetaLukuToimii() {
        sl.asetaLuku(100);
        assertEquals(100, sl.getLuku());
    }

    @Test
    public void isku17vaatiimolemmat() {
        sl.asetaLuku(17);
        assertEquals("MOLEMMAT", sl.tarkistaLaatta(sl.getLuku()));

    }

    @Test
    public void tarkistamod7toimii() {
        sl.asetaLuku(6);
        assertFalse(sl.tarkistaMod7(sl.getLuku()));
        sl.kasvataLukua();
        assertTrue(sl.tarkistaMod7(sl.getLuku()));

    }

    @Test
    public void tarkistaLaattaMetodiPalauttaaTahti() {
        assertEquals("TAHTI", sl.tarkistaLaatta(sl.getLuku()));
    }

    @Test
    public void tarkistaLaattaMetodiPalauttaaSeuraava() {
        sl.kasvataLukua();
        assertEquals("SEURAAVA", sl.tarkistaLaatta(sl.getLuku()));
    }

    @Test
    public void tarkistaLaattaMetodipalauttaLaattaKunMerkkijonoSisaltaa7() {
        sl.asetaLuku(27);
        assertEquals("LAATTA", sl.tarkistaLaatta(sl.getLuku()));
    }

    @Test
    public void tarkistaLaattaMetodiPalauttaaLaattaKun_n_Mod_7() {
        sl.asetaLuku(14);
        assertEquals("LAATTA", sl.tarkistaLaatta(sl.getLuku()));
    }

    @Test
    public void tarkistaLaattaMetodiPalauttaaMolemmat_Sisaltaa7() {
        sl.asetaLuku(17);
        assertEquals("MOLEMMAT", sl.tarkistaLaatta(sl.getLuku()));
    }

    @Test
    public void tarkistaLaattaMetodiPalauttaaMolemmatKun_n_Mod_7() {
        sl.asetaLuku(21);
        assertEquals("MOLEMMAT", sl.tarkistaLaatta(sl.getLuku()));
    }

    @Test
    public void alustaPeliMetodiToimii() {
        sl.asetaLuku(2);
        sl.alustaPeli();
        assertEquals(1, sl.getLuku());
    }

    @Test
    public void getElamatToimii() {
        assertEquals(3, sl.getElamat());
    }

    @Test
    public void vahennaElamaToimii() {
        sl.vahennaElama();
        assertEquals(2, sl.getElamat());
    }
}
