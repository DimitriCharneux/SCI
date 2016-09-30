package motionPlanning;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.Agent;
import core.Parameters;

public class Avatar extends Agent<EnvironnementMotionPlanning> implements
		KeyListener {
	public static int vitesseAvatar = 1;

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
		System.out.println("frappe" + e.getKeyCode());
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
					|| this.env.environnement[tmpX][tmpY] instanceof Mur) {
				directionx = 0;
				directiony = 0;
			}
		}
	}

	//TODO voir si j'enleve le torique
	public void update() {
		if (Parameters.tick % Avatar.vitesseAvatar == 0
				&& (directionx != 0 || directiony != 0)) {
			Point tmp;
			if (env.estTorique()) {
				tmp = nextCaseTorique();
			} else {
				tmp = nextCaseNonTorique();
			}
			env.removeTabAgent(this);
			x = tmp.x;
			y = tmp.y;
			env.moveAgent(this);
			env.calculPathFinding();
		}
	}

	public Point nextCaseTorique() {
		int newx = (x + directionx) % Parameters.gridSizeX;
		int newy = (y + directiony) % Parameters.gridSizeY;
		newx = newx < 0 ? newx + Parameters.gridSizeX : newx;
		newy = newy < 0 ? newy + Parameters.gridSizeY : newy;
		return new Point(newx, newy);
	}

	public Point nextCaseNonTorique() {
		return new Point(x + directionx, y + directiony);
	}

}
