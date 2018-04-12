package engine.systems.collisions;

import java.util.List;
import java.util.Map;

import engine.components.Component;
import engine.components.Damage;
import engine.components.DamageLauncher;
import engine.components.EntityType;
import engine.components.Player;

public class CollisionHandler {
	private String PLAYER = "player";
	private DamageHandler damageHandler;
	private SpriteHandler spriteHandler;
	
	public CollisionHandler() {
		damageHandler = new DamageHandler();
		spriteHandler = new SpriteHandler();
	}

	public void handle(Map<Integer, Map<String, Component>> handledComponents, int key1, int key2) {
		
		Map<String, Component> components1 = handledComponents.get(key1);
		Map<String, Component> components2 = handledComponents.get(key2);
		//System.out.println(key1+" "+ key2);
		boolean flag1 = components1.containsKey(Player.KEY);
		boolean flag2 = components2.containsKey(Player.KEY);
		System.out.println(flag1+" "+ flag2);
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
		System.out.println("In Collision handler");
		damageHandler.handle(playerID, player, colliderID, collider);
		spriteHandler.handle(playerID, player, colliderID, collider);
	}

}
