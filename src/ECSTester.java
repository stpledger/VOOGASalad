import components.*;
import entities.*;
import systems.*;

import java.util.List;
import java.util.ArrayList;

public class ECSTester {

    public static void main (String[] args) {
        Entity e = new Entity(001);
        Velocity v = new Velocity(2, 10);
        e.add("Velocity", v);
        Gravity g = new Gravity();
        List<Entity> gEntities = new ArrayList<>();
        gEntities.add(e);
        System.out.println(v.XVel + " " + v.YVel);
        g.execute(gEntities);
        System.out.println(v.XVel + " " + v.YVel);
    }
}
