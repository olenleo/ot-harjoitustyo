/**
 * Luokka ohjaa käyttöliittymän Button-olioita.
 *
 *
 */
package rytmipeli.kayttoliittyma;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import rytmipeli.aaniefektit.SoundLibrary;
import rytmipeli.pisteet.HighScoreManager;
import rytmipeli.sovelluslogiikka.ApplicationLogic;

/**
 *
 * @author Leo Niemi
 */
// Tämä luokka on hieman epäselvä.
// Tämä käsittelee napin buttoneventin. Toisaalta, tämä toistaa myös äänet.
// Toisin sanoen: Miten single responsibility principle ja tämä menevät yksiin?
public class RytmipeliButton extends Button {

    private String type;
    private ApplicationLogic sl;
    private SoundLibrary soundlibrary = new SoundLibrary();

    /**
     * Asettaa napin labelin
     *
     * @param text Haluttu teksti
     */
    /**
     * Alkumenun napit eivät vaadi yhtä laajaa toiminnallisuutta kuin itse
     * pelin.
     *
     * @param text Asettaa napin labelin
     */
    public RytmipeliButton(String text) {
        this.setText(text);
    }

    /**
     *
     * @param text Haluttu teksti
     * @param type Napin funktio @See rytmipeli.sovelluslogiikka
     * @param sl Kytkentä sovelluslogiikkaan
     * @param scorefield Pistekentän arvon muutosta varten
     * @param state Viestien kirjoittaminen pelaajalle
     * @param ui Käyttöliittymän hallinta
     * @param highscoremanager
     */
    public RytmipeliButton(String text, String type, ApplicationLogic sl, Label scorefield, Label state, UserInterface ui, HighScoreManager highscoremanager) {
        this.type = type;
        this.setText(text);
        this.setOnAction((ActionEvent e) -> {
            String playerInput = this.type;
            String expected = sl.checkCurrentBeat(sl.getCurrentBeat());
            if (playerInput.equals(expected)) {
                sl.increaseCurrentBeat();
                scorefield.setText("Score: " + sl.getCurrentBeat());
                state.setText("Hyvin pyyhkii!");
                playSound(soundlibrary.getSound(type));
                FadeTransition ft = new FadeTransition(Duration.millis(50), this);
                ft.setFromValue(1.0);
                ft.setToValue(0.8);
                ft.setCycleCount(2);
                ft.setAutoReverse(true);
                ft.play();
            } else {
                if (sl.getLives() > 1) {
                    sl.reduceLives();
                    playSound(soundlibrary.getSound("VIRHE"));
                    state.setText("Elämiä jäljellä: " + sl.getLives());
                } else {
                    UserInterface.getStage().setScene(UserInterface.sceneGameOver);
                }
            }
        });
    }

    /**
     * Metodi toistaa .wav-muotoisen äänitiedoston InputStreamina.
     *
     * @param url Halutun .wav-tiedoston nimi /resources-kansiossa
     */
    public static synchronized void playSound(final String url) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    InputStream audioSrc = getClass().getResourceAsStream("/" + url);
                    InputStream bufferedIn = new BufferedInputStream(audioSrc);
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                    clip.open(audioStream);
                    bufferedIn.close();
                    clip.start();
                } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
                   Logger.getLogger(RytmipeliButton.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();

    }
}
