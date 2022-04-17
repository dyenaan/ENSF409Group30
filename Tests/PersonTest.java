package edu.ucalgary.ensf409;

import java.util.*;
import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Ahmad Khaled, Pansilu Wickramasinghe, Dyenaan Dapoet, Esohe Aideyan.
 * @version 1.3
 * @since 1.0
 */

/*
 *   The purpose of the PersonTest class is to test out the functionality of the Person class
 */

public class PersonTest{
	
	int validClientID = 1;
	int negativeClientID = -2;
	int invalidClientID = 5;

    // The testPersonConstructorValidInput tests if the constructor works properly if given a valid input

	@Test
    public void testPersonConstructorValidInput() {
        Person person = new Person(validClientID);
		
        assertNotNull("Person constructor did not create an object when given a valid array of log entries.", person);
    }

    // The testPersonConstructorNegativeInput tests if the constructor throws an IllegalArgumentException if given a negative input

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

    // The testPersonConstructorNegativeInput tests if the constructor throws an IllegalArgumentException if given a negative input

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

    /* the testPersonInvalidGetNutrition tests if the getNutrition getter throws an IllegalArgumentException if an invalid
    * input is given. */

    @Test
    public void testPersonInvalidGetNutrition() {
        boolean correctException = false;
        Person person = new Person(validClientID);

        try{
            double nutrition = person.getNutrition("Invalid");
        }
        catch(IllegalArgumentException e){
            correctException = true;
        }

        assertTrue("Person constructor did not throw an IllegalArgumentException when given an invalid client ID: ", correctException);
    }

    // the testPersonGetClientID tests if the getClientID getter returns the client ID

    @Test
    public void testPersonGetClientID() {
        Person person = new Person(validClientID);
        int foundClientID = person.getClientID();
        int expectedClientID = validClientID;
        assertEquals("Method getClientID did not return the expected result: ", expectedClientID, foundClientID);
    }

}