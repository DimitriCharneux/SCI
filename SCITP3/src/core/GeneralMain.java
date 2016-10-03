package core;

import java.util.Random;

import particules.EnvironnementParticule;

import motionPlanning.EnvironnementMotionPlanning;
import motionPlanning.Interaction;
import wator.EnvironnementWator;
import wator.Fish;
import wator.Shark;

public class GeneralMain {

	public static void main(String[] args) {
		paddingParameters(args);

		if (Parameters.seed == 0)
			Parameters.random = new Random();
		else
			Parameters.random = new Random(Parameters.seed);
		Environnement env;

		if (Parameters.choice == TPChoice.wator) {
			env = new EnvironnementWator();
		} else if (Parameters.choice == TPChoice.motionPlanning) {
			env = new EnvironnementMotionPlanning();
		} else {
			env = new EnvironnementParticule();
		}

		try {
			env.init();
		} catch (Exception e) {
			System.out.println("Nombre de joueur invalide !");
			return;
		}

		SMA sma = new SMA(env);
		Vue vue = new Vue(sma);
		sma.addObserver(vue);
		if (Parameters.choice == TPChoice.motionPlanning) {
			Interaction i = new Interaction();
			vue.addKeyListener(((EnvironnementMotionPlanning) env).avatar);
			vue.addKeyListener(i);
		}
		sma.run();
	}

	private static void paddingParameters(String[] args) {
		// Choice mod
		if (args.length > 0)
			switch (args[0]) {
			case "particules":
				Parameters.choice = TPChoice.particules;
				break;
			case "wator":
				System.out.println("wator");
				Parameters.choice = TPChoice.wator;
				break;
			case "motionPlanning":
				Parameters.choice = TPChoice.motionPlanning;
				break;
			}
		if (args.length > 1)
			switch (args[1]) {
			case "particulesDefaut":
				Parameters.gridSizeX = 100;
				Parameters.gridSizeY = 100;
				Parameters.boxSize = 5;
				Parameters.delay = 50;
				Parameters.nbParticles=30;
				Parameters.TORIQUE = false;
				break;
			case "watorDefaut":
				Parameters.gridSizeX = 100;
				Parameters.gridSizeY = 100;
				Parameters.boxSize = 5;
				Parameters.delay = 50;
				Parameters.nbParticles=3;
				Parameters.TORIQUE = true;
				break;
			case "motionPlanningDefaut":
				Parameters.gridSizeX = 10;
				Parameters.gridSizeY = 10;
				Parameters.boxSize = 50;
				Parameters.delay = 200;
				Parameters.nbParticles=3;
				break;
			}

		for (int i = 0; i < args.length - 1; i++) {
			switch (args[i]) {
			// GLOBAL
			case "grid":
				if (args[i + 1].equals("false"))
					Parameters.grid = false;
				break;
			case "trace":
				if (args[i + 1].equals("false"))
					Parameters.trace = false;
				break;
			case "torique":
				if (args[i + 1].equals("true"))
					Parameters.TORIQUE = true;
				break;
			case "sheduling":
				if (args[i + 1].equals("sequentiel"))
					Parameters.sheduling = "sequentiel";
				else if (args[i + 1].equals("aleatoire"))
					Parameters.sheduling = "aleatoire";
				else
					Parameters.sheduling = "equitable";
				break;
			case "delay":
				try {
					Parameters.delay = Integer.parseInt(args[i + 1]);
				} catch (NumberFormatException e) {
					Parameters.delay = 500;
				}
				break;
			case "nbTicks":
				try {
					Parameters.nbTicks = Integer.parseInt(args[i + 1]);
				} catch (NumberFormatException e) {
					Parameters.nbTicks = 0;
				}
				break;
			case "refresh":
				try {
					Parameters.refresh = Integer.parseInt(args[i + 1]);
				} catch (NumberFormatException e) {
					Parameters.refresh = 1;
				}
				break;
			case "boxSize":
				try {
					Parameters.boxSize = Integer.parseInt(args[i + 1]);
				} catch (NumberFormatException e) {
					Parameters.boxSize = 50;
				}
				break;
			case "canvasSizeX":
				try {
					Parameters.canvasSizeX = Integer.parseInt(args[i + 1]);
				} catch (NumberFormatException e) {
					Parameters.canvasSizeX = -1;
				}
				break;
			case "canvasSizeY":
				try {
					Parameters.canvasSizeY = Integer.parseInt(args[i + 1]);
				} catch (NumberFormatException e) {
					Parameters.canvasSizeY = -1;
				}
				break;
			case "gridSizeX":
				try {
					Parameters.gridSizeX = Integer.parseInt(args[i + 1]);
				} catch (NumberFormatException e) {
					Parameters.gridSizeX = 10;
				}
				break;
			case "gridSizeY":
				try {
					Parameters.gridSizeY = Integer.parseInt(args[i + 1]);
				} catch (NumberFormatException e) {
					Parameters.gridSizeY = 10;
				}
				break;
			case "seed":
				try {
					Parameters.seed = Integer.parseInt(args[i + 1]);
				} catch (NumberFormatException e) {
					Parameters.seed = 3;
				}
				break;

			// MOTION PLANNIG AND PARTICULES
			case "nbParticles":
				try {
					Parameters.nbParticles = Integer.parseInt(args[i + 1]);
				} catch (NumberFormatException e) {
					Parameters.nbParticles = 3;
				}
				break;

			// WATOR
			case "nbFishes":
				try {
					EnvironnementWator.nbBeginFishes = Integer
							.parseInt(args[i + 1]);
				} catch (NumberFormatException e) {
				}
				break;
			case "nbSharks":
				try {
					EnvironnementWator.nbBeginSharks = Integer
							.parseInt(args[i + 1]);
				} catch (NumberFormatException e) {
				}
				break;
			case "SharkBreedTime":
				try {
					Shark.SharkBreedTime = Integer.parseInt(args[i + 1]);
				} catch (NumberFormatException e) {
				}
				break;
			case "SharkStarveTime":
				try {
					Shark.SharkStarveTime = Integer.parseInt(args[i + 1]);
				} catch (NumberFormatException e) {
				}
				break;
			case "FishBreedTime":
				try {
					Fish.FishBreedTime = Integer.parseInt(args[i + 1]);
				} catch (NumberFormatException e) {
				}
				break;
			}
		}
	}

}
