package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstatistics.herokuapp.com/players.txt"));
          
        QueryBuilder qb = new QueryBuilder();
        
        Matcher m = qb.playsIn("ANA").hasAtLeast(10, "goals").hasAtLeast(20, "assists").build();
        Matcher m2 = qb.playsIn("PHI").hasAtLeast(15, "goals").build();
        Matcher m5 = qb.hasAtLeast(10, "goals").build();
        
        Matcher m3 = qb.notOneOf(m, m2, m5);
        
//        Matcher m = new Not( new HasAtLeast(25, "goals"),
//                             new PlaysIn("PHI")
//        );
        
        for (Player player : stats.matches(m3)) {
            System.out.println( player );
        }
    }
}
