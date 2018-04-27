package engine.systems.collisions;

import java.util.*;

import engine.components.Component;
import engine.components.Height;
import engine.components.Width;
import engine.components.XPosition;
import engine.components.XVelocity;
import engine.components.YPosition;
import engine.components.YVelocity;
import engine.setup.EntityManager;
import engine.systems.DefaultSystem;

public class Collision extends DefaultSystem{
	private Map<Integer, Map<String,Component>> handledComponents = new HashMap<>();
	private List<Integer> colliders;
	private CollisionHandler handler;

	public Collision(EntityManager em) {
		colliders = new ArrayList<>();
		handler = new CollisionHandler(em);
	}

	@Override
	public Map<Integer, Map<String, Component>> getHandledComponent(){
		return handledComponents;
	}

	public void execute(double time) {

		colliders.forEach((key1) -> {
			handledComponents.forEach((key2, map) -> {

				if(key1 != key2) {

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
		if(handledComponents.containsKey(pid)) {
			handledComponents.remove(pid);
		}
		if(colliders.contains(pid)) {
			colliders.remove(pid);
		}
	}

	/*public void addComponent(int pid, String componentName) {
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
	}*/

	@Override
	public void setActives(Set<Integer> actives) {
		//put in active listeners
	}


	public void addComponent(int pid, Map<String, Component> components) {
		
		if(components.containsKey(XPosition.KEY) && 
				components.containsKey(YPosition.KEY) && 
				components.containsKey(Width.KEY) && 
				components.containsKey(Height.KEY)) {
		
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
	public CollisionHandler getCH() {
		return handler;
	}
}
