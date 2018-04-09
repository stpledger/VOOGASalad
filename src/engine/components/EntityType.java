package engine.components;

import java.util.ArrayList;
import java.util.List;

/**
 * This component defines type fof entity,like "player","block" etc
 * @author Yameng
 */
public class EntityType extends Component{
	private String type = "Not Defined";
	
	public EntityType(int pid, List<String> parameters) {
		super(pid);
		this.type = parameters.get(0);
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

	@Override
	public List<String[]> getParameters(){
		List<String[]> parameters = new ArrayList<>(){{
		     add(new String[] {"type","string"});
		}};
		
		return parameters;
	}
}
