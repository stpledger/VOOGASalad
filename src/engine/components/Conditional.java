package engine.components;

import engine.setup.EntityManager;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Conditional implements Component {
    private int pid;
    public static String KEY = "Component";
    private Supplier conditional, condition;
    private Component changed;
    private Consumer action;

    public Conditional (int pid) {
        this.pid = pid;
    }

    public void setCondition (Supplier conditional, Supplier condition) {
        this.conditional = conditional;
        this.condition = condition;
    }

    public void setAction (Component changed, Consumer action) {
        this.changed = changed;
        this.action = action;
    }

    public void evaluate() {
        if (conditional.get().equals(condition.get())) {
            System.out.println("condition is true");
            action.accept(changed);
        }
    }

    @Override
    public Map<String, String> getParameters() {
        return null;
    }

    public int getPID(){
        return pid;
    }
}
