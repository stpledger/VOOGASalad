package engine.systems.collisions;

import java.util.List;
import java.util.Map;

import engine.components.Component;
import engine.components.EntityType;

public class CollisionHandler {
	public CollisionHandler(Map<Integer, List<Component>> handledComponents) {

	}
	
	public void handle(Map<Integer, List<Component>> handledComponents, int key1, int key2) {
		List<Component> components1 = handledComponents.get(key1);
		List<Component> components2 = handledComponents.get(key2);
		
		boolean flag1 = ((EntityType)(components1.get(Index.TYPE_INDEX))).equals("player");
		boolean flag2 = ((EntityType)(components2.get(Index.TYPE_INDEX))).equals("player");
		if(!flag1 && !flag2) {
			return;
		}
		List<Component> player = flag1 ? components1: components2;
		List<Component> collider = flag1? components2: components1;
		
		handleCollision(player, collider);
		
	}
	
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
	}

}
