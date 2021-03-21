/* Ayoub TAIHI + Rayane CHIKHI 
      21-03-2021 */

package org.rayoub.exo18;

import org.rayoub.exo17.PersonInputStream;
import org.rayoub.exo17.PersonOutputStream;
import org.rayoub.exo17.Person;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class FileOfPersons3 {

    public static void main(String[] args) {

        List<Person> persons = Arrays.asList(
                new Person("Ayoub", "Taihi", 23),
                new Person("Rayane", "Chikhi", 23) 
                );
        
        /* Q1 : par la sérialization d'un objet. La sérialization est un procédé pour avoir une représentation portable d'un objet 
        *      ObjectOutputStream est un décorateur "high-level" de File utilisé pour la manipulation et l'écriture des objets du FileOutputStream (qui est un low-level) 
        *  Q2 : On doit mettre une classe "Person" sérializable" en implementant une interface implémentable par la classe personne de telle sorte que "public class Person" implemente Serializable
        * On peut également ajouter un optional "serialVersionUID"
         */
        
        String fileName = "files/exo18Persons.bin";

        try (PersonOutputStream pos = new PersonOutputStream(new FileOutputStream(fileName))) {
            pos.writePeople(persons);
        } catch (IOException e) {
            e.printStackTrace();
        }


       try (PersonInputStream pis = new PersonInputStream(new FileInputStream(fileName))) {
            List<Person> people = pis.readPeople();
           
            System.out.println("\nQ5 : The list of persons is :");
           
            people.forEach(System.out::println);
        
       } catch (IOException e) {
       
    	   e.printStackTrace();
       
       }

       
    }


}
