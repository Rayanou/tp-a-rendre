/*Rayane CHIKHI + Ayoub TAIHI
 07-03-2021 */


package org.rayoub.exo9;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class TestPredicate {

	public static void main(String[] args) {
		
	    /*Q1 : Prédicat qui prend en paramètre une chaîne 
	     et retourne true si cette chaîne fait plus de 4 caractères  */
	    	Predicate<String> possedeTaillePlusQuatre = s -> s.length() > 4;
		    System.out.println("cette chaîne contient-elle plus de 4 caractères ? "
	    	+possedeTaillePlusQuatre.test("onesss"));
			List<String> strings = Arrays.asList("one","two","three","viva l'algerie");
			
			// essaie avec une liste de caractères :
			Predicate<String> p1 = 
					(String s) -> s.length()>4 ;
					for( String string : strings) {
				System.out.println("les chaines de la liste ont ils plus de 4 caractères? "
					+ p1.test(string));
					}

	    	
		/*Q2 : Prédicat qui prend en paramètre une chaîne et retourne true si cette chaîne est vide */
		    Predicate<String> estVide = s -> s.isEmpty();
		    System.out.println("\n cette chaîne est-elle vide ? "+estVide.test("onesss"));	
		    
		/*Q3 : Prédicat qui prend en paramètre une chaîne et retourne true si cette chaîne est vide */
		    Predicate<String> commenceParJ = s -> s.indexOf("J") == 0;
		    System.out.println("\n cette chaîne commence-t-elle par J ? "+commenceParJ.test("J'y suiiiiis !"));	
		    
		/*Q4 : Prédicat qui prend en paramètre une chaîne et retourne true si cette chaîne contient exactement 5 caractères */
		    Predicate<String> possedeTailleCinque = s -> s.length() == 5;
		    System.out.println(" \n cette chaîne possède-t-elle une longueur de 5 ? "+possedeTailleCinque.test("Ayoub"));	
		    
	    /*Q5 : Prédicat qui prend en paramètre une chaîne et retourne true si cette chaîne réaliser la Q3 et la Q4 au même temps */
		    Predicate<String> possedeTailleCinqueEtCommenceJ = s -> s.length() == 5 && s.indexOf("J") == 0;
		    System.out.println(" \n cette chaîne possède-t-elle une longueur de 5 tout en commençant par J ? "+possedeTailleCinqueEtCommenceJ.test("Jeans"));	
			    
	}

}
