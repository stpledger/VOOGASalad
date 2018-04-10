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


    public void setCenterX (double newCenterX) {
        centerX = newCenterX;
    }

    public void setCenterY (double newCenterY) {
        centerY = newCenterY;
    }

    public void add (Position p) {
        if (withinRenderDistance(p.getXPos(), p.getYPos())) withinRender.put(p.getParentID(), p);
        else outsideRender.put(p.getParentID(), p);
    }

    public void garbageCollect() {
        for (Position p : withinRender.values()) {
            if (!withinRenderDistance(p.getXPos(), p.getYPos())) {
                outsideRender.put(p.getParentID(), p);
                withinRender.remove(p.getParentID());
            }
        }
    }

    public Set<Integer> renderObjects() {
        for (Position p : outsideRender.values()) {
            if (withinRenderDistance(p.getXPos(), p.getYPos())) {
                withinRender.put(p.getParentID(), p);
                outsideRender.remove(p.getParentID());
            }
        }
        return withinRender.keySet();
    }

    private boolean withinRenderDistance(double x, double y) {
        return centerX - x < renderDistance && centerY - y < renderDistance;
    }
}
