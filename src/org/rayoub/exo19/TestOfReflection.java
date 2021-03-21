/* Ayoub TAIHI + Rayane CHIKHI 
      21-03-2021 */


package org.rayoub.exo19;

import java.lang.reflect.Method;
import java.util.List;
import java.lang.reflect.Modifier;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class TestOfReflection {


	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
		
/*Q1 : Ajouter à cette classe une méthode getClassName(Object o) (qui retourne le nom complet de la classe de l’objet passé en paramètre) */
		
		AnalyzeBean beanAnalyzer = new AnalyzeBean();
		System.out.println("Q1 : The 'getClassName' added to the class Person : "+beanAnalyzer.getClassName(String.class));
		
/*Q2 : méthode getInstance(String className), qui prend un nom complet de classe
en paramètre. Cette méthode retourne une instance de la classe dont le nom est passé en
paramètre. */
		
		Person p1 = new Person();
		Class <?> BeanClass1 = p1.getClass();
		Object newInstance = BeanClass1.getConstructor().newInstance();
		System.out.println("\nQ2 : - The new instance (proceeding by the method 'newInstance') : "+newInstance);
		
		Person personBeanAnalyzer = (Person)beanAnalyzer.getInstance("Exo19.Person");
		System.out.println("\n     - The new instance (proceeding by the method 'getInstance') : "+personBeanAnalyzer);

/* Q3 : Créer une méthode getProperties(Object o), qui retourne la liste des noms
des propriétés de l’objet passé en paramètre */
		// 1. Méthodes 
		    // 1.1. getMethod() : Méthode qui retourne un tableau méthodes dans "String.class" (méthodes publiques de String et super-classes) //
		
		Class<?> stringClass = String.class;
		
		
		Method[] tableOfMethods = stringClass.getMethods();
		System.out.println("\nQ3 : - The name of all methods contained in 'string.class' : ");
		for (Method method : tableOfMethods) 
			System.out.println(method.getName());
		
		
     	    // 1.2. getDeclaredMethod() : Méthode qui retourne un tableau méthodes dans "String.class" contenues dans la classe "Person" (méthodes publiques et privées de 'Person') //
		

		Method[] tableOfDeclaredMethods = stringClass.getDeclaredMethods();
		System.out.println("\n    - The name of all methods contained in 'string.class' (only contained in the class 'Person') : ");
		for (Method method : tableOfDeclaredMethods) 
			System.out.println(method.getName());
		
		  // 1.3 getModifier() : Méthode qui retourne un champ de bit (entier) représentant le type d'une classe (public, private, static...) par la classe "Modifier" //
		 
		Class<?> personClass = Person.class;
		
		for (Method method : tableOfDeclaredMethods) {
			String methodName = method.getName();
		   if ((methodName.startsWith("get") || methodName.startsWith("is")) && method.getParameterCount() == 0 && Modifier.isPublic(method.getModifiers())){
	    	 
	    	    String nameOfProperty = ""; 		
		   if (methodName.startsWith("get"))
			   nameOfProperty = methodName.substring(3);
		   else
			   nameOfProperty = methodName.substring(2);
		
		
		nameOfProperty = nameOfProperty.substring(0,1).toLowerCase() + nameOfProperty.substring(1);
	    	}
    	}
		
		      // i. getProperties
		 List<String> properties = beanAnalyzer.getProperties(personBeanAnalyzer);
		 System.out.println("\nQ3 : - Properties (by getProperties) : "+properties); 
		 
		      // ii. getDeclaredField  
		 
	        System.out.println("\n     - Properties (by getDeclaredField) : "); 
		 Field[] Fields = personClass.getDeclaredFields();
		 
		 for (Field f : Fields)
		        System.out.println("\n field : "+f.getName()+"\n"); 
			
	 /* Q4 : Création de la méthode get(Object bean, String property) */
         // 1. Field qui porte le nom du champ "firstName" //
		  
		 Field firstName = personClass.getDeclaredField("firstName");
		 
		 firstName.setAccessible(true);
		 firstName.set(personBeanAnalyzer, "Ayoub");
		
		 // 2. Field qui porte le nom du champ "lastName" //
		  
		 Field lastName = personClass.getDeclaredField("lastName");
				 
		 lastName.setAccessible(true);
	     lastName.set(personBeanAnalyzer, "Taihi");
				 
		 
		// 3. Field qui porte le nom du champ "age" //
		 
		 Field age = personClass.getDeclaredField("age");
		 age.setAccessible(true);
		 age.set(personBeanAnalyzer, 23);
		 System.out.println("\nQ4 : - The person ivoked thanks to get method is : "+personBeanAnalyzer);
		 
		 System.out.println("\n     - Is private the type returned ? : "+Modifier.isPrivate(age.getModifiers()));

	  /* Q5 : Création de la méthode set(Object bean, String property) */
		 
		// 1. Ivocation de la méthode setProperty //
		 
		 String propertyName = "age";
		 int realAge = 38;
		 String NameOfMethod = "set" + propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);
		 Method setAge = personBeanAnalyzer.getClass().getMethod(NameOfMethod,int.class);
	
		 setAge.invoke(personBeanAnalyzer,realAge); 
		 System.out.println("\nQ5 : - The person ivoked thanks to set method is : "+personBeanAnalyzer);
			
		// 2. Appel à un constructeur par le bean //
			
		 Constructor<?> constructorOfPerson = personClass.getConstructor();
		 Person person3 = (Person) constructorOfPerson.newInstance();
		 System.out.println("\n     - The constructor invoked thanks to a get constructor method (getConstructor) : "+person3);
	}
}

