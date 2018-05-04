package engine.components.presets;

import engine.actions.Actions;
import engine.components.Collidable;
import engine.components.Player;
import engine.systems.collisions.CollisionDirection;

public class FireballCollision extends Collidable {

	public FireballCollision(int pid) {
		super(pid);
		
		this.setOnDirection(CollisionDirection.Top, (e1,e2) -> {
			if(!e2.containsKey(Player.KEY)) {
				Actions.damage().accept(e1, e2);
				Actions.remove().accept(e1);
			}
			
		});
		
		this.setOnDirection(CollisionDirection.Bot, (e1,e2) -> {
			
			Actions.damage().accept(e1, e2);
			Actions.moveUp(50).accept(e2);
			Actions.remove().accept(e1);
			
		});

		this.setOnDirection(CollisionDirection.Left, (e1,e2) -> {
			if(!e2.containsKey(Player.KEY)) {
				Actions.damage().accept(e1, e2);
				Actions.remove().accept(e1);
			}
			
		});
		this.setOnDirection(CollisionDirection.Right, (e1,e2) -> {
			if(!e2.containsKey(Player.KEY)) {
				Actions.damage().accept(e1, e2);
				Actions.remove().accept(e1);
			}
			
		});
		
	}

}
