public class AA_PokerAsV1 {
	/**
	Le jeu du poker d'as pour deux personnes 
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
	
	/** Afficher le type Relance */
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
	
	/** Teste si le gobelet contient un full, si oui, renvoie le nombre de points associé */
	public static int pointsFull(Gobelet gob){
		
		int compteurDeIdentique1 = 0, compteurDeIdentique2 = 0;
		boolean paire1 = false, paire2= false, brelan1= false, brelan2= false;
		for(int i = 1; i <= 6 ; i++){
			compteurDeIdentique1 = 0;
			
			if(i == gob.de1){
				compteurDeIdentique1++;
			}
			if(i == gob.de2){
				compteurDeIdentique1++;
			}
			if(i == gob.de3){
				compteurDeIdentique1++;
			}
			if(i == gob.de4){
				compteurDeIdentique1++;
			}
			if(i == gob.de5){
				compteurDeIdentique1++;
			}
			
			if(compteurDeIdentique1==3){
				brelan1=true ;
			} else if(compteurDeIdentique1==2){
				paire1=true;
			}
		}
		for(int i = 1; i <= 6 ; i++){
			compteurDeIdentique2 = 0;
			
			if(i == gob.de1){
				compteurDeIdentique2++;
			}
			if(i == gob.de2){
				compteurDeIdentique2++;
			}
			if(i == gob.de3){
				compteurDeIdentique2++;
			}
			if(i == gob.de4){
				compteurDeIdentique2++;
			}
			if(i == gob.de5){
				compteurDeIdentique2++;
			}
			if(compteurDeIdentique2==3){
				brelan2=true ;
			} else if(compteurDeIdentique2==2){
				paire2=true;
			}
		}
		
		int points = 0;
		
		if((paire1 && brelan2) || (paire2 && brelan1))
			points= 4;
		
		return points;
		
	}
	/** Teste si le gobelet contient une petite suite, et si oui, renvoie le nombre de point associé */
	public static int pointsPetiteSuite(Gobelet gob) {
		boolean a3et4 = true ;
		boolean a1et2 = false , a2et5 = false , a5et6 = false ;

		for(int i=2 ;  i<=4 ; i++){
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
				compteurDeIdentique = 0;
				
				
				if(i == gob.de1){
					compteurDeIdentique++;
				}
				if(i == gob.de2){
					compteurDeIdentique++;
				}
				if(i == gob.de3){
					compteurDeIdentique++;
				}
				if(i == gob.de4){
					compteurDeIdentique++;
				}
				if(i == gob.de5){
					compteurDeIdentique++;
				}
				
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
		int points;
		points = Math.max(pointsPaireBrelanCarrePoker(gob),pointsGrandeSuite(gob));
		points = Math.max(points,pointsPetiteSuite(gob));
		points = Math.max(points,pointsFull(gob));
		return(points);
	}

	/**Afficher un gobelet et la combinaison la plus forte qu'il contient */
	public static void affichageCombinaison(Gobelet gob){
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
	}
	
	/** Fonction pour entrer le nom du joueur, et définir son nombre de coups remportés à 0. */
	public static Joueur creerJoueur() {
		Joueur joueur = new Joueur();
		Ecran.afficher("Entrez le nom du joueur : ");
		joueur.nom = Clavier.saisirString();
		joueur.gagnes = 0 ;
		return(joueur);
	}
	
	/** Fonction pour afficher le nom du joueur et son nombre de coups remportés */
	public static void afficherJoueur(Joueur jou){
		Ecran.afficher(jou.nom,", ",jou.gagnes," victoire");
		
		if(jou.gagnes<2){
			Ecran.afficher(". ");
		} else {
			Ecran.afficher("s. ");
		}
	}

	/** Fonction affichant le score des deux joueurs puis le gagnant ou l'égalité. */
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
	
	
	/** Fonction générant un nombre aléatoire entre 0 et 1 */
	public static int tirageAuSort() {
		return((int)(Math.random()*2)) ;
	}
	
	 
	/* Fonction coup qui joue un coup avec les relances */
	public static int coup(int tourJoueur, Joueur j1, Joueur j2) {
		
		Gobelet gobJ1 = new Gobelet();
		int pointsJ1;
		Gobelet gobJ2 = new Gobelet();
		int pointsJ2;
		
		//Premier tour
		for(int i=0 ; i <2 ; i++){
			if(tourJoueur==1){
				Ecran.afficherln(j1.nom,", c'est votre tour.");
				gobJ1 = lancerGob();
				affichageCombinaison(gobJ1);
				
				
			} else {
				Ecran.afficherln(j2.nom,", c'est votre tour.");
				gobJ2 = lancerGob();
				affichageCombinaison(gobJ2);
			}
			
			//Changement de tour
			if(tourJoueur==1) {
				tourJoueur = 2 ;
			} else {
				tourJoueur = 1 ;
			}
		}
		
		//Second tour (première relance)
		for(int i=0 ; i <2 ; i++){
			if(tourJoueur==1){
				Ecran.afficherln(j1.nom,", c'est votre tour.");
				gobJ1 = relanceGob(gobJ1);
				affichageCombinaison(gobJ1);
				
				
			} else {
				Ecran.afficherln(j2.nom,", c'est votre tour.");
				gobJ2 = relanceGob(gobJ2);
				affichageCombinaison(gobJ2);
			}
			
			//Changement de tour
			if(tourJoueur==1) {
				tourJoueur = 2 ;
			} else {
				tourJoueur = 1 ;
			}
		}
		
		return(2);
	}
	
	
	/** Fonction qui incrémente le nombre de victoire du joueur gagnant */
	public static void incrementeGagnant(Joueur j1, Joueur j2, int gagnant){
		if(gagnant==1){
			j1.gagnes++ ;
		} else if(gagnant==2){
			j2.gagnes++ ;
		}
	}
	
	
	/** Vérifie que le nombre de la relance est valide, si oui, donne quels dés il faut retourner */
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
			
			if(i > 5){
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
	
	
	/** Fonction pour relancer un gobelet */
	public static Gobelet relanceGob(Gobelet gob){
	
		int valeur = 0;
		
		Ecran.afficherln("Quel dé voulez vous relancez ?");
		valeur = Clavier.saisirInt();
		
		Relance relance = new Relance();
		relance = estValideDe(valeur);
		
		while(!(relance.estValide)){
			Ecran.afficherln("Quel dé voulez vous relancez ?");
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
			
		//do 
			/** Coup */
		vainqueurTour = coup(tourJoueur,joueur1,joueur2);
		incrementeGagnant(joueur1,joueur2,vainqueurTour);
		
		
		
			
		// while(continuer()) ;
		afficherScore(joueur1,joueur2);
	}
}