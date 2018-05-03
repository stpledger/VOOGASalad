package engine.components.presets;

import java.util.Map;
import java.util.function.BiConsumer;

import engine.components.Collidable;
import engine.components.Component;
import engine.components.YAcceleration;
import engine.systems.collisions.CollisionDirection;

public class RollerJumpPad extends Collidable {

	public RollerJumpPad(int pid) {
		super(pid);

		this.setOnDirection(CollisionDirection.Top, jumper());
		this.setOnDirection(CollisionDirection.Bot, jumper());
		this.setOnDirection(CollisionDirection.Left, jumper());
		this.setOnDirection(CollisionDirection.Right, jumper());

	}
	
	private BiConsumer<Map<String,Component>,Map<String,Component>> jumper() {
		return (e1,e2)->{
			if(e2.containsKey(YAcceleration.KEY)) {
				YAcceleration ya2 = (YAcceleration) e2.get(YAcceleration.KEY);
				ya2.setData(-ya2.getData());
			}
		};
	}
	
}
