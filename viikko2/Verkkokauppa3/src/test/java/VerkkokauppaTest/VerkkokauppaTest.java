package VerkkokauppaTest;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import ohtu.verkkokauppa.*;

public class VerkkokauppaTest {

    @Test
    public void ostoksenPaaytyttyapankinMetodiaTilisiirtoKutsutaan() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(1);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
    }

    //aloitataan asiointi, koriin lisätään koriin tuote jota varastossa on ja suoritetaan ostos (eli kutsutaan metodia kaupan tilimaksu()). 
    //varmistettava että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla
    @Test
    public void asioidaanJaSuoritetaanOstosJotaOnVarastossaJonkaJalkeenTilisiirrossaOnOikeatTiedot() {

        Pankki pankki = mock(Pankki.class);
        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        Kauppa kauppa = new Kauppa(varasto, pankki, viite);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("jerry", "12345");

        verify(pankki).tilisiirto(eq("jerry"), anyInt(), eq("12345"), anyString(), eq(5));
    }

    //aloitataan asiointi, koriin lisätään koriin kaksi eri tuotetta joita varastossa on ja suoritetaan ostos. 
    //varmistettava että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla
    @Test
    public void tehdaanSamaMitaEdellisessaMuttaKahdellaEriTuotteella() {
        Pankki pankki = mock(Pankki.class);
        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "aikuisviihdemagasiini", 3));
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        Kauppa kauppa = new Kauppa(varasto, pankki, viite);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("putin", "715517");

        verify(pankki).tilisiirto(eq("putin"), anyInt(), eq("715517"), anyString(), eq(8));
    }

    //aloitataan asiointi, koriin lisätään koriin kaksi samaa tuotetta jota on varastossa tarpeeksi ja suoritetaan ostos. 
    //varmistettava että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla
    @Test
    public void samaKuinAikaisemminKahdellaSamalla() {
        Pankki pankki = mock(Pankki.class);
        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "rekursiomaatuska", 5));
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        Kauppa kauppa = new Kauppa(varasto, pankki, viite);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("putin", "715517");

        verify(pankki).tilisiirto(eq("putin"), anyInt(), eq("715517"), anyString(), eq(10));
    }

    //aloitataan asiointi, koriin lisätään koriin tuote jota on varastossa tarpeeksi ja tuote joka on loppu ja suoritetaan ostos. 
    //varmistettava että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla
    @Test
    public void samaKuinAikaisemminYhdellaJotaOnJaToisellaJotaEiOle() {
        Pankki pankki = mock(Pankki.class);
        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "pensseli", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "telaketju", 15));
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        Kauppa kauppa = new Kauppa(varasto, pankki, viite);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("putin", "715517");

        verify(pankki).tilisiirto(eq("putin"), anyInt(), eq("715517"), anyString(), eq(5));
    }

    //varmistettava että metodin aloita asiointi kutsuminen nollaa edellisen ostoksen tiedot
    @Test
    public void varmistetaanUudenAsioinninNollaavanAikaisemmatTiedot() {
        Pankki pankki = mock(Pankki.class);
        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(13);
        when(varasto.saldo(3)).thenReturn(1);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "pensseli", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "telaketju", 15));
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "sombrero", 27));
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        Kauppa kauppa = new Kauppa(varasto, pankki, viite);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(3);
        kauppa.tilimaksu("putin", "715517");

        verify(pankki).tilisiirto(eq("putin"), anyInt(), eq("715517"), anyString(), eq(27));
    }

    //varmistettava että kauppa pyytää uuden viitenumeron jokaiselle maksutapahtumalle
    @Test
    public void viiteNumeroOnUusiJokaiselleMaksutapahtumalle() {
        Pankki pankki = mock(Pankki.class);
        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(13);
        when(varasto.saldo(3)).thenReturn(1);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "pensseli", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "telaketju", 15));
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "sombrero", 27));
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(1);
        Kauppa kauppa = new Kauppa(varasto, pankki, viite);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("putin", "715517");
        
        verify(pankki).tilisiirto(eq("putin"), anyInt(), eq("715517"), anyString(), eq(20));
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("escobarPablo", "1337");
        verify(viite, times(2)).uusi();
        verify(pankki).tilisiirto(eq("escobarPablo"), anyInt(), eq("1337"), anyString(), eq(20));
    }
}