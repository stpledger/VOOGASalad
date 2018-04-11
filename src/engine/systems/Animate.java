package engine.systems;

import engine.components.*;
import engine.setup.EntityManager;
import javafx.scene.image.ImageView;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class Animate implements ISystem {
	private final String PLAYER = "player";
    private Map<Integer, Map<String, Component>> handledComponents = new HashMap<>();
    private Set<Integer> activeComponents;
    private int playerID;

    @Override
    public void addComponent(int pid, Map<String, Component> components) {
        if (components.containsKey(Position.getKey()) && components.containsKey(Sprite.getKey())) {
            Map<String, Component> newComponents = new HashMap<>();
            newComponents.put(Position.getKey(),components.get(Position.getKey()));
            newComponents.put(Sprite.getKey(),components.get(Sprite.getKey()));
            if (((EntityType)components.get(EntityType.getKey())).getType().equals(PLAYER)) {
                playerID = pid;
                newComponents.put(Velocity.getKey(),components.get(Velocity.getKey()));
            }
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
		if(!componentName.equals(Position.getKey()) && !componentName.equals(Sprite.getKey())) {
			return;
		}
		
		if(handledComponents.containsKey(pid)) {
			System.out.println("Animate System tries adding duplicate " + componentName + " component for entity " + pid + " !");
		}
		

		Map<String, Component> map = new HashMap<>();
		map.put(componentName,EntityManager.getComponent(pid, componentName));
		if(componentName.equals(Position.getKey())) {
			Component component = EntityManager.getComponent(pid,Sprite.getKey());
			if(component == null) {
				System.out.println("Entity " + pid + " has " + componentName + " component but has no " + Sprite.getKey() + " component!");
				return;
			}
			map.put(Sprite.getKey(), component);
		}
		else {
			Component component = EntityManager.getComponent(pid,Position.getKey());
			if(component == null) {
				System.out.println("Entity " + pid + " has " + componentName + " component but has no " + Position.getKey() + " component!");
				return;
			}
			map.put(Position.getKey(), component);
		}
		handledComponents.put(pid,map);
    }

	public void removeComponent(int pid, String componentName) {
		if(!componentName.equals(Position.getKey()) && !componentName.equals(Sprite.getKey())) {
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
        Map<String, Component> components = handledComponents.get(playerID);
        Velocity v = (Velocity) components.get(Velocity.getKey());
        double xVel = v.getXVel();
        double yVel = v.getYVel();

        for (int pid : handledComponents.keySet()) {
            components = handledComponents.get(pid);
            Position p = (Position) components.get(Position.getKey());

            if (pid!=playerID) {
                p.setXPos(p.getXPos()-xVel*time);
                p.setYPos(p.getYPos()-yVel*time);

                if (activeComponents.contains(pid)) {
                    Sprite s = (Sprite) components.get(Sprite.getKey());

                    ImageView im = s.getImage();
                    im.setX(p.getXPos());
                    im.setY(p.getYPos());
                }
            }
        }
    }
    
    @Override
	public Map<Integer, Map<String, Component>> getHandledComponent() {
		return handledComponents;
	}
}
