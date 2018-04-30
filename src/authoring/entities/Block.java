package authoring.entities;

/**
 * Block class that acts as a preset. Makes it easier to users to create an enemy without needing 
 * to manually add components.
 * @author Hemanth Yakkali(hy115)
 *
 */
public class Block extends InteractableEntity {

	private final static String TYPE =  "Block";
	private String name;

	public Block(int ID, String name) {
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
