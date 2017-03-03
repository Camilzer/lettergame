package fr.esiea.Harchi_Juma.Game.Core;

import java.util.ArrayList;

public class IA{
	
	ArrayList<Character> iaBag = new ArrayList<Character>();
	int iaMode;
	
	//Constructeur
	public IA(){
		this.iaBag = null;
		this.iaMode = 0;
	}

	//Accesseur du pot de l'ia
	public ArrayList<Character> getIaBag(){
		return this.iaBag;
	}
	
	//Mutateur du pot de l'ia
	public void setIaBag(ArrayList<Character> iaBag){
		this.iaBag = iaBag;
	}
	
	//Accesseur du mode ia
	public int getIaMode(){
		return this.iaMode;
	}
	
	//Mutateur du mode ia
	public void setIaMode(int iaMode){
		this.iaMode = iaMode;
	}
}
