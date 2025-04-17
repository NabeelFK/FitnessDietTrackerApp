package com.example.fitnesstrackergp_gui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfileManagerTests {


    @Test
    //test isValidUsername method, ensure is username is valid, assert is true
    void test_isValidUsername_Valid(){
        ProfileManager testProfileManager = new ProfileManager();
        String toCheck = "JohnDoe";
        boolean test_result = testProfileManager.isValidUsername(toCheck);
        assertTrue(test_result,"Used username <JohnDoe>. Result should have been true");
    }

    @Test
    //test isValidUsername method for when username is invalid, ensure that this results in false assert
    void test_isValidUsername_Invalid(){
        ProfileManager testProfileManager = new ProfileManager();
        String toCheck = "JohnDoe123";
        boolean test_result = testProfileManager.isValidUsername(toCheck);
        assertFalse(test_result,"Used username <JohnDoe123>. Test result should have been false");
    }

    @Test
    //test if user does not input a username, this will have the same assertion as invalid test
    void test_isValidUsername_Blank(){
        ProfileManager testProfileManager = new ProfileManager()    ;
        String toCheck = "";
        boolean test_result = testProfileManager.isValidUsername(toCheck);
        assertFalse(test_result,"No username input. Result should have been false");
    }
}