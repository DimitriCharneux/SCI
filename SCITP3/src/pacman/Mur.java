package pacman;

import java.awt.Color;

import core.Agent;

public class Mur extends Agent<EnvironnementPacMan>{

	public Mur(int x, int y, EnvironnementPacMan env) {
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
