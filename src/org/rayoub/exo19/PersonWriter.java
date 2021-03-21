/* Ayoub TAIHI + Rayane CHIKHI 
      21-03-2021 */

package org.rayoub.exo19;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class PersonWriter {

	public static void main(String []args) throws FileNotFoundException {

/* Q9 : Création d'une classe PersonWriter en tant que décoration de FileWriter. Création d'une méthode write(List<Object> beans), capable de créer le fichier spécifié	*/

		System.out.println("\nQ6 : The informations of the file written : \n");
                 
		File filePersonsEmployees = new File("files/persons&employees2.txt");

		String personsAndEmployees = "# Ceci est une ligne de commentaires\r\n"
				+ "bean.name=p1\r\n"
				+ "p1.class=Exo19.Person\r\n"
				+ "p1.lastName=Tabarly\r\n"
				+ "p1.firstName=Eric\r\n"
				+ "p1.age=32\r\n"
				+ "bean.name=p2\r\n"
				+ "p2.class=Exo19.Employee\r\n"
				+ "p2.lastName=Auguin\r\n"
				+ "p2.firstName=Christophe\r\n"
				+ "p2.age=45\r\n"
				+ "p2.salary=12000";
		
		try (OutputStream os = new FileOutputStream(filePersonsEmployees);
			BufferedOutputStream bos = new BufferedOutputStream(os);) {

		  bos.write(personsAndEmployees.getBytes());
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
	
		
	}

}
