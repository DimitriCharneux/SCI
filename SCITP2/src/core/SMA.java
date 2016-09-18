package core;

import java.util.Collections;
import java.util.Observable;




public class SMA extends Observable {
	protected Environnement env;

	public SMA(Environnement env) {
		this.env = env;
	}

	public void run() {

		Parameters.tick = 0;
		if (!env.agents.isEmpty())
			do {
				// delay
				long time = System.currentTimeMillis();
				while(System.currentTimeMillis() < time + Parameters.delay){}
				//fin delay
				if (!Parameters.sheduling.equals("sequentiel"))
					Collections.shuffle(env.agents);
				if (!Parameters.sheduling.equals("aleatoire")) {
					for (Agent agent : env.agents) {
						agent.decide();
						agent.update();
					}
				} else {
					Agent agent = env.agents.get(0);
					agent.decide();
					agent.update();
				}
				this.setChanged();
				this.notifyObservers();
				Parameters.tick++;
				if(Parameters.trace)
					System.out.println("Tick ; " + Parameters.tick );
			} while (Parameters.tick != Parameters.nbTicks);
	}

}
