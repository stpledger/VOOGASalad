package engine.components;

import java.io.Serializable;
import java.util.Map;
import java.util.function.BiConsumer;

import engine.actions.Actions;
import engine.systems.collisions.CollisionDirection;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Win extends Collidable implements Component{
	public static final String KEY = "WIN";
	private BooleanProperty winStatus = new SimpleBooleanProperty();
	
	@SuppressWarnings("unchecked")
	public Win(int pid) {
		super(pid);
		winStatus.setValue(false);
		System.out.println(winStatus.getValue());
        this.setOnDirection(CollisionDirection.Top, (Serializable & BiConsumer<Map<String,Component>, Map<String,Component>>) (e1, e2) -> {
			if(e1.containsKey(Player.KEY)) {
				win();
			}		
		});
        this.setOnDirection(CollisionDirection.Bot, (Serializable & BiConsumer<Map<String,Component>, Map<String,Component>>) (e1, e2) -> {
			if(e1.containsKey(Player.KEY)) {
				win();
			}		
		});
        this.setOnDirection(CollisionDirection.Left, (Serializable & BiConsumer<Map<String,Component>, Map<String,Component>>) (e1, e2) -> {
			if(e1.containsKey(Player.KEY)) {
				win();
			}		
		});
        this.setOnDirection(CollisionDirection.Right, (Serializable & BiConsumer<Map<String,Component>, Map<String,Component>>) (e1, e2) -> {
			if(e1.containsKey(Player.KEY)) {
				win();
				
			}		
		});
        
		
	}

	public String getKey() {
		return KEY;
	}

	public void win() {
		winStatus.setValue(true);
		System.out.println(winStatus.getValue());

	}

	public BooleanProperty getWinStatus () {
		return winStatus;
	}

	

}
