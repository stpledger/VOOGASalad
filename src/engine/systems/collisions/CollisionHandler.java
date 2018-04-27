package engine.systems.collisions;

import java.util.List;
import java.util.Map;

import engine.components.*;
import engine.setup.EntityManager;
import engine.setup.SystemManager;

public class CollisionHandler {
	private String PLAYER = "player";
	private DamageHandler damageHandler;
	private LevelStatus levelStatus;
	private ScoreHandler scoreHandler;
	
	public CollisionHandler(SystemManager sm) {
		damageHandler = new DamageHandler(sm);
		levelStatus = new LevelStatus();
		scoreHandler = new ScoreHandler(sm);
	}

	public void handle(Map<Integer, Map<String, Component>> handledComponents, int key1, int key2) {
		
		Map<String, Component> components1 = handledComponents.get(key1);
		Map<String, Component> components2 = handledComponents.get(key2);

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
		damageHandler.handle(playerID, player, colliderID, collider);
		levelStatus.handle(playerID, player, colliderID, collider);
		scoreHandler.handle(playerID, player, colliderID, collider);
		
	}
	
	public LevelStatus getLS() {
		return levelStatus;
	}

}
