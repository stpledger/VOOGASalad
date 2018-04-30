package engine.systems;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * A Javafx transition to be used with a sprite for animation. Uses a rectangle viewbox to look at 
 * individual frames from a sprite sheet, and switch them at a certain speed.
 * Inspired from https://netopyr.com/2012/03/09/creating-a-sprite-animation-with-javafx/ .
 * @author fitzj
 *
 */
public class SpriteTransition extends Transition {

	private final ImageView image;
	private final int total;
	private final int cols;
	private final int x;
	private final int y;
	private final int w;
	private final int h;
	
	private int lastIndex;
	
	public SpriteTransition(ImageView image, double duration, int total, int cols, int x, int y, int w, int h) {
		this.image = image;
        this.total = total;
        this.cols = cols;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        setCycleDuration(new Duration(duration));
        setInterpolator(Interpolator.LINEAR);
	}
	
	protected void interpolate(double k) {
		final int index = Math.min((int) Math.floor(k * total), total - 1);
        if (index != lastIndex) {
            final int xPos = (index % cols) * w  + x;
            final int yPos = (index / cols) * h + y;
            image.setViewport(new Rectangle2D(xPos, yPos, w, h));
            lastIndex = index;
        }
	}

}
