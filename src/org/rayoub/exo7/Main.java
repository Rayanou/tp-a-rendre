package org.rayoub.exo7;

import java.util.*;

/***************************** Série 3 *******************************/

public class Main
{
	public static void main(String[] args) {
	
	String n_p1 = "Zinedine";
	int age1 = 42;

 	String n_p2 = "Fabien";
	int age2 = 26;
	
	String n_p3 = "Lilian";
	int age3 = 26;
	
	String n_p4 = "Bixente";
	int age4 = 28;
	
	String n_p5 = "Youri";
	int age5 = 30;
	

	Joueur j1 = new Joueur(n_p1,age1);
	Joueur j2 = new Joueur(n_p2,age2);
	Joueur j3 = new Joueur(n_p3,age3);
	Joueur j4 = new Joueur(n_p4,age4);
	Joueur j5 = new Joueur(n_p5,age5);

	/* Q1 */
	   // 1.1. Affichage d'un nom et d'un âge d'un joueur //
	   
    System.out.println("Joueur : "+j1.getNom()+", age = "+j1.getAge()+"\n");

	   // 1.2. Deux joueurs sont égaux ? // 	
   
       System.out.println("Joueur1 = Joueur2 = ? "+j1.equals(j2)+"\n");

	   // 1.3. Trier les instances dans un ordre alphabétiques //  


       // Créer un ArrayList vide
       List<String> Joueur = new ArrayList<String>();
       // Ajouter des joueurs dans cette ArrayList
       
        Joueur.add("Mbappe");
        Joueur.add("Zidane");
        Joueur.add("Di Maria");
        Joueur.add("Neymar");
        Joueur.add("Mahrez");
     
     
       //trier la liste
       Collections.sort(Joueur); 
     
       //Affichez les éléments de la liste
       System.out.println("Classement des joueurs par ordre alphabétique : "+ Joueur+"\n");     // ou par la méthode compareTo //
       
   /* Q4 */

     
       SortedSet <Joueur> joueur = new TreeSet<>();
    
       joueur.add(new Joueur (n_p2,age2));
       joueur.add(new Joueur (n_p3,age3));
       joueur.add(new Joueur (n_p4,age4));
       joueur.add(new Joueur (n_p5,age5));
     
      Equipe eq = new Equipe();
 
     // ajout des joueurs dans la liste // 
       eq.addJoueur(j2);
       eq.addJoueur(j3);
       eq.addJoueur(j4);
       eq.addJoueur(j5);
       
       System.out.println("\n\nEquipe : "+joueur.size()+" joueurs");
       for (Joueur je : joueur) {
           System.out.println(je);
  
	}
}
	
	/* Q12 : tester le fonctionnement de "nombreMax" */

      EquipeLimite eql = new EquipeLimite();
/* 
       eql.addJoueur(j1);
       eql.addJoueur(j2);
       eql.addJoueur(j3);
       eql.addJoueur(j4);
       eql.addJoueur(j5);
*/
      
}
