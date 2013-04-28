package olutopas.overseer;

import java.util.List;
import olutopas.Datamapper;
import olutopas.model.User;

public class ListUsers implements Command {

    private Datamapper datamapper;

    public ListUsers(Datamapper datamapper) {
        this.datamapper = datamapper;
    }

    @Override
    public void run() {
        List<User> users = this.datamapper.listUsers();
        for (User user : users) {
            System.out.println(user.getName() + " " + user.getRatings().size() + " ratings");
        }
    }
}
