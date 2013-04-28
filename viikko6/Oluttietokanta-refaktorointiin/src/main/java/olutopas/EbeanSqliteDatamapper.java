package olutopas;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.model.Rating;
import olutopas.model.User;

public class EbeanSqliteDatamapper implements Datamapper {

    private EbeanServer server;
    private User user;

    public EbeanSqliteDatamapper(EbeanServer server) {
        this.server = server;
    }

    @Override
    public Brewery breweryByName(String name) {
        return server.find(Brewery.class).where().like("name", name).findUnique();
    }

    @Override
    public Beer beerByName(String name) {
        return server.find(Beer.class).where().like("name", name).findUnique();
    }

    @Override
    public User findUserByName(String name) {
        return server.find(User.class).where().like("name", name).findUnique();
    }

    @Override
    public List<Beer> listBeers() {
        return server.find(Beer.class).findList();
    }

    @Override
    public List<Brewery> listBreweries() {
        return server.find(Brewery.class).findList();
    }

    @Override
    public List<User> listUsers() {
        return server.find(User.class).findList();
    }

    @Override
    public void saveBrewery(Brewery brewery) {
        server.save(brewery);
    }

    @Override
    public void registerUser(User user) {
        server.save(user);
    }
    
    @Override
    public void saveRating(Rating rating) {
        server.save(rating);
    }

    @Override
    public User getCurrentUser() {
        return this.user;
    }

    @Override
    public void setCurrentUser(User user) {
        this.user = user;
    }
}
