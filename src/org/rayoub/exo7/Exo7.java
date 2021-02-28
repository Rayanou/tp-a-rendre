package org.rayoub.exo7;

import java.util.LinkedList;
import java.util.List;

/*Q5  */

public class Exo7 {

	
		  private List<Equipe> equipe;
		  public Exo7() {
			    equipe = new LinkedList<Equipe>();
			  } 
		  
		  
public void addAllEquipe(Equipe eq) {
	for (int i = 0; i < 5; i++) {
	  System.out.println(eq+" est un nouveau joueur");
	  equipe.add(eq);
}

}

/* Q7 : clear() ; effacer le contenu de l'équipe */

public void clear(Equipe eq) {
	
	for (int i = 0; i < 5; i++) {
	
		  System.out.println(eq+" est un joueur enlevé");
		  equipe.remove(eq);
	  
	}	
}

/* Q8 : getNombreJoueur() ;  retourner le nombre de joueurs dans une équipe */

public int getNombreJoueur() {

	return equipe.size();

}

/* Q9 : getMoyenneAge() ; retourner la moyenne des âges */

public double getMoyenneAge(Joueur[] j) {
double somme = 0.0, moyenne;
	for (int i = 0; i < j.length; i++) {
        somme += j[i].getAge();   
}
	
moyenne = somme/5;
return moyenne;
}

}
