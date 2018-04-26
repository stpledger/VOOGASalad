package engine.setup;

import java.util.*;

import engine.components.Position;
import javafx.geometry.Pos;

/**
 * Class which renders entities in and out of being acted upon based in a center location and rendering distance
 *
 * @author cndracos
 */
public class RenderManager {

    private final double renderDistance;
    private double centerX, centerY;

    private List<Integer> swapList = new ArrayList<>();
    private Map<Integer, Position> withinRender = new HashMap<>(), outsideRender = new HashMap<>();

    /**
     * Loads in a render distance and initial centers
     * @param renderDistance how far the program looks for other entities
     * @param initialCenterX where to initially start looking from in the X position
     * @param initialCenterY where to initially start looking from in the Y position
     */
    public RenderManager (double renderDistance, double initialCenterX, double initialCenterY) {
        this.renderDistance = renderDistance;
        centerX = initialCenterX; centerY = initialCenterY;
    }

    /**
     * Adds a new position to the system and loads it into outsideRender or insideRender
     * @param p new entity's position component
     */
    public void add (Position p) {
        if (withinRenderDistance(p.getXPos(), p.getYPos())) withinRender.put(p.getParentID(), p);
        else outsideRender.put(p.getParentID(), p);
    }

    public Set<Integer> render(double newCenterX, double newCenterY) {
        centerX = newCenterX; centerY = newCenterY;
        garbageCollect(); renderObjects();
        return withinRender.keySet();
    }

    private void garbageCollect() {
        updateNodes(withinRender, outsideRender, false);
    }

    private void renderObjects() {
        updateNodes(outsideRender, withinRender, true);
    }

    private void updateNodes (Map<Integer, Position> origin, Map<Integer, Position> destination, boolean intoRender) {
        for (Iterator<Position> iterator = origin.values().iterator(); iterator.hasNext(); ) {
            Position p = iterator.next();
            if (withinRenderDistance(p.getXPos(), p.getYPos()) == intoRender) {
                destination.put(p.getParentID(), p);
                swapList.add(p.getParentID());
            }
        }
        removeOldNodes(origin);
    }

    private void removeOldNodes (Map<Integer, Position> updated) {
        for (int i : swapList) {
            updated.remove(i);
        }
        swapList.clear();
    }

    private boolean withinRenderDistance(double x, double y) {
        return Math.abs(centerX - x) < renderDistance && Math.abs(centerY - y) < renderDistance;
    }
}
