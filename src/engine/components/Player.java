package engine.components;

import java.util.List;

public class Player extends Component {

    public Player(int pid) {
        super(pid);
    }

    public static String getKey() {
        return "Player";
    }

	@Override
	public List<String[]> getParameters() {
		// TODO Auto-generated method stub
		return null;
	}
}
