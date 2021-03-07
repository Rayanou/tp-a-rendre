/*Rayane CHIKHI + Ayoub TAIHI
 07-03-2021 */

package org.rayoub.exo11;

	import java.util.Comparator;
	import java.util.List;
	import java.util.Arrays;
		
	public class PlayWithPerson {
		
		public static void main(String[] args) {
			
			/* Q1 : Comparaison des chaînes en fonction de leurs longueurs */
			
			String msg1 = "Hello Everyone!";
			String msg2 = "Ayoub"; 

			
			Comparator <String> cmpByLength = (s1,s2) -> s1.length() - s2.length();
			
			if (cmpByLength.compare(msg1, msg2) > 0)
				System.out.println(msg1 + " : est plus grande que : "+ msg2);
			else if (cmpByLength.compare(msg1, msg2) < 0)
	            System.out.println(msg2 + " : est plus grande que : "+ msg1);
	        else
	            System.out.println(msg1 + " : est à égalité avec : "+ msg2);

			/*Q2 : Comparateur de personnes (instances de Person) qui compare 
			 les personnes en fonction de leurs noms de famille (lastName) */
			
	  		        /* 1- Ajout des personnes dans la table */
			System.out.println();
			List <Person> persons = Arrays.asList
					(new Person("Ayoub","Taihi",23),
					 new Person("Rayane","Chikhi",24),
					 new Person("Celina","Lounas",22),
					 new Person("Sylia","Nait",22),
			         new Person("Hana","Chikhi",23));
			persons.forEach(s -> System.out.println(s));
			
		           /* 2- Interface fonctionnelle Comparator comportant une 
		            fonction lambda, qui fait référence à "LastName" de la 
		            classe "Person" */
					
			 Comparator <Person> compareByLastName = (p1 , p2) -> {
					int cmpLastName = p1.getLastName().compareTo(p2.getLastName());
					return cmpLastName;
				};
			 
			 
			       /* 3- Comparaison des personnes en fonction de leur Noms */
				 
		     persons.sort(compareByLastName);		 
			 System.out.println("\nPersons alphabetically compared by their last names : \n  ");
			 persons.forEach(System.out::println);
			
			/* Q3 : Comparateur de personne qui compare les personnes en fonction de leurs
			  noms de famille, et si ces personnes ont même noms de famille, 
			  en fonction de leurs prénoms */
	     	    
	                   Comparator<Person> compareLastNameOrFirstNameIfEq = (p1 , p2) -> {
	                	   if(p1.getLastName().compareTo(p2.getLastName()) == 0) {
	                		   return p1.getFirstName().compareTo(p2.getFirstName());
	                				  
	                	   }
	                	   return  p1.getLastName().compareTo(p2.getLastName());
	                   };
	                   
	                   persons.sort(compareLastNameOrFirstNameIfEq);	
	                   System.out.println("\nCompare persons by lastName then by firstName : \n");
	                   persons.forEach(System.out::println);
	              
	            
	          /* Q4 : Comparateur inverse du comparateur précédent */
	                   Comparator<Person> compareLastNameFirstNameReversed =
	                		   compareLastNameOrFirstNameIfEq.reversed();
	                   persons.sort(compareLastNameFirstNameReversed);	
	                   System.out.println("\nThe comparaison between last names in the inverse order : \n  ");
	                   persons.forEach(System.out::println);
	          
	         /* Q5 : Tri de liste, et attribution des valeurs */
	                   /* 1- Etablissement d'une liste d'éléments comparables */ 

	                   System.out.println();
	           		List <Person> listavecnull = Arrays.asList
	           				(new Person("Ayoub","Taihi",23),
	           				 null,	
	           				 new Person("Rayane","Chikhi",24),
	           				 new Person("Celina","Lounas",22),
	           				 null,
	           				 new Person("Sylia","Nait",22));
	                                       		   
	                   Comparator<Person> cmp = Comparator.nullsLast(
	                		   Comparator.comparing(
	                		   Person::getLastName));
	           				listavecnull.sort(cmp);
	           				System.out.println("\nTri en mettant les élements null à la fin");
	           				listavecnull.forEach(System.out::println);
	                   
		}
	          
	}
