package wator;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import core.Agent;
import core.Environnement;

public class Fish extends Agent{
	public static final int FishBreedTime = 5;
	
	public int age;
	
	public Fish(int x, int y, EnvironnementWator env) {
		super(x, y, env);
		age = 0;
		color = Color.green;
	}

	@Override
	public void decide() {
		if(age >= Fish.FishBreedTime)
			color = Color.blue;
		List<Point> listVoisins = ((EnvironnementWator)env).getFreeCasesAround(x, y);
		
		age++;
	}

	@Override
	public void update() {
		
	}

}
