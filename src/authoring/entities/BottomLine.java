package authoring.entities;

import engine.components.Jumps;
import engine.components.Lives;
import engine.components.Player;
import engine.components.Score;
import engine.components.presets.BottomCollision;
import engine.components.presets.PlayerMovement;
import javafx.scene.input.KeyCode;

/**
 * Block class that acts as a preset. Makes it easier to users to create an enemy without needing
 * to manually add components.
 * @author Hemanth Yakkali(hy115)
 *
 */
public class BottomLine extends InteractableEntity {

    private final static String TYPE =  "BottomLine";

    /**
     * Initialize
     * @param ID
     * @param name
     */
    public BottomLine(int ID, String name) {
        super(ID);
        addDefaultComponents();
        this.setName(name);
        this.setPresetType(TYPE);
    }

    /**
     * Add the default components to the player object.
     */
    private void addDefaultComponents() {
        this.add(new BottomCollision(this.getID()));
    }
}
