package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    Kassapaate kassapaate;
    Maksukortti kortti;

    public KassapaateTest() {
    }

    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        kortti = new Maksukortti(500);
    }

    @Test
    public void kassapaatteenRahamaaraOnAluksiOikea() {
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void lounaitaOnMyytyAluksiOikeaMaara() {
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void rahamaaraKasvaaOikeinKunSyotEdullisestiKateisella() {
        kassapaate.syoEdullisesti(500);
        assertEquals(100000 + 240, kassapaate.kassassaRahaa());
    }

    @Test
    public void rahamaaraKasvaaOikeinKunSyotMaukkaastiKateisella() {
        kassapaate.syoMaukkaasti(1000);
        assertEquals(100000 + 400, kassapaate.kassassaRahaa());
    }

    @Test
    public void vaihtorahaOnOikeaKunSyotEdullisestiKateisella() {
        assertEquals(155, kassapaate.syoEdullisesti(240 + 155));
    }

    @Test
    public void vaihtorahaOnOikeaKunSyotMaukkaastiKateisella() {
        assertEquals(198, kassapaate.syoMaukkaasti(400 + 198));
    }

    @Test
    public void myytyjenLounaidenMaaraKasvaaKunRahaRiittaaKateisella() {
        kassapaate.syoEdullisesti(500);
        kassapaate.syoMaukkaasti(1212);

        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void josRahaEiRiitaKateisellaRahamaaraEiMuutu1() {
        kassapaate.syoEdullisesti(100);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void josRahaEiRiitaKateisellaRahamaaraEiMuutu2() {
        kassapaate.syoMaukkaasti(200);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void josRahaEiRiitaKateisellaNiinRahatPalautetaan1() {
        assertEquals(142, kassapaate.syoEdullisesti(142));
    }

    @Test
    public void josRahaEiRiitaKateisellaNiinRahatPalautetaan2() {
        assertEquals(349, kassapaate.syoMaukkaasti(349));
    }

    @Test
    public void josRahaEiRiitaKateisellaNiinMyytyjenLounaidenMaaraEiMuutu() {
        kassapaate.syoEdullisesti(122);
        kassapaate.syoMaukkaasti(312);

        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void josKortillaTarpeeksiRahaaNiinSiltaVeloitetaan1(){
        kassapaate.syoEdullisesti(kortti);
        assertEquals(500-240, kortti.saldo());

    }
    
    @Test
    public void josKortillaTarpeeksiRahaaNiinSiltaVeloitetaan2(){
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(500-400, kortti.saldo());

    }
    
    @Test
    public void josKortillaTarpeeksiRahaaNiinPalautetaanTrue(){
        kortti.lataaRahaa(500);
        assertEquals(true, kassapaate.syoEdullisesti(kortti));
        assertEquals(true, kassapaate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void josKortillaTarpeeksiRahaaNiinLounaidenMaaraKasvaa(){
        kortti.lataaRahaa(500);
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void josKortillaEiTarpeeksiRahaaNiinSenRahamaaraEiMuutu(){
        kortti.otaRahaa(400);
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        
        assertEquals("saldo: 1.0", kortti.toString());
    }
    
    @Test
    public void josKortillaEiTarpeeksiRahaaNiinLounaidenMaaraEiMuutu(){
        kortti.otaRahaa(400);
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void josKortillaEiTarpeeksiRahaaNiinPalautetaanFalse(){
        kortti.otaRahaa(400);
        
        assertEquals(false, kassapaate.syoEdullisesti(kortti));
        assertEquals(false, kassapaate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void rahamaaraKassassaEiMuutuKortillaOstettaessa(){
        kortti.lataaRahaa(500);
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kortilleLadattaessaKortinJaKassanSaldoMuuttuu(){
        kassapaate.lataaRahaaKortille(kortti, 123);
        assertEquals(100000+123, kassapaate.kassassaRahaa());
        assertEquals(500+123, kortti.saldo());
    }
    
    @Test
    public void josKortilleLadataanNegatiivistaSeEiMuutaKortinJaKassanSaldoa(){
        kassapaate.lataaRahaaKortille(kortti, -929);
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(500, kortti.saldo());
    }

}
