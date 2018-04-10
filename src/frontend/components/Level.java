package frontend.components;

import java.util.ArrayList;
import java.util.List;

import frontend.entities.Entity;

public class Level {

	private int levelNum;
	private String levelInfo;
	private String levelDifficulty;
	private String levelText;
	private double levelTime;
	private double levelDistance;
	private List<Entity> entityList;
	
	public Level(int level) {
		this.setLevelNum(level);
		this.entityList = new ArrayList<Entity>();
	}
	
	public void addEntity(Entity entity) {
		this.entityList.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		this.entityList.remove(entity);
	}

	public String getLevelDifficulty() {
		return this.levelDifficulty;
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
	
}
