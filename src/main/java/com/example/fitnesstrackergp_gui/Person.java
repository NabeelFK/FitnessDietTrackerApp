package com.example.fitnesstrackergp_gui;

public abstract class Person {
    protected String name;
    protected Gender gender;
    protected int age;
    protected float weight;
    protected float height;

    /**
     * This constructor initializes a Person object with the provided name, gender, age, weight, and height.
     *
     * @param name The name of the person.
     * @param gender The gender of the person (e.g., MALE, FEMALE, OTHER).
     * @param age The age of the person in years.
     * @param weight The weight of the person (kg).
     * @param height The height of the person (cm).
     */
    public Person(String name, Gender gender, int age, float weight, float height ) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    /**
     * Calculates the Basal Metabolic Rate (BMR) for a person.
     *
     * @return The calculated BMR value as a double, representing the number of calories burned per day at rest.
     */
    public abstract double calculateBMR();
}
