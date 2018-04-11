package frontend.entities;

/**
 * 
 * @author Hemanth Yakkali
 *
 */
public class Block extends Entity{
	
	private final String TYPE =  "Block";
	private final double BLOCK_HEALTH = 50;
	private final double BLOCK_DIMENSION = 25;

	public Block(int ID) {
		super(ID);
		addDefaultComponents();
	}

	@Override
	public void addDefaultComponents() {
		this.setHealth(BLOCK_HEALTH);
		this.setEntityType(TYPE);
		this.setDimension(BLOCK_DIMENSION, BLOCK_DIMENSION);
		// TODO add method to set sprite
	}
	
}
