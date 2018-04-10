package engine.systems.collisions;

import java.util.List;
import java.util.Map;

import engine.components.Component;
import engine.components.Damage;
import engine.components.DamageLauncher;
import engine.components.EntityType;
import engine.components.Player;

public class CollisionHandler {
	private DamageHandler damageHandler;
	
	public CollisionHandler() {
		damageHandler = new DamageHandler();
	}

	public void handle(Map<Integer, Map<String, Component>> handledComponents, int key1, int key2) {
		Map<String, Component> components1 = handledComponents.get(key1);
		Map<String, Component> components2 = handledComponents.get(key2);
		
		boolean flag1 = components1.containsKey(Player.getKey());
		boolean flag2 = components2.containsKey(Player.getKey());
		if(!flag1 && !flag2) {
			return;
		}
		
		Map<String, Component> player = flag1 ? components1: components2;
		int playerID = flag1 ? key1 : key2;
		Map<String, Component> collider = flag1? components2: components1;
		int colliderID = flag1 ? key2 : key1;
		
		handleCollision(playerID, player, colliderID, collider);
		
	}
	
<<<<<<< HEAD
	private void handleCollision(int playerID, Map<String, Component> player, int colliderID, Map<String, Component> collider) {
		damageHandler.handle(playerID, player, colliderID, collider);
=======
	private void handleCollision(List<Component> player, List<Component> collider) {

		String colliderType = ((EntityType)(collider.get(Index.TYPE_INDEX))).toString();
		switch(colliderType){
			case "simple enemy":
				break;
			case "flying fire-shooting enemy":
				break;
			case "simple block":
				break;
			case "fire block":
				break;
			case "super power block":
				break;
			case "power-up":
				break;
			case "fire ball":
				break;
		}
>>>>>>> 9d2c61e58bf633d7fda5043bafe04e74d489d2b8
	}

}
