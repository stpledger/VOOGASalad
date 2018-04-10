package engine.systems;

import engine.components.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class Animate implements ISystem {

    private static final int POSITION_INDEX = 0;
    private static final int SPRITE_INDEX = 1;
    private static final int VELOCITY_INDEX = 2;

    private Map<Integer, List<Component>> handledComponents = new HashMap<>();
    private Set<Integer> activeComponents;
    private int playerID;

    @Override
    public void addComponent(int pid, Map<String, Component> components) {
        if (components.containsKey(Position.getKey()) && components.containsKey(Sprite.getKey())) {
            List<Component> newComponents = new ArrayList<>();
            newComponents.add(components.get(Position.getKey()));
            newComponents.add(components.get(Sprite.getKey()));
            if (components.containsKey(Player.getKey())) {
                playerID = pid;
                newComponents.add(components.get(Velocity.getKey()));
            }
            handledComponents.put(pid, newComponents);
        }
    }

    @Override
    public void removeComponent(int pid) {
        if(handledComponents.containsKey(pid)) {
            handledComponents.remove(pid);
        }
    }

    @Override
    public void setActives(Set<Integer> actives) {
        activeComponents = actives;
    }

    @Override
    public void execute(double time) {
        for (int pid : activeComponents) {
            List<Component> components = handledComponents.get(pid);
            Position p = (Position) components.get(POSITION_INDEX);
            Sprite s = (Sprite) components.get(SPRITE_INDEX);

            ImageView iv = s.getImage();
            iv.setX(p.getXPos());
            iv.setY(p.getYPos());
        }
    }
}
