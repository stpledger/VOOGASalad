package engine.components;
import java.util.Map;


public class RightKey implements IKeyInput {
	
	  private static final double X_VEL=50;
	  private static final String CODE_NAME = "RIGHT";

	@Override
	public void execute(String code, Map<String, Component> entityComponents) {
		if(code == CODE_NAME) {
		Velocity vel =(Velocity) entityComponents.get("Velocity");
		vel.setXVel(X_VEL);
		}
		
	}

}
