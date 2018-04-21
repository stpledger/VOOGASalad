
package engine.test;


import engine.systems.InputHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.Collection;
import java.util.HashSet;

import java.io.FileNotFoundException;

public class TestPlayer {

	TestGameState tgs;
	
	public TestPlayer() {
		System.out.println("start TestPlayer");
		try {
			tgs = new TestGameState();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Scene getScene() {
		Group root = new Group();
		Scene s = new Scene(root, 600, 600);
		InputHandler IH = tgs.getIH();
		s.setOnKeyPressed( (event) -> {
			IH.addCode(event.getCode());
		});
		Renderer r = new Renderer(root, tgs.getEntities());
		//tgs.run(r);
		return s;
	}

}
