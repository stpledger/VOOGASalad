package authoring.entities;

import java.io.FileNotFoundException;

/**
 * 
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
	public void addDefaultComponents() {
		this.setEntityType(TYPE);
		this.setDimension(Entity.ENTITY_WIDTH, Entity.ENTITY_HEIGHT);
		try {
			this.setSprite("data/images/block.png");
		} catch (FileNotFoundException e) {
			LOGGER.log(java.util.logging.Level.SEVERE, e.toString(), e);
		}
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
