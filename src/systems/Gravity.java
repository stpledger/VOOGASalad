package systems;

import java.util.List;

import components.Velocity;
import entities.Entity;

public class Gravity implements ISystem {

    private final double acceleration = 9.8;
    private final String name = "Velocity";

    public Gravity () { }

    @Override
    public void execute(List<Entity> entities) {
        for (Entity e: entities) {
            Velocity v = (Velocity) e.get(name);
            v.YVel+=acceleration;
        }
    }
}
