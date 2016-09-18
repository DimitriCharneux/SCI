package wator;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import core.Agent;
import core.Environnement;
import core.Parameters;

public class Shark extends Agent<EnvironnementWator> {
	public static int SharkBreedTime = 6, SharkStarveTime = 5;
	public int age, hungry, reproductionDelay;

	public Point nextPoint;

	public Shark(int x, int y, EnvironnementWator env) {
		super(x, y, env);
		color = Color.pink;
		age = hungry = reproductionDelay = 0;
	}

	@Override
	public void decide() {
		if (!env.listDead.contains(this)) {
			if (SharkStarveTime == hungry + 1)
				this.died();
			else {
				List<Point> listFishCase = env.getFishCasesAround(x, y);
				if (!listFishCase.isEmpty()) {
					eat(listFishCase
							.get((int) (Parameters.random.nextFloat() * listFishCase
									.size())));
				} else {
					List<Point> listFreeCase = env.getFreeCasesAround(x, y);
					if (!listFreeCase.isEmpty()) {
						nextPoint = listFreeCase.get((int) (Parameters.random
								.nextFloat() * listFreeCase.size()));
					}
				}
				hungry++;
				age++;
				reproductionDelay++;
			}
		}
	}

	/**
	 * methode qui va faire mourir un requin.
	 */
	private void died() {
		EnvironnementWator.nbSharks--;
		env.addDead(this);
	}

	/**
	 * Méthode qui va permettre a un requin de manger.
	 * 
	 * @param p
	 */
	private void eat(Point p) {
		Fish f = (Fish) (env.environnement[p.x][p.y]);
		EnvironnementWator.nbFishes--;
		env.addDead(f);
		nextPoint = p;
		hungry = 0;
	}

	@Override
	public void update() {
		if (nextPoint != null) {
			if (reproductionDelay >= Shark.SharkBreedTime) {
				env.addBaby(new Shark(x, y, env));
				EnvironnementWator.nbSharks++;
				reproductionDelay = 0;
			} else
				env.removeTabAgent(this);
			x = nextPoint.x;
			y = nextPoint.y;
			nextPoint = null;
			env.moveAgent(this);
		}
		color = Color.red;
	}
	
	
	@Override
	public String toString() {
		return "shark "  + id + " ; " + x + " ; "
				+ y + " ; " + age;
	}
	

}
