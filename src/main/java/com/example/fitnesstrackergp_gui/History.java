package com.example.fitnesstrackergp_gui;

public abstract class History {

    protected double total;
    protected int dayCount;

    /**
     * Constructor for initializing the total calorie count and the day count to zero.
     * Used to create a new instance of History with initial values.
     */
    public History() {
        this.total = 0;
        this.dayCount = 0;
    }

    public abstract void loadCSV(String path, String name, Gender gender);

    /**
     * Function that calculates average per day over all meal entries.
     */
    public double getAverage() {
        return this.total / this.dayCount;
    }

    /**
     * Call this function to display average per day over all meal entries.
     */
    public void showAverage() {
        System.out.println("Average over " + this.dayCount + " day(s): " + this.getAverage());
    }
}
