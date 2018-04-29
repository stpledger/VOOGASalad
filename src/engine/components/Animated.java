package engine.components;

/**
 * Flag component to mark if an entity is animated. May be changed to point to the animation's folder.
 * @author fitzj
 *
 */
public class Animated extends FlagComponent implements Component {

	public static final String KEY = "Animated";
	
	public Animated(int pid) {
		super(pid);
	}

	@Override
	public String getKey() {
		return KEY;
	}

}
