package fr.esiea.Harchi_Juma.Game;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import fr.esiea.Harchi_Juma.Game.PotCommun;
import fr.esiea.Harchi_Juma.Game.Joueur;
import fr.esiea.Harchi_Juma.Game.Mots;
import fr.esiea.Harchi_Juma.Game.Core.Lettre;

public class WordsTest {

	Mots mots = new Mots();
	Joueur target = new Joueur();
	Joueur test = new Joueur();
	Joueur[] tabPlayer = new Joueur[2];
	PotCommun potCommun = new PotCommun();
	Lettre letterdraw = new Lettre();
	ArrayList<Character> list = new ArrayList<Character>();
	int val = 0;
	
	
	@Test
	public final void testRemoveAccent() {
		assertEquals("reve",mots.removeAccent("rêve"));
	}

	
	@Before
	public final void beforeTest(){
		ArrayList<String> listWord = new ArrayList<String>(1);
		listWord.add("mot");
		target.setListWord(listWord);
		test.listWord.remove(0);
	}
	
	@Test
	public final void testWhichWordStolen(){
		assertEquals(0,mots.whichWordStolen(target, "mot"));
	}
	
	@Before
	public final void beforeTestWhoIsStolen(){
		tabPlayer[0] = new Joueur();
		tabPlayer[1] = new Joueur();
		tabPlayer[0].setName("Target");
		tabPlayer[0].setScore(0);
		tabPlayer[1].setScore(0);
		tabPlayer[1].setName("Test");
		target.setName("Target");
		
		
		list.add('M');
		list.add('O');
		list.add('U');
		list.add('S');
		/*list.set(0, null);
		list.set(1, 'm');
		list.set(2, 'o');
		list.set(3, 'u');
		list.set(4, 's');*/
		potCommun.setPotCommun(list);
	}
	
	@Test
	public final void testWhoIsStolen() {		
		assertEquals(target.getName(),mots.whoIsStolen(tabPlayer, "Target").getName());
		
	}
	
	@Test
	public final void testStatPlayer(){
		mots.statPlayer(0, tabPlayer, "de");
		assertEquals(1,tabPlayer[0].getScore());
		assertEquals("de",tabPlayer[0].getListWord().get(1));
	}
	
	@Test
	public final void testSucceedTheft(){
		String newWord = "mousse";
		target.setScore(1);
		test.setScore(0);
		mots.succeedTheft(potCommun, list, target, test, newWord, 0);
		assertEquals(0,target.getScore());
		assertEquals(1,test.getScore());
		assertEquals(newWord,test.listWord.get(0));
		
	}
	
	/*@Test
	public final void testSearchInDicoBasic(){
		String line = "mous";
		int i = 0;
		System.out.println(mutualBag.getMutualBag());
		File currentDirFile = new File("src/main/resources/dico.txt");
    	String helper = currentDirFile.getAbsolutePath();
    	try {
			Scanner file = new Scanner(new File(helper));
			words.searchInDicoBasic(file, line, mutualBag, i, tabPlayer, val, letterdraw);
			System.out.println(tabPlayer[i].getScore());
			assertEquals(1,val);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

}
