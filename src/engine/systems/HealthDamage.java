package engine.systems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import engine.components.Component;
import engine.components.DamageLifetime;
import engine.components.DamageValue;

import engine.components.Health;
import engine.components.Lives;
import engine.setup.SystemManager;

/**
 * A system that handles what happens when two entities collide, one having health component and the other damage
 * @author cndracos
 * @author sv116
 * @author Yameng Liu
 */
public class HealthDamage extends AbstractSystem implements ISystem {

	private SystemManager sm;
	
	public HealthDamage(SystemManager sm) {
		super();
		this.sm = sm;
	}

	public void addComponent(int pid, Map<String, Component> components) {
		Map<String, Component> newComponents;

		if (this.checkComponents(components, Health.KEY)) {
			newComponents = new HashMap<>();
			newComponents.put(Health.KEY, components.get(Health.KEY));
			this.directAddComponent(pid, newComponents);
		}
		else if (this.getHandled().containsKey(pid) && this.checkComponents(components, DamageValue.KEY, DamageLifetime.KEY)) {
			DamageValue d = (DamageValue) components.get(DamageValue.KEY);
			DamageLifetime dl = (DamageLifetime) components.get(DamageLifetime.KEY);
			newComponents = this.getHandled().get(pid);

			if (d.getPID()!=pid) {
				newComponents.put(DamageValue.KEY, d);
				newComponents.put(DamageLifetime.KEY, dl);
				this.directAddComponent(pid, newComponents);
			}			
		}

		if (this.getHandled().containsKey(pid) && components.containsKey(Lives.KEY)) {
			newComponents = this.getHandled().get(pid);
			newComponents.put(Lives.KEY, components.get(Lives.KEY));
			this.directAddComponent(pid, newComponents);
		}
	}
	
	private void kill(int key) {
		Map<String,Component> map = this.getHandled().get(key);
		if(map.containsKey(Lives.KEY)) {
			Lives l =((Lives) map.get(Lives.KEY));
			Health h = (Health) map.get(Health.KEY);
			l.setData(l.getData()-1);
			if(l.getData()>=0) {
				h.resetHealth();
			} else {
			   sm.removeEntity(key);
			}
		} else {
			sm.removeEntity(key);
		}
	}
	
	public void execute(double time) {
		for (int key : this.getActives()) {
			Map<String, Component> map = this.getHandled().get(key);
			if(map.containsKey(DamageValue.KEY) && map.containsKey(DamageLifetime.KEY) && map.containsKey(Health.KEY)) {
				this.processDamage(key);
			}
		}
	}

	private void processDamage(int key) {
		Map<String,Component> map = this.getHandled().get(key);
		Health h = (Health) map.get(Health.KEY);
		DamageValue d = (DamageValue) map.get(DamageValue.KEY);
		DamageLifetime dl = (DamageLifetime) map.get(DamageLifetime.KEY);

		if (h.getPID()!=d.getPID()) {
			h.setData(h.getData() - d.getData());
			dl.setData(dl.getData() - 1);
			if(dl.getData() <= 0) {
				map.remove(d.getKey());
				map.remove(dl.getKey());
			}
		}

		if(h.getData() <= 0 && d!=null) {
			this.kill(key);
		}		
	}

}
