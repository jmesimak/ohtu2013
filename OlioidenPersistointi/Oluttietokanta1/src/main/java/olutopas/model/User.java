package olutopas.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

    private String name;
    @Id
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Rating> ratings;

    public User() {
        this.ratings = new ArrayList<Rating>();
    }

    public User(String name) {
        this.name = name;
        this.ratings = new ArrayList<Rating>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings.addAll(ratings);
    }

    public void addRating(Rating r) {
        this.ratings.add(r);
    }

    @Override
    public String toString() {
        return "* Name: " + this.name;
    }
}
