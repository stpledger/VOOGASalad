package engine.components;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Sprite component containing an image. Constructor and setter throw file not found if the filepath is incorrect.
 * @author fitzj
 */
public class Sprite extends Component {

	private ImageView image;
	
	public Sprite(int pid, List<String> parameters) throws FileNotFoundException {
		super(pid);
		Image im;
		try {
			im = new Image(parameters.get(0));
		} catch (Exception e) {
			System.out.println("Can not find image files");
			throw new FileNotFoundException();
		}
		image = new ImageView(im);
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
	
	@Override
	public List<String[]> getParameters(){
		List<String[]> parameters = new ArrayList<String[]>(){{
		     add(new String[] {"filename","string"});
		}};
		
		return parameters;
	}
}
