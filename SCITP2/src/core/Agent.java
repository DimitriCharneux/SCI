package core;



import java.awt.Color;
import java.awt.Point;


public abstract class Agent {
	public int id;
	public Color color;
	public int directionx, directiony, x, y;
	public Environnement env;
	protected boolean colision;

	public Agent(int x, int y, Environnement env) {
		this.env = env;
		this.x = x;
		this.y = y;
		id = Parameters.numAgent;
		Parameters.numAgent++;
		color = new Color(Parameters.random.nextFloat(),
				Parameters.random.nextFloat(), Parameters.random.nextFloat());
		int nb = (int) (Parameters.random.nextFloat() * 8);
		directionx = Parameters.listDirection[nb].x;
		directiony = Parameters.listDirection[nb].y;
		colision = false;
		Parameters.cptBilles++;
		if (Parameters.trace)
			System.out.println(this);
	}

	/**
	 * Cette méthode est utilisée pour créer un agent spécifique. Elle sera
	 * utilisée uniquement par les développeur.
	 * 
	 * @param x
	 * @param y
	 * @param env
	 * @param directionx
	 * @param directiony
	 */
	public Agent(int x, int y, Environnement env, int directionx, int directiony) {
		this.env = env;
		this.x = x;
		this.y = y;
		color = new Color((float) Math.random(), (float) Math.random(),
				(float) Math.random());
		this.directionx = directionx;
		this.directiony = directiony;
		colision = false;
		if (Parameters.trace)
			System.out.println(this);
	}

	public abstract void decide();

	public void inverseDirection() {
		directionx = directionx * -1;
		directiony = directiony * -1;
		if (Parameters.trace)
			System.out.println(this);
	}

	public void echangePosition(int dirx, int diry) {
		directionx = dirx;
		directiony = diry;
		if (Parameters.trace)
			System.out.println(this);
	}

	public abstract void update();

	public Point nextCaseTorique() {
		int newx = (x + directionx) % Parameters.gridSizeX;
		int newy = (y + directiony) % Parameters.gridSizeY;
		newx = newx < 0 ? newx + Parameters.gridSizeX : newx;
		newy = newy < 0 ? newy + Parameters.gridSizeY : newy;
		return new Point(newx, newy);
	}

	public Point nextCaseNonTorique() {
		return new Point(x + directionx, y + directiony);
	}

	@Override
	public String toString() {
		return id + " ; " + directionx + " ; " + directiony + " ; " + x + " ; "
				+ y;
	}

}
