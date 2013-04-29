package statistics.matcher;

import java.util.ArrayList;
import java.util.List;
import statistics.Player;

public class QueryBuilder {
    
    private Matcher m;
    
    public QueryBuilder() {
        this.m = new HasAtLeast(0, "goals");
    }
    
    private QueryBuilder(Matcher matcher) {
        this.m = matcher;
    }

    public QueryBuilder playsIn(String where) {
        return new QueryBuilder(new And(new PlaysIn(where), m));
    }
    
    public QueryBuilder hasAtLeast(int what, String field) {
        return new QueryBuilder(new And(new HasAtLeast(what, field), m));
    }
    
    public QueryBuilder hasFewerThan(int what, String field) {
        return new QueryBuilder(new And(new HasFewerThan(what, field), m));
    }
    
    public Matcher oneOf(Matcher... matchers) {
        return new Or(matchers);
    }
    
    public Matcher notOneOf(Matcher... matchers) {
        return new Not(matchers);
    }
    
    public Matcher build() {
        return this.m;
    }
    
}