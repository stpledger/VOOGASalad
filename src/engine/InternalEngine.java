package engine;

import java.util.ArrayList;
import java.util.List;

import engine.systems.ISystem;

public class InternalEngine implements Engine {

	private List<ISystem> systems;
	
	public InternalEngine() {
		systems = new ArrayList<>();
		
		
	}
	
	public void start() {
		
	}

	public void stop() {
	}
	
	public void pause() {
	}
	
	
	
}
