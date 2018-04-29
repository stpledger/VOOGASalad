package engine.components.presets;

import java.io.Serializable;
import java.util.Map;
import java.util.function.BiConsumer;

import engine.actions.Actions;
import engine.components.Collidable;
import engine.components.Component;
import engine.systems.collisions.CollisionDirection;

public class PlayerCollision extends Collidable {

	@SuppressWarnings("unchecked")
	public PlayerCollision(int pid) {
		super(pid);
		
		this.setOnDirection(CollisionDirection.Top, (Serializable & BiConsumer<Map<String,Component>, Map<String,Component>>) (e1, e2) -> {
			
			Actions.damage().accept(e1, e2);
			Actions.moveUp(50).accept(e1);
			Actions.transferScore().accept(e1, e2);
			
		});
		
		this.setOnDirection(CollisionDirection.Bot, (Serializable & BiConsumer<Map<String,Component>, Map<String,Component>>) (e1, e2) -> {
			Actions.moveDown(50).accept(e1);
		});
		
		this.setOnDirection(CollisionDirection.Left, (Serializable & BiConsumer<Map<String,Component>, Map<String,Component>>) (e1, e2) -> {
			
			
			
		});
		
		this.setOnDirection(CollisionDirection.Right, (Serializable & BiConsumer<Map<String,Component>, Map<String,Component>>) (e1, e2) -> {
			
			
		});
		
	}

}
