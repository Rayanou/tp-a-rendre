package org.rayoub.miniprojet;

/*******************************************************
* Projet : Ascenseur Etape 1                           *
* Nom : Taihi                                          *
* Pr�nom : Ayoub                                       *
*                                                      *                                                     *
* Nom : Chikhi                                         *
* Pr�nom : Rayane                                      * 
*                                                      *                                                      *
* Sp�cialit� : R�seaux & T�l�com.                      *
********************************************************/

package org.paumard.elevator.student;

import org.paumard.elevator.Elevator;
import org.paumard.elevator.model.Person;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

public class DumbElevator implements Elevator {

/** Attributs membres **/
	
	Random random = new Random(314L); 
    private int elevatorCapacity;

	private int peopleInElevator = 0;
	private int peopleWaitingAtFloors = 0;

	private int currentFloor;
	private int nextFloor;

	private boolean lastPersonArrived = false;
	private List<List<Person>> peopleByFloor;

    public DumbElevator(int elevatorCapacity) {
        this.elevatorCapacity = elevatorCapacity;
    }

/** M�thodes **/
/* 1- Initialisation de l'�tage de d�part */
    @Override
    public void startsAtFloor(LocalTime time, int initialFloor) {
        this.currentFloor = initialFloor;
    }

    
/* 2- Gens en fil d'attente dans les �tages */   
    @Override
    public void peopleWaiting(List<List<Person>> peopleByFloor) {
        this.peopleByFloor = peopleByFloor;
        this.peopleWaitingAtFloors = peopleByFloor.stream().mapToInt(List::size).sum();
    }

    
/* 3- Choix des �tages en fonction de la situation de l'ascenseur */    
    @Override
    public int chooseNextFloor() { 
 
 /*   	
  i- Chercher l'�tage le plus proche du celui courant
  ii- R�cup�ration sur une liste mutable de ces �tages
  iii- Mont�e et descente dans un temps optimal  
  
    	List<Person> floors = Arrays.asList(); 
    	
    	for (int i = 0; i < 50; i++)
         	if (this.nextFloor - this.currentFloor == 1)
       	    	 floors = peopleByFloor.get(currentFloor);
 
    	     floors.add(peopleByFloor);
    	*/
    	 if(this.lastPersonArrived && this.peopleInElevator == 0 && this.peopleWaitingAtFloors == 0) 
        	return 1;
    	
    	
   int direction = 0;
       this.nextFloor = random.nextInt(4) + 1;
       
 
   if (this.nextFloor == this.currentFloor) {
	      /* in this case, we do nothing */
	    }
   else if(nextFloor > currentFloor) 
      
	  direction = 1 ;
    else 
      direction = -1;
   
    while(currentFloor != nextFloor) 
        currentFloor = currentFloor + direction;  

   return nextFloor;
    }


/* 4- Etage d'arriv�e */
    @Override
    public void arriveAtFloor(int floor) {
        this.currentFloor = floor;
    }

    
/* 5- Chargement des personnes en fonction de la situation de l'ascenseur */    
    @Override
    public void loadPerson(Person person) {
//       List<Person> people = peopleByFloor.get(currentFloor);
     
     //this means that this person cannot be loaded into the elevator as he is not in the waiting queue of the current floor
      
//        if(people.contains(person)){
//        this.peopleInElevator++;

         this.peopleWaitingAtFloors--;
    }

    
/* 6- D�chargement des personnes en fonction de la situation de l'ascenseur */       
    @Override
    public void unloadPerson(Person person) {
        //	  Iterator<List<Person>> itr = peopleByFloor.iterator();
  	    
   //    while(itr.hasNext()) 
    	    	this.peopleInElevator--;
    }
    

/* 7- Chargement de nouvelles personnes en fonction de la situation de l'ascenseur */     
    @Override
    public void newPersonWaitingAtFloor(int floor, Person person) {
  /*   
     	for(int i=1; i<=4; i++) {
    	      Iterator<Person> itr = (peopleByFloor.get(i)).iterator();
    	      while(itr.hasNext()) 
                  this.peopleWaitingAtFloors++;
    	}
   */ 
         this.peopleWaitingAtFloors++;
    }
    
    
/* 8- Initialisation de la derni�re personne arriv�e � l'ascenseur */   
    @Override
    public void lastPersonArrived() {
    	this.lastPersonArrived = true;
    }
 
}
