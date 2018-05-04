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
<<<<<<< HEAD
	DoubleProperty lives;
	
=======
	DoubleProperty lives = new SimpleDoubleProperty();
>>>>>>> d14d617d1a426ca2ef12578b63ae3857366674a7
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
<<<<<<< HEAD
	
    public DoubleProperty getLives() {
    	System.out.println("hello");
=======
	@Override
	public double getData() {
		return lives.getValue();
	}
    public DoubleProperty getLives() {
>>>>>>> d14d617d1a426ca2ef12578b63ae3857366674a7
    	return lives;
    }
}
