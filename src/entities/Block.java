package entities;

import engine.components.EntityType;
import engine.components.Health;

/**
 * 
 * @author Hemanth Yakkali
 *
 */
public class Block extends Entity{
	
	private final String TYPE =  "Block";
	
	private final int BLOCK_HEALTH = 50;

	public Block(int ID) {
		super(ID);
		addDefaultComponents();
	}

	@Override
	public void addDefaultComponents() {
		this.add(new Health(this.getID(),BLOCK_HEALTH));
		this.add(new EntityType(this.getID(),TYPE));
	}
	
}
