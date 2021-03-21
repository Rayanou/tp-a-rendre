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

/* Q4 : Cr�ation d'une classe PersonWriter, comportant une m�thode write(List<Person> people,String fileName), capable d��crire la liste de personnes pass�e en param�tre dans le fichier dont le nom est pass� en param�tres. */

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
