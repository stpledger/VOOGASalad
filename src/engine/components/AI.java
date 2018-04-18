package engine.components;

import java.util.Map;
import java.util.function.Consumer;

public class AI extends Component {

    public static String KEY = "AI";

    private Consumer action;

    public AI (int pid) {
        super(pid);
    }

    public void setAction (Consumer action) {
        this.action = action;
    }

    public void doAction(Double time) {
        action.accept(time);
    }

	@Override
	public Map<String, String> getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getKey() {
		return KEY;
	}
}
