/*

 */
package rytmipeli;

import rytmipeli.kayttoliittyma.Kayttoliittyma;
import rytmipeli.sovelluslogiikka.SovellusLogiikka;
import rytmipeli.tekstiui.TekstiKayttoliittyma;

public class Main {

    public static void main(String args[]) {
        TekstiKayttoliittyma tk = new TekstiKayttoliittyma();
        Kayttoliittyma ui = new Kayttoliittyma();
        ui.main(args);
        
    }

}
