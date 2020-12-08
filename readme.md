# RYTMIPELI

Rytmipeli perustuu nelitahtisen rytmin seuraamiseen yhdessä seitsentahtisen rytmin kanssa. Lisähaastetta tuo että jokainen numero joka sisältää merkin '7' lasketaan myös. Tämä ei ole (vielä) reaaliaikainen toteutus; lisätietoa vaatimusmäärittelystä alla.

Tämä repositorio liittyy Helsingin yliopiston kurssiin *Ohjelmistotekniikka 2020*.

# Viimeisin release
Saatavilla [tästä.](https://github.com/olenleo/ot-harjoitustyo/releases/tag/v.0.2-beta)

# HUOMIO JAVAN VERSIOISTA:
Sovellus toimii Java 11:lla myös laitoksen laitteilla [Testattu 17.11].

# Dokumentaatio
- Vaatimusmäärittely: [vaatimusmaarittely.md.](https://github.com/olenleo/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)
- Työaikakirjanpito: [tyoaikakirjanpito.md](https://github.com/olenleo/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md) 
- Arkkitektuuri: [arkkitektuuri.md](https://github.com/olenleo/ot-harjoitustyo/blob/master/dokumentaatio/arkkitektuuri.md)

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



Käyttäjä: [Olenleo](https://github.com/olenleo)

Repositorion [osoite](https://github.com/olenleo/ot-harjoitustyo)

