package motionPlanning;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.Agent;
import core.Parameters;
import core.SMA;

public class Avatar extends Agent<EnvironnementMotionPlanning> implements
		KeyListener {
	public static int vitesseAvatar = 1;
	public boolean attrape = false;

	public Avatar(int x, int y, EnvironnementMotionPlanning env) {
		super(x, y, env);
		directionx = directiony = 0;
		this.color = Color.BLUE;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			directionx = 0;
			directiony = -1;
			break;

		case KeyEvent.VK_DOWN:
			directionx = 0;
			directiony = 1;
			break;
		case KeyEvent.VK_RIGHT:
			directionx = 1;
			directiony = 0;
			break;
		case KeyEvent.VK_LEFT:
			directionx = -1;
			directiony = 0;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void decide() {
		if (Parameters.tick % Avatar.vitesseAvatar == 0) {
			int tmpX = this.x + directionx;
			int tmpY = this.y + directiony;
			if (tmpX < 0 || tmpX >= this.env.environnement.length || tmpY < 0
					|| tmpY >= this.env.environnement[0].length
					|| this.env.environnement[tmpX][tmpY] instanceof Mur
					|| this.env.environnement[tmpX][tmpY] instanceof Chasseur) {
				directionx = 0;
				directiony = 0;
			}
		}
	}

	public void update() {
		if (Parameters.tick % Avatar.vitesseAvatar == 0
				&& (directionx != 0 || directiony != 0)) {
			env.removeTabAgent(this);
			x += directionx;
			y += directiony;

			if(env.environnement[x][y] instanceof Defender){
				Defender def = (Defender) env.environnement[x][y];
				def.die();
				EnvironnementMotionPlanning.cptDefender++;
				System.out.println("nbdefender ; " + EnvironnementMotionPlanning.cptDefender);
			}
			//on stop
			if(env.environnement[x][y] instanceof Winner){
				SMA.fini = true;
			}
			
			env.moveAgent(this);
			env.calculPathFinding();
		}
	}
}
