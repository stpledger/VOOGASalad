package engine.systems;

import java.util.HashMap;
import java.util.Map;

import engine.components.Component;
import engine.components.Dimension;
import engine.components.Position;

public class Collision implements System {

	private Map<Integer, Position> positions;
	private Map<Integer, Dimension> dimensions;
	
	public Collision() {
		positions = new HashMap<>();
		dimensions = new HashMap<>();
	}

	public void execute(double elapsedTime) {
		
	}

	public void addComponent(int pid, Component c) {
		if(c instanceof Position) {
			positions.put(pid, (Position) c);
		}
		if(c instanceof Dimension) {
			dimensions.put(pid, (Dimension) c);
		}
	}

	public void removeComponent(int pid) {
		if(positions.containsKey(pid)) {
			positions.remove(pid);
		}
		if(dimensions.containsKey(pid)) {
			dimensions.remove(pid);
		}
	}
	
	
	
}
