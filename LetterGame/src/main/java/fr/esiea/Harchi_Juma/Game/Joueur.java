package fr.esiea.Harchi_Juma.Game;

import java.util.ArrayList;


public class Joueur{
	 String name;
	int val;
	int score;
	Boolean play;
	public ArrayList<String> listWord = new ArrayList<String>();

	
	//Joueur
	public Joueur(){
		this.name = null;
		this.val = 0;
		this.score = 0;
		this.play = false;
		this.listWord.add("null");
	}
	

	//Accesseur nom
	public String getName(){
		return this.name;
	}
	//Mutateur nom
	public void setName(String name){
		this.name = name;
	}
	//Get liste de mot
	public ArrayList<String> getListWord(){
		return this.listWord;
	}
	//Set liste de mot
	public void setListWord(ArrayList<String> listWord){
		this.listWord = listWord;
	}
	//Accesseur val
	public int getVal(){
		return this.val;
	}
	//mutateur de la valeur
	public void setVal(int val){
		this.val = val; 
	}
	//Accesseur du score
	public int getScore(){
		return this.score;
	}
	//mutateur du score
	public void setScore(int score){
		this.score = score;
	}
	//Accesseur tour du joueur
	public Boolean getPlay(){
		return this.play;
	}
	//Accesseur tour  du joueur
	public void setPlay(Boolean play){
		this.play = play;
	}
	
	
}
