/**
 * Tämä luokka lataa ääniefektit
 */
package rytmipeli.aaniefektit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Luokka hallinnoi resources-kansion äänitiedostoja. String-muotoiset viitteet tallennetaan hajautustauluun jonka avulla voidaan arpoa sopivia ääniefektejä.   
 * @author Leo Niemi
 */
public class Aanikirjasto {

    private HashMap<String, ArrayList<String>> sounds = new HashMap<>();
    private ArrayList<String> kicks;
    private ArrayList<String> claps;
    private ArrayList<String> pads;
    private ArrayList<String> bass;
    private ArrayList<String> fx;
    private Random r;

    /**
     * Kicks, claps, pads, bass ja fx-ArrayListit sisältävät /resources-mapissa olevien ääniitiedostojen nimiä formaatissa "tiedostonimi.wav".
     */
    public Aanikirjasto() {
        r = new Random();
        kicks = new ArrayList<>();
        claps = new ArrayList<>();
        pads = new ArrayList<>();
        bass = new ArrayList<>();
        fx = new ArrayList<>();

        claps.add("rytmipeli_clap.wav");
        kicks.add("rytmipeli_909kick.wav");
        pads.add("rytmipeli_padAm2.wav");
        pads.add("rytmipeli_padDm2.wav");
        pads.add("rytmipeli_padEm2.wav");
        pads.add("rytmipeli_padGm2.wav");
        bass.add("rytmipeli_BassA.wav");
        bass.add("rytmipeli_BassE.wav");
        bass.add("rytmipeli_BassG.wav");
        fx.add("rytmipeli_err1.wav");
        fx.add("rytmipeli_err2.wav");
        sounds.put("TAHTI", claps);
        sounds.put("SEURAAVA", kicks);
        sounds.put("LAATTA", bass);
        sounds.put("MOLEMMAT", pads);
        sounds.put("VIRHE", fx);
    }

    /**
     * Palauttaa sattumanvaraisen äänen äänikirjastosta.
     *
     * @param type Oletusarvoisesti kick, clap, pad, bass tai fx
     * @return tiedostonimi.wav
     */
    public String getSound(String type) {
        ArrayList<String> list = sounds.get(type);
        try {
            return list.get(r.nextInt(list.size()));
        } catch (Exception e) {
            System.out.println("Virhe äänikirjastossa: " + e.getMessage());
        }
        return "Virhe";
    }

}
