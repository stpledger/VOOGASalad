package engine.actions;

import engine.components.Component;
import engine.components.KeyInput;
import engine.components.Position;
import engine.components.Velocity;
import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

public class ScriptTester {

    public static void main (String [] args) {

        HashMap<Integer, Map<String, Component>> entities = new HashMap<>();
        HashMap<String, Component> mario = new HashMap<>();
        Position p = new Position(1, 0, 0);
        Velocity v = new Velocity(1, 0, 0);
        KeyInput k = new KeyInput(1);
        mario.put(Position.KEY, p);
        mario.put(Velocity.KEY, v);
        mario.put(KeyInput.KEY, k);
        entities.put(1, mario);

        ScriptReader SR = new ScriptReader(entities);

        HashMap<String, Component> mario2 = new HashMap<>();
        Position p2 = new Position(2, 0, 0);
        Velocity v2 = new Velocity(2, 0, 0);
        KeyInput k2 = new KeyInput(2);
        mario2.put(Position.KEY, p2);
        mario2.put(Velocity.KEY, v2);
        mario2.put(KeyInput.KEY, k2);
        entities.put(2, mario2);
        entities.put(2, mario2);

        SR.readCommand("2 Position setXPos 20");

        k2.action(KeyCode.P);

        System.out.println(p2.getXPos());

    }
}
