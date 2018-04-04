package engine.components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite extends Component implements IComponent {

	private ImageView image;
	
	public Sprite(int pid, String filename) {
		super(pid);
		image = new ImageView(new Image(filename));
	}

	public ImageView getImage() {
		return image;
	}

	public void setImage(String im) {
		image.setImage(new Image(im));
	}
}
