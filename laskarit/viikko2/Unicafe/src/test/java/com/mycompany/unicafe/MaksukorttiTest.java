package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(20);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void kortillaOnAluksiOikeaSaldo() {
        assertEquals("saldo: 0.20", kortti.toString());
    }

    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(100);
        assertEquals("saldo: 1.20", kortti.toString());
    }

    @Test
    public void saldoVaheneeOikeinJosOnRahaa() {
        kortti.otaRahaa(5);
        assertEquals("saldo: 0.15", kortti.toString());
    }

    @Test
    public void saldoEiMuutuJosEiTarpeeksiRahaa() {
        kortti.otaRahaa(1000);
        assertEquals("saldo: 0.20", kortti.toString());
    }

    @Test
    public void metodiPalauttaaOikeanTotuusarvon1() {
        boolean totta = true;
        assertEquals(totta, kortti.otaRahaa(5));
    }

    @Test
    public void metodiPalauttaaOikeanTotuusarvon2() {
        boolean eitotta = false;
        assertEquals(eitotta, kortti.otaRahaa(1000));
    }
    

    
}
