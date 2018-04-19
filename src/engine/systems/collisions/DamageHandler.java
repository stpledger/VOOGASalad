package engine.systems.collisions;

import java.util.Map;

import engine.components.Component;
import engine.components.groups.Damage;
import engine.setup.EntityManager;

public class DamageHandler {
	
	private EntityManager em;
	
	public DamageHandler(EntityManager em) {
		this.em = em;
	}
	
	public void handle(int playerID, Map<String, Component> player, int colliderID, Map<String, Component> collider) {
		//System.out.println("in damage handler");
		if(collider.containsKey(Damage.KEY) || player.containsKey(DamageLauncher.KEY)) {
			if(player.containsKey(DamageLauncher.KEY)) {
				DamageLauncher launcher = (DamageLauncher)player.get(DamageLauncher.KEY);
				int newPid = launcher.getParentID();
				double newDamage = launcher.getDamage();
				double newLifetime = launcher.getLifetime();
				if(collider.containsKey(Damage.KEY)) {
					Damage damage = (Damage)collider.get(Damage.KEY);
					damage.setLifetime(damage.getLifetime() + newLifetime);
					damage.setDamage(damage.getDamage() + newDamage);
					//System.out.println(damage.getDamage() + newDamage);
				}
				else {
					Damage damage = new Damage(newPid,newDamage,newLifetime);
					Component damageComponent = (Component)damage;
					em.addComponent(colliderID, Damage.KEY, damageComponent);
				}
			}
		}
		
		else {
			DamageLauncher launcher = (DamageLauncher)collider.get(DamageLauncher.KEY);
			int newPid = launcher.getParentID();
			double newDamage = launcher.getDamage();
			double newLifetime = launcher.getLifetime();
			if(player.containsKey(Damage.KEY)) {
				Damage damage = (Damage)player.get(Damage.KEY);
				damage.setLifetime(damage.getLifetime() + newLifetime);
				damage.setDamage(damage.getDamage() + newDamage);
			}
			else {
				Damage damage = new Damage(newPid,newDamage,newLifetime);
				Component damageComponent = (Component)damage;
				em.addComponent(playerID, Damage.KEY, damageComponent);
			}
		}
	}
}
