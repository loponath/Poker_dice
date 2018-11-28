public class Coup{
	
	static final int FACES = 6 ;
	
	/** Classe Gobelet qui contient 6 dés à valeurs entières */
	public static class Gobelet {
		int de1 ;
		int de2 ;
		int de3 ;
		int de4 ;
		int de5 ;
	}
	
	public static class Relance {
		boolean estValide ;
		boolean de1 ;
		boolean de2 ;
		boolean de3 ;
		boolean de4 ;
		boolean de5 ;
	}
	
/** Afficher le contenu d'un gobelet */
	public static void afficherGob(Gobelet gob){
		Ecran.afficher('(',gob.de1,' ',gob.de2,' ',gob.de3,' ',gob.de4,' ',gob.de5,')');
	}
	
	/** Lancer aléatoire d'un dé à FACES nombre de FACE */
	public static int aleatoire(){
		return((int)(Math.random()*FACES)+1);
		
	}
	
	/** Lancer aléatoire d'un gobelet de 6 dés */
	public static Gobelet lancerGob(){
		
		Gobelet gob = new Gobelet();
		
		gob.de1 = aleatoire() ;
		gob.de2 = aleatoire() ;
		gob.de3 = aleatoire() ;
		gob.de4 = aleatoire() ;
		gob.de5 = aleatoire() ;
		return(gob) ;
	}
	
	public static class Relance {
		boolean estValide ;
		boolean de1 ;
		boolean de2 ;
		boolean de3 ;
		boolean de4 ;
		boolean de5 ;
	}/** Afficher le contenu d'un gobelet */
	public static void afficherGob(Gobelet gob){
		Ecran.afficher('(',gob.de1,' ',gob.de2,' ',gob.de3,' ',gob.de4,' ',gob.de5,')');
	}
	
	public static void afficherRelance(Relance relance){
		Ecran.afficher('(',relance.estValide,' ',relance.de1,' ',relance.de2,' ',relance.de3,' ',relance.de4,' ',relance.de5,')');
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
	public static void affichageCombinaison(Gobelet gob, int point){
		afficherGob(gob);
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
	

	
	/** Un coup */
	public static void main(String args[]) {
		Gobelet gobelet = new Gobelet();
		int points;
		
		gobelet = lancerGob();
		
		affichageCombinaison(gobelet, points(gobelet));
		
		relanceDe(gobelet);
		
		affichageCombinaison(gobelet, points(gobelet));

	}
	
}


	