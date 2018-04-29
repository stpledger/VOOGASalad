package authoring.entities;

import java.util.Map;

import engine.actions.Actions;
import engine.components.Collidable;
import engine.components.Component;
import engine.components.Lives;
import engine.components.Player;
import engine.systems.collisions.CollisionDirection;

/**
 * A class to represent the player object, and its default components.
 * @author Dylan Powers
 * @author Hemanth Yakkali(hy115)
 */
public class User extends InteractableEntity {

	private final static String TYPE = "Player";
	private String name;
	private final static double INITIAL_HEALTH = 100;
	private final static int INITIAL_LIVES = 10;
	private final static double PLAYER_WIDTH = 25;
	private final static double PLAYER_HEIGHT = 50;
	/**
	 * Construct the object with a given ID
	 * @param ID the ID of the parent object
	 */
	public User(int ID, String name) {
		super(ID);
		addDefaultComponents();
		this.name = name;
	}

	/**
	 * Add the default components to the player object.
	 */
	@SuppressWarnings("unchecked")
	public void addDefaultComponents() {
		this.setHealth(INITIAL_HEALTH);
		this.setEntityType(TYPE);
		this.setDimension(PLAYER_WIDTH, PLAYER_HEIGHT);
		this.add(new Player(this.getID()));
		this.add(new Lives(this.getID(), INITIAL_LIVES));
		
		Collidable cbd = new Collidable(this.getID());
		cbd.addBehavior(CollisionDirection.Top, (e1, e2) -> {
			
			Actions.moveUp(200).accept((Map<String, Component>) e1);
			Actions.damage().accept((Map<String, Component>) e1, (Map<String, Component>) e2);
			
		});
		
	}

	@Override
	public String type() {
		return TYPE;
	}
	
	@Override
	public String name() {
		return this.name;
	}
}