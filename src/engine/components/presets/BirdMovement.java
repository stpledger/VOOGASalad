package engine.components.presets;

import java.io.Serializable;
import java.util.Map;
import java.util.function.Consumer;

import engine.actions.Actions;
import engine.components.Component;
import engine.components.Height;
import engine.components.Jumps;
import engine.components.KeyInput;
import engine.components.Sprite;
import engine.components.YPosition;
import javafx.scene.input.KeyCode;

public class BirdMovement extends KeyInput {

	
	private static final int JUMP_SPEED = 100;
	private static final int MOVE_SPEED = 100;
	
	private boolean crouched;
	private double timing;
	@SuppressWarnings("unchecked")
	public BirdMovement(int pid, KeyCode up) {
		super(pid);
		
		timing = System.currentTimeMillis();
		
		
		this.addCode(up, (Serializable & Consumer<Map<String,Component>>) map -> {
			long time = System.currentTimeMillis();
			
			
							
					Actions.moveUp(JUMP_SPEED).accept(map);					
					timing = time;

			
			if(map.containsKey(Sprite.KEY)) {
				Sprite s = (Sprite) map.get(Sprite.KEY);
				if(s.isPlaying()) {
					s.pauseAnimation();
				}
			}
					
			
		});
		
		
		
		this.addCode(KeyCode.SPACE, Actions.fireball());
		
	}
	
}
