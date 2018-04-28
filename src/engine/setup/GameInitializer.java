package engine.setup;

import java.io.FileNotFoundException;
import java.util.*;

import engine.components.Component;
import engine.components.XPosition;
import engine.components.YPosition;
import engine.components.groups.Position;
import engine.systems.*;
import engine.systems.collisions.Collision;

/**
 * This is the class which is created when the player first decides to run a game. It creates the managers and loads
 * the components into the appropriate systems, setting up the game to be run smoothly.
 *
 * @author cndracos
 */
public class GameInitializer {

    private List<ISystem> systems = new ArrayList<>();

    private SystemManager systemManager;
    private RenderManager renderManager;
    private EntityManager entityManager;
    private InputHandler inputHandler;
    private Collision c;

    /**
     * Creates the managers and systems, then reads in the entities. Sets up the rendering system and input handler
     *
     * @param entities
     */
    public GameInitializer (Map <Integer, Map<String, Component>> entities,
                            double renderDistance, double renderCenterX, double renderCenterY) throws FileNotFoundException {
        renderManager = new RenderManager(renderDistance, renderCenterX, renderCenterY);
        entityManager = new EntityManager(entities, renderManager, systemManager);
        c = new Collision(entityManager);
        inputHandler = new InputHandler();

        addSystems();
        
        systemManager = new SystemManager(systems, c, entityManager);
        entityManager.setSM(systemManager);



        for (int id : entities.keySet()) {
            Map<String, Component> components = entities.get(id);
            if (components.containsKey(XPosition.KEY) && components.containsKey(YPosition.KEY)) {
                XPosition px = (XPosition) components.get(XPosition.KEY);
                YPosition py = (YPosition) components.get(YPosition.KEY);
                renderManager.add(new Position(px.getPID(), px.getData(), py.getData()));
                
            }
            systemManager.addEntity(id, components);
        }

        systemManager.setActives(renderManager.render(50, 50));
    }

    public void execute (double time) {
        systemManager.execute(time); //runs all functions of the systems
    }


    public InputHandler getInputHandler() {
         return inputHandler;

         }

    public Collision getC() {
    	return c;
    }

    public RenderManager getRenderManager() { return renderManager; }

    public SystemManager getSystemManager() { return systemManager; }

    public List<ISystem> getSystems() {		// For testing
    		return systems;
    }
    
    private void addSystems() {
        systems.add(new Accelerate());
        systems.add(new Motion());
        systems.add(new ConditionChecker());
        systems.add((new ArtificialIntelligence()));
        systems.add(c);
        systems.add(new HealthDamage(entityManager));
        systems.add(new Animate());
        systems.add(inputHandler);
    }

}
