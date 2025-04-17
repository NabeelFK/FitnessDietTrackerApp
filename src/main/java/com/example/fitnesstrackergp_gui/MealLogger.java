package com.example.fitnesstrackergp_gui;

import java.time.LocalDate;
import java.util.Scanner;

public class MealLogger {
    private static String fileName = "src/main/resources/com/example/fitnesstrackergp_gui/meals.csv";
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructor that accepts a meal file name as a parameter. If provided meal file name is not null,
     * assign it to the fileName variable.
     */
    public MealLogger(String mealFileName) {
        if (mealFileName != null)
            fileName = mealFileName;
    }

    /**
     * Default constructor --> used when MealLogger is created without a specifying a file name.
     */
    public MealLogger() {
    }

    /**
     * Prompts the user to enter meal details (username, gender, meal names, and calories) and logs the information
     * by creating a new Meal object. The details are then saved to a file in CSV format.
     */
    public void logMeal() {
        System.out.println("Enter Username: ");
        String username = scanner.nextLine();

        System.out.println("Enter Gender (M/F/O): ");
        char genderChar = scanner.nextLine().charAt(0);
        Gender gender = Gender.fromChar(genderChar);

        LocalDate todayDate = LocalDate.now();

        System.out.println("Enter Breakfast Meal: ");
        String breakfast = scanner.nextLine();
        System.out.println("Enter calories in "+breakfast+": ");
        int breakfastCalories = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter Lunch Meal: ");
        String lunch = scanner.nextLine();
        System.out.println("Enter calories in " + lunch+": ");
        int lunchCalories = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter Dinner Meal: ");
        String dinner = scanner.nextLine();
        System.out.println("Enter calories in " + dinner+": ");
        int dinnerCalories = scanner.nextInt();
        scanner.nextLine();

        Meal meal = new Meal(username, gender, todayDate, breakfast, breakfastCalories,
                lunch, lunchCalories, dinner, dinnerCalories);
        FileHandler.writeToFile(fileName, meal.toCSV() + "\n");

        System.out.println("Your meal is saved.");
    }

    /**
     * Prompts the user to enter their username and gender, and allows them to view their meal history and
     * average daily calorie intake over a specified period (7 days, 30 days, or all-time).
     */
    public void viewMealHistory() {
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

        // Ask user to enter 7-day or 30-day average daily calorie intake
        System.out.println("View average daily calorie intake for 7 (Enter 7) or 30 days (Enter 30) " +
                "or All-Time (Enter 0): ");
        String day = scanner.nextLine().trim();

        Integer intDay = 0; // Initiate day as Integer
        try { // Try to convert the day entered by user to Integer
            intDay = Integer.parseInt(day);
        } catch (NumberFormatException e) { // Catches error if user enters anything other than a number and exits program
            System.out.println("Invalid input.");
            return;
        }

        DietHistory dietHistory = new DietHistory(); // Initiate new DietHistory object
        dietHistory.loadCSV(fileName, username, gender); // Load user history from csv

        // Exits program if history does not exist for entered user otherwise prints history.
        if (dietHistory.showDietHistory() == 1){
            return;
        }

        // If user entered a number other than 7 or 30 the average daily calorie intake will not display.
        if (!intDay.equals(7) && !intDay.equals(30) && !intDay.equals(0)) {
            System.out.println("Invalid number entered. Cannot view average daily calorie intake.");
        } else if (intDay.equals(7)) { // Displays average daily calorie intake if user entered 7
            dietHistory.showAverageDailyCalorieIntake(7);
        } else if (intDay.equals(30)) { // Displays average daily calorie intake if user entered 30
            dietHistory.showAverageDailyCalorieIntake(30);
        } else if (intDay.equals(0)) {
            dietHistory.showAverage();
        }
    }

    /**
     * Returns the full meal history and average calories for a user (all-time),
     * suitable for GUI display.
     *
     * @param username The username to fetch meal history for.
     * @return A formatted string of meal records and calorie averages.
     */
    public String getMealHistory(String username) {
        StringBuilder result = new StringBuilder();

        for (Gender gender : Gender.values()) {
            DietHistory dietHistory = new DietHistory();
            dietHistory.loadCSV(fileName, username, gender);

            if (dietHistory.getDayCount() > 0) {
                result.append("Meal History for ").append(username).append(" (").append(gender).append("):\n\n");
                result.append(dietHistory.getFormattedMeals());
                result.append("\nAverage Calories/Day: ").append(String.format("%.2f", dietHistory.getAverage()));
                return result.toString();
            }
        }

        return "No meal history found for user: " + username;
    }

}
