package engine.components;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * Sprite component containing an image. Constructor and setter throw file not found if the filepath is incorrect.
 * @author fitzj
 */
public class Sprite extends Component {

	public static String KEY = "Sprite";
	private String filename;
	//@XStreamOmitField
	private ImageView image;

	public Sprite(int pid, String filename) throws FileNotFoundException {
	    super(pid);
		this.filename = filename;
		Image im = new Image(filename);
		/** try {
			im = new Image(filename);
		} catch (Exception e) {
			System.out.println("Can not find image files");
			throw new FileNotFoundException();
		} **/
		image = new ImageView(im);

	}

	public String getName() { return filename; }

	public ImageView getImage() {
		return image;
	}

	public void setImage(String im) throws FileNotFoundException {
		try {
			image.setImage(new Image(im));
		} catch (Exception e) {
			throw new FileNotFoundException();
		}
	}

	public static String getKey() { return KEY; }

}
