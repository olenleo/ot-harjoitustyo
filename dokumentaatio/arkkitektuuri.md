# Rakenne
Pakkaukset:
- rytmipeli.kayttoliittyma sisältää JavaFX-pohjaisen käyttöliittymän sekä pelin painikkeet luovan luokan Nappi.java.
- rytmipeli.sovelluslogiikka ajaa pelin sovelluslogiikkaa.
- rytmipeli.aanikirjasto sisältää viittaukset pelin .wav-tiedostoihin. Tässä luokassa pystyy manipuloimaan mitä ääniefektejä haluaa kytkeä minkä painikkeen alle.
- rytmipeli.pisteet hallinnoi highscore-listaa.


![Kaavio](https://github.com/olenleo/ot-harjoitustyo/blob/master/dokumentaatio/Luokkakaavio.jpg)



## rytmipeli.kayttoliittyma
Käyttöliittymä sisältää kolme Scene-oliota:
- sceneMenu (oletus) tarjoaa seuraavat toiminnot:
  - Uuden pelin käynnistäminen (alustaa sovelluslogiikan)
  - Highscore-listan näyttäminen (alla)
- sceneGame
  - Nappi-olioiden eventHandlerit ohjaavat sovelluslogiikkaa
    - Käyttöliittymän label-oliot saavat tiedot pistetilanteesta ja elämistä sovelluslogiikalta
    - Nappi-olion eventhandler vertailee k. napin tyyppiä ja sovelluslogiikan tarkistaLaatta()-metodia => sovelluslogiikka kasvattaa pisteitä tai vähentää elämiä.
  - Nappi-olioiden eventHandlerit ohjaavat ääniä
    - Eventhandler toistaa äänikirjaston arpoman äänen
- sceneScore
  - Näkymä esittää highscore-listan järjestetyssä TableView-muodossa
  - Mahdollisuus aloittaa uusi peli
  
  Kaikkien Nappi-olioiden graafista ulkoasua hallinnoidaan buttonCSS.css-tiedostolla.
  
  Käyttöliittymä-pakkauksessa myös luokat näppäimille (Nappi.java) sekä TableView:iä hallinnoiva HighScoreTableView-luokka.
  
## rytmipeli.aanikirjasto
Luokka sisältää viitteet .wav-tiedostoihin järjestettynä tyypin mukaan sekä random-olion sopivien ääniefektien arpomista varten.
- HashMap sounds
  - Sisältää ArrayListejä joiden indekseissä "tiedostonimi.wav".

## rytmipeli.sovelluslogiikka
Luokka sisältää metodeja pelin sääntöjä sekä pisteytystä varten.
Sääntöjen suhteen: 

**tarkistaLaatta()**-metodi vastaanottaa kokonaisluvun ja palauttaa merkkijonon. Jos käyttäjän painallus on virheellinen, vähennetään elämä. Muuten kasvatetaan luku-muuttujaa ja tehdään uusi tarkistus. 

**AsetaLuku()**-metodi on toistaiseksi mukana vain automaattisia testejä varten.

Muuttujat *tahti* ja *polyRytmiMuuttuja* ohjaavat pelin polyrytmisiä sääntöjä. 

# Tiedostojen pysyväistallennus:
## rytmipeli.pisteet
- HighScoreManager-luokka hallinnoi .csv-tiedostojen kirjoittamista ja lukua.
- Piste-luokka luo olioita jotka muistavat pelaajan tuloksen SimpleIntegerProperty-muodossa, jotta TableView voisi järjestää tulokset suuruusjärjestykseen.

Tulokset tallennetaan pisteet.txt-tiedostoon sovelluksen juurikansiossa CSV-muodossa, esimerkiksi
~~~
Erkki Esimerkki,20
Elias Exempel,23
~~~

Nimessä ei hyväksytä pilkkua ','. 

# Sekvenssikaavio toiminnallisuudesta
![Sekvenssikaavio](https://github.com/olenleo/ot-harjoitustyo/blob/master/dokumentaatio/lataus.png)

# Rakenteen heikkoudet
Tiedostotallennus on ratkaistu hyvin naiivilla tavalla [1]. Suunnittelin palvelimella sijaitsevaa sql-tietokantaa mutta aika loppui kesken.
Äänitiedoston toisto tulisi ulkoistaa erilliseen, ehkäpä äänikirjaston alaiseen luokkaan.




[1] Tämä johtuu siitä, että käytin luvattoman paljon aikaa ratkaisuun .jar-tiedoston sisäisten tiedostojen muokkaamiseen ennen kuin annoin periksi ja ulkoistin tallennuksen.
