package engine.systems.collisions;

import java.util.Map;

import engine.components.Component;
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


		if (player.containsKey(DamageValue.KEY) && 
				player.containsKey(DamageLifetime.KEY) && 
				collider.containsKey(Health.KEY)) {
			
			DamageValue dlv = (DamageValue) player.get(DamageValue.KEY);
			DamageLifetime dll = (DamageLifetime) player.get(DamageLifetime.KEY);
									
			em.addComponent(colliderID, new DamageValue(playerID, dlv.getData()));
			em.addComponent(colliderID, new DamageLifetime(playerID, dll.getData()));
				
			
		}

		if (collider.containsKey(DamageValue.KEY) && 
				collider.containsKey(DamageLifetime.KEY) && 
				player.containsKey(Health.KEY)) {
			
			DamageValue dlv = (DamageValue) collider.get(DamageValue.KEY);
			DamageLifetime dll = (DamageLifetime) collider.get(DamageLifetime.KEY);
						
			em.addComponent(playerID, new DamageValue(colliderID, dlv.getData()));
			em.addComponent(playerID, new DamageLifetime(colliderID, dll.getData()));
		}
	}
}
