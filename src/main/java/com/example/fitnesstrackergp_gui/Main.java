package com.example.fitnesstrackergp_gui;

public class Main {
    public static void main(String[] args) {

        // Initializing variables to null.
        String argProfileFileName = null;
        String argGoalFileName = null;
        String argMealFileName = null;

        // Check if any command line args are passed through. If exactly 3 passed through, assign them to
        // file variables, else print error message and exit program.
        if (args.length > 0) {
            if (args.length == 3) {
                argProfileFileName = args[0];
                argGoalFileName = args[1];
                argMealFileName = args[2];
            } else {
                System.out.println("Usage of Fitness Tracker app: java Main <profile file> <goals file> <meal file>");
                return;
            }
        }

        // Create new objects for fitness diet tracker and menu, then calls on displayMenu method of Menu to
        // display start page.
        FitnessDietTracker tracker = new FitnessDietTracker(argProfileFileName, argGoalFileName, argMealFileName);
        Menu menu = new Menu();
        menu.displayMenu(tracker);
    }
}
//Shiva Das, Kendra Cowan, Steven Barclay, Pavneet Dhanoa, Nabeel Furqan, Myah Rustad
//March 28, 2025
//Tutorial 10, TA: Tyler Klein-Longmire