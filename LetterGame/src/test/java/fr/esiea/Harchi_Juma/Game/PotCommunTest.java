package fr.esiea.Harchi_Juma.Game;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import fr.esiea.Harchi_Juma.Game.PotCommun;

public class PotCommunTest {
	
	PotCommun potCommun = new PotCommun();
	PotCommun mutualBagTest = new PotCommun();

	@Before
	public void initialize(){
		ArrayList<Character> list = new ArrayList<Character>();
		list.add(null);
		list.add('a');
		list.set(0, null);
		list.set(1, 'a');
		mutualBagTest.setPotCommun(list);
	}
	
	@Test
	public final void testMutualBagAddLetter() {
		char letter = 'a';
		
		potCommun.potCommunAddLetter(letter);
		assertEquals(mutualBagTest.getPotCommun(),potCommun.getPotCommun());
		//System.out.println(mutualBagTest.getMutualBag());
		//System.out.println(mutualBag.getMutualBag());
	}
	
	@Test
	public final void testVerifLetterMutualBag() {
		String word = "a";
		ArrayList<Character> list = new ArrayList<Character>();
		list.add('a');
		mutualBagTest.setPotCommun(list);
		assertTrue("Should return true if letter in the mutualBag",mutualBagTest.verifLettrePotCommun(word, list));
	}


}
