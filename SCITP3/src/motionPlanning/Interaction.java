package motionPlanning;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.Parameters;
import core.SMA;

public class Interaction implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			Chasseur.vitesseChasseur += 1;
			break;
		case KeyEvent.VK_Z:
			if (Chasseur.vitesseChasseur > 1)
				Chasseur.vitesseChasseur -= 1;
			break;
		case KeyEvent.VK_O:
			Avatar.vitesseAvatar += 1;
			break;
		case KeyEvent.VK_P:
			if (Avatar.vitesseAvatar > 1)
				Avatar.vitesseAvatar -= 1;
			break;
		case KeyEvent.VK_W:
			Parameters.delay += 25;
			break;
		case KeyEvent.VK_X:
			if (Parameters.delay > 1)
				Parameters.delay -= 25;
			break;
		case KeyEvent.VK_SPACE:
			SMA.stop = !SMA.stop;
			System.out.println(SMA.stop);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
