package engine.setup;

import java.io.FileNotFoundException;
import java.util.*;

import engine.components.Component;
import engine.components.Position;
import engine.systems.*;
import engine.systems.collisions.Collision;

public class GameInitializer {

    private List<ISystem> systems;

    private SystemManager SM;
    private RenderManager RM;
    private InputHandler IH;

    private EntityManager EM;

    public GameInitializer (Map <Integer, Map<String, Component>> entities) throws FileNotFoundException {
        EM = new EntityManager(entities, SM);
        Collision c = new Collision();
        systems = new ArrayList<>();
        systems.add(new Accelerate(EM));
        systems.add(new Motion());
        IH = new InputHandler();
        systems.add(c);
        systems.add(new Animate());
        systems.add(IH);
        SM = new SystemManager(systems, c, EM);
        EM.setSM(SM);


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

    public void execute (double time) {
        SM.execute(time);
    }


    public InputHandler getIH() {
         return IH;
         }

    public RenderManager getRM() { return RM; }

    public List<ISystem> getSystems() {		// For testing
    		return systems;
    }
    

    
}
