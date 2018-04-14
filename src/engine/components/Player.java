package engine.components;


import javafx.geometry.Pos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

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

    public Position getRespawn () {
        return respawn;
    }

    public void setRespawn(Position p) {
        respawn = p;
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

    public void respawn (Position p, Velocity v, Acceleration a) {
       p.setXPos(respawn.getXPos());
       p.setYPos(respawn.getYPos());
       v.setXVel(0); v.setYVel(0);
       a.setxAcc(0); a.setyAcc(40);
    }

}
