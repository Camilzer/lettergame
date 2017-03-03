package fr.esiea.Harchi_Juma.Game.Core;

import java.util.Scanner;

import fr.esiea.Harchi_Juma.Game.Alphabet;
import fr.esiea.Harchi_Juma.Game.PotCommun;
import fr.esiea.Harchi_Juma.Game.Joueur;
import fr.esiea.Harchi_Juma.Game.Mots;
import fr.esiea.Harchi_Juma.Game.Interface.IGame;

public class Game implements IGame {
	//variable
	int keyEnter, gameMode;
	int numberPlayer = 0;
	//IA ia = new IA();
	Joueur[] tabPlayer = new Joueur[numberPlayer];
	String[] playerName = new String[numberPlayer];
	//Singleton
	PotCommun potCommun = PotCommun.getInstancePotCommun();
	Lettre lettre = new Lettre();
	Mots mots = new Mots();
	Scanner scan = new Scanner(System.in);
	//Methode qui permet de lancer le jeu
	public Game(){
		Alphabet[] alphabet = Alphabet.values();
		startGame(alphabet);
	}
	//Recupere un mot ecris au clavier (nom du joueur)
	public String getString(){
		return scan.next();
	}
	//Permet a l'utilisateur d'entrer le nombre de joueur pour la partie
	public int setNbPlayer(){
		do{
			System.out.println("1) Joueur vs Joueur");
			//System.out.println("2) Joueur vs IA");
			if(scan.hasNextInt()) this.gameMode = scan.nextInt();
			/* gameMode c'est le mode de la Partie (1) Joueur vs Joueur ou 2) Joueur vs IA) */
			else {
	        	System.out.println("La valeur saisie n'est pas un entier!");
	            scan.next();
	            continue;
	        }
			if(gameMode == 1){
				System.out.println("Veuillez entrer le nombre de joueurs, minimum 2 : ");
				if (scan.hasNextInt()) this.numberPlayer = scan.nextInt();
		        else {
		        	System.out.println("La valeur saisie n'est pas un entier!");
		            scan.next();
		            continue;
		        }
			}
		this.tabPlayer = new Joueur[this.numberPlayer];
		}while(numberPlayer < 2 || (gameMode != 1) );
		
		return gameMode;
	}
	

	//Initialise le tableau de joueur avec des joueurs "null"
	public void initTabPlayer(int gameMode){
		for(int i = 0; i<this.numberPlayer; i++){
			this.tabPlayer[i] = new Joueur();
		//on attribue d'office la valeur 1 pour l'IA dans le cas ou on est dans le mode Joueur vs IA histoire de les différencier
//			if(gameMode == 2 && i == 1)this.tabPlayer[i].setIA(1);
			this.tabPlayer[i].listWord.remove("null");
		}
	}
	
	//Initialise le pot commun
	public void initPotCommun(){
		this.potCommun.getPotCommun().remove(null);
	}
	
	//Recupere le nom de chaque joueur et stocke dans un tableau
	public void setNameEnter(){
		int j = 0;
		int i = 0;
		j = setNbPlayer();
		initTabPlayer(j);
		initPotCommun();
		
		//Design Pattern : iterator
		for(Joueur iterator : this.tabPlayer){
			if(j == 2){

				System.out.println("Entrez votre nom : ");
				iterator.setName(getString());
			}
			else{
				//Ajout du numéro des joueurs (esthétisme)
				i++;
				System.out.println("Entrez le nom du Joueur "+i+" :" );
				iterator.setName(getString());
			}
		}
	}
	
	//Affiche les mots et score des joueurs
	public void printWordPlayer(Joueur[] tabPlayer){
		for(Joueur i : this.tabPlayer){
			System.out.println(""+i.getName()+"score : "+i.getScore()+" : "+i.getListWord());
		}
		System.out.println("\n");
	}
	

	//Regroupement des fonctions pour lancer le jeu
	public void startGame(Alphabet[] alphabet){
		setNameEnter();
		lettre.firstDraw(this.tabPlayer, alphabet, potCommun);
		whoStart(this.tabPlayer);
		potCommun.printPotCommun();
		turnPlayer(this.tabPlayer);
	}
	
	//Affiche quel est le joueur qui commence
	public Joueur whoStart(Joueur[] tabPlayer){
		tabPlayer = sortArray(tabPlayer);
		tabPlayer[0].setPlay(true);
		System.out.println(tabPlayer[0].getName()+" obtient la premiere lettre.");
		return tabPlayer[0];
	}
	
	//Permet au joueur de jouer 
	public void turnPlayer(Joueur[] tabPlayer){	
		for(int i = 0; i < tabPlayer.length; i++){
			if(tabPlayer[i].getPlay() == true && tabPlayer[i].getScore() <5){
				System.out.println("C'est le tour de"+tabPlayer[i].getName());
				lettre.playerStarterDraw(tabPlayer[i], potCommun);
				choiceAction(i);
			}
		}
	}	
	
	// Le joueur passe son tour
	public void passTurn(int idPlayer, Joueur[] tabPlayer){
		if(tabPlayer.length-1 == idPlayer){
			tabPlayer[idPlayer].setPlay(false);
			tabPlayer[0].setPlay(true);
		}
		else{
			tabPlayer[idPlayer].setPlay(false);
			tabPlayer[idPlayer+1].setPlay(true);
		}
	}
	
	//Affiche les différentes possibilités du menu
	public void printMenu(){
		System.out.println("1 - Taper un mot avec les lettres "+potCommun.getPotCommun());
		System.out.println("2 - Passer son tour");
		System.out.println("3 - Voler un mot");
	}
	
	
	//Différentes actions en fonction du choix de l'utilisateur
	public int choiceAction(int i){
		int choice = 0;
		do{
	
			printWordPlayer(this.tabPlayer);
			printMenu();
			if(/*ia.iaMode == 0 &&*/ scan.hasNextInt())choice = scan.nextInt();
			else{
	        	System.out.println("La valeur saisie n'est pas un entier!");
	            scan.next();
	            continue;
	        }

			switch(choice){
			case 1:
				System.out.println("Taper un mot : ");
				mots.findWord(i, scan, potCommun,tabPlayer, lettre);
				break;
			case 2: 
				passTurn(i,tabPlayer);
				turnPlayer(tabPlayer);	
				return choice;
			case 3: 
				boolean playerPlaying = tabPlayer[i].getPlay();
				Joueur joueur = new Joueur();
				
				for(int j=0; j < tabPlayer.length; j++){
					if(playerPlaying == true){
						joueur =tabPlayer[i];
					}
				}
				mots.stealingWord(tabPlayer, potCommun, joueur);
				break;
				}
				
		}while((choice != 1 || choice != 3) && this.tabPlayer[i].getScore() <5);
		printWordPlayer(this.tabPlayer);
		System.out.println("Terminé. Victoire de "+tabPlayer[i].getName()+" !");
		return choice;
	}

	//Tri permettant de définir la lettre la moins importante
	public Joueur[] sortArray(Joueur[] tabPlayer){
		int i,j;
		Joueur flag;
		
		for(i=0; i< tabPlayer.length; i++){
			for(j=1; j < tabPlayer.length; j++){
				if(tabPlayer[j-1].getVal() > tabPlayer[j].getVal() ){
					flag = tabPlayer[j];
					tabPlayer[j] = tabPlayer[j-1];
					tabPlayer[j-1] = flag;
				}
			}
		}
		return tabPlayer;
	}

	
}
