package fr.esiea.Harchi_Juma.Game;

import java.util.Random;

public enum Alphabet{
	
    A(120,1,'a'), B(20,2,'b'), C(15,3,'c'), D(16,4,'d'), E(150,5,'e'), F(20,6,'f'), G(23,7,'g'), H(18,8,'h'), I(122,9,'i'),
    J(17,10,'j'), K(14,11,'k'), L(20,12,'l'), M(30,13,'m'), N(18,14,'n'), O(95,15,'o'), P(25,16,'p'), Q(10,17,'q'), R(25,18,'r'),
    S(60,19,'s'), T(55,20,'t'), U(103,21,'u'), V(20,22,'v'), W(2,23,'w'), X(3,24,'x'), Y(4,25,'y'), Z(3,26,'z');
    
    
    
    // Variables
    private final int voy;
    private int numLetter;
    private char letter;
    private static char[] tab = null;
    private static Random rand = new Random();	
    
    // Methodes
    private Alphabet(int voy, int numLetter, char letter){
        this.voy = voy;
        this.numLetter = numLetter;
        this.letter = letter;
    }
    
    public int getValue(){
    	return numLetter;
    }
    
    public char getChar(){
    	return letter;
    }
    
    public void setChar(char letter){
    	this.letter = letter;
    }
    public void setValue(int numLetter){
    	this.numLetter = numLetter;
    }
    
    private static void fillTab(){
        int n = 0;
        
        for (Alphabet v : Alphabet.values())
            n += v.voy;
        
        tab = new char[n];
       
        int i = 0;
        for (Alphabet v : Alphabet.values())
            for (int j = 0; j < v.voy; i++ , j++ )
                tab[i] = v.toString().charAt(0);
    }
    
    public double getFrequency(){
        if (tab == null)
            fillTab();
        
        return ((double) voy) / tab.length;
    }
    

    
    public static char random(){
        if (tab == null)
            fillTab();
        
        return tab[rand.nextInt(tab.length)];
    }
}

