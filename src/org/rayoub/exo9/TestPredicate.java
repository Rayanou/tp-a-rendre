/*Rayane CHIKHI + Ayoub TAIHI
 07-03-2021 */


package org.rayoub.exo9;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class TestPredicate {

	public static void main(String[] args) {
		
	    /*Q1 : Pr�dicat qui prend en param�tre une cha�ne 
	     et retourne true si cette cha�ne fait plus de 4 caract�res  */
	    	Predicate<String> possedeTaillePlusQuatre = s -> s.length() > 4;
		    System.out.println("cette cha�ne contient-elle plus de 4 caract�res ? "
	    	+possedeTaillePlusQuatre.test("onesss"));
			List<String> strings = Arrays.asList("one","two","three","viva l'algerie");
			
			// essaie avec une liste de caract�res :
			Predicate<String> p1 = 
					(String s) -> s.length()>4 ;
					for( String string : strings) {
				System.out.println("les chaines de la liste ont ils plus de 4 caract�res? "
					+ p1.test(string));
					}

	    	
		/*Q2 : Pr�dicat qui prend en param�tre une cha�ne et retourne true si cette cha�ne est vide */
		    Predicate<String> estVide = s -> s.isEmpty();
		    System.out.println("\n cette cha�ne est-elle vide ? "+estVide.test("onesss"));	
		    
		/*Q3 : Pr�dicat qui prend en param�tre une cha�ne et retourne true si cette cha�ne est vide */
		    Predicate<String> commenceParJ = s -> s.indexOf("J") == 0;
		    System.out.println("\n cette cha�ne commence-t-elle par J ? "+commenceParJ.test("J'y suiiiiis !"));	
		    
		/*Q4 : Pr�dicat qui prend en param�tre une cha�ne et retourne true si cette cha�ne contient exactement 5 caract�res */
		    Predicate<String> possedeTailleCinque = s -> s.length() == 5;
		    System.out.println(" \n cette cha�ne poss�de-t-elle une longueur de 5 ? "+possedeTailleCinque.test("Ayoub"));	
		    
	    /*Q5 : Pr�dicat qui prend en param�tre une cha�ne et retourne true si cette cha�ne r�aliser la Q3 et la Q4 au m�me temps */
		    Predicate<String> possedeTailleCinqueEtCommenceJ = s -> s.length() == 5 && s.indexOf("J") == 0;
		    System.out.println(" \n cette cha�ne poss�de-t-elle une longueur de 5 tout en commen�ant par J ? "+possedeTailleCinqueEtCommenceJ.test("Jeans"));	
			    
	}

}
