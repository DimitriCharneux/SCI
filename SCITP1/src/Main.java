import java.util.Random;


public class Main {

	public static void main(String [] args){
		for(int i = 0; i<args.length-1; i++){
			switch(args[i]){
			case "grid":
				if(args[i+1].equals("false"))
					Parameters.grid = false;
				break;
			case "trace":
				if(args[i+1].equals("false"))
					Parameters.trace = false;
				break;
			case "torique":
				if(args[i+1].equals("true"))
					Parameters.TORIQUE = true;
				break;
			case "sheduling":
				if(args[i+1].equals("sequentiel"))
					Parameters.sheduling = "sequentiel";
				else if(args[i+1].equals("aleatoire"))
					Parameters.sheduling = "aleatoire";
				else 
					Parameters.sheduling = "equitable";
				break;
			case "delay":
				try{
					Parameters.delay = Integer.parseInt(args[i+1]);
				} catch (NumberFormatException e){
					Parameters.delay = 500;
				}
				break;
			case "nbTicks":
				try{
					Parameters.nbTicks = Integer.parseInt(args[i+1]);
				} catch (NumberFormatException e){
					Parameters.nbTicks = 0;
				}
				break;
			case "refresh":
				try{
					Parameters.refresh = Integer.parseInt(args[i+1]);
				} catch (NumberFormatException e){
					Parameters.refresh = 1;
				}
				break;
			case "boxSize":
				try{
					Parameters.boxSize = Integer.parseInt(args[i+1]);
				} catch (NumberFormatException e){
					Parameters.boxSize = 50;
				}
				break;
			case "canvasSizeX":
				try{
					Parameters.canvasSizeX = Integer.parseInt(args[i+1]);
				} catch (NumberFormatException e){
					Parameters.canvasSizeX = -1;
				}
				break;
			case "canvasSizeY":
				try{
					Parameters.canvasSizeY = Integer.parseInt(args[i+1]);
				} catch (NumberFormatException e){
					Parameters.canvasSizeY = -1;
				}
				break;
			case "gridSizeX":
				try{
					Parameters.gridSizeX = Integer.parseInt(args[i+1]);
				} catch (NumberFormatException e){
					Parameters.gridSizeX = 10;
				}
				break;
			case "gridSizeY":
				try{
					Parameters.gridSizeY = Integer.parseInt(args[i+1]);
				} catch (NumberFormatException e){
					Parameters.gridSizeY = 10;
				}
				break;
			case "nbParticles":
				try{
					Parameters.nbParticles = Integer.parseInt(args[i+1]);
				} catch (NumberFormatException e){
					Parameters.nbParticles = 3;
				}
				break;
			case "seed":
				try{
					Parameters.seed = Integer.parseInt(args[i+1]);
				} catch (NumberFormatException e){
					Parameters.seed = 3;
				}
				break;
			}
			
		}
		if(Parameters.seed == 0)
			Parameters.random = new Random();
		else 
			Parameters.random = new Random(Parameters.seed);
		Environnement env = new Environnement();
		try {
			env.init();
		} catch (NBBillesInvalideException e) {
			System.out.println("Nombre de billes invalides !");
			return ;
		}
		
		//test colision
//		env.initTest();
//		env.addAgent(new Agent(0, 0, env, 1, -1));
//		env.addAgent(new Agent(8, 0, env, -1, -1));
//		//fin test colision
		
		//env.setTorique(true);
		SMA sma = new SMA(env);
		Vue vue = new Vue(sma);
		sma.addObserver(vue);
		sma.run();
	}
	
}
