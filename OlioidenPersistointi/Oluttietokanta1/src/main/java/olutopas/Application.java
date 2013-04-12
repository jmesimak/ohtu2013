package olutopas;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import java.util.Scanner;
import javax.persistence.OptimisticLockException;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.model.Pub;
import olutopas.model.Rating;
import olutopas.model.User;

public class Application {

    private EbeanServer server;
    private Scanner scanner = new Scanner(System.in);
    private User sessionUser;

    public Application(EbeanServer server) {
        this.server = server;
    }

    public void run(boolean newDatabase) {
        if (newDatabase) {
            seedDatabase();
        }
        System.out.println("LOGIN ROUTINE INITIATED (GIVE ? TO REGISTER)");
        while (true) {
            System.out.print("USERNAME: ");
            String komento = scanner.nextLine();
            if (komento.equals("?")) {
                registerUser();
            } else {
                if (login(komento)) {
                    break;
                }
            }
        }



        while (true) {
            menu();
            System.out.print("> ");
            String command = scanner.nextLine();

            if (command.equals("0")) {
                break;
            } else if (command.equals("1")) {
                findBrewery();
            } else if (command.equals("2")) {
                findBeer();
            } else if (command.equals("3")) {
                addBeer();
            } else if (command.equals("4")) {
                listBreweries();
            } else if (command.equals("5")) {
                deleteBeer();
            } else if (command.equals("6")) {
                listBeers();
            } else if (command.equals("7")) {
                addBrewery();
            } else if (command.equals("8")) {
                deleteBrewery();
            } else if (command.equals("all")) {
                listUsers();
            } else if (command.equals("t")) {
                addRating();
            } else if (command.equals("c")) {
                listRatingsByUser();
            } else if (command.equals("k")) {
                getAverageRatingForBeer();
            } else if (command.equals("q")) {
                addPub();
            } else if (command.equals("w")) {
                addBeerToPub();
            } else if (command.equals("e")) {
                showBeersInPub();
            } else if (command.equals("r")) {
                listPubs();
            } else if (command.equals("y")) {
                removeBeerFromPub();
            } else {
                System.out.println("unknown command");
            }

            System.out.print("\npress enter to continue");
            scanner.nextLine();
        }

        System.out.println("bye");
    }

    private void menu() {
        System.out.println("");
        System.out.println("1   find brewery");
        System.out.println("2   find beer");
        System.out.println("3   add beer");
        System.out.println("4   list breweries");
        System.out.println("5   delete beer");
        System.out.println("6   list beers");
        System.out.println("7   add brewery");
        System.out.println("8   delete brewery");
        System.out.println("?   register user");
        System.out.println("all list users");
        System.out.println("t   add rating");
        System.out.println("c   list user ratings");
        System.out.println("k   get beer average rating");
        System.out.println("q   add pub");
        System.out.println("w   add beer to pub");
        System.out.println("e   show beers in pub");
        System.out.println("r   list pubs");
        System.out.println("y   remove beer from pub");
        System.out.println("0   quit");
        System.out.println("");
    }

    // jos kanta on luotu uudelleen, suoritetaan tämä ja laitetaan kantaan hiukan dataa
    private void seedDatabase() throws OptimisticLockException {
        Brewery brewery = new Brewery("Schlenkerla");
        brewery.addBeer(new Beer("Urbock"));
        brewery.addBeer(new Beer("Lager"));
        // tallettaa myös luodut oluet, sillä Brewery:n OneToMany-mappingiin on määritelty
        // CascadeType.all
        server.save(brewery);

        // luodaan olut ilman panimon asettamista
        Beer b = new Beer("Märzen");
        server.save(b);

        // jotta saamme panimon asetettua, tulee olot lukea uudelleen kannasta
        b = server.find(Beer.class, b.getId());
        brewery = server.find(Brewery.class, brewery.getId());
        brewery.addBeer(b);
        server.save(brewery);

        server.save(new Brewery("Paulaner"));
    }

    private void findBeer() {
        System.out.print("beer to find: ");
        String n = scanner.nextLine();
        Beer foundBeer = server.find(Beer.class).where().like("name", n).findUnique();

        if (foundBeer == null) {
            System.out.println(n + " not found");
            return;
        }

        System.out.println("found: " + foundBeer);
    }

    private void findBrewery() {
        System.out.print("brewery to find: ");
        String n = scanner.nextLine();
        Brewery foundBrewery = server.find(Brewery.class).where().like("name", n).findUnique();

        if (foundBrewery == null) {
            System.out.println(n + " not found");
            return;
        }

        System.out.println(foundBrewery);
        for (Beer bier : foundBrewery.getBeers()) {
            System.out.println("   " + bier.getName());
        }
    }

    private void listBreweries() {
        List<Brewery> breweries = server.find(Brewery.class).findList();
        for (Brewery brewery : breweries) {
            System.out.println(brewery);
        }
    }

    private void listBeers() {
        List<Beer> beers = server.find(Beer.class).findList();
        for (Beer beer : beers) {
            System.out.println(beer);
        }
    }

    private void registerUser() {
        System.out.print("username pls: ");
        String name = scanner.nextLine();
        User newUser = new User(name);
        server.save(newUser);
        System.out.println("user created!");
    }

    private boolean login(String name) {
        List<User> users = server.find(User.class).findList();
        for (User user : users) {
            if (user.getName().equals(name)) {
                System.out.println("Welcome to Ratebeer " + name);
                this.sessionUser = user;
                return true;
            }
        }
        System.out.println("Username " + name + " not found. Please register to use Ratebeer");
        return false;
    }

    private List<User> listUsers() {
        List<User> users = server.find(User.class).findList();
        System.out.println("Found " + users.size() + " users");
        return users;
    }

    private void addBrewery() {
        System.out.print("give the name: ");
        String name = scanner.nextLine();
        Brewery brewery = new Brewery(name);
        server.save(brewery);
    }

    private void addBeer() {
        System.out.print("to which brewery: ");
        String name = scanner.nextLine();
        Brewery brewery = server.find(Brewery.class).where().like("name", name).findUnique();

        if (brewery == null) {
            System.out.println(name + " does not exist");
            return;
        }

        System.out.print("beer to add: ");

        name = scanner.nextLine();

        Beer exists = server.find(Beer.class).where().like("name", name).findUnique();
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }

        brewery.addBeer(new Beer(name));
        server.save(brewery);
        System.out.println(name + " added to " + brewery.getName());
    }

    private void deleteBeer() {
        System.out.print("beer to delete: ");
        String n = scanner.nextLine();
        Beer beerToDelete = server.find(Beer.class).where().like("name", n).findUnique();

        if (beerToDelete == null) {
            System.out.println(n + " not found");
            return;
        }

        server.delete(beerToDelete);
        System.out.println("deleted: " + beerToDelete);

    }

    private void deleteBrewery() {
        System.out.print("brewery to delete: ");
        String deletable = scanner.nextLine();
        Brewery toDelete = server.find(Brewery.class).where().like("name", deletable).findUnique();

        if (toDelete == null) {
            System.out.println(deletable + " not found");
            return;
        }

        server.delete(toDelete);
        System.out.println("deleted: " + deletable);
    }

    private void addRating() {
        System.out.print("give the name of the beer: ");
        String beerName = scanner.nextLine();
        List<Beer> beers = server.find(Beer.class).findList();
        for (Beer beer : beers) {
            if (beer.getName().equals(beerName)) {
                System.out.print("give rating: ");
                int value = Integer.parseInt(scanner.nextLine());
                Rating r = new Rating(beer, this.sessionUser, value);
                this.sessionUser.addRating(r);
                server.save(r);
                return;
            }
        }
        System.out.println("beer was not found from the database, try adding it first");
    }

    private void listRatingsByUser() {
        System.out.print("give the name of the user: ");
        String name = scanner.nextLine();
        for (User u : listUsers()) {
            if (u.getName().equals(name)) {
                List<Rating> ratings = u.getRatings();
                for (Rating rating : ratings) {
                    System.out.println(rating);
                }
                return;
            }
        }

    }

    private void getAverageRatingForBeer() {
        System.out.print("give the name of the beer: ");
        String name = scanner.nextLine();
        Beer beerToFind = server.find(Beer.class).where().like("name", name).findUnique();
        if (beerToFind == null) {
            System.out.println("Beer not found.");
            return;
        }
        List<Rating> ratings = beerToFind.getRatings();
        int sum = 0;
        for (Rating rating : ratings) {
            sum += rating.getValue();
        }
        System.out.println("Average rating for " + beerToFind.getName() + ": " + sum / ratings.size());
    }

    private void showBeersInPub() {
        System.out.print("Please give the name of the pub: ");
        String pubName = scanner.nextLine();

        Pub pub = server.find(Pub.class).where().like("name", pubName).findUnique();

        if (pub == null) {
            System.out.println("Pub was not found. Has it been added yet?");
            return;
        }

        List<Beer> beers = pub.getBeers();

        System.out.println(pub);
        System.out.println("Beer list");
        for (Beer beer : beers) {
            System.out.println(beer);
        }
    }

    private void showBeersInPubByPub(String pubName) {

        Pub pub = server.find(Pub.class).where().like("name", pubName).findUnique();

        if (pub == null) {
            System.out.println("Pub was not found. Has it been added yet?");
            return;
        }

        List<Beer> beers = pub.getBeers();

        System.out.println(pub);
        System.out.println("Beer list");
        for (Beer beer : beers) {
            System.out.println(beer);
        }
    }

    private void listPubs() {
        List<Pub> pubs = server.find(Pub.class).findList();
        for (Pub pub : pubs) {
            showBeersInPubByPub(pub.getName());
            System.out.println("--------------------------------------");
        }
    }

    private void removeBeerFromPub() {
        System.out.print("Give the pub name: ");
        String pubName = scanner.nextLine();
        System.out.print("Give the beer name: ");
        String beerName = scanner.nextLine();

        Pub pub = server.find(Pub.class).where().like("name", pubName).findUnique();

        if (pub == null) {
            System.out.println("Pub was not found. Has it been added yet?");
            return;
        }

        Beer beer = server.find(Beer.class).where().like("name", beerName).findUnique();

        if (beer == null) {
            System.out.println("Beer was not found. Has it been added yet?");
            return;
        }

        pub.removeBeer(beer);
        System.out.println(beer.getName() + " removed from " + pub.getName());
    }

    private void addPub() {
        System.out.print("pub to add: ");

        String name = scanner.nextLine();

        Pub exists = server.find(Pub.class).where().like("name", name).findUnique();
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }

        server.save(new Pub(name));
    }

    private void addBeerToPub() {
        System.out.print("beer: ");
        String name = scanner.nextLine();
        Beer beer = server.find(Beer.class).where().like("name", name).findUnique();

        if (beer == null) {
            System.out.println("does not exist");
            return;
        }

        System.out.print("pub: ");
        name = scanner.nextLine();
        Pub pub = server.find(Pub.class).where().like("name", name).findUnique();

        if (pub == null) {
            System.out.println("does not exist");
            return;
        }

        pub.addBeer(beer);
        server.save(pub);
    }
}
