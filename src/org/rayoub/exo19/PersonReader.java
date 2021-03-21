/* Ayoub TAIHI + Rayane CHIKHI 
      21-03-2021 */


package org.rayoub.exo19;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.lang.reflect.Field;

public class PersonReader {

	public static void main(String []args) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		/* Q6 : Cr�er une classe PersonReader en tant que d�coration de FileReader (par le BufferedReader). Cr�ation dans cette classe d'une m�thode read()  (qui retourne une liste d�Object contenant les objets sp�cifi�s dans ce fichier) */
               // 1. Cr�ation de Path : interface de mod�lisation des ressources du disque o� est stock� le fichier "persons&employees.txt" //
		
		Path peopleAndEmployeeFile = Path.of("files/persons&employees.txt");
		System.out.println("\nQ6 : The informations of the file read : \n");
            
		      // 2. BufferedReader : pattern decorateur de design API (lecture "buff�ris�e") //	
		
			Set<String> keyOfBean = new HashSet<>();
			Map<String, Object> registryBean = new HashMap<>();
		
			           // 2.1. Suppression des s�parateurs et des lignes qui ne correspondent ni aux classes ni aux instances //
			
			try (BufferedReader personOrEmployee = Files.newBufferedReader(peopleAndEmployeeFile);){
			
			String line = personOrEmployee.readLine();
		
			
			while (line != null) {
				if (!line.startsWith("#")) {
					String[] separator = line.split("=");
				
				if (separator[0].equals("bean.name"))                                                // p dans un Set //
					keyOfBean.add(separator[1]);
			
				else if (separator[0].endsWith("class")) {
					String keyOfOneBean = separator[0].substring(0, separator[0].indexOf('.'));      // Avoir la cl� en Bean (keyOfBean), la charger, l'instancier et la mettre dans la table de hachage //
		            String nameOfClass = separator[1];
				
		            Class<?> classOfBean = Class.forName(nameOfClass);
				    Constructor<?> emptyConstructorOfBean = classOfBean.getConstructor();
				    
				    
				    registryBean.put(keyOfOneBean,emptyConstructorOfBean.newInstance());
				}  
				
				// Association de beanKey � l'objet d'instanciation
				     // i. Instance de lastName
				
				else if (separator[0].endsWith(".lastName")) {
					
				    String keyOfOneBean = separator[0].substring(0, separator[0].indexOf('.')); 				    	
				    String name = separator[1];
				    
				    Object bean = registryBean.get(keyOfOneBean);
				    Field lastNameField = bean.getClass().getDeclaredField("lastName");
				    lastNameField.setAccessible(true);
				    lastNameField.set(bean,name);
				    }
				
			
			          // ii. Instance de firstName
					
                 else if (separator[0].endsWith(".firstName")) {
					
				    String keyOfOneBean = separator[0].substring(0, separator[0].indexOf('.')); 				    	
				    String name = separator[1];
				    
				    Object bean = registryBean.get(keyOfOneBean);
				    Field firstNameField = bean.getClass().getDeclaredField("firstName");
				    firstNameField.setAccessible(true);
				    firstNameField.set(bean,name);
				    }

		              // iii. Instance de 'age'
								
                 else if (separator[0].endsWith(".age")) {
 					
 				    String keyOfOneBean = separator[0].substring(0, separator[0].indexOf('.')); 				    	
 				    int age = Integer.parseInt(separator[1]);
 				    
 				    Object bean = registryBean.get(keyOfOneBean);
 				    Field ageField = bean.getClass().getDeclaredField("age");
 				    ageField.setAccessible(true);
 				    ageField.set(bean,age);
 				    }
				
				}
				line = personOrEmployee.readLine(); 
			}
			 
		}catch ( IOException | ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException|NoSuchFieldException exc)
				{
  
			
			exc.printStackTrace();
			
		}
			
			// 3. Cr�ation des Beans et des "AnalyzedKeys" //
			
			System.out.println("       - The instance of the classes created in 'Analyzed Keys' : ");
			keyOfBean.forEach(System.out::println);
			
			System.out.println("\n     - The beans of the classes created : ");
			registryBean.forEach((k,obj) -> System.out.println(k+" : " +obj));
		
			
			
	}

}

				
