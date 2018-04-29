package data;

import gameplayer.Person;

import java.util.List;
import java.util.Map;

public class HighScore {
    private Map<String, List<Person>> hs;

    public HighScore(Map<String, List<Person>> hs ){
        this.hs = hs;
    }
    public Map<String, List<Person>> getHighscores(){
        return hs;
    }
}
