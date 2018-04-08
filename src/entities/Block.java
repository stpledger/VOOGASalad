package entities;

import java.io.FileNotFoundException;

import engine.components.*;

public class Block extends Entity{
	
	private final String TYPE =  "Block";

	public Block(int ID) {
		super(ID);
		addDefaultComponents();
	}

	@Override
	public void addDefaultComponents() {
		this.add(new Velocity(this.getID(),0,0));
		this.add(new Damage(this.getID(),0,0));
		this.add(new EntityType(this.getID(),TYPE));
		this.add(new Acceleration(this.getID(),0,0));
	}
	
}
