/*
 */
package rytmipeli;


import sovelluslogiikka.SovellusLogiikka;
import rytmipeli.tekstiui.TekstiKayttoliittyma;
public class Main {

    public static void main(String args[]) {
        SovellusLogiikka s = new SovellusLogiikka();
        TekstiKayttoliittyma tk = new TekstiKayttoliittyma();
        tk.kaynnista();
    }

}
