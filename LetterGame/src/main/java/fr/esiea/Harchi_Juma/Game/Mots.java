package fr.esiea.Harchi_Juma.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.esiea.Harchi_Juma.Game.Core.IA;
import fr.esiea.Harchi_Juma.Game.Core.Lettre;

public class Mots {

	
	
	public Mots(){}

	//Normalise les mots
	public String removeAccent(String source) {
		return Normalizer.normalize(source, Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", "");
	}
	
	//Tableau des scores
	public List<String> playerListScoreWord(Joueur[] tabPlayer){
		List<String> tabScoreWord = new ArrayList<String>();
		for(int i=0; i<tabPlayer.length; i++){
			for(int j=0; j<tabPlayer[i].listWord.size(); j++){
				tabScoreWord.add(tabPlayer[i].listWord.get(j));
				System.out.println(tabScoreWord.get(j));
			}
			System.out.println("\n");
		}
		return tabScoreWord;
	}

	//retourne le mot volé
	public int whichWordStolen(Joueur joueur, String sameWord){
		int idWord = 20;
		for(int i = 0; i < joueur.listWord.size(); i++){
			if(joueur.listWord.get(i).equals(sameWord)){
				idWord = i;
			}
		}
		return idWord;
	}

	//retourne le joueur volé
	public Joueur whoIsStolen(Joueur[] tabPlayer, String namePlayer){
		Joueur target = null;
		for(int i = 0; i < tabPlayer.length; i++){
			if(namePlayer.equals(tabPlayer[i].getName())){
				target  = tabPlayer[i];
			}
		}
		return target;
	}

	//dérobe un mot déjà présent
	public void stealingWord(Joueur[] tabPlayer, PotCommun pot, Joueur thief){
		System.out.println("A qui voulez vous voler un mot ?");
		Scanner scan = new Scanner(System.in);
		String namePlayer = scan.next();
		Joueur target = new Joueur();
		target = whoIsStolen(tabPlayer, namePlayer);
		ArrayList<Character> tmpPot = new ArrayList<Character>(pot.getPotCommun().size());
		tmpPot.addAll(pot.getPotCommun());
		
		if(target == null){
			System.out.println("Le joueur "+namePlayer+" n'existe pas");
		}
		else{
			whichWordToSteal(scan,target,pot,tmpPot,thief);
		}
	}
	
	//Indication sur le mot dérobé
	public void whichWordToSteal(Scanner sc, Joueur target,PotCommun pot, ArrayList<Character> tmpPot, Joueur thief){
		System.out.println("Quel mot voulez vous voler ?");
		String wordToSteal = sc.next();
		wordToSteal = wordToSteal.toUpperCase();
		int idWordToSteal = whichWordStolen(target, wordToSteal);
		
		if(idWordToSteal == 20){
			System.out.println("Ce joueur n'a pas le mot "+wordToSteal);
		}
		else{
			newWord(sc,wordToSteal,pot,tmpPot,target,thief,idWordToSteal);
		}
	}
	
	//valide le nouveau mot 
	public void newWord(Scanner scan, String wordToSteal,PotCommun pot, ArrayList<Character> tmpPot, Joueur target, Joueur thief, int idWordToSteal){
		Scanner file;
		System.out.println("Entrez votre nouveau mot à partir du mot "+wordToSteal+" :");
		String newWord = scan.next();
		newWord = newWord.toUpperCase();
		
		if(newWord.contains(wordToSteal) && newWord.length() > wordToSteal.length()){
			try {

				File currentDirFile = new File("src/main/resources/dico.txt");
		    	String helper = currentDirFile.getAbsolutePath();
				file = new Scanner(new File(helper));
				searchInDico(file,newWord,wordToSteal,pot,tmpPot,target,thief,idWordToSteal);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("Le mot n'est pas valide");
		}
	}
	
	//Vérifie si le mot est conforme
	public void searchInDico(Scanner file, String newWord, String wordToSteal,PotCommun pot, ArrayList<Character> tmpPot, Joueur target, Joueur thief, int idWordToSteal){
		int val =0;
		while(file.hasNextLine()){
			String line = file.nextLine().toUpperCase();
			line = removeAccent(line);
			int size = line.length();
			if(line.indexOf(newWord.toUpperCase()) != -1 && newWord.length() == size ){
				String diff = newWord.replace(wordToSteal, "");
				boolean supprPotCommun = pot.verifLettrePotCommun(diff, tmpPot);
				val =1;
				
				if(supprPotCommun == true) succeedTheft(pot,tmpPot,target, thief,newWord,idWordToSteal);		
			}else if(!file.hasNextLine()){
				if(val == 0) System.out.println("Ce mot n'existe pas");
			}
		}
	}
	
	//Mise a jour des deux joueurs après vol
	public void succeedTheft(PotCommun pot, ArrayList<Character> tmpPot, Joueur target, Joueur thief, String newWord, int idWordToSteal ){
		System.out.println("Vol : ajout du mot dans la liste");
		pot.getPotCommun().removeAll(pot.getPotCommun());
		pot.getPotCommun().addAll(tmpPot);
		target.listWord.remove(idWordToSteal); 
		target.score--;
		thief.score++;
		thief.listWord.add(newWord);
		System.out.println(thief.listWord);
	}
	
	//vérifie si le mot est valide
	public void findWord(int i, Scanner scan, PotCommun potCommun, Joueur[] tabPlayer, Lettre lettre){
		String word = "";
		int val = 0;
	    Scanner file;
	    try {
	    	File currentDirFile = new File("src/main/resources/dico.txt");
	    	String helper = currentDirFile.getAbsolutePath();
				file = new Scanner(new File(helper));
				    word = scan.next();
				    word = removeAccent(word);
				    searchInDicoBasic(file,word,potCommun,i,tabPlayer,val,lettre);
				
				}			            
			*/} catch (FileNotFoundException e) {
				e.printStackTrace();
			}			
	}
	
	//Méthode qui permet de parcourir le dico
	public void searchInDicoBasic(Scanner file, String word, PotCommun potCommun, int i, Joueur[] tabPlayer, int val,Lettre lettre){
		while(file.hasNextLine()){
			String line = file.nextLine().toUpperCase();
			line = removeAccent(line);
			int size = line.length();
			if(line.indexOf(word.toUpperCase()) != -1 && word.length() == size && potCommun.verifLettrePotCommun(line,potCommun.getPotCommun()) == true){						
				statPlayer(i,tabPlayer,line);
	            val++;  
	            
	            if(tabPlayer[i].getScore() < 5)lettre.oneDraw(tabPlayer[i], potCommun);

	            if(tabPlayer[i].getScore() < 5)lettre.oneDraw(tabPlayer[i], potCommun);
	        }
	     }
	        if(val == 0) System.out.println("Le mot n'est pas valide");
	}
	

	
	//Mise à jour du joueur (score, liste de mot)
	public void statPlayer(int i, Joueur[] tabPlayer, String line){
		tabPlayer[i].score++;
		tabPlayer[i].setListWord(tabPlayer[i].listWord);
		tabPlayer[i].listWord.add(line);
		System.out.println("Score de "+tabPlayer[i].name+" : "+tabPlayer[i].score);
	}
	
}
