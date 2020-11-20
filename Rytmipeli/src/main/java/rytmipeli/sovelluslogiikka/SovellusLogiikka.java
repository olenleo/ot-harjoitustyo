package rytmipeli.sovelluslogiikka;

/**
 * Tässä luokassa toteutetaan pelin tarkistukset
 *
 *
 */
public class SovellusLogiikka {

    private int luku;
    private int tahti;
    private int laatta;
    private String charLaatta; // muuttujana jotta luku olisi vaihdettavissa

    public SovellusLogiikka() {
        this.luku = 1;
        this.tahti = 4;  // TODO toiminnallisuus polyrytmiikan muuttamiseen
        this.laatta = 7; // TODO toiminnallisuus polyrytmiikan muuttamiseen
        this.charLaatta = "7";

    }

    public void asetaLuku(int n) { // testausta varten
        this.luku = n;
    }

    public void kasvataLukua() {
        this.luku++;
    }

    public int getLuku() {
        return this.luku;
    }

    public boolean tarkistaTahti(int n) {
        return ((n - 1) % tahti == 0); // iskut 1, 5, 9
    }

    public boolean tarkistaMod7(int n) {
        return (n % 7 == 0);
    }

    public boolean tarkistaMerkkiJono(int n) {
        return (Integer.toString(n).contains(charLaatta));
    }

    public String tarkistaLaatta(int n) {
        if ((tarkistaMerkkiJono(n) || tarkistaMod7(n)) && tarkistaTahti(n)) {
            return "MOLEMMAT";
        } else if (tarkistaMerkkiJono(n) || tarkistaMod7(n)) {
            return "LAATTA"; // jos vain Laatta
        } else if (tarkistaTahti(n)) {
            return "TAHTI"; // jos vain Tahti
        }
        return "SEURAAVA"; // ei mikään erikoistapaus, jatketaan.
    }
}
