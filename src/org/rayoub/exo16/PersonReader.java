/* Ayoub TAIHI + Rayane CHIKHI 
      21-03-2021 */

package org.rayoub.exo16;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/* Q3 : Création d'une classe PersonReader, comportant une méthode read(String fileName) */

public class PersonReader {
	
	Function <String, Person> onePersonFromString= (String s) -> {
	
		Person person = new Person();
	
		String separator= ", ";
		String[] words = s.split(separator);
		
		int i=0;
		
		person.setLastName(words[i]);
		person.setFirstName(words[i+1]);
		person.setAge(Integer.parseInt(words[i+2]));
		
		
	return person;

	};
	
	public List<Person> read (String nameOfFile) {
		File file = new File (nameOfFile);
		List <Person> persons = new ArrayList <Person>();
		
		try(Reader reader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(reader);){
			
			List <String> lines = bufferedReader.lines().filter(s -> s.startsWith("#")!=true).collect(Collectors.toList());
		
			for (String nameOfPerson: lines) 
				persons.add(onePersonFromString.apply(nameOfPerson));
		
			
	
		} catch(IOException e) {
		
			e.printStackTrace();
	
		}
		
	return persons;
	
	
	}
}
