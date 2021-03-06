package pacman;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import core.Agent;
import core.Parameters;
import core.SMA;

public class Chasseur extends Agent<EnvironnementPacMan> {
	public Point nextPoint;
	public static boolean fuit = false;
	public static int vitesseChasseur = 3;

	public Chasseur(int x, int y, EnvironnementPacMan env) {
		super(x, y, env);
		this.color = Color.RED;
	}
	
	@Override
	public void decide(){
		if(Chasseur.fuit){
			this.color = Color.PINK;
			decideFuite();
		}else {
			this.color = Color.RED;
			decideAttaque();
		}
	}

	private void decideAttaque() {
		List<Point> lp = this.env.getNeighbors(new Point(this.x, this.y));
		int tmpDist = this.env.pathFinding[this.x][this.y];
		for (Point p : lp) {
			if (!(p.x < 0 || p.y < 0 || p.x >= env.pathFinding.length || p.y >= env.pathFinding[0].length)) {
				if (this.env.pathFinding[p.x][p.y] != -1
						&& this.env.pathFinding[p.x][p.y] < tmpDist
						&& (this.env.environnement[p.x][p.y] == null || this.env.environnement[p.x][p.y] instanceof Avatar)) {
					nextPoint = p;
					tmpDist = this.env.pathFinding[p.x][p.y];
				}
			}
		}

	}
	
	private void decideFuite() {
		List<Point> lp = this.env.getNeighbors(new Point(this.x, this.y));
		int tmpDist = this.env.pathFinding[this.x][this.y];
		for (Point p : lp) {
			if (!(p.x < 0 || p.y < 0 || p.x >= env.pathFinding.length || p.y >= env.pathFinding[0].length)) {
				if (this.env.pathFinding[p.x][p.y] != -1
						&& this.env.pathFinding[p.x][p.y] > tmpDist
						&& this.env.environnement[p.x][p.y] == null) {
					nextPoint = p;
					tmpDist = this.env.pathFinding[p.x][p.y];
				}
			}
		}

	}

	@Override
	public void update() {
		if (Parameters.tick % Chasseur.vitesseChasseur == 0)
			if (nextPoint != null) {
				if (this.env.environnement[nextPoint.x][nextPoint.y] instanceof Avatar) {
					((Avatar) this.env.environnement[nextPoint.x][nextPoint.y]).attrape = true;
					SMA.fini = true;
				} else {
					env.removeTabAgent(this);
					x = nextPoint.x;
					y = nextPoint.y;
					env.moveAgent(this);
				}
			}
		nextPoint = null;
	}

}
