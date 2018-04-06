package engine.setup;

import java.util.*;

import engine.components.Component;
import engine.components.Position;
import engine.systems.*;

public class GameInitializer {

    private List<ISystem> systems;

    double renderCenterX;
    double renderCenterY;

    private SystemManager SM;
    private RenderManager RM;

    public GameInitializer (Map <Integer, Map<String, Component>> entities, double centerX, double centerY) {
        systems = new ArrayList<>();

        systems.add(new Accelerate());
        systems.add(new Motion());
        SM = new SystemManager(systems);

        double renderDistance = 150.0;
        renderCenterX = centerX;
        renderCenterY = centerY;
        RM = new RenderManager(renderDistance, renderCenterX, renderCenterY);


        for (int id : entities.keySet()) {
            Map<String, Component> components = entities.get(id);
            if (components.containsKey("Position")) {
                Position p = (Position) components.get("Position");
                RM.add(p);
            }
        }

        for (int id : RM.getWithinRender()) {
            Map<String, Component> components = entities.get(id);
            SM.addComponents(id, components);
        }
    }

    public SystemManager getSM () {
        return SM;
    }

    public RenderManager getRM() {
        return RM;
    }

}
