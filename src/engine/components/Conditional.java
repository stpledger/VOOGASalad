package engine.components;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class Conditional implements Component {

    public static String KEY = "Conditional";
    private Supplier<Object> conditional;
    private BiConsumer<Object, Object> action;
    private int pid;

    public Conditional (int pid) {
        this.pid = pid;
    }

    public void setCondition (Supplier<Object> conditional) {
        this.conditional = conditional;
    }

    public void setAction (BiConsumer<Object, Object> action) {
        this.action = action;
    }

    public void evaluate() {
        Object o = conditional.get();
        if (o!=null) {
            action.accept(o, null);
        }
    }
    
     public void evaluate(Object o2) {
        Object o = conditional.get();
        if (o!=null) {
            action.accept(o, o2);
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
