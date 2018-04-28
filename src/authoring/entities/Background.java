package authoring.entities;

/**
 * 
 * @author Hemanth Yakkali(hy115)
 *
 */
public class Background extends NonInteractableEntity{

	private static final String TYPE = "Background";
	private String name;
	private static final double BKGRND_DIMENSION = 50;

	public Background(int ID, String name) {
		super(ID);
		this.name = name;
	}

	protected void addDefaultComponents() {
		this.setEntityType(TYPE);
		this.setDimension(BKGRND_DIMENSION, BKGRND_DIMENSION);
	}

	@Override
	public String type() {
		return TYPE;
	}
	
	@Override 
	public String name() {
		return this.name;
	}

}
