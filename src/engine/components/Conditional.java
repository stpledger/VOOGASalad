package engine.components;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Conditional implements Component {

    public static String KEY = "Conditional";
    private Supplier<Object> conditional;
    private Consumer<Object> action;
    private int pid;

    public Conditional (int pid) {
        this.pid = pid;
    }

    public void setCondition (Supplier<Object> conditional) {
        this.conditional = conditional;
    }

    public void setAction (Consumer<Object> action) {
        this.action = action;
    }

    public void evaluate() {
        Object o = conditional.get();
        if (o!=null) {
            action.accept(o);
        }
    }

	public String getKey() {
		return KEY;
	}

	@Override
	public int getPID() {
		return pid;
	}

    

}
