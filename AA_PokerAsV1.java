public class AA_PokerAsV1 {
	/**
	* Le jeu du poker d'as pour deux personnes 
	*/
	
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
			Ecran.afficher("s. ");
		} else {
			Ecran.afficher(". ");
		}
	}

	/** Fonction affichant le score des deux joueurs puis le gagnant ou l'égalité. */
	public static void afficherScore(Joueur j1, Joueur j2){
		afficherJoueur(j1);
		afficherJoueur(j2);
		Ecran.sautDeLigne();

		if(j1.gagnes<j2.gagnes){
			Ecran.afficher("C'est ",j2.nom," qui a gagné.");
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
	
	
	public static void main(String args[]) {
		/** Déclaration des joueurs et saisie des noms */
		Ecran.afficherln("Premier joueur : ");
		Joueur joueur1 = creerJoueur() ;
		Ecran.afficherln("Deuxième joueur :");
		Joueur joueur2 = creerJoueur() ;
		
		/** Déclaration des données */
		int tourJoueur ;
		
		
		/** Partie */
			/**Tirage au sort du premier joueur */
		tourJoueur = tirageAuSort() ;
			
		//do {
			/** Coup */
			//coup(tourJoueur);
			
			
			
			/** Changement de joueur */
		//	if(tourJoueur==0) {
		//		tourJoueur = 1 ;
		//	} else {
		//		tourJoueur = 0 ;
		//	}
			
		//} while(continuer()) ;
		afficherScore(joueur1,joueur2);
	}
}