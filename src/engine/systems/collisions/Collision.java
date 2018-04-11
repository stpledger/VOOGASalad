package engine.systems.collisions;

import java.util.*;

import engine.components.Acceleration;
import engine.components.Component;
import engine.components.Dimension;
import engine.components.Position;
import engine.components.Velocity;
import engine.setup.EntityManager;
import engine.systems.DefaultSystem;
import engine.systems.ISystem;

public class Collision extends DefaultSystem{
	private Map<Integer, Map<String,Component>> handledComponents;
	private Map<Integer, Velocity> colliders;
	private CollisionHandler handler;
	
	public Collision() {
		handledComponents = EntityManager.getEntities();
		colliders = new HashMap<>();
		handler = new CollisionHandler();
	}

	public void execute(double time) {
		colliders.forEach((key1, vel) -> {
			handledComponents.forEach((key2, map) -> {				// Hooooorrible code, refactor
				if(key1 != key2) {
					Dimension d1 = (Dimension) handledComponents.get(key1).get(Dimension.KEY);
					Dimension d2 = (Dimension) handledComponents.get(key2).get(Dimension.KEY);
					Position p1 = (Position) handledComponents.get(key1).get(Position.KEY);
					Position p2 = (Position) handledComponents.get(key2).get(Position.KEY);
					
					double topOverlap = p1.getYPos() + d1.getHeight() - p2.getYPos();
					double leftOverlap = p1.getXPos() + d1.getWidth() - p2.getXPos();
					double botOverlap = p2.getYPos() + d2.getHeight() - p1.getYPos();
					double rightOverlap = p2.getXPos() + d2.getWidth() - p1.getXPos();
					
					boolean top = (topOverlap > 0) && (p1.getYPos() < p2.getYPos());
					boolean left = (leftOverlap > 0) && (p1.getXPos() < p2.getXPos());
					boolean bot = (botOverlap > 0) && (p1.getYPos() > p2.getYPos());
					boolean right = (rightOverlap > 0) && (p1.getXPos() > p2.getXPos());
					
					if(top || bot || left || right) {
						handler.handle(handledComponents, key1, key2);// Signal collision
					}
						
					List<Double> lengths = new ArrayList<>();
					lengths.add(topOverlap);
					lengths.add(botOverlap);
					lengths.add(leftOverlap);
					lengths.add(rightOverlap);
					
					Collections.sort(lengths);
					
					for(int i = 0; i < lengths.size(); i++) {
						if(lengths.get(i) > 0) {
							if(lengths.get(i) == topOverlap && top) {
								p1.setYPos(p2.getYPos() - d1.getHeight()); 		// Change velocity
							} else if(lengths.get(i) == botOverlap && bot) {
								p1.setYPos(p2.getYPos() + d2.getHeight());
							} else if(lengths.get(i) == leftOverlap && left) {
								p1.setXPos(p2.getXPos() - d1.getWidth());
							} else if(right) {
								p1.setXPos(p2.getXPos() + d2.getWidth());
							}
						}
					}
					
				}
			});
		});
	}

	public void removeComponent(int pid) {
		if(handledComponents.containsKey(pid)) {
			handledComponents.remove(pid);
		}
		if(colliders.containsKey(pid)) {
			colliders.remove(pid);
		}
	}

	public void addComponent(int pid, String componentName) {
		if(!componentName.equals(Velocity.KEY)) {
			return;
		}
		
		if(colliders.containsKey(pid)) {
			System.out.println("Collision System tries adding duplicate " + componentName + " component for entity " + pid + " !");
		}
		

		Velocity velocity = (Velocity)EntityManager.getComponent(pid, componentName);
		colliders.put(pid, velocity);
	}

	public void removeComponent(int pid, String componentName) {
		if(!componentName.equals(Velocity.KEY)) {
			return;
		}
		
		if(!colliders.containsKey(pid)) {
			System.out.println("Collision System tries remove " + componentName + " component from non-existing entity " + pid + " !");
		}
		

		colliders.remove(pid);
	}

	@Override
	public void setActives(Set<Integer> actives) {
		//put in active listeners
	}

	
	public void addComponent(int pid, Map<String, Component> components) {
		if (components.containsKey(Position.KEY) && components.containsKey(Dimension.KEY)) {
			Map<String, Component> newComponents = new HashMap<>();
			newComponents.put(Dimension.KEY,components.get(Dimension.KEY));
			newComponents.put(Position.KEY,components.get(Position.KEY));
			handledComponents.put(pid, newComponents);
			if(components.containsKey(Velocity.KEY)) {
				colliders.put(pid, (Velocity) components.get(Velocity.KEY));
			}
		}
	}

	@Override
	public void update(Map<Integer, Map<String, Component>> handledComponents) {
		this.handledComponents = handledComponents;
	}
	
}
