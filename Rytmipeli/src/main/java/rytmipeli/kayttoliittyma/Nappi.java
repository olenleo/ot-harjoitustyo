/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rytmipeli.kayttoliittyma;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.util.Duration;

/**
 *
 * @author Leo Niemi
 */
public class Nappi extends Button {
    private String type;
    
    public Nappi(String text, String type) {
        this.type = type;
        this.setText(text);
        this.setOnAction((ActionEvent e) -> {
            
            FadeTransition ft = new FadeTransition(Duration.millis(50), this);
            ft.setFromValue(1.0);
            ft.setToValue(0.8);
            ft.setCycleCount(2);
            ft.setAutoReverse(true);
            ft.play();
        });
    }

}
