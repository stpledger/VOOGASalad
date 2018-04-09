package engine.components;
/**
 * Component that indicates that the entity has action when the key corresponding to String LEFT is pressed.
 * It changes xVelocity of the entity
 * @author Stefani Vukajlovic
 */
import java.util.Map;

public class LeftKey implements IKeyInput{
	  private static final double X_VEL=-50;
	  private static final String CODE_NAME = "LEFT";

	@Override
	public void execute(String code, Map<String, Component> entityComponents) {
		if(code == CODE_NAME) {
		Velocity vel =(Velocity) entityComponents.get("Velocity");
		vel.setXVel(X_VEL);
		}
		
	}

}
