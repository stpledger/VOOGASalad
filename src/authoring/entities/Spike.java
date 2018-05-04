package authoring.entities;

import engine.components.DamageLifetime;
import engine.components.DamageValue;
import engine.components.presets.SpikeCollision;

public class Spike extends InteractableEntity {

	private final static String TYPE =  "Spike";
	private final int DEFAULT_DAMAGE = 50;
	private final int DEFAULT_LIFETIME = 1;

	public Spike(int ID, String name) {
		super(ID);
		this.setName(name);
		this.setPresetType(TYPE);
		this.add(new SpikeCollision(this.getID()));
		this.add(new DamageValue(this.getID(), DEFAULT_DAMAGE));
		this.add(new DamageLifetime(this.getID(), DEFAULT_LIFETIME));
	}

}
