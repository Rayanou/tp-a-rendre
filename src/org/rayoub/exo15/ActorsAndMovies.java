/*Rayane CHIKHI + Ayoub TAIHI 
  14-03-2021 */

package org.rayoub.exo15;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;


public class ActorsAndMovies {

    public static void main(String[] args) {

        ActorsAndMovies actorsAndMovies = new ActorsAndMovies();
        Set<Movie> movies = actorsAndMovies.readMovies();
        
        //1
        System.out.println("Combien y a t'il de films?");
        System.out.println("There is in the file : " + movies.size() + " movies");

           
        				
        /* Q2 : Nombre d'acteurs référencés dans ce fichier */
        /* 1. Utilisation de flatMap : méthode incluant un stream de movies et retournant 
        un stream d'acteurs ; après avoir mis "à plat" tous les streams de movies */
        // 2. Elimination des doublons qui existent dans le code (*) //

  	// (*) : java.util.List<Actor> actors = movies.stream().flatMap(movie -> movie.actors().stream()).collect(Collectors.toList());
  	
        long numberOfActors = movies.stream()
  			.flatMap(movie -> movie.actors().stream())
  			.distinct().count(); 
  	 System.out.println("\nHow many actors in the file?");
      System.out.println("\nQ2 : There is in the file : " + numberOfActors + "actors");		
 
      
      /* Q3 : Nombre d'années référencées dans ce fichier */
      
      long numberOfYears = movies.stream()
    		  .map(movie -> movie.releaseYear())
    		  .distinct().count();
      System.out.println("\nQ3 : Number of years referenced in the file containing films : "+numberOfYears);		
        
      

      /* Q4 : Année de sortie du film le plus vieux et le plus récent */
      // a- Le plus ancien //

	    System.out.println("\nQ4 : - The year of release of the oldest film (from the file) : "
	    +movies.stream().map(movie -> movie.releaseYear())
	    .max(Integer::compare));

      // b- Le plus récent //

	    System.out.println("\n       - The year of release of the latest film (from the file) :"
	    +movies.stream().map(movie -> movie.releaseYear())
	    .min(Integer::compare));    
      
	    /* Q5 : Année de sortie du plus grand nombre de films */
        
        Entry<Integer, Long> numberOfMoviesInOneYear = movies.stream().
        		               collect(Collectors.groupingBy(
        		            		   Movie::releaseYear, Collectors.counting()
        		            		   ))
        		               .entrySet().stream()
        		               .max(Entry.comparingByValue())
        		               .get();
        
        int yearWithHighestNumberOfMovie = numberOfMoviesInOneYear.getKey();
        /* Ou :  long mostMoviesOneYear = movies.stream()
		.map(movie -> movie.releaseYear())
		.max(Integer::max).distinct()
		.count();
*/
        
        long numberOfMoviesReleasedThatYear = numberOfMoviesInOneYear.getValue();
        
        System.out.println("\nQ5 : - The year of release of the biggest number of movies (from the file) : "+yearWithHighestNumberOfMovie);
        System.out.println("\n     - The number of movies released in that year : "+numberOfMoviesReleasedThatYear);    

      
    /* Q6 : film avec le plus grand nombre d'acteurs */ 
    /* 1. Interface fonctionnelle Comparator pour la comparaison 
	    entre les films en termes de taille */
    /* 2. Utilisation d'un stream et retour d'un entier qui 
     exprime le film comportant le max d'acteurs */
 
	 Comparator<Movie> compareMovies = Comparator.comparing(movie -> movie.actors().size());
	 Movie movieWithHighestActors = movies.stream().max(compareMovies).orElseThrow();
	 System.out.println("\nQ4 : - Max of actors in one movie : "+movieWithHighestActors);		
	 System.out.println("       - Number of its actors : "+movieWithHighestActors.actors().size());		
      
	
  	 
	 /* Q7 : Acteur ayant joué le plus grand nombre de films */
     // Histogramme      
  Map<Actor, Long> mapOfActors = movies.stream().flatMap(movie -> movie.actors()
		  .stream()).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
  Actor cateBlanchett = new Actor("Cate","Blanchett");
  Actor bloomOrlando = new Actor("Bloom","Orlando");
  System.out.println("\nQ7 : - Number of films Cate Blanchett has played : "+mapOfActors.get(cateBlanchett));
  System.out.println("\n      - Number of films Bloom Orlando has played : "+mapOfActors.get(bloomOrlando));
	 
	 
//  /* Q8 : Acteur ayant joué le plus grand nombre de films en une année */ 
//  /* 1. A la différence de Q7, utilisation d'un downStreamCollector 
//  pour la réalisation de ce traitement (rangement des élément du flatMapping en une année) */
//  /* 2. Utilisation de "collectingAndThen" : Post-traitement 
//  d'un Collector ("stream" de la table de hachage et  . Prise en paramètre
//  d'un Collector et d'une fonction de référence */
  
		  Collector<Movie, Integer, Entry<Actor, Long>> collector = 
		          Collectors.collectingAndThen(
		      Collectors.flatMapping(
		        m -> m.actors().stream(), 
		         Collectors.groupingBy(
		           Function.identity(),
		           Collectors.counting()
		         )
		       ),
		      map -> map.entrySet().stream()
		           .max(Comparator.comparing(Entry::getValue))
		           .orElseThrow()
		          );
		        
		        Map<Integer, Entry<Actor, Long>> mapWithHighestActorPerYear = 
		          movies.stream()
		              .collect(
		              Collectors.groupingBy(
		                Movie::releaseYear,
		                collector
		              )
		              );
		        Entry <Integer, Entry<Actor, Long>> ActorWithMaxMovies2 =
		        mapWithHighestActorPerYear.entrySet().stream()
		           .max(Comparator.comparing(e -> e.getValue().getValue()))
		           .orElseThrow();
        System.out.println("\nQ8 : The actor who has played the most number of movies in one year : ");  

        System.out.println( ActorWithMaxMovies2.getValue().getKey().firstName + " "
        + ActorWithMaxMovies2.getValue().getKey().lastName + " is the actor who has played the"
        + " most movies in a year.\n\tHe played in " + ActorWithMaxMovies2.getValue()
        .getValue() + " movies in " + ActorWithMaxMovies2.getKey());


  
    }

    public Set<Movie> readMovies() {

        Function<String, Stream<Movie>> toMovie =
                line -> {
                    String[] elements = line.split("/");
                    String title = elements[0].substring(0, elements[0].lastIndexOf("(")).trim();
                    String releaseYear = elements[0].substring(elements[0].lastIndexOf("(") + 1, elements[0].lastIndexOf(")"));
                    if (releaseYear.contains(",")) {
                        // Movies with a coma in their title are discarded
                        return Stream.empty();
                    }
                    Movie movie = new Movie(title, Integer.valueOf(releaseYear));


                    for (int i = 1; i < elements.length; i++) {
                        String[] name = elements[i].split(", ");
                        String lastName = name[0].trim();
                        String firstName = "";
                        if (name.length > 1) {
                            firstName = name[1].trim();
                        }

                        Actor actor = new Actor(lastName, firstName);
                        movie.addActor(actor);
                    }
                    return Stream.of(movie);
                };

        try (FileInputStream fis = new FileInputStream("files/movies-mpaa.txt.gz");
             GZIPInputStream gzis = new GZIPInputStream(fis);
             InputStreamReader reader = new InputStreamReader(gzis);
             BufferedReader bufferedReader = new BufferedReader(reader);
             Stream<String> lines = bufferedReader.lines();
        ) {

            return lines.flatMap(toMovie).collect(Collectors.toSet());

        } catch (IOException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }

        return Set.of();
    }
}
