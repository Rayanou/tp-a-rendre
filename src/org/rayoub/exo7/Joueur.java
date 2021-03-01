/*Ayoub TAIHI + Rayane CHIKHI
  Serie 3 28-02-2021 */

package org.rayoub.exo7;

import java.util.Objects;

public class Joueur implements Comparable<Joueur>{

/*  Membres de "Joueur" */
	
private String nom;
private int age;

// Constructeur de "Joueur" //

 public Joueur(String nom, int age) {
  this.nom=nom;
  this.age=age;
 }

/* getters et setters */
 

public int getAge() {
  return age;
 }

 public void setAge(int age) {
  this.age = age;
 }

 public String getNom() {
  return nom;
 }

 public void setNom(String nom) {
  this.nom = nom;
 }

/* hashCode, equals et compareTo */
 
@Override
public int hashCode() {
	return Objects.hash(age, nom);
}

@Override
public boolean equals(Object obj) {
	if (this == obj) {
		return true;
	}
	if (!(obj instanceof Joueur)) {
		return false;
	}
	Joueur other = (Joueur) obj;
	return age == other.age && Objects.equals(nom, other.nom);
}

@Override
public int compareTo(Joueur j) {
	return this.nom.compareTo(j.nom);
}

@Override
public String toString() {
	return "Joueur : name = " + nom + ", age = " + age ;
}


}
