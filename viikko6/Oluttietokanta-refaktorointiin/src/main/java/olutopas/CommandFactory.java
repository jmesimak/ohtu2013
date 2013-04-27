package olutopas;

import java.util.HashMap;

public class CommandFactory {
    
    private HashMap<String, Command> commands;
    
    public CommandFactory(Datamapper datamapper) {
        this.commands = new HashMap<String, Command>();
        this.commands.put("List Breweries", new ListBreweries(datamapper));
        
    }
    
    public Command findCommand(String name) {
        return commands.get(name);
    }

}
