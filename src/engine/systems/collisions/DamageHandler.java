package engine.systems.collisions;

import java.util.Map;

import engine.components.Component;
import engine.components.DamageLauncherLifetime;
import engine.components.DamageLauncherValue;
import engine.components.DamageLifetime;
import engine.components.DamageValue;
import engine.components.Health;

import engine.setup.EntityManager;

public class DamageHandler {
	
	private EntityManager em;
	
	public DamageHandler(EntityManager em) {
		this.em = em;
	}
	
	public void handle(int playerID, Map<String, Component> player, int colliderID, Map<String, Component> collider) {


		if (player.containsKey(DamageLauncherValue.KEY) && 
				player.containsKey(DamageLauncherLifetime.KEY) && 
				collider.containsKey(Health.KEY)) {
			
			DamageLauncherValue dlv = (DamageLauncherValue) player.get(DamageLauncherValue.KEY);
			DamageLauncherLifetime dll = (DamageLauncherLifetime) player.get(DamageLauncherLifetime.KEY);
						
			em.addComponent(colliderID, new DamageValue(colliderID, dlv.getData()));
			em.addComponent(colliderID, new DamageLifetime(colliderID, dll.getData()));
		}

		if (collider.containsKey(DamageLauncherValue.KEY) && 
				collider.containsKey(DamageLauncherLifetime.KEY) && 
				player.containsKey(Health.KEY)) {
			
			DamageLauncherValue dlv = (DamageLauncherValue) collider.get(DamageLauncherValue.KEY);
			DamageLauncherLifetime dll = (DamageLauncherLifetime) collider.get(DamageLauncherLifetime.KEY);
						
			em.addComponent(playerID, new DamageValue(playerID, dlv.getData()));
			em.addComponent(playerID, new DamageLifetime(playerID, dll.getData()));
		}
	}
}
