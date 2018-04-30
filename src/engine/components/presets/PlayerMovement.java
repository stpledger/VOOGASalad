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

public class PlayerMovement extends KeyInput {

	private boolean crouched;
	private double timing;
	@SuppressWarnings("unchecked")
	public PlayerMovement(int pid, KeyCode left, KeyCode right, KeyCode up, KeyCode down) {
		super(pid);
		crouched = false;
		timing = System.currentTimeMillis();
		this.addCode(left, (Serializable & Consumer<Map<String,Component>>) (map) -> {
			Actions.moveLeft(100).accept(map);
			if(map.containsKey(Sprite.KEY)) {
				Sprite s = (Sprite) map.get(Sprite.KEY);
				if(!s.isPlaying()) {
					//s.setData(".png");
					//s.animate(1000, 27, 7, 0, 0, 75, 85);
				}
				s.getImage().setScaleX(-1);
			}
		});
		
		this.addCode(right, (Serializable & Consumer<Map<String,Component>>) (map) -> {
			Actions.moveRight(100).accept(map);
			if(map.containsKey(Sprite.KEY)) {
				Sprite s = (Sprite) map.get(Sprite.KEY);
				if(!s.isPlaying()) {
					//s.setData("braid.png");
					//s.animate(1000, 27, 7, 0, 0, 75, 85);
				}
				s.getImage().setScaleX(1);
			}
		});
		
		this.addCode(up, (Serializable & Consumer<Map<String,Component>>) (map) -> {
			long time = System.currentTimeMillis();
			Actions.xFriction(0).accept(map, null);
			if(map.containsKey(Jumps.KEY) && time - timing > 200) {
				
				Jumps s = (Jumps) map.get(Jumps.KEY);
				if(s.getData() > 0) {
					Actions.moveUp(200).accept(map);
					s.setData(s.getData() - 1);
					timing = time;

				} 
			}
			
			if(map.containsKey(Sprite.KEY)) {
				Sprite s = (Sprite) map.get(Sprite.KEY);
				if(s.isPlaying()) {
					s.pauseAnimation();
				}
			}
			
			
			if(map.containsKey(Height.KEY)) {
				Height s = (Height) map.get(Height.KEY);
				if(crouched) {
					s.setData(s.getData()*2);
					if(map.containsKey(YPosition.KEY)) {
						YPosition y = (YPosition) map.get(YPosition.KEY);
						y.setData(y.getData() - s.getData() / 2);
					}
					crouched = false;
				}
			}
			
		});
		
		this.addCode(down, (Serializable & Consumer<Map<String,Component>>) (map) -> {
			if(map.containsKey(Height.KEY)) {
				Height s = (Height) map.get(Height.KEY);
				if(!crouched) {
					s.setData(s.getData()/2);
					if(map.containsKey(YPosition.KEY)) {
						YPosition y = (YPosition) map.get(YPosition.KEY);
						y.setData(y.getData() + s.getData());
					}
					crouched = true;
				}
			}
		});
		
	}
	
}
