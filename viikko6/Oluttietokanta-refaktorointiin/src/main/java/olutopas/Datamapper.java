package olutopas;

import java.util.List;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.model.Rating;
import olutopas.model.User;

public interface Datamapper {

    public Brewery breweryByName(String name);

    public Beer beerByName(String name);

    public User findUserByName(String name);

    public List<Beer> listBeers();

    public List<Brewery> listBreweries();

    public List<User> listUsers();

    public void saveBrewery(Brewery brewery);

    public void registerUser(User user);
    
    public void saveRating(Rating rating);
}
