package engine.systems.collisions;

import java.util.Map;

import engine.components.Component;
import engine.components.EntityType;

public class SpriteHandler {
	private String playerImage;
	public SpriteHandler() {
		
	}
	
	public void handle(int playerID, Map<String, Component> player, int colliderID, Map<String, Component> collider) {
		if(!((EntityType)collider.get(EntityType.getKey())).getType().equals("Power-up")) {
			return;
		}
		
		//
	}
}
