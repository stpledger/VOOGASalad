package engine.components;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import engine.systems.collisions.CollisionDirection;

public class CollisionAction implements Component {

	public static final String KEY = "CollisionAction";

	private Map<CollisionDirection, Consumer<Void>> directions;
	
	private int pid;
	
	public CollisionAction(int pid) {
		this.pid = pid;

		directions = new HashMap<>();
	}

	public void addAction(CollisionDirection cd, Consumer r) {
		directions.put(cd,  r);
	}
	
	public boolean containsAction (CollisionDirection cd) {
		return directions.containsKey(cd);
	}

	public void action(CollisionDirection cd) {
		directions.get(cd).accept(null);
	}

	public String getKey() {
		return KEY;
	}
	
	@Override
	public int getPID() {		
		return pid;
	}

}
