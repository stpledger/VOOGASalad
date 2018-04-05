package engine.systems;

import engine.components.Component;



public abstract class System {
	
	public abstract void addComponent(int pid, Component c);
	public abstract void removeComponent(int pid);
	
	
 
}
