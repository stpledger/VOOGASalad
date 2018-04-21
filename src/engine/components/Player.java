package engine.components;


import javafx.geometry.Pos;

import java.util.HashMap;
import java.util.Map;

public class Player extends Component {
    public static String KEY = "Player";

    private int lives;
    private int score;

    private Position respawn;

    public Player(int pid, int lives, int score) {
        super(pid, KEY);
        this.lives = lives;
        this.score = score;
    }

    public static String getKey() { return KEY; }

    @Override
    public Map<String, String> getParameters(){
        return null;
    }

    public void setRespawn(Position p) {
        respawn = p;
    }

    public Position getRespawn() {
        return respawn;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
