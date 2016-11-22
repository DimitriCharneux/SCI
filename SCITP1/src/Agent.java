import java.awt.Color;
import java.awt.Point;

public class Agent {
	public int id;
	public Color color;
	public int directionx, directiony, x, y;
	public Environnement env;
	private boolean colision;

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

	public void decide() {
		// Verification des colisions avec le contour
		if (!env.estTorique()) {
			decideNonTorique();
		}
		colision();
	}

	public void decideNonTorique() {
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

	private void colision() {
		Point nextCase;
		if (env.estTorique())
			nextCase = nextCaseTorique();
		else
			nextCase = nextCaseNonTorique();
		if (env.getAgent(nextCase) != null) {
			Point p = new Point(directionx, directiony);
			Agent a = env.getAgent(nextCase);
			echangePosition(a.directionx, a.directiony);
			a.echangePosition(p.x, p.y);
			colision = true;
		}

	}

	public void inversePosition() {
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

	public void update() {
		if (colision) {
			colision = false;
			return;
		}
		Point tmp;
		if (env.estTorique()) {
			tmp = nextCaseTorique();
		} else {
			tmp = nextCaseNonTorique();
		}
		env.removeAgent(this);
		x = tmp.x;
		y = tmp.y;
		env.moveAgent(this);
	}

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
