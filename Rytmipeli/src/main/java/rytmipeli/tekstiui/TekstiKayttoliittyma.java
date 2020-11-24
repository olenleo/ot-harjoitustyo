package rytmipeli.tekstiui;

/*
    Varsinainen käyttöliittymä rytmipeli.kayttoliittyma-pakkauksessa!
    Säilytän tämän mukana jos sille käyttöä myöhemmin ilmenee. 
*/
import java.util.Scanner;
import rytmipeli.sovelluslogiikka.SovellusLogiikka;

public class TekstiKayttoliittyma {

    rytmipeli.sovelluslogiikka.SovellusLogiikka sl;
    Scanner lukija;
    private String komento;
    private int seuraava;
    
    public TekstiKayttoliittyma() {
        sl = new SovellusLogiikka();
        lukija = new Scanner(System.in);  
    }
    
    public String getKomento() {
        return this.komento;
    }

    public void kaynnista() {
        System.out.println("Uusi peli!");
        System.out.println("z = ykköstahti, c = laatta, v = molemmat, x = ei erikoistapauksia");
        while (true) {
            seuraava = sl.getLuku();
            System.out.println("Seuraava: " + seuraava);
            System.out.println("z = [1/4] beat, x = neXt, c = Laatta, v == laatta + beat");
            komento = luettuKomento();
            // syötetään seuraava sovelluslogiikalle ja katsotaan mitä se palauttaa!
            if (sl.tarkistaLaatta(seuraava).equals(komento)) {
            sl.kasvataLukua();    
            }
            else {
                System.out.println("Virhe! Yritä uudestaan!");
            }
            if (komento.equals("EXIT")) {
                break;
            }
        }
    }

    public String luettuKomento() {
        switch (lukija.nextLine()) {
            case "z":
                return "TAHTI";
            case "x":
                return "SEURAAVA";
            case "c":
                return "LAATTA";
            case "v": // molemmat
                return "MOLEMMAT";
            case "q":
                return "EXIT";
            default:
                return "Jotain pielessä";
        }
    }
}
