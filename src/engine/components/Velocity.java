package engine.components;

/**
 *  Velocity component class
 **/
public class Velocity extends Component implements IComponent {

    private double XVel; //X velocity associated with an entity that has this VelocityComponent
	private double YVel; //Y velocity associated with an entity that has this VelocityComponent
	private double gravAcc;

    /**
     * Constructor for a VelocityComponent, just giving it its XVel and YVel values to be stored.
     * @param XVel 		entity's initial XVel
     * @param YVel 		entity's initial YVel
     * @param gravAcc	entity's gravity acceleration
     **/
    public Velocity (int pid, double XVel, double YVel, double gravAcc) {
        super(pid);
    	this.XVel = XVel;
        this.YVel = YVel;
        this.gravAcc = gravAcc;
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

	public double getGravAcc() {
		return gravAcc;
	}

	public void setGravAcc(double gravAcc) {
		this.gravAcc = gravAcc;
	}
	
	
	
}
