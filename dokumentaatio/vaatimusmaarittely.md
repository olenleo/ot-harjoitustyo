# Ohjelmistotuotannon harjoitustyö - Rytmipeli 

## Kuvaus

Rytmipeli on yhden pelaajan reaaliaikainen rytmipeli, jonka perusidea pohjautuu Laatta-leikkiin ja polyrytmiikan harjoitteluun. Reaaliaikaisuus kuuluu tosin jatkotavoitteisiin.

Laatta-leikissä pelaajat lausuvat vuorotellen numeroita. Jos numero on jaollinen seitsemällä (n % 7 == 0) tai sisältää merkin 7 (esim. 37) tulee korvata numero sanalla Laatta. Esimerkiksi:
>> 12, 13, Laatta, 15, 16, Laatta

Rytmipelissä korostetaan aluksi nelijakoista tahtia. Numerot kasvavat itsenäisesti joka sekunti. Pelaajan tulee merkitä ns. ykköstahdit (ajattele metronomia) ja laatta-iskut painalluksella. 

Pelimekaniikka toimii kolmella näppäimellä.  **A** merkitsee ykköstahtia, **B** merkitsee Laattaa (n % 7 == 0 || numero.toString.contains("7")) ja **C** täyttää *molemmat* ehdot (esimerkiksi '17'). Näppäimet olisivat klikattavissa hiirellä tai jollain sopivalla näppäimistön painikkeella - kyseessä ei siis välttämättä ole näppäimistön merkit A,B ja C.

Ensimmäiset 20 lukua olisivat:
**A**2 3 4 **A** 6 **B** 8 **A** 10 11 12 **A** **B** 15 16 **C** 18 19 20

 Jos pelaaja on liian hidas, tai painaa väärin, hän häviää. Muussa tapauksessa pistemäärää kasvatetaan. High score-listaa säilytetään kehityksen alkuvaiheessa tiedostossa käyttäjän koneella ja valmiissa sovelluksessa palvelimella. Koska toteutan ei-reaaliaikaisen version tavoitteena on siis peli jossa väärästä painalluksesta yksinkertaisesti häviää.

Pelin käyttöliittymä, logiikka ja pisteiden säilytysjärjestelmä edustavat eri komponentteja.

## Suunnitelma
Ensimmäisen version tavoite: Komentorivipohjainen pelilogiikka joka tallentaa paikallisesti korkeimman pistemäärän. 


## Valmiin sovelluksen ominaisuudet:
- Graafinen käyttöliittymä (JavaFX) - **TEHTY** (Viimeistelyä tarvitaan vielä)
- Top-10 pisteet ja pelaajien nimet palvelimella **ALOITETTU** (Lisätty viimeisin tulos sovelluksen muistiin)
- Alkumenu jossa voi tarkastella high scorea, aloittaa uuden pelin tai sulkea sovelluksen. (TEHTY osittain - ks edellinen, jätän pois sulkemisnapin)
- Muutama ääniefekti


## Mahdolliset kehityssuunnat / lisäominaisuudet:
- Reaaliaikainen toteutus
- Vaikeustason implementointi
  - Pitkä, keskiverto, lyhyt aikaikkuna painallukselle
  - Lisäelämät (Asetettu 3 elämää)
- Eri rytmit
  - **A**:n arvo muutettavissa mielivaltaiseen tahtiin: 
    - 3/4: **A** 2 3 **A** 5 6 **C** 8 9 **A** 11 12 **A** **B** 15
    - 5/4: **A** 2 3 4 **A** 6 **B** 8 9 10 **A** 12 13 **B** 15

    
Tämän projektin ulkopuolelle melko varmasti ulottuva idea olisi MIDI-liitännäisyys jolloin sovelluksen käyttöliittymää voisi helposti laajentaa vaikkapa sähkörumpuihin.

Esimerkiksi [jMusic](https://explodingart.com/jmusic/)-kirjaston tai [Supercollider](https://supercollider.github.io/)-syntetisaattorin avulla voisi myös tehdä proseduraalisesti generöityä musiikkia. Tämäkin on liian laaja haaste tähän tehtävään.
