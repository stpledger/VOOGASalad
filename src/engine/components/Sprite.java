package engine.components;

import java.io.FileNotFoundException;


import data.DataRead;
import engine.systems.SpriteTransition;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Sprite component containing an image. Constructor and setter throw file not found if the filepath is incorrect.
 * @author fitzj
 */
public class Sprite extends SingleStringComponent {
	public static final String FILE_PATH ="File:";
	public static final String IMAGE_PATH ="data\\";
	private String name;
	
	public static String KEY = "Sprite";

	@XStreamOmitField
	private transient ImageView image;
	
	@XStreamOmitField
	private transient Animation a;
	
	public Sprite(int pid, String path) throws FileNotFoundException {
	    super(pid, path);
		setData(path);
		name = path;
	}
	
	public ImageView getImage() {
		if (image == null) setData(getData());
		return image;
	}

	@Override
	public void setData(String im) {

		try {
			image = new ImageView(DataRead.loadImage(im));
			
		} catch(RuntimeException e) {
			System.out.print("Cant load image no Application");
		}
		
	}
	
	public void animate(double dur, int count, int columns, int offsetX, int offsetY, int width, int height) {
		Duration duration = new Duration(dur);
		getImage().setViewport(new Rectangle2D(offsetX, offsetY, width, height));
		a = new SpriteTransition(getImage(), duration, count, columns, offsetX, offsetY, width, height);
		a.setCycleCount(Animation.INDEFINITE);
		a.play();
	}
	
	public void pauseAnimation() {
		if(a != null) {
			a.pause();
		}
	}
	
	public void playAnimation() {
		if(a != null) {
			a.play();			
		}
	}
	
	public String getKey() {
		return KEY;
	}

	public String getImageFile() {
		return getData();
	}
}

