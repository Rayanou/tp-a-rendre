/* Ayoub TAIHI + Rayane CHIKHI 
      21-03-2021 */
package org.rayoub.exo16;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;



public class FileOfPersons {

	public static void main(String[] args) throws FileNotFoundException {

/* Q1 : Création d'une instance de Function qui prend en paramètre une ligne de ce fichier texte, et de ce format sous forme d’une String, et qui retourne une instance de Person */ 		

		PersonReader personRead= new PersonReader();
		PersonWriter personWritten= new PersonWriter();
		
		Function <String, Person> onePersonFromString = (String s) -> {
			Person person = new Person();
			String separator= ", ";
			String[] words = s.split(separator);
	
			int i = 0;
	
			person.setLastName(words[i]);
			person.setFirstName(words[i+1]);
			person.setAge(Integer.parseInt(words[i+2]));
			return person;
		};
		
		
		System.out.println("\n Q1 : Class Person has been created \n");
	
		
		
		String s=" Zidane, Zinedine, 47";

		System.out.println("\n Q2 : "+ onePersonFromString.apply(s));
		
	/* Q3 : Création d'une instance de Function qui prend en paramètre une ligne de ce fichier texte, et de ce format sous forme d’une String, et qui retourne une instance de Person */ 		
		
		System.out.println("\n Q3 : "+personRead.read("./files/persons.txt"));
	
	/* Q4 & 5 : test des méthodes de PersonWriter sur une liste de personnes, puis vérification que le fichier est correctement écrit */
		
		System.out.println("\n Q4 & 5 : Cf persons2.txt ");
		
		List<Person> personalities= new ArrayList<>();
		Person p1 = new Person ("Aimé","Jacquet",79);
		Person p2 = new Person ("Didier","Deschamps",51);
		Person p3 = new Person ("Lilian","Thuram",48);
		Person p4 = new Person ("Fabien","Barthez",49);
		Person p5 = new Person ("Fabien","Barthez",47);
		
		Person tableOfPersons[]= {p1,p2,p3,p4,p5};
		
		for(int i=0 ; i < tableOfPersons.length ; i++) 
			personalities.add(tableOfPersons[i]);
		
		personWritten.write(personalities,"files/persons2.txt");
	}

}
