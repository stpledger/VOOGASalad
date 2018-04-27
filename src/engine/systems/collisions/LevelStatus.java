package engine.systems.collisions;

import java.util.Map;

import engine.components.Component;
import engine.components.Player;
import engine.components.Win;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class LevelStatus {
	double level;
	private DoubleProperty levelStatus =new SimpleDoubleProperty();
	public LevelStatus() {
		level =1;
		levelStatus.set(level);
	}
	
	
	public void handle(int playerID, Map<String, Component> player, int colliderID, Map<String, Component> collider) {
		if(((Player)player.get(Player.KEY)).getData()<0) {
			level =-1;  //GAME OVER (health system updated lives
			updateStatus(level);
		}
		else if(collider.containsKey(Win.KEY)) {
	    updateStatus(level++);
		}
		
	}
	
	public void updateStatus(double stat) {
		levelStatus.set(stat);
	}
	public DoubleProperty getUpdate() {
		return levelStatus;
	}
}

//code for the player to keep track of the level status, check if this works
//LevelStatus l = new LevelStatus();
//l.getUpdate().addListener((o,ov,nv) -> {
//   some action based on the value of nv like -1 game over, from 1 to 2 change to level to etc. 
//  });