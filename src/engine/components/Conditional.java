package engine.components;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Conditional implements Component {

    public static String KEY = "Component";
    private Supplier conditional;
    private Consumer action;
    private int pid;

    public Conditional (int pid) {
        this.pid = pid;
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
	public String getKey() {
		return KEY;
	}

	@Override
	public int getPID() {
		return pid;
	}

    
}
