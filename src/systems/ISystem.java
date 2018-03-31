package systems;

import java.util.List;

import components.IComponent;

public interface ISystem {

    public void execute(List<IComponent> components);

}

