package gr.unipi.movieapp;

import java.util.List;
import java.util.Scanner;

import exception.MovieAPIException;
import model.MovieInfo;
import my.app.MovieAPI;
import services.MovieDBService;


public class Application {

	public static void main(String[] args) {
		final MovieDBService movieDBService = MovieAPI.getMovieDBService();

		System.out.println("Welcome the the MovieApp");
		
		// Initialize Scanner
		Scanner sc;

		while (true) {
			System.out.println("Select one from the following options: ");
			System.out.println("1. Show top rated movies for specified year.");
			System.out.println("2. Search for movie.");
			System.out.println("3. Exit.");
			System.out.println("Your choice: ");

			sc = new Scanner(System.in);
			String input = sc.nextLine();

			System.out.println("Your choice: " + input);
			
			if(input.equals("1")) {
				System.out.println("Selected 1");
				System.out.println("Enter year: ");
				try {
					final String yearInput = sc.nextLine();
					final int yearNumber = Integer.parseInt(yearInput);
					List<MovieInfo> results;
					results = movieDBService.getPopularMoviesForYear(yearNumber);
					System.out.println("Results are: ");
					System.out.println(results);
				} catch (MovieAPIException e) {
					System.err.println(e.getMessage());
				}
			}
			else if(input.equals("2")){
				System.out.println("Selected 2");
				System.out.print("Enter search parameter: ");
				String searchParam = sc.nextLine();
				try {
					final List<MovieInfo> results = movieDBService.searchForMovies(searchParam);
					System.out.println("Results are: ");
					System.out.println(results);
				} catch (MovieAPIException e) {
					System.err.println(e.getMessage());
				}
			}
			else if(input.equals("3")) {
				System.out.println("Selected 3");
				sc.close();
				System.exit(0);
			}
			else {
				System.out.println("Invalid input");
			}

		}
	}

}