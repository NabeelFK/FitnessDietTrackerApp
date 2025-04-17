package com.example.fitnesstrackergp_gui;

import java.io.*;
import java.util.*;

public class FileHandler {

    /**
     * Writes a given line of text to a specified file. Appends the line to the file if it already exists,
     * or creates a new file if it does not.
     * It uses a BufferedWriter to efficiently write the provided text, and handles potential IOExceptions
     * if writing to the file fails. Any unexpected errors will cause the program to terminate.
     *
     * @param fileName The name of the file to which the line of text will be written.
     * @param line The line of text to write into the file.
     *
     * @throws IOException If an error occurs during the writing process, an error message is printed.
     * @throws Exception If any unexpected errors occur, the program exits.
     */
    public static void writeToFile(String fileName, String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(line);
            writer.newLine();
        }
        catch (IOException e) {
            System.out.println("Error! Cannot write in the file.: " + e.getMessage());
        }
        catch (Exception e){
            System.out.println("Something unexpected happened.");
            System.exit(0);
        }
    }

    /**
     * Reads all lines from a specified CSV file that match given username and gender.
     * Method iterates through each line in the file, splits it by commas, and checks if the name
     * and gender in the file match the provided parameters. It returns a list of matching lines.
     *
     * @param fileName The name of the file to read data from.
     * @param name The username to search for in the file.
     * @param genderChar The gender character (M/F/O) to match in the file.
     * @return A list of strings containing all lines from the file that match the provided name and gender.
     *         If no matches are found, the list will be empty.
     *
     * @throws FileNotFoundException If the specified file cannot be found, an error message is printed.
     * @throws Exception If an unexpected error occurs, the program exits.
     */
    public static List<String> readAllMatches(String fileName, String name, char genderChar) {
        //creating empty list
        List<String> output = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] index = line.split(",");

                if (index.length >= 2) {
                    String nameFile = index[0].trim();
                    char genderFile = index[1].trim().charAt(0);

                    if (nameFile.equalsIgnoreCase(name) && Character.toUpperCase(genderFile) == Character.toUpperCase(genderChar)) {
                        output.add(line);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
        catch (Exception e){
            System.out.println("Something unexpected happened.");
            System.exit(0);
        }
        return output;
    }
}