package rytmipeli.sovelluslogiikka;

/**
 * Tässä luokassa toteutetaan pelilogiikan tarkistukset.
 *
 *
 */
public class SovellusLogiikka {

    private int luku;
    private int tahti;
    private int polyRytmiMuuttuja;
    private String charPolyRytmi;
    private int elamat;

    /**
     * Konstruktori alustaa tarvittavat muuttujat.
     */
    public SovellusLogiikka() {
        this.luku = 1;
        this.tahti = 4;
        this.polyRytmiMuuttuja = 7;
        this.charPolyRytmi = Integer.toString(this.polyRytmiMuuttuja);
        this.elamat = 3;
        this.luku = 1;
    }

    /**
     * Uuden pelin käynnistys. Alustaa elämät ja pisteet alkuarvoihin.
     */
    public void alustaPeli() {
        this.elamat = 3;
        this.luku = 1;
    }

    public int getElamat() {
        return this.elamat;
    }

    /**
     * Vähentää elämän.
     */
    public void vahennaElama() {
        this.elamat--;
    }

    /**
     * Yksikkötestausta varten.
     *
     * @param n Aseta haluttu luku.
     */
    public void asetaLuku(int n) { // testausta varten
        this.luku = n;
    }

    /**
     * Kasvattaa vuorossa olevaa kokonaislukua.
     */
    public void kasvataLukua() {
        this.luku++;
    }

    public int getLuku() {
        return this.luku;
    }

    /**
     * Metodi tarkistaa ensimmäisen tahtilajn ykkösiskun.
     *
     * @param n Tarkistettava kokonaisluku
     * @return TRUE jos kyseessä on ns. ykköstahti 4/4-tahtilajissa (1,5,9, ...
     * , n)
     */
    public boolean tarkistaTahti(int n) {
        return ((n - 1) % tahti == 0); // iskut 1, 5, 9
    }

    /**
     * Metodi tarkistaa toisen tahtilajin ykkösiskun.
     *
     * @param n Tarkistettava kokonaisluku
     * @return TRUE jos kyseessä on ns. ykköstahti 7/4-tahtilajissa (1,7,14, ...
     * , n)
     */
    public boolean tarkistaMod7(int n) {
        return (n % 7 == 0);
    }

    /**
     * Metodi tarkistaa jos luku sisältää etsityn merkin.
     *
     * @see tarkistaLaatta
     * @param n Tarkistettava kokonaisluku
     * @return TRUE jos n sisältää merkin '7'
     */
    public boolean tarkistaMerkkiJono(int n) {
        return (Integer.toString(n).contains(charPolyRytmi));
    }

    /**
     * Vastaanottaa kokonaisluvun, palauttaa ohjeet k. luvun käsittelylle
     * pelisääntöjen mukaan.
     *
     * @param n Tarkistettava kokonaisluku.
     * @return Merkkijono jota vertaillaan käyttäjän syötteeseen.
     */
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
