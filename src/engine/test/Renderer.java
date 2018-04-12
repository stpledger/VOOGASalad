package engine.test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import engine.components.Component;
import engine.components.Dimension;
import engine.components.Position;
import engine.components.Sprite;
import javafx.scene.Group;

public class Renderer implements Runnable {

	Group root;
	List<Integer> rooted;
	Map<Integer, Map<String, Component>> entities;
	
	public Renderer(Group r, Map<Integer, Map<String, Component>> e) {
		root = r;
		entities = e;
		rooted = new ArrayList<>();
	}
	
	@Override
	public void run() {
		entities.forEach((key, map) -> {
			//System.out.println(map.toString());
			if(map.containsKey(Sprite.KEY) && map.containsKey(Position.getKey()) && map.containsKey(Dimension.getKey())) {
				Sprite s = (Sprite) map.get(Sprite.KEY);
				Position p = (Position) map.get(Position.KEY);
				Dimension d = (Dimension) map.get(Dimension.KEY);

				System.out.println("looking at entities");
				
				if(!rooted.contains(s.getParentID())) {
					rooted.add(s.getParentID());
					root.getChildren().add(s.getImage());
				}
				
				s.getImage().setFitWidth(d.getWidth());
				s.getImage().setFitHeight(d.getHeight());
				s.getImage().setX(p.getXPos());
				s.getImage().setY(p.getYPos());
			} else if(map.containsKey(Sprite.KEY)) {
				Sprite s = (Sprite) map.get(Sprite.KEY);
				
				if(rooted.contains(s.getParentID())) {
					rooted.remove(s.getParentID());
					root.getChildren().remove(s.getImage());
				}
			}
		});
	}
	
}
