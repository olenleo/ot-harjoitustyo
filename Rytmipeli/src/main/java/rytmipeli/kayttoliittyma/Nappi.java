/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rytmipeli.kayttoliittyma;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
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

    public Nappi(String text) {
        this.setText(text);

    }

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
                    state.setText("Elämiä jäljellä: " + sl.getElamat());
                } else {
                    ui.getMid().setText("Edellinen yritys: " + sl.getLuku() + "!\nPystytkö parempaan?");
                    sl.alustaPeli();
                    Kayttoliittyma.getStage().setScene(Kayttoliittyma.sceneMenu);
                }
            }
        });
    }
    
  

    public static synchronized void playSound(final String url) {
        new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    InputStream audioSrc = getClass().getResourceAsStream("/" + url);
                    InputStream bufferedIn = new BufferedInputStream(audioSrc);
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                    clip.open(audioStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
}
