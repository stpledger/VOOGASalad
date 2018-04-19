package engine.test;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import authoring.gamestate.Level;
import data.DataGameState;
import data.DataWrite;
import engine.Engine;
import engine.InternalEngine;
import engine.components.*;
import engine.setup.GameInitializer;
import engine.systems.InputHandler;
import javafx.scene.input.KeyCode;

public class TestGameState {

	private Map<Integer, Map<String, Component>> entities;
	private Engine eng;
	private InputHandler ih;

	public TestGameState() throws FileNotFoundException {
		System.out.println("TestGameState");
		entities = new HashMap<>();
		Sprite s = new Sprite(0,"Mario.png");
		Sprite s2 = new Sprite(1,"mario.png"); 
		Sprite s3 = new Sprite(2,"mario.png");

		Position p = new Position(0, 100, 100);
		Dimension d = new Dimension(0, 100, 100);
		Velocity v = new Velocity(0, 0, 0);

		Acceleration a = new Acceleration(0, 0, 40);
		KeyInput k = new KeyInput(0);
		k.addCode( KeyCode.RIGHT, (Runnable & Serializable) () -> {
			v.setXVel(v.getXVel()+20);
		});
		k.addCode(KeyCode.UP, (Runnable & Serializable)() ->
		{ 
			v.setYVel(v.getYVel()-20);
		});
		k.addCode(KeyCode.DOWN,(Runnable & Serializable) () ->
		{ 
			v.setYVel(v.getYVel()+20);
		});
		k.addCode(KeyCode.LEFT,(Runnable & Serializable) () ->
		{ 
			v.setXVel(v.getXVel()-20);
		});
		Health h = new Health(0,10);
		DamageLauncher launcher = new DamageLauncher(0,2,2);

		Player play = new Player(0, 3);
        play.setRespawn(p.clone());
		k.addCode(KeyCode.R, (Runnable & Serializable) () ->
		{
			play.respawn(p, v, a);
		});

		Map<String, Component> mario = new HashMap<>();
		mario.put(Position.KEY, p);
		mario.put(Dimension.KEY, d);
		mario.put(Sprite.KEY, s);
		mario.put(Velocity.KEY, v);
		mario.put(Acceleration.KEY, a);
		mario.put(KeyInput.KEY, k);
		mario.put(Health.KEY, h);
		mario.put(DamageLauncher.KEY, launcher);
        mario.put(Player.KEY, play);


		EntityType type3 = new EntityType(2,"enermy");
		Position p3 = new Position(2, 300, 100);
		Dimension d3 = new Dimension(2, 100, 100);
		Velocity v3 = new Velocity(2, 0, 0);
		Acceleration a3 = new Acceleration(2, 0, 0);
		Health h3 = new Health(2,10);
		DamageLauncher launcher3 = new DamageLauncher(0,2,2); 
		Win win3 = new Win(2);

		AI ai = new AI(2);
		ai.setAction( (Consumer & Serializable) (time) -> {
			Double myTime = (Double) time;
			v3.setXVel((p.getXPos() - p3.getXPos()) * myTime * 10);
			v3.setYVel((p.getYPos() - p3.getYPos()) * myTime * 10);
		});

		Map<String, Component> mario3 = new HashMap<>();
		mario3.put(AI.KEY, ai);
		mario3.put(Position.KEY, p3);
		mario3.put(Dimension.KEY, d3);
		mario3.put(Sprite.KEY, s3);
		mario3.put(Velocity.KEY, v3);
		mario3.put(Acceleration.KEY, a3);
		mario3.put(Health.KEY, h3);
		mario3.put(DamageLauncher.KEY, launcher3);
		mario3.put(Win.KEY, win3);

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
