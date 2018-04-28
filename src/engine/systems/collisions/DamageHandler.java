package engine.systems.collisions;

import java.util.Map;

import engine.components.Component;
import engine.components.Damage;
import engine.components.DamageLauncher;
import engine.components.Health;
import engine.setup.EntityManager;
import engine.setup.SystemManager;

public class DamageHandler {
	
	private SystemManager sm;
	
	public DamageHandler(SystemManager sm) {
		this.sm = sm;
	}
	
	public void handle(int playerID, Map<String, Component> player, int colliderID, Map<String, Component> collider) {

		if (player.containsKey(Damage.KEY) && collider.containsKey(Health.KEY)) {
			Damage d = (Damage) player.get(Damage.KEY);
			Damage dLauncher = d.clone();
			sm.addComponent(colliderID, dLauncher);
		}

		if (collider.containsKey(Damage.KEY) && player.containsKey(Health.KEY)) {
			Damage d = (Damage) collider.get(Damage.KEY);
			Damage dLauncher = d.clone();
			sm.addComponent(playerID, dLauncher);
		}
	}
}
