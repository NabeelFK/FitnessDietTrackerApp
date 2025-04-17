package com.example.fitnesstrackergp_gui;

public class UserProfile extends Person implements Trackable {

    /**
     * Constructor initializes a UserProfile object by calling the constructor of the superclass
     * with the provided name, gender, age, weight, and height.
     *
     * @param name The name of the user.
     * @param gender The gender of the user (e.g., MALE, FEMALE, OTHER).
     * @param age The age of the user in years.
     * @param weight The weight of the user (kg).
     * @param height The height of the user (cm).
     */
    public UserProfile(String name, Gender gender,int age, float weight, float height) {
        super(name, gender, age, weight, height);
    }

    /**
     * Calculates the Basal Metabolic Rate (BMR) for the user based on their gender, weight, height, and age.
     * The formula is used to estimate the number of calories a person needs to maintain basic bodily functions at rest.
     *
     * @return The calculated BMR as a double.
     *         Returns 0 if an invalid gender is provided.
     */
    @Override
    public double calculateBMR() {
        switch (gender) {
            case MALE, OTHER:
                return 88.36 + (13.4 * weight) + (4.8 * height) - (5.7 * age);
            case FEMALE:
                return 447.6 + (9.2 * weight) + (3.1 * height) - (4.3 * age);
            default:
                System.out.println("Invalid gender.");
                return 0;
        }
    }

    /**
     * Converts the UserProfile object to a CSV format string.
     * This CSV string is used for saving the user profile data to a file or for data exchange purposes.
     *
     * @return A CSV-formatted string representing the UserProfile object.
     */
    @Override
    public String toCSV() {
        return name + "," + gender.toChar() + "," + age + "," + weight + "," + height;
    }

    /**
     * Converts a CSV-formatted string into a UserProfile object.
     * The CSV string is split into individual components, and parsed into corresponding data type.
     *
     * @param csv A CSV-formatted string representing a user profile.
     * @return A new UserProfile object initialized with the data from the CSV string.
     */
    public static UserProfile fromCSV(String csv) {
        String[] index = csv.split(",");

        return new UserProfile(
                index[0].trim(),                                 // name
                Gender.fromChar(index[1].trim().charAt(0)),       // gender
                Integer.parseInt(index[2].trim()),               // age
                Float.parseFloat(index[3].trim()),               // weight
                Float.parseFloat(index[4].trim())               // height
        );
    }

    /**
     * Returns a summary of the UserProfile object in a human-readable format.
     *
     * @return A formatted string containing the user's profile details and calculated BMR.
     */
    @Override
    public String toString() {
        return "Name: " + name + " | Gender: " + gender +  " | Age: " + age + " | Weight: " + weight + "kg" +
                " | Height: " + height + "cm" + " | BMR: " + calculateBMR();
    }
}
