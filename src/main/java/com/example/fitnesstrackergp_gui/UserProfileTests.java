package com.example.fitnesstrackergp_gui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserProfileTests {

    @Test
    //Test calculateBMR method to ensure it calculates according to gender of user
    void test_calculateBMR_Female(){
        UserProfile testUserProfile = new UserProfile("Jane",Gender.FEMALE,20,56,101);
        double test_result = testUserProfile.calculateBMR();
        double actual_result = 447.6 + (9.2 * 56) + (3.1 * 101) - (4.3 * 20);
        assertEquals(actual_result,test_result,"Calculated BMR <1189.9>. Test result should have equaled actual result.");
    }

    @Test
    //test calculateBMR for Male gender
    void test_calculateBMR_Male(){
        UserProfile testUserProfile = new UserProfile("John",Gender.MALE,20,56,101);
        double test_result = testUserProfile.calculateBMR();
        double actual_result = 88.36 + (13.4 * 56) + (4.8 * 101) - (5.7 * 20);
        assertEquals(actual_result,test_result,"Calculated BMR <1209.56>. Test result should have equaled actual result");
    }

    @Test
    //test calculateBMR method if gender is not specified, method should default to calculate same way as if gender was Male
    void test_calculateBMR_Other(){
        UserProfile testUserProfile = new UserProfile("John",Gender.OTHER,20,56,101);
        double test_result = testUserProfile.calculateBMR();
        double actual_result = 88.36 + (13.4 * 56) + (4.8 * 101) - (5.7 * 20);
        assertEquals(actual_result,test_result,"Calculated BMR <1209.56>. Test result should have equaled actual result");
    }
    @Test
    //Test toString method, ensure that variables are correctly called and print as expected
    void test_toString(){
        UserProfile testUserProfile = new UserProfile("John",Gender.MALE,20,56,101);
        String test_result = testUserProfile.toString();
        String actual_result = "Name: " + "John" + " | Gender: " + "MALE" +  " | Age: " + "20" + " | Weight: " + "56.0" + "kg" +
                               " | Height: " + "101.0" + "cm" + " | BMR: " + "1209.56";
        assertEquals(actual_result,test_result,"Test result should have equaled actual result.");
    }

}


