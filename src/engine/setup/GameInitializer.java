package engine.setup;

import java.io.FileNotFoundException;
import java.util.*;

import engine.components.Component;
import engine.components.groups.Position;
import engine.systems.*;
import engine.systems.collisions.Collision;
import engine.systems.collisions.LevelStatus;

/**
 * This is the class which is created when the player first decides to run a game. It creates the managers and loads
 * the components into the appropriate systems, setting up the game to be run smoothly.
 *
 * @author cndracos
 */
public class GameInitializer {

    private List<ISystem> systems;

    private SystemManager SM;
    private RenderManager RM;
    private InputHandler IH;
    private Collision c;
    private EntityManager EM;

    /**
     * Creates the managers and systems, then reads in the entities. Sets up the rendering system and input handler
     *
     * @param entities
     */
    public GameInitializer (Map <Integer, Map<String, Component>> entities) throws FileNotFoundException {
        EM = new EntityManager(entities, SM);
         c = new Collision(EM);
        systems = new ArrayList<>();
        systems.add(new Accelerate(EM));
        systems.add(new Motion());
        IH = new InputHandler();
        systems.add((new ArtificialIntelligence()));
        systems.add(c);
        systems.add(new Animate(EM));
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
        SM.execute(time); //runs all functions of the systems
    }


    public InputHandler getIH() {
         return IH;
         }

    public Collision getC() {
    	return c;
    }

    public RenderManager getRM() { return RM; }

    public SystemManager getSM() { return SM; }

    public List<ISystem> getSystems() {		// For testing
    		return systems;
    }
    

    
}
