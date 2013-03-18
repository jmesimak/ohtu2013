package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto esitaytettyVarasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        esitaytettyVarasto = new Varasto(15, 5);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoEsitaytetynVaraston() {
        assertEquals(5, esitaytettyVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriEiLuoNegatiivistaVarastoa() {
        Varasto v = new Varasto(-5);
        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
        Varasto v2 = new Varasto(-5, 0);
        assertEquals(0, v2.getSaldo(), vertailuTarkkuus);
        assertEquals(0, v2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiiviseenVarastoonEiVoiLisataSaldoa() {
        Varasto negis = new Varasto(-100, 50);
        assertEquals(0, negis.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void isoonVarastoonMahtuuJuttujaJaSenKokoOnOikea() {
        Varasto iso = new Varasto(100, 50);
        assertEquals(50, iso.getSaldo(), vertailuTarkkuus);
        assertEquals(100, iso.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoVarastonJonkaTilavuusOnAlleAlkusaldon() {
        Varasto liianPieni = new Varasto(1, 15);
        assertEquals(1, liianPieni.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void jemmaaVarastoonNegatiivistaEiTeeMitaan() {
        Varasto supervarasto = new Varasto(15, 10);
        supervarasto.lisaaVarastoon(-10);
        assertEquals(10, supervarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaLiikaaSaldoEiMeneYliTilavuuden() {
        Varasto ylimeneva = new Varasto(15, 10);
        ylimeneva.lisaaVarastoon(10);
        assertEquals(ylimeneva.getTilavuus(), ylimeneva.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaVaikkaTaynna() {
        Varasto v = new Varasto(15, 15);
        v.lisaaVarastoon(1);
        assertEquals(15, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaaNegatiivistaEiTeeMitaan() {
        Varasto v = new Varasto(15, 15);
        double miinus = v.otaVarastosta(-2);
        assertEquals(15, v.getSaldo(), vertailuTarkkuus);
        assertEquals(0, miinus, vertailuTarkkuus);
    }

    @Test
    public void ottaaSopivasti() {
        Varasto v = new Varasto(15, 15);
        double kiva = v.otaVarastosta(3);
        assertEquals(12, v.getSaldo(), vertailuTarkkuus);
        assertEquals(3, kiva, vertailuTarkkuus);
    }

    @Test
    public void lisaaSopivasti() {
        Varasto v = new Varasto(15);
        v.lisaaVarastoon(12);
        assertEquals(12, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaaLiikaaMutteiPalautaLiikaa() {
        Varasto v = new Varasto(15, 15);
        double vastaus = v.otaVarastosta(25);
        assertEquals(15, vastaus, vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void ajaMain() {
        Main.main(new String[] { "a", "b" });
    }
    
    @Test
    public void tamaEiTeeMuutaKuinTestaaWebHookiaKoskaUusienTestienTekeminenOnKivaaJaNimienTuleeOllaPitkia() {
        System.out.println("Sinep");
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void konstr() {
        varasto = new Varasto(-1);
        varasto = new Varasto(0);
        varasto = new Varasto(1, 1);
        varasto = new Varasto(1, 2);
        varasto = new Varasto(-1, 2);
        varasto = new Varasto(-1, -1);
        varasto.toString();
    }
}