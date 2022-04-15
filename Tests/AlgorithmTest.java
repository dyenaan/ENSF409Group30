package edu.ucalgary.ensf409;

import java.util.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class AlgorithmTest {
    Person[] emptyPeopleArray = new Person[0];
    ArrayList<String> usedItemIDs = new ArrayList<>();

    @Test
    public void testInvalidAlgorithmConstructor() throws HamperAlreadyFoundException, StockNotAvailableException {

        boolean correctException = false;

        try{
            Algorithm algorithm = new Algorithm(emptyPeopleArray, usedItemIDs);
        }
        catch(IllegalArgumentException e){
            correctException = true;
        }

        assertTrue("Algorithm constructor did not throw an IllegalArgumentException when given an empty person array: ", correctException);
    }

    @Test
    public void testAlgorithmGetFamilyCaloriesTotal() throws HamperAlreadyFoundException, StockNotAvailableException {
        Person[] people = new Person[1];
        people[0] = new Person(ClientTypes.MALE.clientID());
        Algorithm algorithm = new Algorithm(people, usedItemIDs);
        double totalCalories = algorithm.getFamilyCalories(NutritionTypes.CALORIES.asString());
        double expectedValue = people[0].getNutrition(NutritionTypes.CALORIES.asString());

        assertEquals("The getFamilyNutritionCalories method did not return a value for Calories", totalCalories, expectedValue, 0.1);
    }

    @Test
    public void testAlgorithmGetFamilyCaloriesWG() throws HamperAlreadyFoundException, StockNotAvailableException {
        Person[] people = new Person[1];
        people[0] = new Person(ClientTypes.MALE.clientID());
        Algorithm algorithm = new Algorithm(people, usedItemIDs);
        double wholeGrains = algorithm.getFamilyCalories(NutritionTypes.WHOLE_GRAINS.asString());
        double calories = people[0].getNutrition(NutritionTypes.CALORIES.asString());
        double expectedValue = people[0].getNutrition(NutritionTypes.WHOLE_GRAINS.asString()) * 0.01 * calories;

        assertEquals("The getFamilyCalories method did not return a value for whole grains", expectedValue, wholeGrains, 0.1);
    }

    @Test
    public void testAlgorithmGetFamilyCaloriesPro() throws HamperAlreadyFoundException, StockNotAvailableException {
        Person[] people = new Person[1];
        people[0] = new Person(ClientTypes.MALE.clientID());
        Algorithm algorithm = new Algorithm(people, usedItemIDs);
        double protein = algorithm.getFamilyCalories(NutritionTypes.PROTEIN.asString());
        double calories = people[0].getNutrition(NutritionTypes.CALORIES.asString());
        double expectedValue = people[0].getNutrition(NutritionTypes.PROTEIN.asString()) * 0.01 * calories;

        assertEquals("The getFamilyCalories method did not return a value for protein", expectedValue, protein, 0.1);
    }

    @Test
    public void testAlgorithmGetFamilyCaloriesFV() throws HamperAlreadyFoundException, StockNotAvailableException {
        Person[] people = new Person[1];
        people[0] = new Person(ClientTypes.MALE.clientID());
        Algorithm algorithm = new Algorithm(people, usedItemIDs);
        double fv = algorithm.getFamilyCalories(NutritionTypes.FRUIT_VEGGIES.asString());
        double calories = people[0].getNutrition(NutritionTypes.CALORIES.asString());
        double expectedValue = people[0].getNutrition(NutritionTypes.FRUIT_VEGGIES.asString()) * 0.01 * calories;

        assertEquals("The getFamilyCalories method did not return a value for Fruit Veggies", expectedValue, fv, 0.1);
    }

    @Test
    public void testAlgorithmGetFamilyCaloriesOther() throws HamperAlreadyFoundException, StockNotAvailableException {
        Person[] people = new Person[1];
        people[0] = new Person(ClientTypes.MALE.clientID());
        Algorithm algorithm = new Algorithm(people, usedItemIDs);
        double other = algorithm.getFamilyCalories(NutritionTypes.OTHER.asString());
        double calories = people[0].getNutrition(NutritionTypes.CALORIES.asString());
        double expectedValue = people[0].getNutrition(NutritionTypes.OTHER.asString()) * 0.01 * calories;

        assertEquals("The getFamilyCalories method did not return a value for other", expectedValue, other, 0.1);
    }

    @Test
    public void testAlgorithmInvalidGetFamilyNutritionCalories() throws HamperAlreadyFoundException, StockNotAvailableException {
        Person[] people = new Person[1];
        people[0] = new Person(ClientTypes.MALE.clientID());
        Algorithm algorithm = new Algorithm(people, usedItemIDs);

        boolean correctException = false;

        try{
            double number = algorithm.getFamilyCalories("invalid type");
        }
        catch(IllegalArgumentException e){
            correctException = true;
        }
        assertTrue("getFamilyNutritionCalories constructor did not throw an IllegalArgumentException when given an invalid argument: ", correctException);
    }

    @Test
    public void testAlgorithmGetFBestHamper() throws HamperAlreadyFoundException, StockNotAvailableException {
        Person[] people = new Person[1];
        people[0] = new Person(ClientTypes.MALE.clientID());
        Algorithm algorithm = new Algorithm(people, usedItemIDs);
        ArrayList<Map<String, String>> bestHamper = algorithm.getBestHamper();
        assertNotNull("The getBestHamper method did not return a hamper: ", bestHamper);
    }

    @Test
    public void testAlgorithmGetUsedItemIDs() throws HamperAlreadyFoundException, StockNotAvailableException {
        Person[] people = new Person[1];
        people[0] = new Person(ClientTypes.MALE.clientID());
        Algorithm algorithm = new Algorithm(people, usedItemIDs);
        ArrayList<String> curUsedItemIDs = algorithm.getUsedItemIDs();
        assertNotNull("The getUsedItemIDs method did not return an arrayList: ", curUsedItemIDs);
    }
}
