package engine.test;

import javafx.animation.AnimationTimer;

/*
 * Abstract superclass for all GameLoop subclasses. Holds the common maximumStep property.
 */
public abstract class GameLoop extends AnimationTimer	// Taken from https://github.com/svanimpe/fx-game-loops/blob/master/src/main/java/svanimpe/fxgameloop/loop/GameLoop.java
														// NOT MY PROPERTY, just for testing
{
    private float maximumStep = Float.MAX_VALUE;

    public float getMaximumStep()
    {
        return maximumStep;
    }

    public void setMaximumStep(float maximumStep)
    {
        this.maximumStep = maximumStep;
    }
}
