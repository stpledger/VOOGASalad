package authoring.entities;

import java.io.FileNotFoundException;

/**
 * 
 * @author Hemanth Yakkali
 *
 */
public class Block extends Entity{

	private final static String TYPE =  "Block";
	private final static double BLOCK_HEALTH = 50;
	private final static double BLOCK_DIMENSION = 25;

	public Block(int ID) {
		super(ID);
	}

	@Override
	public void addDefaultComponents() {
		this.setHealth(BLOCK_HEALTH);
		this.setEntityType(TYPE);
		this.setDimension(BLOCK_DIMENSION, BLOCK_DIMENSION);
		try {
			this.setSprite("data/images/block.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String type() {
		return TYPE;
	}

}
