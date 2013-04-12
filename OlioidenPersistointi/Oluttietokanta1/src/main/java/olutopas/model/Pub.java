package olutopas.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Pub {

    @Id
    private Integer id;
    
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Beer> beers;
    
    public Pub() {
        
    }
}
