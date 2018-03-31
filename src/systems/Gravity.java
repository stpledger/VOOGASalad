package systems;

import java.util.List;

import components.IComponent;

public class Gravity implements ISystem {

    private double acceleration;

    public Gravity () {
        acceleration = 9.8;
    }

    @Override
    public void execute(List<IComponent> components) {

    }
}
