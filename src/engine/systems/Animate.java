package engine.systems;

import engine.components.Animated;
import engine.components.Component;
import engine.components.Height;
import engine.components.Sprite;
import engine.components.Width;
import engine.components.XPosition;
import engine.components.XVelocity;
import engine.components.YPosition;
import engine.exceptions.EngineException;
import javafx.scene.image.ImageView;

import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

/**
 * System which updates the entity's sprite depending on its position component which may (or may not) have been
 * moved in earlier systems, so that what the user sees moving on the screen mimics the values of that entity's
 * position, velocity, and acceleration components
 *
 * @author cndracos, fitzj
 */
public class Animate implements ISystem {
    private Map<Integer, Map<String, Component>> handledComponents;
    private Set<Integer> activeComponents;

    public Animate() {
    	handledComponents = new HashMap<>();
    	activeComponents = new HashSet<>();
    }
    
    
    /**
     * Adds components which it can act upon, choosing only the entities which have both a position AND
     * a Sprite component
     * @param pid entity's ID
     * @param components all of the entity's components
     */
    @Override
    public void addComponent(int pid, Map<String, Component> components) {
        if (components.containsKey(XPosition.KEY) && 
        	components.containsKey(YPosition.KEY) &&
        	components.containsKey(Sprite.KEY)) {
        	
            handledComponents.put(pid, components);
        }
    }

    @Override
    public void removeComponent(int pid) {
        if(handledComponents.containsKey(pid)) {
            Sprite s = (Sprite) handledComponents.get(pid).get(Sprite.KEY);
            s.getImage().setVisible(false);
            handledComponents.remove(pid);
        }
    }



    @Override
    public void setActives(Set<Integer> actives) {
        Set<Integer> myActives = new HashSet<>(actives);
        myActives.retainAll(handledComponents.keySet());
        activeComponents = myActives;
    }

    @Override
    public void execute(double time) throws EngineException {
        for (int pid : activeComponents) {

            Map<String, Component> components = handledComponents.get(pid);
            Sprite s = (Sprite) components.get(Sprite.KEY);
                        
            XPosition px = (XPosition) components.get(XPosition.KEY);
            YPosition py = (YPosition) components.get(YPosition.KEY);
            
            this.animateComponents(components);
            
            ImageView im = s.getImage();
            im.setX(px.getData()); //updates image x on position x pos
            im.setY(py.getData()); //updates image y on position y pos
            
            if(components.containsKey(Width.KEY) && components.containsKey(Height.KEY)) {
            	Width w = (Width) components.get(Width.KEY);
            	Height h = (Height) components.get(Height.KEY);
            	im.setFitWidth(w.getData());
            	im.setFitHeight(h.getData());
            }
            
        }
    }

	private void animateComponents(Map<String, Component> components) throws EngineException {
		if(components.containsKey(Animated.KEY)) {
            Sprite s = (Sprite) components.get(Sprite.KEY);
        	Animated an = (Animated) components.get(Animated.KEY);
			if(!s.isPlaying()) {
				s.animate(an.getData());
			}
        	if(components.containsKey(XVelocity.KEY)) {
        		if(Math.abs(((XVelocity) components.get(XVelocity.KEY)).getData()) < 1) {
        			if(s.isPlaying()) {
        				s.pauseAnimation();
        			}
        		} else {
        			s.playAnimation();
        		}
        	}
        }
	}

}
