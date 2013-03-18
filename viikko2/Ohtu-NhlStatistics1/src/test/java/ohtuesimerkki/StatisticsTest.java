package ohtuesimerkki;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jjoonia
 */
public class StatisticsTest {

    Statistics stats;
    Reader readerStub = new Reader() {
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    public StatisticsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void loytyykoOlemassaOlevaPelaaja() {
        Player p = stats.search("Semenko");
        Player semenko = new Player("Semenko", "EDM", 4, 12);
        assertEquals(p.toString(), semenko.toString());
    }
    
    @Test
    public void palauttaakoOlematonPelajaaNull() {
        assertEquals(stats.search("BATMAN"), null);
    }
    
    @Test
    public void palauttaakoOlemattomallaJoukkueellaTyhjanListan() {
        List<Player> mockup = stats.team("mockup");
        assertEquals(0, mockup.size());
    }
    
    @Test
    public void palauttaakoOikeanJoukkueen() {
        List<Player> eddut = stats.team("EDM");
        String eka = new Player("Semenko", "EDM", 4, 12).toString() + new Player("Kurri", "EDM", 37, 53).toString() +
                new Player("Gretzky", "EDM", 35, 89).toString();
        String toka = "";
        for (int i = 0; i < 3; i++) {
            toka += eddut.get(i).toString();
        }
        assertEquals(eka, toka);
    }
    
    @Test
    public void onkoOikeaMaaraMaalimiehiaYhdella() {
        List<Player> maalijabat = stats.topScorers(1);
        assertEquals(1, maalijabat.size());
    }
    
    @Test
    public void onkoOikeaMaaraMaalimiehiaKahdella() {
        List<Player> maalijabat = stats.topScorers(2);
        assertEquals(2, maalijabat.size());
    }
    
    @Test
    public void onkoOikeaMaaraMaalimiehiaNollalla() {
        List<Player> maalijabat = stats.topScorers(0);
        assertEquals(0, maalijabat.size());
    }
    
    

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}