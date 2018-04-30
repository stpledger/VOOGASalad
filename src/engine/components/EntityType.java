package engine.components;

public class EntityType extends SingleStringComponent{

	public static final String KEY = "EntityType";
	
	public EntityType(int pid, String data) {
		super(pid, data);
	}

	public String getKey() {
		return "EntityType";
	}

}
