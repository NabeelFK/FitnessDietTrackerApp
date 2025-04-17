package com.example.fitnesstrackergp_gui;

public class FitnessGoal implements Trackable,Comparable<FitnessGoal> {

    private final String username;
    private final Gender gender;
    private final float targetWeight;
    private final int workoutMinutes;
    private final float sleepHours;

    /**
     * Constructs a new FitnessGoal object with the specified user details and fitness goals.
     * This constructor initializes a FitnessGoal object with the provided username, gender, target weight,
     * workout duration, and sleep hours, setting the respective fields of the object.
     *
     * @param username The username of the individual setting the fitness goal.
     * @param gender The gender of the individual (M/F/O).
     * @param targetWeight The target weight (in kilograms) that the individual aims to reach.
     * @param workoutMinutes The number of minutes the individual plans to exercise per day.
     * @param sleepHours The number of hours the individual aims to sleep per day.
     */
    public FitnessGoal(String username, Gender gender, float targetWeight, int workoutMinutes, float sleepHours) {
        this.username = username;
        this.gender = gender;
        this.targetWeight = targetWeight;
        this.workoutMinutes = workoutMinutes;
        this.sleepHours = sleepHours;
    }

    public Gender getGender() {
        return gender;
    }

    public String getName() {
        return username;
    }

    public float getTargetWeight() {
        return targetWeight;
    }

    public int getWorkoutMinutes() {
        return workoutMinutes;
    }

    public float getSleepHours() {
        return sleepHours;
    }

    /**
     * Converts the FitnessGoal object to a CSV format string.
     * Method returns a string representation of the FitnessGoal object, with each field
     * separated by a comma. Resulting string can be used for saving the goal data in a CSV file.
     *
     * @return A CSV-formatted string representing the FitnessGoal object.
     */
    @Override
    public String toCSV() {
        return getName() + "," + getGender().toChar() + "," + getTargetWeight() + "," + getWorkoutMinutes() + "," + getSleepHours();
    }

    /**
     * Returns a string representation of the FitnessGoal object.
     * Method generates a human-readable message that includes the user's username and their fitness goal details.
     * It provides the target weight, daily workout duration, and daily sleep hours in a formatted message.
     *
     * @return A string containing a personalized message with the user's fitness goal details.
     */
    @Override
    public String toString() {
        String output = "Hello "+username+"! Here is your Fitness goal: \n"+"Target Weight: " + getTargetWeight() +
                " kg | Workout: " + getWorkoutMinutes() +
                " mins/day | Sleep: " + getSleepHours() + " hrs/day";
        return output;
    }

    /**
     * Compares this FitnessGoal object with another FitnessGoal object for ordering.
     * Comparison is first based on the workout minutes, and if the workout minutes are the same,
     * it proceeds to compare the sleep hours to determine the order.
     * Method allows FitnessGoal objects to be sorted based on their workout minutes and,
     * if necessary, their sleep hours.
     *
     * @param other The FitnessGoal object to compare this object with.
     * @return A negative or a positive integer as this object's workout minutes (and possibly sleep hours)
     *         are less than, equal to, or greater than the specified object's workout minutes (and sleep hours).
     */
    @Override
    public int compareTo(FitnessGoal other) {
        //this is a comparable that starts by comparing users workout minutes to each other
        int result = Integer.compare(this.workoutMinutes, other.workoutMinutes);
        //if that result is 0 then it will compare sleep hours and sort based on that
        if (result==0) {
            return Float.compare(this.sleepHours,other.sleepHours);
        }
        return result;
    }
}
