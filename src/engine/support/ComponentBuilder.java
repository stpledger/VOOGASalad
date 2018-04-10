package engine.support;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import engine.components.Acceleration;
import engine.components.Component;
import engine.components.Dimension;
import engine.components.EntityType;
import engine.components.Health;
import engine.components.Position;
import engine.components.Sprite;
import engine.components.Velocity;

public class ComponentBuilder {
	public static Component buildComponent(int pid,String component, List<String> inputs) {
		switch(component) {
			case "Acceleration":
				return new Acceleration(pid, inputs);
			case "Dimension":
				return new Dimension(pid, inputs); 
			case "EntityType":
				return new EntityType(pid, inputs); 	
			case "Health":
				return new Health(pid, inputs); 
			case "Position":
				return new Position(pid, inputs); 	
			case "Sprite":
				try {
					return new Sprite(pid, inputs);
				} catch (FileNotFoundException e) {
					return null;
				} 
			case "Velocity":
				return new Velocity(pid, inputs); 
		}
		return null;
	}
}
