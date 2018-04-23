package engine.components;

import java.util.HashMap;
import java.util.Map;

import engine.systems.collisions.CollisionDirection;

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
	
	@Override
	public Map<String, String> getParameters() {
		Map<String,String> res = new HashMap<>();
		for(Map.Entry<CollisionDirection, Runnable> entry : directions.entrySet()) {
			res.put("Direction", entry.getKey().name());
		}
		
		return res;
	}

}
