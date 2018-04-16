package engine.systems.collisions;

import java.util.*;

import engine.components.Component;
import engine.components.Dimension;

import engine.components.Position;
import engine.components.Velocity;
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
					double leftOverlap = p1.getXPos() + d1.getWidth() - p2.getXPos();
					double botOverlap = p2.getYPos() + d2.getHeight() - p1.getYPos();
					double rightOverlap = p2.getXPos() + d2.getWidth() - p1.getXPos();

					boolean top = (topOverlap > 0) && (p1.getYPos() < p2.getYPos());
					boolean left = (leftOverlap > 0) && (p1.getXPos() < p2.getXPos());
					boolean bot = (botOverlap > 0) && (p1.getYPos() > p2.getYPos());
					boolean right = (rightOverlap > 0) && (p1.getXPos() > p2.getXPos());
					
					CollisionDirection cd = null;
					
					if(top) {
						if(right) cd = CollisionDirection.TopRight;
						else if(left) cd = CollisionDirection.TopLeft;
						else if((p1.getXPos()==p2.getXPos())) cd = CollisionDirection.Top;
					} else if(bot) {
						if(right) cd = CollisionDirection.BotRight;
						else if(left) cd = CollisionDirection.BotLeft;
						else if((p1.getXPos()==p2.getXPos())) cd = CollisionDirection.Bot;
					} else if(right && (p1.getYPos()==p2.getYPos())) {
						cd = CollisionDirection.Right;
					} else if((p1.getYPos()==p2.getYPos())){
						cd = CollisionDirection.Left;
					}

					if(cd != null) {
						handler.handle(handledComponents, key1, key2);
						
						switch (cd) {
						
						case Top:
							p1.setYPos(p2.getYPos() - d1.getHeight()/2-d2.getHeight()/2);
							((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setYVel(0);
							break;
							
						case Bot:
							p1.setYPos(p2.getYPos() + d2.getHeight()/2+d1.getHeight()/2);
							((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setYVel(0);
							break;
							
						case Left:
							p1.setXPos(p2.getXPos() - d1.getWidth()/2-d2.getWidth()/2);
							((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setXVel(0);
							break;
							
						case Right:
							p1.setXPos(p2.getXPos() + d2.getWidth()/2+d1.getWidth()/2);
							((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setXVel(0);
							break;
							
						case BotLeft:
							if(botOverlap>leftOverlap) {
								p1.setXPos(p2.getXPos() - d2.getWidth()/2-d1.getWidth()/2);
								((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setXVel(0);
							} else {
								p1.setYPos(p2.getYPos() + d2.getHeight()/2+d1.getHeight()/2);
								((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setYVel(0);
							}
							break;
							
						case BotRight:
							if(botOverlap>rightOverlap) {
								p1.setXPos(p2.getXPos() + d2.getWidth()/2+d1.getWidth()/2);
								((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setXVel(0);
							} else {
								p1.setYPos(p2.getYPos() + d2.getHeight()/2+d1.getHeight()/2);
								((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setYVel(0);
							}
							break;
	
						case TopLeft:
							if(topOverlap<leftOverlap) {
								p1.setYPos(p2.getYPos() - d1.getHeight()/2-d2.getHeight()/2);
								((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setYVel(0);
							} else {
								p1.setXPos(p2.getXPos() - d1.getWidth()/2-d2.getWidth()/2);
								((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setXVel(0);
							}
							break;
							
						case TopRight:
							if(topOverlap<rightOverlap) {
								p1.setYPos(p2.getYPos() - d1.getHeight()/2-d2.getHeight()/2);
								((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setYVel(0);
							} else {
								p1.setXPos(p2.getXPos() + d1.getWidth()/2+d2.getWidth()/2);
								((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setXVel(0);
							}
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
			System.out.println("Collision System tries adding duplicate " + componentName + " component for entity " + pid + " !");
		}
		

		Velocity velocity = (Velocity)em.getComponent(pid, componentName);
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
	handledComponents.put(pid, components);

		if(components.containsKey(Velocity.KEY)) {
			colliders.put(pid, (Velocity) components.get(Velocity.KEY));
		}

	}

	@Override
	public void update(Map<Integer, Map<String, Component>> handledComponents) {
		this.handledComponents = handledComponents;
	}
	
}
