# Ohjelmistotuotannon harjoitustyö - Rytmipeli 

## Kuvaus

Sovellus on yhden pelaajan reaaliaikainen rytmipeli, jonka perusidea pohjautuu Laatta-leikkiin ja polyrytmiikan harjoitteluun.

Tässä leikissä pelaajat lausuvat vuorotellen numeroita. Jos numero on jaollinen seitsemällä (n % 7 == 0) tai sisältää merkin 7 (esim. 37) tulee korvata numero sanalla Laatta. Esimerkiksi:
>> 12, 13, Laatta, 15, 16, Laatta

Rytmipelissä korostetaan aluksi nelijakoista tahtia. Numerot kasvavat itsenäisesti joka sekunti. Pelaajan tulee merkitä ns. ykköstahdit (ajattele metronomia) ja laatta-iskut painalluksella. 

Pelimekaniikka toimii kolmella näppäimellä.  **A** merkitsee ykköstahtia, **B** merkitsee Laattaa (n % 7 == 0 || numero.toString.contains("7")) ja **C** täyttää *molemmat* ehdot (esimerkiksi '17'). Näppäimet olisivat klikattavissa hiirellä tai jollain sopivalla näppäimistön painikkeella.

Ensimmäiset 20 lukua olisivat siten
**A**2 3 4 **A** 6 **B** 8 **A** 10 11 12 **A** **B** 15 16 **C** 18 19 20

Peli toimii reaaliajassa. Jos pelaaja on liian hidas, tai painaa väärin, hän häviää. Muussa tapauksessa pistemäärää kasvatetaan. High score-listaa säilytetään kehityksen alkuvaiheessa tiedostossa käyttäjän koneella ja valmiissa sovelluksessa palvelimella.

Pelin käyttöliittymä, logiikka ja pisteiden säilytysjärjestelmä edustavat eri komponentteja.

## Suunnitelma
Ensimmäisen version tavoite: Komentorivipohjainen pelilogiikka (ei reaaliajassa) joka tallentaa korkeimman pistemäärän. 
Valmiin sovelluksen ominaisuudet:
- Graafinen käyttöliittymä (JavaFX)
- Reaaliaikainen toiminnallisuus
- Top-10 pisteet ja pelaajien nimet palvelimella
- Yksinkertaiset ääniefektit (metronomi)


## Mahdolliset kehityssuunnat / lisäominaisuudet:
- Vaikeustason implementointi
  - Pitkä, keskiverto, lyhyt aikaikkuna painallukselle
  - Lisäelämät
- Eri rytmit
  - **A**:n arvo muutettavissa mielivaltaiseen tahtiin: 
    - 3/4: **A** 2 3 **A** 5 6 **C** 8 9 **A** 11 12 **A** **B** 15
    - 5/4: **A** 2 3 4 **A** 6 **B** 8 9 10 **A** 12 13 **B** 15

    
Tämän projektin ulkopuolelle melko varmasti ulottuva idea olisi MIDI-liitännäisyys jolloin sovelluksen käyttöliittymää voisi helposti laajentaa vaikkapa sähkörumpuihin.

Esimerkiksi [jMusic](https://explodingart.com/jmusic/)-kirjaston tai [Supercollider](https://supercollider.github.io/)-syntetisaattorin avulla voisi myös tehdä proseduraalisesti generöityä musiikkia. Tämäkin on liian laaja haaste tähän tehtävään.
