/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rytmipeli.pisteet;

import javafx.beans.property.SimpleIntegerProperty;

public class Piste implements Comparable<Piste> {

    private String nimi;
    private SimpleIntegerProperty pisteet;

    /**
     * Konstruktori piste-oliolle.
     *
     * @param nimi Pelaajan nimi
     * @param pisteet pelaajan savuttama pistemäärä.
     */
    public Piste(String nimi, SimpleIntegerProperty pisteet) {
        this.nimi = nimi;
        this.pisteet = pisteet;
    }

    public String getNimi() {
        return nimi;
    }

    public Integer getPisteetFactory() {
        return pisteet.get();
    }

    @Override
    public int compareTo(Piste toinenPiste) {
        return Integer.compare(this.getPisteetFactory(), toinenPiste.getPisteetFactory());
    }

    @Override
    public String toString() {
        return " " + this.nimi + " " + this.pisteet;
    }
}
