package engine.systems.collisions;

import engine.components.Component;
import engine.components.Velocity;

import java.util.Map;

public class VelocityHandler {
    public void handle(int playerID, Map<String, Component> player, int colliderID, Map<String, Component> collider) {
        if (player.containsKey(Velocity.KEY)) {
            Velocity pVel = (Velocity) player.get(Velocity.KEY);
            if (collider.containsKey(Velocity.KEY)) {
                Velocity cVel = (Velocity) collider.get(Velocity.KEY);

                //pVel.setXVel(cVel.getXVel());
                //pVel.setYVel(cVel.getYVel());
            }
            else {
            	//e.g. hitting blocks stops player from moving
            	pVel.setXVel(0);
            	pVel.setYVel(0);
            }
        }

    }
}
