# Testausdokumentti

Ohjelmaa on testattu sekä manuaalisesti että JUnit-kirjaston automaattisilla testeillä. Testiraportti generoidaan komennolla 
~~~ 
$ mvn test jacoco:report 
~~~

# Manuaalinen testaus
Olen testannut että ohjelma toimii sekä windows 10:llä että seuraavilla Linux-pohjaisilla käyttöjärjestelmillä:
- Ubuntu 20.04
- Laitoksen Cubbli-linux (Testattu etätyöpöydällä) 

**HUOM** Viimeisin [release](https://github.com/olenleo/ot-harjoitustyo/releases/tag/Loppupalautus) toimii vain Linuxilla. Windows-käyttäjä joutuu luomaan oman .jar-tiedoston.

# JUnit-yksikkötestaus 
Ohjeitten mukaan graafista käyttöliittymää ei testata automaattisesti.
## Sovelluslogiikka - SovellusLogiikkaTest.java
Sovelluslogiikkaa ohjaavan luokan toiminnallisutta testataan automaattisilla testeillä jotka simuloivat pelin mahdollisia tilanteita.

## Äänikirjasto - AaniKirjastoTest.java
Äänikirjastoa testataan tarkastamalla äänikirjaston palauttamaa tiedostoa eri tilanteissa.

## HighScoreManager - HighScoreTest.java
Luodaan HighScoreManager ja annetaan sille parametriksi testitiedosto mock.txt. Tätä luokkaa testataan kolmella tapauksella: 
- Konstruktori toimii oikein
- WriteCSV-metodi kirjoittaa Piste-olion CSV-muodossa.
- Luodaan uusi HighScoreManager ja annetaan sille parametrina olematon tiedosto.

# Testikattavuus
![Testikattavuus](https://github.com/olenleo/ot-harjoitustyo/blob/master/dokumentaatio/testikattavuus.png)
Testeissä ohitettu muutama setteri sekä Exception. Olen testannut tiedostonluontia ja -kirjoittamista myös manuaalisesti.
