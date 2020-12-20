# Käynnistys
Lataa viimeisin [release](https://github.com/olenleo/ot-harjoitustyo/releases/download/Loppupalautus/Rytmipeli-0.8.1.jar)
ja käynnistä se komennolla 
~~~
$ java -jar Rytmipeli.0.8.1.jar
~~~
tai vaihtoehtoisesti kopioi repositorio ja luo uusi .jar-tiedosto komennolla 
~~~
$ mvn package
~~~

Peli käynnistyy menu-näkymään jossa voi aloittaa uuden pelin tai tarkastella highscore-listaa.

# Peliohjeet
Pelissä seurataan neli- sekä seitsenjakoista tahtia. Lisähaastetta tuo se, että seitsenjakoiseen tahtiin lasketaan mukaan myös ne iskut joiden järjestysnumero sisältää luvun 7!

Pelaajan tulee siis laskea iskujen lukumäärää ja kahta tahtilajia samaan aikaan.

Peliä ohjataan klikkaamalla neljää painiketta, järjestyksessä vasemmalta oikealle:
- Uusi tahti [4/4] - iskut 1,5,9...
- Uusi isku - iskut 2,3,4,6...
- Uusi tahti [7/4] TAI isku sisältää luvun 7 - iskut 7,14
- Uusi tahti [4/4] ja joko uusi tahti [7/4] tai isku sisältää luvun 7 - isku 21...

Virhepainalluksesta menettää elämän. Miten pitkälle pääset?
