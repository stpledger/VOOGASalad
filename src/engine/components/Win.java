package engine.components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Win extends SingleDataComponent implements Component, DataComponent, ReadDataComponent {
	public static final String KEY = "WIN";
	private BooleanProperty winStatus = new SimpleBooleanProperty();
	
	public Win(int pid) {
		super(pid, 0);
		winStatus.setValue(false);
	}

	public String getKey() {
		return KEY;
	}

	public void win() {
		winStatus.setValue(true);

	}

	public BooleanProperty getWinStatus () {
		return winStatus;
	}

}
