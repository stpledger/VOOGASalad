package engine.components;

import java.util.Map;

public class Player extends Component {
    public static String KEY = "Player";

    private int lives;
    private Position respawn;

    public Player(int pid, int lives) {
        super(pid);
        this.lives = lives;
        
    }

    public String getKey() { return KEY; }


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
   
     public void respawn (Position p, Velocity v, Acceleration a) {
       p.setXPos(respawn.getXPos());
       p.setYPos(respawn.getYPos());
       v.setXVel(0); v.setYVel(0);
       a.setxAcc(0); a.setyAcc(40);
    }

	@Override
	public Map<String, String> getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

}
