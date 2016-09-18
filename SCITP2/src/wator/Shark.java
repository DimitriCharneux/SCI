package wator;

import java.awt.Color;

import core.Agent;
import core.Environnement;

public class Shark extends Agent{
	public static final int SharkBreedTime = 10, SharkStarveTime = 10;
	public int age, hungry;
	
	public Shark(int x, int y, Environnement env) {
		super(x, y, env);
		color = Color.pink;
		age = hungry= 0;
	}

	@Override
	public void decide() {
		
	}

	@Override
	public void update() {
		
	}

}
