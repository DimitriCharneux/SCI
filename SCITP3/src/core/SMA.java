package core;

import java.util.Collections;
import java.util.Observable;

public class SMA extends Observable {
	protected Environnement env;
	public static boolean stop = false, fini = false;

	public SMA(Environnement env) {
		this.env = env;
	}

	public void run() {

		Parameters.tick = 0;
		if (!env.agents.isEmpty())
			do {
				// pause
				while (stop) {
					long time = System.currentTimeMillis();
					while (System.currentTimeMillis() < time + 100) {
					}
				}

				// delay
				long time = System.currentTimeMillis();
				while (System.currentTimeMillis() < time + Parameters.delay) {
				}
				// fin delay
				if (!Parameters.sheduling.equals("sequentiel"))
					Collections.shuffle(env.agents);
				if (!Parameters.sheduling.equals("aleatoire")) {
					Agent[] tab = (Agent[]) env.agents
							.toArray(new Agent[env.agents.size()]);
					for (Agent agent : tab) {
						agent.decide();
						agent.update();
					}
				} else {
					Agent agent = env.agents.get(0);
					agent.decide();
					agent.update();
				}
				env.environnementUpdate();
				this.setChanged();
				this.notifyObservers();
				Parameters.tick++;
				if (Parameters.trace)
					System.out.println("Tick ; " + Parameters.tick);
			} while (Parameters.tick != Parameters.nbTicks && !fini);
	}

}
