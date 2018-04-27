package engine.components;

/**
 * Class to represent the unique name of an entity.
 * @author Dylan Powers
 *
 */
public class Name extends SingleStringComponent {

	public static String KEY = "Name";
	public Name(int pid, String name) {
		super(pid, name);
	}

	public String getKey() {
		return KEY;
	}
}
