/**
 * MovieDatabase.java
 * @author Daniil Durnev
 * @author Simon Zhang
 * CIS 22C, Lab 5
 */

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class MovieDatabase {
    private static final int NUM_MOVIES = 26;
    Hash<Movie> ht = new Hash<>(NUM_MOVIES * 2);
    BST<Movie> bst = new BST<>();

    public static void main(String[] args) throws IOException {

        MovieDatabase mdb = new MovieDatabase();

        String title;
        String director;
        int year;
        double grossMillions;
        File file = new File("D:/Apps/untitled/src/movies.txt");
        Scanner input = new Scanner(file);

        while (input.hasNextLine()) {
            title = input.nextLine();
            director = input.nextLine();
            year = Integer.parseInt(input.nextLine());
            grossMillions = Double.parseDouble(input.nextLine());

            if(input.hasNext()) {
                input.nextLine();
            }

            Movie movie = new Movie(title, director, year, grossMillions);
            mdb.bst.insert(movie);
            mdb.ht.insert(movie);
        }

        input = new Scanner(System.in);
        boolean finished = false;

        System.out.println("Welcome to the Bond Movie Database!\n");

        while (!finished) {
            System.out.println("Please select from one of the following options:\n");

            System.out.println("A. Add a Movie");
            System.out.println("D. Display all Movies");
            System.out.println("R. Remove a Movie");
            System.out.println("S. Search for a Movie");
            System.out.println("X. Exit\n");

            System.out.print("Enter your choice: ");
            String selection = input.nextLine();

            switch (selection) {
                case "A": {
                    System.out.println("\nAdding a movie!\n");

                    System.out.print("Enter the title: ");
                    title = input.nextLine();
                    System.out.print("Enter the director: ");
                    director = input.nextLine();
                    System.out.print("Enter the year: ");
                    year = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter the gross in millions: $");
                    grossMillions = input.nextDouble();
                    input.nextLine();

                    Movie newMovie = new Movie(title, director, year, grossMillions);
                    mdb.ht.insert(newMovie);
                    mdb.bst.insert(newMovie);

                    System.out.println("\n" + title + " was added!\n");

                    break;
                }
                case "D": {
                    System.out.println("\nPlease select one of the following options:\n");
                    System.out.println("S. Sorted");
                    System.out.println("U. Unsorted\n");

                    System.out.print("Enter your choice: ");
                    String Dselection = input.nextLine();

                    switch (Dselection) {
                        case "S": {
                            System.out.println("\nDisplaying Movies:\n");
                            mdb.bst.inOrderPrint();
                            break;
                        }
                        case "U": {
                            System.out.println("\nDisplaying Movies:\n");
                            System.out.println(mdb.ht);
                            break;
                        }
                    }

                    break;
                }
                case "R": {
                    System.out.println("\nRemoving a movie!\n");
                    System.out.print("Enter the title: ");
                    title = input.nextLine();
                    System.out.print("Enter the director: ");
                    director = input.nextLine();

                    Movie dummy = new Movie(title, director, -1, -1);

                    try {
                        mdb.ht.remove(dummy);
                        mdb.bst.remove(dummy);

                        System.out.println("\n" + director + "'s " + title + " was removed!\n");
                    } catch (NoSuchElementException e) {
                        System.out.println("\nI cannot find " + director + "'s " + title + " in the database.\n");
                    }

                    break;
                }
                case "S": {
                    System.out.println("\nSearching for a movie!\n");
                    System.out.print("Enter the title: ");
                    title = input.nextLine();
                    System.out.print("Enter the director: ");
                    director = input.nextLine();

                    Movie dummy = new Movie(title, director, -1, -1);

                    boolean found = mdb.bst.search(dummy);
                    if (found) {
                        System.out.println("\n" + director + "'s " + title + " is in the database!\n");
                    } else {
                        System.out.println("\n" + director + "'s " + title + " is not in the database.\n");
                    }
                    break;
                }
                case "X": {
                    finished = true;
                    break;
                }
                default: {
                    System.out.println("\nInvalid Selection!\n");
                    break;
                }
            }
        }

        System.out.println("\nGoodbye!");
    }
}