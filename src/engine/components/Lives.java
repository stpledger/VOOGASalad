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
	DoubleProperty lives;
	
	public Lives(int pid, double data) {
		super(pid, data);
		lives = new SimpleDoubleProperty(1);
	}

	public String getKey() {
		return KEY;
	}
	@Override
	public void setData(double data) {
		lives.setValue(data);
	}
	
    public DoubleProperty getLives() {
    	System.out.println("hello");
    	return lives;
    }
}
