package olutopas.overseer;

import java.util.Scanner;
import olutopas.Datamapper;
import olutopas.model.Beer;
import olutopas.model.Brewery;

public class FindBrewery implements Command {

    private Datamapper datamapper;
    private Scanner scanner;

    public FindBrewery(Datamapper datamappper, Scanner scanner) {
        this.datamapper = datamappper;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        System.out.print("brewery to find: ");
        String n = scanner.nextLine();
        Brewery foundBrewery = datamapper.breweryByName(n);

        if (foundBrewery == null) {
            System.out.println(n + " not found");
            return;
        }

        System.out.println(foundBrewery);
        for (Beer bier : foundBrewery.getBeers()) {
            System.out.println("   " + bier.getName());
        }
    }
}
