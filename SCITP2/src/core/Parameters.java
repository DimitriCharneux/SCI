package core;
import java.awt.Point;
import java.util.Random;

public class Parameters {
	public static int gridSizeX=10, gridSizeY=10, nbParticles=3, boxSize = 50, seed = 0,
			delay = 500, nbTicks=0, refresh=1, tick, canvasSizeX = -1, canvasSizeY = -1, cptBilles = 0, numAgent = 1;
	public static boolean TORIQUE = true, grid = true, trace = true;
	public static String sheduling = "equitable";
	public static Random random;
	public static final Point[] listDirection = {new Point(1,1), new Point(1,0),new Point(1,-1),new Point(-1,-1),new Point(-1,0),new Point(-1,1),new Point(0,1),new Point(0,-1)};
}
