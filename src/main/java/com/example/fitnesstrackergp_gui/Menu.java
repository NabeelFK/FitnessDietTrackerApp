package com.example.fitnesstrackergp_gui;

import java.util.*;

class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<String> options = new ArrayList<>();

    static {
        options.add("Exit");
        options.add("Create New Account");
        options.add("View Profile History");
        options.add("Log Daily Meals");
        options.add("View Diet History");
        options.add("Set Fitness Goals");
        options.add("View Fitness Goals");
    }

    /**
     * Displays the main menu for the Diet Tracker application and allows the user to select an option.
     * This method runs in a loop, presenting the user with a list of options and prompting them to
     * make a choice. Based on the user's input, the method performs the corresponding action, such as
     * creating a new account, setting/viewing goals, viewing profile history, logging daily meals.
     * If the user selects an invalid option, the menu will prompt them to try again.
     *
     * @param tracker The FitnessDietTracker object used to perform the actions associated with the menu options.
     */
    public void displayMenu(FitnessDietTracker tracker) {
        while (true) {
            System.out.println("\nWelcome to the Diet Tracker!\nPlease enter the number associated with the " +
                    "option you'd like to pick!");
            for (int i = 0; i < options.size(); i++) {
                System.out.println(i + ". " + options.get(i));
            }
            System.out.print("Please enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    System.out.println("Bye. Have a good day.");
                    return; //exit the program
                case 1:
                    tracker.createNewAccount();
                    break;
                case 2:
                    tracker.viewProfileHistory();
                    break;
                case 3:
                    tracker.logDailyMeals();
                    break;
                case 4:
                    tracker.viewDietHistory();
                    break;
                case 5:
                    tracker.setGoals();
                    break;
                case 6:
                    tracker.viewGoals();
                    break;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
