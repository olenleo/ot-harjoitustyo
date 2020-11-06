/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import sovelluslogiikka.SovellusLogiikka;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author 35850
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

//    @Test
//    public void nimi() {
//        esimerkkejä
//        assertEquals(param, metodi.param());
//        assertTrue(boolean, metodi.getBoolean());
//    }
    
    @Test
    public void lukuAlussa0() {
        assertEquals(0, sl.getLuku());
    }
    
    @Test
    public void alussaTahtiOnFalse() {
        assertFalse(sl.tarkistaTahti(sl.getLuku()));
    }

    @Test
    public void lukuKasvatusToimii() {
        sl.kasvataLukua();
        assertEquals(1, sl.getLuku());
    }
    
    @Test
    public void isku1PalauttaaTrue() {
        sl.kasvataLukua();
        assertTrue(sl.tarkistaTahti(sl.getLuku()));
        
    }

}
