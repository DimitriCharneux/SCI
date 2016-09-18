package wator;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import core.Agent;
import core.Environnement;
import core.NBAgentInvalideException;
import core.Parameters;

public class EnvironnementWator extends Environnement {
	public static int nbSharks = 0, nbFishes = 0, nbBeginSharks = 5,
			nbBeginFishes = 25;
	public List<Agent> listBaby, listDead;

	public EnvironnementWator() {
		listBaby = new ArrayList<Agent>();
		listDead = new ArrayList<Agent>();
	}

	@Override
	public void addNewAgent() throws NBAgentInvalideException {
		int nbAgent = EnvironnementWator.nbBeginFishes
				+ EnvironnementWator.nbBeginSharks;
		if (nbAgent < 0
				|| nbAgent > Parameters.gridSizeX * Parameters.gridSizeY)
			throw new NBAgentInvalideException();

		for (int i = 0; i < EnvironnementWator.nbBeginFishes; i++) {
			int x = Parameters.random.nextInt(Parameters.gridSizeX);
			int y = Parameters.random.nextInt(Parameters.gridSizeY);
			if (environnement[x][y] == null) {
				Agent tmp = new Fish(x, y, this);
				environnement[x][y] = tmp;
				agents.add(tmp);
				EnvironnementWator.nbFishes++;
			} else {
				i--;
			}
		}

		for (int i = 0; i < EnvironnementWator.nbBeginSharks; i++) {
			int x = Parameters.random.nextInt(Parameters.gridSizeX);
			int y = Parameters.random.nextInt(Parameters.gridSizeY);
			if (environnement[x][y] == null) {
				Agent tmp = new Shark(x, y, this);
				environnement[x][y] = tmp;
				agents.add(tmp);
				EnvironnementWator.nbSharks++;
			} else {
				i--;
			}
		}
	}

	public List<Point> getFreeCasesAround(int x, int y) {
		List<Point> listPoint = new ArrayList<Point>();
		for (int i = x - 1; i <= x + 1; i++) {
			if (i >= 0 && i < environnement.length)
				for (int j = y - 1; j <= y + 1; j++) {
					if (j >= 0 && j < environnement[0].length) {
						if (environnement[i][j] == null)
							listPoint.add(new Point(i, j));
					}
				}
		}
		return listPoint;
	}

	public List<Point> getFishCasesAround(int x, int y) {
		List<Point> listPoint = new ArrayList<Point>();
		for (int i = x - 1; i <= x + 1; i++) {
			if (i >= 0 && i < environnement.length)
				for (int j = y - 1; j <= y + 1; j++) {
					if (j >= 0 && j < environnement[0].length) {
						if (environnement[i][j] instanceof Fish)
							listPoint.add(new Point(i, j));
					}
				}
		}
		return listPoint;
	}

	public void addBaby(Agent baby) {
		if (Parameters.trace)
			System.out.println("birth" + baby);
		environnement[baby.x][baby.y] = baby;
		listBaby.add(baby);
	}

	public void addDead(Agent dead) {
		if (Parameters.trace)
			System.out.println("death" + dead);
		environnement[dead.x][dead.y] = null;
		listDead.add(dead);
	}

	@Override
	public void environnementUpdate() {
		agents.addAll(listBaby);
		agents.removeAll(listDead);
		if (Parameters.trace) {
			System.out.println("sharks ; " + EnvironnementWator.nbSharks);
			System.out.println("fishes ; " + EnvironnementWator.nbFishes);
		}
		listBaby = new ArrayList<Agent>();
	}

}
