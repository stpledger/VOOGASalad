package engine.systems;
/**
 * A system that handles what happens when two entities collide, one having health component and the other damage
 * @author sv116
 */
import java.util.HashMap;
import java.util.Map;

import java.util.*;

import engine.components.Component;
import engine.components.Damage;
import engine.components.DamageLauncher;
import engine.components.Health;
import engine.components.Player;
import engine.setup.EntityManager;
import engine.setup.SystemManager;

public class HealthDamage implements ISystem {
	private Map<Integer, Map<String, Component>> handledComponents;
	private Set<Integer> activeComponents;
	private EntityManager em;
	private SystemManager sm;
	
	public HealthDamage(EntityManager em, SystemManager sm) {
		handledComponents = new HashMap<>();
		this.em = em;
		this.sm = sm;
	}

	public void addComponent(int pid, Map<String, Component> components) {
		if (components.containsKey(Health.KEY) && components.containsKey(DamageLauncher.KEY)) {
			Map<String, Component> newComponents = new HashMap<>();
			newComponents.put(Health.KEY,components.get(Health.KEY));
			newComponents.put(DamageLauncher.KEY,components.get(DamageLauncher.KEY));

			handledComponents.put(pid, newComponents);
		}
		
	}
	
    public void removeComponent(int pid) {
		if(handledComponents.containsKey(pid)) {
    		handledComponents.remove(pid);
    	}  
	}

    public void addComponent(int pid, String componentName) {
		if(!componentName.equals(Health.KEY) && !componentName.equals(DamageLauncher.KEY)) {
			return;
		}
		
		if(handledComponents.containsKey(pid)) {
			System.out.println("HealthDamage System tries adding duplicate " + componentName + " component for entity " + pid + " !");
		}
		

		Map<String, Component> map = new HashMap<>();
		map.put(componentName,em.getComponent(pid, componentName));
		if(componentName.equals(Health.KEY)) {
			Component component = em.getComponent(pid,Damage.KEY);
			if(component == null) {
				System.out.println("Entity " + pid + " has " + componentName + " component but has no " + Damage.KEY + " component!");
			     return;
			}
			map.put(Damage.KEY, component);
		}
		else {
			Component component = em.getComponent(pid,Health.KEY);
			if(component == null) {
				System.out.println("Entity " + pid + " has " + componentName + " component but has no " + Health.KEY + " component!");
				return;
			}
			map.put(Health.KEY, component);
		}
		handledComponents.put(pid,map);
    }

	public void removeComponent(int pid, String componentName) {
		if(!componentName.equals(Health.KEY) && !componentName.equals(Damage.KEY)) {
			return;
		}
		
		if(!handledComponents.containsKey(pid)) {
			System.out.println("HealthDamage System tries remove " + componentName + " from non-existing entity " + pid + " !");
		}
		
	
		handledComponents.remove(pid);
	}

	public void setActives(Set<Integer> actives) {
		Set<Integer> myActives = new HashSet<>(actives);
		myActives.retainAll(handledComponents.keySet());
		activeComponents = myActives;
	}

	public void execute(double time) {
		activeComponents.forEach((key) -> {
			Map<String, Component> map = handledComponents.get(key);
			Health h = (Health) map.get(Health.KEY);
			if(map.containsKey(Damage.KEY)) {
				Damage d = (Damage) map.get(Damage.KEY);

				if (h.getParentID()!=d.getParentID()) {
					h.setHealth(h.getHealth() - d.getDamage());
					d.decrementLife();
				}

				Component dComponent = (Component)d;
				map.put(Damage.KEY, dComponent);
				if(d.getLifetime() == 0) {
					em.removeComponent(key, Damage.KEY, dComponent);
				}

			}
			
			if(h.getHealth() <= 0) {
				Player p = (Player) em.getComponent(key, Player.KEY);
				
				if(p!=null) {
				p.setLives(p.getLives()-1);
				h.resetHealth();
				}
				else {
				sm.removeEntity(key);
				System.out.println("removing");
				}
			}
			
		});
	}

	@Override
	public Map<Integer, Map<String, Component>> getHandledComponent() {
		return handledComponents;
	}
}
