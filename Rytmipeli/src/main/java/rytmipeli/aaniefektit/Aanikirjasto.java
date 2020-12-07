/*
Tämä luokka lataa ääniefektit
 */
package rytmipeli.aaniefektit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author olenleo
 */
public class Aanikirjasto {

    private HashMap<String, ArrayList<String>> sounds = new HashMap<>();
    private ArrayList<String> kicks;
    private ArrayList<String> claps;
    private ArrayList<String> pads;
    private ArrayList<String> bass;
    private Random r;

    public Aanikirjasto() {
        r = new Random();
        kicks = new ArrayList<>();
        claps = new ArrayList<>();
        pads = new ArrayList<>();
        bass = new ArrayList<>();

        claps.add("rytmipeli_clap.wav");
        kicks.add("rytmipeli_909kick.wav");
        pads.add("rytmipeli_padAm2.wav");
        pads.add("rytmipeli_padDm2.wav");
        pads.add("rytmipeli_padEm2.wav");
        pads.add("rytmipeli_padGm.wav");
        bass.add("rytmipeli_bassA.wav");
        bass.add("rytmipeli_bassE.wav");
        bass.add("rytmipeli_bassG.wav");

        sounds.put("TAHTI", claps);
        sounds.put("SEURAAVA", kicks);
        sounds.put("LAATTA", bass);
        sounds.put("MOLEMMAT", pads);
    }

    public String getSound(String type) {
        ArrayList<String> list = sounds.get(type);
        try {
            return list.get(r.nextInt(list.size()));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

}
