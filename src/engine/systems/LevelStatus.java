package engine.systems;

import java.util.Map;

import engine.components.Component;
import engine.components.Player;
import engine.components.Win;
import javafx.beans.property.DoubleProperty;
//need to check if player collided with a win component or if lives is less than zero

public class LevelStatus {
	double level;
	public LevelStatus() {
		level =1;
	}
	
	private DoubleProperty levelStatus;
	
	public void handle(int playerID, Map<String, Component> player, int colliderID, Map<String, Component> collider) {
		if(collider.containsKey(Win.KEY)) {
		player.get(Player.KEY).get	
		}
	}
	
	public void updateStatus(double stat) {
		levelStatus.set(stat);
	}
	public DoubleProperty getUpdate() {
		return levelStatus;
	}
}

//LevelStatus l = new LevelStatus();
//l.getUpdate().addListener((o,ov,nv) -> {
//   some action based on the value of nv like -1 game over, from 1 to 2 change to level to etc. 
//  });