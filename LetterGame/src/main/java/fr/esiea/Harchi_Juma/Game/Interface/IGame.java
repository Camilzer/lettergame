package fr.esiea.Harchi_Juma.Game.Interface;

import fr.esiea.Harchi_Juma.Game.Alphabet;
import fr.esiea.Harchi_Juma.Game.Joueur;

//Methode de l'interface
public interface IGame{
	
	public String getString();
	public int setNbPlayer();
	public void initTabPlayer(int gameMode);
	public void setNameEnter();
	public void startGame(Alphabet[] alphabet);
	public Joueur whoStart(Joueur[] tabPlayer);
	public Joueur[] sortArray(Joueur[] tabPlayer);
	
}
