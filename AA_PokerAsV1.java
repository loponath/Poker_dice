public class AA_PokerAsV1 {
	/**
	* Le jeu du poker d'as (Poker dice) pour deux joueurs.
	* Créateurs : Lucas Cosson & Nathanaël Houn
	*/
	
	/** Déclaration du nombre de faces des dés utilisés */
	static final int FACES = 6 ;
	
	/** Classe Gobelet qui contient 6 dés à valeurs entières */
	public static class Gobelet {
		int de1 ;
		int de2 ;
		int de3 ;
		int de4 ;
		int de5 ;
	}
	
	/** Classe relance pour savoir si l'on relance le gobelet */
	public static class Relance {
		boolean estValide ;
		boolean de1 ;
		boolean de2 ;
		boolean de3 ;
		boolean de4 ;
		boolean de5 ;
		
	}
	
	/** 
	* Afficher le type Relance 
	* @param Type Relance que l'on veut afficher
	*/
	public static void afficherRelance(Relance relance){
		Ecran.afficher('(',relance.estValide,' ',relance.de1,' ',relance.de2,' ',relance.de3,' ',relance.de4,' ',relance.de5,')');
	}
	
	/** Lancer aléatoire d'un dé à FACES nombre de FACE */
	public static int aleatoire(){
		return((int)(Math.random()*FACES)+1);
		
	}
	
	/** Lancer aléatoire d'un gobelet de 5 dés */
	public static Gobelet lancerGob(){
		Gobelet gob=new Gobelet();
		gob.de1 = aleatoire() ;
		gob.de2 = aleatoire() ;
		gob.de3 = aleatoire() ;
		gob.de4 = aleatoire() ;
		gob.de5 = aleatoire() ;
		return(gob) ;
	}
	
	/** Afficher le contenu d'un gobelet */
	public static void afficherGob(Gobelet gob){
		Ecran.afficher('(',gob.de1,' ',gob.de2,' ',gob.de3,' ',gob.de4,' ',gob.de5,')');
	}
	
	
	
	/** Teste si le gobelet contient une valeur */
	public static boolean GobeletAValeur(Gobelet gob, int i){
		return(gob.de1 == i || gob.de2 == i || gob.de3 == i || gob.de4 == i || gob.de5 == i);
	}
	
	/** Fonction qui retourne le nombre de dé identique à la valeur */
	public static int compteurDeIdentique(Gobelet gob, int i){
		int nbDe = 0 ;
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
	
	/** Teste si le gobelet contient un full, si oui, renvoie le nombre de points associé */
	public static int pointsFull(Gobelet gob){
		
		int compteurDeIdentique1 = 0, compteurDeIdentique2 = 0;
		boolean paire=false, brelan= false;
		
		for(int i = 1; i <= 6 ; i++){
			compteurDeIdentique1 = compteurDeIdentique(gob,i);
			
			if(compteurDeIdentique1==3){
				brelan=true ;
			} else if(compteurDeIdentique1==2){
				paire=true;
			}
		}
		
		int points = 0;
		
		if(paire && brelan)
			points= 4;
		
		return points;
		
	}
	
		/** Teste si le gobelet contient une double paire, si oui, renvoie le nombre de points associé */
	public static int pointsDoublePaire(Gobelet gob){
		
		int compteurDeIdentique1 = 0, compteurDeIdentique2 = 0;
		boolean paire1 = false, paire2= false;
		
		for(int i = 1; i <= 6 ; i++){
			compteurDeIdentique1 = compteurDeIdentique(gob,i);
			
			if(compteurDeIdentique1==2 && paire1){
				paire2=true;
			}
			if(compteurDeIdentique1==2){
				paire1=true;
			}
		}
		
		int points = 0;
		
		if(paire1 && paire2)
			points= 2;
		
		return points;
		
	}
	
	/** Teste si le gobelet contient une petite suite, et si oui, renvoie le nombre de point associé */
	public static int pointsPetiteSuite(Gobelet gob) {
		boolean a3et4 = true ;
		boolean a1et2 = false , a2et5 = false , a5et6 = false ;

		for(int i=3 ;  i<=4 ; i++){
			if(!GobeletAValeur(gob,i)) {
				a3et4 = false ;
			}
		}

		a1et2 = (GobeletAValeur(gob,1) && GobeletAValeur(gob,2));
		a2et5 = (GobeletAValeur(gob,2) && GobeletAValeur(gob,5));
		a5et6 = (GobeletAValeur(gob,5) && GobeletAValeur(gob,6));

		int points =0 ;
		if(a3et4 && (a1et2||a2et5||a5et6)){
			points = 6 ;
		}
		return(points);
	}

	/** Teste si le gobelet contient une grande suite, et si oui, renvoie le nombre de point associé */
	public static int pointsGrandeSuite(Gobelet gob) {
		boolean aDe2a5 = true ;
		boolean a1ou6 = false ; 

		for(int i=2 ;  i<=5 ; i++){
			if(!GobeletAValeur(gob,i)) {
				aDe2a5 = false ;
			}
		}
		a1ou6 = (GobeletAValeur(gob,1) || GobeletAValeur(gob,6));
		
		int points =0 ;
		if(aDe2a5 && a1ou6) 
			points = 7 ;

		return(points);
	}
	
	/** Teste si le gobelet contient une paire, un brelan, un carré ou un poker */
	public static int pointsPaireBrelanCarrePoker(Gobelet gob){
		
			int compteurDeIdentique = 0, resultat = 0, resultatProvisoire = 0;
			
		
			for(int i = 1; i <= FACES ; i++){
				compteurDeIdentique = compteurDeIdentique(gob,i);
				
				switch(compteurDeIdentique){
					case 2:
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
				if(resultatProvisoire > resultat){
					resultat = resultatProvisoire;
				}
			}

			return resultat;
		
		}
	
	/** Donne le nombre de points dans un gobelet */
	public static int points(Gobelet gob){
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
		return(points);
	}

	/**Afficher un gobelet et la combinaison la plus forte qu'il contient */
	public static void affichageCombinaison(Joueur j, Gobelet gob){
		Ecran.afficher(j.nom," ");
		afficherGob(gob);
		int point = points(gob);
		switch(point){
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
	
	
	/** Classe Joueur qui contient le nom du joueur et le nombre de coups remportés par le joueur */
	public static class Joueur {
		String nom ;
		int gagnes ;
		boolean continuer = true ;
	}
	
	/** Fonction pour entrer le nom du joueur, et définir son nombre de coups remportés à 0. */
	public static Joueur creerJoueur() {
		Joueur joueur = new Joueur();
		Ecran.afficher("Entrez le nom du joueur : ");
		joueur.nom = Clavier.saisirString();
		joueur.gagnes = 0 ;
		return(joueur);
	}
	
	/** Fonction pour afficher le nom du joueur et son nombre de coups remportés 
	
	@param j : 
	
	*/
	public static void afficherJoueur(Joueur jou){
		Ecran.afficher(jou.nom,", ",jou.gagnes," victoire");
		
		if(jou.gagnes<2){
			Ecran.afficher(". ");
		} else {
			Ecran.afficher("s. ");
		}
	}

	/** Fonction affichant le score des deux joueurs puis le gagnant ou l'égalité. 
	
	@param j1 : Type agrégé du joueur 1
	@param j2 : Type agrégé du joueur 2
	
	*/
	public static void afficherScore(Joueur j1, Joueur j2){
		Ecran.afficherln("\n\n *********** Fin de partie *********** ");
		Ecran.afficherln(" ********* Écran des scores ********** ");
		afficherJoueur(j1);
		Ecran.sautDeLigne();
		afficherJoueur(j2);
		Ecran.afficher("\n ************************************* \n");
		
		if(j1.gagnes<j2.gagnes){
			Ecran.afficher("   C'est ",j2.nom," qui a gagné.");
		} else if(j1.gagnes==j2.gagnes){	
			Ecran.afficher("Égalité parfaite entre ",j1.nom," et ",j2.nom,".");
		} else {
			Ecran.afficher("C'est ",j1.nom," qui a gagné.");
		}
		Ecran.afficherln(" Bravo !");	
	}
	
	
	/** Fonction générant un nombre aléatoire entre 0 et 1 
	@return Un nombre aléatoire entre 0 et 1 permettant de connaitre le joueur qui commence
	*/
	public static int tirageAuSort() {
		return((int)(Math.random()*2)) ;
	}
	
	 
	/** Fonction coup qui joue un coup avec les relances 
	
	@param tourJoueur : Le tour du joueur en cour 
	@param j1 : Type agrégé du joueur 1
	@param j2 : Type agrégé du joueur 2
	
	*/
	public static void coup(int tourJoueur, Joueur j1, Joueur j2) {
		
		Gobelet gobJ1 = new Gobelet();
		int pointsJ1;
		Gobelet gobJ2 = new Gobelet();
		int pointsJ2;
		
		int gagnant = 0;
		
		
		//Premier tour
		for(int i=0 ; i <2 ; i++){
			if(tourJoueur==1){
				Ecran.afficherln(j1.nom,", c'est votre tour.");
				gobJ1 = lancerGob();
				affichageCombinaison(j1,gobJ1);
				Ecran.sautDeLigne();
			} else {
				Ecran.afficherln(j2.nom,", c'est votre tour.");
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
		
		//Première et deuxième relance
		for(int j=1 ; j<3 ; j++){
			if(j==1){
				Ecran.afficher("Première relance \n");
			} else if(j==2){
				Ecran.afficher("Deuxième relance \n");
			}
			
			
			for(int i=0 ; i <2 ; i++){
				if(tourJoueur==1){
					Ecran.afficherln(j1.nom,", c'est votre tour. Vous avez pour l'instant ");
					affichageCombinaison(j1, gobJ1);
					gobJ1 = relanceGob(gobJ1);
					affichageCombinaison(j1, gobJ1);
					Ecran.sautDeLigne();
				} else {
					Ecran.afficherln(j2.nom,", c'est votre tour. Vous avez pour l'instant ");
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
		if(points(gobJ1)<points(gobJ2)){
			gagnant = 2;
		} else if(points(gobJ2)<points(gobJ1)){
			gagnant = 1;
		}
		
		//Incrémenter la victoire du gagnant
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
		Ecran.afficher("\n§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§\n");
	}
	
	
	
	
	
	/** Vérifie que le nombre de la relance est valide, si oui, donne quels dés il faut retourner 
	
	@param valeur : Valeur des dés entrée par l'utilisateur à vérifier
	@return Le type structuré permettant de savoir si la relance est possible ou non
	
	*/
	public static Relance estValideDe(int valeur){
		
		int de  = 0, i = 1, valeurSafe = valeur;
		
		Relance relance = new Relance();
		relance.estValide = true;
		
		if(valeur < 0){
			relance.estValide = false;
			Ecran.afficherln("Attention votre saisie est invalide !");
		}
		
		while(relance.estValide && valeur != 0){
			
			de = valeur % 10;
			
			if (de == 0 || de >= 6){
				relance.estValide = false;
				Ecran.afficherln("Vos dés doivent être compris entre 1 et 5 !");
			}
			
			valeur = valeur / 10;
			i++;
			
			if(i > 6){
				relance.estValide = false;
				
				Ecran.afficherln("Vous ne pouvez pas modifier plus de cinq dés !");
			}
		}
		
		while(valeurSafe != 0 && relance.estValide){
			
			de = valeurSafe % 10;
			
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
			if(!(relance.estValide)){
				Ecran.afficherln("Vous avez entré deux fois le même dé. ");
			}
			valeurSafe = valeurSafe/10;
		}
		
		return(relance);
	}
	
	
	/** Fonction pour relancer un gobelet 
	
	@param gob : Selection du gobelet pour la relance
	@return Le gobelet avec les modifications sur les différents dés
	
	*/
	public static Gobelet relanceGob(Gobelet gob){
	
		int valeur = 0;
		
		Ecran.afficherln("Quel(s) dé(s) voulez-vous relancer ?");
		valeur = Clavier.saisirInt();
		
		Relance relance = new Relance();
		relance = estValideDe(valeur);
		
		while(!(relance.estValide)){
			Ecran.afficherln("Quel(s) dé(s) voulez-vous relancer ?");
			valeur = Clavier.saisirInt();
			relance = estValideDe(valeur);
		}
		
		
		
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
	
	/**Continuer 
	
	@param j : Selection du joueur pour la demande de poursuite du programme
	@return Le type structuré joueur avec la valeur de poursuite du programme modifié
	
	*/
	public static Joueur continuer(Joueur j){
		Ecran.afficher(j.nom,", voulez-vous continuer la partie ? (Ecrivez 'n' pour arrêter, n'importe quoi pour continuer) : ");
		char touche=Clavier.saisirChar();
		if(touche=='n' || touche=='N'){
			j.continuer=false;
		}
		return(j);
		
	}
	/** 
	
	Action principale
	
	*/
	public static void main(String args[]) {
		/** Déclaration des joueurs et saisie des noms */
		
		Ecran.afficherln("¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤ \nPremier joueur : ");
		Joueur joueur1 = creerJoueur() ;
		Ecran.afficherln("Deuxième joueur :");
		Joueur joueur2 = creerJoueur() ;
		Ecran.afficherln("¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤");
		
		/** Déclaration des données */
		int tourJoueur ;
		int vainqueurTour ;
		
		
		/** Partie */
			/**Tirage au sort du premier joueur */
		tourJoueur = tirageAuSort() ;
		
		boolean continuer = true;
		do{
			/** Coup */

			coup(tourJoueur,joueur1,joueur2);
			
			//Changement de joueur qui commencera
			if(tourJoueur==1){
				joueur2 = continuer(joueur2);
				joueur1 = continuer(joueur1);
				tourJoueur=2;
			} else {
				tourJoueur=1;
				joueur1 = continuer(joueur1);
				joueur2 = continuer(joueur2);
			}
			
			
		
			
		}while(joueur2.continuer || joueur1.continuer) ;
		
		afficherScore(joueur1,joueur2);
	}
}