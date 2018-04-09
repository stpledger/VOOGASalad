package engine.components;

import java.util.Map;

public class DownKey implements IKeyInput {
	 private static final double Y_VEL=-50;
	  private static final String CODE_NAME = "DOWN";

	@Override
	public void execute(String code, Map<String, Component> entityComponents) {
		if(code == CODE_NAME) {
		Velocity vel =(Velocity) entityComponents.get("Velocity");
		vel.setYVel(Y_VEL);
		}
		
	}
}
