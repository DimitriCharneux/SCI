package core;



import java.awt.Color;
import java.awt.Point;


public abstract class Agent <T extends Environnement>{
	public int id;
	public Color color;
	public int directionx, directiony, x, y;
	public T env;
	protected boolean colision;
	public boolean isLife = true;

	public Agent(int x, int y, T env) {
		this.env = env;
		this.x = x;
		this.y = y;
		id = Parameters.numAgent;
		Parameters.numAgent++;
		colision = false;
		Parameters.cptBilles++;
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
	public Agent(int x, int y, T env, int directionx, int directiony) {
		this.env = env;
		this.x = x;
		this.y = y;
		colision = false;
		if (Parameters.trace)
			System.out.println(this);
	}

	public abstract void decide();

	public abstract void update();

	@Override
	public String toString() {
		return id + " ; " + x + " ; "
				+ y;
	}

}
