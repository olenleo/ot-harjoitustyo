# RYTMIPELI

Rytmipeli perustuu nelitahtisen rytmin seuraamiseen yhdessä seitsentahtisen rytmin kanssa. Lisähaastetta tuo että jokainen numero joka sisältää merkin '7' lasketaan myös. Tämä ei ole (vielä) reaaliaikainen toteutus; lisätietoa vaatimusmäärittelystä alla.

Tämä repositorio liittyy Helsingin yliopiston kurssiin *Ohjelmistotekniikka 2020*.

Pelin ääniefektit ovat omaa käsialaani, ja ne ovat vapaasti käytettävissä.

# Viimeisin release
Loppupalautus saatavilla [tästä.](https://github.com/olenleo/ot-harjoitustyo/releases/tag/Loppupalautus)

# HUOMIO JAVAN VERSIOISTA:
Sovellus toimii Java 11:lla myös laitoksen laitteilla [Testattu 20.12]. Windows-käyttöympäristössä .jar-tiedosto täytyy generoida itse.

# Dokumentaatio
- Vaatimusmäärittely: [vaatimusmaarittely.md.](https://github.com/olenleo/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)
- Työaikakirjanpito: [tyoaikakirjanpito.md](https://github.com/olenleo/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md) 
- Arkkitektuuri: [arkkitektuuri.md](https://github.com/olenleo/ot-harjoitustyo/blob/master/dokumentaatio/arkkitektuuri.md)
- Käyttöohje: [kayttoohje.md](https://github.com/olenleo/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)
- Testausraportti: [Testaus.md](https://github.com/olenleo/ot-harjoitustyo/blob/Loppupalautus/dokumentaatio/Testaus.md)

# Komentorivitoiminnot

Käynnistys:
~~~
$ mvn compile exec:java -Dexec.mainClass=rytmipeli.Main    
~~~
Testaus: 
~~~
$ mvn test
~~~
Testikattavuusraportti:
~~~
$ mvn test jacoco:report
~~~
Suoritettavan jarin generointi
~~~
$ mvn package
~~~
Checkstyle-tarkastus: 
~~~
$ mvn checkstyle:checkstyle
~~~
JavaDocin generointi:
~~~
$ mvn javadoc:javadoc
~~~


Käyttäjä: [Olenleo](https://github.com/olenleo)

Repositorion [osoite](https://github.com/olenleo/ot-harjoitustyo)

