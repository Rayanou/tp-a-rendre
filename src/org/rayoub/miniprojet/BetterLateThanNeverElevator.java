/* Ayoub Taihi
 * Rayane Chikhi 
  Mini-Projet partie 2 */


package org.paumard.elevator.student;

import org.paumard.elevator.Building;
import org.paumard.elevator.Elevator;
import org.paumard.elevator.event.DIRECTION;
import org.paumard.elevator.model.Person;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DumbElevatorForVIP implements Elevator {
    private static final int ANGER_LIMIT_THRESHOLD = 120;
    private DIRECTION direction = DIRECTION.UP;
    private int currentFloor = 1;
    private List<List<Person>> peopleByFloor = List.of();          //étage demandé
    private List<Person> people = new ArrayList<>();
    private final int capacity;
    private LocalTime time;
    private List<Integer> destinations = List.of();
    private boolean lastPersonArrived = false;

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
    
        if (!this.destinations.isEmpty() && !this.lastPersonArrived) {
            return this.destinations;
        }
        
        int numberOfPeopleWaiting = countWaitingPeople();       
         
        if (numberOfPeopleWaiting > 0) {
            
            List<Integer> destinations = destinationsToPickUpAngryPeople();
            if (!destinations.isEmpty()) {
                this.destinations = destinations;
                return this.destinations;
            }
            
            List<Integer> nonEmptyFloors = findNonEmptyFloor();
            int nonEmptyFloor = nonEmptyFloors.get(0);
            

            if (numberOfPeopleWaiting >= Building.ELEVATOR_CAPACITY) {
                if (numberOfPeopleWaiting == Building.ELEVATOR_CAPACITY) {
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
    
                    if (!destinations.isEmpty()) {
                    List<Integer> destination = destinationsToPickUpAngryPeople();
                    this.destinations = destination;
                    return this.destinations;    
                }     
        }    
        
            
            
            if(this.time.getHour() <= 15 && this.time.getHour() > 10) {
               
             int indexOfCurrentFloor = this.currentFloor - 1;
               
               List <Integer> close = new ArrayList<>();
            List<Person> waitingListForCurrentFloor = this.peopleByFloor.get(indexOfCurrentFloor);
            List<Integer> destinationFloorsForCurrentFloor = findDestinationFloors(waitingListForCurrentFloor);
            Map<Integer, Long> numberOfPeopleInDestination = waitingListForCurrentFloor.stream().collect(Collectors.groupingBy(Person::getDestinationFloor,Collectors.counting()));            
            Entry<Integer, Long> bestDestinationCountingNumberOfPeople = numberOfPeopleInDestination.entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow();
                
            int bestDestination = bestDestinationCountingNumberOfPeople.getKey();

            int numberOfPersonsGoingInTheNearestDestination = Collections.frequency(destinationFloorsForCurrentFloor,destinationFloorsForCurrentFloor.get(0));
               
            int numberOfPersonsGoingInTheHighestDestination = Collections.frequency(destinationFloorsForCurrentFloor,destinationFloorsForCurrentFloor.get(1));                
                
            int maxNumberOfPeople = Math.max(numberOfPersonsGoingInTheNearestDestination, numberOfPersonsGoingInTheHighestDestination);
                
        for(int indexDestination = 0; indexDestination < Building.MAX_FLOOR; indexDestination++) {
            if(Math.abs(currentFloor - destinationFloorsForCurrentFloor.get(indexDestination)) > 0) {
                close.add(destinationFloorsForCurrentFloor.get(indexDestination));
        
                   if(destinationInAHigherFloors(destinationFloorsForCurrentFloor) || destinationInALowerFloors(destinationFloorsForCurrentFloor)) { 
                         this.destinations = destinationFloorsForCurrentFloor;
                              return this.destinations;           
            }
            }
         }
       }                    
            if (nonEmptyFloor != this.currentFloor) 
                return List.of(nonEmptyFloor);
             
    
            else{
                int indexOfCurrentFloor = this.currentFloor - 1;
                List<Person> waitingListForCurrentFloor = 
                        this.peopleByFloor.get(indexOfCurrentFloor);
                
                List<Integer> destinationFloorsForCurrentFloor = 
                        findDestinationFloors(waitingListForCurrentFloor);
                this.destinations  = destinationFloorsForCurrentFloor;
                return this.destinations;
            }
        
        
        
        }
           return List.of(1);

    } 

    
private List<Integer> destinationsToPickUpAngryPeople() {
        
        for (int indexFloor = 0 ; indexFloor < Building.MAX_FLOOR ; indexFloor++) {
            List<Person> waitingList = this.peopleByFloor.get(indexFloor);
            if (!waitingList.isEmpty()) {
                Person mostPatientPerson = waitingList.get(0);
                LocalTime arrivalTime = mostPatientPerson.getArrivalTime();
                Duration waitingTime = Duration.between(arrivalTime, this.time); 
                long waitingTimeInSeconds = waitingTime.toSeconds();
                if (waitingTimeInSeconds >= ANGER_LIMIT_THRESHOLD) {
                    List<Integer> result = List.of(indexFloor + 1, mostPatientPerson.getDestinationFloor());
                    return new ArrayList<>(result);
                }
            }
        }
        return List.of();
    }

    
    
private List<Integer> sameDestinationForMostPeopleWaiting(){
        
        for (int indexFloor = 0 ; indexFloor < Building.MAX_FLOOR ; indexFloor++) {
            List<Person> waitingList = this.peopleByFloor.get(indexFloor);
            if (!waitingList.isEmpty()) {
                
                List<Integer> nonEmptyFloors = findNonEmptyFloor();
                Entry<Integer, Long> maxSameDestinationForPersonsWaiting = nonEmptyFloors.stream()
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
    
    
    

private boolean destinationInAHigherFloors(List<Integer> sortedDestinationFromCurrentFloor) {
        return sortedDestinationFromCurrentFloor.get(0) > currentFloor && sortedDestinationFromCurrentFloor.get(1) > currentFloor;
    }

    
    private boolean destinationInALowerFloors(List<Integer> sortedDestinationFromCurrentFloor) {
        return sortedDestinationFromCurrentFloor.get(0) < currentFloor && sortedDestinationFromCurrentFloor.get(1) > currentFloor;
    }
    
    
    private List<Integer> findDestinationFloors(List<Person> waitingListForCurrentFloor) {
        return waitingListForCurrentFloor.stream()
            .map(person -> person.getDestinationFloor())
            .distinct()
            .sorted()
            .collect(Collectors.toList());
    }

private List<Integer> findNonEmptyFloor() {
        for (int indexFloor = 0 ; indexFloor < Building.MAX_FLOOR ; indexFloor++) {
            if (!peopleByFloor.get(indexFloor).isEmpty()) {
                return List.of(indexFloor + 1);
            }
        }
        return List.of(-1);
    }

private int countWaitingPeople() {
        return peopleByFloor.stream()
            .mapToInt(list -> list.size())
            .sum();
    }

    @Override
public void arriveAtFloor(int floor) {
        if (!this.destinations.isEmpty()) {
            this.destinations.remove(0);
        }
        this.currentFloor = floor;
    }

    @Override
public void loadPeople(List<Person> people) {

        this.people.addAll(people);
        int indexFloor = this.currentFloor - 1;
        this.peopleByFloor.get(indexFloor).removeAll(people);
    
   }
    @Override
public void unload(List<Person> people) {
        this.people.removeAll(people);
    }

    @Override
public void newPersonWaitingAtFloor(int floor, Person person) {
        int indexFloor = floor - 1;
        this.peopleByFloor.get(indexFloor).add(person);
    }

    @Override
public void lastPersonArrived() {
        this.lastPersonArrived = true;
    }

    
    @Override
public void timeIs(LocalTime time) {
        LocalTime arrivalTime = LocalTime.of(6,0,0);
        Duration timeBetweenAscendAndDescend = Duration.between(arrivalTime,time);
        this.time = time;
    }

    
    
    @Override
public void standByAtFloor(int currentFloor) {
         this.currentFloor = currentFloor;
    }
}