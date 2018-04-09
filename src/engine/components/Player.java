package engine.components;

public class Player extends Component {

    public Player(int pid) {
        super(pid);
    }

    public static String getKey() {
        return "Player";
    }
}
