
//package engine.test;
//
//
//import engine.systems.InputHandler;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//
//import java.io.FileNotFoundException;
//
//public class TestPlayer {
//
//	TestGameState tgs;
//
//	public TestPlayer() {
//		try {
//			tgs = new TestGameState();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public Scene getScene() {
//		Group root = new Group();
//		Scene s = new Scene(root, 600, 600);
//		Renderer r = new Renderer(root, tgs.getEntities());
//		tgs.run(r);
//		return s;
//	}
//
//}
/**package engine.test;


import javafx.scene.Group;
import javafx.scene.Scene;

import java.io.FileNotFoundException;

public class TestPlayer {

	TestGameState tgs;
	
	public TestPlayer() {
		try {
			tgs = new TestGameState();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Scene getScene() {
		Group root = new Group();
		Scene s = new Scene(root, 600, 600);
		Renderer r = new Renderer(root, tgs.getEntities());
		tgs.run(r);
		return s;
	}
	
}**/