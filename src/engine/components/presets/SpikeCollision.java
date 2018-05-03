package engine.components.presets;

import engine.actions.Actions;
import engine.components.Collidable;
import engine.systems.collisions.CollisionDirection;

public class SpikeCollision extends Collidable{

	public SpikeCollision(int pid) {
		super(pid);
		
		this.setOnDirection(CollisionDirection.Top, Actions.damage());
		this.setOnDirection(CollisionDirection.Bot, (e1,e2) -> {
			
			Actions.damage().accept(e1, e2);
			Actions.moveUp(50).accept(e2);
			
		});

		this.setOnDirection(CollisionDirection.Left, Actions.damage());
		this.setOnDirection(CollisionDirection.Right, Actions.damage());

		
	}
	
}
