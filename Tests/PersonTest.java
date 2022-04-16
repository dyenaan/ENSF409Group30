package edu.ucalgary.ensf409;

import java.util.*;
import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class PersonTest{
	
	int validClientID = 1;
	int negativeClientID = -2;
	int invalidClientID = 5;
	NutritionTypes nut;
	
	@Test
    public void testPersonConstructorValidInput() {
        Person person = new Person(validClientID);
		
        assertNotNull("Person constructor did not create an object when given a valid array of log entries.", person);
    }

    @Test
    public void testPersonConstructorNegativeInput() {
        boolean correctException = false;

        try{
            Person person = new Person(negativeClientID);
        }
        catch(IllegalArgumentException e){
            correctException = true;
        }

        assertTrue("Person constructor did not throw an IllegalArgumentException when given a negative client ID: ", correctException);
    }

    @Test
    public void testPersonConstructorInvalidInput() {
        boolean correctException = false;

        try{
            Person person = new Person(invalidClientID);
        }
        catch(IllegalArgumentException e){
            correctException = true;
        }

        assertTrue("Person constructor did not throw an IllegalArgumentException when given an invalid client ID: ", correctException);
    }

    @Test
    public void testPersonGetCalories() {
        Person person = new Person(validClientID);
        int foundNumber = (int)person.getNutrition(nut.CALORIES);
        assertNotNull("Method getNutrition did not return client calories value: ", foundNumber);
    }

    @Test
    public void testPersonGetWholeGrains() {
        Person person = new Person(validClientID);
        int foundNumber = (int)person.getNutrition(nut.WHOLE_GRAINS);
        assertNotNull("Method getNutrition did not return client whole grain value: ", foundNumber);
    }

    @Test
    public void testPersonGetProtein() {
        Person person = new Person(validClientID);
        int foundNumber = (int)person.getNutrition(nut.PROTEIN);
        assertNotNull("Method getNutrition did not return client protein value: ", foundNumber);
    }

    @Test
    public void testPersonGetFruitVeggies() {
        Person person = new Person(validClientID);
        int foundNumber = (int)person.getNutrition(nut.FRUIT_VEGGIES);
        assertNotNull("Method getNutrition did not return client fruit veggies value: ", foundNumber);
    }

    @Test
    public void testPersonGetOther() {
        Person person = new Person(validClientID);
        int foundNumber = (int)person.getNutrition(nut.OTHER);
        assertNotNull("Method getNutrition did not return other value: ", foundNumber);
    }

    @Test
    public void testPersonGetClientID() {
        Person person = new Person(validClientID);
        int foundClientID = person.getClientID();
        int expectedClientID = validClientID;
        assertEquals("Method getClientID did not return the expected result: ", expectedClientID, foundClientID);
    }
	
	
	
}