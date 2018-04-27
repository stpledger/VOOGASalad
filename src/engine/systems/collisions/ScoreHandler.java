package engine.systems.collisions;

import java.util.Map;

import engine.components.Component;
import engine.components.Damage;
import engine.components.DamageLauncher;
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
			((Score) player.get(Score.KEY)).addScore(((ScoreLauncher) collider.get(ScoreLauncher.KEY)).getScore());
			em.removeComponent(colliderID, ScoreLauncher.KEY,collider.get(ScoreLauncher.KEY));
		}
		if(collider.containsKey(Score.KEY) && player.containsKey(ScoreLauncher.KEY)) {
			((Score) collider.get(Score.KEY)).addScore(((ScoreLauncher) player.get(ScoreLauncher.KEY)).getScore());
			em.removeComponent(colliderID, ScoreLauncher.KEY,collider.get(ScoreLauncher.KEY));
		}
	}
	
	
}
