package pacman;

import java.awt.Color;

import core.Agent;

public class Defender extends Agent<EnvironnementPacMan>{
	int lifeTime;
	
	public Defender(int x, int y, EnvironnementPacMan env) {
		super(x, y, env);
		color = Color.GREEN;
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
