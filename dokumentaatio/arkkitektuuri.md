Ohjelman rakenne (tällä hetkellä).

# Rakenne
Pakkaukset:
- rytmipeli.kayttoliittyma sisältää JavaFX-pohjaisen käyttöliittymän sekä pelin painikkeet luovan luokan Nappi.java.
- rytmipeli.sovelluslogiikka ajaa pelin sovelluslogiikkaa.
- rytmipeli.aanikirjasto sisältää viittaukset pelin .wav-tiedostoihin. Tässä luokassa pystyy manipuloimaan mitä ääniefektejä haluaa kytkeä minkä painikkeen alle.

![Kaavio](https://github.com/olenleo/ot-harjoitustyo/blob/master/dokumentaatio/rytmipelikaavio.jpg)


High score-toiminnallisuus vielä lisäämättä sovellukseen.


# Käyttöliittymä
Käyttöliittymä sisältää kaksi Scene-oliota:
- sceneMenu (oletus) tarjoaa seuraavat toiminnot:
  - Uuden pelin käynnistäminen (alustaa sovelluslogiikan)
  - Highscore-lista (*työn alla*)
- sceneGame
  - Nappi-olioiden eventHandlerit ohjaavat sovelluslogiikkaa
    - Käyttöliittymän label-oliot saavat tiedot pistetilanteesta ja elämistä sovelluslogiikalta
    - Nappi-olion eventhandler vertailee k. napin tyyppiä ja sovelluslogiikan tarkistaLaatta()-metodia => sovelluslogiikka kasvattaa pisteitä tai vähentää elämiä.
  - Nappi-olioiden eventHandlerit ohjaavat ääniä
    - Äänikirjasto arpoo ja palauttaa sopivan ääniefektin eventhandlerille. 
    
  
# Äänikirjasto
Luokka sisältää viitteet .wav-tiedostoihin järjestettynä tyypin mukaan sekä random-olion sopivien ääniefektien arpomista varten.
- HashMap sounds
  - Sisältää ArrayListejä joiden indekseissä "tiedostonimi.wav".

# Sovelluslogiikka
Luokka sisältää metodeja pelin sääntöjä sekä pisteytystä varten.
Sääntöjen suhteen: 

**tarkistaLaatta()**-metodi vastaanottaa kokonaisluvun ja palauttaa merkkijonon. Jos käyttäjän painallus on virheellinen, vähennetään elämä. Muuten kasvatetaan luku-muuttujaa ja tehdään uusi tarkistus. 

**AsetaLuku()**-metodi on toistaiseksi mukana vain automaattisia testejä varten.

Muuttujat *tahti* ja *laatta* ohjaavat pelin polyrytmisiä sääntöjä. 





# Alla koodi yuml.me:tä varten.
Siivoan pois lopullisessa palautuksessa.
[Main] -> 1[Käyttöliittymä]
[Käyttöliittymä] 1->* [Nappi]
[Käyttöliittymä] -> 1[Sovelluslogiikka] 1<->* [Nappi]
[Sovelluslogiikka]* <-> 1[Highscorelista | - palvelimella; - tiedostossa] -> [Käyttöliittymä]
[Nappi]*<-1[Äänikirjasto]
