package rytmipeli.sovelluslogiikka;

/**
 *  Tässä luokassa toteutetaan pelin tarkistukset
 * 
 * 
 */
public class SovellusLogiikka {

    private int luku;
    private int a;
    private int laatta;
    private String charLaatta; // muuttujana jotta luku olisi vaihdettavissa

    public SovellusLogiikka() {
        this.luku = 1;
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
        return ((n - 1) % 4 == 0); // iskut 1, 5, 9
    }

    public boolean tarkistaMod7(int n) {
        return (n % 7 == 0);
    }

    public boolean tarkistaMerkkiJono(int n) {
        return (Integer.toString(n).equals(charLaatta));
    }

    public String tarkistaLaatta(int n) {
        if ((tarkistaMerkkiJono(luku) || tarkistaMod7(luku)) && tarkistaTahti(luku) && n == 4) {
            System.out.println("MOLEMMAT");
            return "MOLEMMAT"; // jos sekä Laatta että Tahti, palauta 2
        } else if (tarkistaMerkkiJono(n) || tarkistaMod7(n)) {
            System.out.println("LAATTA");
            return "LAATTA"; // jos vain Laatta palauta 2
        } else if (tarkistaTahti(n)) {
            System.out.println("TAHTI");
            return "TAHTI"; // jos vain Tahti palauta 1
        }
        return "SEURAAVA"; // ei mikään erikoistapaus, jatketaan.
    }
}
