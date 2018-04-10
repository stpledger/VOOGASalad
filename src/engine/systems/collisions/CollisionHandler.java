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
	
	private void handleCollision(int playerID, Map<String, Component> player, int colliderID, Map<String, Component> collider) {
		damageHandler.handle(playerID, player, colliderID, collider);
	}

}
