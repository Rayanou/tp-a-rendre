/* Ayoub TAIHI + Rayane CHIKHI 
      21-03-2021 */

package org.rayoub.exo17;


import java.io.*;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FileOfPerson2 {

/* Q1 : Création d’une fonction qui prend en paramètre une personne et retourne un tableau de d’octets (byte[]) contenant le nom et le prénom de cette personne, ainsi que son âge, écrits sous forme d’octets */

    static Function<Person,byte[]> personToBytes = (person) -> {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             DataOutputStream dos = new DataOutputStream(bos)) {

            dos.writeUTF(person.getLastName());
            dos.writeUTF(person.getFirstName());
            dos.writeInt(person.getAge());
            
            return bos.toByteArray();
        } catch (IOException e) {
            
                e.printStackTrace();
       
             }

        return new byte[0];
    };

    public static void main(String[] args) throws IOException {
        /*Q1.a : ByteArrayOutputStream est une classe stream qui crée des objets d’une classe dans un buffer (à condition que celle-ci soit Sérializable) .

        *      b : DataOutputStream est un décorateur de File qui permet d’écrire et de manipuler des types primitifs dans les fichiers
         */

        Person p = new Person("Ayoub","Taihi",23);
        byte[] personBytes = personToBytes.apply(p);

        String fileName = "files/exo17PersonBytes.bin";

        System.out.println("\nQ3 : - Data in the form of Bytes : "+MessageFormat.format("personBytes= {0}", personBytes));

        InputStream is = new ByteArrayInputStream(personBytes);
        DataInputStream dis = new DataInputStream(is);

        String firstName = dis.readUTF();
        String  lastName= dis.readUTF();

        int  age= dis.readInt();

        System.out.println("\n        - Data in the form of Bytes converted : "+firstName+" "+lastName+" "+age);

/*  Test de la méthode writeFields(List<Person> people) */ 
 
        List<Person> people = Arrays.asList(

                new Person("Ayoub","Taihi",22),
                new Person("Rayane","Chikhi",23)
                );


        System.out.println("\nQ6 : Test of writing and reading the file : "); 
 
        try (PersonOutputStream personOutput = new PersonOutputStream(new FileOutputStream(fileName))) {
            personOutput.writeFields(people);
        }


        try (PersonInputStream personInput = new PersonInputStream(new FileInputStream(fileName))) {
            List<Person> people1;
            people1 = personInput.readFields();
            people1.forEach(System.out::println);
        }

    }

}
