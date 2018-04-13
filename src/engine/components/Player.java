package engine.components;


import java.util.HashMap;
import java.util.Map;

public class Player extends Component {
    public static String KEY = "Player";

    public Player(int pid) {
        super(pid, KEY);
    }

    public static String getKey() { return KEY; }

}
