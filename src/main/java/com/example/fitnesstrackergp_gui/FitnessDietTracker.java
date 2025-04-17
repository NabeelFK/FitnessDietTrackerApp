package com.example.fitnesstrackergp_gui;

public class FitnessDietTracker {
    // Creating final instances, so they can only be assigned once through constructor
    private final ProfileManager profileManager;
    private final MealLogger mealLogger;
    private final Goal goal;

    /**
     * Constructor for initializing the FitnessDietTracker with specific file names for profile, goal, and meal data.
     *
     * @param profileFileName The file name for loading the user's profile data.
     * @param goalFileName The file name for loading the user's goal data.
     * @param mealFileName The file name for logging the user's meal data.
    */
    public FitnessDietTracker(String profileFileName, String goalFileName, String mealFileName) {
        profileManager = new ProfileManager(profileFileName);
        goal = new Goal(goalFileName);
        mealLogger = new MealLogger(mealFileName);
    }

    /**
     * Creates a new user profile by invoking the createProfile method of the ProfileManager.
     * Method is used to initiate the process of creating a new account.
     */
    public void createNewAccount() {
        profileManager.createProfile();
    }

    /**
     * Displays profile history by invoking the viewProfile method of profileManager.
     * Method is used to view history of existing profiles.
     */
    public void viewProfileHistory() {
        profileManager.viewProfile();
    }

    /**
     * Logs the users meals by invoking logMeal method of mealLogger.
     * Method is used to record the meals consumed in a day.
     */
    public void logDailyMeals() {
        mealLogger.logMeal();
    }

    /**
     * Displays diet/meal input history by invoking the viewMealHistory method of mealLogger.
     * Method is used to view history of meal inputs.
     */
    public void viewDietHistory() {
        mealLogger.viewMealHistory();
    }

    /**
     * Sets fitness goals for the user by invoking setFitnessGoals method of goal.
     * Method is used to define/update the fitness goals.
     */
    public void setGoals() {
        goal.setFitnessGoals();
    }

    /**
     * Displays the users fitness goals by invoking viewFitnessGoals method of goal.
     * Method is used to view personal fitness goals.
     */
    public void viewGoals() {
        goal.viewFitnessGoals();
    }
}
