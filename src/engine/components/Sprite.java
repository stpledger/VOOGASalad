package engine.components;
import java.io.FileNotFoundException;


import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamOmitField;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Sprite component containing an image. Constructor and setter throw file not found if the filepath is incorrect.
 * @author fitzj
 * @author Yameng
 */
public class Sprite extends Component {
	public static final String FILE_PATH ="File:data/";
	public static String KEY = "Sprite";
	private String filename;

	@XStreamOmitField
	private ImageView image;

	public Sprite(int pid, String path) throws FileNotFoundException {
	    super(pid, KEY);
		filename = path;
		Image im = new Image(filename);
		image = new ImageView(im);
	}

	public String getName() { return filename; }

	public ImageView getImage() {
		image= new ImageView(new Image("File:data/"+filename));
		return image;
	}

	public void setImage(String im) throws RuntimeException {
		image  = new ImageView(new Image(FILE_PATH + im));

	}

}
