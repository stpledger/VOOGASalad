package engine.setup;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import engine.components.groups.Position;


/**
 * Class which renders entities in and out of being acted upon based in a center location and rendering distance
 *
 * @author cndracos
 */
public class RenderManager {

    private final double renderDistance;
    private double centerX, centerY;

    private Map<Integer, Position> withinRender = new HashMap<>(), outsideRender = new HashMap<>();

    /**
     * Loads in a render distance and initial centers
     * @param renderDistance how far the program looks for other entities
     * @param initialCenterX where to initially start looking from in the X position
     * @param initialCenterY where to initially start looking from in the Y position
     */
    public RenderManager (double renderDistance, double initialCenterX, double initialCenterY) {
        this.renderDistance = renderDistance;
        centerX = initialCenterX;
        centerY = initialCenterY;
    }


    public void setCenterX (double newCenterX) {
        centerX = newCenterX; //sets a new center as the player moves
    }

    public void setCenterY (double newCenterY) {
        centerY = newCenterY; //sets a new center as the player moves
    }

    /**
     * Adds a new position to the system and loads it into outsideRender or insideRender
     * @param p new entity's position component
     */
    public void add (Position p) {
        if (withinRenderDistance(p.getXPos(), p.getYPos())) withinRender.put(p.getPID(), p);
        else outsideRender.put(p.getPID(), p);
    }

    public void garbageCollect() {
        for (Position p : withinRender.values()) {
            if (!withinRenderDistance(p.getXPos(), p.getYPos())) {
                outsideRender.put(p.getPID(), p);
                withinRender.remove(p.getPID());
            }
        }
    }

    public Set<Integer> renderObjects() {
        for (Position p : outsideRender.values()) {
            if (withinRenderDistance(p.getXPos(), p.getYPos())) {
                withinRender.put(p.getPID(), p);
                outsideRender.remove(p.getPID());
            }
        }
        return withinRender.keySet();
    }

    private boolean withinRenderDistance(double x, double y) {
        return centerX - x < renderDistance && centerY - y < renderDistance;
    }
}
