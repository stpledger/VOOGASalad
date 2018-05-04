package engine.components.presets;

import engine.actions.Actions;
import engine.components.Component;

import java.io.Serializable;
import java.util.Map;
import java.util.function.BiConsumer;

import engine.components.Collidable;
import engine.systems.collisions.CollisionDirection;


public class KoopaCollision extends Collidable {


    public KoopaCollision(int pid) {
        super(pid);

        this.setOnDirection(CollisionDirection.Top, 
        		(Serializable & BiConsumer<Map <String, Component>, Map<String, Component>>) Actions.damage());

        this.setOnDirection(CollisionDirection.Bot, (Serializable & BiConsumer<Map <String, Component>, Map<String, Component>>) (e1, e2) -> {
        		Actions.bounce(4, 80).accept(e1, e2);
        });
        
        		

        this.setOnDirection(CollisionDirection.Left, (Serializable & BiConsumer<Map <String, Component>, Map<String, Component>>)
        		(e1, e2) -> {
            Actions.damage().accept(e1, e2);
            Actions.bounce(1, 40).accept(e1, e2);
        });

        this.setOnDirection(CollisionDirection.Right, (Serializable & BiConsumer<Map <String, Component>, Map<String, Component>>)
        		(e1, e2) -> {
            Actions.damage().accept(e1, e2);
            Actions.bounce(2, 40).accept(e1, e2);
        });
    }
}
