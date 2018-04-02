package gameEngine.ECS.systems;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import gameEngine.ECS.components.IComponent;

public class Gravity implements ISystem {
	private final String COMPONENTS_NAMES = "components_names.properties";
	private List<String> handledComponents;
    private double acceleration;
    
    public Gravity () {
    		setHandledComponets();
        acceleration = 9.8;
    }

    private void setHandledComponets() {
    		try {
				OutputStream output = new FileOutputStream(COMPONENTS_NAMES);
			} catch (FileNotFoundException e) {
				System.out.println("file not exist!");
			}
    }
    
    @Override
    public void execute(List<IComponent> components) {

    }

}
