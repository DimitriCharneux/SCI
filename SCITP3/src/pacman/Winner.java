package pacman;

import java.awt.Color;

import core.Agent;

public class Winner extends Defender{
	int lifeTime;
	
	public Winner(int x, int y, EnvironnementPacMan env) {
		super(x, y, env);
		color = Color.YELLOW;
	}
}
