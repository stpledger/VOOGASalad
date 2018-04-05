package engine;

import java.util.ArrayList;
import java.util.List;

import engine.systems.ISystem;

public class InternalEngine implements Engine {

	private List<ISystem> systems;
	
	public InternalEngine() {
		systems = new ArrayList<>();
		
		
	}

	public void update(double time) {
		systems.forEach(sys -> sys.execute(time));
	}
	
	
	
}
