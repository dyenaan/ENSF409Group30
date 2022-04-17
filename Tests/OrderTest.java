package edu.ucalgary.ensf409;

import java.util.*;
import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Ahmad Khaled, Pansilu Wickramasinghe, Dyenaan Dapoet, Esohe Aideyan.
 * @version 1.0
 * @since 1.0
 */

/*
 * The purpose of the OrderTest class is to test the functionality of the Order class.
 */

public class OrderTest{

    int mCount = 1;
    int invMCount = -2; // Invalid male count
    int fCount = 0;
    int uECount = 0;
    int oECount = 0;

    // The testOrderEmptyOrderList checks if the Order class throws an IllegalArgumentException if the OrderList is empty

    @Test
    public void testOrderEmptyOrderList() {
        boolean correctException = false;
        ArrayList<Map<String,String>> emptyOrderList = new ArrayList<>();

        try{
            Order order = new Order(emptyOrderList);
        }
        catch(IllegalArgumentException e){
            correctException = true;
        } catch (StockNotAvailableException | HamperAlreadyFoundException ignore) {}

        assertTrue("Order constructor did not throw an IllegalArgumentException for an empty order list.", correctException);
    }

    // The testInvalidOrderList checks if the Order class throws an IllegalArgumentException if the OrderList is invalid

    @Test
    public void testInvalidOrderList() {
        boolean correctException = false;
        ArrayList<Map<String,String>> invalidOrderList = new ArrayList<>();
        Map<String, String> family = new HashMap<>();
        family.put("Male", Integer.toString(invMCount)); // Invalid male count
        family.put("Female", Integer.toString(fCount));
        family.put("ChildUE", Integer.toString(uECount));
        family.put("ChildOE", Integer.toString(oECount));
        invalidOrderList.add(family);

        try{
            Order order = new Order(invalidOrderList);
        }
        catch(IllegalArgumentException e){
            correctException = true;
        } catch (StockNotAvailableException | HamperAlreadyFoundException ignore) {}

        assertTrue("Order constructor did not throw an IllegalArgumentException for an invalid order list.", correctException);
    }

    /* The testMissingClientTypeOrderList checks if the Order class throws an IllegalArgumentException
    * if the OrderList is missing a client type */

    @Test
    public void testMissingClientTypeOrderList() {
        boolean correctException = false;
        ArrayList<Map<String,String>> missingOrderList = new ArrayList<>();
        Map<String, String> family = new HashMap<>();
        family.put("Male", Integer.toString(mCount));
        family.put("Female", Integer.toString(fCount));
        family.put("ChildUE", Integer.toString(uECount));
        missingOrderList.add(family);

        try{
            Order order = new Order(missingOrderList);
        }
        catch(IllegalArgumentException e){
            correctException = true;
        } catch (StockNotAvailableException | HamperAlreadyFoundException ignore) {}

        assertTrue("Order constructor did not throw an IllegalArgumentException for an order list with a missing client type.", correctException);
    }

    // The testValidConstructor method tests if the constructor runs properly if given a valid input

    @Test
    public void testValidConstructor() throws HamperAlreadyFoundException, StockNotAvailableException {
        ArrayList<Map<String,String>> validOrderList = new ArrayList<>();
        Map<String, String> family = new HashMap<>();
        family.put("Male", Integer.toString(mCount));
        family.put("Female", Integer.toString(fCount));
        family.put("ChildOE", Integer.toString(oECount));
        family.put("ChildUE", Integer.toString(uECount));
        validOrderList.add(family);
        Order order = new Order(validOrderList);
        assertNotNull("Order constructor did not create an object when given a valid count:", order);
    }

    // The testGetFamilies method tests the getFamilies getter

    @Test
    public void testGetFamilies() throws HamperAlreadyFoundException, StockNotAvailableException {
        ArrayList<Map<String,String>> validOrderList = new ArrayList<>();
        Map<String, String> family = new HashMap<>();
        family.put("Male", Integer.toString(mCount));
        family.put("Female", Integer.toString(fCount));
        family.put("ChildOE", Integer.toString(oECount));
        family.put("ChildUE", Integer.toString(uECount));
        validOrderList.add(family);
        Order order = new Order(validOrderList);
        Family[] families = order.getFamilies();
        assertNotNull("Method getPeople did not return an object of type Family: " , families);
    }

    /* Note: The getOrderCompleted method could not be tested because the value of the getter depends on
    * the state of the database. */
}


