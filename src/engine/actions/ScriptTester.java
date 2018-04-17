package engine.actions;

import engine.components.Component;
import engine.components.Position;
import engine.components.Velocity;

import java.util.HashMap;
import java.util.Map;

public class ScriptTester {

    public static void main (String [] args) {

        HashMap<Integer, Map<String, Component>> entities = new HashMap<>();
        HashMap<String, Component> mario = new HashMap<>();
        Position p = new Position(1, 0, 0);
        Velocity v = new Velocity(1, 0, 0);
        mario.put(Position.KEY, p);
        mario.put(Velocity.KEY, v);
        entities.put(1, mario);

        ScriptReader SR = new ScriptReader(entities);
        SR.readCommand("1 Position setXPos 10.0");

    }
}
