package olutopas;

import olutopas.overseer.CommandFactory;
import com.avaje.ebean.EbeanServer;
import java.util.List;
import java.util.Scanner;
import javax.persistence.OptimisticLockException;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.model.Rating;
import olutopas.model.User;

public class Application {

    private EbeanServer server;
    private Scanner scanner;
    private User user;
    private EbeanSqliteDatamapper datamapper;
    private CommandFactory commandFactory;

    public Application(EbeanServer server) {
        this.server = server;
        this.datamapper = new EbeanSqliteDatamapper(server);
        this.scanner = new Scanner(System.in);
        this.commandFactory = new CommandFactory(datamapper, scanner);
    }

    public void run(boolean newDatabase) {
        if (newDatabase) {
            seedDatabase();
        }

        this.commandFactory.findCommand("Login").run();
        this.user = this.datamapper.getCurrentUser();

        System.out.println("\nWelcome to Ratebeer " + user.getName());

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine();

            if (command.equals("Quit")) {
                break;
            }

            this.commandFactory.findCommand(command).run();

            System.out.print("\npress enter to continue");
            scanner.nextLine();
        }

        System.out.println("bye");
    }

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

        server.save(new User("mluukkai"));
    }
}
