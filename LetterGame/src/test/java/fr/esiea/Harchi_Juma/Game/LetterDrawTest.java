package fr.esiea.Harchi_Juma.Game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.esiea.Harchi_Juma.Game.Alphabet;
import fr.esiea.Harchi_Juma.Game.PotCommun;
import fr.esiea.Harchi_Juma.Game.Joueur;
import fr.esiea.Harchi_Juma.Game.Core.Lettre;

public class LetterDrawTest {
	
	Lettre lettre = new Lettre();
	Alphabet[] alphabet = Alphabet.values();
	PotCommun mutualBag1 = new PotCommun();
	PotCommun mutualBag2 = new PotCommun();
	PotCommun mutualBag3 = new PotCommun();
	Joueur[] tabPlayer = new Joueur[2];
	Joueur target1 = new Joueur();
	Joueur target2 = new Joueur();
	
	@Before
	public final void beforeTestOneDraw(){
		mutualBag1.getPotCommun().remove(null);
		lettre.oneDraw(target1, mutualBag1);
	}
	
	@Test
	public final void testOneDraw() {
		assertNotNull("MutualBag n'est plus vide car une lettre a été piochée", mutualBag1.getPotCommun());
	}
	
	@Before
	public void beforeTestPlayerStarterDraw(){
		mutualBag2.getPotCommun().clear();
		lettre.playerStarterDraw(target2, mutualBag2);
	}
	@Test
	public void testPlayerStarterDraw(){
		assertNotNull("MutualBag n'est plus vide car 2 lettres ont été piochées", mutualBag2.getPotCommun());
	}
	
	@Before
	public void beforeTestFirstDraw(){
		mutualBag3.getPotCommun().clear();
		tabPlayer[0] = new Joueur();
		tabPlayer[1] = new Joueur();
		
		lettre.firstDraw(tabPlayer, alphabet,mutualBag3);
	}
	@Test
	public void testFirstDraw(){
		assertNotNull("MutualBag n'est plus vide car 2 lettres ont été piochées", mutualBag3.getPotCommun());
	}

}