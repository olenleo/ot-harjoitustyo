/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

/**
 *
 * @author olenleo (Leo Niemi)
 */
public class SovellusLogiikka {

    private int luku;
    private int a;
    private int laatta;
    private String charLaatta;

    public SovellusLogiikka() {
        this.luku = 0;
        this.a = 4;
        this.laatta = 7; // TODO toiminnallisuus polyrytmiikan muuttamiseen
        this.charLaatta = "7";

    }

    public void kasvataLukua() {
        this.luku++;
    }

    public int getLuku() {
        return this.luku;
    }

    public boolean tarkistaTahti(int n) {
        return (n % 4 + 1 == 0); // iskut 1, 5, 9
    }

    public boolean tarkistaMod7(int n) {
        return (n % 7 == 0);
    }

    public boolean tarkistaMerkkiJono(int n) {
        return (Integer.toString(n).equals(charLaatta));
    }

    public int tarkistaLaatta(int n) {
        if ((tarkistaMerkkiJono(n) || tarkistaMod7(n)) && tarkistaTahti(n)) {
            return 3; // jos sekä Laatta että Tahti, palauta 2
        } else if (tarkistaMerkkiJono(n) || tarkistaMod7(n)) {
            return 2; // jos vain Laatta palauta 2
        } else if (tarkistaTahti(n)) {

            return 1; // jos vain Tahti palauta 1
        }
        return 0; // ei mikään erikoistapaus, jatketaan.
    }
}
