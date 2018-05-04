package engine.components;

/**
 * Data component representing an entity's score. Only used for player entities.
 * @author fitzj, sv116
 *
 */
public class Score extends SingleDataComponent implements Component, DataComponent, ReadDataComponent {
	
	public static final String KEY = "Score";
	

	public Score(int pid, double data) {
		super(pid, data);
	}

	public String getKey() {
		return KEY;
	}


}
