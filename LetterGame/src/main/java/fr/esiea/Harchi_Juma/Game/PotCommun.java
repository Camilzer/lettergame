package fr.esiea.Harchi_Juma.Game;

import java.util.ArrayList;

public class PotCommun {
	ArrayList<Character> potCommun = new ArrayList<Character>();
	 //Singleton
    private static PotCommun instancePotCommun = new PotCommun();
    //Ajout d'un élément au pot commun
	public PotCommun(){
		this.potCommun.add(null);
	}
	
	public static PotCommun getInstancePotCommun() { 
		return instancePotCommun; 
	}
	//accesseur Pot commun
	public ArrayList<Character> getPotCommun(){
		return this.potCommun;
	}
	//Racine du Pot commun
	public void setPotCommun(ArrayList<Character> potCommun){
		this.potCommun = potCommun;
	}
	//Ajout de lettre dans le pot commun
	public void potCommunAddLetter(char letter){
			this.potCommun.add(letter); 
	}
	//Vérifie la présence de lettre dans le pot
	public Boolean verifLettrePotCommun(String word, ArrayList<Character> potCommun){
		char[] tabChar;
		boolean flag = true;
		tabChar = word.toCharArray();
		ArrayList<Character> tmpPotCommun = new ArrayList<Character>(potCommun.size());
		tmpPotCommun.addAll(potCommun);
			
		for(int i =0; i< tabChar.length; i++){
			for(int j = 0; j < tmpPotCommun.size(); j++){
				if(tabChar[i] == tmpPotCommun.get(j)){
					tmpPotCommun.remove(j);
					j--;
					break;
				}else if(j == tmpPotCommun.size()-1){
					flag = false;
				}
			}
		}
		if(flag == true){
			potCommun.removeAll(potCommun);
			potCommun.addAll(tmpPotCommun);
		}
		return flag;
	}
	//Affiche le contenu du pot commun
	public void printPotCommun(){
		System.out.println("Pot commun : "+this.potCommun);
	}

}
