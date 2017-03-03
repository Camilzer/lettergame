package fr.esiea.Harchi_Juma.Game.Core;

public class Score {
	String word;
	int idPlayer;
	
	//Constructeur
	public Score(){
		this.word = null;
		this.idPlayer = 0;
	}
	
	//Surcharge du constructeur
	public Score(String word, int idPlayer){
		this.word = word;
		this.idPlayer = idPlayer;
	}
	
	//Accesseur du mot
	public String getWord(){
		return this.word;
	}
	
	//Mutateur du mot
	public void setWord(String word){
		this.word = word;
	}
	
	//Accesseur de l'id du joueur
	public int getIdPlayer(){
		return this.idPlayer;
	}
	
	//Mutateur de l'id du joueur
	public void setIdPlayer(int idPlayer){
		this.idPlayer = idPlayer;
	}
}
