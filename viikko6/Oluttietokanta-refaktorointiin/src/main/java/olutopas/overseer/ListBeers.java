package olutopas.overseer;

import java.util.List;
import olutopas.Datamapper;
import olutopas.model.Beer;

class ListBeers implements Command {

    private Datamapper datamapper;

    public ListBeers(Datamapper datamapper) {
        this.datamapper = datamapper;
    }

    @Override
    public void run() {
        List<Beer> beers = this.datamapper.listBeers();
        for (Beer beer : beers) {
            System.out.println(beer);
            if (beer.getRatings() != null && beer.getRatings().size() != 0) {
                System.out.println("  ratings given " + beer.getRatings().size() + " average " + beer.averageRating());
            } else {
                System.out.println("  no ratings");
            }
        }
    }
}
