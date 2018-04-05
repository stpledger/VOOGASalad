package engine.setup;

import java.util.*;

import engine.components.Component;
import engine.components.Position;
import engine.systems.*;

public class GameInitializer {

    private List<ISystem> systems;
    private List<Position> renderOrder;

    private SystemManager SM;

    public GameInitializer (Map <Integer, Map<String, Component>> entities) {
        systems = new ArrayList<>();
        renderOrder = new ArrayList<>();

        systems.add(new Accelerate());
        systems.add(new Motion());

        SM = new SystemManager(systems);

        for (int id : entities.keySet()) {
            Map<String, Component> components = entities.get(id);
            if (components.containsKey(Position.getKey())) {
                Position p = (Position) components.get(Position.getKey());
                renderOrder.add(p);
            }
            SM.addComponents(id, components);
        }

        Collections.sort(renderOrder, new posComparator());
    }

    public class posComparator implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            Position p1 = (Position) o1;
            Position p2 = (Position) o2;

            if (p1.getXPos() <= p2.getXPos()) return 1;
            else if (p2.getXPos() > p1.getXPos()) return -1;
            else return 0;
        }
    }
}
