package engine.systems;

import engine.components.Component;

public interface System {

	public void execute(double elapsedTime);
	
	public void addComponent(int pid, Component c);
	
	public void removeComponent(int pid);
	
}
