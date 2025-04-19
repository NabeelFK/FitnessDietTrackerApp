package com.example.fitnesstrackergp_gui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// DietHistory handles parsing and displaying user's logged meals and average daily calorie intake
public class DietHistory extends History implements Comparable<DietHistory> {

    // Creates an array list to store all meal objects for user.
    private final ArrayList<Meal> MEALS = new ArrayList<>();

    /**
     * Loads meal log data from a CSV file, parses each entry, and updates the list of meals.
     * The method reads all the lines matching the provided username and gender, parses each line
     * to create a new `Meal` object, and adds the valid meals to the list.
     * Updates the total calories and the count of days with logged meals.
     *
     * @param path The file path to the CSV file containing the meal logs.
     * @param username The username to match the meal logs to.
     * @param gender The gender of the user, to match the username and meal logs to.
     */
    @Override
    public void loadCSV(String path, String username, Gender gender) {
        List<String> mealLogs = FileHandler.readAllMatches(path, username, gender.toChar());

        for (String line : mealLogs) {
            Meal newMeal = parseMeal(line);
            if (newMeal != null) {
                MEALS.add(newMeal);
                total += newMeal.getTotalCalories();
                dayCount++;
            }
        }
    }

    /**
     * Parses a CVS containing meal information and returns a Meal object.
     * String should contain details for a person's first name, gender, date, and three meals
     * (breakfast, lunch, and dinner) with their respective calorie counts.
     * The method splits the input string by commas and processes each part to extract relevant information
     * for constructing a Meal object.
     *
     * @param info A CVS containing meal information in the following format:
     *             <firstName>, <gender>, <date>, <breakfastName>(<breakfastCalories>),
     *             <lunchName>(<lunchCalories>), <dinnerName>(<dinnerCalories>).
     * @return A new Meal object populated with the parsed data, or null if the input is invalid
     *         or an error occurs during parsing.
     */
    private Meal parseMeal(String info) { // Parses meal
        try {
            String[] infoSplit = info.split(","); // Initialize array of each item seperated by a comma

            String firstName = infoSplit[0].trim(); // Get first name
            Gender gender = Gender.fromChar(infoSplit[1].trim().charAt(0)); // Get Gender
            LocalDate date = LocalDate.parse(infoSplit[2]); // Get current date

            String breakfast = infoSplit[3].split("\\(")[0].trim(); // Get name of breakfast
            int breakfastCals = Integer.parseInt(infoSplit[3].split("\\(")[1].replace(")", "").trim()); // Get calories of breakfast

            String lunch = infoSplit[4].split("\\(")[0].trim(); // Get name of lunch
            int lunchCals = Integer.parseInt(infoSplit[4].split("\\(")[1].replace(")", "").trim()); // Get calories of lunch

            String dinner = infoSplit[5].split("\\(")[0].trim(); // Get name of dinner
            int dinnerCals = Integer.parseInt(infoSplit[5].split("\\(")[1].replace(")", "").trim()); // Get calories of dinner

            return new Meal(firstName, gender, date, breakfast, breakfastCals, lunch, lunchCals, dinner, dinnerCals); // Return new meal object

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Displays the diet history by printing the details of all meals stored in the MEALS list.
     * If the MEALS list is empty, it outputs a message indicating that no meals are found.
     * Otherwise, it iterates through the list and prints each meal's information.
     *
     * @return An integer value indicating the result of the operation:
     *         - Returns 1 if no meals are found.
     *         - Returns 0 if the meals are successfully displayed.
     */
    public int showDietHistory() {
        if (MEALS.isEmpty()) {
            System.out.println("No meals found.");
            return 1;
        } else {
            for (Meal meal : MEALS) {
                System.out.println(meal);
            }
        }
        return 0;
    }

    /**
     * Sorts Meals in the Meal list by descending order.
     */
    private void sortMealsByDate() {
        Collections.sort(MEALS, new Comparator<Meal>() {
            @Override
            public int compare(Meal meal1, Meal meal2) {
                return meal2.getDate().compareTo(meal1.getDate());
            }
        });
    }

    /**
     * Calculates the average daily calorie intake based on the total calories consumed across all meals.
     * Sums the total calories from all meals in the MEALS list and divides the result by the specified
     * number of days to compute the average daily intake.
     *
     * @param days The number of days to calculate the average daily calorie intake over.
     *             This value should be greater than 0.
     * @return The average daily calorie intake as a double. If the MEALS list is empty, it returns 0.
     */
    public double getAverageDailyCalorieIntake(int days) {
        if (MEALS.isEmpty()) { return 0; }

        sortMealsByDate();

        double totalCalories = 0;

        for (int i = 0; i < days; i++) {
            totalCalories += MEALS.get(i).getTotalCalories();
        }

        return totalCalories / days;
    }

    /**
     * Displays the average daily calorie intake over a specified number of days.
     * If the specified number of days exceeds the available meals in the history,
     * the method adjusts the number of days to the available meals and informs the user.
     * It then calculates and prints the average daily calorie intake over the adjusted number of days.
     *
     * @param days The number of days over which to calculate the average daily calorie intake.
     *             If the number of days exceeds the available meals, it is adjusted to the number of available meals.
     */
    public void showAverageDailyCalorieIntake(int days) {

        if (days > MEALS.size()) {
            System.out.println("Not enough meals in History to calculate daily average over " + days + " days. Average over " + MEALS.size() + " day(s).");
            days = MEALS.size();
        }

        System.out.println("Average Daily Calorie Intake over " + days + " day(s): "
                + getAverageDailyCalorieIntake(days));
    }


    /**
     * Compares this DietHistory object with another DietHistory object based on their average daily calorie intake.
     * Calculates the average daily calorie intake for both objects and returns a comparison result.
     * The comparison is based on the result of calling the `getAverageDailyCalorieIntake` method on both objects.
     * The comparison is done using `Double.compare()` to handle the comparison of calculated average
     * daily calorie intake.
     *
     * @param other The DietHistory object to compare this object with.
     * @return A negative integer, zero, or a positive integer as this object's average daily calorie intake is less than,
     *         equal to, or greater than the specified object's average daily calorie intake.
     */
    @Override
    public int compareTo(DietHistory other) {
        return Double.compare(this.getAverageDailyCalorieIntake(MEALS.size()),
                other.getAverageDailyCalorieIntake(MEALS.size()));
    }

    public String getFormattedMeals() {
        sortMealsByDate();

        StringBuilder sb = new StringBuilder();
        for (Meal meal : MEALS) {
            sb.append(meal).append("\n\n");
        }
        return sb.toString();
    }

    public int getDayCount() {
        return this.dayCount;
    }

}
