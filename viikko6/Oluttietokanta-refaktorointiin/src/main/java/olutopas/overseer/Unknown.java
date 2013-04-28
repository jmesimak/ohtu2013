package olutopas.overseer;

public class Unknown implements Command{

    public Unknown() {
        
    }
    
    @Override
    public void run() {
        System.out.println("Unknown command.\nAvailable commands: Add Brewery, Add Beer, Find Brewery, Find Beer, My Ratings\nList Beers, List Ratings, Quit");
    }
    
}
