package frontend.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import frontend.entities.Entity;

/**
 * 
 * @author Hemanth Yakkali(hy115)
 *
 */
public class Level {

	private int levelNum;
	private String levelInfo;
	private String levelDifficulty;
	private String levelText;
	private double levelTime;
	private double levelDistance;
	private List<Entity> entityList;
	private Map<String,Boolean> HUDprops;
	private Map<String,String> GProps;
	
	public Level(int level) {
		this.setLevelNum(level);
		this.entityList = new ArrayList<>();
		this.HUDprops = new HashMap<>();
		this.GProps = new HashMap<>();
	}
	
	public void addEntity(Entity entity) {
		this.entityList.add(entity);
		System.out.println("Added!");
	}
	
	public void removeEntity(Entity entity) {
		this.entityList.remove(entity);
	}

	public String getLevelDifficulty() {
		return this.levelDifficulty;
	}

	public void setLevelDifficulty(String levelDifficulty) {
		this.levelDifficulty = levelDifficulty;
		System.out.println(levelDifficulty);
	}

	public String getLevelInfo() {
		return this.levelInfo;
	}

	public void setLevelInfo(String levelInfo) {
		this.levelInfo = levelInfo;
		System.out.println(levelInfo);
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
		System.out.println(levelTime);
	}

	public double getLevelDistance() {
		return levelDistance;
	}

	public void setLevelDistance(double levelDistance) {
		this.levelDistance = levelDistance;
		System.out.println(levelDistance);
	}

	public Map<String,Boolean> getHUDprops() {
		return this.HUDprops;
	}

	public void setHUDprops(Map<String,Boolean> HUDprops) {
		this.HUDprops = HUDprops;
	}
	
	public Map<String,String> getGProps() {
		return this.GProps;
	}

	public void setGProps(Map<String,String> GProps) {
		this.GProps = GProps;
	}
	
	public void addHUDProp(String prop, Boolean bool) {
		this.HUDprops.put(prop, bool);
	}
	
	public void addGProp(String prop, String value) {
		this.GProps.put(prop, value);
	}
	
}
