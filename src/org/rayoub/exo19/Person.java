/* Ayoub TAIHI + Rayane CHIKHI 
      21-03-2021 */

package org.rayoub.exo19;

import java.util.Comparator;

public class Person {

/** Membres attributs de "Person" **/
	
private String firstName;
private String lastName;
private int age; 

/** Getters et setters **/

public String getFirstName() {
	return firstName;
}


public void setFirstName(String firstName) {
	this.firstName = firstName;
}


public String getLastName() {
	return lastName;
}


public void setLastName(String lastName) {
	this.lastName = lastName;
}


public int getAge() {
	return age;
}

public void setAge(int age) {
	this.age = age;
}

/** Constructeur de "Person" **/

/*	public Person(String firstName, String lastName, int age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}
*/	
	 @Override
public String toString() {
	return "Person [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + "]";
}

	public static Comparator<Person> ComparatorLastName = new Comparator<Person>() {
	      
	        @Override
	        public int compare(Person p1, Person p2) {
	            return p1.getLastName().compareTo(p2.getLastName());
	        }
	    };
}
