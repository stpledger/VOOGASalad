<<<<<<< HEAD:src/gameEngine/ECS/systems/ISystem.java
package gameEngine.ECS.systems;
=======
package systems;
>>>>>>> ab308d7045b80c175976d1e4971d96daf04903bc:src/systems/ISystem.java

import java.util.List;
import gameEngine.ECS.components.IComponent;

import components.IComponent;

public interface ISystem {

    public void execute(List<IComponent> components);

}

