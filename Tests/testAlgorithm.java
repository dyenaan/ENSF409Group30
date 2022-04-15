package edu.ucalgary.ensf409;

import java.util.*;
import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class testAlgorithm {
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

//    @Test
//    public void testAlgorithmGetFamilyNutritionCalories() throws HamperAlreadyFoundException, StockNotAvailableException {
//        Person[] people = new Person[1];
//        people[0] = new Person(ClientTypes.MALE.clientID());
//        Algorithm algorithm = new Algorithm(people, usedItemIDs);
//        double totalCalories = algorithm.getFamilyNutritionCalories(NutritionTypes.CALORIES);
//        double totalWholeGrains = algorithm.getFamilyNutritionCalories(NutritionTypes.WHOLE_GRAINS);
//        double totalProtein = algorithm.getFamilyNutritionCalories(NutritionTypes.PROTEIN);
//        double totalFruitVeggies = algorithm.getFamilyNutritionCalories(NutritionTypes.FRUIT_VEGGIES);
//        double totalOther = algorithm.getFamilyNutritionCalories(NutritionTypes.OTHER);
//
//        assertNotNull("The getFamilyNutritionCalories method did not return a value for Calories", totalCalories);
//        assertNotNull("The getFamilyNutritionCalories method did not return a value for Whole grains", totalWholeGrains);
//        assertNotNull("The getFamilyNutritionCalories method did not return a value for fruit veggies", totalFruitVeggies);
//        assertNotNull("The getFamilyNutritionCalories method did not return a value for protein", totalProtein);
//        assertNotNull("The getFamilyNutritionCalories method did not return a value for other", totalOther);
//    }

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
