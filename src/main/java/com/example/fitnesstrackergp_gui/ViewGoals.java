package com.example.fitnesstrackergp_gui;

import java.util.ArrayList;
import java.util.List;

public class ViewGoals extends History {

    private final ArrayList<FitnessGoal> GOALS = new ArrayList<>();

    /**
     * Loads fitness goals from a CSV file and adds them to the list of goals.
     * This method reads the CSV file at the specified path, filters the records that match
     * the given name and gender, and parses the matching records into `FitnessGoal` objects.
     * Each valid `FitnessGoal` object is then added to the list of goals.
     *
     * @param path The path to the CSV file containing the fitness goal data.
     * @param name The name of the individual whose fitness goals are to be loaded.
     * @param gender The gender of the individual whose fitness goals are to be loaded.
     */
    @Override
    public void loadCSV(String path, String name, Gender gender) {
        GOALS.clear();

        List<String> goalLogs = FileHandler.readAllMatches(path, name, gender.toChar());

        if (goalLogs.isEmpty()) {
            System.out.println("No goals found for username: " + name);
        }

        for (String goal : goalLogs) {
            FitnessGoal newGoal = parseFitnessGoal(goal);
            if (newGoal != null) {
                GOALS.add(newGoal);
            }
        }
    }

    /**
     * Parses a CSV string with first name, gender (as a character), target weight, workout minutes per day,
     * and sleep hours per day representing a fitness goal and converts it into a `FitnessGoal` object.
     * If any errors occur during parsing, an exception is caught, and the method returns `null`.
     *
     * @param goalInfo A CSV-formatted string containing the fitness goal information for an individual.
     * @return A `FitnessGoal` object initialized with the parsed data, or `null` if an error occurs during parsing.
     */
    private FitnessGoal parseFitnessGoal(String goalInfo) {

        try {
            String[] info = goalInfo.split(",");

            String firstName = info[0];
            Gender gender = Gender.fromChar(info[1].trim().charAt(0));

            float targetWeight = Float.parseFloat(info[2].trim());
            int workoutMinutes = Integer.parseInt(info[3].trim());
            float sleepHours = Float.parseFloat(info[4].trim());

            return new FitnessGoal(firstName, gender, targetWeight, workoutMinutes, sleepHours);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Displays the user's fitness goal history based on their profile information and gender.
     * This method attempts to retrieve the user's profile from the "profiles.csv" file using the provided
     * name and gender. If a matching profile is found, it displays the current weight from the profile, then checks
     * the list of fitness goals and displays each goal if available.
     * If no profile or fitness goals are found, appropriate messages are displayed, and a corresponding
     * return value is provided.
     *
     * @param name The name of the user whose profile and fitness goals are to be retrieved.
     * @param gender The gender of the user (M/F/O) whose profile and fitness goals are to be retrieved.
     * @return An integer indicating the result of the operation.
     */
    public int showGoalHistory(String name, Gender gender) {
        List<String> profile = FileHandler.readAllMatches("src/main/resources/com/example/fitnesstrackergp_gui/profiles.csv", name, gender.toChar());

        if (profile.isEmpty()) {
            System.out.println("No profile found.");
            return 2;
        }

        UserProfile profile1 = UserProfile.fromCSV(profile.getFirst());

        if (GOALS.isEmpty()) {
            System.out.println("No fitness goals found. Please set a fitness goal.");
            return 1;
        } else {
            System.out.println("Current Weight: " + profile1.getWeight() + " Kg");
            for (FitnessGoal goal : GOALS) {
                System.out.println(goal);
            }
        }
        return 0;
    }

    public String getGoalsSummary(String name) {
        StringBuilder output = new StringBuilder();

        for (Gender gender : Gender.values()) {
            List<String> profile = FileHandler.readAllMatches("src/main/resources/com/example/fitnesstrackergp_gui/profiles.csv", name, gender.toChar());

            if (!profile.isEmpty()) {
                loadCSV("src/main/resources/com/example/fitnesstrackergp_gui/goals.csv", name, gender);
                UserProfile user = UserProfile.fromCSV(profile.get(0));

                output.append("Current Weight: ").append(user.getWeight()).append(" Kg\n\n");

                if (GOALS.isEmpty()) {
                    output.append("No fitness goals found.\n");
                } else {
                    for (FitnessGoal goal : GOALS) {
                        output.append(goal).append("\n");
                    }
                }
                return output.toString();  // Return early after first valid match
            }
        }

        return "Profile not found.\n";
    }

}