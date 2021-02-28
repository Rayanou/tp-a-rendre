package org.rayoub.exo7;

/* Q10 */
import java.util.*;

public class EquipeLimite{

	private List<Joueur> joueurs;
	private Iterator<Joueur> iterator;
	Collection<Joueur>equipe = new ArrayList<>();	
	private int nombreMax = 4;  
	
	  public EquipeLimite() {
	    joueurs = new LinkedList<Joueur>();
	    iterator = null;	    
	  } 
	  
	/* Constructeur avec paramètre nombreMax */
	  
	  public EquipeLimite(int n_max) {
		  this.setNombreMax(n_max);
	  }

/* addJoueur(), removeJoueur(), isJoueurPresent() */
     // a- addJoueur() //
	  
public void addJoueur(Joueur j, int nombreMax) {
		if(nombreMax <= this.nombreMax)
		{
  System.out.println(j+" est un nouveau joueur");
  joueurs.add(j);
		}
}

     // b- removeJoueur() //

public void removeJoueur(Joueur j, int nombreMax) {
  if(nombreMax <= this.nombreMax)	
	  System.out.println(j+" est un joueur enlevé");
	  joueurs.remove(j);

  
}

    // c- isJoueurPresent() //

@SuppressWarnings("unchecked")
public boolean isJoueurPresent(Joueur j, int nombreMax) {
	
	if (((List<Joueur>) j).isEmpty() && nombreMax <= this.nombreMax)
		return false;
	else 
		return true;
	
	
	
}

/* Q7: clear() */

public void clear() {
	equipe.clear();
}


public int getNombreJoueurs() {
	return this.equipe.size();
}

public int getMoyenneAge() {
	int somme = 0;
	for(Joueur element : equipe)
	somme = element.getAge() + somme;

return somme/this.equipe.size();
}



 
/* toString */

@Override
public String toString() {
	return "Equipe : 4 joueurs \n [joueurs=" + joueurs + ", iterator=" + iterator + "]";
 }

/* getter et setter de "nombreMax" */

public int getNombreMax() {
	return nombreMax;
}

public void setNombreMax(int nombreMax) {
	this.nombreMax = nombreMax;
}

/* Q11 : les méthodes à modifier sont :  addJoueur(), removeJoueur() et isJoueurPresent() */

}
