package engine.actions;


import authoring.entities.Enemy;


import java.util.ArrayList;
import java.util.List;

import engine.components.Player;
import engine.components.groups.Position;
import engine.components.groups.Velocity;

import java.util.function.Consumer;

public class ScriptTester {

    public static void main (String [] args) {

       ActionReader AR = new ActionReader();
       List<Object> arguments = new ArrayList<>();
       Player player = new Player(1, "Mario");
       Position p = new Position(1, 100, 100);
       player.add(p);
       Enemy enemy = new Enemy(2, "Enemy");
       Position p2 = new Position(2, 50, 200);
       Velocity v = new Velocity(2, 0, 0);
       enemy.add(p2);
       enemy.add(v);
       arguments.add(player);
       arguments.add(enemy);
       Consumer c = AR.getAction("followsYou", arguments);
       c.accept(10.0);
       System.out.println(v.getXVel());

    }
}
