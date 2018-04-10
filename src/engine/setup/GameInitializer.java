package engine.setup;

import java.io.FileNotFoundException;
import java.util.*;

import engine.components.Component;
import engine.components.KeyInput;
import engine.components.Position;
import engine.systems.*;

public class GameInitializer {

    private List<ISystem> systems;

    private SystemManager SM;
    private RenderManager RM;
    private InputHandler IH;

    public GameInitializer (Map <Integer, Map<String, Component>> entities) throws FileNotFoundException {
        systems = new ArrayList<>();
        systems.add(new Accelerate());
        systems.add(new Motion());
        systems.add(new Animate());
        IH = new InputHandler();
        systems.add(IH);
        SM = new SystemManager(systems);

        double renderDistance = 300.0;
        double renderCenterX = 50;
        double renderCenterY = 50;
        RM = new RenderManager(renderDistance, renderCenterX, renderCenterY);

        for (int id : entities.keySet()) {
            Map<String, Component> components = entities.get(id);
            if (components.containsKey(Position.getKey())) {
                Position p = (Position) components.get(Position.getKey());
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

    public InputHandler getIH() { return IH; }

    public List<ISystem> getSystems() {		// For testing
    	return systems;
    }
}
