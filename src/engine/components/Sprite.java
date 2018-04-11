package engine.components;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Sprite component containing an image. Constructor and setter throw file not found if the filepath is incorrect.
 * @author fitzj
 * @author Yameng
 */
public class Sprite extends Component {
	public static String KEY = "Sprite";
	private String filename;
	
	@XStreamOmitField
	private ImageView image;

	public Sprite(int pid, String filename) throws FileNotFoundException {
	    super(pid, KEY);
		this.filename = filename;
		try {
			setImage(filename);
		}
		catch(RuntimeException e){
			System.out.print("Havent created javafx form");
		}

	}

	public String getName() { return filename; }

	public ImageView getImage() {
		ImageView image=new ImageView(new Image("File:data/"+filename));
		return image;
	}

	public void setImage(String im) throws FileNotFoundException {
		try {
			image.setImage(new Image(im));
		} catch (RuntimeException e) {
			
		}
	}

}
