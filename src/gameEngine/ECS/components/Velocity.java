/**
 *  Velocity component class
 **/
public class Velocity implements IComponent {

    public double XVel; //X velocity associated with an entity that has this VelocityComponent
    public double YVel; //Y velocity associated with an entity that has this VelocityComponent

    /**
     * Construtor for a VelocityComponent, just giving it its XVel and YVel values to be stored.
     * @param XVel entity's initial XVel
     * @param YVel entity's initial YVel
     **/
    public Velocity (double XVel, double YVel) {
        this.XVel = XVel;
        this.YVel = YVel;
    }

}
