package motionPlanning;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import core.Agent;
import core.Parameters;

public class Chasseur extends Agent<EnvironnementMotionPlanning> {
	public Point nextPoint;
	public static int vitesseChasseur = 3;

	public Chasseur(int x, int y, EnvironnementMotionPlanning env) {
		super(x, y, env);
		this.color = Color.RED;
	}

	@Override
	public void decide() {
		//TODO ajouter le cas ou on tropuve l'avatar
		List<Point> lp = this.env.getNeighbors(new Point(this.x, this.y));
		int tmpDist = this.env.pathFinding[this.x][this.y];
		System.out.println("decide");
		for (Point p : lp) {
			if (p.x < 0 || p.y < 0 || p.x >= env.pathFinding.length
					|| p.y >= env.pathFinding[0].length)
				break;
			if (this.env.pathFinding[p.x][p.y] != -1
					&& this.env.pathFinding[p.x][p.y] < tmpDist
					&& this.env.environnement[p.x][p.y] == null) {
				nextPoint = p;
				tmpDist = this.env.pathFinding[p.x][p.y];
			}
		}

	}

	@Override
	public void update() {
		if (Parameters.tick % Chasseur.vitesseChasseur == 0)
			if (nextPoint != null) {
				env.removeTabAgent(this);
				x = nextPoint.x;
				y = nextPoint.y;
				env.moveAgent(this);
			}
		nextPoint = null;
	}

}
