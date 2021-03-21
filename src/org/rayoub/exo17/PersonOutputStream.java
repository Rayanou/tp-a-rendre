/* Ayoub TAIHI + Rayane CHIKHI 
      21-03-2021 */

package org.rayoub.exo17;

import java.io.*;
import java.util.List;

public class PersonOutputStream extends FileOutputStream {
    private FileOutputStream fos;

    public PersonOutputStream(FileOutputStream fos) {
        super(FileDescriptor.in);
        this.fos = fos;
    }

    public void writeFields(List<Person> people) {

        int size = people.size();

        try (FileOutputStream fos = this.fos;
             DataOutputStream dos = new DataOutputStream(fos)) {

            dos.writeInt(size); 
            for (Person person : people) {
                dos.write(FileOfPerson2.personToBytes.apply(person));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[OK] File written successfully");
    }

    public  void writePeople(List<Person> people) {

        try (OutputStream os = this.fos;
             ObjectOutputStream oos = new ObjectOutputStream(os)) {

            oos.writeObject(people);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("File written! ");
    }
}

