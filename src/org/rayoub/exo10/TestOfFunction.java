/*Rayane CHIKHI + Ayoub TAIHI
 07-03-2021 */


package org.rayoub.exo10;

import java.util.function.Function;
import java.util.function.BiFunction;

public class TestOfFunction {

	public static void main(String[] args) {

/* Q1 : Fonction qui prend une chaîne de caractères en paramètre et retourne cette chaîne en majuscules */		
           
		    String msg = "Ayoub is the best";
 		    Function<String, String> toUpperCase = String::toUpperCase;
	     	System.out.println(msg + "\n say it loudly : "+ toUpperCase.apply(msg)) ; 

/* Q2 : Fonction qui prend une chaîne de caractères en paramètre et retourne cette même chaîne, sans modification, (si la chaîne passée est nulle, alors cette fonction doit retourner la chaîne vide) */
			
	     	String msg2 = "\n Rayane is the best";
	     	
	     	/* 1- Fonction de retour la même chaîne sans modification */
	     	
		    Function<String, String> sameAsFirst = s -> s;
		
			/* 2- Fonction de retour de chaîne vide */
			
		    Function<String, Boolean> emptyString = s -> s.isEmpty();
		    if (emptyString.apply(msg2) == true)
		    	msg2 = ""; 
			
		    System.out.println(msg2 + "\n say it again : "+ sameAsFirst.apply(msg2));
			
/* Q3 : Fonction qui prend une chaîne de caractères en paramètre et retourne la longueur de cette chaîne. Si la chaîne passée en paramètre est nulle */			
	
			Function<String, Integer> lengthOfCharacter = s -> s.length();
			System.out.println(msg2 + "\n The message 2 has : "+ lengthOfCharacter.apply(msg2) + " words\n");

/* Q4 : Fonction qui prend une chaîne de caractères en paramètre et retourne cette chaîne entre parenthèses
 (Si la chaîne passée est nulle, alors le résultat est une parenthèse ouvrante) immédiatement suivie d’une parenthèse fermante */

			Function<String, String> addBrackets = s -> "("+s+")";
			Function<String, Boolean> emptyString2 = s -> s.isEmpty();
			if (emptyString2.apply(msg2) == true)
			    System.out.println("()"); 
			else
  		    	System.out.println("\n The message 2 between brackets : "+addBrackets.apply(msg2));

/* Q5 : Bifonction, qui prend deux chaînes de caractères en paramètres (cette fonction doit retourner un entier qui est la première position de la deuxième chaîne dans la première. Si la deuxième chaîne ne se trouve pas dans la première, alors cette fonction doit retourner -1) */			
			
			BiFunction<String , String, Integer> indexOf = (s1 ,s2) -> s1.indexOf(s2);	
		  	System.out.println (" \n message 2 in message 1 : " + indexOf.apply(msg, msg2));

/* Q6 : Fonction qui prend une chaîne de caractères, et retourne un entier qui est la position de cette chaîne dans la chaîne « abcdefghi » (si cette chaîne ne s’y trouve pas, cette fonction retourne -1) */
		  	
		  	System.out.println ("\n message 1 in <abcdefghi> : " + indexOf.apply("abcdefghi", msg));
	}  
}
