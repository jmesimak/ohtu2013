package olutopas.overseer;

import java.util.List;
import olutopas.Datamapper;
import olutopas.model.Brewery;

public class ListBreweries implements Command {
    
    private Datamapper datamapper;
    
    public ListBreweries(Datamapper datamapper) {
        this.datamapper = datamapper;
    }
    
    @Override
    public void run() {
        List<Brewery> breweries = this.datamapper.listBreweries();
        for (Brewery brewery : breweries) {
            System.out.println(brewery);
        }
    }
}
