package engine.components;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Conditional extends  Component {

    public static String KEY = "Component";
    private Supplier conditional;
    private Consumer action;

    public Conditional (int pid) {
        super(pid, KEY);
    }

    public void setCondition (Supplier conditional) {
        this.conditional = conditional;
    }

    public void setAction (Consumer action) {
        this.action = action;
    }

    public void evaluate() {
        Object o = conditional.get();
        if (o!=null) {
            action.accept(o);
        }
    }

    @Override
    public Map<String, String> getParameters() {
        return null;
    }
}
