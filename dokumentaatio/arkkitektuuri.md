![Kaavio](https://github.com/olenleo/ot-harjoitustyo/blob/master/dokumentaatio/rytmipelikaavio.jpg)


High score-toiminnallisuus vielä lisäämättä sovellukseen.
Jäi vähän epäselväksi haetaanko tässä lopullista vai tämän hetkistä arkkitektuuria.

*Kysymys:* Onko *Käyttöliittymä -> Nappi*-merkintä oikein, kun käyttöliittymä luo napit muttei muuten vaikuta niiden toimintaan?

# Alla koodi yuml.me:tä varten.

[Main] -> [Käyttöliittymä]
[Käyttöliittymä] -> [Nappi]
[Käyttöliittymä] -> [Sovelluslogiikka] 1<->* [Nappi]
[Sovelluslogiikka]* <-> 1[Highscorelista | - palvelimella; - tiedostossa] -> [Käyttöliittymä]
[Main]-.-[note: Ei käytössä. \n Mutta varmuuden vuoksi mukana.{bg:yellow}] -.-  [Tekstikäyttöliittymä]
