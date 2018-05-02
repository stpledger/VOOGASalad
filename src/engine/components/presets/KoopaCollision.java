package engine.components.presets;

import engine.actions.Actions;
import engine.components.Collidable;
import engine.systems.collisions.CollisionDirection;

public class KoopaCollision extends Collidable {


    public KoopaCollision(int pid) {
        super(pid);

        this.setOnDirection(CollisionDirection.Top, Actions.damage());

        this.setOnDirection(CollisionDirection.Bot, Actions.bounce(CollisionDirection.Bot, 40));

        this.setOnDirection(CollisionDirection.Left, Actions.damage(), Actions.bounce(CollisionDirection.Left, 40));

        this.setOnDirection(CollisionDirection.Right, Actions.damage(), Actions.bounce(CollisionDirection.Right, 40));
    }
}
