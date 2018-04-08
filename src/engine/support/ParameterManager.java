package engine.support;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.components.Component;
import java.lang.reflect.*;
public class ParameterManager {
	public ParameterManager() {
		
	}
	
	/**
	 * get name, type and number of parameters the component needs
	 * @param component name of component
	 * @return a map: name of parameters -> type of parameters
	 */
	public static Map<String, String> getParameters(String component){
		Map<String, String> parameters = new HashMap<>();
		Class<?> cls;
		try {
			cls = Class.forName(component);
			Method method = cls.getDeclaredMethod("getParameters");
			parameters = (Map<String, String>) method.invoke(null);
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
		return ComponentBuilder.buildComponent(pid,component, parameters);
	}
	
	private static boolean isValid(String component, List<Object> parameters) {
		Map<String, String> map = getParameters(component);
		int index = 0;
		for(Map.Entry<String, String> entry: map.entrySet()) {
			String type = entry.getValue();
			Object object = parameters.get(index);
			if(!compareType(object, type)) {
				return false;
			}
			index++;
		}
		return true;	
	}
	
	private static boolean compareType(Object object, String type) {
		return false;
	}
}
