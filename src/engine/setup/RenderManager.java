package engine.setup;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import engine.components.Position;

public class RenderManager {

    private final double renderDistance;
    private double centerX, centerY;

    private Map<Integer, Position> withinRender = new HashMap<>(), outsideRender = new HashMap<>();

    public RenderManager (double renderDistance, double initialCenterX, double initialCenterY) {
        this.renderDistance = renderDistance;
        centerX = initialCenterX;
        centerY = initialCenterY;
    }

    public Set<Integer> getWithinRender() {
        return withinRender.keySet();
    }

    public void setCenterX (double newCenterX) {
        centerX = newCenterX;
    }

    public void setCenterY (double newCenterY) {
        centerY = newCenterY;
    }

    public void add (Position p) {
        double positionX = p.getXPos();
        double positionY = p.getYPos();
        if (withinRenderDistance(positionX, positionY)) withinRender.put(p.getParentID(), p);
        else outsideRender.put(p.getParentID(), p);
    }

    public void garbageCollect() {
        for (int id : withinRender.keySet()) {
            Position p = withinRender.get(id);
            if (!withinRenderDistance(p.getXPos(), p.getYPos())) {
                outsideRender.put(id, p);
                withinRender.remove(id);
            }
        }
    }

    public void renderObjects() {
        for (int id : outsideRender.keySet()) {
            Position p = outsideRender.get(id);
            if (withinRenderDistance(p.getXPos(), p.getYPos())) {
                withinRender.put(id, p);
                outsideRender.remove(id);
            }
        }
    }

    private boolean withinRenderDistance(double x, double y) {
        return centerX - x < renderDistance && centerY - y < renderDistance;
    }
}
