package engine.components;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Data component representing an entity's lives.
 * @author fitzj
 *
 */
public class Lives extends SingleDataComponent implements Component, DataComponent, ReadDataComponent {

	public static final String KEY = "Lives";
	public Lives(int pid, double data) {
		super(pid, data);
	}

	public String getKey() {
		return KEY;
	}

}
