package com.example.fitnesstrackergp_gui;

import java.util.List;
import java.util.Scanner;

public class ProfileManager {
    private static String fileName = "src/main/resources/com/example/fitnesstrackergp_gui/profiles.csv";
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructor that accepts a profileFileName as a parameter. If provided profile file name is not null,
     * assign it to the fileName variable.
     */
    public ProfileManager(String profileFileName) {
        if (profileFileName != null)
            fileName = profileFileName;
    }

    /**
     * Default constructor --> used when profileManager is created without a specifying a file name.
     */
    public ProfileManager() {
    }

    /**
     * Checks whether the provided username is valid based on specific criteria.
     * A valid username consists of only alphabetic characters (a-z, A-Z).
     *
     * @param username The username to be validated.
     * @return `true` if the username is valid,
     *         `false` otherwise.
     */
    public boolean isValidUsername (String username) {
        return username.matches("[a-zA-Z]+");
    }

    /**
     * Prompts the user to input their profile details (username, gender, age, weight, and height)
     * and creates a new user profile. The method validates the username to ensure it only contains
     * alphabetic characters, and then collects the remaining user data.
     * New `UserProfile` object is created and saved to a file in CSV format with user info.
     */
    public void createProfile() {
        String username;
        while (true) {
            System.out.println("Enter Username (please only enter alphabetic letters) : ");
            username = scanner.nextLine();
            if (isValidUsername(username)) {
                break; // Exit loop if username is valid
            } else {
                System.out.println("Invalid username. Please enter only alphabetic characters.");
            }
        }

        System.out.println("Enter Gender (M/F/O): ");
        char genderChar = scanner.nextLine().charAt(0);
        Gender gender = Gender.fromChar(genderChar);

        System.out.println("Enter Age: ");
        int age = scanner.nextInt();

        System.out.println("Enter Weight (kg): ");
        float weight = scanner.nextFloat();

        System.out.println("Enter Height (cm): ");
        float height = scanner.nextFloat();
        scanner.nextLine();

        UserProfile profile = new UserProfile(username, gender, age, weight, height);
        FileHandler.writeToFile(fileName, profile.toCSV() + "\n");

        System.out.println("Profile created.");
    }

    /**
     * Prompts the user to enter their username and gender, then attempts to retrieve and display
     * their user profile from a file.
     * If no matching profile is found, the user is prompted to create a new account. Else, the method parses
     * the CSV data and displays the profile information.
     */
    public void viewProfile() {
        System.out.println("Enter Username: ");
        String username = scanner.nextLine();

        System.out.println("Enter Gender (M/F/O): ");
        char genderChar = scanner.nextLine().charAt(0);
        Gender gender = Gender.fromChar(genderChar);

        List<String> matches = FileHandler.readAllMatches(fileName, username, gender.toChar());

        if (matches.isEmpty()) {
            System.out.println("Profile not found. Please create a new account.");
        } else {
            String csv = matches.getFirst(); //default use 1st match
            UserProfile profile = UserProfile.fromCSV(csv);
            System.out.println(profile);
        }
    }

    /**
     * Retrieves a UserProfile object based on username.
     * It searches the profiles.csv file for the first match of username (case-sensitive).
     *
     * @param username The username to look up.
     * @return UserProfile object if found, null otherwise.
     */
    public UserProfile getProfile(String username) {
        for (Gender gender : Gender.values()) {
            List<String> matches = FileHandler.readAllMatches(fileName, username, gender.toChar());
            if (!matches.isEmpty()) {
                return UserProfile.fromCSV(matches.get(0)); // return first match
            }
        }
        return null;
    }

    /**
     * Checks whether a profile with the given username exists.
     *
     * @param username The username to check.
     * @return true if any gendered profile with this name exists; false otherwise.
     */
    public boolean profileExists(String username) {
        for (Gender gender : Gender.values()) {
            List<String> matches = FileHandler.readAllMatches(fileName, username, gender.toChar());
            if (!matches.isEmpty()) {
                return true;
            }
        }
        return false;
    }

}
