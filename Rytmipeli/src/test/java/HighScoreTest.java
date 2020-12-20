
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import rytmipeli.pisteet.HighScoreManager;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;
import rytmipeli.pisteet.Piste;

/**
 * Highscore-toiminnallisuuteen liittyviä automaattisia testejä.
 *
 * @author Leo Niemi
 */
public class HighScoreTest {

    private static HighScoreManager hsm;
    private FileReader fr;
    private BufferedReader br;
    private static FileOutputStream fos;
    private static File mockFile;

    public HighScoreTest() {
        try {
            mockFile = new File("mock.txt");
            hsm = new HighScoreManager("mock.txt");
            fr = new FileReader(mockFile.getPath());
            br = new BufferedReader(fr);
            fos = new FileOutputStream(mockFile);
            hsm.writeCSV("TESTI", 1);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HighScoreTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HighScoreTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeAll
    public static void setUpClass() {

    }

    @AfterAll
    public static void tearDownClass() {
        mockFile.delete();
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void HighScoreManagerKonstruktoriToimii() {
        String line = "";
        String[] tempArr;

        try {
            while ((line = br.readLine()) != null) {
                tempArr = line.split(",");
                int tulos = Integer.valueOf(tempArr[1]);
                String nimi = tempArr[0];
                assertTrue(tulos == 1);
                assertTrue(nimi.equals("TESTI"));
            }

            br.close();
        } catch (IOException ex) {
            Logger.getLogger(HighScoreTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void WriteCSVMetodiToimii() {
        hsm.writeCSV("Kaarlo", 22);
        String line = "";
        String[] tempArr;
        ArrayList<Piste> tulokset = new ArrayList<>();
        try {
            while ((line = br.readLine()) != null) {
                tempArr = line.split(",");
                SimpleIntegerProperty tulos = new SimpleIntegerProperty((Integer.valueOf(tempArr[1])));
                Piste p = new Piste(tempArr[0], tulos);
                tulokset.add(p);
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(HighScoreTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertTrue(tulokset.size() == 2);
        assertTrue(tulokset.get(1).getNimi().equals("Kaarlo") && tulokset.get(1).getPisteetFactory() == 22);
    }

    @Test
    public void testOlematonTiedostoAntaaVirheen() {

        String s = "(The system cannot find the file specified)";
        try {
            HighScoreManager mock_hsm = new HighScoreManager("OLEMATON TIEDOSTO");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains(s));
        }

    }

}
