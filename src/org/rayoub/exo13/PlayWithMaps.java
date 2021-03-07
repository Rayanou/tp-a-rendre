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
		
		/* Q3 : La m�thode qui permet d�avoir le m�me r�sultat 
		 sans modifier map est : putIfAbsent. 
		  Cette m�thode permet de garantir qu'une liste n'est pas vide, 
		  ou non nulle. 
		  Pour cel�, elle fait deux choses : 
		       - Si une cl� n'existe pas dans une table de hachage,
		        elle fait un "put", la cr�ant dans la percle de hachage 
		        avec sa valeur associ�e 
		       - Sinon, ne la cr�e pas tant qu'elle est existante dans notre
		        percle de hachage.  	
		 => Dans les 2 cas, elle retourne une liste.
		 */
			
		/* Q4 : Cr�ation d'une table de hachage dont :
		 *       - Les cl�s : Longueurs des cha�nes de caract�res
		 *       - Les valeurs : Listes associ�es aux longueurs 	
		 */
			    /* 1- Cr�ation d'une map et d'une liste (pour l'associ� � la valeur de la map) */
			       
			       List<String> words = List.of("one", "two", "three","four", "five", "six", "seven", "eight","nine","ten","eleven", "twelve");
			       Map <Integer, List<String>> listMap = new HashMap<>();
			       
			     /* 2- computeIfAbsent : Retour de la valeur courante de la map :
			      *      - Si la cl� n'existe pas, la fonction "lambda" est invoqu�e, retournant une nouvelle liste et l'ajoutant � la map associ�e � sa cl�
			      *      - Enfin, cette liste est retourn�e par le "computeIfAbsent" pour la cr�ation de la valeur de la nouvelle cl�         
			      */
			       for (String value : words)
			    	   listMap.computeIfAbsent(value.length(), key-> new ArrayList<>()).add(value);
			       
		/* Q5 : Cr�ation de la table de hachage, qui cette fois-ci les valeurs sont des String, construites par concat�nation des valeurs de words, s�par�es des virgules */	       

			       Map <Integer,Map<Character,List<String>>> MapMap = new HashMap<>();
			       for (String w : words) {
			    	   int k = w.length();
			    	   char firstLetter = w.charAt(0);
			    	   MapMap.computeIfAbsent(k, key -> new HashMap<>()).computeIfAbsent(firstLetter, key -> new ArrayList<>()).add(w);
			       }
			    	   
			       System.out.println("\nQ4 : The 'mapping' map is : ");
			       MapMap.forEach((k,v) -> System.out.println(k + " = " + v));
			 
			      /*1- On utilise pour cel� "merge" qui fusionne une cha�ne de caract�re issue d'une table de hachage avec une nouvelle pass�e dans son param�tre (car computeIfAbsent ne fonctionne qu'avec les cha�nes immutables)
			       *      - Si la cl� est dans la liste de hachage, elle fonction de la m�me mani�re que "put"
			       *      - Sinon (si la cl� n'est pas dans la table de hachage, mais est associ�e � une valeur existante, elle va prendre la valeur (qu'on veut associer � la table de hachage), et effectue une "fusion" avec sa cl� associ�e.
			       *              - Cette op�ration est r�alis�e gr�ce � la l'interface "BiFunction"  */ 
			       
			      
			      Map <Integer, String> mapMerged = new HashMap <>();
			      for (String w : words) {
			    	  int k = w.length();
			    	  mapMerged.merge(k,w,(existingValue,associatedValue) -> existingValue + ", "+ associatedValue);
			      }
			       	
			       System.out.println("\nQ5 : The merged map is : ");
			       mapMerged.forEach((k,v) -> System.out.println(k + "  " + v));
			       
			}
}














