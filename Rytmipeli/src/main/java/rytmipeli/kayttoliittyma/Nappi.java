/**
 * Luokka ohjaa käyttöliittymän Button-olioita.
 *
 *
 */
package rytmipeli.kayttoliittyma;

import java.io.BufferedInputStream;
import java.io.InputStream;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import rytmipeli.aaniefektit.Aanikirjasto;
import rytmipeli.sovelluslogiikka.SovellusLogiikka;

/**
 *
 * @author Leo Niemi
 */
public class Nappi extends Button {

    private String type;
    private SovellusLogiikka sl;
    private Aanikirjasto aanikirjasto = new Aanikirjasto();

    /**
     * Asettaa napin labelin
     *
     * @param text Haluttu teksti
     */
    public Nappi(String text) {
        this.setText(text);
    }

    /**
     *
     * @param text Haluttu teksti
     * @param type Napin funktio@See rytmipeli.sovelluslogiikka
     * @param sl Kytkentä sovelluslogiikkaan
     * @param scorefield Pistekentän arvon muutosta varten
     * @param state Viestien kirjoittaminen pelaajalle
     * @param ui Käyttöliittymän hallinta
     */
    public Nappi(String text, String type, SovellusLogiikka sl, Label scorefield, Label state, Kayttoliittyma ui) {
        this.type = type;
        this.setText(text);
        this.setOnAction((ActionEvent e) -> {
            String annettu = this.type;
            String vaadittu = sl.tarkistaLaatta(sl.getLuku());
            if (annettu.equals(vaadittu)) {
                sl.kasvataLukua();
                scorefield.setText("Score: " + sl.getLuku());
                state.setText("Hyvin pyyhkii!");
                playSound(aanikirjasto.getSound(type));
                FadeTransition ft = new FadeTransition(Duration.millis(50), this);
                ft.setFromValue(1.0);
                ft.setToValue(0.8);
                ft.setCycleCount(2);
                ft.setAutoReverse(true);
                ft.play();
            } else {
                if (sl.getElamat() > 0) {
                    sl.vahennaElama();
                    playSound(aanikirjasto.getSound("VIRHE"));
                    state.setText("Elämiä jäljellä: " + sl.getElamat());
                } else {
                    ui.getMid().setText("Edellinen yritys: " + sl.getLuku() + "!\nPystytkö parempaan?");
                    sl.alustaPeli();
                    Kayttoliittyma.getStage().setScene(Kayttoliittyma.sceneMenu);
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
                } catch (Exception e) {
                    System.out.println("Virhe playSound-metodissa: " + e.getMessage());
                }
            }
        }).start();

    }
}
