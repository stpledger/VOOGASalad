package engine.systems.collisions;

import java.util.Map;

import engine.components.Component;

import engine.components.Score;
import engine.components.ScoreLauncher;
import engine.setup.EntityManager;

public class ScoreHandler {
private EntityManager em;
	
	public ScoreHandler(EntityManager e) {
		this.em = e;
	}
	
	public void handle(int playerID, Map<String, Component> player, int colliderID, Map<String, Component> collider) {
		if(collider.containsKey(ScoreLauncher.KEY) && player.containsKey(Score.KEY)) {	
			Score s = (Score) player.get(Score.KEY);
			ScoreLauncher sl = (ScoreLauncher) collider.get(ScoreLauncher.KEY);
			s.setData(s.getData() + sl.getData());
			em.removeComponent(colliderID, ScoreLauncher.KEY,collider.get(ScoreLauncher.KEY));
		}
		if(collider.containsKey(Score.KEY) && player.containsKey(ScoreLauncher.KEY)) {
			Score s = (Score) collider.get(Score.KEY);
			ScoreLauncher sl = (ScoreLauncher) player.get(ScoreLauncher.KEY);
			s.setData(s.getData() + sl.getData());
			em.removeComponent(colliderID, ScoreLauncher.KEY,collider.get(ScoreLauncher.KEY));
		}
	}
	
	
}
