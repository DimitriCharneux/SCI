package particules;

import java.awt.Color;
import java.awt.Point;


import core.Agent;
import core.Environnement;
import core.Parameters;



public class Particule extends Agent<EnvironnementParticule>{

	public Particule(int x, int y, EnvironnementParticule env) {
		super(x, y, env);
		color = new Color((float) Math.random(), (float) Math.random(),
				(float) Math.random());
		color = new Color(Parameters.random.nextFloat(),
				Parameters.random.nextFloat(), Parameters.random.nextFloat());
		int nb = (int) (Parameters.random.nextFloat() * 8);
		directionx = Parameters.listDirection[nb].x;
		directiony = Parameters.listDirection[nb].y;
		if (Parameters.trace)
			System.out.println(this);
	}
	public Particule(int x, int y, EnvironnementParticule env, int directionx, int directiony) {
		super(x, y, env, directionx, directiony);
		color = new Color((float) Math.random(), (float) Math.random(),
				(float) Math.random());
		this.directionx = directionx;
		this.directiony = directiony;
		if (Parameters.trace)
			System.out.println(this);
	}
	
	public void decide() {
		// Verification des colisions avec le contour
		if (!Parameters.TORIQUE) {
			colisionMur();
		}
		colisionParticule();
	}

	private void colisionMur() {
		// Verification des colisions avec le contour
		if (x + directionx < 0 || x + directionx > Parameters.gridSizeX - 1) {
			directionx = directionx * -1;
			if (Parameters.trace)
				System.out.println(this);
		}
		if (y + directiony < 0 || y + directiony > Parameters.gridSizeY - 1) {
			directiony = directiony * -1;
			if (Parameters.trace)
				System.out.println(this);
		}
	}

	private void colisionParticule() {
		Point nextCase;
		if (Parameters.TORIQUE)
			nextCase = nextCaseTorique();
		else
			nextCase = nextCaseNonTorique();
		if (env.getAgent(nextCase) != null) {
			Point p = new Point(directionx, directiony);
			Particule a =(Particule) env.getAgent(nextCase);
			echangePosition(a.directionx, a.directiony);
			a.echangePosition(p.x, p.y);
			colision = true;
		}

	}
	
	public void update() {
		if (colision) {
			colision = false;
			return;
		}
		Point tmp;
		if (Parameters.TORIQUE) {
			tmp = nextCaseTorique();
		} else {
			tmp = nextCaseNonTorique();
		}
		env.removeTabAgent(this);
		x = tmp.x;
		y = tmp.y;
		env.moveAgent(this);
	}
	
	private Point nextCaseTorique() {
		int newx = (x + directionx) % Parameters.gridSizeX;
		int newy = (y + directiony) % Parameters.gridSizeY;
		newx = newx < 0 ? newx + Parameters.gridSizeX : newx;
		newy = newy < 0 ? newy + Parameters.gridSizeY : newy;
		return new Point(newx, newy);
	}

	private Point nextCaseNonTorique() {
		return new Point(x + directionx, y + directiony);
	}

	public void echangePosition(int dirx, int diry) {
		directionx = dirx;
		directiony = diry;
		if (Parameters.trace)
			System.out.println(this);
	}
	
	@Override
	public String toString() {
		return id + " ; " + directionx + " ; " + directiony + " ; " + x + " ; "
				+ y;
	}
}
