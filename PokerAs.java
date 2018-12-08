/**
* Le jeu du poker d'as (Poker dice) pour deux joueurs
* <br>Cr�ateurs : Lucas Cosson & Nathana�l Houn
* <br>Groupe C1 / CMI
*/
public class PokerAs {


	/** D�claration du nombre de faces des d�s utilis�s */
	public static final int FACES = 6 ;


	/** Type agr�g� Gobelet pour enregistrer 6 d�s et le nombre de points */
	public static class Gobelet {
		/** Le premier d� du gobelet*/
		int de1 ;
		/** Le deuxi�me d� du gobelet */ 
		int de2 ;
		/** Le troisi�me d� du gobelet */ 
		int de3 ;
		/** Le quatri�me d� du gobelet */ 
		int de4 ;
		/** Le cinqui�me d� du gobelet */ 
		int de5 ;
		/** Nombre de points correspondant � la combinaison la plus forte du gobelet */
		int points = 0 ;
	}

	/**
	*Cr�er et lancer de fa�on al�atoire un gobelet de 5 d�s
	*@return Le gobelet contenant 5 d�s � valeurs enti�res al�atoires comprises entre 1 et le nombre de FACES inclus
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


	/** Type agr�g� Joueur pour enregistrer le nom du joueur et le nombre de coups remport�s par le joueur */
	public static class Joueur {
		/** Nom du joueur */
		String nom ;
		/* Nombre de coups remport�s */
		int gagnes ;
	}

	/**
	*Cr�er un joueur, entrer son nom, et d�finir son nombre de coups remport�s � 0.
	*@return Le joueur avec son nom entr� et 0 victoire
	*/
	public static Joueur creerJoueur() {
		Joueur joueur = new Joueur();
		Ecran.afficher("Entrez le nom du joueur : ");
		joueur.nom = Clavier.saisirString();
		joueur.gagnes = 0 ;
		return(joueur);
	}

	/**
	*Afficher le nom du joueur et son nombre de coups remport�s
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


	/** Type agr�g� pour enregistrer si l'on peut relancer le gobelet et quels d�s du gobelet on veut relance*/
	public static class Relance {
		/** La relance demand�e est-elle valide ? */
		boolean estValide ;
		/** Faut-il relancer le d� 1 ? */
		boolean de1 ;
		/** Faut-il relancer le d� 2 ? */
		boolean de2 ;
		/** Faut-il relancer le d� 3 ? */
		boolean de3 ;
		/** Faut-il relancer le d� 4 ? */
		boolean de4 ;
		/** Faut-il relancer le d� 5 ? */
		boolean de5 ;
	}

	/**
	* Afficher le type Relance (fonction utilis�e lors du d�veloppement)
	* @param relance  type Relance que l'on veut afficher
	*/
	public static void afficherRelance(Relance relance){
		Ecran.afficher('(',relance.estValide,' ',relance.de1,' ',relance.de2,' ',relance.de3,' ',relance.de4,' ',relance.de5,')');
	}


	/**
	* Lancer de fa�on al�atoire un d� � FACES nombre de faces
	* @return Un entier compris entre 1 et le nombre de faces du d� (inclus)
	*/
	public static int aleatoire(){
		return((int)(Math.random()*FACES)+1);
	}

	/**
	*Tester si le gobelet contient une valeur test�e
	*@param gob  Gobelet que l'on veut tester
	*@param i  entier dont on �tudie la pr�sence ou l'absence dans le gobelet
	*@return true si gob contient l'entier, false sinon
	*/
	public static boolean GobeletAValeur(Gobelet gob, int i){
		return(gob.de1 == i || gob.de2 == i || gob.de3 == i || gob.de4 == i || gob.de5 == i);
	}


	/**
	*Fonction qui retourne le nombre de d� identique � une valeur test�e
	*@param gob  Gobelet que l'on veut tester
	*@param i  entier dont on �tudie le nombre de pr�sence dans le gobelet
	*@return Le nombre de d�s contenus dans le gobelet qui ont la valeur i
	*/
	public static int compteurDeIdentique(Gobelet gob, int i){
		//D�claration de la variable
		int nbDe = 0 ;
		
		//Comptage du nombre de d�s identiques
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
	*Teste si le gobelet contient un full, si oui, renvoie le nombre de points associ�
	*@param gob  Gobelet que l'on teste
	*@return 4 s'il y a un full, 0 sinon
	*/
	public static int pointsFull(Gobelet gob){
		//D�claration des variables
		int compteurDeIdentique1 = 0, compteurDeIdentique2 = 0;
		boolean paire=false, brelan= false;

		//Test de la pr�sence d'un brelan et d'une paire
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
	*Teste si le gobelet contient une double paire, si oui, renvoie le nombre de points associ�
	*@param gob  Gobelet que l'on teste
	*@return 2 s'il y a une double paire, 0 sinon
	*/
	public static int pointsDoublePaire(Gobelet gob){
		//D�claration des variables
		int compteurDeIdentique1 = 0, compteurDeIdentique2 = 0;
		boolean paire1 = false, paire2= false;

		//Test de la pr�sence de deux paires
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
	*Teste si le gobelet contient une petite suite, si oui, renvoie le nombre de points associ�
	*@param gob  Gobelet que l'on teste
	*@return 7 s'il y a une petite suite, 0 sinon
	*/
	public static int pointsPetiteSuite(Gobelet gob) {
		//D�claration des variables
		boolean a3et4 = true ;
		boolean a1et2 = false , a2et5 = false , a5et6 = false ;

		//D�tection de la pr�sence de 3 et 4
		for(int i=3 ;  i<=4 ; i++){
			if(!GobeletAValeur(gob,i)) {
				a3et4 = false ;
			}
		}

		//D�tection des autres valeurs
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
	*Teste si le gobelet contient une grande suite, si oui, renvoie le nombre de points associ�
	*@param gob  Gobelet que l'on teste
	*@return 8 s'il y a une grande suite, 0 sinon
	*/
	public static int pointsGrandeSuite(Gobelet gob) {
		//D�claration des variables
		boolean aDe2a5 = true ;
		boolean a1ou6 = false ;

		//D�tection de la pr�sence de 2,3,4 et 5
		for(int i=2 ;  i<=5 ; i++){
			if(!GobeletAValeur(gob,i)) {
				aDe2a5 = false ;
			}
		}

		//D�tection de la pr�sence de 1 ou 6
		a1ou6 = (GobeletAValeur(gob,1) || GobeletAValeur(gob,6));

		//Enregistrement du nombre de points
		int points =0 ;
		if(aDe2a5 && a1ou6)
			points = 7 ;

		return(points);
	}


	/**
	*Teste si le gobelet contient une paire, un brelan, un carr� ou un poker, si oui, renvoie le nombre de points associ�
	*@param gob  Gobelet que l'on teste
	*@return 1 s'il y a une paire, 3 s'il y a un brelan, 4 s'il y a un carr�, 8 s'il y a un poker, 0 sinon
	*/
	public static int pointsPaireBrelanCarrePoker(Gobelet gob){
		//D�claration des variables	
		int compteurDeIdentique = 0, resultat = 0, resultatProvisoire = 0;

		//D�tection du nombre de d�s identiques et affectation du nombre de point
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

			//On prends le r�sultat le plus haut
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
	*@param j  Type agr�g� Joueur du joueur qui a obtenu ce gobelet
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
				Ecran.afficher(" - Carr�");
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
	*Affiche le score des deux joueurs puis le nom du gagnant ou l'�galit�.
	*@param j1  Type agr�g� du joueur 1
	*@param j2  Type agr�g� du joueur 2
	*/
	public static void afficherScore(Joueur j1, Joueur j2){
		Ecran.afficherln("\n\n\n *********** Fin de partie *********** ");
		Ecran.afficherln(" ********* �cran des scores ********** ");
		afficherJoueur(j1);
		Ecran.sautDeLigne();
		afficherJoueur(j2);
		Ecran.afficher("\n ************************************* \n");

		//Affichage du gagnant
		if(j1.gagnes<j2.gagnes){
			Ecran.afficher("   C'est ",j2.nom," qui a gagn�.");
		} else if(j1.gagnes==j2.gagnes){
			Ecran.afficher("�galit� parfaite entre ",j1.nom," et ",j2.nom,".");
		} else {
			Ecran.afficher("	C'est ",j1.nom," qui a gagn�.");
		}
		Ecran.afficherln(" Bravo ! \n \n \n");
	}


	/** 
	*G�n�re un nombre al�atoire entre 0 et 1
	*@return Un nombre al�atoire entre 0 et 1 permettant de savoir quel joueur commence
	*/
	public static int TirageAuSort() {
		return((int)(Math.random()*2)) ;
	}


	/**
	*Jouer un coup avec les relances
	*@param tourJoueur Le tour du joueur qui commence le coup
	*@param j1  Type agr�g� du joueur 1
	*@param j2  Type agr�g� du joueur 2
	*/
	public static void coup(int tourJoueur, Joueur j1, Joueur j2) {
		//D�claration des variables
		Gobelet gobJ1 = new Gobelet();
		int pointsJ1;
		Gobelet gobJ2 = new Gobelet();
		int pointsJ2;
		int gagnant = 0;

		//Premier tour pour les deux joueurs
		for(int i=0 ; i <2 ; i++){
			if(tourJoueur==1){
				Ecran.afficherln(j1.nom,", premier lanc� de d�s.");
				gobJ1 = lancerGob();
				affichageCombinaison(j1,gobJ1);
				Ecran.sautDeLigne();
			} else {
				Ecran.afficherln(j2.nom,", premier lanc� de d�s.");
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

		//Premi�re et deuxi�me relance pour les deux joueurs
		for(int j=1 ; j<3 ; j++){
			if(j==1){
				Ecran.afficher(" ���� Premi�re relance ����\n\n");
			} else if(j==2){
				Ecran.afficher(" ���� Deuxi�me relance ����\n\n");
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

		//Afficher le gagnant et incr�menter la victoire du gagnant
		Ecran.sautDeLigne();
		if(gagnant==1){
			Ecran.afficher(j1.nom," a remport� le coup. ");
			j1.gagnes++ ;
		} else if(gagnant==2){
			Ecran.afficher(j2.nom," a remport� le coup. ");
			j2.gagnes++ ;
		} else {
			Ecran.afficher("�galit� entre ",j1.nom," et ",j2.nom,". ");
		}
		Ecran.afficher("\n------------------------------------------- \n");
	}


	/**
	*V�rifie que le nombre de la relance est valide, si oui, donne quels d�s il faut relancer
	*@param valeur Valeur entr�e par l'utilisateur � v�rifier
	*@return Le type structur� permettant de savoir si la relance est possible ou non et quels d�s il faut relancer
	*/
	public static Relance estValideDe(int valeur){
		//D�claration des donn�es
		int de  = 0, i = 1, valeurSafe = valeur;
		Relance relance = new Relance();
		relance.estValide = true;

		//Saisie n�gative
		if(valeur < 0){
			relance.estValide = false;
			Ecran.afficherln("Attention votre saisie est invalide !");
		}

		while(relance.estValide && valeur != 0){
			
			de = valeur % 10;

			//Saisie non comprises entre 1 et 5 
			if (de == 0 || de >= 6){
				relance.estValide = false;
				Ecran.afficherln("Vos d�s doivent �tre compris entre 1 et 5 !");
			}

			valeur = valeur / 10;
			i++;

			//Saisie sup�rieure � 5 d�s
			if(i > 6){
				relance.estValide = false;

				Ecran.afficherln("Vous ne pouvez pas modifier plus de cinq d�s !");
			}
		}

		while(valeurSafe != 0 && relance.estValide){

			de = valeurSafe % 10;

			//Quels d�s faut-il relancer ?
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

			//M�me d� rentr� deux fois
			if(!(relance.estValide)){
				Ecran.afficherln("Vous avez entr� deux fois le m�me d�. ");
			}
			valeurSafe = valeurSafe/10;
		}

		return(relance);
	}


	/** Relance un gobelet avec demande de la relance
	*@param gob  S�lection du gobelet que l'on veut relancer
	*@return Le gobelet avec les modifications sur les diff�rents d�s
	*/
	public static Gobelet relanceGob(Gobelet gob){
		//D�claration de la variable
		int valeur = 0;
		Relance relance = new Relance();

		//Demande de la relance
		Ecran.afficher("Quel(s) d�(s) voulez-vous relancer (0 pour aucun) ? ");
		valeur = Clavier.saisirInt();
		relance = estValideDe(valeur);

		while(!(relance.estValide)){
			Ecran.afficher("Quel(s) d�(s) voulez-vous relancer (0 pour aucun) ? ");
			valeur = Clavier.saisirInt();
			relance = estValideDe(valeur);
		}

		//Relance des d�s
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
	*@return 'true' si la partie doit �tre continu�e, 'false' sinon
	*/
	public static boolean continuer(){
		boolean continuer = true ;
		Ecran.afficher("Voulez-vous continuer la partie ? (Ecrivez 'n' pour arr�ter, n'importe quoi pour continuer)  ");
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
		
		//D�claration des joueurs et saisie des noms
		Ecran.afficher("   ���������������������������� \nPremier joueur - ");
		Joueur joueur1 = creerJoueur() ;
		Ecran.afficher("Deuxi�me joueur - ");
		Joueur joueur2 = creerJoueur() ;
		Ecran.afficherln("   ���������������������������� \n \n");

		//D�claration des variables
		int tourJoueur ;
		int vainqueurTour ;

		//Affichage des r�gles du jeu
		Ecran.afficherln(" ***** Bienvenue dans le Poker d'As *****");
		Ecran.afficherln("Le but est d'effectuer, par lancer al�atoire de d�, la plus grande combinaison parmi(ordre croissant) : \n - Paire \n - Double paire \n - Brelan \n - Carr� \n - Petite suite (4 d�s qui se suivent) \n - Grande suite (5 d�s)\n - Poker");
		Ecran.afficher("Chacun votre tour, vous aurez le droit de relancer deux fois les d�s de votre choix parmi vos cinq d�s afin de faire le plus forte combinaison possible. ");
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
