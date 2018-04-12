package engine.support;

import java.lang.reflect.Constructor;
import java.util.List;

import engine.components.Acceleration;
import engine.components.Component;
import engine.components.Damage;
import engine.components.Dimension;
import engine.components.Health;
import engine.components.Position;
import engine.components.Sprite;
import engine.components.Velocity;

public class ComponentBuilder {
	public static Component buildComponent(int pid, String component, List<String> inputs) throws IllegalArgumentException {
		if(inputs.size() >= 2 && (component.equals(Acceleration.KEY) 
							|| component.equals(Velocity.KEY) 
							|| component.equals(Position.KEY)
							|| component.equals(Damage.KEY)
							|| component.equals(Dimension.KEY))) {
			double x = 0; 
			double y = 0;
			try {
				x = Double.parseDouble(inputs.get(0));
				y = Double.parseDouble(inputs.get(1));
				Class<?> c = Class.forName("engine.components." + component);
				Constructor<?> con = c.getConstructor(int.class, double.class, double.class);
				return (Component) con.newInstance(pid, x, y);
			} catch (Exception e) {
				throw new IllegalArgumentException("Please provide numbers as inputs");
			}
		} else if(inputs.size() >= 1 && component.equals(Health.KEY)) {
			double h = 0;
			try {
				h = Double.parseDouble(inputs.get(0));
				Class<?> c = Class.forName("engine.components." + component);
				Constructor<?> con = c.getConstructor(int.class, double.class);
				return (Component) con.newInstance(pid, h);
			} catch (Exception e) {
				throw new IllegalArgumentException("Please provide numbers as inputs");
			}
		} else if(inputs.size() >= 1 && component.equals(Sprite.KEY)) {
			try {
				Class<?> c = Class.forName("engine.components." + component);
				Constructor<?> con = c.getConstructor(int.class, String.class);
				return (Component) con.newInstance(pid, inputs.get(0));
			} catch (Exception e) {
				throw new IllegalArgumentException("Please provide numbers as inputs");
			}
		}
		
		return null;
	}
}
