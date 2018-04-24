package engine.components;

import java.util.Map;

/**
 * Class to represent the type of an entity. Refer to the package {@code authoring.entities} for more information.
 * @author Dylan Powers
 *
 */
public class Type extends Component {

	public static String KEY = "Type";
	private String type;
	public Type(int pid, String type) {
		super(pid, KEY);
		this.type = type;
	}

	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public Map<String, String> getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

}
