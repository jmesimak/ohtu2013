package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class StaticsticsTest {

//    Statistics stats;
//    PlayerReader pr;
    @Before
    public void setUp() {
//        pr = mock(PlayerReader.class);
//        stats = mock(Statistics.class);
    }

    @Test
    public void playerFound() {


        PlayerReader pr = mock(PlayerReader.class);
        Statistics stats = mock(Statistics.class);
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player("Semenko", "EDM", 4, 12));
        players.add(new Player("Lemieux", "PIT", 45, 54));
        players.add(new Player("Kurri", "EDM", 37, 53));
        players.add(new Player("Yzerman", "DET", 42, 56));
        players.add(new Player("Gretzky", "EDM", 35, 89));
        when(stats.search("Lemieux")).thenReturn(new Player("Lemieux", "PIT", 45, 54));
        when(pr.getPlayers()).thenReturn(players);
        Player p = stats.search("Lemieux");

        assertEquals("PIT", p.getTeam());
        assertEquals(45, p.getGoals());
        assertEquals(54, p.getAssists());
        assertEquals(45 + 54, p.getPoints());

    }

    @Test
    public void teamMembersFound() {
        PlayerReader pr = mock(PlayerReader.class);
        Statistics stats = mock(Statistics.class);
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player("Semenko", "EDM", 4, 12));
        players.add(new Player("Kurri", "EDM", 37, 53));
        players.add(new Player("Gretzky", "EDM", 35, 89));
        when(pr.getPlayers()).thenReturn(players);
        when(stats.team("EDM")).thenReturn(players);

        List<Player> playerz = stats.team("EDM");
        assertEquals(3, playerz.size());
        for (Player player : playerz) {
            assertEquals("EDM", player.getTeam());
        }
    }

    @Test
    public void topScorersFound() {
        PlayerReader pr = mock(PlayerReader.class);
        Statistics stats = mock(Statistics.class);
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player("Gretzky", "EDM", 35, 89));
        players.add(new Player("Lemieux", "PIT", 45, 54));
        players.add(new Player("Yzerman", "DET", 42, 56));

        when(pr.getPlayers()).thenReturn(players);
        when(stats.topScorers(2)).thenReturn(players);

        List<Player> playerz = stats.topScorers(2);
        assertEquals(3, playerz.size());
        assertEquals("Gretzky", playerz.get(0).getName());
        assertEquals("Lemieux", playerz.get(1).getName());
    }
}
