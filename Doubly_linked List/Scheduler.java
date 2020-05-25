/**
 * Scheduler.java
 *
 * @author Simon Zhang
 */

import java.util.Scanner;

public class Scheduler {
    public static void main(String[] args) {
        String choice, yes_no;
        int int_choice;
        List<String> lists = new List<>();
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Scheduler App!\n");
        System.out.println("You have no upcoming events!");
        printMenu();
        System.out.print("Enter your choice: ");
        choice = input.nextLine();

        while (!(choice.equalsIgnoreCase("x"))) {
            if (is_valid_input(choice)) {
                if (choice.equalsIgnoreCase("a")) {
                    System.out.print("\nPlease enter an event: ");
                    choice = input.nextLine();
                    lists.addFirst(choice);
                    if (lists.getLength() == 1) {
                        System.out.println("\nYour Current Schedule: \n");
                        lists.printNumberedList();
                    } else {
                        System.out.println("\nYou just added the following event to your schedule: " + choice);
                        System.out.println("\nYour Current Schedule: \n");
                        lists.printNumberedList();
                        System.out.print("\nWould you like to move this event up in your schedule (Y/N): ");
                        yes_no = input.nextLine();
                        if (yes_no.equalsIgnoreCase("y")) {
                            System.out.print("Enter the numbers of places: ");
                            int_choice = Integer.parseInt(input.nextLine());
                            while (int_choice >= lists.getLength() || int_choice <= 0) {
                                System.out.println("Sorry that input is invalid!");
                                System.out.print("\nEnter the numbers of places: ");
                                int_choice = Integer.parseInt(input.nextLine());
                            }
                            lists.placeIterator();
                            lists.removeIterator();
                            lists.placeIterator();
                            for (int i = 0; i < int_choice - 1; i++) {
                                lists.advanceIterator();
                            }
                            lists.addIterator(choice);

                            System.out.println("\nYour Current Schedule: \n");
                            lists.printNumberedList();

                        } else {
                            System.out.println("\nYour Current Schedule: \n");
                            lists.printNumberedList();
                        }
                    }

                }

                if (choice.equalsIgnoreCase("r")) {
                    System.out.print("Enter the number of the event to remove: ");
                    int_choice = Integer.parseInt(input.nextLine());
                    while (int_choice > lists.getLength()) {
                        System.out.println("Sorry that input is invalid!");
                        System.out.print("\nEnter the numbers of places: ");
                        int_choice = Integer.parseInt(input.nextLine());
                    }
                    lists.placeIterator();
                    for (int i = 0; i < int_choice - 1; i++) {
                        lists.advanceIterator();
                    }
                    System.out.println("\nRemoving: " + choice);
                    lists.removeIterator();
                    System.out.println("\nYour Current Schedule: \n");
                    lists.printNumberedList();
                }

            } else {
                System.out.println("\nInvalid choice!");
            }
            printMenu();
            System.out.print("Enter your choice: ");
            choice = input.nextLine();
        }
        System.out.println("\nGoodbye!");


    }

    public static void printMenu() {
        System.out.println("\nSelect from the following options:");
        System.out.println("A: Add an event");
        System.out.println("R: Remove an event");
        System.out.println("X: Exit\n");
    }

    public static boolean is_valid_input(String input) {
        return input.equalsIgnoreCase("a") ||
                input.equalsIgnoreCase("r") ||
                input.equalsIgnoreCase("X");
    }
}
