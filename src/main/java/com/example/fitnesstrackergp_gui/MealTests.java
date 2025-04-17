package com.example.fitnesstrackergp_gui;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MealTests {
    //preset a date so tests don't have to be adjusted depending on the day tests are ran
private final LocalDate date = LocalDate.of(2025,01,01);


    @Test
    //test getTotalCalories method, ensure that the addition is correct and correct variables are called
    void test_GetTotalCalories(){
        Meal testMeal = new Meal("Jane",Gender.FEMALE, date,"cereal",250,"Chips",350,"Pasta",400);
        int test_result = testMeal.getTotalCalories();
        int actual_result = 250 + 350 + 400;
        assertEquals(actual_result,test_result,"Total calories <1000>. Test result should have equaled actual result");
    }

    @Test
    //test toCSV method, ensure that the expected format executes correctly
    void test_toCSV(){
        Meal testMeal = new Meal("Jane",Gender.FEMALE, date,"cereal",250,"Chips",350,"Pasta",400);
        String test_result = testMeal.toCSV();
        String actual_result = "Jane" + "," + "F" + "," + "2025-01-01" + "," + "cereal" + " (" + "250" + ")" +
                               "," + "Chips" + " (" + "350" + ")" + "," + "Pasta" + " (" + "400" + ")";
        assertEquals(actual_result,test_result,"Test result should have equaled actual result.");
    }

    @Test
    //test toString method, ensure that the expected output formats the same for test result
    void test_toString(){
        Meal testMeal = new Meal("Jane",Gender.FEMALE, date,"cereal",250,"Chips",350,"Pasta",400);
        String test_result = testMeal.toString();
        String actual_result = "Date: " + "2025-01-01" + " | Breakfast: " + "cereal" + " (" + "250" + " cal)" +
           " | Lunch: " + "Chips" + " (" + "350" + " cal)" + " | Dinner: " + "Pasta" + " (" + "400" + " cal)" +
           " | Total: " + "1000" + " cal";
        assertEquals(actual_result,test_result,"Test result should have equaled actual result.");
    }
}





