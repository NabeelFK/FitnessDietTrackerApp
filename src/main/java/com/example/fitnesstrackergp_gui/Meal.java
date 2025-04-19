package com.example.fitnesstrackergp_gui;

import java.time.LocalDate;

public class Meal implements Trackable {
    private final String username;
    private final Gender gender;
    private final LocalDate date;
    private final String breakfast;
    private final int breakfastCalories;
    private final String lunch;
    private final int lunchCalories;
    private final String dinner;
    private final int dinnerCalories;

    /**
     * Constructor initializes specified user details, date, and meal inputs (breakfast, lunch, and
     * dinner and their calorie counts).
     *
     * @param username The username of user inputting data.
     * @param gender The gender of the user (M/F/O).
     * @param date The date the meal was consumed.
     * @param breakfast The name of the breakfast meal.
     * @param breakfastCalories The number of calories consumed.
     * @param lunch The name of the lunch meal.
     * @param lunchCalories The number of calories consumed.
     * @param dinner The name of the dinner meal.
     * @param dinnerCalories The number of calories consumed.
     */
    public Meal(String username, Gender gender, LocalDate date, String breakfast, int breakfastCalories,
                String lunch, int lunchCalories, String dinner, int dinnerCalories) {
        this.username = username;
        this.gender = gender;
        this.date = date;
        this.breakfast = breakfast;
        this.breakfastCalories = breakfastCalories;
        this.lunch = lunch;
        this.lunchCalories = lunchCalories;
        this.dinner = dinner;
        this.dinnerCalories = dinnerCalories;
    }

    /**
     * Calculates and returns the total number of calories consumed from all meals (breakfast, lunch, and dinner).
     *
     * @return The total number of calories consumed across meals.
     */
    public int getTotalCalories() {
        return breakfastCalories + lunchCalories + dinnerCalories;
    }

    /**
     * Converts the Meal object to a CSV format string.
     * Method returns a string representation of the Meal object with resulting string containing the
     * username, gender, date, and the details of each meal.
     *
     * @return A CSV-formatted string representing the Meal object, including the following fields:
     */

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toCSV() {
        return username + "," + gender.toChar() + "," + date + "," + breakfast + " (" + breakfastCalories + ")" +
                "," + lunch + " (" + lunchCalories + ")" + "," + dinner + " (" + dinnerCalories + ")";
    }

    /**
     * Method generates a summary of the meal details into human-readable form. This includes the date, names of
     * meals (breakfast, lunch, dinner), respective calorie counts, and the total calories consumed for the day.
     *
     * @return A string containing a formatted summary of the meal details and total calories for the day.
     */
    @Override
    public String toString() {
        return "Date: " + date + " | Breakfast: " + breakfast + " (" + breakfastCalories + " cal)" +
                " | Lunch: " + lunch + " (" + lunchCalories + " cal)" + " | Dinner: " + dinner + " (" + dinnerCalories + " cal)" +
                " | Total: " + getTotalCalories() + " cal";
    }
}
