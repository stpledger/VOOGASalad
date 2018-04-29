package engine.components;

import javafx.scene.input.KeyCode;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import engine.systems.collisions.CollisionDirection;

/**
 * This is the component class that allows the designer to assign an action to an entity based on a key code/key input,
 * such that the user can decide what should be done by the entity either by a preset selection or writing in his/her
 * own code
 *
 * @author cndracos
 */
public class KeyInput extends Conditional implements BehaviorComponent {

	private Map<KeyCode, Consumer<Map<String, Component>>> codes = new HashMap<>();

	public static String KEY = "KeyInput";
	
	public KeyInput(int pid) {
		super(pid);
	}

	public boolean containsCode (KeyCode key) {
		return codes.containsKey(key);
	}

	/**
	 * This method allows the user to allow a code-action pairing to a specific
	 * entity's keyInput component, allowing multiple actions to one entity
	 *
	 * @param code the keycode that will trigger the action
	 * @param con the action that happens when the key is pressed
	 */
	public void addCode (KeyCode code, Consumer<Map<String, Component>> con) {
		codes.put(code, con);
	}

	/*public void action(KeyCode key) {
		codes.get(key).accept(null);
	}*/
	
	@SuppressWarnings("unchecked")
	public void action(Set<KeyCode> codeSet, Map<String, Component> entityMap) {
        codeSet.forEach((key) -> {
        	if(codes.containsKey(key)) {
        		/*this.setAction((entity, o2) -> {
        			if(entity instanceof Map<?,?>) {
        				codes.get(key).accept((Map<String, Component>) entity);
        			}
        		});
        		this.setCondition(() -> {
        			return entityMap;
        		});
        		this.evaluate();*/
        		
        		codes.get(key).accept(entityMap);
        	}
        });
	}

	
	public String getKey() { 
		return KEY; 
	}

	@Override
	public void addBehavior(Object identifier, Consumer con) {
		if(identifier instanceof KeyCode) {
			this.addCode((KeyCode) identifier, con);
		}
	}

	@Override
	public void addBehavior(Object identifier, BiConsumer bic) {
		if(identifier instanceof KeyCode) {
			this.addCode((KeyCode) identifier, (entity) -> {
				bic.accept(entity, null);
			});
		}
	}

}


