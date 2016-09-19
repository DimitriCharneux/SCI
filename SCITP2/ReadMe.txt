TP réalisé par Dimitri Charneux (pas de binôme)

lancer avec la commande suivante : java -jar SCITP2.jar

Vous pouvez ajoutez les paramètres suivantes :
	gridSizeX [nombre] pour modifier le nombre de cases en X (10 par defaut).
	gridSizeY [nombre] pour modifier le nombre de cases en Y (10 par defaut).
	canvasSizeX [nombre] pour modifier la largeur de la fenêtre (adaptative par defaut).
	canvasSizeY [nombre] pour modifier la hauteur de la fenêtre (adaptative par defaut).
	boxSize [nombre] pour modifier la taille d'une case (50 par defaut).
	delay [nombre] pour modifier le temps de déroulement d'un tour (500 par defaut).
	scheduling ["equitable"|"sequentiel"|"aleatoire"] pour modifier le mode de scheduling (equitable par defaut).
	nbTicks [nombre] pour modifier le nombre de tour, mettre 0 pourt infini (0 par defaut).
	grid [booléen] pour afficher ou non la grille (true par defaut).
	trace [booléen] pour afficher la trace (true par defaut).
	seed [nombre] pour utiliser une graine au générateur aléatoire, mettre 0 pour aléatoire (0 par defaut).
	refresh [nombre] pour modifier la fréquence de rafraichissement de la fenêtre, mettre 1 pour rafraichir à chaque tour (1 par defaut).
	nbParticles [nombre] pour modifier le nombre de billes de la grille (3 par defaut).
	torique [booleen] pour mettre le monde en torique ou non (no torique par défaut).
	nbSharks [nombre] pour modifier le nombre de shark de la grille (3 par defaut).
	nbFishes [nombre] pour modifier le nombre de fish de la grille (3 par defaut).
	
Configuration efficace en Torique comme en Non torique:
	SharkBreedTime = 6, SharkStarveTime = 5, FishBreedTime = 2, delay 50 gridSizeX 100 gridSizeY 100 boxSize 6 nbSharks 100 nbFishes 1000 
	

	