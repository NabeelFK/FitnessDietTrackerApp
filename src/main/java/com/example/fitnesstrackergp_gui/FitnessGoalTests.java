package com.example.fitnesstrackergp_gui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FitnessGoalTests {

    @Test
        //test getGender method, ensure it calls the correct variable
    void test_getGender(){
        FitnessGoal testFitnessGoal = new FitnessGoal("Jane",Gender.FEMALE,45,123,321);
        Gender test_result = testFitnessGoal.getGender();
        Gender actual_result = Gender.FEMALE;
        assertEquals(actual_result,test_result,"Gender <female>. Test result should have equaled actual result");
    }

    @Test
        //test getName method, ensure it calls the correct variable
    void test_getName(){
        FitnessGoal testFitnessGoal = new FitnessGoal("Jane",Gender.FEMALE,45,123,321);
        String test_result = testFitnessGoal.getName();
        String actual_result = "Jane";
        assertEquals(actual_result,test_result,"User name <Jane>. Test result should have equaled actual result.");
    }

    @Test
        //test getTargetWeight method, ensure it calls the correct variable
    void test_getTargetWeight(){
        FitnessGoal testFitnessGoal = new FitnessGoal("Jane",Gender.FEMALE,45,123,321);
        float test_result = testFitnessGoal.getTargetWeight();
        float actual_result = 45;
        assertEquals(actual_result,test_result,"Target weight <45>. Test result should have equaled actual result.");
    }

    @Test
        //test getWorkoutMinutes method, ensure it calls the correct variable
    void test_getWorkoutMinutes(){
        FitnessGoal testFitnessGoal = new FitnessGoal("Jane",Gender.FEMALE,45,123,321);
        int test_result = testFitnessGoal.getWorkoutMinutes();
        int actual_result = 123;
        assertEquals(actual_result,test_result,"Workout minutes <123>. Test result should have equaled actual result.");
    }

    @Test
        //test getSleepHours method, ensure it calls the correct variable
    void test_getSleepHours(){
        FitnessGoal testFitnessGoal = new FitnessGoal("Jane",Gender.FEMALE,45,123,321);
        float test_result = testFitnessGoal.getSleepHours();
        float actual_result = 321;
        assertEquals(actual_result,test_result,"Sleep hours <321>. Test result should have equaled actual result.");
    }

    @Test
    //test toCSV method, ensure that format of test matches expected format of output
    void test_toCSV(){
        FitnessGoal testFitnessGoal = new FitnessGoal("Jane",Gender.FEMALE,45,123,321);
        String test_result = testFitnessGoal.toCSV();
        String actual_result = "Jane" + "," + "F" + "," + "45.0" + "," + "123" + "," + "321.0";
        assertEquals(actual_result,test_result,"Test result should have equaled actual result.");
    }

    @Test
    //test toString method, ensure it outputs what is expected
    void test_toString(){
        FitnessGoal testFitnessGoal = new FitnessGoal("Jane",Gender.FEMALE,45,123,321);
        String test_result = testFitnessGoal.toString();
        String actual_result = "Hello "+"Jane"+"! Here is your Fitness goal: \n"+"Target Weight: " + "45.0" +
                               " kg | Workout: " + "123" +
                               " mins/day | Sleep: " + "321.0" + " hrs/day";
        assertEquals(actual_result,test_result,"Test result should have equaled actual result.");
    }
}
