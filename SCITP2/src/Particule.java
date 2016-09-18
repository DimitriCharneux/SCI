import java.awt.Point;



public class Particule extends Agent{

	public Particule(int x, int y, Environnement env) {
		super(x, y, env);
	}
	public Particule(int x, int y, Environnement env, int directionx, int directiony) {
		super(x, y, env, directionx, directiony);
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
}
