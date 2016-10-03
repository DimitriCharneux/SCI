package core;

import java.awt.Graphics;

import javax.swing.JPanel;

import motionPlanning.EnvironnementMotionPlanning;

public class PanelBilles extends JPanel {

	private SMA sma;

	public PanelBilles(SMA sma) {
		this.sma = sma;
	}

	public void paintComponent(Graphics graph) {
		super.paintComponent(graph);
		if (Parameters.choice == TPChoice.motionPlanning && sma.fini) {
			if (((EnvironnementMotionPlanning) sma.env).avatar.attrape) {
				graph.drawString("Perdu !", 50, 50);
			} else {
				graph.drawString("Gagné !", 50, 50);
			}
			return ;
		}

		if (Parameters.grid) {
			for (int i = 0; i <= Parameters.gridSizeX; i++) {
				graph.drawLine(i * Parameters.boxSize, 0, i
						* Parameters.boxSize, Parameters.gridSizeY
						* Parameters.boxSize);
			}

			for (int i = 0; i <= Parameters.gridSizeY; i++) {
				graph.drawLine(0, i * Parameters.boxSize, Parameters.gridSizeX
						* Parameters.boxSize, i * Parameters.boxSize);
			}
		}
		Agent[] tab = (Agent[]) sma.env.agents.toArray(new Agent[sma.env.agents
				.size()]);
		for (Agent agent : tab) {
			graph.setColor(agent.color);
			// System.out.println("x : " + agent.x + ", y : " + agent.y);
			graph.fillOval(Parameters.boxSize * agent.x, Parameters.boxSize
					* agent.y, Parameters.boxSize, Parameters.boxSize);
		}

//		if (Parameters.choice == TPChoice.motionPlanning) {
//			graph.setColor(Color.GRAY);
//			for (int x = 0; x < sma.env.environnement.length; x++) {
//				for (int y = 0; y < sma.env.environnement[0].length; y++) {
//					graph.drawString(
//							((EnvironnementMotionPlanning) (sma.env)).pathFinding[x][y]
//									+ "", 15 + x * Parameters.boxSize, 15 + y
//									* Parameters.boxSize);
//				}
//			}
//		}
	}

}
