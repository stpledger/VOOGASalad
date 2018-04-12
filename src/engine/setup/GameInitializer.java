package engine.setup;

import java.io.FileNotFoundException;
import java.util.*;

import engine.components.Component;
import engine.components.EntityType;
import engine.components.KeyInput;
import engine.components.Position;
import engine.systems.*;
import engine.systems.collisions.Collision;
import javafx.beans.property.SetProperty;
import javafx.scene.input.KeyCode;

public class GameInitializer {

    private List<ISystem> systems;

    private SystemManager SM;
    private RenderManager RM;
    private InputHandler IH;

    private EntityManager EM;

    public GameInitializer (Map <Integer, Map<String, Component>> entities) throws FileNotFoundException {
    		System.out.println("GameInitializer");
    		EM = new EntityManager(entities);
        systems = new ArrayList<>();
        systems.add(new Accelerate());
        systems.add(new Motion());
        IH = new InputHandler(); 
        Collision collision = new Collision();
        //systems.add(collision);
        systems.add(new Animate());
        systems.add(IH);
        SM = new SystemManager(systems, collision);

        double renderDistance = 300.0;
        double renderCenterX = 50;
        double renderCenterY = 50;
        RM = new RenderManager(renderDistance, renderCenterX, renderCenterY);

        for (int id : entities.keySet()) {
            Map<String, Component> components = entities.get(id);
            if (components.containsKey(Position.KEY)) {
                Position p = (Position) components.get(Position.KEY);

                RM.add(p);
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

    public EntityManager getEM() {
    		return EM;
    }

    public InputHandler getIH() {
         return IH;
         }

    public List<ISystem> getSystems() {		// For testing
    		return systems;
    }
    

    
}
