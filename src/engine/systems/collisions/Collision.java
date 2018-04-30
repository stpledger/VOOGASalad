package engine.systems.collisions;
/**
 * System that checks if the collision happens, and determines where it happens
 * in relation to dimensions
 * author jcf44, sv116
 */
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import engine.components.Collidable;
import engine.components.Component;
import engine.components.Height;
import engine.components.Width;
import engine.components.XPosition;
import engine.components.XVelocity;
import engine.components.YPosition;
import engine.components.YVelocity;
import engine.systems.ISystem;


public class Collision implements ISystem {
	private Map<Integer, Map<String,Component>> handledComponents;
	private Set<Integer> colliders;
	private Set<Integer> activeComponents;
	
	public Collision () {
		colliders = new HashSet<>();
		handledComponents = new HashMap<>();
		activeComponents = new HashSet<>();
	}

	public void execute(double time) {
		activeComponents.forEach(key1 -> {
			handledComponents.forEach((key2, vel) -> {
				if(key1 != key2) {
					checkCollision(key1, key2);
				}
			});
		});
	}

	private void checkCollision(Integer key1, Integer key2) {

		Width w1 = (Width) handledComponents.get(key1).get(Width.KEY);
		Height h1 = (Height) handledComponents.get(key1).get(Height.KEY);
		Width w2 = (Width) handledComponents.get(key2).get(Width.KEY);
		Height h2 = (Height) handledComponents.get(key2).get(Height.KEY);
		
		XPosition x1 = (XPosition) handledComponents.get(key1).get(XPosition.KEY);
		YPosition y1 = (YPosition) handledComponents.get(key1).get(YPosition.KEY);
		XPosition x2 = (XPosition) handledComponents.get(key2).get(XPosition.KEY);
		YPosition y2 = (YPosition) handledComponents.get(key2).get(YPosition.KEY);

		

		CollisionDirection cd = this.getOverlaps(x1.getData(), y1.getData(), w1.getData(), h1.getData(),
												x2.getData(), y2.getData(), w2.getData(), h2.getData());
		
		if (cd != null) {

			this.evaluateCollidable(cd, key1, key2);
			
			switch (cd) {

			case Top:
				y1.setData(y2.getData() - h1.getData());
				break;
				
			case Bot:
				y1.setData(y2.getData() + h2.getData());
				break;
				
			case Left:
				x1.setData(x2.getData() - w1.getData());
				break;
				
			case Right:
				x1.setData(x2.getData() + w2.getData());
				break;

			}
		}
	}


	private void evaluateCollidable(CollisionDirection cd, Integer key1, Integer key2) {
		if(handledComponents.get(key1).containsKey(Collidable.KEY)) {
			Collidable cdb = (Collidable) handledComponents.get(key1).get(Collidable.KEY);
			cdb.action(cd, handledComponents.get(key1), handledComponents.get(key2));
		}
		
		if(handledComponents.get(key2).containsKey(Collidable.KEY)) {
			Collidable cdb = (Collidable) handledComponents.get(key2).get(Collidable.KEY);
			
			CollisionDirection cd2 = null;
			if(cd == CollisionDirection.Top) {
				cd2 = CollisionDirection.Bot;
			}
			else if(cd == CollisionDirection.Bot) {
				cd2 = CollisionDirection.Top;
			}
			else if(cd == CollisionDirection.Left) {
				cd2 = CollisionDirection.Right;
			}
			else {
				cd2 = CollisionDirection.Left;
			}
			cdb.action(cd2, handledComponents.get(key2), handledComponents.get(key1));
		}
		
	}


	private CollisionDirection getOverlaps(double x1, double y1, double w1, double h1,
										double x2, double y2, double w2, double h2) {
		
		double topOverlap = y1 + h1 - y2;
		double botOverlap = y2 + h2 - y1;
		double leftOverlap = x1 + w1 - x2;
		double rightOverlap = x2 + w2 - x1;

		boolean to = topOverlap >= 0 && topOverlap <= h1;
		boolean bo = botOverlap >= 0 && botOverlap <= h2;
		boolean lo = leftOverlap >= 0 && leftOverlap <= w1;
		boolean ro = rightOverlap >= 0 && rightOverlap <= w2;
		
		List<Double> overlaps = new ArrayList<>();
		
		if (bo && !to && (lo || ro)) {
			overlaps.add(botOverlap);
		}
		if (to && !bo && (lo || ro)) {
			overlaps.add(topOverlap);
		}
		if (lo && !ro && (to || bo)) {
			overlaps.add(leftOverlap);
		}
		if (ro && !lo && (to || bo)) {
			overlaps.add(rightOverlap);
		}
		
		Collections.sort(overlaps);

		if (overlaps.size() > 0) {
			if (overlaps.get(0) == topOverlap) {
				return CollisionDirection.Top;
			}
			else if (overlaps.get(0) == botOverlap) {
				return CollisionDirection.Bot;
			}
			else if (overlaps.get(0) == rightOverlap) {
				return CollisionDirection.Right;
			}
			else if (overlaps.get(0) == leftOverlap) {
				return CollisionDirection.Left;
			}
		}
		
		return null;
	}


	public void removeComponent(int pid) {
		if (handledComponents.containsKey(pid)) {
			handledComponents.remove(pid);
		}
		if(colliders.contains(pid)) {
			colliders.remove(pid);
		}
	}


	public void setActives(Set<Integer> actives) {
		Set<Integer> myActives = new HashSet<>(actives);
		myActives.retainAll(handledComponents.keySet());
		myActives.retainAll(colliders);
		activeComponents = myActives;
	}


	public void addComponent(int pid, Map<String, Component> components) {
		
		if(		components.containsKey(XPosition.KEY) &&
				components.containsKey(YPosition.KEY) && 
				components.containsKey(Width.KEY) && 
				components.containsKey(Height.KEY) &&
				components.containsKey(Collidable.KEY)) {
		
			handledComponents.put(pid, components);

			if(components.containsKey(XVelocity.KEY) && components.containsKey(YVelocity.KEY)) {
				colliders.add(pid);
			}

		}

	}


}
