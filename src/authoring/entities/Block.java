package authoring.entities;

import java.io.FileNotFoundException;

/**
 * 
 * @author Hemanth Yakkali(hy115)
 *
 */
public class Block extends Entity {

	private final static String TYPE =  "Block";
	private String name;
	private final static double BLOCK_HEALTH = 50;
	private final static double BLOCK_DIMENSION = 25;

	public Block(int ID, String name) {
		super(ID);
		this.name = name;
	}

	@Override
	public void addDefaultComponents() {
		this.setHealth(BLOCK_HEALTH);
		this.setEntityType(TYPE);
		this.setDimension(BLOCK_DIMENSION, BLOCK_DIMENSION);
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
