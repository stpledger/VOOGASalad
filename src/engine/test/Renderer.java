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
			if(map.containsKey(Sprite.getKey()) && map.containsKey(Position.getKey()) && map.containsKey(Dimension.getKey())) {
				Sprite s = (Sprite) map.get(Sprite.getKey());
				Position p = (Position) map.get(Position.getKey());
				Dimension d = (Dimension) map.get(Dimension.getKey());
				
				if(!rooted.contains(s.getParentID())) {
					rooted.add(s.getParentID());
					root.getChildren().add(s.getImage());
				}
				
				s.getImage().setFitWidth(d.getWidth());
				s.getImage().setFitHeight(d.getHeight());
				s.getImage().setX(p.getXPos());
				s.getImage().setY(p.getYPos());
			} else if(map.containsKey(Sprite.getKey())) {
				Sprite s = (Sprite) map.get(Sprite.getKey());
				
				if(rooted.contains(s.getParentID())) {
					rooted.remove(s.getParentID());
					root.getChildren().remove(s.getImage());
				}
			}
		});
	}
	
}
