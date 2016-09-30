package motionPlanning;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import particules.Particule;

import core.Agent;
import core.Environnement;
import core.NBAgentInvalideException;
import core.Parameters;

public class EnvironnementMotionPlanning extends Environnement {

	public Avatar avatar;
	public double pourcentageMur;
	public int[][] pathFinding;

	public void init() throws NBAgentInvalideException {
		environnement = new Agent[Parameters.gridSizeX][Parameters.gridSizeY];
		pathFinding = new int[Parameters.gridSizeX][Parameters.gridSizeY];
		agents = new ArrayList<Agent>();
		initPathFindingToNull();
		pourcentageMur = 0.2;
		addWall();
		addAvatar();
		addNewAgent();
	}

	private void addAvatar() {
		int x, y;
		boolean place = false;
		while (!place) {
			x = Parameters.random.nextInt(Parameters.gridSizeX);
			y = Parameters.random.nextInt(Parameters.gridSizeY);
			if (environnement[x][y] == null) {
				avatar = new Avatar(x, y, this);
				environnement[x][y] = avatar;
				agents.add(avatar);
				place = true;
			}
		}
		calculPathFinding();
	}

	public void addNewAgent() throws NBAgentInvalideException {

		if (Parameters.nbParticles < 0
				|| Parameters.nbParticles > Parameters.gridSizeX
						* Parameters.gridSizeY)
			throw new NBAgentInvalideException();

		for (int i = 0; i < Parameters.nbParticles; i++) {
			int x = Parameters.random.nextInt(Parameters.gridSizeX);
			int y = Parameters.random.nextInt(Parameters.gridSizeY);
			if (environnement[x][y] == null) {
				 Agent tmp = new Chasseur(x, y, this);
				 environnement[x][y] = tmp;
				 agents.add(tmp);
				 
			} else {
				i--;
			}
		}
	}

	private void addWall() {
		for (int x = 0; x < this.environnement.length; x++) {
			for (int y = 0; y < this.environnement[0].length; y++) {
				if (Parameters.random.nextDouble() < pourcentageMur) {
					Mur mur = new Mur(x, y, this);
					environnement[x][y] = mur;
					agents.add(mur);
				}
			}
		}
	}

	@Override
	public void environnementUpdate() {
	}
	
	public void calculPathFinding() {
		initPathFindingToNull();
		initPathFinding();
	}

	
	public void initPathFindingToNull(){
		for(int x = 0; x < pathFinding.length; x++){
			for(int y = 0; y < pathFinding[0].length; y++){
				pathFinding[x][y] = -1;
			}
		}
	}

	/** Calculate for each case the distance from the Nexus to this case. */
	public void initPathFinding() {
		Queue<Point> listCases = new LinkedList<Point>();
		pathFinding[avatar.x][avatar.y] = 0;
		listCases.add(new Point(avatar.x, avatar.y));
		while (!listCases.isEmpty()) {
			Point currentCase = listCases.poll();
			System.out.println("taille " + listCases.size());
			List<Point> neighbors = this.getNeighbors(currentCase);
			for (Point tmp : neighbors) {
				if(tmp.x < 0 || tmp.y < 0 || tmp.x >= pathFinding.length || tmp.y >= pathFinding[0].length) break;
				if (!(environnement[tmp.x][tmp.y] instanceof Mur)) {
					if(pathFinding[tmp.x][tmp.y] == -1 || pathFinding[tmp.x][tmp.y] > pathFinding[currentCase.x][currentCase.y]+1){
						System.out.println(pathFinding[tmp.x][tmp.y] + " " + pathFinding[currentCase.x][currentCase.y]);
						pathFinding[tmp.x][tmp.y] = pathFinding[currentCase.x][currentCase.y] +1;
						listCases.add(tmp);
					}
				}
			}
		}
}
	
	public List<Point> getNeighbors(Point currentCase) {
		List<Point> tmp = new ArrayList<Point>();
		tmp.add(new Point(currentCase.x+1, currentCase.y));
		tmp.add(new Point(currentCase.x-1, currentCase.y));
		tmp.add(new Point(currentCase.x, currentCase.y+1));
		tmp.add(new Point(currentCase.x, currentCase.y-1));
		return tmp;
	}

}
