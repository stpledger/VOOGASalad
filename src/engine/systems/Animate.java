package engine.systems;

import engine.components.*;
import engine.components.groups.Position;
import engine.setup.EntityManager;
import javafx.scene.image.ImageView;

import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

/**
 * System which updates the entity's sprite depending on its position component which may (or may not) have been
 * moved in earlier systems, so that what the user sees moving on the screen mimics the values of that entity's
 * position, velocity, and acceleration components
 *
 * @author cndracos
 */
public class Animate implements ISystem {
    private Map<Integer, Map<String, Component>> handledComponents = new HashMap<>();
    private Set<Integer> activeComponents;
    private EntityManager em;
    
    public Animate(EntityManager em) {
    	this.em = em;
    }

    /**
     * Adds components which it can act upon, choosing only the entities which have both a position AND
     * a Sprite component
     * @param pid entity's ID
     * @param components all of the entity's components
     */
    @Override
    public void addComponent(int pid, Map<String, Component> components) {
        if (components.containsKey(Position.KEY) && components.containsKey(Sprite.KEY)) {
            Map<String, Component> newComponents = new HashMap<>();
            newComponents.put(Position.KEY,components.get(Position.KEY));
            newComponents.put(Sprite.KEY,components.get(Sprite.KEY));
            handledComponents.put(pid, newComponents);
        }
    }

    @Override
    public void removeComponent(int pid) {
        if(handledComponents.containsKey(pid)) {
            Sprite s = (Sprite) handledComponents.get(pid).get(Sprite.KEY);
            s.getImage().setX(10000);
            handledComponents.remove(pid);
        }
    }

    public void addComponent(int pid, String componentName) {
		if(!componentName.equals(Position.KEY) && !componentName.equals(Sprite.KEY)) {
			return;
		}
		
		if(handledComponents.containsKey(pid)) {
			System.out.println("Animate System tries adding duplicate " + componentName + " component for entity " + pid + " !");
		}
		

		Map<String, Component> map = new HashMap<>();
		map.put(componentName, em.getComponent(pid, componentName));
		if(componentName.equals(Position.KEY)) {
			Component component = em.getComponent(pid,Sprite.KEY);
			if(component == null) {
				System.out.println("Entity " + pid + " has " + componentName + " component but has no " + Sprite.KEY + " component!");
				return;
			}
			map.put(Sprite.KEY, component);
		}
		else {
			Component component = em.getComponent(pid,Position.KEY);
			if(component == null) {
				System.out.println("Entity " + pid + " has " + componentName + " component but has no " + Position.KEY + " component!");
				return;
			}
			map.put(Position.KEY, component);
		}
		handledComponents.put(pid,map);
    }

	public void removeComponent(int pid, String componentName) {
		if(!componentName.equals(Position.KEY) && !componentName.equals(Sprite.KEY)) {
			return;
		}
		
		if(!handledComponents.containsKey(pid)) {
			System.out.println("Animate System tries remove " + componentName + " from non-existing entity " + pid + " !");
		}
		handledComponents.remove(pid);
	}

    @Override
    public void setActives(Set<Integer> actives) {
        Set<Integer> myActives = new HashSet<>(actives);
        myActives.retainAll(handledComponents.keySet());
        activeComponents = myActives;
    }

    @Override
    public void execute(double time) {
        for (int pid : activeComponents) {

            Map<String, Component> components = handledComponents.get(pid);
            Sprite s = (Sprite) components.get(Sprite.KEY);
            Position p = (Position) components.get(Position.KEY);

            ImageView im = s.getImage();
            im.setX(p.getXPos()); //updates image x on position x pos
            im.setY(p.getYPos()); //updates image y on position y pos
        }
    }
    
    @Override
	public Map<Integer, Map<String, Component>> getHandledComponent() {
		return handledComponents;
	}
}
