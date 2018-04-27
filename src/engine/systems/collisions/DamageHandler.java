package engine.systems.collisions;

import java.util.Map;

import engine.components.Component;
import engine.components.Damage;
import engine.components.DamageLauncher;
import engine.components.Health;
import engine.setup.EntityManager;
import engine.setup.SystemManager;

public class DamageHandler {
	
	private EntityManager em;
	
	public DamageHandler(EntityManager em) {
		this.em = em;
	}
	
	public void handle(int playerID, Map<String, Component> player, int colliderID, Map<String, Component> collider) {

		if (player.containsKey(Damage.KEY) && collider.containsKey(Health.KEY)) {
			Damage d = (Damage) player.get(Damage.KEY);
			Damage dLauncher = d.clone();
			em.addComponent(colliderID, Damage.KEY, dLauncher);
		}

		if (collider.containsKey(Damage.KEY) && player.containsKey(Health.KEY)) {
			Damage d = (Damage) collider.get(Damage.KEY);
			Damage dLauncher = d.clone();
			em.addComponent(playerID, Damage.KEY, dLauncher);
		}
	}
}
