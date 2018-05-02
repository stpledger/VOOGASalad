package engine.components.presets;

import engine.components.Collidable;
import engine.components.Health;
import engine.components.Jumps;
import engine.systems.collisions.CollisionDirection;

public class RollerCollision extends Collidable {

	public RollerCollision(int pid) {
		super(pid);

		this.setOnDirection(CollisionDirection.Left, (e1,e2) -> {
			if(e1.containsKey(Health.KEY)) {
				((Health) e1.get(Health.KEY)).setData(0);
			}
		});
	

		this.setOnDirection(CollisionDirection.Top, (e1,e2) -> {
			if(e1.containsKey(Jumps.KEY)) {
				((Jumps) e1.get(Jumps.KEY)).reset();
			}
		});
		
		this.setOnDirection(CollisionDirection.Bot, (e1,e2) -> {
			if(e1.containsKey(Jumps.KEY)) {
				((Jumps) e1.get(Jumps.KEY)).reset();
			}
		});
		
	}

}
