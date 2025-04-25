package com.example.fitnesstrackergp_gui;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class ArgsManager {
    private static final ArgsManager INSTANCE = new ArgsManager();
    private List<String> rawArgs = new ArrayList<>();

    private ArgsManager() {
        // prevent external instantiation
    }

    public static ArgsManager getInstance() {
        return INSTANCE;
    }

    /**
     * Initialize with the raw command-line arguments.
     */
    public void setRawArgs(List<String> args) {
        if  (args.isEmpty()) {
            rawArgs.add("src/main/resources/com/example/fitnesstrackergp_gui/profiles.csv");
            rawArgs.add("src/main/resources/com/example/fitnesstrackergp_gui/goals.csv");
            rawArgs.add("src/main/resources/com/example/fitnesstrackergp_gui/meals.csv");
        } else {
            this.rawArgs = args;
        }
    }

    /**
     * Return the profile CSV file path (first argument).
     */
    public String getProfileCSV() {
        if (rawArgs == null || rawArgs.size() < 1) {
            throw new IllegalStateException("Profile CSV argument is missing");
        }
        return rawArgs.get(0);
    }

    /**
     * Return the goals CSV file path (second argument).
     */
    public String getGoalsCSV() {
        if (rawArgs == null || rawArgs.size() < 2) {
            throw new IllegalStateException("Goals CSV argument is missing");
        }
        return rawArgs.get(1);
    }

    /**
     * Return the meals CSV file path (third argument).
     */
    public String getMealsCSV() {
        if (rawArgs == null || rawArgs.size() < 3) {
            throw new IllegalStateException("Meals CSV argument is missing");
        }
        return rawArgs.get(2);
    }

    /**
     * Generic accessor if you need arbitrary elements.
     */
    public String getRawArg(int index) {
        return rawArgs.get(index);
    }

    /**
     * Full list of raw arguments.
     */
    public List<String> getRawArgs() {
        return rawArgs;
    }
}
