package engine.test;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import data.DataGameState;
import data.DataWrite;
import engine.Engine;
import engine.InternalEngine;
import engine.components.*;
import engine.setup.GameInitializer;
import engine.systems.InputHandler;
import frontend.components.Level;
import javafx.scene.input.KeyCode;

public class TestGameState {

	private Map<Integer, Map<String, Component>> entities;
	private Engine eng;
	private InputHandler ih;

	public TestGameState() throws FileNotFoundException {
		System.out.println("TestGameState");
		entities = new HashMap<>();
		Sprite s = new Sprite(0,"mario.png");
		//Sprite s2 = new Sprite(1,"engine/components/Waluigi.png");
		Sprite s3 = new Sprite(2,"mario.png");

		EntityType type = new EntityType(0,"player");
		Position p = new Position(0, 100, 100);
		Dimension d = new Dimension(0, 100, 100);
		Velocity v = new Velocity(0, 0, 0);

		Acceleration a = new Acceleration(0, 0, 9.8);
		KeyInput k = new KeyInput(0, KeyCode.RIGHT, (Runnable & Serializable) () -> {
			v.setXVel(v.getXVel()+10);
		});
		k.addCode(KeyCode.UP, (Runnable & Serializable)() ->
		{ 
			v.setYVel(v.getYVel()-10);
		});
		k.addCode(KeyCode.DOWN,(Runnable & Serializable) () ->
		{ 
			v.setYVel(v.getYVel()+10);
		});
		k.addCode(KeyCode.LEFT,(Runnable & Serializable) () ->
		{ 
			v.setXVel(v.getXVel()-10);
		});
		Health h = new Health(0,10);
		DamageLauncher launcher = new DamageLauncher(0,2,2);
        Player play = new Player(0);
		Map<String, Component> mario = new HashMap<>();
		mario.put(Position.getKey(), p);
		mario.put(Dimension.getKey(), d);
		mario.put(Sprite.KEY, s);
		mario.put(Velocity.getKey(), v);
		mario.put(Acceleration.getKey(), a);
		mario.put(type.getKey(), type);
		mario.put(KeyInput.getKey(), k);
		mario.put(Health.getKey(), h);
		mario.put(DamageLauncher.getKey(), launcher);
        mario.put(Player.getKey(), play);

		EntityType type3 = new EntityType(2,"enermy");
		Position p3 = new Position(2, 300, 100);
		Dimension d3 = new Dimension(2, 100, 100);
		Velocity v3 = new Velocity(2, 0, 0);
		Acceleration a3 = new Acceleration(2, 0, 0);
		Health h3 = new Health(2,10);
		DamageLauncher launcher3 = new DamageLauncher(0,2,2);

		Map<String, Component> mario3 = new HashMap<>();
		mario3.put(Position.getKey(), p3);
		mario3.put(Dimension.getKey(), d3);
		mario3.put(Sprite.KEY, s3);
		mario3.put(Velocity.getKey(), v3);
		mario3.put(Acceleration.getKey(), a3);
		mario3.put(type3.getKey(), type);
		mario3.put(Health.getKey(), h3);
		mario3.put(DamageLauncher.getKey(), launcher3);

		entities.put(0, mario);
		entities.put(2, mario3);
		GameInitializer gi = new GameInitializer(entities);
		ih = gi.getIH();
		eng = new InternalEngine(gi.getSystems());

		Map<Level, Map<Integer,Map<String,Component>>> state = new HashMap<>();
		Level l = new Level(1);
		state.put(l,entities);
		DataGameState dState = new DataGameState(state, "DemoDemo");
		try {
			DataWrite.saveFile(dState);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public Map<Integer, Map<String, Component>> getEntities() {
		return entities;
	}


	public void run(Renderer r) {
		FixedSteps fs = new FixedSteps((time) -> eng.update(time), r, (fps) -> System.out.println("FPS: " + fps));
		fs.start();
	}

	public InputHandler getIH() { return ih; }

}
