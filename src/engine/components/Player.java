package engine.components;

import java.util.Map;

/**
 * This is the player component that defines which entity on the screen is the 'player', or the person the user
 * is controlling/following around. It can respawn, have a score and lives such that the game can run smoothly
 * by following this component and the entity which contains it
 *
 * @author cndracos
 */
public class Player implements Component {
    public static String KEY = "Player";
    private int pid;
    private int lives;
    private XPosition respawnX;
    private YPosition respawnY;

    public Player(int pid, int lives) {
        this.pid = pid;
        this.lives = lives;

    }

    public XPosition getRespawnX () {
        return respawnX;
    }

    public YPosition getRespawnY () {
        return respawnY;
    }

    public void setRespawnX(XPosition p) {
        respawnX = p;
    }

    public void setRespawnY(YPosition p) {
        respawnY = p;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void respawn (XPosition px, YPosition py, XVelocity vx, YVelocity vy, XAcceleration ax, YAcceleration ay) {
        px.setData(respawnX.getData());
        py.setData(respawnY.getData());
        vx.setData(0);
        vy.setData(0);
        ax.setData(0);
        ay.setData(40);
    }

    public int getPID(){
        return pid;
    }
}
