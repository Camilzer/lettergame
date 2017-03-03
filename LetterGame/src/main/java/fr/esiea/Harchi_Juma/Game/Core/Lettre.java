package fr.esiea.Harchi_Juma.Game.Core;

import fr.esiea.Harchi_Juma.Game.Alphabet;
import fr.esiea.Harchi_Juma.Game.PotCommun;
import fr.esiea.Harchi_Juma.Game.Joueur;

public class Lettre {

	public Lettre(){}
	
	// Affichage de la lettre sortie du pot commun au joueur
	public void oneDraw(Joueur joueur, PotCommun potCommun ){
		char letterChosen;
	
		letterChosen = Alphabet.random();
		potCommun.potCommunAddLetter(letterChosen);
		System.out.println(joueur.getName()+" pioche : "+letterChosen);
		potCommun.printPotCommun();
	}
	
	//Piochage des deux premières lettres de la partie
	public void playerStarterDraw(Joueur joueur, PotCommun potCommun){
		char letterChosen;
		
		for(int i =0; i<2;i++){
			letterChosen = Alphabet.random();
			potCommun.potCommunAddLetter(letterChosen);
			System.out.println(joueur.getName()+" pioche : "+letterChosen);
		}
		potCommun.printPotCommun();
	}
	
	//Defini une lettre au hasard au joueur
	public void firstDraw(Joueur[] tabPlayer, Alphabet[] alphabet, PotCommun potCommun ){
		int i,j;
		char letterChosen;
		
		for(i=0; i<tabPlayer.length; i++){
			letterChosen = Alphabet.random();	
			alphabet[i].setChar(letterChosen);
			alphabet[i].setValue(letterChosen);
	
			for(j=0;j<25;j++){	
				if(alphabet[j].getChar()==letterChosen){
					potCommun.potCommunAddLetter(letterChosen);
					tabPlayer[i].setVal(alphabet[j].getValue());
					System.out.println(tabPlayer[i].getName()+" pioche la lettre "+letterChosen);
					alphabet[j].setChar('0');
				}
			}
		}
	}
}
