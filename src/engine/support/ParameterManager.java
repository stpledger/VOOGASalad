package engine.support;

import java.util.ArrayList;
import java.util.List;
import engine.components.Component;
import java.lang.reflect.*;

/**
 * Class used to get information for required component class, and build component instances.
 * Support the creation of component instances in authoring environment.
 * @author Yameng
 */
public class ParameterManager {
	public ParameterManager() {
		
	}

	/**
	 * Get name, type and number of parameters the component constructor requires.
	 * Return an empty list if given component class does not exist
	 * @param component name of component class
	 * @return a list of string[]: ["name of parameter", "type of parameter"]
	 */
	public static List<String[]> getParameters(String component){
		List<String[]> parameters = new ArrayList<>();
		Class<?> cls;
		try {
			cls = Class.forName(component);
			Method method = cls.getDeclaredMethod("getParameters");
			parameters = (List<String[]>) method.invoke(null);
		} catch (ClassNotFoundException e) {
			System.out.println("Missing component class error");
		}
		catch (NoSuchMethodException | SecurityException e) {
			System.out.println("Missing getParameters() method error");
		}
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.out.println("Can not invode getParameters()");
		}
		return parameters;
	}
	
	/**
	 * Build components instances based on inputs of parameters 
	 * Return null if parameters inputs are invalid
	 * @param pid parent id
	 * @param inputs parameters inputs
	 * @return component instance
	 */
	public static Component buildComponent(int pid, String component, List<String> inputs) {
		if(!isValid(component, inputs)) {
			System.out.println("Input type is invalid!");
			return null;
		}

		Component res = ComponentBuilder.buildComponent(pid,component,inputs);

		if(res == null) {
			System.out.println("Can not build a "+component+" component");
		}
		return res;
	}
	
	/**
	 * Check whether or not inputs values are invalid
	 * @param component
	 * @param inputs
	 * @return valid or not
	 */
	private static boolean isValid(String component, List<String> inputs) {
		List<String[]> parameters = getParameters(component);
		int index = 0;
		if(parameters.size() != inputs.size()) {
			return false;
		}
		for(int i = 0; i < parameters.size(); i++) {
			String type = parameters.get(i)[1];
			String input = inputs.get(i);
			if(!compareType(input,type)) {
				return false;
			}
		}
		return true;	
	}
	
	/**
	 * Check if type of object equals to type string
	 * @param type
	 * @return true or false
	 * Check if type of object matches to type string
	 * @param input input parameter value
	 * @param type required type for the parameter
	 * @return if match or not
	 */
	private static boolean compareType(String input, String type) {
		try {
			if(type.equals("double")) {
				Double.parseDouble(input);
				return true;
			}
			else if(type.equals("string")) {
				return true;
			}
			else {
				return false;
			}
		}catch(Exception e) {
			System.out.println("Input parameter " + input+" is invalid!");
			return false;
		}
	}
}
