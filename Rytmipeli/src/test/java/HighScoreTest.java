import java.io.BufferedReader;
import java.io.File;
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
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import rytmipeli.pisteet.Score;

/**
 * Highscore-toiminnallisuuteen liittyviä automaattisia testejä.
 *
 * @author Leo Niemi
 */

public class HighScoreTest {

    private FileReader fr;
    private BufferedReader br;
    private static FileOutputStream fos;


    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    public HighScoreTest() throws IOException {
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
    public void HighScoreManagerKonstruktoriToimii() throws IOException {
        File createdFile = tempFolder.newFile("myfile.txt");
        HighScoreManager hsm = new HighScoreManager(createdFile.getCanonicalPath());
        fr = new FileReader(createdFile.getAbsolutePath());
        br = new BufferedReader(fr);
        fos = new FileOutputStream(createdFile);
        fos.write("TESTI,1".getBytes());
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
    public void WriteCSVMetodiToimii() throws IOException {
        File createdFile = tempFolder.newFile("myfile.txt");
        HighScoreManager hsm = new HighScoreManager(createdFile.getCanonicalPath());
        fr = new FileReader(createdFile.getAbsolutePath());
        br = new BufferedReader(fr);
        hsm.writeCSV("Kaarlo", 22);
        String line = "";
        String[] tempArr;
        ArrayList<Score> results = new ArrayList<>();
        try {
            while ((line = br.readLine()) != null) {
                tempArr = line.split(",");
                SimpleIntegerProperty tulos = new SimpleIntegerProperty((Integer.valueOf(tempArr[1])));
                Score p = new Score(tempArr[0], tulos);
                results.add(p);
            }
            br.close();
        } catch (IOException | NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
        assertTrue(results.size() == 1);
        assertTrue(results.get(0).getName().equals("Kaarlo") && results.get(0).getPlayerScoreFactory() == 22);

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
