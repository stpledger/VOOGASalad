package engine.components.presets;

import engine.actions.Actions;
import engine.components.Collidable;
import engine.components.Player;
import engine.systems.collisions.CollisionDirection;

public class BottomCollision extends Collidable {

    public BottomCollision(int pid) {
        super(pid);

        this.setOnDirection(CollisionDirection.Top, (e1,e2) -> {
            System.out.println("Bottom Line collides top");
            if(e2.containsKey(Player.KEY)) {
                System.out.println("Bottom Line collides with player");
                Actions.remove().accept(e2);
            }
        });

        this.setOnDirection(CollisionDirection.Bot, (e1,e2) -> {
            System.out.println("Bottom Line collides bot");
            Actions.damage().accept(e1, e2);
            Actions.moveUp(50).accept(e2);
            Actions.remove().accept(e1);

        });

        this.setOnDirection(CollisionDirection.Left, (e1,e2) -> {
            System.out.println("Bottom Line collides left");
            if(!e2.containsKey(Player.KEY)) {
                Actions.damage().accept(e1, e2);
                Actions.remove().accept(e1);
            }

        });
        this.setOnDirection(CollisionDirection.Right, (e1,e2) -> {
            System.out.println("Bottom Line collides right");
            if(!e2.containsKey(Player.KEY)) {
                Actions.damage().accept(e1, e2);
                Actions.remove().accept(e1);
            }

        });
    }

}
