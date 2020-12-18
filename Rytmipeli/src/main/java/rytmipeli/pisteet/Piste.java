/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rytmipeli.pisteet;

public class Piste implements Comparable<Piste> {

    private String nimi;
    private int pisteet;

    /**
     * Konstruktori piste-oliolle.
     *
     * @param nimi Pelaajan nimi
     * @param pisteet pelaajan savuttama pistemäärä.
     */
    public Piste(String nimi, int pisteet) {
        this.nimi = nimi;
        this.pisteet = pisteet;
    }

    public String getNimi() {
        return nimi;
    }

    public int getPisteet() {
        return pisteet;
    }

    @Override
    public int compareTo(Piste toinenPiste) {
        return Integer.compare(this.getPisteet(), toinenPiste.getPisteet());
    }

    @Override
    public String toString() {
        return " " + this.nimi + " " + this.pisteet;
    }
}
