package olutopas.overseer;

import java.util.Scanner;
import olutopas.Datamapper;
import olutopas.model.Beer;
import olutopas.model.Rating;

public class FindBeer implements Command {

    private Datamapper datamapper;
    private Scanner scanner;

    public FindBeer(Datamapper datamapper, Scanner scanner) {
        this.datamapper = datamapper;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        System.out.print("beer to find: ");
        String n = scanner.nextLine();
        Beer foundBeer = this.datamapper.beerByName(n);

        if (foundBeer == null) {
            System.out.println(n + " not found");
            return;
        }

        System.out.println(foundBeer);

        if (foundBeer.getRatings() != null && foundBeer.getRatings().size() != 0) {
            System.out.println("  number of ratings: " + foundBeer.getRatings().size() + " average " + foundBeer.averageRating());
        } else {
            System.out.println("no ratings");
        }

        System.out.print("give rating (leave emtpy if not): ");
        try {
            int rating = Integer.parseInt(scanner.nextLine());
            addRating(foundBeer, rating);
        } catch (Exception e) {
        }
    }
    
    private void addRating(Beer beer, int val) {
        Rating rating = new Rating(beer, this.datamapper.getCurrentUser(), val);
        this.datamapper.saveRating(rating);
    }
}
