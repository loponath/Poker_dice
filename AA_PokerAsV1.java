public class AA_PokerAsV1 {
	/**
	* Le jeu du poker d'as pour deux personnes 
	*/
	public static void main(String args[]) {
		/** D�claration des joueurs et saisie des noms */
		Joueur joueur1 = creerJoueur() ;
		Joueur joueur2 = creerJoueur() ;
		
		/** D�claration des donn�es */
		int tourJoueur ;
		
		
		/** Partie */
			/**Tirage au sort du premier joueur */
		tourJoueur = tirageAuSort() ;
			
		do {
			/** Coup */
			coup(tourJoueur);
			
			
			
			/** Changement de joueur */
			if(tourJoueur==0) {
				tourJoueur = 1 ;
			} else {
				tourJoueur = 0 ;
			}
			
		} while(continuer()) ;
		afficherScore(joueur1,joueur2);
	}
}