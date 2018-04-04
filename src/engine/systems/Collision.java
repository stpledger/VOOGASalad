package engine.systems;

import java.util.HashMap;
import java.util.Map;

import engine.components.Component;
import engine.components.Position;

public class Collision implements System {

	private Map<Integer, Position> positions;
	
	public Collision() {
		positions = new HashMap<>();
	}

	public void execute(double elapsedTime) {
		
	}

	public void addComponent(int pid, Component c) {
		if(c instanceof Position) {
			positions.put(pid, (Position) c);
		}
	}

	public void removeComponent(int pid) {
		if(positions.containsKey(pid)) {
			positions.remove(pid);
		}
	}
	
	
	
}
