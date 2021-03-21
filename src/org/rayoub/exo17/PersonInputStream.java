/* Ayoub TAIHI + Rayane CHIKHI 
      21-03-2021 */

package org.rayoub.exo17;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonInputStream extends FileInputStream {
    private FileInputStream fis;

    public PersonInputStream(FileInputStream fis) {
        super(FileDescriptor.in);
        this.fis = fis;
    }



   public List<Person> readFields() {
        try (FileInputStream fis = this.fis;
             InputStream is = new ByteArrayInputStream(fis.readAllBytes());
             DataInputStream dis = new DataInputStream(is)){

            List<Person> people = new ArrayList<>();
            final int  size= dis.readInt();
            for (int i = 0;i<size;i++) {
                people.add(new Person(dis.readUTF(),dis.readUTF(),dis.readInt()));
            }
            return people;
       } catch (IOException e) {
           e.printStackTrace();
       }

        return Collections.emptyList();
   }

    public List<Person> readPeople(){

        try (InputStream is = this.fis;
             ObjectInputStream ois = new ObjectInputStream(is)) {

            @SuppressWarnings("unchecked")
            List<Person> people = (List<Person>)ois.readObject();
            return people;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


}
