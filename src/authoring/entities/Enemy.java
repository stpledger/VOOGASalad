package authoring.entities;

/**
 *
 * @author Hemanth Yakkali(hy115)
 */
public class Enemy extends InteractableEntity {

	private final static String TYPE = "Enemy";
	private String name;

	public Enemy(int ID, String name) {
		super(ID);
		this.name = name;
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
