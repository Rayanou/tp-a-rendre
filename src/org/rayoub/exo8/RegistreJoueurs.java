package org.rayoub.exo8;

import java.util.Map;
import java.util.HashMap;
import java.util.*;
/* Q2 : classe RegistreJoueurs + tables de "Hachage" */

public class RegistreJoueurs {

	public RegistreJoueurs()  {
		
		
            Map <Integer, String> map = new HashMap <>();
        
            /* instanciation des joueurs */
            
            Joueur j1 = new Joueur("Laurent",1965);
            Joueur j2 = new Joueur("Marcel",1968);
            Joueur j3 = new Joueur("Patrick",1976);
            Joueur j4 = new Joueur("Alain",1970);
            Joueur j5 = new Joueur("Didier",1968);
            
            /* ajouts dans une percle de hachage */
            
            map.put(j1.getAnneeDeNaissance(),j1.getNom());
            map.put(j2.getAnneeDeNaissance(),j2.getNom());
            map.put(j3.getAnneeDeNaissance(),j3.getNom());
            map.put(j4.getAnneeDeNaissance(),j4.getNom());
            map.put(j5.getAnneeDeNaissance(),j5.getNom());
            
            
            
	}

/* Q3 : addJoueur() */
/*
public void addJoueur(Joueur j) {

	

map.put(j.getAnneeDeNaissance(),j.getNom());

		}	
*/

/* Q4 : get(int)  ; nombre de joueurs né dans cette décennie */
/*
public void get(int number) {
	

     for (Map.Entry mapentry : map.entrySet()) {
        System.out.println("clé: "+mapentry.getKey() 
                           + " | valeur: " + mapentry.getValue());
}
     
}
*/
	
/* Q5 : retour de joueurs nés dans la décennie de 1970 */
public <K, V> int count(Map<K, ? extends Collection<V>> map,int number) {
    int count = 0;
  
    for (Collection<V> collection : map.values())
    	if (number > 1970 && number < 1980) 		
    	   count += collection.size();
    return count;
}

/* Q6 : retour du nombre total des joueurs */

public <K, V> int count(Map<K, ? extends Collection<V>> map) {
    int count = 0;
  
    for (Collection<V> collection : map.values())
    	count += collection.size();
    return count;
}


/* Q7 : toString (affichage table de hachage) */


public String toString(Joueur j) {
	return "RegistreJoueurs name = "+ j.getNom() + ", annee de Naissance = " + j.getAnneeDeNaissance()+"";
}


}
