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
	DoubleProperty lives = new SimpleDoubleProperty();
	public Lives(int pid, double data) {
		super(pid, data);
		setData(data);
	}

	public String getKey() {
		return KEY;
	}
	@Override
	public void setData(double data) {
		lives.setValue(data);
	}
	@Override
	public double getData() {
		return lives.getValue();
	}
    public DoubleProperty getLives() {
    	return lives;
    }
}
