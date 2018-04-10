package engine.components;


import java.util.List;

/**
 * This component defines type fof entity,like "player","block" etc
 * @author Yameng
 */
public class EntityType extends Component{
	private String type = "Not Defined";
	
	public EntityType(int pid, String type) {
		super(pid);
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

}
