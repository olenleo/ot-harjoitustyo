package rytmipeli.tekstiui;

import java.util.Scanner;
import sovelluslogiikka.SovellusLogiikka;

public class TekstiKayttoliittyma {

    sovelluslogiikka.SovellusLogiikka sl;
    Scanner lukija;
    private int komento;
    private int nykyinen;
    
    public TekstiKayttoliittyma() {
        sl = new SovellusLogiikka();
        lukija = new Scanner(System.in);
        
    }
    
    public int getKomento() {
        return this.komento;
    }

    public void kaynnista() {
        System.out.println("Uusi peli!");
        while (true) {
            nykyinen = sl.getLuku();
            System.out.println("Luku: " + nykyinen);
            System.out.println("z = [1/4] beat, x = neXt, c = Laatta, v == laatta + beat");
            komento = komento();
            if (komento == 2) {
                sl.kasvataLukua();
            } else if (komento == 5) {
                System.out.println("QUIT");
                break;
            }
            
        }
    }

    public int komento() {
        switch (lukija.nextLine()) {
            case "z":
                return 1;
            case "x":
                return 2;
            case "c":
                return 3;
            case "v":
                return 4;
            case "q":
                return 5;
            default:
                return 0;
        }
    }
}
