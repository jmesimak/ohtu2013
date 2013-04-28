package olutopas.overseer;

import java.util.Scanner;
import olutopas.Datamapper;
import olutopas.model.Beer;
import olutopas.model.Brewery;

public class AddBeer implements Command {
    
    private Scanner scanner;
    private Datamapper datamapper;

    public AddBeer(Datamapper datamapper, Scanner scanner) {
        this.datamapper = datamapper;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        System.out.print("to which brewery: ");
        String name = scanner.nextLine();
        Brewery brewery = this.datamapper.breweryByName(name);

        if (brewery == null) {
            System.out.println(name + " does not exist");
            return;
        }

        System.out.print("beer to add: ");

        name = scanner.nextLine();

        Beer exists = this.datamapper.beerByName(name);
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }

        brewery.addBeer(new Beer(name));
        this.datamapper.saveBrewery(brewery);
        System.out.println(name + " added to " + brewery.getName());
    }
}
