/*Ayoub TAIHI + Rayane CHIKHI
  Serie 3 28-02-2021 */

package org.rayoub.exo7;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.*;

/* Q2 . */

public class Equipe{

	private List<Joueur> joueurs;
	private Iterator<Joueur> iterator;
	Collection<Joueur>equipe = new ArrayList<>();	
	  
	  public Equipe() {
	    joueurs = new LinkedList<Joueur>();
	  } 

/* Q3 : addJoueur(), removeJoueur(), isJoueurPresent() */
     // a- addJoueur() //
	  
public void addJoueur(Joueur j) {
		
  System.out.println(j+" est un nouveau joueur");
  joueurs.add(j);

}

     // b- removeJoueur() //

public void removeJoueur(Joueur j) {
	
	  System.out.println(j+" est un joueur enlevé");
	  joueurs.remove(j);

  
}


     //c- containsJoueur() //

public void containsJoueur(Joueur j) {
	
	  System.out.println(j+" est un joueur contenu dans la liste");
	  joueurs.contains(j);


}


    // d- isJoueurPresent() //

@SuppressWarnings("unchecked")
public boolean isJoueurPresent(Joueur j) {
	if (((List<Joueur>) j).isEmpty())
		return false;
	else 
		return true;
}    


/* Q7: clear() */

public void clear() {
	equipe.clear();
}

/* Q8: getNombreJoueur() */

public int getNombreJoueurs() {
	return this.equipe.size();
}

/* Q9: getMoyenneAge() */

public int getMoyenneAge() {
	int somme = 0;
	for(Joueur element : equipe)
	somme = element.getAge() + somme;

return somme/this.equipe.size();
}

}
