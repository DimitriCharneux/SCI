package core;

import java.awt.Graphics;

import javax.swing.JPanel;



public class PanelBilles extends JPanel {

	private SMA sma;

	public PanelBilles(SMA sma) {
		this.sma = sma;
	}

	public void paintComponent(Graphics graph) {
		super.paintComponent(graph);

		if (Parameters.grid) {
			for (int i = 0; i <= Parameters.gridSizeX; i++) {
				graph.drawLine(i * Parameters.boxSize, 0, i * Parameters.boxSize,
						Parameters.gridSizeY * Parameters.boxSize);
			}

			for (int i = 0; i <= Parameters.gridSizeY; i++) {
				graph.drawLine(0, i * Parameters.boxSize, Parameters.gridSizeX * Parameters.boxSize,
						i * Parameters.boxSize);
			}
		}

		for (Agent agent : sma.env.agents) {
			graph.setColor(agent.color);
			//System.out.println("x : " + agent.x + ", y : " + agent.y);
			graph.fillOval(Parameters.boxSize * agent.x, Parameters.boxSize * agent.y, Parameters.boxSize,
					Parameters.boxSize);
		}
	}

}
