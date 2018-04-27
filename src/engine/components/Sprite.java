package engine.components;

import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

import java.io.FileNotFoundException;


import data.DataRead;

import com.thoughtworks.xstream.annotations.XStreamOmitField;


import javafx.scene.image.ImageView;

/**
 * Sprite component containing an image. Constructor and setter throw file not found if the filepath is incorrect.
 * @author fitzj
 * @author Yameng
 */
public class Sprite extends SingleStringComponent {
	public static final String FILE_PATH ="File:";
	public static final String IMAGE_PATH ="data\\";
	
	public static String KEY = "Sprite";

	@XStreamOmitField
	private transient ImageView image;

	public Sprite(int pid, String path) throws FileNotFoundException {
	    super(pid, path);
		setData(path);
	}

	public ImageView getImage() {
		if (image == null) setData(getData());
		return image;
	}

	@Override
	public void setData(String im) {

		try {
			image = new ImageView(DataRead.loadImage(im));
			image.setPickOnBounds(true);

		} catch(RuntimeException e) {
			System.out.print("Cant load image no Application");
		}
	}
	
	public String getKey() {
		return KEY;
	}
}

