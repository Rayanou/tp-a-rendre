/*Rayane CHIKHI + Ayoub TAIHI
 07-03-2021 */

package org.rayoub.exo13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PlayWithMaps {

	public static void main(String[] args) {
		
		Map<Integer, String> immutableMap = 
				Map.ofEntries(
						Map.entry(3, "one"),
						Map.entry(4, "four"),
						Map.entry(5, "three"),
						Map.entry(6, "eleven")
				);
		
		// 1) Mettre les valeurs en majuscule :
		
		Map<Integer, String> map = new HashMap<>(immutableMap);
		map.replaceAll((key,value)->value.toUpperCase());
		System.out.println("Les valeurs en majuscules : ");
		map.forEach((key,value)->System.out.println(key+ "="+value));
		
		
		// 2) Faire en sorte que map.get(key) ne retournera jamais null:
		
		Map<Integer, String> firstMap = new HashMap<>(immutableMap);
		List<Integer> keys = 
				List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
		System.out.println("\nmap sans null :");
		for (int key : keys) {
			firstMap.putIfAbsent(key, "");
			System.out.println(key + " = " + firstMap.get(key));
		}
		
		/* Q3 : La méthode qui permet d’avoir le même résultat 
		 sans modifier map est : putIfAbsent. 
		  Cette méthode permet de garantir qu'une liste n'est pas vide, 
		  ou non nulle. 
		  Pour celà, elle fait deux choses : 
		       - Si une clé n'existe pas dans une table de hachage,
		        elle fait un "put", la créant dans la percle de hachage 
		        avec sa valeur associée 
		       - Sinon, ne la crée pas tant qu'elle est existante dans notre
		        percle de hachage.  	
		 => Dans les 2 cas, elle retourne une liste.
		 */
			
		/* Q4 : Création d'une table de hachage dont :
		 *       - Les clés : Longueurs des chaînes de caractères
		 *       - Les valeurs : Listes associées aux longueurs 	
		 */
			    /* 1- Création d'une map et d'une liste (pour l'associé à la valeur de la map) */
			       
			       List<String> words = List.of("one", "two", "three","four", "five", "six", "seven", "eight","nine","ten","eleven", "twelve");
			       Map <Integer, List<String>> listMap = new HashMap<>();
			       
			     /* 2- computeIfAbsent : Retour de la valeur courante de la map :
			      *      - Si la clé n'existe pas, la fonction "lambda" est invoquée, retournant une nouvelle liste et l'ajoutant à la map associée à sa clé
			      *      - Enfin, cette liste est retournée par le "computeIfAbsent" pour la création de la valeur de la nouvelle clé         
			      */
			       for (String value : words)
			    	   listMap.computeIfAbsent(value.length(), key-> new ArrayList<>()).add(value);
			       
		/* Q5 : Création de la table de hachage, qui cette fois-ci les valeurs sont des String, construites par concaténation des valeurs de words, séparées des virgules */	       

			       Map <Integer,Map<Character,List<String>>> MapMap = new HashMap<>();
			       for (String w : words) {
			    	   int k = w.length();
			    	   char firstLetter = w.charAt(0);
			    	   MapMap.computeIfAbsent(k, key -> new HashMap<>()).computeIfAbsent(firstLetter, key -> new ArrayList<>()).add(w);
			       }
			    	   
			       System.out.println("\nQ4 : The 'mapping' map is : ");
			       MapMap.forEach((k,v) -> System.out.println(k + " = " + v));
			 
			      /*1- On utilise pour celà "merge" qui fusionne une chaîne de caractère issue d'une table de hachage avec une nouvelle passée dans son paramètre (car computeIfAbsent ne fonctionne qu'avec les chaînes immutables)
			       *      - Si la clé est dans la liste de hachage, elle fonction de la même manière que "put"
			       *      - Sinon (si la clé n'est pas dans la table de hachage, mais est associée à une valeur existante, elle va prendre la valeur (qu'on veut associer à la table de hachage), et effectue une "fusion" avec sa clé associée.
			       *              - Cette opération est réalisée grâce à la l'interface "BiFunction"  */ 
			       
			      
			      Map <Integer, String> mapMerged = new HashMap <>();
			      for (String w : words) {
			    	  int k = w.length();
			    	  mapMerged.merge(k,w,(existingValue,associatedValue) -> existingValue + ", "+ associatedValue);
			      }
			       	
			       System.out.println("\nQ5 : The merged map is : ");
			       mapMerged.forEach((k,v) -> System.out.println(k + "  " + v));
			       
			}
}














