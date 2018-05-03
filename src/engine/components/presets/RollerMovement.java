package engine.components.presets;

import engine.components.Component;
import engine.components.Jumps;
import engine.components.KeyInput;
import engine.components.YAcceleration;
import javafx.scene.input.KeyCode;

public class RollerMovement extends KeyInput {

	public RollerMovement(int pid) {
		super(pid);

		this.addCode(KeyCode.SPACE, (map) -> {
			if(map.containsKey(YAcceleration.KEY)) {
				YAcceleration ya = (YAcceleration) map.get(YAcceleration.KEY);
				if(map.containsKey(Jumps.KEY)) {
					Jumps j = (Jumps) map.get(Jumps.KEY);
					if(j.getData() > 0) {
						ya.setData(-ya.getData());
						j.addData(-1);
					}
				}
			}
		});
	
	}



}
