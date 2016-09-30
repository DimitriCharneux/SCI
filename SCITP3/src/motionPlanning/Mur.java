package motionPlanning;

import java.awt.Color;

import core.Agent;

public class Mur extends Agent<EnvironnementMotionPlanning>{

	public Mur(int x, int y, EnvironnementMotionPlanning env) {
		super(x, y, env);
		this.color = Color.BLACK;
	}

	@Override
	public void decide() {
	}

	@Override
	public void update() {
	}

}
