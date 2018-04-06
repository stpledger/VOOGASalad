package engine.test;


import javafx.scene.Group;
import javafx.scene.Scene;

public class TestPlayer {

	TestGameState tgs;
	
	public TestPlayer() {
		tgs = new TestGameState();
	}
	
	public Scene getScene() {
		Group root = new Group();
		Scene s = new Scene(root, 600, 600);
		Renderer r = new Renderer(root, tgs.getEntities());
		tgs.run(r);
		return s;
	}
	
}
