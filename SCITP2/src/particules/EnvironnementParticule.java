package particules;


import core.Agent;
import core.Environnement;
import core.NBAgentInvalideException;
import core.Parameters;

public class EnvironnementParticule extends Environnement{
	
	public void addNewAgent() throws NBAgentInvalideException{
		
		if(Parameters.nbParticles < 0 || Parameters.nbParticles > Parameters.gridSizeX * Parameters.gridSizeY)
			throw new NBAgentInvalideException();
		
		for(int i=0; i<Parameters.nbParticles; i++){
			int x = Parameters.random.nextInt(Parameters.gridSizeX);
			int y = Parameters.random.nextInt(Parameters.gridSizeY);
			if(environnement[x][y] == null){
				Agent tmp = new Particule(x, y, this);
				environnement[x][y] = tmp;
				agents.add(tmp);
			} else {
				i--;
			}
		}
	}

	@Override
	public void environnementUpdate() {}
}
