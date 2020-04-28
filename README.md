# **Klondike-pasianssi**

Sovellus on klondike-pasianssi, jota käyttäjät pysytyvät pelaamaan helpoimmalla vaikeusasteella.


## **Dokumentaatio**

[Käyttöohje](https://github.com/VolmarKa/otmPasianssi/blob/master/dokumentaatio/K%C3%A4ytt%C3%B6ohje.md)

[Vaatimusmäärittely](https://github.com/VolmarKa/otmPasianssi/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/VolmarKa/otmPasianssi/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/VolmarKa/otmPasianssi/blob/master/dokumentaatio/arkkitehtuuri.md)

## **Releaset**

[Viikko 5](https://github.com/VolmarKa/otmPasianssi/releases/tag/viikko5)

[Viikko 6](https://github.com/VolmarKa/otmPasianssi/releases/tag/Viikko6)

## **Komentorivitoiminnot**

### Testaus

Testit suoritetaan komennolla

```
mvn test
```
Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```
### Suoritettavan jarin generointi

komento

```
mvn package
```
generoi hakemistoon target suoritettavan jar-tiedoston KlondikePasianssi-1.0-SNAPSHOT.jar

### Checkstyle

Tiedoston [checkstyle.xml](https://github.com/VolmarKa/otmPasianssi/blob/master/KlondikePasianssi/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

### JavaDoc

Komennolla

```
mvn javadoc:javadoc
```

saadaan generoitua tiedosto *target/site/apidocs/index*, jota voi tarkastella selaimella.

### Ohjelman suorittaminen

Ohjelman voi suorittaa komennolla

```
 mvn compile exec:java -Dexec.mainClass=klondikepasianssi.Main
```
