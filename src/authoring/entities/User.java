package authoring.entities;

import engine.components.Animated;
import engine.components.Jumps;
import engine.components.Lives;
import engine.components.Player;
import javafx.scene.input.KeyCode;
import engine.components.Score;
import engine.components.presets.PlayerCollision;
import engine.components.presets.PlayerMovement;

/**
 * A class to represent the player object, and its default components.
 * @author Dylan Powers
 * @author Hemanth Yakkali(hy115)
 */
public class User extends InteractableEntity {

	private final static String TYPE = "User";
	private String name;
	private final static double INITIAL_HEALTH = 100;
	private final static int INITIAL_LIVES = 10;
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
	public void addDefaultComponents() {
		this.setHealth(INITIAL_HEALTH);
		this.setEntityType(TYPE);
		this.setDimension(Entity.ENTITY_WIDTH, Entity.ENTITY_HEIGHT);
		this.add(new Player(this.getID()));
		this.add(new Lives(this.getID(), INITIAL_LIVES));

		/*Collidable cbd = new Collidable(this.getID());
		cbd.addBehavior(CollisionDirection.Top, (e1, e2) -> {

			Actions.moveUp(200).accept((Map<String, Component>) e1);
			//Actions.damage().accept((Map<String, Component>) e1, (Map<String, Component>) e2);

		});*/

		this.add(new PlayerCollision(this.getID()));

		this.add(new PlayerMovement(this.getID(), KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP, KeyCode.DOWN));

		this.add(new Score(this.getID(), 0));

		this.add(new Jumps(this.getID(), 3));
		
		this.add(new Animated(this.getID(), "animations/blob/animation.properties"));
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