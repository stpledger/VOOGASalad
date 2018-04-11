package engine.systems;

import java.util.Map;

import engine.components.Component;

public abstract class DefaultSystem implements ISystem{
	public abstract void update(Map<Integer, Map<String,Component>> handledComponents);
}
