package engine.components;
import java.io.FileNotFoundException;


import javax.imageio.ImageIO;

import data.DataRead;
import javafx.embed.swing.SwingFXUtils;

import java.util.*;

import com.thoughtworks.xstream.annotations.XStreamOmitField;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Sprite component containing an image. Constructor and setter throw file not found if the filepath is incorrect.
 * @author fitzj
 * @author Yameng
 */
public class Sprite extends Component {
	public static final String FILE_PATH ="File:";
	public static final String IMAGE_PATH ="data\\";
	public static String KEY = "Sprite";
	private String filename;

	@XStreamOmitField
	private transient ImageView image;

	public Sprite(int pid, String path) throws FileNotFoundException {
	    super(pid, KEY);
		filename = path;
		setImage(filename);
	}

	public String getName() { return filename; }

	public ImageView getImage() {
		if (image == null) setImage(filename);
		return image;
	}

	public void setImage(String im) {

		try {
			image =new ImageView(DataRead.loadImage(im));
		} catch(RuntimeException e) {
			System.out.print("Cant load image no Application");
		}
	}
	public String getImageFile()
	{
		return filename;
	}

}
