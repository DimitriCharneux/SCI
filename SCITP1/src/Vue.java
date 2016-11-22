import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Vue extends JFrame implements Observer {

	private PanelBilles content;

	public Vue(SMA sma) {
		this.setTitle("billes");
		if(Parameters.canvasSizeX == -1)
			Parameters.canvasSizeX = Parameters.gridSizeX*Parameters.boxSize +30;
		if(Parameters.canvasSizeY == -1)
			Parameters.canvasSizeY = (Parameters.gridSizeY*Parameters.boxSize) + 30;
		this.setSize(Parameters.canvasSizeX, Parameters.canvasSizeY);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		content = new PanelBilles(sma);
		content.setPreferredSize(new Dimension(Parameters.canvasSizeX, Parameters.canvasSizeY));
		JScrollPane scroll = new JScrollPane(content);
		this.setContentPane(scroll);
		this.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(Parameters.tick % Parameters.refresh == 0){
			content.repaint();
		}
	}

}
