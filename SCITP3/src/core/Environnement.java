package core;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;



public abstract class Environnement {
	public Agent [][] environnement;
	public List<Agent> agents;
	
	
	
	//Methode pour créer une map perso
	public void initTest(){
		environnement = new Agent [Parameters.gridSizeX][Parameters.gridSizeY];
		agents = new ArrayList<Agent>();
	}
	
	public void addAgent(Agent tmp) {
		environnement[tmp.x][tmp.y] = tmp;
		agents.add(tmp);
	}
	
	
	public void init() throws NBAgentInvalideException{
		environnement = new Agent [Parameters.gridSizeX][Parameters.gridSizeY];
		agents = new ArrayList<Agent>();
		addNewAgent();
	}	
	
	public void setTorique(boolean b){
		Parameters.TORIQUE = b;
	}
	
	public abstract void addNewAgent() throws NBAgentInvalideException;

	public boolean estTorique() {
		return Parameters.TORIQUE;
	}
	
	public void removeTabAgent(Agent agent) {
		environnement[agent.x][agent.y] = null;
	}
	
	public void removeAllAgent(Agent agent) {
		environnement[agent.x][agent.y] = null;
		agents.remove(agent);
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
	
	public abstract void environnementUpdate();
}
