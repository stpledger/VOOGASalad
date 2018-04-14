package engine.systems.collisions;

import java.util.*;

import engine.components.Component;
import engine.components.Dimension;
import engine.components.EntityType;
import engine.components.KeyInput;
import engine.components.Player;
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
		handler = new CollisionHandler();
		this.em = em;
	}

	@Override
	public Map<Integer, Map<String, Component>> getHandledComponent(){
		return handledComponents;
	}

	public void execute(double time) {

		colliders.forEach((key1, vel) -> {
			handledComponents.forEach((key2, map) -> {				// Hooooorrible code, refactor

				if(key1 != key2) {

					Dimension d1 = (Dimension) handledComponents.get(key1).get(Dimension.KEY);
					Dimension d2 = (Dimension) handledComponents.get(key2).get(Dimension.KEY);
					Position p1 = (Position) handledComponents.get(key1).get(Position.KEY);
					Position p2 = (Position) handledComponents.get(key2).get(Position.KEY);

					//System.out.println("p1 "+p1.getXPos());
					//System.out.println(p2.getXPos()+"  "+d2.getWidth());

					double topOverlap = p1.getYPos() + d1.getHeight() - p2.getYPos();
					double leftOverlap = p1.getXPos() + d1.getWidth() - p2.getXPos();
					double botOverlap = p2.getYPos() + d2.getHeight() - p1.getYPos();
					double rightOverlap = p2.getXPos() + d2.getWidth() - p1.getXPos();


					boolean top = (topOverlap > 0) && (p1.getYPos() < p2.getYPos());
					boolean left = (leftOverlap > 0) && (p1.getXPos() < p2.getXPos());
					boolean bot = (botOverlap > 0) && (p1.getYPos() > p2.getYPos());
					boolean right = (rightOverlap > 0) && (p1.getXPos() > p2.getXPos());// && (p1.getYPos()==p2.getYPos() | (p1.getYPos()-p2.getYPos()<d1.getHeight()/2+d2.getHeight()/2));
					//System.out.println("Printing"+(p1.getXPos() > p2.getXPos()) +"  "+ (p1.getYPos()==p2.getYPos() | (p1.getYPos()-p2.getYPos()<d1.getHeight()/2+d2.getHeight()/2)));
					boolean righttop = (top==true) &&(right==true);
					boolean rightbot = (bot==true) && (right==true);
					boolean lefttop = (top==true) &&(left==true);
					boolean leftbot = (bot==true) && (left==true);
					boolean rightonly = (right==true) && (p1.getYPos()==p2.getYPos());
					boolean leftonly= (left==true) && (p1.getYPos()==p2.getYPos());
					boolean toponly =(top==true) && (p1.getXPos()==p2.getXPos());
					boolean botonly =(bot==true) && (p1.getXPos()==p2.getXPos());

					if(righttop || rightbot || lefttop ||  leftbot || rightonly || leftonly || toponly || botonly) {
						handler.handle(handledComponents, key1, key2);// Signal collision
						//System.out.println("collision");
					}

					List<Double> lengths = new ArrayList<>();
					lengths.add(topOverlap);
					lengths.add(botOverlap);
					lengths.add(leftOverlap);
					lengths.add(rightOverlap);

					Collections.sort(lengths);

					for(int i = 0; i < lengths.size(); i++) {
						if(lengths.get(i) > 0) {
							if(toponly) {
								p1.setYPos(p2.getYPos() - d1.getHeight()/2-d2.getHeight()/2);
								((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setYVel(0);
								//((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setXVel(0);
							} else if(botonly) {
								p1.setYPos(p2.getYPos() + d2.getHeight()/2+d1.getHeight()/2);
								((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setYVel(0);
								//((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setXVel(0);
							}
							else if(leftbot) {
								if(botOverlap>leftOverlap) {
									p1.setXPos(p2.getXPos() - d2.getWidth()/2-d1.getWidth()/2);
									((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setYVel(0);
									((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setXVel(0);
								}
								else {
									p1.setYPos(p2.getYPos() + d2.getHeight()/2+d1.getHeight()/2);
									((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setYVel(0);
									((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setXVel(0);
								}

							}
							else if (rightbot) {
								if(botOverlap>rightOverlap) {
									p1.setXPos(p2.getXPos() + d2.getWidth()/2+d1.getWidth()/2);
									((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setYVel(0);
									((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setXVel(0);
								}
								else {
									p1.setYPos(p2.getYPos() + d2.getHeight()/2+d1.getHeight()/2);
									((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setYVel(0);
									((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setXVel(0);
								}

							}
							else if (lefttop) {
								if(topOverlap<leftOverlap) {
									p1.setYPos(p2.getYPos() - d1.getHeight()/2-d2.getHeight()/2);
									((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setYVel(0);
									((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setXVel(0);
								}
								else {
									p1.setXPos(p2.getXPos() - d1.getWidth()/2-d2.getWidth()/2);
									((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setYVel(0);
									((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setXVel(0);
								}
							}
							else if (righttop) {
								if(topOverlap<rightOverlap) {
									p1.setYPos(p2.getYPos() - d1.getHeight()/2-d2.getHeight()/2);
									((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setYVel(0);
									((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setXVel(0);
								}
								else {
									p1.setXPos(p2.getXPos() + d1.getWidth()/2+d2.getWidth()/2);
									((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setYVel(0);
									((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setXVel(0);
								}
							}
							else if(leftonly) {
								p1.setXPos(p2.getXPos() - d1.getWidth()/2-d2.getWidth()/2);
								//((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setYVel(0);
								((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setXVel(0);
							}
							else if(rightonly) {
								p1.setXPos(p2.getXPos() + d2.getWidth()/2+d1.getWidth()/2);
								//((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setYVel(0);
								((Velocity)handledComponents.get(p1.getParentID()).get(Velocity.KEY)).setXVel(0);
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
