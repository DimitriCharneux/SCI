import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Environnement {
	protected Agent [][] environnement;
	protected List<Agent> agents;
	
	
	
	//Methode pour créer une map perso
	public void initTest(){
		environnement = new Agent [Parameters.gridSizeX][Parameters.gridSizeY];
		agents = new ArrayList<Agent>();
	}
	
	public void addAgent(Agent tmp) {
		environnement[tmp.x][tmp.y] = tmp;
		agents.add(tmp);
	}
	
	
	public void init() throws NBBillesInvalideException{
		environnement = new Agent [Parameters.gridSizeX][Parameters.gridSizeY];
		agents = new ArrayList<Agent>();
		addNewAgent();
	}	
	
	public void setTorique(boolean b){
		Parameters.TORIQUE = b;
	}
	
	private void addNewAgent() throws NBBillesInvalideException{
		
	//TODO gestion des erreurs du au nombre des agents souhaité
		if(Parameters.nbParticles < 0 || Parameters.nbParticles > Parameters.gridSizeX * Parameters.gridSizeY)
			throw new NBBillesInvalideException();
		
		for(int i=0; i<Parameters.nbParticles; i++){
			int x = Parameters.random.nextInt(Parameters.gridSizeX);
			int y = Parameters.random.nextInt(Parameters.gridSizeY);
			if(environnement[x][y] == null){
				Agent tmp = new Agent(x, y, this);
				environnement[x][y] = tmp;
				agents.add(tmp);
			} else {
				i--;
			}
		}
	}

	public boolean estTorique() {
		return Parameters.TORIQUE;
	}
	
	public void removeAgent(Agent agent) {
		environnement[agent.x][agent.y] = null;
	}
	
	public void moveAgent(Agent agent) {
		environnement[agent.x][agent.y] = agent;
	}
	
	public Agent getAgent(int x, int y){
		return environnement[x][y];
	}
	
	public Agent getAgent(Point p){
		return environnement[p.x][p.y];
	}
}
