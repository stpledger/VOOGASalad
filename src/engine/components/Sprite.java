package engine.components;

import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite extends Component {

	private ImageView image;
	
	public Sprite(int pid, String filename) throws FileNotFoundException {
		super(pid);
		try {
			image = new ImageView(new Image(filename));
		} catch (Exception e) {
			throw new FileNotFoundException();
		}
	}

	public static String getKey() {
		return "Sprite";
	}
	
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
