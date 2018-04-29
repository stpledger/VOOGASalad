package engine.systems.collisions;
/**
 * System that checks if the collision happens, and determines where it happens
 * in relation to dimensions
 * author jcf44, sv116
 */
import java.util.*;

import engine.components.Collidable;
import engine.components.Component;
import engine.components.Height;
import engine.components.Width;
import engine.components.XPosition;
import engine.components.XVelocity;
import engine.components.YPosition;
import engine.components.YVelocity;
import engine.setup.SystemManager;
import engine.systems.DefaultSystem;

public class Collision extends DefaultSystem{
	private Map<Integer, Map<String,Component>> handledComponents = new HashMap<>();
	private Set<Integer> colliders;
	private Set<Integer> activeComponents;
	
	public Collision () {
		colliders = new HashSet<>();
	}

	
	public Map<Integer, Map<String, Component>> getHandledComponents(){
		return handledComponents;
	}

	public void execute(double time) {
		activeComponents.forEach((key1) -> {
			handledComponents.forEach((key2, vel) -> {

				if (key1 != key2) {

					Width w1 = (Width) handledComponents.get(key1).get(Width.KEY);
					Height h1 = (Height) handledComponents.get(key1).get(Height.KEY);
					Width w2 = (Width) handledComponents.get(key2).get(Width.KEY);
					Height h2 = (Height) handledComponents.get(key2).get(Height.KEY);
					
					XPosition x1 = (XPosition) handledComponents.get(key1).get(XPosition.KEY);
					YPosition y1 = (YPosition) handledComponents.get(key1).get(YPosition.KEY);
					XPosition x2 = (XPosition) handledComponents.get(key2).get(XPosition.KEY);
					YPosition y2 = (YPosition) handledComponents.get(key2).get(YPosition.KEY);

					double topOverlap = y1.getData() + h1.getData() - y2.getData();
					double botOverlap = y2.getData() + h2.getData() - y1.getData();
					double leftOverlap = x1.getData() + w1.getData() - x2.getData();
					double rightOverlap = x2.getData() + w2.getData() - x1.getData();

					boolean to = topOverlap >= 0 && topOverlap <= h1.getData();
					boolean bo = botOverlap >= 0 && botOverlap <= h2.getData();
					boolean lo = leftOverlap >= 0 && leftOverlap <= w1.getData();
					boolean ro = rightOverlap >= 0 && rightOverlap <= w2.getData();

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

					CollisionDirection cd = null;

					if (overlaps.size() > 0) {
						if (overlaps.get(0) == topOverlap) cd = CollisionDirection.Top;
						else if (overlaps.get(0) == botOverlap) cd = CollisionDirection.Bot;
						else if (overlaps.get(0) == rightOverlap) cd = CollisionDirection.Right;
						else if (overlaps.get(0) == leftOverlap) cd = CollisionDirection.Left;
					}

					if (cd != null) {


						if(handledComponents.get(key1).containsKey(Collidable.KEY)) {
							Collidable cdb = (Collidable) handledComponents.get(key1).get(Collidable.KEY);
							cdb.action(cd, handledComponents.get(key1), handledComponents.get(key2));
						}
						
						if(handledComponents.get(key2).containsKey(Collidable.KEY)) {
							Collidable cdb = (Collidable) handledComponents.get(key2).get(Collidable.KEY);
							cdb.setCondition(() -> {
								return handledComponents.get(key1);
							}); 
							CollisionDirection cd2 = null;
							if(cd == CollisionDirection.Top) cd2 = CollisionDirection.Bot;
							else if(cd == CollisionDirection.Bot) cd2 = CollisionDirection.Top;
							else if(cd == CollisionDirection.Left) cd2 = CollisionDirection.Right;
							else cd2 = CollisionDirection.Left;
							cdb.action(cd2, handledComponents.get(key2), handledComponents.get(key1));
						}

						switch (cd) {

						case Top:
							y1.setData(y2.getData() - h1.getData());
							((YVelocity) handledComponents.get(key1).get(YVelocity.KEY)).setData(0);
							break;
							
						case Bot:
							y1.setData(y2.getData() + h2.getData());
							((YVelocity) handledComponents.get(key1).get(YVelocity.KEY)).setData(0);
							break;
							
						case Left:
							x1.setData(x2.getData() - w1.getData());
							((XVelocity) handledComponents.get(key1).get(XVelocity.KEY)).setData(0);
							break;
							
						case Right:
							x1.setData(x2.getData() + w2.getData());
							((XVelocity) handledComponents.get(key1).get(XVelocity.KEY)).setData(0);
							break;

						}
					}
				}
			});
		});
	}

	public void removeComponent(int pid) {
		if (handledComponents.containsKey(pid)) {
			handledComponents.remove(pid);
		}
		if(colliders.contains(pid)) {
			colliders.remove(pid);
		}
	}


	@Override
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

	@Override
	public void update(Map<Integer, Map<String, Component>> handledComponents) {
		this.handledComponents = handledComponents;
	}

}
