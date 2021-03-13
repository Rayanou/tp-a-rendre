/*Rayane CHIKHI + Ayoub TAIHI 
  13-03-2021 */

package org.rayoub.exo14;

import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayWithStream {

		public static void main(String[] args) {
			/*Cr�ation et affichage des �l�ments d'un stream comportant
			douze cha�nes de caract�res */		
			
			List<String> words = Arrays.asList(
					"one","two","three","four",
					"five","six","seven","eight",
					"nine","ten","eleven","twelve");
			
			Stream<String> strings = words.stream();
			
			//1 Afficher les �lements du stream
			System.out.println("\nQ1: Les �lements du stream :");
			words.forEach((s)-> System.out.println(s));
			
			
			//2 Affichage des �l�ments de ce stream en majuscule 
			String wordMaj = words.stream()
					.map(w->w.toUpperCase())
					.collect(Collectors.joining(" "));
			System.out.println("\nQ2: Mots en majuscules :");
			System.out.println(wordMaj);
			
			//3 Affichage des premi�res lettres des �l�ments de ce stream (sans doublon)
			
			System.out.println("\nQ3: 1st lettre en majuscules :");
			words.stream()
					.map(w->w.substring(0,1).toUpperCase()+w.substring(1))
					.forEach((s)->System.out.println(s));
				
			
			//4	Affichage des longueurs des �l�ments de ce stream (sans doublon)			
					
			System.out.println("\nQ4: Longeur des �lements :");
			strings.map(word->word.length()).distinct().forEach((s)->System.out.println(s));
			
			
			//5 Affichage du nombre d'�l�ments de ce stream
			System.out.println("\nQ5: nombre d'�lements :");
			System.out.println(words.stream().count());
			
			
			//6 Affichage du nombre d'�l�ments de ce stream (qui ont une longueur paire)
			System.out.println("\nQ6: nombre d'�lements de taille paire :");
			System.out.println(words.stream().filter(word->word.length()%2==0).count());
			
			
			//7 Affichage de la longueur de la cha�ne la plus longue
			System.out.println("\nQ7: longueur de la chaine la plus longue :");
			System.out.println(words.stream()
			.max(Comparator.comparingInt(String::length))
			.get().length());
			
			
			//8 Affichage du nombre d'�l�ments de ce stream (qui ont une longueur paire)
			System.out.println("\nQ8: Chaine de longueur impaire en majuscule :");
			List<String> longeurImpaire = words.stream().map(String::toUpperCase)
					.filter(word->word.length()%2!=0)
					.collect(Collectors.toList());
			longeurImpaire.forEach(System.out::println);
			
			//9 Tri par ordre alphab�tiques des �lements de longueurs sup�rieurs ou �gale 5 
			
			System.out.println("\nQ9: Tri par odre alphab�tique des �lements de longueur >=5 ");
			String concatenation = words.stream()
					.filter(w->w.length()>=5).sorted()
					.collect(Collectors.joining(";","{","}"));
					System.out.println(concatenation);
			
					
			//10 Cr�ation d'une table de hachage
		
			Map<String, Integer> keyLengthAndValueList = words.stream()
					.collect(toMap(Function.identity(), String::length));
			System.out.println("\nQ10: Table de hachage avec cl�s:longueurs des cha�nes, valeurs:listes des cha�nes");
			System.out.println(keyLengthAndValueList);	
	
						
			//11		
			Map<Character,String> concat = 
					words.stream().collect(
							Collectors.groupingBy(s->s.toString().charAt(0),
									 Collectors.joining(",")));
			System.out.println("\nQ11 : map avec cl�s: 1ere lettre des chaines, valeurs: concat�nation des chaines :");
			concat.forEach((key,value)->System.out.println(key+":"+value));
			
		}
		
	}
