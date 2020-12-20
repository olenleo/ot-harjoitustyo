package rytmipeli.sovelluslogiikka;

/**
 * Tässä luokassa toteutetaan pelilogiikan tarkistukset.
 *
 *
 */
public class ApplicationLogic {

    private int currentBeat;
    private final int primaryBeat;
    private final int polyrhythmicBeat;
    private final String polyrhythmicBeatAsChar;
    private int lives;

    /**
     * Konstruktori alustaa tarvittavat muuttujat.
     */
    public ApplicationLogic() {
        this.currentBeat = 1;
        this.primaryBeat = 4;
        this.polyrhythmicBeat = 7;
        this.polyrhythmicBeatAsChar = Integer.toString(this.polyrhythmicBeat);
        this.lives = 3;
        this.currentBeat = 1;
    }

    /**
     * Uuden pelin käynnistys. Alustaa elämät ja pisteet alkuarvoihin.
     */
    public void initializeGame() {
        this.lives = 3;
        this.currentBeat = 1;
    }

    public int getLives() {
        return this.lives;
    }

    /**
     * Vähentää elämän.
     */
    public void reduceLives() {
        this.lives--;
    }

    /**
     * Yksikkötestausta varten.
     *
     * @param n Aseta haluttu currentBeat.
     */
    public void setCurrentBeat(int n) { // testausta varten
        this.currentBeat = n;
    }

    /**
     * Kasvattaa vuorossa olevaa kokonaislukua.
     */
    public void increaseCurrentBeat() {
        this.currentBeat++;
    }

    public int getCurrentBeat() {
        return this.currentBeat;
    }

    /**
     * Metodi tarkistaa ensimmäisen tahtilajn ykkösiskun.
     *
     * @param n Tarkistettava kokonaisluku
     * @return TRUE jos kyseessä on ns. ykköstahti 4/4-tahtilajissa (1,5,9, ...
     * , n)
     */
    public boolean checkPrimaryBeat(int n) {
        return ((n - 1) % primaryBeat == 0); // iskut 1, 5, 9
    }

    /**
     * Metodi tarkistaa toisen tahtilajin ykkösiskun.
     *
     * @param n Tarkistettava kokonaisluku
     * @return TRUE jos kyseessä on ns. ykköstahti 7/4-tahtilajissa (1,7,14, ...
     * , n)
     */
    public boolean checkMod7(int n) {
        return (n % 7 == 0);
    }

    /**
     * Metodi tarkistaa jos currentBeat sisältää etsityn merkin.
     *
     * @see tarkistaLaatta#checkCurrentBeat
     * @param n Tarkistettava kokonaisluku
     * @return TRUE jos n sisältää merkin '7'
     */
    public boolean checkStringForPolyrhytm(int n) {
        return (Integer.toString(n).contains(polyrhythmicBeatAsChar));
    }

    /**
     * Vastaanottaa kokonaisluvun, palauttaa ohjeet k. luvun käsittelylle
     * pelisääntöjen mukaan.
     *
     * @param n Tarkistettava kokonaisluku.
     * @return Merkkijono jota vertaillaan käyttäjän syötteeseen.
     */
    public String checkCurrentBeat(int n) {
        if ((checkStringForPolyrhytm(n) || checkMod7(n)) && checkPrimaryBeat(n)) {
            return "MOLEMMAT";
        } else if (checkStringForPolyrhytm(n) || checkMod7(n)) {
            return "LAATTA"; // Polyrytmi
        } else if (checkPrimaryBeat(n)) {
            return "TAHTI"; // jos vain Primarybeat
        }
        return "SEURAAVA"; // ei mikään erikoistapaus, jatketaan.
    }
}
