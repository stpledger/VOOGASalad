package engine.components;

import java.util.List;

public class Player extends Component {

    public Player(int pid) {
        super(pid);
    }

    @Override
    public List<String[]> getParameters() {
        return null;
    }

    public static String getKey() {
        return "Player";
    }
}
