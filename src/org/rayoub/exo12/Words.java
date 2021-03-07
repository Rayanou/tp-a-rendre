/*Rayane CHIKHI + Ayoub TAIHI
 07-03-2021 */

package org.rayoub.exo12;


import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.function.Function;

public class Words {


		public static void main(String[] args) {
		
/* Q1 : Affichage du contenu de la liste "words", en utilisant sa méthode forEach() */
	
		System.out.println("Q1 : The content of our list is : ");
		List<String> words = new ArrayList<String>();

		words.add("one");
		words.add("two");
		words.add("three");
		words.add("four");
		words.add("five");
		words.add("six");
		words.add("seven");
		words.add("eight");
		words.add("nine");
		words.add("ten");
		words.add("eleven");
		words.add("twelve");
		words.forEach(System.out::println);
		
/* Q2 : Retrait de cette liste, les chaînes de caractères dont la longueur est paire */
		
		words.removeIf(n -> (n.length() % 2 == 0)); 
		System.out.println("\nQ2 : The new list (after removing the even length of elements) : ");
        words.forEach(System.out::println);
		
/* Q3 : a- Mise en majuscule de chaque élément de la liste */
        
System.out.println("\nQ3 : a-  Words in majuscule : ");
		
		Function<String, String> majuscule =  s -> s.toUpperCase();
		for (String s2 : words) {
			System.out.println(majuscule.apply(s2));
		}
		System.out.println("\n b- Words begin with voyelle : ");
		Predicate<String> Voyelle = s -> s.startsWith("a")||s.startsWith("e")||s.startsWith("i")||s.startsWith("o")||s.startsWith("u")||s.startsWith("y");
		for (String s : words) {
			System.out.println(Voyelle.test(s));
		}
		System.out.println("\n c- Words begin with a vowel (in majuscule) : ");
		for (String s : words) {
			if (Voyelle.test(s))
			System.out.println(majuscule.apply(s));
			else 
				System.out.println(s);
		}
/* Q4 : Comparator qui permet de comparer les chaînes de caractères par leur longueur, en triant cette liste par ordre croissant de leur longueur */
	
		Comparator <String> cmpByLength = (s1,s2) -> s1.length() - s2.length();
		words.sort(cmpByLength);
		System.out.println("\nQ4 : The new list (in which, elements are put according to their ascending order in terms of length) : \n"+words);

/* Q5 : Tri de cette liste dans l’ordre croissant de la longueur des mots (deux mots de même longueur seront triés dans l’ordre alphabétique) */
	
		Comparator<String> compareByLength =Comparator.comparing(String::length);
		words.sort(compareByLength);
		
		Comparator<String> cmpBylengthandalphabet=compareByLength.thenComparing(String::toString);
		words.sort(cmpBylengthandalphabet);

		System.out.println("\nQ5 : The new list (in which, elements are put according to their ascending order in terms of length. If they are equals, we classify them by alphabetic orders) : \n");
		words.forEach(s2 -> System.out.println(s2));	


	}
}
