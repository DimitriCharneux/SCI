package wator;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import core.Agent;
import core.Environnement;
import core.Parameters;

public class Fish extends Agent<EnvironnementWator> {
	public static int FishBreedTime = 5;

	public Point nextPoint;
	public int age, reproductionDelay;

	public Fish(int x, int y, EnvironnementWator env) {
		super(x, y, env);
		age = reproductionDelay= 0;
		color = Color.green;
	}

	@Override
	public void decide() {
		if(!env.listDead.contains(this)){
		List<Point> listFreeCase = env.getFreeCasesAround(x, y);
		if (!listFreeCase.isEmpty())
			nextPoint = listFreeCase
					.get((int) (Parameters.random.nextFloat() * listFreeCase
							.size()));
		age++;
		reproductionDelay++;
		}
	}

	@Override
	public void update() {
		if (nextPoint != null) {
			if (reproductionDelay >= Fish.FishBreedTime){
				env.addBaby(new Fish(x, y, env));
				EnvironnementWator.nbFishes++;
				reproductionDelay = 0;
			}else
				env.removeTabAgent(this);
			x = nextPoint.x;
			y = nextPoint.y;
			nextPoint = null;
			env.moveAgent(this);
		}
		color = Color.blue;
	}

	@Override
	public String toString() {
		return "fish " + id + " ; " + x + " ; "
				+ y + " ; " + age;
	}
	
}
