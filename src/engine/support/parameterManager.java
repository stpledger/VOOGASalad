package engine.support;

import java.util.List;
import java.util.Map;

import engine.components.Component;

public class parameterManager {
	public parameterManager() {
		
	}
	
	/**
	 * get name, type and number of parameters the component needs
	 * @param component name of component
	 * @return a map: name of parameters -> type of parameters
	 */
	public static Map<String, String> getParameters(String component){
		return null;
	}
	
	/**
	 * build components instances based on parameters inputs 
	 * return null if parameters inputs are invalid
	 * @param pid parent id
	 * @param component name of component class
	 * @param parameters parameters inputs
	 * @return component instance
	 */
	public static Component buildComponent(int pid, String component, List<Object> parameters) {
		if(!isValid(component, parameters)) {
			return null;
		}
		
		//build component here
		return null;
	}
	
	private static boolean isValid(String component, List<Object> parameters) {
		return false;
	}
}
