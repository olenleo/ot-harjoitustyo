package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;
    Kassapaate kassapaate;
    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
        kassapaate = new Kassapaate();
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void saldoToimii() {
        assertEquals(10.0, kortti.saldo(),0);
        
    }

    @Test
    public void saldoKasvaaOikein() {
        kortti.lataaRahaa(10);
        assertEquals("saldo: 0.20", kortti.toString());
    }

    @Test
    public void saldoEiMuutuJosRahaaEiTarpeeksi() {
        kortti.otaRahaa(100);
        assertEquals("saldo: 0.10", kortti.toString());

    }

    @Test
    public void SaldoVahenee() {
        kortti.otaRahaa(1);
        assertEquals("saldo: 0.9", kortti.toString());
    }

    @Test
    public void booleanKunRahaRiittaa() {
        assertTrue(kortti.otaRahaa(5));

    }
    @Test
    public void booleanKunRahaEiRiitta() {
       assertFalse(kortti.otaRahaa(5000));

    }
}
