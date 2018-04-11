package engine.systems.collisions;

import java.util.Map;

import engine.components.Component;
import engine.components.Damage;
import engine.components.DamageLauncher;
import engine.setup.EntityManager;

public class DamageHandler {
	public DamageHandler() {
		
	}
	
	public void handle(int playerID, Map<String, Component> player, int colliderID, Map<String, Component> collider) {
		if(collider.containsKey(DamageLauncher.getKey()) || player.containsKey(DamageLauncher.getKey())) {
			if(player.containsKey(DamageLauncher.getKey())) {
				DamageLauncher launcher = (DamageLauncher)player.get(DamageLauncher.getKey());
				int newPid = launcher.getParentID();
				double newDamage = launcher.getDamage();
				double newLifetime = launcher.getLifetime();
				if(collider.containsKey(Damage.getKey())) {
					Damage damage = (Damage)collider.get(Damage.getKey());
					damage.setLifetime(damage.getLifetime() + newLifetime);
					damage.setDamage(damage.getDamage() + newDamage);
				}
				else {
					Damage damage = new Damage(newPid,newDamage,newLifetime);
					Component damageComponent = (Component)damage;
					EntityManager.addComponent(colliderID, Damage.getKey(), damageComponent);
				}
			}
		}
		
		else {
			DamageLauncher launcher = (DamageLauncher)collider.get(DamageLauncher.getKey());
			int newPid = launcher.getParentID();
			double newDamage = launcher.getDamage();
			double newLifetime = launcher.getLifetime();
			if(player.containsKey(Damage.getKey())) {
				Damage damage = (Damage)player.get(Damage.getKey());
				damage.setLifetime(damage.getLifetime() + newLifetime);
				damage.setDamage(damage.getDamage() + newDamage);
			}
			else {
				Damage damage = new Damage(newPid,newDamage,newLifetime);
				Component damageComponent = (Component)damage;
				EntityManager.addComponent(playerID, Damage.getKey(), damageComponent);
			}
		}
	}
}
