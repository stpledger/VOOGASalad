package engine.components.presets;

import java.io.Serializable;
import java.util.Map;
import java.util.function.BiConsumer;

import engine.actions.Actions;
import engine.components.Collidable;
import engine.components.Component;
import engine.components.Jumps;
import engine.systems.collisions.CollisionDirection;

/**
 * Presets made for final demo/authoring.
 * Contains more advanced game logic that can be expanded on, but is not included in systems.
 * In this way, more "advanced" game makers can add their own code.
 * @author fitzj
 *
 */
 public class PlayerCollision extends Collidable {

	private static final int FRICTION = 50;
	
	@SuppressWarnings("unchecked")
	public PlayerCollision(int pid) {
		super(pid);
		
		this.setOnDirection(CollisionDirection.Top, Actions.damage(), Actions.transferScore(), Actions.xFriction(FRICTION));
		//this.setOnDirection(CollisionDirection.Top, Actions.moveUp(0));
		
		this.setOnDirection(CollisionDirection.Bot, Actions.moveDown(50));
		
		this.setOnDirection(CollisionDirection.Left, Actions.moveLeft(0));
		
		this.setOnDirection(CollisionDirection.Right, Actions.moveRight(0));
		
	}

}
