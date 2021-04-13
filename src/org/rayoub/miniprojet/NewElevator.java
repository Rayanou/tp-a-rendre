/* Ayoub Taihi
 * Rayane Chikhi 
  Mini-Projet partie 2 UPDATE */


package org.paumard.elevator.student;

import org.paumard.elevator.Elevator;
import org.paumard.elevator.event.DIRECTION;
import org.paumard.elevator.model.Person;
import org.paumard.elevator.Building;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalTime;
import java.util.ArrayList;
import java.time.Duration;



public class NewElevator implements Elevator {
    private static final int ANGER_LIMIT_THRESHOLD = 180;
    private int currentFloor = 1;
	private final int capacity;  
          private List<List<Person>> peopleByFloor = List.of();
	private List<Integer> destinations = List.of();
	private LocalTime time;
	private List<Person> people = new ArrayList<>();
        

    public DumbElevatorForVIP(int capacity) {
		this.capacity = capacity;
    }

    @Override
    public void startsAtFloor(LocalTime time, int initialFloor) {
		this.time = time;
    }

    @Override
    public void peopleWaiting(List<List<Person>> peopleByFloor) {
    	this.peopleByFloor = peopleByFloor;
    }

    @Override
    public List<Integer> chooseNextFloors() {
/*
if (!this.destinations.isEmpty()) {
    		return this.destinations;
    	}
        
    	int numberOfPeopleWaitingAtFloors = countWaitingPeople();       
    	 
    	if(numberOfPeopleWaitingAtFloors == 0 && this.lastPersonArrived) {
    		return List.of(1);     	
    	}
    	
    	
    	if (numberOfPeopleWaitingAtFloors > 0) {
    		
    		List<Integer> destinations = destinationsToPickUpAngryPeople();
    		if (!destinations.isEmpty()) {
    			this.destinations = destinations;
    			return this.destinations;
    		}
    		List<Integer> fullFloors = findFullFloor();
              int fullFloor = fullFloors.get(0);
    		
    		
    		

    		if (numberOfPeopleWaitingAtFloors >= Building.ELEVATOR_CAPACITY) {
        		if (numberOfPeopleWaitingAtFloors == Building.ELEVATOR_CAPACITY) {
        			List<Integer> destinationForSameFloor = sameDestinationForMostPeopleWaiting();
        			this.destinations = destinationForSameFloor;
        			return this.destinations;
        		}
        		
        		else {
        			   if(this.time.getHour() <= 10 && this.time.getHour() > 7) {
    	            List <Integer> destinationWithHighestAffluence = List.of(1);
    	        	this.destinations = destinationWithHighestAffluence;
    	            return this.destinations;
        	   }
        			   if(this.time.getHour() <= 20 && this.time.getHour() > 16) {
           	         List <Integer> destinationInTheMiddleOfElevator = List.of(5);
       	        	this.destinations = destinationInTheMiddleOfElevator;
    	            return this.destinations;
               	   }
             }    		
       
    	}
    		
 		   if(this.time.getHour() <= 14 && this.time.getHour() > 12) {
     	         List <Integer> destinationInTheMiddleOfElevator = List.of(5);
 	        	this.destinations = destinationInTheMiddleOfElevator;
	            return this.destinations;
 		   }
    					
    		if (fullFloor != this.currentFloor) 
    			return List.of(fullFloor);
    		 
	
    		else{
    			int indexOfCurrentFloor = this.currentFloor - 1;
				List<Person> waitingListFromCurrentFloor = 
						this.peopleByFloor.get(indexOfCurrentFloor);
				
				List<Integer> destinationFloorsForCurrentFloor = 
						DestinationOfAllFloors(waitingListFromCurrentFloor);
				this.destinations  = destinationFloorsForCurrentFloor;
				return this.destinations;
    		}
    	}
    	
    	
    	return List.of(1);
    }

*/
int indexOfFloor = this.currentFloor - 1;
    	if ( !peopleByFloor.get(indexOfFloor).isEmpty() ) {
    		List<Integer> destinationsOfPeopleFromCurrentFloor = DestinationOfAllFloors(peopleByFloor.get(indexOfFloor));
    		for (Integer floor : destinationsOfPeopleFromCurrentFloor) {
				if (floor > this.currentFloor) 
					this.destinations.add(floor);
				
				this.destinations = 
						this.destinations.stream().distinct().sorted().collect(Collectors.toList());
			}
    	}
    	if (!this.destinations.isEmpty()) {
    		if(people.size() < this.capacity) 
    			this.destinations = PeopleAscendingFromTheWaitingList();
    		return this.destinations;
    	}
    	int numberOfPeopleWaitingAtFloors = countWaitingPeople();
    	if (numberOfPeopleWaitingAtFloors > 0) {
			List<Integer> destinationsToPickAngryPeople = destinationsToPickUpAngryPeople(); 
			
			
			
			if (!destinationsToPickAngryPeople.isEmpty()) {
				this.destinations = destinationsToPickAngryPeople;
				return this.destinations;
			}
			
			List<Integer> fullFloors = findFullFloor();
			int fullFloor = fullFloors.get(0);
			if (fullFloor != this.currentFloor) {
				return List.of(fullFloor);
			} else {
				int indexOfCurrentFloor = indexOfFloor;
				List<Person> waitingListFromCurrentFloor = 
						this.peopleByFloor.get(indexOfCurrentFloor);
				List<Integer> destinationFloorsForCurrentFloor = 
						DestinationOfAllFloors(waitingListFromCurrentFloor);
				this.destinations  = destinationFloorsForCurrentFloor;
			
				return this.destinations;
			}
    	}
    	else {
    		if (this.time.isAfter(LocalTime.of(10,30,0)) || 
    				this.time.isAfter( LocalTime.of(12,0,0)) && this.time.isBefore(LocalTime.of(13,0,0))) {
    			return List.of(1);
    		}else 
    			return List.of(10);
    		
    	}
    }

	private List<Integer> PeopleAscendingFromTheWaitingList() {
	             List<Integer> fullFloors = findFullFloor();
                 int fullFloor = fullFloors.get(0);
                        
		
		if (fullFloor > this.currentFloor) {
			List<Integer> destinations = DestinationOfAllFloors(peopleByFloor.get(fullFloor - 1));
			List<Integer> newDestinations = new ArrayList<>();
			for (Integer destination : destinations ) {
				if (destination > this.currentFloor) {
					newDestinations.add(destination);
				}
			}
			if (fullFloor > this.currentFloor && !this.destinations.contains(fullFloor)) {
				newDestinations.add(fullFloor);
			}
			newDestinations.addAll(this.destinations);
			newDestinations = newDestinations.stream().distinct().sorted().collect(Collectors.toList());
			return new ArrayList<>(newDestinations);
		}
		return this.destinations;
	}
	
	
/*	
private List<Integer> sameDestinationForMostPeopleWaiting(){
		
		for (int indexFloor = 0 ; indexFloor < Building.MAX_FLOOR ; indexFloor++) {
			List<Person> waitingList = this.peopleByFloor.get(indexFloor);
			if (!waitingList.isEmpty()) {
				List<Integer> fullFloors= findFullFloor();
				Entry<Integer, Long> maxSameDestinationForPersonsWaiting = fullFloors.stream()
						.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
						.entrySet()
						.stream()
						.max(
						(floorX, floorY) ->
						floorX.getValue() == floorY.getValue()
						?
						Long.compare(floorX.getKey(), floorY.getKey())
						:
						Long.compare(floorX.getValue(), floorY.getValue())
						)
						.get();
	
				
				
				
	    			List<Integer> sameDestinations = List.of(indexFloor + 1, ((Person) maxSameDestinationForPersonsWaiting).getDestinationFloor());
    				return new ArrayList<>(sameDestinations);			
    				
			     }
			}
		
		return List.of();
}
*/
/*
private boolean destinationAreHigherFloors(List<Integer> sortedDestinationFromCurrentFloor) {
		return sortedDestinationFromCurrentFloor.get(0) > currentFloor && sortedDestinationFromCurrentFloor.get(1) > currentFloor;
	}

	
	private boolean destinationAreLowerFloors(List<Integer> sortedDestinationFromCurrentFloor) {
		return sortedDestinationFromCurrentFloor.get(0) < currentFloor && sortedDestinationFromCurrentFloor.get(1) > currentFloor;
	}
	
*/

	private List<Integer> findFullFloor() {
		for (int indexFloor = 0 ; indexFloor < Building.MAX_FLOOR ; indexFloor++) {
			if (!peopleByFloor.get(indexFloor).isEmpty()) {
				return List.of(indexFloor + 1);
			}
		}
		return List.of(-1);
	}


private List<Integer> DestinationOfAllFloors(List<Person> waitingListFromCurrentFloor) {
		return waitingListFromCurrentFloor.stream()
			.map(person -> person.getDestinationFloor())
			.distinct()
			.sorted()
			.collect(Collectors.toList());
	}

	private List<Integer> destinationsToPickUpAngryPeople() {
		
		for (int indexFloor = 0 ; indexFloor < Building.MAX_FLOOR ; indexFloor++) {
			List<Person> waitingList = this.peopleByFloor.get(indexFloor);
			if (!waitingList.isEmpty()) {
				Person bigWaitingPerson = waitingList.get(0);
				LocalTime arrivalTime = bigWaitingPerson.getArrivalTime();
				Duration waitingTime = Duration.between(arrivalTime, this.time); 
				long timeWaitingInFloors = waitingTime.toSeconds();
				if (timeWaitingInFloors  >= ANGER_LIMIT_THRESHOLD) {
					List<Integer> result = List.of(indexFloor + 1, bigWaitingPerson.getDestinationFloor());
					return new ArrayList<>(result);
				}
			}
		}
		return List.of();
	}

	
    @Override
    public void loadPeople(List<Person> people) {
    	this.people.addAll(people);
    	int indexOfFloor = this.currentFloor - 1;
    	this.peopleByFloor.get(indexOfFloor).removeAll(people);
    }

    @Override
    public void unload(List<Person> people) {
    	this.people.removeAll(people);
    }

    @Override
    public void newPersonWaitingAtFloor(int floor, Person person) {
    	int indexOfFloor = floor - 1;
    	this.peopleByFloor.get(indexOfFloor).add(person);
    }


 @Override
    public void standByAtFloor(int currentFloor) {
    }

@Override
    public void lastPersonArrived() {
    }


    @Override
    public void arriveAtFloor(int floor) {
    	if (!this.destinations.isEmpty()) 
    		this.destinations.remove(0);
    	
    	this.currentFloor = floor;
    }

	private int countWaitingPeople() {
		return peopleByFloor.stream()
			.mapToInt(list -> list.size())
			.sum();
	}



    
    @Override
    public void timeIs(LocalTime time) {
    	this.time = time;
    }

   
}



