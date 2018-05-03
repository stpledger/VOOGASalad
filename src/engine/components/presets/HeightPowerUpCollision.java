package engine.components.presets;

import engine.actions.Actions;
import engine.components.Collidable;
import engine.components.Player;
import engine.systems.collisions.CollisionDirection;

public class HeightPowerUpCollision extends Collidable {

	public HeightPowerUpCollision(int pid) {
		super(pid);
		
		this.setOnDirection(CollisionDirection.Top, (e1,e2) -> {
			if(!e2.containsKey(Player.KEY)) {
				Actions.HeightPowerUp();
			}
			
		});
		
		this.setOnDirection(CollisionDirection.Bot, (e1,e2) -> {
			
			Actions.HeightPowerUp();
			
		});

		this.setOnDirection(CollisionDirection.Left, (e1,e2) -> {
			if(!e2.containsKey(Player.KEY)) {
				Actions.HeightPowerUp();
			}
			
		});
		this.setOnDirection(CollisionDirection.Right, (e1,e2) -> {
			if(!e2.containsKey(Player.KEY)) {
				Actions.HeightPowerUp();;
			}
			
		});
		
	}
}
