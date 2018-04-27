package engine.systems.collisions;

import java.util.*;

import engine.components.Component;
import engine.components.groups.Dimension;

import engine.components.groups.Position;
import engine.components.groups.Velocity;
import engine.setup.EntityManager;
import engine.systems.DefaultSystem;

public class Collision extends DefaultSystem{
	private Map<Integer, Map<String,Component>> handledComponents = new HashMap<>();
	private Map<Integer, Velocity> colliders;
	private CollisionHandler handler;
	private EntityManager em;

	public Collision(EntityManager em) {
		colliders = new HashMap<>();
		handler = new CollisionHandler(em);
		this.em = em;
	}

	@Override
	public Map<Integer, Map<String, Component>> getHandledComponent(){
		return handledComponents;
	}

	public void execute(double time) {

		colliders.forEach((key1, vel) -> {
			handledComponents.forEach((key2, map) -> {

				if(key1 != key2) {

					Dimension d1 = (Dimension) handledComponents.get(key1).get(Dimension.KEY);
					Dimension d2 = (Dimension) handledComponents.get(key2).get(Dimension.KEY);
					Position p1 = (Position) handledComponents.get(key1).get(Position.KEY);
					Position p2 = (Position) handledComponents.get(key2).get(Position.KEY);

					double topOverlap = p1.getYPos() + d1.getHeight() - p2.getYPos();
					double botOverlap = p2.getYPos() + d2.getHeight() - p1.getYPos();
					double leftOverlap = p1.getXPos() + d1.getWidth() - p2.getXPos();
					double rightOverlap = p2.getXPos() + d2.getWidth() - p1.getXPos();

					boolean to = topOverlap >= 0 && topOverlap <= d1.getHeight();
					boolean bo = botOverlap >= 0 && botOverlap <= d2.getHeight();
					boolean lo = leftOverlap >= 0 && leftOverlap <= d1.getWidth();
					boolean ro = rightOverlap >= 0 && rightOverlap <= d2.getWidth();

					List<Double> overlaps = new ArrayList<>();
					if(bo && !to && (lo || ro)) {
						overlaps.add(botOverlap);
					}
					if(to && !bo && (lo || ro)) {
						overlaps.add(topOverlap);
					}
					if(lo && !ro && (to || bo)) {
						overlaps.add(leftOverlap);
					}
					if(ro && !lo && (to || bo)) {
						overlaps.add(rightOverlap);
					}

					Collections.sort(overlaps);

					CollisionDirection cd = null;

					if(overlaps.size() > 0) {
						if(overlaps.get(0) == topOverlap) cd = CollisionDirection.Top;
						else if(overlaps.get(0) == botOverlap) cd = CollisionDirection.Bot;
						else if(overlaps.get(0) == rightOverlap) cd = CollisionDirection.Right;
						else if(overlaps.get(0) == leftOverlap) cd = CollisionDirection.Left;
					}

					if(cd != null) {
						handler.handle(handledComponents, key1, key2);

						switch (cd) {
						
						case Top:
							p1.setYPos(p2.getYPos() - d1.getHeight());
							((Velocity)handledComponents.get(p1.getPID()).get(Velocity.KEY)).setYVel(0);
							break;
							
						case Bot:
							p1.setYPos(p2.getYPos() + d2.getHeight());
							((Velocity)handledComponents.get(p1.getPID()).get(Velocity.KEY)).setYVel(0);
							break;
							
						case Left:
							p1.setXPos(p2.getXPos() - d1.getWidth());
							((Velocity)handledComponents.get(p1.getPID()).get(Velocity.KEY)).setXVel(0);
							break;
							
						case Right:
							p1.setXPos(p2.getXPos() + d2.getWidth());
							((Velocity)handledComponents.get(p1.getPID()).get(Velocity.KEY)).setXVel(0);
							break;
						

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
			//System.out.println("Collision System tries adding duplicate " + componentName + " component for entity " + pid + " !");
		}


		Velocity velocity = (Velocity)em.getComponent(pid, componentName);
		colliders.put(pid, velocity);
	}

	public void removeComponent(int pid, String componentName) {
		if(!componentName.equals(Velocity.KEY)) {
			return;
		}

		if(!colliders.containsKey(pid)) {
			//System.out.println("Collision System tries remove " + componentName + " component from non-existing entity " + pid + " !");
		}


		colliders.remove(pid);
	}

	@Override
	public void setActives(Set<Integer> actives) {
		//put in active listeners
	}


	public void addComponent(int pid, Map<String, Component> components) {
		handledComponents.put(pid, components);

		if(components.containsKey(Velocity.KEY)) {
			colliders.put(pid, (Velocity) components.get(Velocity.KEY));
		}

	}

	@Override
	public void update(Map<Integer, Map<String, Component>> handledComponents) {
		this.handledComponents = handledComponents;
	}
	public CollisionHandler getCH() {
		return handler;
	}
}
