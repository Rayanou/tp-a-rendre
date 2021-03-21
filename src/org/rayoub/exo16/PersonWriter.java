/* Ayoub TAIHI + Rayane CHIKHI 
      21-03-2021 */

package org.rayoub.exo16;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.function.Function;

/* Q4 : Création d'une classe PersonWriter, comportant une méthode write(List<Person> people,String fileName), capable d’écrire la liste de personnes passée en paramètre dans le fichier dont le nom est passé en paramètres. */

public class PersonWriter {
	Function <Person, String> onePersonFromString= (Person p) -> {
		StringBuilder str= new StringBuilder();
		str.append(p.getFirstName()+", "+p.getLastName()+", "+p.getAge()+"\n");
	return str.toString();
	
	};
	
	
	public void write (List <Person> people, String filename) {
	
		File file=new File(filename);
		
		try(Writer writer =new FileWriter(file,true);BufferedWriter personWritten=new BufferedWriter(writer);)
				{
		
			for(Person p:people) 
				personWritten.write(onePersonFromString.apply(p));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
}
}
