package engine.components;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import data.DataUtils;
import engine.exceptions.EngineException;

/**
 * 
 * @author fitzj
 *
 */
public class Animated extends SingleStringComponent implements Component, StringComponent, ReadStringComponent {

	public static final String KEY = "Animated";
	
	private static final String DURATION_KEY = "Duration";
	private static final String COUNT_KEY = "Count";
	private static final String COLUMN_KEY = "Columns";
	private static final String X_KEY = "XOffset";
	private static final String Y_KEY = "YOffset";
	private static final String W_KEY = "Width";
	private static final String H_KEY = "Height";
	
	private static final String MISSING_FILE_MESSAGE = "Missing animation properties file.";
	private static final String BAD_FORMAT_MESSAGE = "Improperly formatted animation properties file.";
	
	public Animated(int pid, String filepath) {
		super(pid, filepath);
	}
	
	public void animateSprite(Sprite s) throws EngineException {
		Properties rb = new Properties();
		try {
			File f = DataUtils.loadFile(getData());
			rb.load(new FileInputStream(f));
		} catch (Exception e) {
			throw new EngineException(MISSING_FILE_MESSAGE);
		}
		try {
			int duration = Integer.parseInt(rb.getProperty(DURATION_KEY));
			int count = Integer.parseInt(rb.getProperty(COUNT_KEY));
			int columns = Integer.parseInt(rb.getProperty(COLUMN_KEY));
			int x = Integer.parseInt(rb.getProperty(X_KEY));
			int y = Integer.parseInt(rb.getProperty(Y_KEY));
			int w = Integer.parseInt(rb.getProperty(W_KEY));
			int h = Integer.parseInt(rb.getProperty(H_KEY));
			
			s.animate(duration, count, columns, x, y, w, h);
		} catch (Exception e) {
			throw new EngineException(BAD_FORMAT_MESSAGE);
		}
		
	}

	@Override
	public String getKey() {
		return KEY;
	}

}
