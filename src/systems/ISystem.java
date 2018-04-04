package systems;

import java.util.List;

import entities.Entity;

public interface ISystem {

    void execute(List<Entity> entities);

}

