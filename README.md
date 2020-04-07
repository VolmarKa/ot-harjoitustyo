# **Klondike-pasianssi**




## **Dokumentaatio**

[Vaatimusmäärittely](https://github.com/VolmarKa/otmPasianssi/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/VolmarKa/otmPasianssi/blob/master/dokumentaatio/tuntikirjanpito.md)

## **Komentorivitoiminnot**

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

### Checkstyle

Tiedoston [checkstyle.xml](https://github.com/VolmarKa/otmPasianssi/blob/master/KlondikePasianssi/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

### Ohjelman suorittaminen

Ohjelman voi suorittaa komennolla

```
 mvn compile exec:java -Dexec.mainClass=klondikePasianssi.Main
```
