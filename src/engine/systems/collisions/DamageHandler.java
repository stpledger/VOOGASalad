package engine.systems.collisions;

import java.util.Map;
import engine.components.Component;
import engine.components.DamageLifetime;
import engine.components.DamageValue;
import engine.components.Health;

import engine.setup.SystemManager;

public class DamageHandler {
	
	private SystemManager sm;
	
	public DamageHandler(SystemManager sm) {
		this.sm = sm;
	}
	
	public void handle(int playerID, Map<String, Component> player, int colliderID, Map<String, Component> collider) {
		giveDamage(playerID, player, colliderID, collider);
		giveDamage(colliderID, collider, playerID, player);
	}

	private void giveDamage(int playerID, Map<String, Component> player, int colliderID, Map<String, Component> collider) {
		if (player.containsKey(DamageValue.KEY) &&
				player.containsKey(DamageLifetime.KEY) &&
				collider.containsKey(Health.KEY)) {

			DamageValue dlv = (DamageValue) player.get(DamageValue.KEY);
			DamageLifetime dll = (DamageLifetime) player.get(DamageLifetime.KEY);

			sm.addComponent(colliderID, new DamageValue(playerID, dlv.getData()));
			sm.addComponent(colliderID, new DamageLifetime(playerID, dll.getData()));
		}
	}
}
