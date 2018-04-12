package engine.systems;

import engine.components.*;

import engine.setup.EntityManager;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class Animate implements ISystem {
    private Map<Integer, Map<String, Component>> handledComponents = new HashMap<>();
    private Set<Integer> activeComponents;

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
		map.put(componentName,EntityManager.getComponent(pid, componentName));
		if(componentName.equals(Position.KEY)) {
			Component component = EntityManager.getComponent(pid,Sprite.KEY);
			if(component == null) {
				System.out.println("Entity " + pid + " has " + componentName + " component but has no " + Sprite.KEY + " component!");
				return;
			}
			map.put(Sprite.KEY, component);
		}
		else {
			Component component = EntityManager.getComponent(pid,Position.KEY);
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
        activeComponents = actives;
    }

    @Override
    public void execute(double time) {
        for (int pid : activeComponents) {
            if (handledComponents.keySet().contains(pid)) {
                Map<String, Component> components = handledComponents.get(pid);
                Sprite s = (Sprite) components.get(Sprite.KEY);
                Position p = (Position) components.get(Position.getKey());

                ImageView im = s.getImage();
                im.setX(p.getXPos());
                im.setY(p.getYPos());
            }
        }
    }
    
    @Override
	public Map<Integer, Map<String, Component>> getHandledComponent() {
		return handledComponents;
	}
}
