package engine.components;


import java.util.ArrayList;
import java.util.List;

/**
 *  Velocity component class
 *  @author fitzj
 **/
public class Velocity extends Component {

    private double XVel; //X velocity associated with an entity that has this VelocityComponent
	private double YVel; //Y velocity associated with an entity that has this VelocityComponent

	public static String KEY = "Velocity";

    /**
     * Constructor for a VelocityComponent, just giving it its XVel and YVel values to be stored.
     * @param XVel 		entity's initial XVel
     * @param YVel 		entity's initial YVel
     **/
    public Velocity (int pid, double XVel, double YVel) {
        super(pid, KEY);
    	this.XVel = XVel;
        this.YVel = YVel;
    }
    
    public double getXVel() {
		return XVel;
	}

	public void setXVel(double xVel) {
		XVel = xVel;
	}

	public double getYVel() {
		return YVel;
	}

	public void setYVel(double yVel) {
		YVel = yVel;
	}
}
