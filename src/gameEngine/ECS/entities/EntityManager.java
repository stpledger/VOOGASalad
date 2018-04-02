package gameEngine.ECS.entities;

import java.util.List;

public class EntityManager {
	private static List<Entity> activeEntities;
	public EntityManager() {};
	
	public static List<Entity> getActiveEntities() {
		return activeEntities;
	}
}
