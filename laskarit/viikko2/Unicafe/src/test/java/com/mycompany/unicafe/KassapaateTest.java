package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    Kassapaate kassapaate;
    Maksukortti kortti;
    int alkusaldo = 100000;
    int maukas = 400;
    int edullinen = 240;

    public KassapaateTest() {

    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void kassaPaateIlmanOstojaToimiiOikein() {
        assertEquals(alkusaldo, kassapaate.kassassaRahaa());
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());

    }

    @Test
    public void edullisenLounaanOstoToimii() {
        kassapaate.syoEdullisesti(300);
        assertEquals(alkusaldo + edullinen, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());

    }

    @Test
    public void maukkkaanLounaanOstoeiToimi() {
        kassapaate.syoMaukkaasti(200);
        assertEquals(alkusaldo, kassapaate.kassassaRahaa());
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());

    }

    @Test
    public void maukkkaanLounaanOstoToimii() {
        kassapaate.syoMaukkaasti(600);
        assertEquals(alkusaldo + maukas, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());

    }

    @Test
    public void liianPieniMaksuToimiiOikein() {
        kassapaate.syoEdullisesti(1);
        kassapaate.syoMaukkaasti(1);
        kassaPaateIlmanOstojaToimiiOikein(); // onko tämä huono käytäntö?
    }

    @Test
    public void korttiostoEdullisestiToimii() {
        assertTrue(kassapaate.syoEdullisesti(kortti));
        assertEquals(760, kortti.saldo());

    }

    @Test
    public void korttiostoMaukkaastiToimii() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
    }

    @Test
    public void korttiostoMaukkaastiKateisellaToimii() {
        kassapaate.syoMaukkaasti(600);
        assertEquals(100400, kassapaate.kassassaRahaa());
    }

    @Test
    public void luonaittenMaaraKasvaa() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void liianPieniSaldoMaukkailleToimii() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertFalse(kassapaate.syoMaukkaasti(kortti));
        assertEquals(2, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(200, kortti.saldo());
    }
@Test
    public void liianPieniSaldoEdullisilleToimii() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoEdullisesti(kortti);
        assertFalse(kassapaate.syoEdullisesti(kortti));
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(200, kortti.saldo());
    }
    @Test
    public void korttiOstoEiKasvataSaldoa() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void kortinLatausToimii() {
        kassapaate.lataaRahaaKortille(kortti, 1000);
        assertEquals(2000, kortti.saldo());
        assertEquals(101000, kassapaate.kassassaRahaa());

    }

    @Test
    public void kortinLatausNegatiivisellaSaldollaEiToimi() {
        kassapaate.lataaRahaaKortille(kortti, -1000);
        assertEquals(1000, kortti.saldo());
        assertEquals(alkusaldo, kassapaate.kassassaRahaa());

    }
}
