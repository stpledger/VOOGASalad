package engine.components;
import java.io.File;
import java.io.FileNotFoundException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * Sprite component containing an image. Constructor and setter throw file not found if the filepath is incorrect.
 * @author fitzj
 * @author Yameng
 */
public class Sprite extends Component {
	public static String KEY = "Sprite";
	private String filename;
	//@XStreamOmitField
	private ImageView image;

	public Sprite(int pid, String fName) throws FileNotFoundException {
	    super(pid);
		this.filename = fName;
		try {
			File imageFile = new File(filename);
			Image im = SwingFXUtils.toFXImage(ImageIO.read(imageFile), null);
		} catch (Exception e) {
			System.out.println("Can not find image files");
			throw new FileNotFoundException();
		}

	}

	public String getName() { return filename; }

	public ImageView getImage() {
		return image;
	}

	/**public void setImage(String im) throws FileNotFoundException {
		try {
			image.setImage(new Image(im));
		} catch (RuntimeException e) {
			System.out.println("oops");
		}
	}**/

	public static String getKey() { return KEY; }

}
