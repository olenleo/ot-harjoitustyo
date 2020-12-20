# Ohjelmistotuotannon harjoitustyö - Rytmipeli 

## Kuvaus

Rytmipeli on yhden pelaajan reaaliaikainen rytmipeli, jonka perusidea pohjautuu Laatta-leikkiin ja polyrytmiikan harjoitteluun. Reaaliaikaisuus kuuluu tosin jatkotavoitteisiin.

Laatta-leikissä pelaajat lausuvat vuorotellen numeroita. Jos numero on jaollinen seitsemällä (n % 7 == 0) tai sisältää merkin 7 (esim. 37) tulee korvata numero sanalla Laatta. Esimerkiksi:
>> 12, 13, Laatta, 15, 16, Laatta

Rytmipelissä korostetaan neli- ja seitsenjakoista tahtia. Pelaajan tulee merkitä sekä 4/4-tahtien ja 7/4-tahtien ns. ykkösiskut painalluksella. 

Pelimekaniikka toimii neljällä näppäimellä.  **A** merkitsee alkavaa 4/4-tahtia, **B** alkavaa 7/4-tahtia *tai* merkkiä 7 luvussa (n % 7 == 0 || numero.toString.contains("7")), **C** täyttää *molemmat* ehdot (esimerkiksi '17') ja **D** yksinkertaisesti seuraavaa iskua. Näppäimet ovat klikattavissa hiirellä.

Ensimmäiset 20 lukua olisivat:
**A** 2 3 4 **A** 6 **B** 8 **A** 10 11 12 **A** **B** 15 16 **C** 18 19 20

Jos pelaaja painaa väärin, hän menettää elämän. Muussa tapauksessa pistemäärää kasvatetaan. High score-listaa säilytetään  tiedostossa käyttäjän koneella. Pelin päättyessä pelaaja syöttää nimensä jolloin tulos lisätään highscore-listalle.

Pelin käyttöliittymä, logiikka ja pisteiden säilytysjärjestelmä edustavat eri komponentteja.


## Sovelluksen ominaisuudet
- Graafinen käyttöliittymä (JavaFX)
- Highscore-lista tallennettuna paikallisesti
- Alkumenu jossa voi tarkastella high scorea tai aloittaa uuden pelin
- Muutama ääniefekti


## Mahdolliset kehityssuunnat / lisäominaisuudet:
- Reaaliaikainen toteutus
- Vaikeustason implementointi
  - Pitkä, keskiverto, lyhyt aikaikkuna painallukselle
  - Lisäelämät
- Eri rytmit
  - **A**:n arvo muutettavissa mielivaltaiseen tahtiin: 
    - 3/4: **A** 2 3 **A** 5 6 **C** 8 9 **A** 11 12 **A** **B** 15
    - 5/4: **A** 2 3 4 **A** 6 **B** 8 9 10 **A** 12 13 **B** 15
- Pistetilaston tallentaminen palvelimelle
- Uusien ääniefektien lisääminen
    
Tämän projektin ulkopuolelle melko varmasti ulottuva idea olisi MIDI-liitännäisyys jolloin sovelluksen käyttöliittymää voisi helposti laajentaa vaikkapa sähkörumpuihin.

Esimerkiksi [jMusic](https://explodingart.com/jmusic/)-kirjaston tai [Supercollider](https://supercollider.github.io/)-syntetisaattorin avulla voisi myös tehdä proseduraalisesti generöityä musiikkia. Tämäkin on liian laaja haaste tähän tehtävään.
