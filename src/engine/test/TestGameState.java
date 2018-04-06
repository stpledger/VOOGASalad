package engine.test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import engine.Engine;
import engine.InternalEngine;
import engine.components.Acceleration;
import engine.components.Component;
import engine.components.Dimension;
import engine.components.Position;
import engine.components.Sprite;
import engine.components.Velocity;
import engine.setup.GameInitializer;

public class TestGameState {

	private Map<Integer, Map<String, Component>> entities;
	private Engine eng;
	
	public TestGameState() {
		entities = new HashMap<>();
		Sprite s = null;
		try {
			s = new Sprite(0, "mario.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Position p = new Position(0, 100, 100);
		Dimension d = new Dimension(0, 50, 50);
		Velocity v = new Velocity(0,0,-80);
		Acceleration a = new Acceleration(0, 0, 50);
		Map<String, Component> mario = new HashMap<>();
		mario.put(Position.getKey(), p);
		mario.put(Dimension.getKey(), d);
		mario.put(Sprite.getKey(), s);
		mario.put(Velocity.getKey(), v);
		mario.put(Acceleration.getKey(), a);
		
		
		
		
		
		entities.put(0,  mario);
		GameInitializer gi = new GameInitializer(entities);
		eng = new InternalEngine(gi.getSystems());
	}
	
	
	public Map<Integer, Map<String, Component>> getEntities() {
		return entities;
	}
	
	public void run(Renderer r) {		
		FixedSteps fs = new FixedSteps((time) -> eng.update(time), r, (fps) -> System.out.println("FPS: " + fps));
		fs.start();
	}
	
}
