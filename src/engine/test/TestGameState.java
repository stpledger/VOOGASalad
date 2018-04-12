package engine.test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

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
		entities = new HashMap<>();
		Sprite s = new Sprite(0,"engine/components/Waluigi.png");
		Sprite s2 = new Sprite(1,"engine/components/Waluigi.png");
		Sprite s3 = new Sprite(2,"engine/components/Waluigi.png");

		EntityType type = new EntityType(0,"player");
		Position p = new Position(0, 100, 100);
		Dimension d = new Dimension(0, 100, 100);
		Velocity v = new Velocity(0, 0, -80);

		Acceleration a = new Acceleration(0, 0, 50);
		KeyInput k = new KeyInput(0, KeyCode.SPACE, e -> {
		});
		Health h = new Health(0,10);
		DamageLauncher launcher = new DamageLauncher(0,2,2);

		Map<String, Component> mario = new HashMap<>();
		mario.put(Position.getKey(), p);
		mario.put(Dimension.getKey(), d);
		mario.put(Sprite.getKey(), s);
		mario.put(Velocity.getKey(), v);
		mario.put(Acceleration.getKey(), a);
		mario.put(type.getKey(), type);
		mario.put(KeyInput.getKey(), k);
		mario.put(Health.getKey(), h);
		mario.put(DamageLauncher.getKey(), launcher);

		EntityType type2 = new EntityType(1,"enermy");
		Position p2 = new Position(1, 200, 100);
		Dimension d2 = new Dimension(1, 100, 100);
		Velocity v2 = new Velocity(1, 0, 0);
		Acceleration a2 = new Acceleration(1, 0, 0);
		Health h2 = new Health(0,10);
		DamageLauncher launcher2 = new DamageLauncher(0,2,2);

		Map<String, Component> mario2 = new HashMap<>();
		mario2.put(Position.getKey(), p2);
		mario2.put(Dimension.getKey(), d2);
		mario2.put(Sprite.getKey(), s2);
		mario2.put(Velocity.getKey(), v2);
		mario2.put(type2.getKey(), type);
		mario2.put(Acceleration.getKey(), a2);
		mario2.put(Health.getKey(), h2);
		mario2.put(DamageLauncher.getKey(), launcher2);

		EntityType type3 = new EntityType(2,"enermy");
		Position p3 = new Position(2, 300, 100);
		Dimension d3 = new Dimension(2, 100, 100);
		Velocity v3 = new Velocity(2, 0, 10);
		Acceleration a3 = new Acceleration(1, 0, 0);
		Health h3 = new Health(0,10);
		DamageLauncher launcher3 = new DamageLauncher(0,2,2);

		Map<String, Component> mario3 = new HashMap<>();
		mario3.put(Position.getKey(), p3);
		mario3.put(Dimension.getKey(), d3);
		mario3.put(Sprite.getKey(), s3);
		mario3.put(Velocity.getKey(), v3);
		mario3.put(Acceleration.getKey(), a3);
		mario3.put(type3.getKey(), type);
		mario3.put(Health.getKey(), h3);
		mario3.put(DamageLauncher.getKey(), launcher3);

		entities.put(0, mario);
		entities.put(1, mario2);
		entities.put(2, mario3);

		GameInitializer gi = new GameInitializer(entities);
		ih = gi.getIH();
		eng = new InternalEngine(gi.getSystems());


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
