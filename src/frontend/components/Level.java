package frontend.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import frontend.entities.Entity;

public class Level {

	private int levelNum;
	private String levelInfo;
	private String levelDifficulty;
	private String levelText;
	private double levelTime;
	private double levelDistance;
	@XStreamOmitField
	private transient List<Entity> entityList;
	private Map<String,Boolean> HUDprops;
	private Map<String,String> GProps;
	
	public Level(int level) {
		this.setLevelNum(level);
		this.entityList = new ArrayList<Entity>();
		this.HUDprops = new HashMap<>();
		this.GProps = new HashMap<>();
	}
	
	public void addEntity(Entity entity) {
		for (Entity other : entityList) {
			if (entity.getID() == other.getID()) return;
		}
		this.entityList.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		this.entityList.remove(entity);
	}

	public String getLevelDifficulty() {
		return this.levelDifficulty;
	}
	
	public List<Entity> getEntityList(){
		return entityList;
	}

	public void setLevelDifficulty(String levelDifficulty) {
		this.levelDifficulty = levelDifficulty;
	}

	public String getLevelInfo() {
		return this.levelInfo;
	}

	public void setLevelInfo(String levelInfo) {
		this.levelInfo = levelInfo;
	}

	public int getLevelNum() {
		return this.levelNum;
	}

	public void setLevelNum(int levelNum) {
		this.levelNum = levelNum;
	}

	public String getLevelText() {
		return levelText;
	}

	public void setLevelText(String levelText) {
		this.levelText = levelText;
	}

	public double getLevelTime() {
		return levelTime;
	}

	public void setLevelTime(double levelTime) {
		this.levelTime = levelTime;
	}

	public double getLevelDistance() {
		return levelDistance;
	}

	public void setLevelDistance(double levelDistance) {
		this.levelDistance = levelDistance;
	}

	public Map<String,Boolean> getHUDprops() {
		return this.HUDprops;
	}

	public void setHUDprops(Map<String,Boolean> hUDprops) {
		this.HUDprops = hUDprops;
	}
	
	public void addHUDProp(String prop, Boolean bool) {
		this.HUDprops.put(prop, bool);
	}
	
	public void removeHUDProp(String prop) {
		this.HUDprops.remove(prop);
	}

	public Map<String,String> getGProps() {
		return this.GProps;
	}

	public void setGProps(Map<String,String> gProps) {
		this.GProps = gProps;
	}
	
	public void addGProp(String prop, String value) {
		this.GProps.put(prop, value);
	}
	
	public void removeGProp(String prop) {
		this.GProps.remove(prop);
	}
	
}
