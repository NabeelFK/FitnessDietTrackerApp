package com.example.fitnesstrackergp_gui;

import java.util.Scanner;

public class Goal {
    private static String fileName = "goals.csv";
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructor that accepts a goalFileName as a parameter. If provided goal file name is not null,
     * assign it to the fileName variable.
     */
    public Goal(String goalFileName) {
        if (goalFileName != null)
            fileName = goalFileName;
    }

    /**
     * Default constructor --> used when Goal is created without a specifying a file name.
     */
    public Goal() {
    }

    /**
     * Prompts the user to enter their fitness goals, including username, gender, target weight,
     * workout duration, and sleep duration.
     * The method creates a FitnessGoal object with the input data and saves it to a file in CSV format.
     * Inputted data is used to create a new FitnessGoal object, which is then written to a file.
     */
    public void setFitnessGoals() {
        System.out.println("Enter Username: ");
        String username = scanner.nextLine();

        System.out.println("Enter Gender (M/F/O): ");
        char genderChar = scanner.nextLine().charAt(0);
        Gender gender = Gender.fromChar(genderChar);

        System.out.println("Enter Target Weight (kg): ");
        float targetWeight = scanner.nextFloat();

        System.out.println("Enter Workout Duration (minutes/day): ");
        int workoutMinutes = scanner.nextInt();

        System.out.println("Enter Sleep Duration (hours/day): ");
        float sleepHours = scanner.nextFloat();
        scanner.nextLine();

        FitnessGoal goal = new FitnessGoal(username, gender, targetWeight, workoutMinutes, sleepHours);
        FileHandler.writeToFile(fileName, goal.toCSV() + "\n");

        System.out.println("Your Goals are saved!");
    }

    /**
     * Prompts the user to enter their username and gender, then displays their fitness goal history.
     * If the user provides invalid input or no fitness goals are found for the user, the method will
     * prompt the user to set new fitness goals.
     */
    public void viewFitnessGoals() {
        System.out.println("Enter Username: "); // Ask user for Name
        String username = scanner.nextLine();

        if (username.isEmpty()) {
            System.out.println("Invalid Input. Must enter a valid name.");
            return;
        }

        System.out.println("Enter Gender (M/F/O): "); // Ask user for Gender
        Gender gender;
        try {
            String genderString = scanner.nextLine();

            if (genderString.isEmpty()) {
                System.out.println("Invalid Input.");
                return;
            }

            char genderChar = genderString.charAt(0);
            gender = Gender.fromChar(genderChar);

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input.");
            return;
        }

        ViewGoals goalHistory = new ViewGoals(); // Initiate new ViewGoals object
        goalHistory.loadCSV(fileName, username, gender); // Load goal history from csv for user
        // If no goals currently exist for user, call setFitnessGoals() to set new goal.
        if (goalHistory.showGoalHistory(username, gender) == 1) {
            setFitnessGoals();
        }
    }
}
