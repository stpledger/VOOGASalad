package engine.setup;

import java.io.FileNotFoundException;
import java.util.*;

import engine.components.Component;
import engine.components.KeyInput;
import engine.components.Position;
import engine.components.Sprite;
import engine.systems.*;

public class GameInitializer {

    private List<ISystem> systems;

    private SystemManager SM;
    private RenderManager RM;
    private EntityManager EM;
    
    public GameInitializer (Map <Integer, Map<String, Component>> entities) throws FileNotFoundException {
        systems = new ArrayList<>();
        systems.add(new Accelerate());
        systems.add(new Motion());
        systems.add(new Animate());
        SM = new SystemManager(systems);
        EM = new EntityManager(entities, SM);
        
        double renderDistance = 300.0;
        double renderCenterX = 50;
        double renderCenterY = 50;
        RM = new RenderManager(renderDistance, renderCenterX, renderCenterY);

        for (int id : entities.keySet()) {
            Map<String, Component> components = entities.get(id);
            if (components.containsKey(Position.getKey())) {
                Position p = (Position) components.get(Position.getKey());
                RM.add(p);
                /**if (components.containsKey(Sprite.getKey())) {
                    Sprite s = (Sprite) components.get(Sprite.getKey());
                    try {
                        s.setImage(s.getName());
                        s.getImage().setX(p.getXPos());
                        s.getImage().setY(p.getYPos());
                    } catch (Exception e) {
                        throw new FileNotFoundException();
                    }
                }**/
            }
            if (components.containsKey(KeyInput.getKey())) {

            }
            SM.addEntity(id, components);
        }

        SM.setActives(RM.renderObjects());
    }

    public SystemManager getSM () {
        return SM;
    }

    public RenderManager getRM() {
        return RM;
    }

    public List<ISystem> getSystems() {		// For testing
    	return systems;
    }
}
