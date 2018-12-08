/**
* Le jeu du poker d'as (Poker dice) pour deux joueurs
* <br>Créateurs : Lucas Cosson & Nathanaël Houn
* <br>Groupe C1 / CMI
*/
public class PokerAs {


	/** Déclaration du nombre de faces des dés utilisés */
	public static final int FACES = 6 ;


	/** Type agrégé Gobelet pour enregistrer 6 dés et le nombre de points */
	public static class Gobelet {
		/** Le premier dé du gobelet*/
		int de1 ;
		/** Le deuxième dé du gobelet */ 
		int de2 ;
		/** Le troisième dé du gobelet */ 
		int de3 ;
		/** Le quatrième dé du gobelet */ 
		int de4 ;
		/** Le cinquième dé du gobelet */ 
		int de5 ;
		/** Nombre de points correspondant à la combinaison la plus forte du gobelet */
		int points = 0 ;
	}

	/**
	*Créer et lancer de façon aléatoire un gobelet de 5 dés
	*@return Le gobelet contenant 5 dés à valeurs entières aléatoires comprises entre 1 et le nombre de FACES inclus
	*/
	public static Gobelet lancerGob(){
		Gobelet gob = new Gobelet();
		gob.de1 = aleatoire() ;
		gob.de2 = aleatoire() ;
		gob.de3 = aleatoire() ;
		gob.de4 = aleatoire() ;
		gob.de5 = aleatoire() ;
		return(gob) ;
	}

	/**
	*Afficher le contenu d'un gobelet
	* @param gob  Gobelet que l'on veut afficher
	*/
	public static void afficherGob(Gobelet gob){
		Ecran.afficher('(',gob.de1,' ',gob.de2,' ',gob.de3,' ',gob.de4,' ',gob.de5,')');
	}


	/** Type agrégé Joueur pour enregistrer le nom du joueur et le nombre de coups remportés par le joueur */
	public static class Joueur {
		/** Nom du joueur */
		String nom ;
		/* Nombre de coups remportés */
		int gagnes ;
	}

	/**
	*Créer un joueur, entrer son nom, et définir son nombre de coups remportés à 0.
	*@return Le joueur avec son nom entré et 0 victoire
	*/
	public static Joueur creerJoueur() {
		Joueur joueur = new Joueur();
		Ecran.afficher("Entrez le nom du joueur : ");
		joueur.nom = Clavier.saisirString();
		joueur.gagnes = 0 ;
		return(joueur);
	}

	/**
	*Afficher le nom du joueur et son nombre de coups remportés
	*@param j  Joueur dont on veut afficher les informations.
	*/
	public static void afficherJoueur(Joueur j){
		Ecran.afficher(j.nom,", ",j.gagnes," victoire");

		//Accord du pluriel
		if(j.gagnes<2){
			Ecran.afficher(". ");
		} else {
			Ecran.afficher("s. ");
		}
	}


	/** Type agrégé pour enregistrer si l'on peut relancer le gobelet et quels dés du gobelet on veut relance*/
	public static class Relance {
		/** La relance demandée est-elle valide ? */
		boolean estValide ;
		/** Faut-il relancer le dé 1 ? */
		boolean de1 ;
		/** Faut-il relancer le dé 2 ? */
		boolean de2 ;
		/** Faut-il relancer le dé 3 ? */
		boolean de3 ;
		/** Faut-il relancer le dé 4 ? */
		boolean de4 ;
		/** Faut-il relancer le dé 5 ? */
		boolean de5 ;
	}

	/**
	* Afficher le type Relance (fonction utilisée lors du développement)
	* @param relance  type Relance que l'on veut afficher
	*/
	public static void afficherRelance(Relance relance){
		Ecran.afficher('(',relance.estValide,' ',relance.de1,' ',relance.de2,' ',relance.de3,' ',relance.de4,' ',relance.de5,')');
	}


	/**
	* Lancer de façon aléatoire un dé à FACES nombre de faces
	* @return Un entier compris entre 1 et le nombre de faces du dé (inclus)
	*/
	public static int aleatoire(){
		return((int)(Math.random()*FACES)+1);
	}

	/**
	*Tester si le gobelet contient une valeur testée
	*@param gob  Gobelet que l'on veut tester
	*@param i  entier dont on étudie la présence ou l'absence dans le gobelet
	*@return true si gob contient l'entier, false sinon
	*/
	public static boolean GobeletAValeur(Gobelet gob, int i){
		return(gob.de1 == i || gob.de2 == i || gob.de3 == i || gob.de4 == i || gob.de5 == i);
	}


	/**
	*Fonction qui retourne le nombre de dé identique à une valeur testée
	*@param gob  Gobelet que l'on veut tester
	*@param i  entier dont on étudie le nombre de présence dans le gobelet
	*@return Le nombre de dés contenus dans le gobelet qui ont la valeur i
	*/
	public static int compteurDeIdentique(Gobelet gob, int i){
		//Déclaration de la variable
		int nbDe = 0 ;
		
		//Comptage du nombre de dés identiques
		if(i == gob.de1){
			nbDe++;
		}
		if(i == gob.de2){
			nbDe++;
		}
		if(i == gob.de3){
			nbDe++;
		}
		if(i == gob.de4){
			nbDe++;
		}
		if(i == gob.de5){
			nbDe++;
		}

		return(nbDe);
	}


	/**
	*Teste si le gobelet contient un full, si oui, renvoie le nombre de points associé
	*@param gob  Gobelet que l'on teste
	*@return 4 s'il y a un full, 0 sinon
	*/
	public static int pointsFull(Gobelet gob){
		//Déclaration des variables
		int compteurDeIdentique1 = 0, compteurDeIdentique2 = 0;
		boolean paire=false, brelan= false;

		//Test de la présence d'un brelan et d'une paire
		for(int i = 1; i <= 6 ; i++){
			compteurDeIdentique1 = compteurDeIdentique(gob,i);

			if(compteurDeIdentique1==3){
				brelan=true ;
			} else if(compteurDeIdentique1==2){
				paire=true;
			}
		}

		//Enregistrement du nombre de points
		int points = 0;
		if(paire && brelan)
			points= 4;

		return points;
	}


	/**
	*Teste si le gobelet contient une double paire, si oui, renvoie le nombre de points associé
	*@param gob  Gobelet que l'on teste
	*@return 2 s'il y a une double paire, 0 sinon
	*/
	public static int pointsDoublePaire(Gobelet gob){
		//Déclaration des variables
		int compteurDeIdentique1 = 0, compteurDeIdentique2 = 0;
		boolean paire1 = false, paire2= false;

		//Test de la présence de deux paires
		for(int i = 1; i <= 6 ; i++){
			compteurDeIdentique1 = compteurDeIdentique(gob,i);

			if(compteurDeIdentique1==2 && paire1){
				paire2=true;
			}
			if(compteurDeIdentique1==2){
				paire1=true;
			}
		}

		//Enregistrement du nombre de points
		int points = 0;
		if(paire1 && paire2)
			points= 2;

		return points;
	}


	/**
	*Teste si le gobelet contient une petite suite, si oui, renvoie le nombre de points associé
	*@param gob  Gobelet que l'on teste
	*@return 7 s'il y a une petite suite, 0 sinon
	*/
	public static int pointsPetiteSuite(Gobelet gob) {
		//Déclaration des variables
		boolean a3et4 = true ;
		boolean a1et2 = false , a2et5 = false , a5et6 = false ;

		//Détection de la présence de 3 et 4
		for(int i=3 ;  i<=4 ; i++){
			if(!GobeletAValeur(gob,i)) {
				a3et4 = false ;
			}
		}

		//Détection des autres valeurs
		a1et2 = (GobeletAValeur(gob,1) && GobeletAValeur(gob,2));
		a2et5 = (GobeletAValeur(gob,2) && GobeletAValeur(gob,5));
		a5et6 = (GobeletAValeur(gob,5) && GobeletAValeur(gob,6));

		//Enregistrement du nombre de points
		int points =0 ;
		if(a3et4 && (a1et2||a2et5||a5et6))
			points = 6 ;
		
		return(points);
	}


	/**
	*Teste si le gobelet contient une grande suite, si oui, renvoie le nombre de points associé
	*@param gob  Gobelet que l'on teste
	*@return 8 s'il y a une grande suite, 0 sinon
	*/
	public static int pointsGrandeSuite(Gobelet gob) {
		//Déclaration des variables
		boolean aDe2a5 = true ;
		boolean a1ou6 = false ;

		//Détection de la présence de 2,3,4 et 5
		for(int i=2 ;  i<=5 ; i++){
			if(!GobeletAValeur(gob,i)) {
				aDe2a5 = false ;
			}
		}

		//Détection de la présence de 1 ou 6
		a1ou6 = (GobeletAValeur(gob,1) || GobeletAValeur(gob,6));

		//Enregistrement du nombre de points
		int points =0 ;
		if(aDe2a5 && a1ou6)
			points = 7 ;

		return(points);
	}


	/**
	*Teste si le gobelet contient une paire, un brelan, un carré ou un poker, si oui, renvoie le nombre de points associé
	*@param gob  Gobelet que l'on teste
	*@return 1 s'il y a une paire, 3 s'il y a un brelan, 4 s'il y a un carré, 8 s'il y a un poker, 0 sinon
	*/
	public static int pointsPaireBrelanCarrePoker(Gobelet gob){
		//Déclaration des variables	
		int compteurDeIdentique = 0, resultat = 0, resultatProvisoire = 0;

		//Détection du nombre de dés identiques et affectation du nombre de point
		for(int i = 1; i <= FACES ; i++){
			compteurDeIdentique = compteurDeIdentique(gob,i);

			switch(compteurDeIdentique){
				case 2 :
					resultatProvisoire = 1;
					break;
				case 3:
					resultatProvisoire = 3;
					break;
				case 4:
					resultatProvisoire = 5;
					break;
				case 5:
					resultatProvisoire = 8;
					break;
			}

			//On prends le résultat le plus haut
			if(resultatProvisoire > resultat){
				resultat = resultatProvisoire;
			}
		}
		return resultat;
	}


	/**
	*Donne le nombre de points dans un gobelet en modifiant directement dans le gobelet
	*@param gob  Gobelet que l'on teste
	*/
	public static void points(Gobelet gob){
		int points = 0;
		
		points = pointsFull(gob);
		if(points==0){
			points = pointsDoublePaire(gob);
			if(points==0){
				points = pointsGrandeSuite(gob);
				if(points==0){
					points = pointsPetiteSuite(gob);
					if(points==0){
						points = pointsPaireBrelanCarrePoker(gob);
					}
				}
			}
		}
		gob.points = points ;
	}


	/**
	*Affiche un gobelet et la combinaison la plus forte qu'il contient au format "nom_du_joueur (0 1 2 3 4) - combinaison_la_plus_forte"
	*@param j  Type agrégé Joueur du joueur qui a obtenu ce gobelet
	*@param gob Le gobelet que l'on affiche et dont on afficher la combinaison
	*/
	public static void affichageCombinaison(Joueur j, Gobelet gob){
		Ecran.afficher(j.nom," ");
		afficherGob(gob);
		points(gob);
		switch(gob.points){
			case 1 :
				Ecran.afficher(" - Paire");
				break;
			case 2 :
				Ecran.afficher(" - Double paire");
				break;
			case 3 :
				Ecran.afficher(" - Brelan");
				break;
			case 4 :
				Ecran.afficher(" - Full");
				break;
			case 5 :
				Ecran.afficher(" - Carré");
				break;
			case 6 :
				Ecran.afficher(" - Petite suite");
				break;
			case 7 :
				Ecran.afficher(" - Grande suite");
				break;
			case 8 :
				Ecran.afficher(" - Poker !");
				break;
			default :
				Ecran.afficher(" - Rien");
				break;
		}
		Ecran.sautDeLigne();
	}


	/** 
	*Affiche le score des deux joueurs puis le nom du gagnant ou l'égalité.
	*@param j1  Type agrégé du joueur 1
	*@param j2  Type agrégé du joueur 2
	*/
	public static void afficherScore(Joueur j1, Joueur j2){
		Ecran.afficherln("\n\n\n *********** Fin de partie *********** ");
		Ecran.afficherln(" ********* Écran des scores ********** ");
		afficherJoueur(j1);
		Ecran.sautDeLigne();
		afficherJoueur(j2);
		Ecran.afficher("\n ************************************* \n");

		//Affichage du gagnant
		if(j1.gagnes<j2.gagnes){
			Ecran.afficher("   C'est ",j2.nom," qui a gagné.");
		} else if(j1.gagnes==j2.gagnes){
			Ecran.afficher("Égalité parfaite entre ",j1.nom," et ",j2.nom,".");
		} else {
			Ecran.afficher("	C'est ",j1.nom," qui a gagné.");
		}
		Ecran.afficherln(" Bravo ! \n \n \n");
	}


	/** 
	*Génère un nombre aléatoire entre 0 et 1
	*@return Un nombre aléatoire entre 0 et 1 permettant de savoir quel joueur commence
	*/
	public static int TirageAuSort() {
		return((int)(Math.random()*2)) ;
	}


	/**
	*Jouer un coup avec les relances
	*@param tourJoueur Le tour du joueur qui commence le coup
	*@param j1  Type agrégé du joueur 1
	*@param j2  Type agrégé du joueur 2
	*/
	public static void coup(int tourJoueur, Joueur j1, Joueur j2) {
		//Déclaration des variables
		Gobelet gobJ1 = new Gobelet();
		int pointsJ1;
		Gobelet gobJ2 = new Gobelet();
		int pointsJ2;
		int gagnant = 0;

		//Premier tour pour les deux joueurs
		for(int i=0 ; i <2 ; i++){
			if(tourJoueur==1){
				Ecran.afficherln(j1.nom,", premier lancé de dés.");
				gobJ1 = lancerGob();
				affichageCombinaison(j1,gobJ1);
				Ecran.sautDeLigne();
			} else {
				Ecran.afficherln(j2.nom,", premier lancé de dés.");
				gobJ2 = lancerGob();
				affichageCombinaison(j2,gobJ2);
				Ecran.sautDeLigne();
			}

			//Changement de tour
			if(tourJoueur==1) {
				tourJoueur = 2 ;
			} else {
				tourJoueur = 1 ;
			}
		}

		//Première et deuxième relance pour les deux joueurs
		for(int j=1 ; j<3 ; j++){
			if(j==1){
				Ecran.afficher(" ¤¤¤¤ Première relance ¤¤¤¤\n\n");
			} else if(j==2){
				Ecran.afficher(" ¤¤¤¤ Deuxième relance ¤¤¤¤\n\n");
			}

			for(int i=0 ; i <2 ; i++){
				if(tourJoueur==1){
					Ecran.afficher(j1.nom,", c'est votre tour. Vous avez pour l'instant ");
					affichageCombinaison(j1, gobJ1);
					gobJ1 = relanceGob(gobJ1);
					affichageCombinaison(j1, gobJ1);
					Ecran.sautDeLigne();
				} else {
					Ecran.afficher(j2.nom,", c'est votre tour. Vous avez pour l'instant ");
					affichageCombinaison(j2, gobJ2);
					gobJ2 = relanceGob(gobJ2);
					affichageCombinaison(j2, gobJ2);
					Ecran.sautDeLigne();
				}

				//Changement de tour
				if(tourJoueur==1) {
					tourJoueur = 2 ;
				} else {
					tourJoueur = 1 ;
				}
			}
		}

		//Comparaison de victoire
		if(gobJ1.points<gobJ2.points){
			gagnant = 2;
		} else if(gobJ1.points<gobJ2.points){
			gagnant = 1;
		}

		//Afficher le gagnant et incrémenter la victoire du gagnant
		Ecran.sautDeLigne();
		if(gagnant==1){
			Ecran.afficher(j1.nom," a remporté le coup. ");
			j1.gagnes++ ;
		} else if(gagnant==2){
			Ecran.afficher(j2.nom," a remporté le coup. ");
			j2.gagnes++ ;
		} else {
			Ecran.afficher("Égalité entre ",j1.nom," et ",j2.nom,". ");
		}
		Ecran.afficher("\n------------------------------------------- \n");
	}


	/**
	*Vérifie que le nombre de la relance est valide, si oui, donne quels dés il faut relancer
	*@param valeur Valeur entrée par l'utilisateur à vérifier
	*@return Le type structuré permettant de savoir si la relance est possible ou non et quels dés il faut relancer
	*/
	public static Relance estValideDe(int valeur){
		//Déclaration des données
		int de  = 0, i = 1, valeurSafe = valeur;
		Relance relance = new Relance();
		relance.estValide = true;

		//Saisie négative
		if(valeur < 0){
			relance.estValide = false;
			Ecran.afficherln("Attention votre saisie est invalide !");
		}

		while(relance.estValide && valeur != 0){
			
			de = valeur % 10;

			//Saisie non comprises entre 1 et 5 
			if (de == 0 || de >= 6){
				relance.estValide = false;
				Ecran.afficherln("Vos dés doivent être compris entre 1 et 5 !");
			}

			valeur = valeur / 10;
			i++;

			//Saisie supérieure à 5 dés
			if(i > 6){
				relance.estValide = false;

				Ecran.afficherln("Vous ne pouvez pas modifier plus de cinq dés !");
			}
		}

		while(valeurSafe != 0 && relance.estValide){

			de = valeurSafe % 10;

			//Quels dés faut-il relancer ?
			switch(de){
			case 1 :
				if(relance.de1){
					relance.estValide = false;
				} else {
					relance.de1 =true ;
				}
				break;
			case 2 :
				if(relance.de2){
					relance.estValide = false;
				} else {
					relance.de2 =true ;
				}
				break;
			case 3 :
				if(relance.de3){
					relance.estValide = false;
				} else {
					relance.de3 =true ;
				}
				break;
			case 4 :
				if(relance.de4){
					relance.estValide = false;
				} else {
					relance.de4 =true ;
				}
				break;
			case 5 :
				if(relance.de5){
					relance.estValide = false;
				} else {
					relance.de5 =true ;
				}
				break;
			}

			//Même dé rentré deux fois
			if(!(relance.estValide)){
				Ecran.afficherln("Vous avez entré deux fois le même dé. ");
			}
			valeurSafe = valeurSafe/10;
		}

		return(relance);
	}


	/** Relance un gobelet avec demande de la relance
	*@param gob  Sélection du gobelet que l'on veut relancer
	*@return Le gobelet avec les modifications sur les différents dés
	*/
	public static Gobelet relanceGob(Gobelet gob){
		//Déclaration de la variable
		int valeur = 0;
		Relance relance = new Relance();

		//Demande de la relance
		Ecran.afficher("Quel(s) dé(s) voulez-vous relancer (0 pour aucun) ? ");
		valeur = Clavier.saisirInt();
		relance = estValideDe(valeur);

		while(!(relance.estValide)){
			Ecran.afficher("Quel(s) dé(s) voulez-vous relancer (0 pour aucun) ? ");
			valeur = Clavier.saisirInt();
			relance = estValideDe(valeur);
		}

		//Relance des dés
		if(relance.de1){
			gob.de1 = aleatoire();
		}
		if(relance.de2){
			gob.de2 = aleatoire();
		}
		if(relance.de3){
			gob.de3 = aleatoire();
		}
		if(relance.de4){
			gob.de4 = aleatoire();
		}
		if(relance.de5){
			gob.de5 = aleatoire();
		}

		return(gob);
	}


	/** Demande si les joueurs veulent continuer la partie ou non
	*@return 'true' si la partie doit être continuée, 'false' sinon
	*/
	public static boolean continuer(){
		boolean continuer = true ;
		Ecran.afficher("Voulez-vous continuer la partie ? (Ecrivez 'n' pour arrêter, n'importe quoi pour continuer)  ");
		char touche=Clavier.saisirChar();
		if(touche=='n' || touche=='N'){
			continuer=false;
		}
		Ecran.afficher("\n------------------------------------------- \n");
		return(continuer);
	}


	/**

	Action principale

	*/
	public static void main(String args[]) {
		
		//Déclaration des joueurs et saisie des noms
		Ecran.afficher("   ¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤ \nPremier joueur - ");
		Joueur joueur1 = creerJoueur() ;
		Ecran.afficher("Deuxième joueur - ");
		Joueur joueur2 = creerJoueur() ;
		Ecran.afficherln("   ¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤ \n \n");

		//Déclaration des variables
		int tourJoueur ;
		int vainqueurTour ;

		//Affichage des règles du jeu
		Ecran.afficherln(" ***** Bienvenue dans le Poker d'As *****");
		Ecran.afficherln("Le but est d'effectuer, par lancer aléatoire de dé, la plus grande combinaison parmi(ordre croissant) : \n - Paire \n - Double paire \n - Brelan \n - Carré \n - Petite suite (4 dés qui se suivent) \n - Grande suite (5 dés)\n - Poker");
		Ecran.afficher("Chacun votre tour, vous aurez le droit de relancer deux fois les dés de votre choix parmi vos cinq dés afin de faire le plus forte combinaison possible. ");
		Ecran.afficherln("Celui qui fait la plus forte combinaison remporte le coup ! ");
		Ecran.afficherln(" ***** Que le meilleur gagne ! ***** \n");

		//Partie
			//Tirage au sort du joueur qui commence
		tourJoueur = TirageAuSort() ;

		do{
			//Un coup
			coup(tourJoueur,joueur1,joueur2);

			//Changement de joueur qui commencera le coup suivant
			if(tourJoueur==1){
				tourJoueur=2;
			} else {
				tourJoueur=1;
			}

		}while(continuer()) ;

		//Afficher le score
		afficherScore(joueur1,joueur2);
	}
}
