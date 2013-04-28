package olutopas.overseer;

import java.util.Scanner;
import olutopas.Datamapper;
import olutopas.model.Brewery;

public class AddBrewery implements Command {

    private Datamapper datamapper;
    private Scanner scanner;

    public AddBrewery(Datamapper datamapper, Scanner scanner) {
        this.datamapper = datamapper;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        System.out.print("brewery to add: ");
        String name = scanner.nextLine();
        Brewery brewery = this.datamapper.breweryByName(name);

        if (brewery != null) {
            System.out.println(name + " already exists!");
            return;
        }

        this.datamapper.saveBrewery(new Brewery(name));
    }
}
