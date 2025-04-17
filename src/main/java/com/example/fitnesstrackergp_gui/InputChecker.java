package com.example.fitnesstrackergp_gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class InputChecker {
    public boolean calorieInputChecker(int input) {
        return false;
    }

    /**
     * Validates if the provided input string consists only of alphabetical characters and spaces.
     *
     * @param input The string to be validated.
     * @return `true` if the input is non-null, non-empty, and contains only letters and spaces;
     *         `false` otherwise.
     */
    public boolean StringInputChecker(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        //checks to see if user input for meal is alphabetical
        for (char c : input.toCharArray()) {
            if ((!Character.isLetter(c)) && (c != ' ')) {
                return false;
            }
        }
        return true;
    }
    public static boolean checkFoodInput(String foods) {
        foods = foods.toLowerCase(); // Converted to lower case as words in the file are lower case only.
        HashSet<String> foodsList = new HashSet<>();
        File sixLetterWordList = new File("food.txt");
        // Credit for file provided: "https://gist.github.com/peterdemin/920ec3eaaa0a9f3cafd3a855557f5e0c#file-food-txt"

        try {
            Scanner fileReader = new Scanner(sixLetterWordList);
            while (fileReader.hasNextLine()) {
                foodsList.add(fileReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return foodsList.contains(foods);
    }
}