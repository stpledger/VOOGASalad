package engine.components;

/**
 * This component defines poisons points of the game object.
 * The entity with this component can harm other entities it collides with, 
 * by reducing poisons points from the health points of others.
 * It is instantiated with an initial value passed from authoring environment, 
 * and changes according to game logic.
 * @author Yameng
 */
public class Poison extends Component implements IComponent{
	private double poison;
	
	public Poison(int pid, double poison) {
		super(pid);
		this.poison = poison;
	}

	public double getPoison() {
		return poison;
	}
	
	public void setPoison(double poison) {
		this.poison = poison;
	}
	
}
