package engine.components;

import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class KeyInput extends Component {

    private Map<KeyCode, Consumer<Integer>> keyActions;

    /**
     * Constructs component with entity parent ID
     *
     * @param pid ID of parent. Can not be changed externally.
     */
    public KeyInput(int pid, Map<KeyCode, Consumer<Integer>> keyActions) {
        super(pid);
        if (keyActions==null) this.keyActions = new HashMap<>();
        else this.keyActions = keyActions;
    }

    public void setOnAction (KeyCode key, Consumer<Integer> action) {
        keyActions.put(key, action);
    }

    public void doAction(KeyCode key) {
        keyActions.get(key).accept(getParentID());
    }

    public static String getKey () {
        return "KeyInput";
    }

    public Set<KeyCode> getActions () {
        return keyActions.keySet();
    }


    @Override
    public List<String[]> getParameters() {
        return null;
    }
}
