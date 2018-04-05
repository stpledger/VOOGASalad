package engine.systems;

import engine.components.Component;

public interface ISystem {

	public void execute(double elapsedTime);
	
	public void addComponent(int pid, Component c);
	
	public void removeComponent(int pid);
	
}
