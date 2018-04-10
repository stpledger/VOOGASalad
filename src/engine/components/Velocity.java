package engine.components;


import java.util.ArrayList;
import java.util.List;

/**
 *  Velocity component class
 *  @author fitzj
 *  @author Yameng
 **/
public class Velocity extends Component {

    private double XVel; //X velocity associated with an entity that has this VelocityComponent
	private double YVel; //Y velocity associated with an entity that has this VelocityComponent

    /**
     * Constructor for a VelocityComponent, just giving it its XVel and YVel values to be stored.
     * @param XVel 		entity's initial XVel
     * @param YVel 		entity's initial YVel
     * @param gravAcc	entity's gravity acceleration
     **/
    public Velocity (int pid, double xv, double yv) {
        super(pid);
    	this.XVel = xv;
        this.YVel = yv;
    }
    
    public static String getKey() {
		return "Velocity";
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
	

	@Override
	public List<String[]> getParameters(){
		List<String[]> parameters = new ArrayList<String[]>(){{
		     add(new String[] {"xVel","double"});
		     add(new String[] {"yVel","double"});
		}};
		
		return parameters;
	}
}
