package engine.components;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Sprite component containing an image. Constructor and setter throw file not found if the filepath is incorrect.
 * @author fitzj
 * @author Yameng
 */
public class Sprite extends Component {
<<<<<<< HEAD
=======

>>>>>>> 9d2c61e58bf633d7fda5043bafe04e74d489d2b8
	public static String KEY = "Sprite";
	private String filename;
	//@XStreamOmitField
	private ImageView image;

	public Sprite(int pid, List<String> parameters) throws FileNotFoundException {
	    super(pid, KEY);
		this.filename = parameters.get(0);
		Image im;
		try {
			im = new Image(parameters.get(0));
            image = new ImageView(im);
        } catch (Exception e) {
            try {
                im = new Image(System.getProperty("user.dir") + "\\"+parameters.get(0));
                image = new ImageView(im);

            } catch (Exception a) {
                System.out.println("Can not find image files    " + System.getProperty("user.dir") + "\\"+ filename);
            }
        }

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

}
