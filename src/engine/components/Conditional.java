package engine.components;

import engine.setup.EntityManager;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Conditional extends  Component {

    public static String KEY = "Component";
    private Supplier conditional;
    private Component changed;
    private Consumer action;

    public Conditional (int pid) {
        super(pid, KEY);
    }

    public void setCondition (Supplier conditional) {
        this.conditional = conditional;
    }

    public void setAction (Component changed, Consumer action) {
        this.changed = changed;
        this.action = action;
    }

    public void evaluate() {
        if (conditional.get().equals(true)) {
            System.out.println("condition is true");
            action.accept(changed);
        }
    }

    @Override
    public Map<String, String> getParameters() {
        return null;
    }
}
