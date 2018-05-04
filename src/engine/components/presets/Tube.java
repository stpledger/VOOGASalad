package engine.components.presets;

import engine.actions.Actions;
import engine.components.Collidable;
import engine.components.Player;
import engine.systems.collisions.CollisionDirection;

public class Tube extends Collidable {

	public Tube(int pid) {
		super(pid);
		
		this.setOnDirection(CollisionDirection.Top, (e1,e2) -> {
			if(!e2.containsKey(Player.KEY)) {
				Actions.damage().accept(e1, e2);
			}
			
		});
		
		this.setOnDirection(CollisionDirection.Bot, (e1,e2) -> {
			
			Actions.damage().accept(e1, e2);
			
		});

		this.setOnDirection(CollisionDirection.Left, (e1,e2) -> {
			if(!e2.containsKey(Player.KEY)) {
				Actions.damage().accept(e1, e2);
			}
			
		});
		this.setOnDirection(CollisionDirection.Right, (e1,e2) -> {
			if(!e2.containsKey(Player.KEY)) {
				Actions.damage().accept(e1, e2);
			}
			
		});
		
	}
}
