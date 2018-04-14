package engine.components;

import java.util.HashMap;
import java.util.Map;

import engine.systems.collisions.CollisionDirection;
import javafx.scene.input.KeyCode;

public class CollisionAction extends Component {

	public static String KEY = "CollisionAction";
	private Map<CollisionDirection, Runnable> directions;
	
	public CollisionAction(int pid) {
		super(pid, KEY);
		directions = new HashMap<>();
	}

	public void addAction(CollisionDirection cd, Runnable r) {
		directions.put(cd,  r);
	}
	
	public boolean containsAction (CollisionDirection cd) {
		return directions.containsKey(cd);
	}

	public void action(CollisionDirection cd) {
		directions.get(cd).run();
	}

	public static String getKey() { return KEY; }
	
	@Override
	public Map<String, String> getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

}
