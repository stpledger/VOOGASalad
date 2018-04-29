package labels;

import java.util.Map;

import engine.components.Component;

public interface IGameStatusLabel{

	public double extractGameStateValue(Map<String, Component> playerStatusMap);
	
	public void update(double newValue);
	
	
}
