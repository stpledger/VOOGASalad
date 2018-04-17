package engine.systems.collisions;

import java.util.List;
import java.util.Map;

import engine.components.*;
import engine.setup.EntityManager;

public class CollisionHandler {
	private String PLAYER = "player";
	private VelocityHandler velocityHandler;
	private DamageHandler damageHandler;
	private SpriteHandler spriteHandler;
	private LevelStatus levelStatus;
	
	public CollisionHandler(EntityManager em) {
		velocityHandler = new VelocityHandler();
		damageHandler = new DamageHandler(em);
		spriteHandler = new SpriteHandler();
		levelStatus = new LevelStatus();
	}

	public void handle(Map<Integer, Map<String, Component>> handledComponents, int key1, int key2) {
		
		Map<String, Component> components1 = handledComponents.get(key1);
		Map<String, Component> components2 = handledComponents.get(key2);
		//System.out.println(key1+" "+ key2);
		boolean flag1 = components1.containsKey(Player.KEY);
		boolean flag2 = components2.containsKey(Player.KEY);
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
		velocityHandler.handle(playerID, player, colliderID, collider);
		damageHandler.handle(playerID, player, colliderID, collider);
		//spriteHandler.handle(playerID, player, colliderID, collider);
		levelStatus.handle(playerID, player, colliderID, collider);
	}
	
	public LevelStatus getLS() {
		return levelStatus;
	}

}
