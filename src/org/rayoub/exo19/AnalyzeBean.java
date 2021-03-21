/* Ayoub TAIHI + Rayane CHIKHI 
      21-03-2021 */

package org.rayoub.exo19;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Modifier;

public class AnalyzeBean {
	
	public String getClassName(Object o) {

/* Q0 : Création de la classe "AnalyzeBean" */
		
		Class <?> classPerson1 = o.getClass();
		return classPerson1.getName();
		
	}	

	public Object getInstance(String className) throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
	try {
		Class <?> classPerson = Class.forName(className);
		Object newInstance = classPerson.getConstructor().newInstance();
		return newInstance;
	}catch(ClassNotFoundException | InstantiationException | IllegalAccessException exc) {
	
	exc.printStackTrace();
	}

return null;
}
	
	/* Q3 : m�thode retourne la liste des noms des propri�t�s de l�objet pass� en param�tre */
	
public List<String> getProperties(Object o){
	Method[] tableOfDeclaredMethods = o.getClass().getDeclaredMethods();

	 List<String> properties = new ArrayList <String>();
	for (Method method : tableOfDeclaredMethods) 
		System.out.println(method.getName());
	
	  // 1.3 getModifier() : M�thode qui retourne un champ de bit (entier) repr�sentant le type d'une classe (public, private, static...) par la classe "Modifier" //
	 
	for (Method method : tableOfDeclaredMethods) {
		String methodName = method.getName();
	   if ((methodName.startsWith("get") || methodName.startsWith("is")) && method.getParameterCount() == 0 && Modifier.isPublic(method.getModifiers())){
    	 
    	    String nameOfProperty = ""; 		
	   if (methodName.startsWith("get"))
		   nameOfProperty = methodName.substring(3);
	   else
		   nameOfProperty = methodName.substring(2);
	
	
	nameOfProperty = nameOfProperty.substring(0,1).toLowerCase() + nameOfProperty.substring(1);
	properties.add(nameOfProperty);
    	}
	}
	return properties;
}
}
