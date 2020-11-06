/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import sovelluslogiikka.SovellusLogiikka;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
//        assertEquals(param, metodi.param());
//        
//    }
    
    
    @Test
    public void lukuAlussa0() {
        assertEquals(0, sl.getLuku());
    }
    
    @Test
    public void lukuKasvatusToimii() {
        sl.kasvataLukua();
        assertEquals(1, sl.getLuku());
        
    }
    
}
