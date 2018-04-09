package engine.test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import engine.Engine;
import engine.InternalEngine;
import engine.components.*;
import engine.setup.GameInitializer;
import engine.systems.InputHandler;
import javafx.scene.input.KeyCode;

public class TestGameState {

	private Map<Integer, Map<String, Component>> entities;
	private Engine eng;
	
	public TestGameState() throws FileNotFoundException {
		entities = new HashMap<>();
		Sprite s = null;
		Sprite s2 = null;
		Sprite s3 = null;
		try {
			s = new Sprite(0, "mario.png");
			s2 = new Sprite(1, "mario.png");
			s3 = new Sprite(2, "mario.png");
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		}
		Player player = new Player(0);
		Position p = new Position(0, 100, 100);
		Dimension d = new Dimension(0, 100, 100);
		Velocity v = new Velocity(0,0,-80);

		Acceleration a = new Acceleration(0, 0, 50);
		KeyInput k = new KeyInput(0, null);
		k.setOnAction(KeyCode.SPACE, (id) -> v.setXVel(v.getXVel()+10));

		Map<String, Component> mario = new HashMap<>();
		mario.put(Position.getKey(), p);
		mario.put(Dimension.getKey(), d);
		mario.put(Sprite.getKey(), s);
		mario.put(Velocity.getKey(), v);
		mario.put(Acceleration.getKey(), a);
		mario.put(Player.getKey(), player);
		mario.put(KeyInput.getKey(), k);

		Position p2 = new Position(1, 200, 100);
		Dimension d2 = new Dimension(1, 100, 100);
		Velocity v2 = new Velocity(1,0,0);
		Acceleration a2 = new Acceleration(1, 0, 0);

		Map<String, Component> mario2 = new HashMap<>();
		mario2.put(Position.getKey(), p2);
		mario2.put(Dimension.getKey(), d2);
		mario2.put(Sprite.getKey(), s2);
		mario2.put(Velocity.getKey(), v2);
		mario2.put(Acceleration.getKey(), a2);

		Position p3 = new Position(2, 300, 100);
		Dimension d3 = new Dimension(2, 100, 100);
		Velocity v3 = new Velocity(2,0,10);
		Acceleration a3 = new Acceleration(1, 0, 0);

		Map<String, Component> mario3 = new HashMap<>();
		mario3.put(Position.getKey(), p3);
		mario3.put(Dimension.getKey(), d3);
		mario3.put(Sprite.getKey(), s3);
		mario3.put(Velocity.getKey(), v3);
		mario3.put(Acceleration.getKey(), a3);
		
		entities.put(0,  mario);

		entities.put(1, mario2);
		entities.put(2, mario3);
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
