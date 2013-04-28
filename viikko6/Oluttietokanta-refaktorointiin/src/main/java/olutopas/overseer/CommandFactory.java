package olutopas.overseer;

import olutopas.overseer.FindBrewery;
import olutopas.overseer.ListBreweries;
import olutopas.overseer.ListUsers;
import olutopas.overseer.FindBeer;
import olutopas.overseer.ListBeers;
import olutopas.overseer.Login;
import olutopas.overseer.AddBeer;
import olutopas.overseer.AddBrewery;
import java.util.HashMap;
import java.util.Scanner;
import olutopas.Datamapper;

public class CommandFactory {
    
    private HashMap<String, Command> commands;
    private Scanner scanner;
    
    public CommandFactory(Datamapper datamapper, Scanner scanner) {
        this.commands = new HashMap<String, Command>();
        this.scanner = scanner;
        this.commands.put("List Breweries", new ListBreweries(datamapper));
        this.commands.put("List Users", new ListUsers(datamapper));
        this.commands.put("List Beers", new ListBeers(datamapper));
        this.commands.put("Find Brewery", new FindBrewery(datamapper, scanner));
        this.commands.put("Find Beer", new FindBeer(datamapper, scanner));
        this.commands.put("Login", new Login(datamapper, scanner));
        this.commands.put("Add Beer", new AddBeer(datamapper, scanner));
        this.commands.put("Add Brewery", new AddBrewery(datamapper, scanner));
        this.commands.put("My Ratings", new MyRatings(datamapper, scanner));
        this.commands.put("Unknown", new Unknown());
    }
    
    public Command findCommand(String name) {
        Command command = this.commands.get(name);
        if (command == null) {
            command = this.commands.get("Unknown");
        }
        return command;
    }

}
