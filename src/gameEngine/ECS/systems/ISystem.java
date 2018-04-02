package gameEngine.ECS.systems;

import java.util.List;
import gameEngine.ECS.components.IComponent;

public interface ISystem {

    public void execute(List<IComponent> components);

}
