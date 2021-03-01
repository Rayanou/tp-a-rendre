/*Ayoub TAIHI + Rayane CHIKHI
  Serie 3 28-02-2021 */

package org.rayoub.exo8;

public class Joueur {

	/*  Q1 : Classe Joueur possédant les propriétés ; nom, String et année de naissance */
         /* membres attributs */
	
	private String nom;
	private int anneeDeNaissance;

	public Joueur(String nom, int anneeDeNaissance) {
		super();
		this.nom = nom;
		this.anneeDeNaissance = anneeDeNaissance;
	}

        /* getters et setters */
	
 	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the anneeDeNaissance
	 */
	public int getAnneeDeNaissance() {
		return anneeDeNaissance;
	}

	/**
	 * @param anneeDeNaissance the anneeDeNaissance to set
	 */
	public void setAnneeDeNaissance(int anneeDeNaissance) {
		this.anneeDeNaissance = anneeDeNaissance;
	}

	public String getAnneeDeNaissance1() {
		
		return null;
	}

}
