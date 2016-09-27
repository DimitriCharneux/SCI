package motionPlanning;

import java.util.ArrayList;

import particules.Particule;

import core.Agent;
import core.Environnement;
import core.NBAgentInvalideException;
import core.Parameters;

public class EnvironnementMotionPlanning extends Environnement {

	public Avatar avatar;
	
	public void init() throws NBAgentInvalideException {
		environnement = new Agent[Parameters.gridSizeX][Parameters.gridSizeY];
		agents = new ArrayList<Agent>();
		addAvatar();
		// addNewAgent();
	}

	private void addAvatar() {
		int x = Parameters.random.nextInt(Parameters.gridSizeX);
		int y = Parameters.random.nextInt(Parameters.gridSizeY);
		avatar = new Avatar(x, y, this);
		environnement[x][y] = avatar;
		agents.add(avatar);
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
				// TODO remplacer particule par chasseurs et decommenter
				/*
				 *  Agent tmp = new Particule(x, y, this);
				 *  environnement[x][y] = tmp;
				 *   agents.add(tmp);
				 */
			} else {
				i--;
			}
		}
	}

	@Override
	public void environnementUpdate() {

	}

}
