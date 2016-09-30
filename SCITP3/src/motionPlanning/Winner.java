package motionPlanning;

import java.awt.Color;

import core.Agent;

public class Winner extends Agent<EnvironnementMotionPlanning>{
	int lifeTime;
	
	public Winner(int x, int y, EnvironnementMotionPlanning env) {
		super(x, y, env);
		color = Color.YELLOW;
		lifeTime = env.environnement.length*2;
	}

	@Override
	public void decide() {
		lifeTime --;
	}

	@Override
	public void update() {
		if(lifeTime <= 0){
			die();
		}
	}
	
	public void die(){
		this.env.agents.remove(this);
		this.env.environnement[this.x][this.y] = null;
		this.env.addDefender();
	}

}
