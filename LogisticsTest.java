package edu.ucalgary.ensf409;

import java.util.*;
import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class LogisticsTest {
    String validUsername = "student";
    String validPassword = "ensf";
    String validUrl = "jdbc:mysql://localhost/food_inventory";

    int mCount = 2;
    int invMCount = -2;
    int fCount = 2;
    int invFCount = -2;

    int uECount = 1;
    int invUECount = -1;
    int oECount = 3;
    int invOECount = -3;

    int validClientID = 1;
    int negativeClientID = -3;
    int invalidClientID = 19; // bigger than 4



    // ------ ORDER CLASS TESTS ------

    @Test
    public void testOrderConstructorInvalidInput() {
        boolean correctException = false;
        try{
            Order order = new Order(invMCount,invFCount,invUECount,invOECount);
        }
        catch(IllegalArgumentException e){
            correctException = true;
        }

        assertTrue("Order constructor did not throw an IllegalArgumentException for invalid number of people: ", correctException);
    }


    @Test
    public void testOrderConstructorValidInput() {

        Order order = new Order(mCount,fCount,uECount,oECount);
        assertNotNull("Order constructor did not create an object when given a valid count:", order);
    }

    @Test
    public void testOrderGetFamilies() {

        Order order = new Order(mCount,fCount,uECount,oECount);
        Family[] families = order.getFamilies();

        assertNotNull("Method getFamilies did not return an object of type family: " , families);
    }

    @Test
    public void testOrderGetTotalHampers() {
        Order order = new Order(mCount,fCount,uECount,oECount);
        List<HashMap<String, Integer>> totalHampers = order.getTotalHampers();

        assertNotNull("Method getTotalHamper did not return an object of type ArrayList: ", totalHampers);
    }
    @Test
    public void testOrderCheckStock() {
        Order order = new Order(mCount,fCount,uECount,oECount);

        Database db = new Database(validUrl, validUsername, validPassword);
        int itemCount = db.getItemCount();

        boolean expectedCheckStock = itemCount > 0;
        boolean actualCheckStock = order.checkStock();

        assertEquals("The actual value of checkStock did not match the expected value", expectedCheckStock, actualCheckStock);
    }


    // ------ FAMILY CLASS TESTS ------

    @Test
    public void testFamilyConstructorInvalidInput() {

        boolean correctException = false;

        try{
            Family family = new Family(invMCount,invFCount,invUECount,invOECount);
        }
        catch(IllegalArgumentException e){
            correctException = true;
        }

        assertTrue("Family constructor did not throw an IllegalArgumentException when given a count less than 0: ", correctException);
    }

    @Test
    public void testFamilyConstructorValidInput() {

        Family family = new Family(mCount,fCount,uECount,oECount);
        assertNotNull("Family constructor did not create an object when given a valid count:", family);
    }


    @Test
    public void testFamilyGetPeople() {

        Family nfamily = new Family(mCount,fCount,uECount,oECount);
        Person[] people = nfamily.getPeople();

        assertNotNull("Method getPeople did not return an array of type person: " , people);
    }

    @Test
    public void testFamilyGetHamper() {

        Family nfamily = new Family(mCount,fCount,uECount,oECount);
        List<HashMap<String, Integer>> hmap = new ArrayList<HashMap<String, Integer>>();

        hmap = nfamily.getHamper();

        assertNotNull("Method getHamper did not return an object of type ArrayList: " ,hmap);


    }

    // ------ PERSON CLASS TESTS ------

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
        int foundNumber = person.getNutrition("Calories");
        assertNotNull("Method getNutrition did not return client calories value: ", foundNumber);
    }

    @Test
    public void testPersonGetWholeGrains() {
        Person person = new Person(validClientID);
        int foundNumber = person.getNutrition("WholeGrains");
        assertNotNull("Method getNutrition did not return client whole grain value: ", foundNumber);
    }

    @Test
    public void testPersonGetProtein() {
        Person person = new Person(validClientID);
        int foundNumber = person.getNutrition("Protein");
        assertNotNull("Method getNutrition did not return client protein value: ", foundNumber);
    }

    @Test
    public void testPersonGetFruitVeggies() {
        Person person = new Person(validClientID);
        int foundNumber = person.getNutrition("FruitVeggies");
        assertNotNull("Method getNutrition did not return client fruit veggies value: ", foundNumber);
    }

    @Test
    public void testPersonGetOther() {
        Person person = new Person(validClientID);
        int foundNumber = person.getNutrition("Other");
        assertNotNull("Method getNutrition did not return other value: ", foundNumber);
    }

    @Test
    public void testPersonGetClientID() {
        Person person = new Person(validClientID);
        int foundClientID = person.getClientID();
        int expectedClientID = validClientID;
        assertEquals("Method getClientID did not return the expected result: ", expectedClientID, foundClientID);
    }

    // ------ HAMPER CLASS TESTS ------

    @Test
    public void testBestHamperConstructorGoodData(){

        Person person1 = new Person(validClientID);
        Person person2 = new Person(validClientID);

        Person[] people = new person[2];

        BestHamper bestHamper = new BestHamper(people);
        assertNotNull("BestHamper constructor did not create an object when given a valid array of log entries.", bestHamper);
    }

    @Test
    public void testBestHamperConstructorBadData(){
        boolean correctException = false;

        try{
            Person[] people = new Person[1]; // Empty person array
            BestHamper bestHamper = new BestHamper(people);
        }
        catch(IllegalArgumentException e){
            correctException = true;
        }
        assertTrue("BestHamper constructor did not throw an IllegalArgumentException when given an empty array for the people class", correctException);
    }

    @Test
    public void testBestHamperGetTotalCalories() {
        Person person1 = new Person(validClientID);
        Person person2 = new Person(validClientID);

        Person[] people = new person[2];
        people[0] = person1;
        people[1] = person2;

        BestHamper bestHamper = new BestHamper(people);
        int foundNumber = bestHamper.getTotalNutrition("Calories");

        assertNotNull("Method getTotalNutrition did not return the total client protein value: ", foundNumber);
    }

    @Test
    public void testBestHamperGetTotalWholeGrains() {
        Person person1 = new Person(validClientID);
        Person person2 = new Person(validClientID);

        Person[] people = new person[2];
        people[0] = person1;
        people[1] = person2;

        BestHamper bestHamper = new BestHamper(people);
        int foundNumber = bestHamper.getTotalNutrition("WholeGrains");

        assertNotNull("Method getTotalNutrition did not return the total client protein value: ", foundNumber);
    }

    @Test
    public void testBestHamperGetTotalProteins() {
        Person person1 = new Person(validClientID);
        Person person2 = new Person(validClientID);

        Person[] people = new person[2];
        people[0] = person1;
        people[1] = person2;

        BestHamper bestHamper = new BestHamper(people);
        int foundNumber = bestHamper.getTotalNutrition("Protein");

        assertNotNull("Method getTotalNutrition did not return the total client protein value: ", foundNumber);
    }

    @Test
    public void testBestHamperGetTotalFruitVeggies() {
        Person person1 = new Person(validClientID);
        Person person2 = new Person(validClientID);

        Person[] people = new person[2];
        people[0] = person1;
        people[1] = person2;

        BestHamper bestHamper = new BestHamper(people);
        int foundNumber = bestHamper.getTotalNutrition("FruitVeggies");

        assertNotNull("Method getTotalNutrition did not return the total client protein value: ", foundNumber);
    }

    @Test
    public void testBestHamperGetTotalOther() {
        Person person1 = new Person(validClientID);
        Person person2 = new Person(validClientID);

        Person[] people = new person[2];
        people[0] = person1;
        people[1] = person2;

        BestHamper bestHamper = new BestHamper(people);
        int foundNumber = bestHamper.getTotalNutrition("Other");

        assertNotNull("Method getTotalNutrition did not return the total client protein value: ", foundNumber);
    }

    @Test
    public void testFindBestHamper(){
        Person person1 = new Person(validClientID);
        Person person2 = new Person(validClientID);

        Person[] people = new person[2];
        people[0] = person1;
        people[1] = person2;

        BestHamper bestHamper = new BestHamper(people);
        List<HashMap<String, Integer>> actualResults = bestHamper.findBestHamper();
        assertNotNull("Method findBestHamper did not return the best hamper available: ", actualResults);

    }
}