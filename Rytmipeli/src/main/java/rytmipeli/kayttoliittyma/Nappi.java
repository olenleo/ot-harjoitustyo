/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rytmipeli.kayttoliittyma;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import rytmipeli.sovelluslogiikka.SovellusLogiikka;


/**
 *
 * @author Leo Niemi
 */
public class Nappi extends Button {

    private String type;
    private SovellusLogiikka sl;

    private boolean virhe = false;

    public Nappi(String text) {
        this.setText(text);

    }
/*
    Nappi.java muuttaa menu-käyttöliittymän labelia. 
    Hieman kömpelöltä ratkaisulta, mutta koska kyseessä on melko yksinkertainen ohjelma,
    pitäydyn tässä.   
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


}
