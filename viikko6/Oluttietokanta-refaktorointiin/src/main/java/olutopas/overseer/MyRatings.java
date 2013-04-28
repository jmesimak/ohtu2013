package olutopas.overseer;

import olutopas.overseer.Command;
import java.util.Scanner;
import olutopas.Datamapper;
import olutopas.model.Rating;

public class MyRatings implements Command {

    private Datamapper datamapper;
    private Scanner scanner;

    public MyRatings(Datamapper datamapper, Scanner scanner) {
        this.datamapper = datamapper;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        System.out.println("Ratings by " + this.datamapper.getCurrentUser().getName());
        for (Rating rating : this.datamapper.getCurrentUser().getRatings()) {
            System.out.println(rating);
        }
    }
}
