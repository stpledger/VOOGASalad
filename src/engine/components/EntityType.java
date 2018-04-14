package engine.components;

import java.util.HashMap;
import java.util.Map;

/**
 * This component defines type fof entity,like "player","block" etc
 * @author Yameng
 */
public class EntityType extends Component{
	private String type = "Not Defined";

	public static String KEY = "EntityType";
	
	public EntityType(int pid, String type) {
		super(pid, KEY);
		this.type = type;
	}

	public void setType(String newType) {
		type = newType;
	}
	
	public String getType() {
		return type;
	}
	
	public boolean equals(String newType) {
		return type.equals(newType);
	}
	
	public String toString() {
		return type;
	}

	public static String getKey() {
		return KEY;
	}

	@Override
	public Map<String, String> getParameters(){
		Map<String,String> res = new HashMap<String, String>(){{
			put("Entity Type", type);
		}};
		return res;
	}
}
