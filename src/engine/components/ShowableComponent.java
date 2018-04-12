package engine.components;
import java.util.Map;

public abstract class ShowableComponent extends Component{
    public ShowableComponent(int pid, String KEY){
        super(pid, KEY);
    };
    public abstract Map<String, String> getParameters();
}
